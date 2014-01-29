package spreader.service.sender;

public class ThreadSenderBuilder <T> {

    private CloneableSender<T> sender;
    
    public ThreadSenderBuilder(CloneableSender<T> sender) {
        this.sender = sender;
    }
    
    public Thread buildThread(Extractable<T> scope) {
        return new Thread(new ThreadSender<T>(scope, sender.clone(), true));
    }
}
