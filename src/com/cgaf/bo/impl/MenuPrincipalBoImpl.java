package com.cgaf.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.cgaf.bo.MenuPrincipalBo;
import com.cgaf.dao.CtVariableDao;
import com.cgaf.model.CtTipos;
import com.cgaf.model.CtVariable;

/**
*
* @author Franck RVX
*/

public class MenuPrincipalBoImpl implements MenuPrincipalBo {
	
	CtVariableDao ctVariableDao;

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
	public List<CtVariable> getVariables() throws Exception {
		List<CtVariable> variables = new ArrayList<CtVariable>();
		variables.add(new CtVariable(1, "Todas"));
		variables.addAll(ctVariableDao.getVariables());
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
	public List<String> getFirstSubHeaders() throws Exception {
		List<String> subHeaders = new ArrayList<String>();
		subHeaders.add("Unidad");
		subHeaders.add("yyyy/mm/dd hh:mm:ss");
		subHeaders.add("yyyy/mm/dd hh:mm:ss");
		subHeaders.add("sin unidad");
		subHeaders.add("kJ/kWh");
		subHeaders.add("kJ/h");
		subHeaders.add("celsius");
		subHeaders.add("porcentaje");
		subHeaders.add("bar");
		subHeaders.add("kW");
		subHeaders.add("kW");
		subHeaders.add("kW");
		subHeaders.add("kWh");
		subHeaders.add("kWh");
		subHeaders.add("kWh");
		subHeaders.add("kWh");
		subHeaders.add("kWh");
		subHeaders.add("kWh");
		subHeaders.add("kWh");
		subHeaders.add("kWh");
		subHeaders.add("kWh");
		subHeaders.add("kWh");
		subHeaders.add("kJ/kg");
		subHeaders.add("kJ/kg");
		subHeaders.add("sin unidad");
		subHeaders.add("sin unidad");
		subHeaders.add("bar");
		subHeaders.add("kVARh");
		subHeaders.add("kVARh");
		subHeaders.add("kVARh");
		subHeaders.add("kVARh");
		subHeaders.add("kVARh");
		subHeaders.add("kVARh");
		subHeaders.add("kVARh");
		subHeaders.add("kVARh");
		subHeaders.add("kJ/M3");
		subHeaders.add("kJ/M3");
		return subHeaders;
	}
	
	@Override
	public List<String> getSecondSubHeaders() throws Exception {
		List<String> subHeaders = new ArrayList<String>();
		subHeaders.add("Tag");
		subHeaders.add("FechaIni");
		subHeaders.add("FechaFin");
		subHeaders.add("Carga");
		subHeaders.add("CTUNGg");
		subHeaders.add("CTOVg");
		subHeaders.add("Tatm");
		subHeaders.add("Hatm");
		subHeaders.add("Patm");
		subHeaders.add("CDDreal");
		subHeaders.add("Potencia_v");
		subHeaders.add("Potencia");
		subHeaders.add("EnTotal");
		subHeaders.add("EnCinco");
		subHeaders.add("EnLin1PrinEnt");
		subHeaders.add("EnLin1PrinSal");
		subHeaders.add("EnLin1RespEnt");
		subHeaders.add("EnLin1RespSal");
		subHeaders.add("EnLin2PrinEnt");
		subHeaders.add("EnLin2PrinSal");
		subHeaders.add("EnLin2RespEnt");
		subHeaders.add("EnLin2RespSal");
		subHeaders.add("PCIgas");
		subHeaders.add("PCSgas");
		subHeaders.add("DensGas");
		subHeaders.add("FactPot");
		subHeaders.add("PresCond");
		subHeaders.add("EnLin1PrinReacQ1");
		subHeaders.add("EnLin1PrinReacQ4");
		subHeaders.add("EnLin1RespReacQ1");
		subHeaders.add("EnLin1RespReacQ4");
		subHeaders.add("EnLin2PrinReacQ1");
		subHeaders.add("EnLin2PrinReacQ4");
		subHeaders.add("EnLin2RespReacQ1");
		subHeaders.add("EnLin2RespReacQ4");
		subHeaders.add("PCIgasM3");
		subHeaders.add("PCSgasM3");
		return subHeaders;
	}

	public CtVariableDao getCtVariableDao() {
		return ctVariableDao;
	}

	public void setCtVariableDao(CtVariableDao ctVariableDao) {
		this.ctVariableDao = ctVariableDao;
	}

}
