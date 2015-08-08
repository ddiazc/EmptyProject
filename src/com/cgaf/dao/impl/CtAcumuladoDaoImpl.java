package com.cgaf.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cgaf.dao.CtAcumuladoDao;
import com.cgaf.model.CtAcumulado;

public class CtAcumuladoDaoImpl extends HibernateDaoSupport
		implements CtAcumuladoDao {

	private static final Logger log = Logger.getLogger(CtAcumuladoDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<CtAcumulado> findAllCtAcumulados() throws Exception {
		String queryString = "SELECT a FROM CtAcumulado a"
				+ " JOIN FETCH a.CtTipoAcumulado"
				+ " ORDER BY a.idAcumulado";
		List<CtAcumulado> list = new ArrayList<>();
		try {
			list = getHibernateTemplate().find(queryString);
		} catch (Exception e) {
			log.error("Ocurrió un erro al buscar CtAcumulados", e);
		}
		return list;
	}

}
