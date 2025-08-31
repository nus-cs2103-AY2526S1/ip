package stella;
public class Parser {
    private TaskList list;

    public Parser(TaskList list) {
        this.list = list;
    }

    public void identifyCommand(String description) throws IncompleteInstructionException, UnknownInstructionException {
            if (description.equals(("list"))) {
                this.list.printList();
            } else if (description.contains("find")) {
                if (description.length() <= 5) {
                    throw new IncompleteInstructionException(description);
                }

                String keyword = "";
                if (description.length() == 6) {
                    keyword = keyword + String.valueOf(description.charAt(5));
                }
                keyword = keyword + description.substring(6);

                TaskList temp = list.findItem(keyword);
                if (temp.getList().isEmpty()) {
                    System.out.println("No items found");
                }
                else {
                    temp.printList();
                }
            } else if (description.contains("delete")) {
                int index = findIndexForModification("delete", description);
                list.deleteItem(index);
            } else if (description.contains("unmark")) {
                int index = findIndexForModification("unmark", description);
                list.modifyItem(index, "unmark");
            } else if (description.contains("mark")) {
                int index = findIndexForModification("mark", description);
                list.modifyItem(index, "mark");
            } else if (description.contains("todo")) {
                if (description.length() <= 5) {
                    throw new IncompleteInstructionException(description);
                }
                String details = description.substring(5);
                ToDo temp = new ToDo(details);
                list.addItem(temp);

            } else if (description.contains("deadline")) {
                if (description.length() <= 9) {
                    throw new IncompleteInstructionException(description);
                }
                String details = description.substring(9, description.indexOf('/'));
                String deadline = description.substring(description.indexOf('/') + 1);
                deadline = this.formatTime(deadline);
                Deadline temp = new Deadline(details, deadline);
                list.addItem(temp);


            } else if (description.contains("event")) {
                if (description.length() <= 6) {
                    throw new IncompleteInstructionException(description);
                }
                String details = description.substring(6, description.indexOf('/'));
                String start = description.substring(description.indexOf('/') + 1, description.lastIndexOf('/'));
                String end = description.substring(description.lastIndexOf('/') + 1);
                start = this.formatTime(start);
                end = this.formatTime(end);
                Event temp = new Event(details, start, end);
                list.addItem(temp);

            } else {
                throw new UnknownInstructionException(description);
            }

    }

    private int findIndexForModification(String marker, String description) throws IncompleteInstructionException {
        int referencePoint = 0;
        if (marker == "delete" || marker == "unmark") {
            referencePoint = 7;
        }
        else if (marker == "mark") {
            referencePoint = 5;
        }


        if (description.length() <= referencePoint) {
            throw new IncompleteInstructionException(description);
        }

        return Integer.valueOf(description.substring(referencePoint)) - 1;

    }

    private String formatTime(String time) {
        if (time.length() == 10) {
            return TimeConverter.convertDate(time);
        }
        if (time.length() == 15) {
            return TimeConverter.convertDatewithTime(time);
        }
        return time;
    }


}