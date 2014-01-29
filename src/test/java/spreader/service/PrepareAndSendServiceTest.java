package spreader.service;

import static org.easymock.EasyMock.*;

import org.xml.sax.Attributes;

import spreader.NullAttributes;
import spreader.SAXElementStructData;
import spreader.service.DummySAXService;
import spreader.service.PrepareAndSendService;
import spreader.service.SAXService;
import spreader.service.converter.Converter;
import spreader.service.sender.Sender;
import junit.framework.TestCase;

public class PrepareAndSendServiceTest extends TestCase {

    protected Converter<Attributes, String> converter;
    protected Sender<String> sender;
    protected SAXService service;
    protected String converterResultString = "string from ConverterStub";
    
    @SuppressWarnings("unchecked")
    protected void setUp() {
        converter = createMock(Converter.class);
        sender = createMock(Sender.class);
        service = new PrepareAndSendService<String>(converter, sender, new String[]{"testNodeName"});    
    }
        
    public void testSetData() {        
        converter.setInput(isA(Attributes.class));
        replay(converter); 
        SAXService service1 = 
                service.setData(new SAXElementStructData("", "", "", new NullAttributes()));  
        SAXService service2 = 
                service.setData(new SAXElementStructData("", "", "testNodeName", new NullAttributes()));         
        verify(converter);
        
        assertTrue(service1 instanceof DummySAXService);
        assertEquals(service, service2);
    }
    
    public void testRun() {
        converter.convert();
        expect(converter.getResult()).andReturn("Test Data").once();
        sender.send("Test Data");
        replay(converter, sender);   
        service.run();
        verify(converter, sender);
    }
}
