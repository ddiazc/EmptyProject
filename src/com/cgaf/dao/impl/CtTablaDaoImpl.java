package com.cgaf.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cgaf.dao.CtTablaDao;
import com.cgaf.model.CtTabla;

public class CtTablaDaoImpl extends HibernateDaoSupport implements CtTablaDao {
	
	private static final Logger log = Logger.getLogger(CtTablaDaoImpl.class);

	@Override
	public void saveTable(CtTabla tabla) throws Exception {
		log.debug("Llegando a la capa DAO");
		getHibernateTemplate().save(tabla);
		log.debug("Se guardo una tabla");
	}

}
