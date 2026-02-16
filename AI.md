
### Tools Used
- ChatGPT (OpenAI)

`Improve DialogBox: avatar clipping + dialog roles A-AiAssisted`
- AI helped suggest:
    - How to clip an `ImageView` into a circular avatar using `Circle`.
    - Refactoring `DialogBox` to add `setAsUser()` and `setAsBot()` helper methods with style classes.
    - - Improved the GUI for better user experience:
  - Added error highlighting: error messages are now displayed inside a red-tinted dialog box, making them stand out from normal responses.
  - Tweaked the `DialogBox` to support applying different CSS style classes (e.g., `dialog-error`) and ensured wrapping width for longer messages.
- AI suggested:
  - How to detect error replies reliably (based on `"OOPS!"` prefix from `Geni#getResponse`).
  - How to apply CSS selectors and JavaFX style classes (`dialog-error`, `.error-label`) to make error bubbles red.
  - How to link the `styles.css` file in `Main.start()` for consistent styling.

- ### Observations
    - Worked well: The clipping code was correct and easy to adapt.
    - Needed adjustment: The radius had to be tuned to match my layout.
- **What worked well**: The AI code snippets for JavaFX CSS (`setAsError()`, `.dialog-error .label` rules) integrated easily. The suggestion to check for `"OOPS!"` prefix simplified the error detection logic.
- **What needed adjustments**: Some initial AI suggestions over-complicated the error detection by checking multiple keywords. After inspecting `Geni#getResponse`, I simplified it to just detect `"OOPS!"`.
- **Overall**: AI assistance saved time in experimenting with JavaFX styling and helped polish the UI without much trial-and-error.
