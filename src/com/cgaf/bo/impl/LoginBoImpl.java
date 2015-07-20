
package com.cgaf.bo.impl;

import org.apache.log4j.Logger;

import com.cgaf.bo.LoginBo;
import com.cgaf.dao.CtUsuariosDao;
import com.cgaf.dao.DHashesDao;
import com.cgaf.model.CtUsuarios;
import com.cgaf.model.DHashes;
import com.cgaf.util.Crypto;

/**
 *
 * @author Franck RVX
 */
public class LoginBoImpl implements LoginBo {
    
	DHashesDao dHashesDao;
    CtUsuariosDao ctUsuariosDao;
    
    private static final Logger log = Logger.getLogger(LoginBoImpl.class);

    @Override
    public boolean authenticate(String username, String password) throws Exception {
    	CtUsuarios userInfo = ctUsuariosDao.getUserInfo(username);
    	if (userInfo != null) {
    		DHashes hash = dHashesDao.getUserHash(userInfo.getIdUsuario());
    		if (hash != null) {
    			return Crypto.decrypt(hash.getHash()).equals(password) ? true : false;
    		} else {
    			log.info("No se encontro ningun hash asociado a " + username);
    			return false;
    		}
    	} else {
    		log.info("No se encontro ningun usuario con " + username);
    		return false;
    	}
    }

    public CtUsuariosDao getCtUsuariosDao() {
        return ctUsuariosDao;
    }

    public void setCtUsuariosDao(CtUsuariosDao ctUsuariosDao) {
        this.ctUsuariosDao = ctUsuariosDao;
    }

	public DHashesDao getdHashesDao() {
		return dHashesDao;
	}

	public void setdHashesDao(DHashesDao dHashesDao) {
		this.dHashesDao = dHashesDao;
	}
    
}
