# V - A V for Vendetta Themed Task Manager

V is a sophisticated task management application inspired by the iconic character from V for Vendetta. This JavaFX-powered application combines dramatic theatrical flair with practical task organization, featuring a modern glassmorphism UI and V's distinctive personality.

## Features

- **Dramatic V Personality**: All interactions are delivered with V's theatrical voice and revolutionary spirit
- **Modern JavaFX GUI**: Beautiful glassmorphism interface with floating elements and smooth animations  
- **V for Vendetta Theme**: Complete visual theming with background images, Guy Fawkes mask avatar, and thematic styling
- **Task Management**: Full CRUD operations for todos, deadlines, and events
- **Intelligent Search**: Case-insensitive task searching with V-themed responses
- **Persistent Storage**: Automatic saving and loading of tasks
- **Responsive Design**: Scalable interface that works across different screen sizes

## Quick Start

Run the application using the pre-built JAR file:
```bash
java -jar v-all.jar
```

Or build and run from source:
```bash
./gradlew run
```

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## Run (JAR)
1. Download `v-all.jar` from the Releases page.
2. Put it in an empty folder.
3. Open a terminal in that folder.
4. Run:
   ```bash
   java -jar "v-all.jar"
   ```

## Build (optional)
To build the runnable JAR yourself:
```bash
./gradlew clean shadowJar      # on macOS/Linux
# or
.\\gradlew clean shadowJar      # on Windows
```
The JAR will be at `build\\libs\\v-all.jar`.

## Acknowledgements

### AI Assistance
This project was developed with  AI assistance using gpt for:
- JavaFX GUI development and modern UI styling
- Code quality improvements and refactoring
- V personality development and thematic responses
