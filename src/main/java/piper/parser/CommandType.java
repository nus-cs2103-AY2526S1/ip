package piper.parser;

/**
 * Enumerates Piper commands and whether they require an argument.
 */

 public enum CommandType {
     BYE(false),
     LIST(false),
     TODO(true),
     DEADLINE(true),
     EVENT(true),
     MARK(true),
     UNMARK(true),
     DELETE(true),
     FIND(true),
     SNOOZE(true);

     private final boolean isRequired;

     CommandType(boolean isRequired) {
         this.isRequired = isRequired;
     }

     public boolean requiresArg() {
         return isRequired;
     }

     /**
      * Maps a lower-cased token to a command type, or null if unknown.
      */
     public static CommandType from(String token) {
         switch (token) {
         case "bye": return BYE;
         case "list": return LIST;
         case "todo": return TODO;
         case "deadline": return DEADLINE;
         case "event": return EVENT;
         case "mark": return MARK;
         case "unmark": return UNMARK;
         case "delete": return DELETE;
         case "find": return FIND;
         case "snooze": return SNOOZE;
         default: return null;
         }
     }
}
