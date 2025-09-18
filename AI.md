## Week 6 – GUI Enhancements

### 1. Asymmetric Conversation Format
- **Tool used**: ChatGPT
- **Task**: Tweaked the GUI so that the user’s input and the chatbot’s response are displayed in different formats, reflecting the asymmetric nature of the conversation.
- **Files modified**: `DialogBox.java`, `MainWindow.java`
- **How AI helped**: Suggested separating user and bot messages into distinct dialog boxes with mirrored alignment and different background styling. Helped adjust VBox alignment in `MainWindow` to stack responses properly.
- **Reflection**: Saved me time in figuring out JavaFX alignment properties and cleaner code for flipping dialog nodes.

### 2. Error Highlighting
- **Tool used**: ChatGPT
- **Task**: Made error messages stand out by applying a different visual style.
- **Files modified**: `DialogBox.java`, `dialog.css`
- **How AI helped**: Suggested CSS styling and integration into JavaFX. Showed how to add a conditional check in `DialogBox` for error-type messages and apply a CSS class.
- **Reflection**: Learned how JavaFX can apply CSS classes dynamically.