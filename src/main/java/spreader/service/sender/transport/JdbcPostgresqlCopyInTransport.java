package spreader.service.sender.transport;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DelegatingConnection;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

public class JdbcPostgresqlCopyInTransport extends BaseJdbcTransport {

    CopyManager copyManager;
    String expression;
    
    public JdbcPostgresqlCopyInTransport(DataSource dataSource, String expression) {
        super(dataSource);        
        this.expression = expression;
    }

    @Override
    protected void doSend(String sql) throws SQLException {
        try { getCopyManager().copyIn(expression, new StringReader(sql)); } 
        catch (IOException e) { logError(e); }        
    }
        
    private CopyManager getCopyManager() throws SQLException {
        if(copyManager == null) copyManager = new CopyManager(getBaseConnection());         
        return copyManager;
    }
    
    private BaseConnection getBaseConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        Connection dconn = ((DelegatingConnection) conn).getInnermostDelegate();
        return (BaseConnection) dconn;
    }
    
    @Override
    public BaseJdbcTransport clone() {
        return new JdbcPostgresqlCopyInTransport(dataSource, expression);
    }
}
