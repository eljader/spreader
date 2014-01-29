package spreader.service.sender;

public class ThreadSender <T> implements Runnable {

    private Sender<T> sender;
    private Extractable<T> scope;
    private Boolean runing;
    
    public ThreadSender(Extractable<T> scope, Sender<T> sender, Boolean runing) {
        this.scope = scope;
        this.sender = sender;
        this.runing = runing;
    }
    
    public void run() {        
        do { sender.send(scope.extract()); } while(runing);                            
    }
}
