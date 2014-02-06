package spreader.service.sender.transport;

import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcExecuteTransport extends BaseJdbcTransport {

    public JdbcExecuteTransport(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doSend(String sql) throws SQLException{
        jdbcTemplate.execute(sql);        
    }
}
