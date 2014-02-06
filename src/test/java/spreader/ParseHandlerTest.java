package spreader;

import org.xml.sax.SAXException;

import spreader.NullAttributes;
import spreader.ParseHandler;
import spreader.SAXElementStructData;
import spreader.service.SAXService;
import static org.easymock.EasyMock.*;
import junit.framework.TestCase;

public class ParseHandlerTest extends TestCase {
    
    private ParseHandler handler;
    private SAXService startMock;
    private SAXService endMock;
    
    protected void setUp() {
        startMock = createMock(SAXService.class);
        endMock = createMock(SAXService.class); 
        handler = new ParseHandler(startMock, endMock);
    }
    
    public void testStartElement() throws SAXException {         
        expect(startMock.setData(isA(SAXElementStructData.class)))
            .andReturn(startMock);
        
        startMock.run();
        
        replay(startMock);
        handler.startElement("", "", "", new NullAttributes()); 
        verify(startMock);
    }
        
    public void testEndElement() throws SAXException {              
        expect(endMock.setData(isA(SAXElementStructData.class)))
            .andReturn(endMock);
        
        endMock.run();
        
        replay(endMock);
        handler.endElement("", "", "");
        verify(endMock);
    }
}
