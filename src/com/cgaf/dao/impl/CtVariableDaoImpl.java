package com.cgaf.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cgaf.dao.CtVariableDao;
import com.cgaf.model.CtVariable;

public class CtVariableDaoImpl extends HibernateDaoSupport implements CtVariableDao {
	
	private static final Logger log = Logger.getLogger(CtVariableDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<CtVariable> getVariables() throws Exception {
		List<CtVariable> listOfVariables = getHibernateTemplate().find("FROM CtVariable");
		log.info("Se encontraron " + listOfVariables.size() + " registros en la tabla CT_VARIABLE.");
		return listOfVariables;
	}

}
