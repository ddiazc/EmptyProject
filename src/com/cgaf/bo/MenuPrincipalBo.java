package com.cgaf.bo;

import java.util.List;

import com.cgaf.model.CtTipos;
import com.cgaf.model.CtVariables;

/**
*
* @author Franck RVX
*/

public interface MenuPrincipalBo {
	
	/**
	 * Metodo que obtiene los tipos de variables.
	 * Ej. Ambientales, Cromatografos, etc.
	 * 
	 * @return Devuelve una lista que contiene los tipos de variable.
	 * @throws Exception
	 * 
	 */
	List<CtTipos> getTipos() throws Exception;
	
	/**
	 * Metodo que obtiene las variables.
	 * Ej. Temperatura, Humedad, etc.
	 * 
	 * @return Devuelve una lista que contiene las variables.
	 * @throws Exception
	 * 
	 */
	List<CtVariables> getVariables() throws Exception;
	
	/**
	 * Metodo que rellena selleciona variables segun el tipo seleccionado.
	 * 
	 * @param selectedTipos los tipos elegidos en el combobox.
	 * @return Devuelve una lista de las variables elegidas.
	 * @throws Exception
	 * 
	 */
	List<String> selectVariables(List<String> selectedTipos) throws Exception;

}
