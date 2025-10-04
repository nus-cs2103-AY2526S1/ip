# Ang Soon Tong User Guide
> **Ang Soon Tong is a secret society and gang based in Singapore and Malaysia which has been active since the 1950s, mainly in the Sembawang area of Singapore.**

<img width="798" height="1248" alt="image" src="https://github.com/user-attachments/assets/99b6e451-8c52-49ec-8737-5dfc7d67d119" />

[Soon Tong](https://soundcloud.com/21ast/21-ast-pht-gss-18siaokimtian) is your friendly neighbourhood ah beng, he can help you:

- Keep track of your daily tasks/commitments
- Entertain you
- Lend a listening ear
- Talk to you ~politely~

Here's 3 fun facts about Soon Tong

1. He knows 2 languages - hokkien and java
2. **Humsup**
3. Likes Matcha 🍵🍃 and Clairo

His hobbies

- [x] Legal activities
- [x] Listen to his [favourite song](https://soundcloud.com/nicholas-chong-19/crazy-baby-nightcore-remix?si=4a9cc4d856c940eab66aadf5184fc5b4&utm_source=clipboard&utm_medium=text&utm_campaign=social_sharing)

## The 3 Types of Tasks

Soon Tong can help you keep track of 3 different kinds of tasks.

### 1. ToDo
Just plain, simple tasks - where the name is just enough.

Example: `todo go for class`

### 2. Deadline
Painful tasks you need to complete before a certain date.

Example: `deadline tutorial sheet /by 2025-09-15`

### 3. Event
For the events with start and end dates.

Example: `event aunt's funeral /from 2025-02-15 /to 2025-02-16`

## Delete
Remove added tasks easily, with the delete feature.

Example: `delete 2`\
Returns: 
```
Ok la! I delete already ah:
[T][] task A
Now you got 1 task only.
```

## List
Shows you the current task list.

Example: `list`\
Returns: 
```
Oi! This one your list:
1. [T][] task A
2. [D][X] task B (by: 16 Aug 2025)
```

## Mark/Unmark
This feature allows you to mark your tasks
as done when you finish them...

Example: `mark 2`\
Returns:
```
Ok la! Do already
[T][X] go for class 
```

Or unmark marked tasks if you've made a mistake.

Example: `unmark 2`\
Returns:
```
Huh why haven't do?!
[T][] go for class 
```

## Tag/Untag
Add more information to your tasks! By using tags #yay

Example: `tag 2 #fun #holiday`\
Returns: 
```
Ok tag already! :
[T][] go bali { #fun, #holiday }
```

On the converse, use untag to remove tags.

Example: `untag 2 #fun #holiday`\
Returns:
```
Removed tags liao! :
[T][] go bali
```

## Find
Allows you to search for tasks with keywords.

Example: `find CS2100`\
Returns:
```
This one your matching tasks! :
[T][] CS2100 lecture
[T][X] CS2100 tutorial
```

## Sing
Arguably the best feature here - listen to Soon Tong sing a song for you.

Example: `sing`

Try it for yourself to find out what it does! Soon Tong knows quite a few songs!

## Bye
And finally, when you are done chatting with Soon Tong. Bye lets you quit the chatbot.

Example: `bye`\
Returns: `Bye. You still here for what?!`

The chatbot closes automatically afterward.
