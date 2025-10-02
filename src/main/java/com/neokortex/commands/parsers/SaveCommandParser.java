package com.neokortex.commands.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.neokortex.DialoguePath;
import com.neokortex.commands.CommandType;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.CommandRequest;

/**
 * Parses an {@code SaveCommand}.
 *
 * <p>
 * A save Command has the following format:
 * </p>
 *
 * <ul>
 *     <li>save {String path}</li>
 * </ul>
 *
 * @see com.neokortex.commands.impl.SaveCommand
 */
public class SaveCommandParser extends CommandParser {
    @Override
    public ParserResponse parse(CommandType commandType, String input) {
        CommandBody body = CommandParser.extractCommandBody(input);
        ParserResponse response;
        response = new ParserResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);

        String path = body.getBody().trim();

        if (!path.isEmpty()) {
            Pattern quoted = Pattern.compile("^\"([^\"]*)\"$");
            Matcher matcher = quoted.matcher(path);
            if (matcher.find()) {
                path = matcher.group(1);
                System.out.println(path);
            }
        }



        response.setCommandRequest(new CommandRequest(body.getCommandType(), input, new String[] {path}));
        return response;
    }
}
