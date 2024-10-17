import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class THE_Connection {

    private static String servername = "localhost";
    private static String dbname = "java_rst_db";
    private static String username = "root";
    private static Integer portnumber = 3307;
    private static String password = "";

    public static Connection getTheConnection() throws SQLException {
        Connection connection = null;

        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setServerName(servername);
        datasource.setUser(username);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);
        datasource.setPassword(password);

        connection = datasource.getConnection();

        return connection;
    }
}
