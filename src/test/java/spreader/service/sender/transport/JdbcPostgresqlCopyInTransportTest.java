package spreader.service.sender.transport;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.*;
import org.postgresql.copy.CopyManager;

import static org.easymock.EasyMock.*;
import junit.framework.Assert;

public class JdbcPostgresqlCopyInTransportTest extends Assert {

    private DataSource dataSource;
    private JdbcPostgresqlCopyInTransport transport;
    private CopyManager copyManager;
    private String expression = "test expression";
    
    @Before
    public void setUp() {
        copyManager = createMock(CopyManager.class);
        dataSource = createMock(DataSource.class);
        transport = new JdbcPostgresqlCopyInTransport(dataSource, expression);
    }
        
    @Test 
    public void sendPayload() throws SQLException, IOException {
        injectCopyManager();
        
        expect(copyManager.copyIn(eq(expression), isA(StringReader.class))).andReturn((long) 0);
        
        replay(copyManager);
        transport.sendPayload("test data");
        verify(copyManager);
    }
    
    @Test 
    public void testClone() {
        JdbcPostgresqlCopyInTransport clone = (JdbcPostgresqlCopyInTransport) transport.clone();
        assertNotSame(transport, clone);
    }
    
    private void injectCopyManager() {
        try { 
            Field field = transport.getClass().getDeclaredField("copyManager");            
            field.setAccessible(true);
            field.set(transport, copyManager);
        } 
        catch (Exception e) { e.printStackTrace(); } 
    }
}
