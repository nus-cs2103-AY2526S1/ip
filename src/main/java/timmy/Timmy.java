package timmy;

import exceptions.TimmyDateParsingException;
import exceptions.TimmyFilerException;
import exceptions.TimmyInvalidParamException;
import exceptions.TimmyTaskListOutOfBoundsException;

public class Timmy {
    protected final Ui ui;
    protected final TaskList taskList;

    public Timmy() {
        this.ui = new Ui();
        this.taskList = new TaskList(Storage.loadStorage());
    }

    public static void main(String[] args) {
        return;
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    public String getResponse(String input) {
        try {
            String[] args = Parser.parseCommand(input);
            ValidCommand command = ValidCommand.valueOf(args[0].toUpperCase());
            switch (command) {
            case LIST:
                return handleList();
            case MARK:
                return handleMark(args[1]);
            case UNMARK:
                return handleUnmark(args[1]);
            case TODO:
                return handleToDo(args[1]);
            case DEADLINE:
                return handleDeadline(args[1]);
            case EVENT:
                return handleEvent(args[1]);
            case DELETE:
                return handleDelete(args[1]);
            case FIND:
                return handleFind(args[1]);
            case CLEAR:
                return handleClear();
            case ARCHIVE:
                return handleArchive();
            default:
                return "";
            }

        } catch (IllegalArgumentException e) {
            return ui.getUnknownCommandMessage();
        } catch (TimmyInvalidParamException e) {
            return ui.getInvalidParameterMessage();
        } catch (TimmyTaskListOutOfBoundsException e) {
            return ui.getInvalidIndexMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.getNoArgumentsMessage();
        } catch (TimmyDateParsingException e) {
            return ui.getInvalidDateFormatMessage();
        } catch (TimmyFilerException e) {
            return ui.getFilerErrorMessage();
        }
    }

    private String handleList() {
        return ui.getList(this.taskList);
    }

    private String handleMark(String input) throws TimmyInvalidParamException, TimmyTaskListOutOfBoundsException {
        int index = Parser.parseMark(input);
        Task targetTask;
        targetTask = this.taskList.getTask(index);
        targetTask.markAsDone();
        Storage.saveStorage(this.taskList.getList());
        return ui.getMarkMessage(targetTask);
    }

    private String handleUnmark(String input) throws TimmyInvalidParamException, TimmyTaskListOutOfBoundsException {
        int index = Parser.parseMark(input);
        Task targetTask;
        targetTask = this.taskList.getTask(index);
        targetTask.markAsNotDone();
        Storage.saveStorage(this.taskList.getList());
        return ui.getUnmarkMessage(targetTask);
    }

    private String handleToDo(String input) {
        ToDo newToDo = new ToDo(input);
        this.taskList.add(newToDo);
        Storage.saveStorage(this.taskList.getList());
        return ui.getAddMessage(newToDo, this.taskList.size());
    }

    private String handleDeadline(String input) throws TimmyInvalidParamException, TimmyDateParsingException {
        String[] args = Parser.parseDeadline(input);
        Deadline newDeadline = new Deadline(args[0], args[1]);
        this.taskList.add(newDeadline);
        Storage.saveStorage(this.taskList.getList());
        return ui.getAddMessage(newDeadline, this.taskList.size());
    }

    private String handleEvent(String input) throws TimmyInvalidParamException {
        String[] args = Parser.parseEvent(input);
        Event newEvent = new Event(args[0], args[1], args[2]);
        this.taskList.add(newEvent);
        Storage.saveStorage(this.taskList.getList());
        return ui.getAddMessage(newEvent, this.taskList.size());
    }

    private String handleDelete(String input) throws TimmyInvalidParamException, TimmyTaskListOutOfBoundsException {
        int index = Parser.parseMark(input);
        Task deletedTask;
        deletedTask = this.taskList.getTask(index);
        this.taskList.remove(index);
        Storage.saveStorage(this.taskList.getList());
        return ui.getDeleteMessage(deletedTask, taskList.size());
    }

    private String handleFind(String input) throws TimmyInvalidParamException {
        if (input.isEmpty()) {
            throw new TimmyInvalidParamException();
        }
        TaskList filteredList = this.taskList.find(input);
        return ui.getFilteredList(filteredList);
    }

    private String handleClear() {
        this.taskList.clear();
        Storage.saveStorage(this.taskList.getList());
        return ui.getClearMessage();
    }

    private String handleArchive() {
        String archivePath = Storage.archiveStorage(this.taskList.getList());
        this.taskList.clear();
        Storage.saveStorage((this.taskList.getList()));
        return ui.getArchiveMessage(archivePath);
    }
}
