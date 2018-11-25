package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectivity
{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/multiagencja";
    private static final String USER = "test" ;
    private static final String PASS = "test";
    private Connection conn;
    public Connectivity() {
        conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    public boolean login()
    {
        return false;
    }

    public Connection getConn() {
        return conn;
    }

    public boolean close()
    {
        try
        {
            if(conn!=null) {
                conn.close();
                return true;
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }

        return false;
    }


}
