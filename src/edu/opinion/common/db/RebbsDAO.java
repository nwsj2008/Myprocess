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
 * A data access object (DAO) providing persistence and search support for Rebbs
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see edu.opinion.common.db.Rebbs
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RebbsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(RebbsDAO.class);
	// property constants
	public static final String URL = "url";
	public static final String HASHCODE = "hashcode";
	public static final String RETOPIC = "retopic";
	public static final String REAUTHOR = "reauthor";
	public static final String TAG = "tag";
	public static final String IDKEY = "idkey";
	public static final String RECONTENT = "recontent";

	protected void initDao() {
		// do nothing
	}

	public void save(Rebbs transientInstance) {
		log.debug("saving Rebbs instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Rebbs persistentInstance) {
		log.debug("deleting Rebbs instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rebbs findById(java.lang.String id) {
		log.debug("getting Rebbs instance with id: " + id);
		try {
			Rebbs instance = (Rebbs) getHibernateTemplate().get(
					"edu.opinion.common.db.Rebbs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Rebbs instance) {
		log.debug("finding Rebbs instance by example");
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
		log.debug("finding Rebbs instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Rebbs as model where model."
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

	public List findByRetopic(Object retopic) {
		return findByProperty(RETOPIC, retopic);
	}

	public List findByReauthor(Object reauthor) {
		return findByProperty(REAUTHOR, reauthor);
	}

	public List findByTag(Object tag) {
		return findByProperty(TAG, tag);
	}

	public List findByIdkey(Object idkey) {
		return findByProperty(IDKEY, idkey);
	}

	public List findByRecontent(Object recontent) {
		return findByProperty(RECONTENT, recontent);
	}

	public List findAll() {
		log.debug("finding all Rebbs instances");
		try {
			String queryString = "from Rebbs";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Rebbs merge(Rebbs detachedInstance) {
		log.debug("merging Rebbs instance");
		try {
			Rebbs result = (Rebbs) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Rebbs instance) {
		log.debug("attaching dirty Rebbs instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rebbs instance) {
		log.debug("attaching clean Rebbs instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RebbsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RebbsDAO) ctx.getBean("RebbsDAO");
	}
}