package com.cgaf.servlets;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class LoadLog4j extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String path;
	
	/**
	 * Servlet encargado de cargar la configuracion de Log4j.
	 */
    @Override
    public void init(ServletConfig config) throws ServletException {
        String log4jFile = config.getInitParameter("log4jPropertiesFile");
        ServletContext context = config.getServletContext();
        log4jFile = context.getRealPath(log4jFile);	
        path = log4jFile;
        if (new File(log4jFile).isFile()) {
            PropertyConfigurator.configure(log4jFile);
        } else {
            BasicConfigurator.configure();
        }
        super.init(config);
    }

}
