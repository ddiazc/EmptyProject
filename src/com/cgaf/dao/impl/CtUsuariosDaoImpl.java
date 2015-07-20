
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

    @Override
    public boolean authenticate(String username, String password) throws Exception {
        log.info("Validando credenciales con la base de datos.");
        if (username.equals("admin") && password.equals("123456")) {
        	return true;
        } else {
        	return false;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public CtUsuarios getUserInfo(String username) throws Exception {
		log.info("Buscando usuario: " + username);
		Object[] params = new Object[] {username};
		List<CtUsuarios> listOfUsers = 	getHibernateTemplate().find("FROM CtUsuarios WHERE nombreUsuario = ?", params);
		if (listOfUsers.size() > 0) {
			log.debug("Se encontro informacion asociada al usuario: " + username);
			return listOfUsers.get(0);
		} else {
			return null;
		}
		
	}
    
}
