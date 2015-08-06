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
import com.cgaf.model.CtTipoVariable;
import com.cgaf.model.CtVariable;
import com.cgaf.util.Validators;
import com.cgaf.util.excel.FileManager;
import com.cgaf.vo.DatosBasicosRendered;
import com.cgaf.vo.DatosBasicosValues;

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
	
	
	
	private Date fechaInicio;
	private Date fechaFin;
	private String fileName;
	private String messagePage;	
	private String headerDialogString;
	private boolean renderedRecords;
	private boolean renderedOriginales;
	private DatosBasicosRendered renderedFields;
	private List<String> selectedTipos;
	private List<String> selectedVars;
	private List<CtTipoVariable> tipos;
	private List<CtVariable> variables;
	private List<DatosBasicosValues> listOfRegToView;
	private static final String FAILURE;
	
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
			setHeaderDialogString("Procesando...");
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
					List<DatosBasicosValues> listOfVO = menuPrincipalBo.getDataFromTables(listOfRecords, getFechaInicio(), getFechaFin());
					setListOfRegToView(listOfVO);
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
	 * Metodo que se ejecuta cuando se elige un tipo de variable.
	 * 
	 */
	public void eligeTipo() {
		try {
			List<String> newSelectedTipo = new ArrayList<String>();
			List<String> newSelectedVar = new ArrayList<String>();
			if (getSelectedTipos().contains("0")) {
				for (int i = 0; i < getTipos().size(); i++) {
					newSelectedTipo.add(getTipos().get(i).getIdTipoVariable().toString());
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
	 * Metodo que se ejecuta cuando se elige una variable.
	 */
	public void eligeVar() {
		try {
			List<String> newSelectedVar = new ArrayList<String>();
			if (getSelectedVars().contains("0")) {
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
			setListOfRegToView(null);
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

	public List<CtTipoVariable> getTipos() {
		return tipos;
	}

	public void setTipos(List<CtTipoVariable> tipos) {
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

	public List<DatosBasicosValues> getListOfRegToView() {
		return listOfRegToView;
	}

	public void setListOfRegToView(List<DatosBasicosValues> listOfRegToView) {
		this.listOfRegToView = listOfRegToView;
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

	public DatosBasicosRendered getRenderedFields() {
		return renderedFields;
	}

	public void setRenderedFields(DatosBasicosRendered renderedFields) {
		this.renderedFields = renderedFields;
	}

}
