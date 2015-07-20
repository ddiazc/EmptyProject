package com.cgaf.model;

// Generated 12-jul-2015 21:00:36 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * CtUsuarios generated by hbm2java
 */
public class CtUsuarios implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idUsuario;
	private CtRoles ctRoles;
	private String nombreUsuario;
	private String apellidopUsuario;
	private String apellidomUsuario;
	private String emailUsuario;
	private Timestamp fechaAlta;
	private Timestamp fechaUltmon;
	private String ultimoUsuario;
	private boolean activo;
	private Set<DHashes> DHasheses = new HashSet<DHashes>(0);

	public CtUsuarios() {
	}

	public CtUsuarios(Integer idUsuario, CtRoles ctRoles,
			String nombreUsuario, String apellidopUsuario,
			Timestamp fechaAlta, boolean activo) {
		this.idUsuario = idUsuario;
		this.ctRoles = ctRoles;
		this.nombreUsuario = nombreUsuario;
		this.apellidopUsuario = apellidopUsuario;
		this.fechaAlta = fechaAlta;
		this.activo = activo;
	}

	public CtUsuarios(Integer idUsuario, CtRoles ctRoles,
			String nombreUsuario, String apellidopUsuario,
			String apellidomUsuario, String emailUsuario,
			Timestamp fechaAlta, Timestamp fechaUltmon,
			String ultimoUsuario, boolean activo, Set<DHashes> DHasheses) {
		this.idUsuario = idUsuario;
		this.ctRoles = ctRoles;
		this.nombreUsuario = nombreUsuario;
		this.apellidopUsuario = apellidopUsuario;
		this.apellidomUsuario = apellidomUsuario;
		this.emailUsuario = emailUsuario;
		this.fechaAlta = fechaAlta;
		this.fechaUltmon = fechaUltmon;
		this.ultimoUsuario = ultimoUsuario;
		this.activo = activo;
		this.DHasheses = DHasheses;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public CtRoles getCtRoles() {
		return this.ctRoles;
	}

	public void setCtRoles(CtRoles ctRoles) {
		this.ctRoles = ctRoles;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidopUsuario() {
		return this.apellidopUsuario;
	}

	public void setApellidopUsuario(String apellidopUsuario) {
		this.apellidopUsuario = apellidopUsuario;
	}

	public String getApellidomUsuario() {
		return this.apellidomUsuario;
	}

	public void setApellidomUsuario(String apellidomUsuario) {
		this.apellidomUsuario = apellidomUsuario;
	}

	public String getEmailUsuario() {
		return this.emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public Timestamp getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Timestamp getFechaUltmon() {
		return this.fechaUltmon;
	}

	public void setFechaUltmon(Timestamp fechaUltmon) {
		this.fechaUltmon = fechaUltmon;
	}

	public String getUltimoUsuario() {
		return this.ultimoUsuario;
	}

	public void setUltimoUsuario(String ultimoUsuario) {
		this.ultimoUsuario = ultimoUsuario;
	}

	public boolean isActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Set<DHashes> getDHasheses() {
		return this.DHasheses;
	}

	public void setDHasheses(Set<DHashes> DHasheses) {
		this.DHasheses = DHasheses;
	}

}