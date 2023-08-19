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
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends GenericServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public AdminServlet() {
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
			String s1=request.getParameter("auname");
			String s2=request.getParameter("apword");
			PreparedStatement psmt=con.prepareStatement("select * from admin where uname=? and pword=?");
			psmt.setString(1, s1);
			psmt.setString(2, s2);
			ResultSet rs=psmt.executeQuery();
			PrintWriter pw=response.getWriter();
			System.out.println("Login Successfully...");
			if(rs.next()) {
				pw.println("<html><body><center>");
				pw.println("<h1>Login Successfully...</h1>");
				pw.println("<a href=count>Show Result</a>");
				pw.println("</center></body></html>");
			}else {
				System.out.println("Invalid Username/Password");
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
