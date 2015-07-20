package com.cgaf.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.cgaf.bo.MenuPrincipalBo;
import com.cgaf.dao.CtTablaDao;
import com.cgaf.model.CtTabla;
import com.cgaf.model.CtTipos;
import com.cgaf.model.CtVariables;

/**
*
* @author Franck RVX
*/

public class MenuPrincipalBoImpl implements MenuPrincipalBo {
	
	CtTablaDao ctTablaDao;

	@Override
	public List<CtTipos> getTipos() throws Exception {
		List<CtTipos> tipos = new ArrayList<CtTipos>();
        tipos.add(new CtTipos(1, "Todas"));
        tipos.add(new CtTipos(2, "Ambientales"));
        tipos.add(new CtTipos(3, "Cromatógrafos"));
        tipos.add(new CtTipos(4, "Energía"));
		return tipos;
	}

	@Override
	public List<CtVariables> getVariables() throws Exception {
		List<CtVariables> variables = new ArrayList<CtVariables>();
		variables.add(new CtVariables(1, "Todas"));
		variables.add(new CtVariables(2, "Temperatura atmosférica"));
		variables.add(new CtVariables(3, "Humedad atmosférica"));
		variables.add(new CtVariables(4, "Presión atmosférica"));
		variables.add(new CtVariables(5, "Poder Calorífico Inferior kJ/kg"));
		variables.add(new CtVariables(6, "Poder Calorífico Superior kJ/kg"));
		variables.add(new CtVariables(7, "Densidad Relativa"));
		variables.add(new CtVariables(8, "Poder Calorífico Inferior kJ/M3"));
		variables.add(new CtVariables(9, "Poder Calorífico Superior kJ/M3"));
		variables.add(new CtVariables(10, "Factor de Potencia"));
		variables.add(new CtVariables(11, "Energía Línea 1 Principal Entrada"));
		variables.add(new CtVariables(12, "Energía Línea 1 Principal Salida"));
		variables.add(new CtVariables(13, "Energía Línea 2 Principal Entrada"));
		variables.add(new CtVariables(14, "Energía Línea 2 Principal Salida"));
		variables.add(new CtVariables(15, "Energía Línea 1 Principal Q1"));
		variables.add(new CtVariables(16, "Energía Línea 1 Principal Q4"));
		variables.add(new CtVariables(17, "Energía Línea 2 Principal Q1"));
		variables.add(new CtVariables(18, "Energía Línea 2 Principal Q4"));
		variables.add(new CtVariables(19, "Energía Línea 1 Principal Entrada"));
		variables.add(new CtVariables(20, "Energía Línea 1 Principal Salida"));
		variables.add(new CtVariables(21, "Energía Línea 2 Principal Entrada"));
		variables.add(new CtVariables(22, "Energía Línea 2 Principal Salida"));
		variables.add(new CtVariables(23, "Energía Línea 1 Principal Q1"));
		variables.add(new CtVariables(24, "Energía Línea 1 Principal Q4"));
		variables.add(new CtVariables(25, "Energía Línea 2 Principal Q1"));
		variables.add(new CtVariables(26, "Energía Línea 2 Principal Q4"));
		return variables;
	}

	@Override
	public List<String> selectVariables(List<String> selectedTipos) throws Exception {
		List<String> newSelectedVarList = new ArrayList<String>();
		for (String item : selectedTipos) {
			if (item.equals("1")) {
				newSelectedVarList.add("1");
				newSelectedVarList.add("2");
				newSelectedVarList.add("3");
				newSelectedVarList.add("4");
				newSelectedVarList.add("5");
				newSelectedVarList.add("6");
				newSelectedVarList.add("7");
				newSelectedVarList.add("8");
				newSelectedVarList.add("9");
				newSelectedVarList.add("10");
				newSelectedVarList.add("11");
				newSelectedVarList.add("12");
				newSelectedVarList.add("13");
				newSelectedVarList.add("14");
				newSelectedVarList.add("15");
				newSelectedVarList.add("16");
				newSelectedVarList.add("17");
				newSelectedVarList.add("18");
				newSelectedVarList.add("19");
				newSelectedVarList.add("20");
				newSelectedVarList.add("21");
				newSelectedVarList.add("22");
				newSelectedVarList.add("23");
				newSelectedVarList.add("24");
				newSelectedVarList.add("25");
				newSelectedVarList.add("26");
				return newSelectedVarList;
			}
			if (item.equals("2")) {
				newSelectedVarList.add("2");
				newSelectedVarList.add("3");
				newSelectedVarList.add("4");
			}
			if (item.equals("3")) {
				newSelectedVarList.add("5");
				newSelectedVarList.add("6");
				newSelectedVarList.add("7");
				newSelectedVarList.add("8");
				newSelectedVarList.add("9");
				newSelectedVarList.add("10");
			}
			if (item.equals("4")) {
				newSelectedVarList.add("11");
				newSelectedVarList.add("12");
				newSelectedVarList.add("13");
				newSelectedVarList.add("14");
				newSelectedVarList.add("15");
				newSelectedVarList.add("16");
				newSelectedVarList.add("17");
				newSelectedVarList.add("18");
				newSelectedVarList.add("19");
				newSelectedVarList.add("20");
				newSelectedVarList.add("21");
				newSelectedVarList.add("22");
				newSelectedVarList.add("23");
				newSelectedVarList.add("24");
				newSelectedVarList.add("25");
				newSelectedVarList.add("26");
			}
		}
		return newSelectedVarList;
	}

	@Override
	public void saveTabla() throws Exception {
		CtTabla tabla = new CtTabla(1, "Nueva tabla");
		ctTablaDao.saveTable(tabla);
	}

	public CtTablaDao getCtTablaDao() {
		return ctTablaDao;
	}

	public void setCtTablaDao(CtTablaDao ctTablaDao) {
		this.ctTablaDao = ctTablaDao;
	}

}
