
package com.cgaf.bo.impl;

import com.cgaf.bo.LoginBo;
import com.cgaf.dao.CtUsuariosDao;

/**
 *
 * @author Franck RVX
 */
public class LoginBoImpl implements LoginBo {
    
    CtUsuariosDao ctUsuariosDao;

    @Override
    public boolean authenticate(String username, String password) throws Exception {
        return ctUsuariosDao.authenticate(username, password);
    }

    public CtUsuariosDao getCtUsuariosDao() {
        return ctUsuariosDao;
    }

    public void setCtUsuariosDao(CtUsuariosDao ctUsuariosDao) {
        this.ctUsuariosDao = ctUsuariosDao;
    }
    
}
