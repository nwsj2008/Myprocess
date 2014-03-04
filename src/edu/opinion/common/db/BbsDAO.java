package edu.opinion.common.db;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for Bbs
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see edu.opinion.common.db.Bbs
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BbsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BbsDAO.class);
	// property constants
	public static final String URL = "url";
	public static final String HASHCODE = "hashcode";
	public static final String TOPIC = "topic";
	public static final String AUTHOR = "author";
	public static final String RENUM = "renum";
	public static final String TAG = "tag";
	public static final String IDKEY = "idkey";
	public static final String CONTENT = "content";

	protected void initDao() {
		// do nothing
	}

	public void save(Bbs transientInstance) {
		log.debug("saving Bbs instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Bbs persistentInstance) {
		log.debug("deleting Bbs instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bbs findById(java.lang.String id) {
		log.debug("getting Bbs instance with id: " + id);
		try {
			Bbs instance = (Bbs) getHibernateTemplate().get(
					"edu.opinion.common.db.Bbs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Bbs instance) {
		log.debug("finding Bbs instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Bbs instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Bbs as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findByHashcode(Object hashcode) {
		return findByProperty(HASHCODE, hashcode);
	}

	public List findByTopic(Object topic) {
		return findByProperty(TOPIC, topic);
	}

	public List findByAuthor(Object author) {
		return findByProperty(AUTHOR, author);
	}

	public List findByRenum(Object renum) {
		return findByProperty(RENUM, renum);
	}

	public List findByTag(Object tag) {
		return findByProperty(TAG, tag);
	}

	public List findByIdkey(Object idkey) {
		return findByProperty(IDKEY, idkey);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Bbs instances");
		try {
			String queryString = "from Bbs";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Bbs merge(Bbs detachedInstance) {
		log.debug("merging Bbs instance");
		try {
			Bbs result = (Bbs) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Bbs instance) {
		log.debug("attaching dirty Bbs instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bbs instance) {
		log.debug("attaching clean Bbs instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BbsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BbsDAO) ctx.getBean("BbsDAO");
	}
}