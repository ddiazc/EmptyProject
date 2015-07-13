
package com.cgaf.dao;

/**
 *
 * @author Franck RVX
 */
public interface CtUsuariosDao {
    
	/**
	 * Metodo que valida las credenciales del usuario con la base de datos.
	 * 
	 * @param username Nombre del usuario que se desea autenticar.
	 * @param password Contraseña del usuario que se desea autenticar.
	 * @return Devuelve verdadero si las credenciales son validas,
	 * de lo contrario devuelve false.
	 * @throws Exception
	 */
    boolean authenticate(String username, String password) throws Exception;
    
}
