package com.spring.mvc;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.modelo.Consulta;

@Controller
public class SistemasController {

	@RequestMapping("/muestraSistemas")
	public String muestraSistemas()// controlador que se encarga de mostrar los sistemas
	{
		return "sistemas";
	}

	@RequestMapping("/procesarSistema")
	public String procesarSistema(@RequestParam("sistema") String a, @RequestParam("consulta") String b) throws SQLException,IOException,NullPointerException{

			
	if( a == null) 
	{	
	throw new IOException("exespfsf");
	}
	else if(b !=null) 
	{
		
		throw new SQLException("aaaaaaaaaaa");
	
	}

		return "lista";
	}
	
	 @ExceptionHandler({IOException.class, java.sql.SQLException.class,java.lang.NullPointerException.class})
	    public ModelAndView handleIOException(Exception ex) {
	        ModelAndView model = new ModelAndView("IOError");
	 
	        model.addObject("exception", ex.getMessage());
	         
	        return model;
	    }
}

