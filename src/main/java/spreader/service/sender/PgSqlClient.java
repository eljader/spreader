package spreader.service.sender;

import java.sql.*;

import org.apache.log4j.Logger;

public class PgSqlClient implements ClonableClient<String> {
    
    static Logger logger = Logger.getLogger(PgSqlClient.class);
    
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
            logger.error(e);            
        } catch (SQLException e) {
            logger.error(e);         
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
            logger.error(e);         
        }
    }
    
    public void execute(String sql) {
        try {
            connection.prepareStatement(sql).execute();
            logger.debug(sql);
        } catch (SQLException e) {
            logger.error(e);         
        }       
    }
    
    public PgSqlClient clone() {
        return new PgSqlClient(host, port, dbname, user, password);     
    }
}
