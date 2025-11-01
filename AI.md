# Record of use of AI tools
## Code Quality 
### src/main/java/nina/Parser.java  
>ChatGPT assisted in shortening the long method.  

original method:  
```Java
public static Command parse(String str) throws CommandException, InvalidInputException {
    if (str.isEmpty()) {
        throw new CommandException("The command is empty");
    }

    if (str.equals("list")) {
        return new ListCommand();
    }

    if (str.startsWith("mark ")) {
        String taskNo = str.substring(5).trim();
        try {
            int number = Integer.parseInt(taskNo);
            return new MarkCommand(number);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Only number can come after mark!");
        }
    }

    if (str.startsWith("unmark ")) {
        String taskNo = str.substring(7).trim();
        try {
            int number = Integer.parseInt(taskNo);
            return new UnmarkCommand(number);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Only number can come after unmark!");
        }
    }

    if (str.startsWith("todo ")) {
        String des = str.substring(5).trim();
        TodoTask td = new TodoTask(des);
        return new AddCommand(td);
    }

    if (str.startsWith("deadline ")) {
        String[] parts = str.substring(9).split("/by", 2);
        if (parts.length < 2) {
            throw new InvalidInputException("Please check the format of task input again!");
        }
        String des = parts[0].trim();
        String by = parts[1].trim();
        assert !des.isEmpty() : "DeadlineTask description must not be empty";
        assert !by.isEmpty() : "DeadlineTask by date must not be empty";
        DeadlineTask ddl = new DeadlineTask(des, by);
        return new AddCommand(ddl);
    }

    if (str.startsWith("event ")) {
        String[] parts = str.substring(6).split("/from|/to");
        if (parts.length < 3) {
            throw new InvalidInputException("Please check the format of task input again!");
        }
        String des = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        assert !des.isEmpty() : "EventTask description must not be empty";
        assert !from.isEmpty() : "EventTask from date must not be empty";
        assert !to.isEmpty() : "EventTask to date must not be empty";
        EventTask ev = new EventTask(des, from, to);
        return new AddCommand(ev);
    }

    if (str.startsWith("delete ")) {
        String taskNo = str.substring(7).trim();
        try {
            int number = Integer.parseInt(taskNo);
            return new DeleteCommand(number);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Only number can come after delete!");
        }
    }

    if (str.startsWith("find ")) {
        String keyword = str.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new InvalidInputException("FindCommand needs a keyword for searching");
        }
        return new FindCommand(keyword);
    }
    throw new InvalidInputException("I cannot find the command OvO");
}
```
Smaller methods as suggested by GPT:  
```Java
public static Command parse(String raw)
            throws CommandException, InvalidInputException {
        String s = sanitize(raw);
        Optional<Command> c;
        if ((c = tryList(s)).isPresent()) return c.get();
        if ((c = tryMark(s)).isPresent()) return c.get();
        if ((c = tryUnmark(s)).isPresent()) return c.get();
        if ((c = tryDelete(s)).isPresent()) return c.get();
        if ((c = tryTodo(s)).isPresent()) return c.get();
        if ((c = tryDeadline(s)).isPresent()) return c.get();
        if ((c = tryEvent(s)).isPresent()) return c.get();
        if ((c = tryFind(s)).isPresent()) return c.get();
        throw new InvalidInputException("I cannot find the command OvO");
    }

private static Optional<Command> tryList(String s) throws InvalidInputException {
    if (!s.equals("list")) return Optional.empty();
    return Optional.of(new ListCommand());
}

private static Optional<Command> tryMark(String s) throws InvalidInputException {
    return tryNumbered(s, "mark ",
            n -> new MarkCommand(n),
            "Only number can come after mark!");
}

private static Optional<Command> tryUnmark(String s) throws InvalidInputException {
    return tryNumbered(s, "unmark ",
            n -> new UnmarkCommand(n),
            "Only number can come after unmark!");
}

private static Optional<Command> tryDelete(String s) throws InvalidInputException {
    return tryNumbered(s, "delete ",
            n -> new DeleteCommand(n),
            "Only number can come after delete!");
}

private static Optional<Command> tryTodo(String s) throws InvalidInputException {
    final String p = "todo ";
    if (!s.startsWith(p)) return Optional.empty();
    String desc = afterPrefix(s, p);
    if (desc.isEmpty()) throw new InvalidInputException("Todo description must not be empty");
    return Optional.of(new AddCommand(new TodoTask(desc)));
}

private static Optional<Command> tryDeadline(String s) throws InvalidInputException {
    final String p = "deadline ";
    if (!s.startsWith(p)) return Optional.empty();
    String arg = afterPrefix(s, p);
    int cut = arg.indexOf("/by");
    if (cut < 0) throw new InvalidInputException("Format: deadline <desc> /by <when>");
    String desc = arg.substring(0, cut).trim();
    String by = arg.substring(cut + 3).trim();
    if (desc.isEmpty() || by.isEmpty())
        throw new InvalidInputException("Please check the format of task input again!");
    return Optional.of(new AddCommand(new DeadlineTask(desc, by)));
}

private static Optional<Command> tryEvent(String s) throws InvalidInputException {
    final String p = "event ";
    if (!s.startsWith(p)) return Optional.empty();
    String arg = afterPrefix(s, p);
    int i = arg.indexOf("/from"), j = arg.indexOf("/to");
    if (i < 0 || j < 0 || j < i)
        throw new InvalidInputException("Format: event <desc> /from <start> /to <end>");
    String desc = arg.substring(0, i).trim();
    String from = arg.substring(i + 5, j).trim();
    String to = arg.substring(j + 3).trim();
    if (desc.isEmpty() || from.isEmpty() || to.isEmpty())
        throw new InvalidInputException("Please check the format of task input again!");
    return Optional.of(new AddCommand(new EventTask(desc, from, to)));
}

private static Optional<Command> tryFind(String s) throws InvalidInputException {
    final String p = "find ";
    if (!s.startsWith(p)) return Optional.empty();
    String kw = afterPrefix(s, p);
    if (kw.isEmpty()) throw new InvalidInputException("FindCommand needs a keyword for searching");
    return Optional.of(new FindCommand(kw));
}

// -------- helpers --------

private static Optional<Command> tryNumbered(
        String s, String prefix, java.util.function.IntFunction<Command> ctor, String err)
        throws InvalidInputException {
    if (!s.startsWith(prefix)) return Optional.empty();
    String arg = afterPrefix(s, prefix);
    try {
        int n = Integer.parseInt(arg);
        return Optional.of(ctor.apply(n));
    } catch (Exception e) {
        throw new InvalidInputException(err);
    }
}

private static String sanitize(String raw) throws CommandException {
    if (raw == null || raw.trim().isEmpty())
        throw new CommandException("The command is empty");
    return raw.trim();
}

private static String afterPrefix(String s, String prefix) {
    return s.substring(prefix.length()).trim();
} 
```
### Observation: 
when directly asking GPT to improve the code quality by making the parse method shorter than 30LoC, the response was not up to expectation (30LoC). GPT shortens the code by changing it to another format (switch instead of if-else), but was only shortening the code by removing repetitive checks instead of breaking it into smaller methods or different levels of abstraction. Only with specific requirements like "Please break each if block into an individual method", GPT can then satisfy the expectation of 30LoC.  

## Testing
### src/test/java/nina/StorageTest.java
>ChatGPT assisted in enhancing my original JUnit test  

Initially, I used Files.createTempFile when I was trying to test my Storage class. ChatGPT suggested that TempDir can keep the test environment cleaner. Since createTempFile will create a real file and will not automatically remove it after testing, but directory made from TempDir will be automatically deleted after testing.
