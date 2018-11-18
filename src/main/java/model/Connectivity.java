package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectivity
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/demo";
    private static final String USER = "root" ;
    private static final String PASS = "root";
    private Connection conn;
    public Connectivity() {
        conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
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
