package com.spring.mvc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.swing.JOptionPane;

import org.apache.catalina.connector.Request;

import com.spring.mvc.modelo.*;

public class Conector {
	private LSistemas SysList;
	
	public Conector() {
		SysList = new LSistemas();
	}
	

	public void Consultar(Consulta C) {

		int SysIndex = this.identificarSistema(C.getSistema());

		String url = SysList.getListaSistemas().get(SysIndex).getURL();
		String SisID = SysList.getListaSistemas().get(SysIndex).getSystemId();
		String user = SysList.getListaSistemas().get(SysIndex).getUser();
		String pass = SysList.getListaSistemas().get(SysIndex).getPass();
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		     Class.forName("com.mysql.jdbc.Driver");
			// Connection con =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo", "root",
			// "root");
			System.out.println(url);
		     url = "jdbc:mysql://" + url + "/" + SisID;
			//url = "jdbc:oracle:thin:@" + url +"/"+SisID;
			Connection con = DriverManager.getConnection(url, user, pass);
			// here sonoo is database name, root is username and password
			System.out.println("la ruta de conexion es: "+url+"; "+user+"; "+pass);
			System.out.println("la consulta es: "+ C.getSqlQuerry()+" con parametros: "+C.getParameters().toString());
			PreparedStatement pStmt;
			//pStmt = con.prepareStatement(C.getSqlQuerry());
			pStmt = con.prepareStatement(C.getSqlQuerry());
			//System.out.println(pStmt);
			/*
			 * 
			 * Aqui falta la configuracion del PreparedStatement....
			 * 
			 */

			ResultSet rs = pStmt.executeQuery();
			while (rs.next())
				
				System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			con.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion");
			System.out.println(e);
		}
	}
	
	public ArrayList ConsultarResulset(Consulta C) {

		int SysIndex = this.identificarSistema(C.getSistema());

		String url = SysList.getListaSistemas().get(SysIndex).getURL();
		String SisID = SysList.getListaSistemas().get(SysIndex).getSystemId();
		String user = SysList.getListaSistemas().get(SysIndex).getUser();
		String pass = SysList.getListaSistemas().get(SysIndex).getPass();
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Class.forName("com.mysql.jdbc.Driver");
			// Connection con =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo", "root",
			// "root");
			System.out.println(url);
			// url = "jdbc:mysql://" + url + "/" + SisID;
			url = "jdbc:oracle:thin:@" + url + "/" + SisID;
			Connection con = DriverManager.getConnection(url, user, pass);
			// here sonoo is database name, root is username and password
			System.out.println("la ruta de conexion es: " + url + "; " + user + "; " + pass);
			System.out.println(
					"la consulta es: " + C.getSqlQuerry() + " con parametros: " + C.getParameters().toString());
			PreparedStatement pStmt;
			// pStmt = con.prepareStatement(C.getSqlQuerry());
			pStmt = con.prepareStatement(C.getSqlQuerry());
			// System.out.println(pStmt);
			/*
			 * 
			 * Aqui falta la configuracion del PreparedStatement....
			 * 
			 */
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {

				/*
				 * String ores = rs.getString(1); String ores2 = rs.getString(2); String ores3 =
				 * rs.getString(3); String [] tokens = ores.split("</n"); String [] tokens2 =
				 * ores2.split("</n>"); String [] tokens3 = ores3.split("</n>");
				 * 
				 * for (int i = 0; i < tokens.length; ++i) { data.add((tokens[i])); //
				 * data.add((tokens2[i])); // data.add((tokens3[i]));
				 * 
				 * }
				 * 
				 * System.out.println(data);
				 */
				ArrayList<String> tokens = new ArrayList<String>();

				int cuantos = ((java.sql.ResultSetMetaData) rs.getMetaData()).getColumnCount();

				for (int j = 1; j <= cuantos; j++) {
					tokens.add(rs.getString(j));
				}
				data.add(tokens);

			}
			
			for(int i =0; i<data.size(); i++) {
				String aux ="";
				for(int j =0; j<data.get(i).size(); j++) {
					aux += data.get(i).get(j)+ ": _ :";					
				}
				System.out.println(aux);
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error en la conexion");
	
			System.out.println(e);
		}

		return (ArrayList) data;
	}


	protected int identificarSistema(String sistema) {
		// devuelve la posicion del sistema en la lista de sistemas
		int sysPos = 0;
		/*for (int i = 0; i < SysList.getListaSistemas().size(); i++) {
			sysPos = i;
		}*/
		return sysPos;
	}
	public LSistemas getLSistemas() {
		return SysList;
	}
}
