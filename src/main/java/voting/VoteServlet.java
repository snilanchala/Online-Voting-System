package voting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet implementation class VoteServlet
 */
public class VoteServlet extends GenericServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public VoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		try {
			String s1=request.getParameter("president");
			String s2=request.getParameter("vpresident");
			String s3=request.getParameter("gsecretary");
			String s4=request.getParameter("agsecretary");
			//String s5=request.getParameter("unid");
			PreparedStatement psmt=con.prepareStatement("insert into data values(?,?,?,?)");
			//psmt.setString(1, s5);
			psmt.setString(1, s1);
			psmt.setString(2, s2);
			psmt.setString(3, s3);
			psmt.setString(4, s4);
			psmt.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("<html><body><center>");
			pw.println("<h1>THANK YOU</h1>");
			pw.println("<a href=homepage.html>HomePage</a>");
			pw.println("</center></body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
