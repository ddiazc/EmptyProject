package com.cgaf.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
* @author Franck RVX
*/

public class Validators {
	
	/**
	 * Metodo que valida la congruencia de las fechas de busqueda.
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * 
	 */
	public static Map<Boolean, String> validaFechas(Date fechaInicio, Date fechaFin) {
		Map<Boolean, String> response = new HashMap<Boolean, String>();
		if (fechaInicio == null || fechaFin == null || fechaInicio.after(fechaFin)) {
			response.put(false, "La fecha inicio debe ser menor a la fecha final.");
			return response;
		} else {
			Calendar calIni = Calendar.getInstance();
			Calendar calFin = Calendar.getInstance();
			calIni.setTime(fechaInicio);
			calFin.setTime(fechaFin);
			if (calIni.get(Calendar.MONTH) != calFin.get(Calendar.MONTH)) {
				response.put(false, "El rango de los días debe estár dentro del mismo mes.");
				return response;
			} else {
				response.put(true, "");
				return response;
			}
		}
	}
	
	/**
	 * Metodo que valida las opciones seleccionadas en los combos.
	 * 
	 * @param selectedTipos Valores seleccionados en el combo de tipos.
	 * @param selectedVariables Valores seleccionados en el combo de variables.
	 * @return
	 * 
	 */
	public static Map<Boolean, String> validaCombos(List<String> selectedTipos, List<String> selectedVariables) {
		Map<Boolean, String> response = new HashMap<Boolean, String>();
		if (selectedTipos.isEmpty() || selectedVariables.isEmpty()) {
			response.put(false, "Debe seleccionar al menos un tipo y una variable.");
			return response;
		} else {
			response.put(true, "");
			return response;
		}
	}
	
	public static Map<Boolean, String> validaCombos(List<Integer> selectedValues, String nombre) {
		Map<Boolean, String> response = new HashMap<Boolean, String>();
		if (selectedValues.isEmpty()) {
			response.put(false, "Debe seleccionar al menos un valor de la lista " + nombre + ".");
			return response;
		} else {
			response.put(true, "");
			return response;
		}
	}

}
