package spreader.service.sender;

public class StringSender implements CloneableSender<String> {

    private ClonableClient<String> client;
    
    public StringSender(ClonableClient<String> client) {
        this.client = client;
    }
    
    public void send(String payload) {
        client.execute(payload);      
    }
    
    public StringSender clone() {
        return new StringSender(client.clone());
    } 
}
