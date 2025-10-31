## GUI Enhancements Summary

This document explains the recent GUI changes made using Claude to improve the chat interface, styling, and exit behavior.

### Overview of Changes
- Dialog bubble styles now render
- Graceful exit on "bye" with 3-second delay
- Added state flags `isUser`, `isError` and `applyStyles()` with CSS classes: `user-message`, `bot-message`, `error-message`, `user-avatar`, `bot-avatar`.
- Adds welcome message on startup.
- Detects errors via response prefix and styles bot bubble accordingly.
- On `bye`, disables input and exits after 3 seconds using `PauseTransition`.