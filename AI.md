Used in Week 6 for A-AiAssisted increment

Used Copilot to generate new error handling in Parser.java file. 
Generated code was to handle case where user input is completely empty at lines 51-53.
However, code was buggy at was underlined red by IDE for exception not being caught.
This was because it threw SimonException, but the method signature throws SimonException.EmptyTaskException
    and SimonException.UnknownCommandException.
Used AI again to solve this, and the edit was simply to change the exception thrown in lines 51-53 to
    SimonException.EmptyTaskException

Also used Copilot to generate new error handling in Storage.java file.
Generated code is in line 66-72 for handling IO Exception thrown while trying to write tasks to the data file.
No issues here and the code worked fine.