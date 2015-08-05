package com.cgaf.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cgaf.dao.CtTipoVariableDao;
import com.cgaf.model.CtTipoVariable;

public class CtTipoVariableDaoImpl extends HibernateDaoSupport implements CtTipoVariableDao {
	
	private static final Logger log = Logger.getLogger(CtTipoVariableDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<CtTipoVariable> getTipos() throws Exception {
		List<CtTipoVariable> listOfTypes = getHibernateTemplate().find("FROM CtTipoVariable");
		log.info("Se encontraron " + listOfTypes.size() + " registros en la tabla CT_TIPO_VARIABLE.");
		return listOfTypes;
	}

}
