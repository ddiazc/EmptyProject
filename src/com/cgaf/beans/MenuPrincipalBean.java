package com.cgaf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cgaf.bo.MenuPrincipalBo;
import com.cgaf.filters.Util;
import com.cgaf.model.CtConcepto;
import com.cgaf.model.CtTipos;
import com.cgaf.model.CtVariable;
import com.cgaf.util.Validators;
import com.cgaf.util.excel.FileManager;
import com.cgaf.vo.DatosBasicos;

/**
*
* @author Franck RVX
*/

@ManagedBean(name = "menuP")
@ViewScoped
public class MenuPrincipalBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean renderedOriginales;
	private boolean renderedRecords;
	private Date fechaInicio;
	private Date fechaFin;
	private String fileName;
	private String headerDialogString;
	private String messagePage;
	private List<String> selectedTipos;
	private List<String> selectedVars;
	private List<CtTipos> tipos;
	private List<CtVariable> variables;
	private List<DatosBasicos> listOfRecords;
	private static final String FAILURE;
	
	private boolean carga;
	private boolean cTUNGg;
	private boolean cTOVg;
	private boolean tatm;
	private boolean hatm;
	private boolean patm;
	private boolean cDDreal;
	private boolean potenciaV;
	private boolean potencia;
	private boolean enTotal;
	private boolean enCinco;
	private boolean enLin1PrinEnt;
	private boolean enLin1PrinSal;
	private boolean enLin1RespEnt;
	private boolean enLin1RespSal;
	private boolean enLin2PrinEnt;
	private boolean enLin2PrinSal;
	private boolean enLin2RespEnt;
	private boolean enLin2RespSal;
	private boolean pCIgas;
	private boolean pCSgas;
	private boolean densGas;
	private boolean factPot;
	private boolean presCond;
	private boolean enLin1PrinReacQ1;
	private boolean enLin1PrinReacQ4;
	private boolean enLin1RespReacQ1;
	private boolean enLin1RespReacQ4;
	private boolean enLin2PrinReacQ1;
	private boolean enLin2PrinReacQ4;
	private boolean enLin2RespReacQ1;
	private boolean enLin2RespReacQ4;
	private boolean pCIgasM3;
	private boolean pCSgasM3;
	
	@ManagedProperty(value = "#{menuPrincipalBo}")
	MenuPrincipalBo menuPrincipalBo;
	
	@ManagedProperty(value = "#{fileManager}")
	FileManager fileManager;
	
	private static final Logger log = Logger.getLogger(MenuPrincipalBean.class);
	
	static {
		FAILURE =  "¡Ups! Algo salió mal al intentar cargar la página."
				+ " Por favor, inténtelo más tarde o contacte al administrador.";
	}
	
	/**
	 * Carga las variables iniciales de la pagina.
	 * 
	 */
	@PostConstruct
    public void init() {
		try {
			setFileName("Reporte");
			setRendered();
			setHeaderDialogString("Process...");
			setMessagePage(FAILURE);
			setRenderedOriginales(false);
			setRenderedRecords(false);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", getMessagePage()));
			log.error("Error al inicializar las variables.", e);
		}
	}
	
	/**
	 * Metodo que configura la pagina para el reporte
	 * de datos originales.
	 * 
	 */
	public void reporteOriginales() {
		log.info("Habilitando la vista del reporte de datos originales.");
		try {
			setRenderedOriginales(true);
			setTipos(menuPrincipalBo.getTipos());
			setVariables(menuPrincipalBo.getVariables());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", getMessagePage()));
			log.error("Error al inicializar los comboBox de Tipos y Variables.", e);
		}
	}
	
	/**
	 * Metodo que se ejecuta para generar el reporte solicitado.
	 * 
	 */
	public void getReporte() {
		try {
			if (checkInputData()) {
				List<CtConcepto> listOfRecords = menuPrincipalBo.getTableNames(getSelectedVars());
				if (listOfRecords.size() > 0) {
					setRenderedRecords(true);
					List<DatosBasicos> listOfVO = menuPrincipalBo.getDataFromTables(listOfRecords, getFechaInicio(), getFechaFin());
					setListOfRecords(listOfVO);
				} else {
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", 
									"No se encontró ningún registro con las variables solicitadas."));
					return;
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", getMessagePage()));
				return;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", getMessagePage()));
			log.error("Error al mostrar el reporte.", e);
		}
	}
	
	/**
	 * Metodo que se encarga de dar formato al archivo excel generado.
	 * 
	 * @param document
	 * 
	 */
	public void postProcessXLS(Object document) {
		try {
			HSSFWorkbook xlsDocument = (HSSFWorkbook) document;
			fileManager.addRow(xlsDocument, menuPrincipalBo.getSecondSubHeaders());
			fileManager.addRow(xlsDocument, menuPrincipalBo.getFirstSubHeaders());
			fileManager.formatXLS(xlsDocument);
			fileManager.styleCells(xlsDocument);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", getMessagePage()));
			log.error("Error al dar formato al archivo excel.", e);
		}
	}
	
	/**
	 * Metodo que invoca los validadores.
	 * 
	 * @return
	 * 
	 */
	public boolean checkInputData() {
		Map<Boolean, String> response;
		try {
			response = Validators.validaCombos(getSelectedTipos(), getSelectedVars());
			if (response.containsKey(false)) {
				setMessagePage(response.get(false));
				return false;
			}
			response = Validators.validaFechas(getFechaInicio(), getFechaFin());
			if (response.containsKey(false)) {
				setMessagePage(response.get(false));
				return false;
			}
			return true;
		} catch (Exception e) {
			setMessagePage(FAILURE);
			log.error("Error en las validaciones.", e);
			return false;
		}
	}
	
	/**
	 * Metodo que se ejecuta cuando se eligen todas las opciones
	 * de los tipos.
	 * 
	 */
	public void eligeTipo() {
		try {
			List<String> newSelectedTipo = new ArrayList<String>();
			List<String> newSelectedVar = new ArrayList<String>();
			if (getSelectedTipos().contains("1")) {
				for (int i = 0; i < getTipos().size(); i++) {
					newSelectedTipo.add(getTipos().get(i).getIdTipo().toString());
				}
			} else {
				newSelectedTipo.addAll(getSelectedTipos());
			}			
			newSelectedVar.addAll(menuPrincipalBo.selectVariables(getSelectedTipos()));
			setSelectedTipos(newSelectedTipo);
			setSelectedVars(newSelectedVar);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", getMessagePage()));
			log.error("Error al seleccionar todas las opciones.", e);
		}
	}
	
	/**
	 * Metodo que se ejecuta cuando se eligen todas las opciones
	 * de los tipos.
	 * 
	 */
	public void eligeVar() {
		try {
			boolean todasFlag = false;
			List<String> newSelectedVar = new ArrayList<String>();
			for (int i = 0; i < getSelectedVars().size(); i++) {
				if (getSelectedVars().get(i).equals("1")) {
					todasFlag = true;
					break;
				}
			}
			if (todasFlag) {
				for (int i = 0; i < getVariables().size(); i++) {
					newSelectedVar.add(getVariables().get(i).getIdVariable().toString());
				}
			} else {
				newSelectedVar.addAll(getSelectedVars());
			}
			setSelectedVars(newSelectedVar);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", getMessagePage()));
			log.error("Error al seleccionar todas las opciones de variables.", e);
		}
	}
	
	/**
	 * Metodo que limpia la pantalla.
	 */
	public void limpiar() {
		try {
			setSelectedTipos(null);
			setSelectedVars(null);
			setListOfRecords(null);
			setFechaInicio(null);
			setFechaFin(null);
			setRenderedRecords(false);
			setMessagePage(FAILURE);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", getMessagePage()));
			log.error("Error al limpiar la pantalla.", e);
		}
	}
	
    /**
     * Metodo para terminar la sesion de modo seguro.
     * 
     * @return Devuelve un String de tipo <from-outcome> para re-direccionar
     * al usuario a la pagina de inicio de sesion.
     * 
     */
    public String logout() {
    	log.info("Cerrando sesion...");
    	try {
    		HttpSession session = Util.getSession();
        	log.info("¡Adios " + session.getAttribute("username") + "!");
            session.invalidate();
    	} catch (HTTPException e) {
    		FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", getMessagePage()));
    		log.error("Error al cerrar la sesion.", e);
    	}
    	
        return "login";
     }
    
    public void setRendered() {
    	setCarga(true);
    	setcTUNGg(true);
    	setcTOVg(true);
    	setTatm(true);
    	setHatm(true);
    	setPatm(true);
    	setcDDreal(true);
    	setPotenciaV(true);
    	setPotencia(true);
    	setEnTotal(true);
    	setEnCinco(true);
    	setEnLin1PrinEnt(true);
    	setEnLin1PrinSal(true);
    	setEnLin1RespEnt(true);
    	setEnLin1RespSal(true);
    	setEnLin2PrinEnt(true);
    	setEnLin2PrinSal(true);
    	setEnLin2RespEnt(true);
    	setEnLin2RespSal(true);
    	setpCIgas(true);
    	setpCSgas(true);
    	setDensGas(true);
    	setFactPot(true);
    	setPresCond(true);
    	setEnLin1PrinReacQ1(true);
    	setEnLin1PrinReacQ4(true);
    	setEnLin1RespReacQ1(true);
    	setEnLin1RespReacQ4(true);
    	setEnLin2PrinReacQ1(true);
    	setEnLin2PrinReacQ4(true);
    	setEnLin2RespReacQ1(true);
    	setEnLin2RespReacQ4(true);
    	setpCIgasM3(true);
    	setpCSgasM3(true);
    }

	public boolean isRenderedOriginales() {
		return renderedOriginales;
	}

	public void setRenderedOriginales(boolean renderedOriginales) {
		this.renderedOriginales = renderedOriginales;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<String> getSelectedTipos() {
		return selectedTipos;
	}

	public void setSelectedTipos(List<String> selectedTipos) {
		this.selectedTipos = selectedTipos;
	}

	public List<String> getSelectedVars() {
		return selectedVars;
	}

	public void setSelectedVars(List<String> selectedVars) {
		this.selectedVars = selectedVars;
	}

	public List<CtTipos> getTipos() {
		return tipos;
	}

	public void setTipos(List<CtTipos> tipos) {
		this.tipos = tipos;
	}

	public List<CtVariable> getVariables() {
		return variables;
	}

	public void setVariables(List<CtVariable> variables) {
		this.variables = variables;
	}

	public MenuPrincipalBo getMenuPrincipalBo() {
		return menuPrincipalBo;
	}

	public void setMenuPrincipalBo(MenuPrincipalBo menuPrincipalBo) {
		this.menuPrincipalBo = menuPrincipalBo;
	}

	public String getMessagePage() {
		return messagePage;
	}

	public void setMessagePage(String messagePage) {
		this.messagePage = messagePage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isRenderedRecords() {
		return renderedRecords;
	}

	public void setRenderedRecords(boolean renderedRecords) {
		this.renderedRecords = renderedRecords;
	}

	public List<DatosBasicos> getListOfRecords() {
		return listOfRecords;
	}

	public void setListOfRecords(List<DatosBasicos> listOfRecords) {
		this.listOfRecords = listOfRecords;
	}

	public String getHeaderDialogString() {
		return headerDialogString;
	}

	public void setHeaderDialogString(String headerDialogString) {
		this.headerDialogString = headerDialogString;
	}
	
		public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public boolean isCarga() {
		return carga;
	}

	public void setCarga(boolean carga) {
		this.carga = carga;
	}

	public boolean iscTUNGg() {
		return cTUNGg;
	}

	public void setcTUNGg(boolean cTUNGg) {
		this.cTUNGg = cTUNGg;
	}

	public boolean iscTOVg() {
		return cTOVg;
	}

	public void setcTOVg(boolean cTOVg) {
		this.cTOVg = cTOVg;
	}

	public boolean isTatm() {
		return tatm;
	}

	public void setTatm(boolean tatm) {
		this.tatm = tatm;
	}

	public boolean isHatm() {
		return hatm;
	}

	public void setHatm(boolean hatm) {
		this.hatm = hatm;
	}

	public boolean isPatm() {
		return patm;
	}

	public void setPatm(boolean patm) {
		this.patm = patm;
	}

	public boolean iscDDreal() {
		return cDDreal;
	}

	public void setcDDreal(boolean cDDreal) {
		this.cDDreal = cDDreal;
	}

	public boolean isPotenciaV() {
		return potenciaV;
	}

	public void setPotenciaV(boolean potenciaV) {
		this.potenciaV = potenciaV;
	}

	public boolean isPotencia() {
		return potencia;
	}

	public void setPotencia(boolean potencia) {
		this.potencia = potencia;
	}

	public boolean isEnTotal() {
		return enTotal;
	}

	public void setEnTotal(boolean enTotal) {
		this.enTotal = enTotal;
	}

	public boolean isEnCinco() {
		return enCinco;
	}

	public void setEnCinco(boolean enCinco) {
		this.enCinco = enCinco;
	}

	public boolean isEnLin1PrinEnt() {
		return enLin1PrinEnt;
	}

	public void setEnLin1PrinEnt(boolean enLin1PrinEnt) {
		this.enLin1PrinEnt = enLin1PrinEnt;
	}

	public boolean isEnLin1PrinSal() {
		return enLin1PrinSal;
	}

	public void setEnLin1PrinSal(boolean enLin1PrinSal) {
		this.enLin1PrinSal = enLin1PrinSal;
	}

	public boolean isEnLin1RespEnt() {
		return enLin1RespEnt;
	}

	public void setEnLin1RespEnt(boolean enLin1RespEnt) {
		this.enLin1RespEnt = enLin1RespEnt;
	}

	public boolean isEnLin1RespSal() {
		return enLin1RespSal;
	}

	public void setEnLin1RespSal(boolean enLin1RespSal) {
		this.enLin1RespSal = enLin1RespSal;
	}

	public boolean isEnLin2PrinEnt() {
		return enLin2PrinEnt;
	}

	public void setEnLin2PrinEnt(boolean enLin2PrinEnt) {
		this.enLin2PrinEnt = enLin2PrinEnt;
	}

	public boolean isEnLin2PrinSal() {
		return enLin2PrinSal;
	}

	public void setEnLin2PrinSal(boolean enLin2PrinSal) {
		this.enLin2PrinSal = enLin2PrinSal;
	}

	public boolean isEnLin2RespEnt() {
		return enLin2RespEnt;
	}

	public void setEnLin2RespEnt(boolean enLin2RespEnt) {
		this.enLin2RespEnt = enLin2RespEnt;
	}

	public boolean isEnLin2RespSal() {
		return enLin2RespSal;
	}

	public void setEnLin2RespSal(boolean enLin2RespSal) {
		this.enLin2RespSal = enLin2RespSal;
	}

	public boolean ispCIgas() {
		return pCIgas;
	}

	public void setpCIgas(boolean pCIgas) {
		this.pCIgas = pCIgas;
	}

	public boolean ispCSgas() {
		return pCSgas;
	}

	public void setpCSgas(boolean pCSgas) {
		this.pCSgas = pCSgas;
	}

	public boolean isDensGas() {
		return densGas;
	}

	public void setDensGas(boolean densGas) {
		this.densGas = densGas;
	}

	public boolean isFactPot() {
		return factPot;
	}

	public void setFactPot(boolean factPot) {
		this.factPot = factPot;
	}

	public boolean isPresCond() {
		return presCond;
	}

	public void setPresCond(boolean presCond) {
		this.presCond = presCond;
	}

	public boolean isEnLin1PrinReacQ1() {
		return enLin1PrinReacQ1;
	}

	public void setEnLin1PrinReacQ1(boolean enLin1PrinReacQ1) {
		this.enLin1PrinReacQ1 = enLin1PrinReacQ1;
	}

	public boolean isEnLin1PrinReacQ4() {
		return enLin1PrinReacQ4;
	}

	public void setEnLin1PrinReacQ4(boolean enLin1PrinReacQ4) {
		this.enLin1PrinReacQ4 = enLin1PrinReacQ4;
	}

	public boolean isEnLin1RespReacQ1() {
		return enLin1RespReacQ1;
	}

	public void setEnLin1RespReacQ1(boolean enLin1RespReacQ1) {
		this.enLin1RespReacQ1 = enLin1RespReacQ1;
	}

	public boolean isEnLin1RespReacQ4() {
		return enLin1RespReacQ4;
	}

	public void setEnLin1RespReacQ4(boolean enLin1RespReacQ4) {
		this.enLin1RespReacQ4 = enLin1RespReacQ4;
	}

	public boolean isEnLin2PrinReacQ1() {
		return enLin2PrinReacQ1;
	}

	public void setEnLin2PrinReacQ1(boolean enLin2PrinReacQ1) {
		this.enLin2PrinReacQ1 = enLin2PrinReacQ1;
	}

	public boolean isEnLin2PrinReacQ4() {
		return enLin2PrinReacQ4;
	}

	public void setEnLin2PrinReacQ4(boolean enLin2PrinReacQ4) {
		this.enLin2PrinReacQ4 = enLin2PrinReacQ4;
	}

	public boolean isEnLin2RespReacQ1() {
		return enLin2RespReacQ1;
	}

	public void setEnLin2RespReacQ1(boolean enLin2RespReacQ1) {
		this.enLin2RespReacQ1 = enLin2RespReacQ1;
	}

	public boolean isEnLin2RespReacQ4() {
		return enLin2RespReacQ4;
	}

	public void setEnLin2RespReacQ4(boolean enLin2RespReacQ4) {
		this.enLin2RespReacQ4 = enLin2RespReacQ4;
	}

	public boolean ispCIgasM3() {
		return pCIgasM3;
	}

	public void setpCIgasM3(boolean pCIgasM3) {
		this.pCIgasM3 = pCIgasM3;
	}

	public boolean ispCSgasM3() {
		return pCSgasM3;
	}

	public void setpCSgasM3(boolean pCSgasM3) {
		this.pCSgasM3 = pCSgasM3;
	}

}
