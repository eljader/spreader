package spreader.thread;

import java.util.List;

import spreader.service.DefaultThreadSenderService;
import spreader.service.converter.Converter;
import spreader.service.sender.CloneableSender;

public class DefaultThreadSenderBuilder<T> implements ThreadBuilder<T> {

    private Converter<List<T>, T> converter;
    private CloneableSender<T> sender;
    private ThreadSource<T> buffer;

        
    public DefaultThreadSenderBuilder(ThreadSource<T> buffer, CloneableSender<T> sender,
            Converter<List<T>, T> converter) {
        this.buffer = buffer;
        this.sender = sender;
        this.converter = converter;
    }
    
    public Thread buildThread() {
        return new Thread(new DefaultThreadSenderService<T>(buffer, sender.clone(), converter));
    }
}
