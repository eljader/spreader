package spreader;

import org.xml.sax.Attributes;

public class SAXElementStructData {

    private String uri; 
    private String name; 
    private String nodeName; 
    private Attributes attrs;
    
    public SAXElementStructData(String uri, String name, String nodeName, Attributes attrs) {
        setUri(uri); 
        setName(name); 
        setNodeName(nodeName); 
        setAttrs(attrs);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Attributes getAttrs() {
        return attrs;
    }

    public void setAttrs(Attributes attrs) {
        this.attrs = attrs;
    }
    
    public Boolean equals(SAXElementStructData that) {
        return 
            this.uri.equals(that.getUri())
            && 
            this.name.equals(that.getName())
            && 
            this.nodeName.equals(that.getNodeName())
            &&
            (this.attrs.equals(that.getAttrs()) || bothAttrsIsEmpty(that.getAttrs()))
        ;
    }
    
    private Boolean bothAttrsIsEmpty(Attributes that) {
        return (this.attrs.getLength() == that.getLength()) && (that.getLength() == 0);
    }
}
