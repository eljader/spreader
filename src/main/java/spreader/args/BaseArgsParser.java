package spreader.args;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

abstract class BaseArgsParser implements ArgsParser{
    
    protected Options options = new Options();    
    private CommandLineParser parser = new GnuParser();
    
    public CommandLine parse(String[] args) throws ParseException, ArgsUsageException {        
       setUp();        
       CommandLine line = getCommandLine(args);
       if(isNotValid(line)) throw new ArgsUsageException(options);
       return line;
    }
    
    private CommandLine getCommandLine(String[] args) throws ParseException, ArgsUsageException {
        CommandLine line;        
        try { line = parser.parse(options, args); } 
        catch (UnrecognizedOptionException e) { throw new ArgsUsageException(options); } 
        return line;
    }
    
    private Boolean isNotValid(CommandLine line) {      
        return !isValid(line);
    }
    
    abstract protected void setUp();
    abstract protected Boolean isValid(CommandLine line);
}
