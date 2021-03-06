package com.cgaf.bo;

import java.util.Date;
import java.util.List;

import com.cgaf.model.CtConcepto;
import com.cgaf.model.CtTipoVariable;
import com.cgaf.model.CtVariable;
import com.cgaf.vo.DatosBasicosValues;

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
	List<CtTipoVariable> getTipos() throws Exception;
	
	/**
	 * Metodo que obtiene las variables.
	 * Ej. Temperatura, Humedad, etc.
	 * 
	 * @return Devuelve una lista que contiene las variables.
	 * @throws Exception
	 * 
	 */
	List<CtVariable> getVariables() throws Exception;
	
	/**
	 * Metodo que seleciona variables segun el tipo seleccionado.
	 * 
	 * @param selectedTipos los tipos elegidos en el combobox.
	 * @return Devuelve una lista de las variables elegidas.
	 * @throws Exception
	 * 
	 */
	List<String> selectVariables(List<String> selectedTipos) throws Exception;
	
	/**
	 * Metodo que devuelve las etiquetas que se imprimen debajo de los headers.
	 * 
	 * @return Devuelve una lista de strings.
	 * @throws Exception
	 * 
	 */
	List<String> getFirstSubHeaders() throws Exception;
	
	/**
	 * Metodo que devuelve las etiquetas que se imprimen debajo de los primeros sub headers.
	 * 
	 * @return Devuelve una lista de strings.
	 * @throws Exception
	 * 
	 */
	List<String> getSecondSubHeaders() throws Exception;
	
	/**
	 * Metodo que invoca el DAO de la tabla CT_CONCEPTO para obtener los nombres
	 * de las tablas pasadas como argumento.
	 * 
	 * @param variables Lista de los identificadores de las variables de las cuales se
	 * requiere saber el nombre de la tabla donde se enuentran almacenadas.
	 * @return Devuelve una lista de objetos de tipo CtConcepto.
	 * @throws Exception
	 * 
	 */
	List<CtConcepto> getTableNames(List<String> variables) throws Exception;
	
	/**
	 * Metodo que procesa la informacion obtenida de la base de datos para acomodarla
	 * en una lista de tipo View Object.
	 *  
	 * @param listOfVars Lista de objetos que contiene los IDs de variables asi como 
	 * los nombres de las tablas donde debera ser buscada la informacion de las variables.
	 * @return Devuelve una lista que contiene la informacion sobre cada variable y que sera
	 * visualizada en el reporte..
	 * @throws Exception
	 * 
	 */
	List<DatosBasicosValues> getDataFromTables(List<CtConcepto> listOfVars, Date fechaInicio, Date fechaFin) throws Exception;

}
