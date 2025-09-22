# AI Usage

Github Copilot's GPT-5 was used to create the `clear` command.

Parser: KingParser.java

- Added CLEAR to Commands enum.
- Added clearRegex = "^clear$" and matcher setup.
  checkParser(CLEAR) returns true when the input is strictly clear.
- Getter getClearMatcher() added for parity with other commands.

Task List: KingTaskList.java

- Added public clearAll() that delegates to the existing reset logic (clears ArrayList and resets storage file).

UI: KingUI.java

- New showClear(int previousSize) returns:
  - “Your list is already empty.” when nothing to clear, else
  - “Cleared all tasks (N) from your list.”
  - Message is short, actionable, and clear.

Main Flow: King.java

- New branch in getResponse(...):
  - If KingParser.Commands.CLEAR is matched, record previous size, call kingTaskList.clearAll(), return kingUI.showClear(prev).
