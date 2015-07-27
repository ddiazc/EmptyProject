
package com.cgaf.dao.impl;

import java.util.List;

import com.cgaf.dao.CtUsuariosDao;
import com.cgaf.model.CtUsuarios;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Franck RVX
 */
public class CtUsuariosDaoImpl extends HibernateDaoSupport implements CtUsuariosDao {
    
    private static final Logger log = Logger.getLogger(CtUsuariosDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public CtUsuarios getUserInfo(String username) throws Exception {
		log.info("Buscando usuario: " + username);
		Object[] params = new Object[] {username};
		List<CtUsuarios> listOfUsers = 	getHibernateTemplate().find("FROM CtUsuarios WHERE nombreUsuario = ?", params);
		if (listOfUsers.size() > 0) {
			log.info("Se encontro informacion asociada al usuario: " + username);
			return listOfUsers.get(0);
		} else {
			return null;
		}
		
	}
    
}
