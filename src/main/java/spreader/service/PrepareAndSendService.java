package spreader.service;

import java.util.Arrays;
import java.util.List;

import org.xml.sax.Attributes;

import spreader.SAXElementStructData;
import spreader.service.converter.Converter;
import spreader.service.sender.Sender;

public class PrepareAndSendService <T> implements SAXService {
    
    private Converter<Attributes, T> converter;
    private Sender<T> sender;
    private List<String> targetNodes;
    
    public PrepareAndSendService(Converter<Attributes, T> converter, Sender<T> sender, String[] targetNodes) {
        this.converter = converter;
        this.sender = sender;
        this.targetNodes = Arrays.asList(targetNodes);
    }
    
    public SAXService setData(SAXElementStructData data) {
        if (isNotTargetNode(data.getNodeName())) return new DummySAXService();
        converter.setInput(data.getAttrs());
        return this;        
    }
    
    private Boolean isNotTargetNode(String name) {
        return !targetNodes.contains(name); 
    }

    public void run() {
        converter.convert();        
        sender.send(converter.getResult());  
    }
}
