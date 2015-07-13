
package com.cgaf.dao.impl;

import com.cgaf.dao.CtUsuariosDao;

import org.apache.log4j.Logger;

/**
 *
 * @author Franck RVX
 */
public class CtUsuariosDaoImpl implements CtUsuariosDao {
    
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
    
}
