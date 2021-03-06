package com.cgaf.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cgaf.dao.CtConceptoDao;
import com.cgaf.model.CtConcepto;

public class CtConceptoDaoImpl extends HibernateDaoSupport implements CtConceptoDao {
	
	private static final Logger log = Logger.getLogger(CtConceptoDaoImpl.class);

	@Override
	public List<String> getIdVariables(Integer idTabla) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(CtConcepto.class);
		criteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("CtVariable", FetchMode.JOIN);
		criteria.setFetchMode("CtTabla", FetchMode.JOIN);
		criteria.add(Property.forName("CtTabla.idTabla").eq(idTabla));
		@SuppressWarnings("unchecked")
		List<CtConcepto> listOfRegs = getHibernateTemplate().findByCriteria(criteria);
		if (listOfRegs.size() > 0) {
			log.info("Se encontraron " + listOfRegs.size() + " registros con el ID de la tabla " + idTabla);
			List<String> listOfResults = new ArrayList<String>();
			for (CtConcepto item : listOfRegs) {
				listOfResults.add(String.valueOf(item.getCtVariable().getIdVariable()));
			}
			return listOfResults;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CtConcepto> getRegistros(List<Integer> variables) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(CtConcepto.class);
		criteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("CtVariable", FetchMode.JOIN);
		criteria.setFetchMode("CtTabla", FetchMode.JOIN);
		criteria.add(Property.forName("CtVariable.idVariable").in(variables));
		List<CtConcepto> listOfRecords = getHibernateTemplate().findByCriteria(criteria);
		if (listOfRecords.size() > 0) {
			log.info("Se encontraron " + listOfRecords.size() + " registros en la tabla CT_CONCEPTO.");
			return listOfRecords;
		} else {
			return null;
		}
	}

}
