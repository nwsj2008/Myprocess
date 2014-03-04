/**
 * 
 */
package edu.opinion.process.multiThread;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import edu.opinion.common.db.Bbs;
import edu.opinion.common.db.BbsDAO;
import edu.opinion.common.db.Rebbs;
import edu.opinion.common.db.RebbsDAO;
import edu.opinion.model.DataTypeConversion;
import edu.opinion.model.QueryDao;

/**
 * @author hsr
 * 
 */
public class BbsParserThread implements Runnable {

	private List<Element> list;
	private Document dom;
	private String url;
	private String module;
	private XPath topic_xpath;
	private XPath tag_xpath;
	private XPath author_xpath;
	private XPath content_xpath;
	private XPath releasetime_xpath;
	private XPath renum_xpath;
	private XPath reauthor_xpath;
	private XPath retime_xpath;
	private XPath recontent_xpath;
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	private RebbsDAO rebbsDAO = (RebbsDAO) ctx.getBean("RebbsDAO");
	private BbsDAO bbsDAO = (BbsDAO) ctx.getBean("BbsDAO");

	private static Logger logger = Logger.getLogger(BbsParserThread.class);

	public BbsParserThread(Document dom, String url, String module) {
		this.dom = dom;
		this.url = url;
		this.module = module;
		newsXmlRead();
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Override
	public void run() {
		dataExtract();
	}

	private void newsXmlRead() {
		SAXReader saxReader = new SAXReader();
		Document doc = null;

		File xmlfile = new File("Xmlpattern/bbs_pattern.xml");

		try {
			doc = saxReader.read(xmlfile);
			logger.info(module);
			list = doc.selectNodes("//item");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dataExtract() {
		List<Node> nodes;

		for (Element mde : list) {
			String name = mde.element("module").getTextTrim();
			topic_xpath = new DefaultXPath(mde.elementTextTrim("title"));
			tag_xpath = new DefaultXPath(mde.elementTextTrim("edition"));
			author_xpath = new DefaultXPath(mde.elementTextTrim("author"));
			content_xpath = new DefaultXPath(mde.elementTextTrim("content"));
			releasetime_xpath = new DefaultXPath(mde.elementTextTrim("time"));
			renum_xpath = new DefaultXPath(mde.elementTextTrim("reNum"));
			reauthor_xpath = new DefaultXPath(mde.elementTextTrim("reauthor"));
			retime_xpath = new DefaultXPath(mde.elementTextTrim("retime"));
			recontent_xpath = new DefaultXPath(mde.elementTextTrim("recontent"));

			if (name.equals(module) && name.endsWith("topic")) {
				List<String> bist = new LinkedList<String>();
				List<String> bblist = new LinkedList<String>();
				Bbs bbs = new Bbs();
				nodes = topic_xpath.selectNodes(dom);
				bbs.setTopic(nodes.get(0).getText());
				nodes = tag_xpath.selectNodes(dom);
				bbs.setTag(nodes.get(0).getText());
				nodes = author_xpath.selectNodes(dom);
				bbs.setAuthor(nodes.get(0).getText());
				nodes = content_xpath.selectNodes(dom);
				bbs.setContent(nodes.get(0).getText().trim());
				nodes = releasetime_xpath.selectNodes(dom);
				bbs.setReleasetime(DataTypeConversion.timeTypeConversion(nodes
						.get(0).getText()));
				nodes = renum_xpath.selectNodes(dom);
				bbs.setRenum(nodes.get(0).getText());
				bbs.setUrl(url);
				bbs.setIdkey(module + url.substring(url.lastIndexOf("/") + 1));
				bist.add(url);
				bist.add(bbs.getTag());
				bist.add(bbs.getTopic());
				bbs.setHashcode(String.valueOf(QueryDao.getHashcode(bist)));
				bbs.setStoragetime(new Date());
				bbsDAO.save(bbs);

				nodes = reauthor_xpath.selectNodes(dom);
				for (int i = 0; i < nodes.size(); i++) {
					Rebbs rebbs = new Rebbs();
					rebbs.setReauthor(nodes.get(i).getText());
					rebbs.setRecontent(((Node) recontent_xpath.selectNodes(dom)
							.get(i)).getText());
					rebbs.setRetime(DataTypeConversion
							.timeTypeConversion(((Node) retime_xpath
									.selectNodes(dom).get(0)).getText()));
					rebbs.setUrl(url);
					rebbs.setTag(bbs.getTag());
					rebbs.setRetopic(bbs.getTopic());
					rebbs.setIdkey(module
							+ url.substring(url.lastIndexOf("/") + 1));
					rebbs.setStoragetime(new Date());
					bblist.add(url);
					bblist.add(rebbs.getRetopic());
					bblist.add(rebbs.getTag());
					rebbs.setHashcode(String.valueOf(QueryDao
							.getHashcode(bblist)));
					rebbsDAO.save(rebbs);
				}
			} else if (name.equals(module) && name.endsWith("reply")) {
				List<String> rist = new LinkedList<String>();

			}
		}
	}
}
