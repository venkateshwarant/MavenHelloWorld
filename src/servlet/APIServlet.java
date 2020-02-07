package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.annotation.WebInitParam;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "My First Servlet", urlPatterns = { "/insert" , "/insert.do"}, initParams = {@WebInitParam(name="id",value="1"),@WebInitParam(name="name",value="venkat")})
public class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APIServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Date date = new Date();
		out.println(HTML_START + "<h2 id='hi' >Hi ljkhk!</h2><br/><h3 id='date' >Date="+date +"</h3>"+HTML_END);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Connection con = DatabaseConnection.initializeDatabase(); 
	        PreparedStatement st = con 
	               .prepareStatement("insert into TEST (NAME,VAL) values(?, ?)"); 
	        st.setString(1, request.getParameter("name")); 
	        st.setString(2, request.getParameter("value")); 
	        st.executeUpdate(); 
	        st.close(); 
	        con.close(); 
	        PrintWriter out = response.getWriter(); 
	        out.println("<html><body><b>Successfully Inserted"
	                    + "</b></body></html>"); 
		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
	}

}