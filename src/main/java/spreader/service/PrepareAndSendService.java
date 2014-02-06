package spreader.service;

import java.util.HashMap;

import spreader.SAXElementStructData;
import spreader.service.converter.Converter;
import spreader.service.sender.Sender;

public class PrepareAndSendService <T> extends BaseSAXService {
    
    private Converter<HashMap<String, String>, T> converter;
    private Sender<T> sender;
    
    public PrepareAndSendService(Converter<HashMap<String, String>, T> converter, Sender<T> sender, String[] targetNodes) {
        super(targetNodes);
        this.converter = converter;
        this.sender = sender;
    }
    
    @Override
    protected void handleData(SAXElementStructData data) {
        converter.setInput(assembleAttributes(data.getAttrs()));        
    }

    @Override
    protected SAXService getDummy() {
        return new DummySAXService();
    }
    
    public void run() {
        converter.convert();
        sender.send(converter.getResult());  
    }
}
