package com.cgaf.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cgaf.dao.CtTablaDao;
import com.cgaf.model.CtTabla;

public class CtTablaDaoImpl extends HibernateDaoSupport implements CtTablaDao {
	
	private static final Logger log = Logger.getLogger(CtTablaDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Integer getIdTabla(String tableName) throws Exception {
		Object[] params = new Object[]{tableName};
		List<CtTabla> listOfTables = getHibernateTemplate().find("FROM CtTabla WHERE descTabla = ?", params);
		if (listOfTables.size() > 0) {
			log.info("Se encontro una tabla con el nombre " + tableName);
			return listOfTables.get(0).getIdTabla();
		} else {
			return null;
		}
	}

}
