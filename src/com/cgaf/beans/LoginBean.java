
package com.cgaf.beans;

import com.cgaf.bo.LoginBo;
import com.cgaf.filters.Util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 *
 * @author Franck RVX
 */

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
    private String password;
    
    @ManagedProperty(value = "#{loginBo}")
    LoginBo loginBo;
    
    private static final Logger log = Logger.getLogger(LoginBean.class);
    
    /**
     * Metodo encargado de procesar la autenticacion del usuario.
     * 
     * @return Devuelve un String de tipo <from-outcome> para re-direccionar
     * al usuario segun el resultado de la validacion de sus credenciales.
     * 
     */
    public String authenticate() {
        log.info("Validando credenciales.");
        try {
        	if (username.isEmpty() || password.isEmpty()) {
        		FacesContext.getCurrentInstance().addMessage(null,
        				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Login!",
        						"Se requiere usuario y contraseña."));
        		return "login";
        	} else {
        		if (loginBo.authenticate(username, password)) {
                	HttpSession session = Util.getSession();
                	session.setAttribute("username", username);
                	return "menuPrincipal";
                } else {
                	FacesContext.getCurrentInstance().addMessage(null,
                			new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!",
                					"El usuario o la contraseña son incorrectos."));
                	return "login";
                }
        	}
        } catch (Exception e) {
            log.error("Error al autenticar al usuario.", e);
            FacesContext.getCurrentInstance().addMessage(null,
    				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Login!",
    						"¡Ups! Algo salió mal al intentar iniciar sesión."));
    		return "login";
        }
    }

    public LoginBo getLoginBo() {
        return loginBo;
    }

    public void setLoginBo(LoginBo loginBo) {
        this.loginBo = loginBo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
