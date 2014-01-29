package spreader;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import spreader.service.SAXService;

public class ParseHandler extends DefaultHandler {
    
    private SAXService startService;
    private SAXService endService;
    
    public ParseHandler(SAXService start, SAXService end) {
        startService = start; 
        endService = end;       
    }
            
    public void startElement(String uri, String name, String nodeName, Attributes attrs) throws SAXException {            
        SAXElementStructData data = 
                new SAXElementStructData(uri, name, nodeName, attrs);
        run(startService.setData(data));
    }  
 
    public void endElement(String uri, String name, String nodeName) throws SAXException { 
        SAXElementStructData data = 
                new SAXElementStructData(uri, name, nodeName, new NullAttributes());
        run(endService.setData(data));
    }
        
    private void run(SAXService service) {
        service.run();
    }
    
}
