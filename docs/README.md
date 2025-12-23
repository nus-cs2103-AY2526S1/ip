# Scribbles User Guide 😃

Scribbles is your personal companion to help you organise your daily tasks!
Scribbles is:
- CLI-Based
- Simple
- ~Friendly~ **_SUPER FRIENDLY_** ❤️ ❤️

![Screenshot of Scribbles App](https://its-me-orion.github.io/ip/Ui.png)

## Requirements
Ensure you have Java `17` or above installed in your Computer.

**Mac users**: Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

## Installation Guide
1. click the link [here](https://github.com/its-me-orion/ip/releases) and download the latest release `.jar` file
2. Double-click the `.jar` file in your local computer
3. Add your tasks with the `add` command
4. List your tasks with the `list` command
5. Watch it manage the task for you! 💯

## Commands
> Notes about command format:  
Words in rounded brackets are parameters required to be supplied by the user to use the command  
&nbsp;&nbsp;&nbsp;&nbsp;e.g. in `mark I(index)`, `index` is a parameter to mark a task as completed at that specific index  
Parameters are prefixed with a character that represents the expected type of input:  
`I(paramName)` represents that the parameter expects an `Integer`  
`S(paramName)` represents that the parameter expects a `String`  
`D(paramName)` represents that the parameter expects a valid `Date` format of `d/m/yyyy hhmm`  
&nbsp;&nbsp;&nbsp;&nbsp;e.g. A valid date could be `19/5/2025 1900` which represents 19 May 2025 at 7:00pm

  
### Viewing help: `help`

Format: `help`  
List down all possible commands that can be used
  
### Adding a todo task: `todo`

Format: `todo S(taskName)`  
Adds a todo task with the name `taskName`
  
### Adding a task with a deadline: `deadline`

Format: `deadline S(taskName) /by D(date)`  
Adds a deadline task with the name `taskName` to be completed by `date`
  
### Adding an event task: `event`

Format: `event S(eventName) /from D(fromDate) /to D(toDate)`  
Adds a event task with the name `eventName` that takes place from `fromDate` to `toDate`
  
### Listing all tasks: `list`

Format: `list`  
Lists all the tasks that was added and not deleted
  
### Marking a task as complete: `mark`

Format: `mark I(index)`  
Marks a task at the specified `index` number as completed
  
### Unmarking a task as complete: `unmark`

Format: `unmark I(index)`  
Unmarks a task at the specified `index` number as completed
  
### Finding a task: `find`

Format: `find S(searchString)`  
Find and list all tasks that contains `searchString` in their name
  
### Exiting the application: `bye`

Format: `bye`  
Exits the application after a few seconds