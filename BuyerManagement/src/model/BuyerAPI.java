package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BuyerAPI")
public class BuyerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BuyerServlet buyerObj = new BuyerServlet();
	
	public BuyerAPI() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String outputString = buyerObj.insertBuyer(request.getParameter("buyerName"), 
				request.getParameter("projectName"),
				request.getParameter("email"), 
				request.getParameter("contactNo"), 
				request.getParameter("researcherName"));
				

		response.getWriter().write(outputString);
	}	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map paras = getParasMap(request);

		String outputString = buyerObj.updateBuyer(
				paras.get("buyerID").toString(),
				paras.get("buyerName").toString(),
				paras.get("projectName").toString(), 
				paras.get("email").toString(),
				paras.get("contactNo").toString(),
				paras.get("researcherName").toString());

		response.getWriter().write(outputString);
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = buyerObj.deleteBuyer(paras.get("buyerID").toString());
		response.getWriter().write(output); 		
	}
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request) {
			
			Map<String, String> map = new HashMap<String, String>();
			
			try {			
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
				scanner.close();
				
				String[] params = queryString.split("&");
				for (String param : params) {
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
				
			} catch (Exception e) {
			  }
			
			return map;
		}
	}