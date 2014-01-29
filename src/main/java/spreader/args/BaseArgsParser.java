package spreader.args;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

abstract class BaseArgsParser implements ArgsParser{
    
    protected Options options = new Options();    
    private CommandLineParser parser = new GnuParser();
    
    public CommandLine parse(String[] args) throws ParseException, ArgsUsageException {        
       setUp();                        
       CommandLine line = parser.parse(options, args);
       if(isNotValid(line)) throw new ArgsUsageException(options);
       return line;
    }
    
    private Boolean isNotValid(CommandLine line) {
        return !isValid(line);
    }
    
    abstract protected void setUp();
    abstract protected Boolean isValid(CommandLine line);
}
