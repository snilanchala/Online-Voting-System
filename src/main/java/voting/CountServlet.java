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


public class CountServlet extends GenericServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
    
    public CountServlet() {
        super();
        }

	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
	}
	public int getResult(String type,String name) {
		String qry1="select PRESIDENT, count(PRESIDENT) from data group by PRESIDENT having count(PRESIDENT) > 1";
		String qry2="select VICE_PRESIDENT, count(VICE_PRESIDENT) from data group by VICE_PRESIDENT having count(VICE_PRESIDENT) > 1";
		String qry3="select GENERAL_SECRETARY, count(GENERAL_SECRETARY) from data group by GENERAL_SECRETARY having count(GENERAL_SECRETARY) > 1";
		String qry4="select ASST_GENERAL_SECRETARY, count(ASST_GENERAL_SECRETARY) from data group by ASST_GENERAL_SECRETARY having count(ASST_GENERAL_SECRETARY) > 1";
		
		
		try {
			PreparedStatement psmt=con.prepareStatement(qry1);
			ResultSet r=psmt.executeQuery();
			int result=0;
			if(r.next());
		    return r.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;	
		
	}

}
