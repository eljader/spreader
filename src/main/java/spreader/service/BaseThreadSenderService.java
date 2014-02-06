package spreader.service;

import java.util.List;

import spreader.service.converter.Converter;
import spreader.service.sender.Sender;
import spreader.thread.ThreadSource;

abstract public class BaseThreadSenderService<T> implements Runnable {

    protected Converter<List<T>, T> converter;
    protected Sender<T> sender;
    protected ThreadSource<T> source;
    
    public BaseThreadSenderService(ThreadSource<T> source, Sender<T> sender,
            Converter<List<T>, T> converter) {
        this.source = source;
        this.sender = sender;
        this.converter = converter;
    }
    
    abstract public void run();
}
