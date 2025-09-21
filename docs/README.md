# **THOPZ** 



## Thopz project template

This a java project on Thopz chatbot. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()` 
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## INSTRUCTIONS

### General Overview

Thopz is a chatbot which helps with managing your task. Inbuilt within it is 
its amazing user interface which facilitates various actions with managing tasks


### HOW TO USE

1) Upon running the launcher file you will be lead to another window screen. (Use fullscreen mode) 
2) You should be greeted with "Wassup wassup! Life ok? Vibes ok? Family ok? I am thopz your virtual chatbot. How may I help you"
3) Upon reaching that you can use these following commands

### Supported commands

1. **todo** <add a todo task>

todo comand adds a todo tasks
(_E.g todo eat_ )
* Ensure there is a space after the todo to add the event

2. **deadline** <add a deadline task> / <add a due date>

deadline comand adds a task and sets a deadline to it 
(_E.g deadline eat / 10/10/2024 05:45_ )
* Ensure there is a '/ ' after the event before you input the deadline. (Take note of the space after the slash)
You can put in any of the following formats
- dd/MM/yyyy HH:mm,
- dd-MM-yyyy HH:mm,
- yyyy-MM-dd HH:mm,
- yyyy/dd/MM HH:mm

It will automatically be converted to MMM dd yyyy, HH:mm.

* If you do not follow the format, it would js display as the input string after the slash

3. **event** <add a deadline task> / <add a due date> / <add a end date>

deadline command adds a task and sets a deadline to it
(_E.g. deadline fight / 10/10/2024 05:45 / 12/10/2024 06:30_ )
* Ensure there is a '/' after the event before you input the start time and another '/' before you put the end time
  You can put in any of the following formats
- dd/MM/yyyy HH:mm,
- dd-MM-yyyy HH:mm,
- yyyy-MM-dd HH:mm,
- yyyy/dd/MM HH:mm

It will automatically be converted to MMM dd yyyy, HH:mm.

* If you do not follow the format, it would js display as the input string after the slash

4. **list**

list command lists out all saved tasks

(_E.g. list_)

* List shows a list of saved tasks in the order it has been added

5. **mark <task number from the list>**

Marks a task number based on the ordering in the list
(_E.g. mark 2_)
* Ensure there is a space after mark before inputting number

6. **delete <task number from the list>**

Deletes a task in the list based on its task number
(_E.g. delete 2_)
* Ensure there is a space after delete before inputting number

7. **find** <add a word or phrase to find>

Finds matching tasks based on the phrase letters which is used
(_E.g. find arc_)

* Ensure there is a space after the find before you add the word you wish to find
* A list of tasks will be returned with their corresponding task number on the list
* Half words would also return matching tasks

8. **bye**


End command to exit from the application
(_E.g. bye_)

* Exits the application after 3 seconds
* Process is stopped 









