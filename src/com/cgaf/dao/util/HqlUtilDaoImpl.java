package com.cgaf.dao.util;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cgaf.dao.HqlUtilDao;
import com.cgaf.model.CtConcepto;
import com.cgaf.model.HtGeneric;

public class HqlUtilDaoImpl extends HibernateDaoSupport implements HqlUtilDao {
	
	private static final Logger log = Logger.getLogger(HqlUtilDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<HtGeneric> executeQuery(CtConcepto object, Timestamp fechaIni, Timestamp fechaFin) {
		Session session = null;
		String tabla;
		if (object.getCtTabla().getDescTabla().equals("HT_CINCAMBIENTAL")) {
			tabla = "HtCincambiental";
		} else if (object.getCtTabla().getDescTabla().equals("HT_CINCCROMATOGRAFO")) {
			tabla = "HtCinccromatografo";
		} else if (object.getCtTabla().getDescTabla().equals("HT_CINCENERGIA")) {
			tabla = "HtCincenergia";
		} else {
			tabla = "";
		}
		String query = "FROM " + tabla + " WHERE CtVariable.idVariable = " + object.getCtVariable().getIdVariable();
		try {
			session = this.getHibernateTemplate().getSessionFactory().openSession();
			Query hql = session.createQuery(query);
			List<HtGeneric> listOfResults = (List<HtGeneric>) hql.list();
			log.debug(hql.toString());
			if (listOfResults.size() > 0) {
				log.info("Se obtuvieron " + listOfResults.size() + " registros de la tabla "
						+ object.getCtTabla().getDescTabla() + " buscando la variable " 
						+ object.getCtVariable().getIdVariable());
			} else {
				log.info("No se encontraron registros.");
			}
			return listOfResults;
		} catch (HibernateException e) {
			log.error("Ocurrio un error al realizar busqueda con HQL." , e);
			return null;
		}
	}
	
}
