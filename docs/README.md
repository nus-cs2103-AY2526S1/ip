# Moon
> “Your mind is for having ideas, not holding them.” – David Allen

***Moon* frees your mind of having to remember things you need to do. It's,**

- text-based
- easy to learn
- ***~~FAST~~ SUPER FAST*** to use

All you need to do is,

1. download it from [here](https://github.com/duyLeu/ip/releases/tag/v3.1).
2. double-click it.
3. add your *tasks*.
4. let it manage your tasks for you :stuck_out_tongue_winking_eye:

And it is **FREE!**

## Features
### 1. Add tasks
#### ToDos
Tasks without any date/time attached to it e.g., visit new theme park
```declarative
todo {task description}
```

#### Deadlines
Tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
```declarative
deadline {task description} /by dd/mm/yyyy HHMM {deadline time}
```
(`HHMM` time is optional)

#### Events
Tasks that start at a specific date/time and ends at a specific date/time e.g., 
- team project meeting 2/10/2019 2-4pm 
- orientation week 4/10/2019 to 11/10/2019
```declarative
event {task description} /from dd/mm/yyyy HHMM {start time} /by dd/mm/yyyy HHMM {end time}
```
(`HHMM` time is optional)

### 2. Show list of tasks
After adding allllll your new tasks, you can ask Moon to show all of them, all at once!
```declarative
list
```

### 3. Mark/Unmark tasks
You can also choose to **mark**, or **unmark** tasks to show that you have done them, or you still have unfinished tasks!
```declarative
mark {task number}

unmark {task number}
```

### 4. Delete tasks
Once you finished your tasks, you can **delete** it to free up space for new tasks!
```declarative
delete {task number}
```

### 5. Find tasks
Too many tasks and don't know where to find them? @-@ Don't worry! Moon has got you covered!
Just put in a keyword, or even *part of a keyword*, and Moon will help you find that tasks along with its index number in the task list!
```declarative
find {keyword}
```
### 6. Practice Java!
Additionally, if you are a Java programmer, you can use it to practice Java too. Here's the Launcher method:

```java
public class Launcher {
    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
```
## Upcoming features
Sit tight, because we have a whole host of new features coming to you as soon as possible
- [X] Search tasks
- [ ] Managing deadlines (coming soon)
- [ ] Reminders (coming soon)
