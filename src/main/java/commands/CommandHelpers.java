package commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Messages;
import errors.BoopError;

/**
 * Utility class providing helper methods for parsing and handling user command
 * inputs.
 *
 * Includes methods such as extracting indices or validating arguments for
 * commands.
 */
public class CommandHelpers {
    /**
     * Gets the index argument for simple task functions like mark and delete
     *
     * @param input Full input string passed by the user
     * @return Index given as parameter by the user
     * @throws BoopError
     */
    public static int getIndexArgument(String input) throws BoopError {
        String[] words = input.split("\\s+", 2);

        if (words.length < 2) {
            throw new BoopError(Messages.ERROR_INDEX_MISSING);
        }

        int index;
        try {
            index = Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new BoopError(Messages.ERROR_PARSE_INDEX);
        }

        return index;
    }

    /**
     * Represents a set of parsed flags from user input.
     *
     * Provides easy access to command parameters by mapping flag names to their
     * values,
     * including support for canonical names and aliases.
     * Includes a static method parseFlags(Map, String) for parsing input strings.
     */
    public static class Flags {
        private final Map<String, String> values = new HashMap<>();
        private final Map<String, String> aliasToCanonical = new HashMap<>();

        private Flags(Map<String, List<String>> definitions, String input) throws BoopError {
            buildAliasLookup(definitions);

            // parts is all the flags and the arguments used
            String[] sections = input.split("\\s+", 2);

            if (sections.length < 2) {
                return;
            }

            String[] parts = sections[1].split(" /(?=\\w+)");
            addNoFlagArgument(parts[0].trim());

            parseFlagParts(parts);
        }

        /**
         * Builds a mapping from flag aliases to canonical names.
         *
         * @param definitions mapping of canonical flags to all valid flag names
         */
        private void buildAliasLookup(Map<String, List<String>> definitions) {
            for (Map.Entry<String, List<String>> e : definitions.entrySet()) {
                String canonical = e.getKey();
                for (String alias : e.getValue()) {
                    aliasToCanonical.put(alias, canonical);
                }
            }
        }

        private void addNoFlagArgument(String arg) {
            if (!arg.isEmpty()) {
                values.put("", arg);
            }
        }

        private void parseFlagParts(String[] parts) throws BoopError {
            for (int i = 1; i < parts.length; i++) {
                String[] flagSplit = parts[i].split("\\s+", 2);
                String rawFlag = flagSplit[0];
                String value = extractFlagValue(flagSplit);

                String canonical = aliasToCanonical.get(rawFlag);
                validateFlag(rawFlag, canonical);

                values.put(canonical, value);
            }
        }

        private String extractFlagValue(String[] flagSplit) {
            return flagSplit.length > 1 ? flagSplit[1].trim() : "";
        }

        private void validateFlag(String rawFlag, String canonical) throws BoopError {
            if (canonical == null) {
                throw new BoopError(String.format(Messages.ERROR_UNKNOWN_FLAG, rawFlag));
            }
            if (values.containsKey(canonical)) {
                throw new BoopError(String.format(Messages.ERROR_DUPLICATE_FLAG, rawFlag));
            }
        }

        /**
         * Returns flags in a format that allows for easy access
         *
         * @param definitions Mapping of canonical flag to all valid flag names
         * @param input       Full input string passed by the user
         * @return Flags that contain input parameters
         */
        public static Flags parseFlags(
                Map<String, List<String>> definitions,
                String input) {
            return new Flags(definitions, input);
        }

        /**
         * Returns value of flag
         *
         * @param flag Canonical name of flag
         * @return flag value
         */
        public String get(String flag) {
            return values.get(flag);
        }

        /**
         * Returns whether flag is present
         *
         * @param flag Canonical name of flag
         * @return is flag present
         */
        public boolean has(String flag) {
            return values.containsKey(flag);
        }
    }
}
