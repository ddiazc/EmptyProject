package com.cgaf.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cgaf.dao.CtTipoAcumuladoDao;
import com.cgaf.model.CtTipoAcumulado;

public class CtTipoAcumuladoDaoImpl extends HibernateDaoSupport
		implements CtTipoAcumuladoDao {

	private static final Logger log = Logger.getLogger(CtTipoAcumuladoDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<CtTipoAcumulado> findAllCtTipoAcumulados() throws Exception {
		String queryString = "FROM CtTipoAcumulado";
		List<CtTipoAcumulado> list = new ArrayList<>();
		try {
			list = getHibernateTemplate().find(queryString);
		} catch (Exception e) {
			log.error("Ocurrió un error al obtener CtTipoAcumulado");
		}
		return list;
	}
}
