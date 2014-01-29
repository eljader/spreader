package spreader.args;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;

public class DefaultArgsParser extends BaseArgsParser {

    @SuppressWarnings("static-access")
    @Override
    protected void setUp() {        
        options.addOption(
            OptionBuilder.withArgName("file")
                .hasArg()
                .withDescription("source xml file")
                .create("source")
        );
        
        options.addOption(
            OptionBuilder.withArgName("file")
                .hasArg()
                .withDescription("spring configuration xml file")
                .create("configuration")   
        );        
    }

    @Override
    protected Boolean isValid(CommandLine line) {
        return line.hasOption("source") && line.hasOption("configuration");
    }
}
