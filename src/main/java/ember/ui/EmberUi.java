    package ember.ui;

    import ember.exception.InvalidInputException;
    import ember.ListManager;
    import ember.exception.MissingInformationException;

    /**
     * Handles user interaction in the console for the Ember application.
     * Reads commands from the user, executes task management commands,
     * and provides feedback until the user exits.
     */
    public class EmberUi {
        ListManager list = new ListManager();

        /**
         * Starts the user interface loop, processes commands until "bye" is entered.
         * Recognized commands include:
         * <ul>
         *     <li>list - displays current tasks</li>
         *     <li>mark {index} - marks task at index as done</li>
         *     <li>unmark {index} - marks task at index as not done</li>
         *     <li>todo {description} - adds a todo task</li>
         *     <li>deadline {description} /by {date} - adds a deadline task</li>
         *     <li>event {description} /from {start} /to {end} - adds an event task</li>
         *     <li>delete {index} - deletes the task at index</li>
         *     <li>bye - exits the program</li>
         * </ul>
         *
         * Invalid inputs will throw and catch exceptions, printing helpful messages.
         */
        public String run(String userInput) {
            String response = "";
            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    response = "Bye. Hope to see you again soon!\n";
                } else if (userInput.equalsIgnoreCase("list")) {
                    response = list.printList();
                } else if (userInput.toLowerCase().startsWith("mark")) {
                    try {
                        String[] parts = userInput.split(" ");
                        // Assumption: mark command should have exactly 2 parts
                        assert parts.length >= 1 : "Split should produce at least one part";
                        if (parts.length == 2) {
                            response = list.mark(Integer.parseInt(parts[1]));
                        }
                    } catch (InvalidInputException e) {
                        response = e.getMessage();
                    }
                } else if (userInput.toLowerCase().startsWith("unmark")) {
                    try {
                        String[] parts = userInput.split(" ");
                        assert parts.length >= 1 : "Split should produce at least one part";
                        if (parts.length == 2) {
                            response = list.unmark(Integer.parseInt(parts[1]));
                        }
                    } catch (InvalidInputException e) {
                        response = e.getMessage();
                    }
                } else if (userInput.toLowerCase().startsWith("todo")) {
                    try {
                        String taskName = userInput.substring(5);
                        response = list.inputList(taskName);
                    } catch (StringIndexOutOfBoundsException e) {
                        response = "Remember to input a 'space' before typing your task description";
                    }
                } else if (userInput.toLowerCase().startsWith("deadline")) {
                    try {
                        String[] parts = userInput.split(" /by ");
                        String taskName = parts[0].substring(9);
                        String taskDeadline = parts[1];
                        response = list.inputList(taskName, taskDeadline);
                    } catch (StringIndexOutOfBoundsException e) {
                        response = "Remember to input a 'space' before typing your task description. ";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        response = "After typing your task description, input your deadline with '/by (date)'. ";
                    }
                } else if (userInput.toLowerCase().startsWith("event")) {
                    try {
                        String[] parts = userInput.split(" /from ");
                        String taskName = parts[0].substring(6);
                        String[] taskDetail = parts[1].split(" /to ");
                        String taskStart = taskDetail[0];
                        String taskEnd = taskDetail[1];
                        response = list.inputList(taskName, taskStart, taskEnd);
                    } catch (StringIndexOutOfBoundsException e) {
                        response = "Remember to input a 'space' before typing your task description. ";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        response = "After typing your task description, input your deadline with '/from (date) /to (date)'. ";
                    }
                } else if (userInput.toLowerCase().startsWith("delete")) {
                    try {
                        String[] parts = userInput.split(" ");
                        if (parts.length == 2) {
                            response = list.deleteTask(Integer.parseInt(parts[1]));
                        }
                    } catch (InvalidInputException | NumberFormatException e) {
                        response = e.getMessage();
                    }
                } else if (userInput.toLowerCase().startsWith("find")) {
                    String keyword = userInput.substring(5);
                    response = list.findKeyword(keyword);
                } else if (userInput.toLowerCase().startsWith("list by name")) {
                    response = list.sortByName();
                } else if (userInput.toLowerCase().startsWith("list by date")) {
                    response = list.sortByDate();
                } else {
                    throw new InvalidInputException("You have entered an invalid input.\n " +
                            "To add a new task, start with: todo, deadline or event.\n " +
                            "To exit, type 'bye'.");
                }
            } catch (InvalidInputException | MissingInformationException e) {
                response = e.getMessage();
            }
            // Post-condition: response should never be null at the end
            assert response != null : "Response should never be null before returning";
        return response;
        }
    }

