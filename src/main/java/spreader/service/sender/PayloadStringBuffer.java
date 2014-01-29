package spreader.service.sender;

final public class PayloadStringBuffer implements PayloadBuffer<String> {

    private StringBuffer buffer = new StringBuffer();
    
    public void append(String payload) {
        buffer.append(payload);
    }

    public String retrieveAll() {
        return buffer.toString();
    }
    
    public void clear() {
        buffer.setLength(0);
    }
}
