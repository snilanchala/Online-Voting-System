package voting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends GenericServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public LoginServlet() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		try {
			String s1=request.getParameter("uname");
			String s2=request.getParameter("unid");
			String s3=request.getParameter("pword");
			PreparedStatement psmt=con.prepareStatement("select * from vinfo where uname=? and unid=? and pword=?");
			psmt.setString(1, s1);
			psmt.setString(2, s2);
			psmt.setString(3, s3);
			ResultSet rs=psmt.executeQuery();
			PrintWriter pw=response.getWriter();
			if(rs.next()) {
				pw.println("<html><body><center>");
				pw.println("<h1>Login Successfully...</h1>");
				pw.println("<a href=vote.html>vote</a>");
				pw.println("</center></body></html>");
			}else {
				System.out.println("Invalid Username/Password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
