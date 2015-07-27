
package com.cgaf.dao;

import com.cgaf.model.CtUsuarios;

/**
 *
 * @author Franck RVX
 */
public interface CtUsuariosDao {
    
    /**
     * Metodo que obtiene los datos del asoiciados al usuario.
     * 
     * @param username Nombre del usuario que se desea autenticar.
     * @return Devuelve un objeto de tipo CtUsuarios.
     * @throws Exception
     */
    CtUsuarios getUserInfo(String username) throws Exception;
    
}
