package com.cgaf.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.cgaf.bo.MenuPrincipalBo;
import com.cgaf.filters.Util;
import com.cgaf.model.CtTipos;
import com.cgaf.model.CtVariables;
import com.cgaf.util.Validators;
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
	private int columnTipos;
	private List<String> selectedTipos;
	private List<String> selectedVars;
	private List<CtTipos> tipos;
	private List<CtVariables> variables;
	private List<DatosBasicos> listOfRecords;
	private static final String FAILURE;
	
	@ManagedProperty(value = "#{menuPrincipalBo}")
	MenuPrincipalBo menuPrincipalBo;
	
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
			setHeaderDialogString("Buscando información...");
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
			if (getTipos().size() > 4) {
				setColumnTipos(getTipos().size()/4);
			} else {
				setColumnTipos(1);
			}
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
				DatosBasicos record = new DatosBasicos();
				record.setId(1);
				record.setCarga(10.1);
				record.setcDDreal(531558.535);
				record.setCtovg(12.1);
				record.setCtungg(13.1);
				record.setDensGas(14.1);
				record.setEnCinco(15.1);
				record.setEnLin1PrinEnt(16.1);
				record.setEnLin1PrinReacQ1(17.1);
				record.setEnLin1PrinReacQ4(18.1);
				record.setEnLin1PrinSal(19.1);
				record.setEnLin1RespEnt(20.1);
				record.setEnLin1RespReacQ1(21.1);
				record.setEnLin1RespReacQ4(22.1);
				record.setEnLin1RespSal(23.1);
				record.setEnLin2PrinEnt(24.1);
				record.setEnLin2PrinReacQ1(25.1);
				record.setEnLin2PrinReacQ4(26.1);
				record.setEnLin2RespSal(27.1);
				record.setEnTotal(28.1);
				record.setFactPot(29.1);
				record.setFechaFin(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				record.setFechaIni(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				record.setHatm(30.1);
				record.setPatm(31.1);
				record.setpCIgas(32.1);
				record.setpCIgasM3(33.1);
				record.setpCSgas(34.1);
				record.setpCSgasM3(35.1);
				record.setPotencia(349332.001);
				record.setPotenciaV(328592.000);
				record.setPresCond(38.1);
				record.setTatm(39.4);
				List<DatosBasicos> listToView = new ArrayList<DatosBasicos>();
				listToView.add(record);
				menuPrincipalBo.saveTabla();
				setListOfRecords(listToView);
				if (getListOfRecords().size() > 0) {
					setRenderedRecords(true);
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
			HSSFWorkbook wb = (HSSFWorkbook) document;
	        HSSFSheet sheet = wb.getSheetAt(0);
	        HSSFCellStyle cellStyle = wb.createCellStyle();
	        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
	        	HSSFRow rows = sheet.getRow(i);
	        	for (int j = 0; j < rows.getPhysicalNumberOfCells(); j++) {
	        		HSSFCell cell = rows.getCell(j);
	        		cell.setCellStyle(cellStyle);
	        	}
	        }
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

	public List<CtVariables> getVariables() {
		return variables;
	}

	public void setVariables(List<CtVariables> variables) {
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

	public int getColumnTipos() {
		return columnTipos;
	}

	public void setColumnTipos(int columnTipos) {
		this.columnTipos = columnTipos;
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

}
