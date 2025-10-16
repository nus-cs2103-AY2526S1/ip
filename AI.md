# AI Assistance Report

This file documents how I used AI tools in developing and enhancing my iP project Junny.

## Tools Used
- ChatGPT (OpenAI GPT-5): for generating code snippets, improving code quality and enhancing GUI.

---

## Areas of AI Assistance

### 1. GUI Enhancements
- **What I asked AI for:**  
  Guidance on improving my JavaFX GUI (`DialogBox.fxml`), specifically:
    - Adding a heading ("Chat with Junny") at the top.
    - Changing the font size for better readability.
    - Updating the chatbox background color for clearer distinction between user and chatbot messages.
- **How AI helped:**  
  AI provided the necessary FXML modifications (adding a `Label` node, updating `VBox`/`HBox` styles) and CSS styling hints.
- **My contribution:**  
  I integrated these suggestions, tested them, and tweaked padding/margins to ensure the layout looked clean in my app.

---

### 2. Code Quality Improvements
- **What I asked AI for:**  
  Help in refactoring my `Parser` class to follow better OOP practices. Initially, the `parse` method was long and heavily nested.
- **How AI helped:**  
  AI suggested splitting parsing logic into smaller helper methods (`parseTodo`, `parseDeadline`, `parseEvent`), each handling their own command and error checking.
- **My contribution:**  
  I adapted these suggestions into my codebase, ensuring exception handling matched my app’s design, and tested with various edge cases.

---

### 3. Error Handling & Storage
- **What I asked AI for:**  
  How to improve error handling when loading/saving tasks from file.
- **How AI helped:**  
  AI suggested wrapping file I/O in `try/catch`, creating missing directories/files automatically, and optionally rethrowing as `IllegalStateException` so errors could be displayed in the GUI rather than only printed to console.
- **My contribution:**  
  I updated the `Storage` constructor accordingly, while preserving my existing logic.

---

## Reflections
Using AI tools helped me:
- Save time on trial-and-error when styling JavaFX.
- Learn new ways to refactor code into smaller, more testable units.
- Strengthen error handling in a user-friendly manner.

I treated AI suggestions as **starting points**, not final answers: I tested, adapted, and refined them to fit my project’s requirements.  
