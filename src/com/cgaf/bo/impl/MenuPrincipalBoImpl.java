package com.cgaf.bo.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cgaf.bo.MenuPrincipalBo;
import com.cgaf.dao.CtConceptoDao;
import com.cgaf.dao.CtTablaDao;
import com.cgaf.dao.CtVariableDao;
import com.cgaf.dao.HqlUtilDao;
import com.cgaf.model.CtConcepto;
import com.cgaf.model.CtTipos;
import com.cgaf.model.CtVariable;
import com.cgaf.model.HtGeneric;
import com.cgaf.vo.DatosBasicos;

/**
*
* @author Franck RVX
*/

public class MenuPrincipalBoImpl implements MenuPrincipalBo {
	
	CtTablaDao ctTablaDao;
	CtVariableDao ctVariableDao;
	CtConceptoDao ctConceptoDao;
	HqlUtilDao hqlUtilDao;
	
	private static final Logger log = Logger.getLogger(MenuPrincipalBoImpl.class);
	
	@Override
	public List<DatosBasicos> getDataFromTables(List<CtConcepto> listOfVars, Date fechaInicio, Date fechaFin) throws Exception {
		log.debug("Inicia proceso de extraccion de informacion para ser presentada en el reporte.");
		long mili = 86399000l;
		long _5min = 300000l;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		String fechaInicial = format.format(fechaInicio);
		String fechaFinal = format.format(new Date(fechaFin.getTime() + mili));
		List<HtGeneric> listOfResults = new ArrayList<HtGeneric>();
		for (CtConcepto item : listOfVars) {
			listOfResults.addAll(hqlUtilDao.executeQuery(item, fechaInicial, fechaFinal));
		}
		log.debug("Total de registros obtenidos " + listOfResults.size());
		List<DatosBasicos> listToReturn = new ArrayList<DatosBasicos>();
		int i = 0;
		for (HtGeneric item : listOfResults) {
			DatosBasicos objToAdd = new DatosBasicos();
			objToAdd.setId(++i);
			objToAdd.setFechaIni(new Timestamp(item.getFechPeriodo().getTime()));
			objToAdd.setFechaFin(new Timestamp(item.getFechPeriodo().getTime() + _5min));
			if (item.getCtVariable().getIdVariable().equals(200002002)) {
				objToAdd.setTatm(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002003)) {
				objToAdd.setHatm(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002004)) {
				objToAdd.setPatm(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002010)) {
				objToAdd.setEnLin1PrinEnt(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002011)) {
				objToAdd.setEnLin1PrinSal(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002012)) {
				objToAdd.setEnLin1RespEnt(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002013)) {
				objToAdd.setEnLin1RespSal(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002014)) {
				objToAdd.setEnLin2PrinEnt(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002015)) {
				objToAdd.setEnLin2PrinSal(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002016)) {
				objToAdd.setEnLin2RespEnt(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002017)) {
				objToAdd.setEnLin2RespSal(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002018)) {
				objToAdd.setpCIgas(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002019)) {
				objToAdd.setpCSgas(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002020)) {
				objToAdd.setDensGas(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002021)) {
				objToAdd.setFactPot(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(200002023)) {
				objToAdd.setEnLin1PrinEnt(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(280002006)) {
				objToAdd.setPotenciaV(item.getValValor());
			}
			if (item.getCtVariable().getIdVariable().equals(280002006)) {
				objToAdd.setPotenciaV(item.getValValor());
			}	
			listToReturn.add(objToAdd);
		}
		return listToReturn;
	}

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
				List<CtVariable> listOfVars = ctVariableDao.getVariables();
				newSelectedVarList.add("1");
				for (CtVariable variable : listOfVars) {
					newSelectedVarList.add(variable.getIdVariable().toString());
				}
				return newSelectedVarList;
			}
			if (item.equals("2")) {
				Integer idTabla = ctTablaDao.getIdTabla("HT_CINCAMBIENTAL");
				List<String> idsVariables = ctConceptoDao.getIdVariables(idTabla);
				newSelectedVarList.addAll(idsVariables);
			}
			if (item.equals("3")) {
				Integer idTabla = ctTablaDao.getIdTabla("HT_CINCCROMATOGRAFO");
				List<String> idsVariables = ctConceptoDao.getIdVariables(idTabla);
				newSelectedVarList.addAll(idsVariables);
			}
			if (item.equals("4")) {
				Integer idTabla = ctTablaDao.getIdTabla("HT_CINCENERGIA");
				List<String> idsVariables = ctConceptoDao.getIdVariables(idTabla);
				newSelectedVarList.addAll(idsVariables);
			}
		}
		return newSelectedVarList;
	}
	
	@Override
	public List<CtConcepto> getTableNames(List<String> variables) throws Exception {
		List<Integer> newVars = new ArrayList<Integer>();
		for (String item : variables) {
			newVars.add(Integer.valueOf(item));
		}
		return ctConceptoDao.getRegistros(newVars);
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

	public CtTablaDao getCtTablaDao() {
		return ctTablaDao;
	}

	public void setCtTablaDao(CtTablaDao ctTablaDao) {
		this.ctTablaDao = ctTablaDao;
	}

	public CtConceptoDao getCtConceptoDao() {
		return ctConceptoDao;
	}

	public void setCtConceptoDao(CtConceptoDao ctConceptoDao) {
		this.ctConceptoDao = ctConceptoDao;
	}

	public HqlUtilDao getHqlUtilDao() {
		return hqlUtilDao;
	}

	public void setHqlUtilDao(HqlUtilDao hqlUtilDao) {
		this.hqlUtilDao = hqlUtilDao;
	}

}