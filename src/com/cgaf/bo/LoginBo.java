
package com.cgaf.bo;

/**
 *
 * @author Franck RVX
 */
public interface LoginBo {
    
	/**
	 * M�todo que procesa la peticion de autenticacion del usuario.
	 * 
	 * @param username Nombre del usuario que se desea autenticar.
	 * @param password Contrase�a del usuario que se desea autenticar.
	 * @return Devuelve verdadero si las credenciales son validas,
	 * de lo contrario devuelve false.
	 * @throws Exception
	 * 
	 */
    boolean authenticate(String username, String password) throws Exception;
    
}
