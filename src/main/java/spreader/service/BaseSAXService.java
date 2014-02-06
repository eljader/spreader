package spreader.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;

import spreader.SAXElementStructData;

public abstract class BaseSAXService implements SAXService {

    private List<String> targetNodes;
    
    public BaseSAXService(String[] targetNodes) {
        this.targetNodes = Arrays.asList(targetNodes);
    }
    
    public SAXService setData(SAXElementStructData data) {
        if (isNotTargetNode(data.getNodeName())) return getDummy();
        handleData(data);
        return this; 
    }
    
    private Boolean isNotTargetNode(String name) {
        return !targetNodes.contains(name); 
    }
    
    protected HashMap<String,String> assembleAttributes(Attributes attributes) {
        HashMap<String, String> map = new HashMap<String,String>();
        for(int i = 0; i < attributes.getLength(); i++ ) {
            map.put(attributes.getQName(i), attributes.getValue(i));
        }
        return map;
    }
    
    abstract public void run();
    abstract protected void handleData(SAXElementStructData data);
    abstract protected SAXService getDummy();
}
