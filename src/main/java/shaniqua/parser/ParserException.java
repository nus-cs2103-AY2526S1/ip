package shaniqua.parser;

import shaniqua.ShaniquaException;

public class ParserException extends ShaniquaException {
    ParserException() {
        super("Error parsing command");
    }
}
