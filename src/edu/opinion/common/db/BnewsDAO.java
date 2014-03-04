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
 * A data access object (DAO) providing persistence and search support for Bnews
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see edu.opinion.common.db.Bnews
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BnewsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BnewsDAO.class);
	// property constants
	public static final String URL = "url";
	public static final String HASHCODE = "hashcode";
	public static final String TOPIC = "topic";
	public static final String TAG = "tag";
	public static final String SOURCE = "source";
	public static final String CONTENT = "content";

	protected void initDao() {
		// do nothing
	}

	public void save(Bnews transientInstance) {
		log.debug("saving Bnews instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Bnews persistentInstance) {
		log.debug("deleting Bnews instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bnews findById(java.lang.String id) {
		log.debug("getting Bnews instance with id: " + id);
		try {
			Bnews instance = (Bnews) getHibernateTemplate().get(
					"edu.opinion.common.db.Bnews", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Bnews instance) {
		log.debug("finding Bnews instance by example");
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
		log.debug("finding Bnews instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Bnews as model where model."
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

	public List findByTag(Object tag) {
		return findByProperty(TAG, tag);
	}

	public List findBySource(Object source) {
		return findByProperty(SOURCE, source);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Bnews instances");
		try {
			String queryString = "from Bnews";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Bnews merge(Bnews detachedInstance) {
		log.debug("merging Bnews instance");
		try {
			Bnews result = (Bnews) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Bnews instance) {
		log.debug("attaching dirty Bnews instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bnews instance) {
		log.debug("attaching clean Bnews instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BnewsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BnewsDAO) ctx.getBean("BnewsDAO");
	}
}