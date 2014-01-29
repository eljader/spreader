package spreader.args;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

public interface ArgsParser {
    
    public CommandLine parse(String[] args) throws ParseException, ArgsUsageException;
}
