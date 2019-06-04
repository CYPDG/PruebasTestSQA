package com.spring.mvc;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@SuppressWarnings("null")
	@RequestMapping("/procesarSistema")
	public String procesarSistema(@RequestParam("sistema") String a, @ModelAttribute("consulta") Controlador con ,ModelMap model) throws SQLException,IOException,NullPointerException{
		
		Controlador cont = new Controlador();
		Conector conec;
		
		ArrayList<Consulta> consultas = con.getConsultas();
		
		conec = con.getConector();
		
//		conec = new Conector();
		
		//conec.Consultar(consultas.get(0));
		conec.ConsultarResulset(consultas.get(0));
	
		ResultSet rs = null; 	//cont.getConector().ConsultarResulset(con.getConsultas().get(i));
		String[] param1 = null;
		
		rs = conec.ConsultarResulset(consultas.get(0));

		
		while (rs.next()) {
			
		System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
		
		param1[0] = rs.getString(1);
		param1[1] = rs.getString(2);
		param1[2] = rs.getString(3);
		param1[3] = rs.getString(4);
		
		
		}
				
		ArrayList<String> lista = new ArrayList<>();
		
		lista.add(a);
		
		
		model.addAttribute("listado",lista);
		

		
		return "lista";
	}
	
	 @ExceptionHandler({IOException.class, java.sql.SQLException.class,java.lang.NullPointerException.class})
	    public ModelAndView handleIOException(Exception ex) {
	        ModelAndView model = new ModelAndView("IOError");
	 
	        model.addObject("exception", ex.getMessage());
	         
	        return model;
	    }
}

