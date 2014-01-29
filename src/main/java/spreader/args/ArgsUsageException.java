package spreader.args;

import org.apache.commons.cli.Options;

public class ArgsUsageException extends Exception {
    
    protected Options options;
    private static final long serialVersionUID = 1L;
    
    public ArgsUsageException(Options options) {
        this.options = options;
    }
    
    public Options getOptions() {
        return options;
    }
}
