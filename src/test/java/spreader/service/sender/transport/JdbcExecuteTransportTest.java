package spreader.service.sender.transport;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.easymock.EasyMock.*;
import junit.framework.Assert;

public class JdbcExecuteTransportTest extends Assert {
    
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private JdbcExecuteTransport transport;
    
    @Before
    public void setUp() {
        jdbcTemplate = createMock(JdbcTemplate.class);
        dataSource = createMock(DataSource.class);
        transport = new JdbcExecuteTransport(dataSource);
    }
    
    @Test 
    public void testSendPayload() {
        injectJdbcTemplate();
        jdbcTemplate.execute("test data");
        replay(jdbcTemplate);
        transport.sendPayload("test data");
        verify(jdbcTemplate);      
    }
    
    @Test 
    public void testClone() {
        JdbcExecuteTransport clone = (JdbcExecuteTransport) transport.clone();
        assertNotSame(transport, clone);
    }
    
    private void injectJdbcTemplate() {
        try { 
            Field field = transport.getClass().getSuperclass().getDeclaredField("jdbcTemplate");            
            field.setAccessible(true);
            field.set(transport, jdbcTemplate);
        } 
        catch (Exception e) { e.printStackTrace(); } 
    }
}
