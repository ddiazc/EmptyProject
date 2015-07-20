package com.cgaf.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cgaf.dao.DHashesDao;
import com.cgaf.model.DHashes;

public class DHashesDaoImpl extends HibernateDaoSupport implements DHashesDao {
	
	private static final Logger log = Logger.getLogger(DHashesDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public DHashes getUserHash(Integer idUsuario) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(DHashes.class);
		criteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("CtUsuarios", FetchMode.JOIN);
		criteria.add(Property.forName("CtUsuarios.idUsuario").eq(idUsuario));
		List<DHashes> listOfHashes = getHibernateTemplate().findByCriteria(criteria);
		if (listOfHashes.size() > 0) {
			log.info("Se encontro hash asociado al usuario con id " + idUsuario);
			return listOfHashes.get(0);
		} else {
			return null;
		}
	}

}
