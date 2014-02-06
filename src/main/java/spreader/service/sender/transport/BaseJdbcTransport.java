package spreader.service.sender.transport;

import java.sql.*;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

abstract public class BaseJdbcTransport implements ClonableTransport<String> {
    
    protected Logger logger = Logger.getLogger(this.getClass().getName());
    protected JdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    public BaseJdbcTransport(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
         
    public void sendPayload(String sql) {
        try { 
            doSend(sql); 
            logger.debug(sql);
        }             
        catch (SQLException e) { logError(e); }       
    }
    
    public BaseJdbcTransport clone() {
        try { return this.getClass().getConstructor(getClass()).newInstance(dataSource); } 
        catch (Exception e) { logError(e); }
        return null;
    }
    
    protected void logError(Exception e) {
        logger.error("is: ", e);
    }
    
    abstract protected void doSend(String sql) throws SQLException; 
}
