package com.cgaf.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CtVariable> getVariables(List<Integer> tipos) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(CtVariable.class);
		criteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("CtTipoVariable", FetchMode.JOIN);
		criteria.add(Property.forName("CtTipoVariable.idTipoVariable").in(tipos));
		List<CtVariable> listOfRecords = getHibernateTemplate().findByCriteria(criteria);
		if (listOfRecords.size() > 0) {
			log.info("Se encontraron " + listOfRecords.size() + " registros en la tabla CT_VARIABLE.");
			return listOfRecords;
		} else {
			return null;
		}
	}

}
