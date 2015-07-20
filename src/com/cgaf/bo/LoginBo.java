
package com.cgaf.bo;

/**
 *
 * @author Franck RVX
 */
public interface LoginBo {
    
	/**
	 * Metodo que procesa la peticion de autenticacion del usuario.
	 * 
	 * @param username Nombre del usuario que se desea autenticar.
	 * @param password Contraseña del usuario que se desea autenticar.
	 * @return Devuelve verdadero si las credenciales son validas,
	 * de lo contrario devuelve false.
	 * @throws Exception
	 * 
	 */
    boolean authenticate(String username, String password) throws Exception;
    
}
