package spreader.service.sender;

public interface CloneableSender<T> extends Sender<T>{
    public CloneableSender<T> clone();
}
