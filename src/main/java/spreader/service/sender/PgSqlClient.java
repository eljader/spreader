package spreader.service.sender;

import java.sql.*;

public class PgSqlClient implements ClonableClient<String> {
    
    private Connection connection;
    private String host; 
    private int port; 
    private String dbname; 
    private String user; 
    private String password;
    
    public PgSqlClient(String host, int port, String dbname, String user, String password) {        
        this.host = host; 
        this.port = port; 
        this.dbname = dbname; 
        this.user = user; 
        this.password = password;
        try {
            connection = getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }               
    }
    
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        
        return DriverManager.getConnection(
            String.format(
                "jdbc:postgresql://%s:%d/%s",
                host,
                port,
                dbname       
            )
            ,user, password
        ); 
    }
    
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void execute(String sql) {
        try {
            connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }       
    }
    
    public PgSqlClient clone() {
        return new PgSqlClient(host, port, dbname, user, password);     
    }
}
