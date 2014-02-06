package spreader.args;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.junit.*;

import spreader.args.ArgsUsageException;
import spreader.args.DefaultArgsParser;

public class DefaultArgsParserTest extends Assert {
    
    private DefaultArgsParser parser = new DefaultArgsParser();
    
    @Test
    public void testParseSuccess() throws ParseException, ArgsUsageException {
        CommandLine line = parser.parse(
            new String[]{"-source", "file1", "-configuration", "file2"}
        );
        
       assertEquals("file1", line.getOptionValue("source"));
       assertEquals("file2", line.getOptionValue("configuration"));
    }
    
    @Test(expected = ArgsUsageException.class) 
    public void testParseUsageException() throws ParseException, ArgsUsageException {                
        parser.parse(new String[]{"source", "file1", "-", "file2"});
    }
    
    @Test(expected = ArgsUsageException.class) 
    public void testParseUnrecognizedOptionExceptionToUsageException() throws ParseException, ArgsUsageException {                
        parser.parse(new String[]{"-source", "file1", "-configuration1", "file2"});
    }
    
   
}
