package com.arnavjhajharia.penguin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.arnavjhajharia.penguin.logic.FileParser;
import com.arnavjhajharia.penguin.model.task.Deadline;
import com.arnavjhajharia.penguin.model.task.Event;
import com.arnavjhajharia.penguin.model.task.Task;
import com.arnavjhajharia.penguin.model.task.Todo;


public class TaskList {

    /**
     * The backing list of tasks. Indexing is zero-based internally.
     */
    private final List<Task> tasks;

    /**
     * Maximum number of tasks allowed in this list.
     */
    private final int limit;

    /**
     * Optional file name associated with this task list. If present, it is used by {@link #save()} and
     * as the source when loading on construction or via {@link #loadFromFile(String)}.
     */
    private Optional<String> fileName;

    /**
     * Creates an empty {@code TaskList} with a maximum capacity but without an associated file.
     * No loading is attempted.
     *
     * @param limit maximum number of tasks that can be contained
     */
    public TaskList(int limit) {
        assert limit > 0 : "limit must be positive";
        this.tasks = new ArrayList<>();
        this.limit = limit;
        this.fileName = Optional.empty();
        assertInvariants();
    }

    /**
     * Creates an empty {@code TaskList} with a maximum capacity and an associated file name,
     * then attempts to load tasks from that file immediately.
     *
     * @param limit    maximum number of tasks that can be contained
     * @param fileName path of the file to load from and optionally save to later
     */
    public TaskList(int limit, String fileName) {
        assert limit > 0 : "limit must be positive";
        assert fileName == null || !fileName.trim().isEmpty() : "fileName, if provided, must not be blank";
        this.limit = limit;
        this.fileName = Optional.ofNullable(fileName);
        this.tasks = new ArrayList<>();
        loadFromFileIfPresent();   // <<— load on construction
        assertInvariants();
    }

    /**
     * Associates this list with the given file path and loads tasks from it, replacing none of the existing
     * tasks but appending until the {@link #limit} is reached.
     *
     * @param filePath path to the file to read from
     */
    public void loadFromFile(String filePath) {
        assert filePath != null && !filePath.isBlank() : "filePath must be non-null and non-blank";
        this.fileName = Optional.ofNullable(filePath);
        loadFromFileIfPresent();
        assertInvariants();
    }

    /**
     * Attempts to load tasks from {@link #fileName} if present. Each line is parsed into a {@link Task}
     * via {@link #parseLineToTask(String, int)} and appended until {@link #limit} is reached.
     * <p>
     * Lines that cannot be parsed (returning {@code null}) are skipped. An invalid {@code Event} line will
     * throw an {@link IllegalArgumentException}.
     *
     * @throws IllegalArgumentException if an Event line has an invalid format (see {@link #parseLineToTask(String, int)})
     */
    private void loadFromFileIfPresent() {
        if (fileName.isEmpty()) return;
        assert fileName.isEmpty() || !fileName.get().trim().isEmpty() : "fileName must not be blank when present";

        List<String> lines = FileParser.readLinesFromFile(fileName.get());
        for (String line : lines) {
            if (tasks.size() >= limit) break;
            Task parsed = parseLineToTask(line, tasks.size());
            if (parsed != null) {
                tasks.add(parsed);
            }
        }
        assertInvariants();
    }

    /**
     * Saves the current tasks to the associated {@link #fileName}, if present.
     *
     * @return {@code true} if a file name is present and saving succeeded; {@code false} if no file name is present or writing failed
     */
    public boolean save() {
        return fileName.filter(this::saveToFile).isPresent();
    }

    /**
     * Saves the current tasks to the specified file path using each task's
     * {@link Task#toStorageLine()} representation.
     *
     * @param filePath destination file path
     * @return {@code true} if the write succeeded; {@code false} otherwise
     */
    public boolean saveToFile(String filePath) {
        assert filePath != null && !filePath.isBlank() : "filePath must be non-null and non-blank";
        List<String> lines = tasks.stream()
                .map(Task::toStorageLine)   // <<— polymorphic call
                .collect(Collectors.toList());
        return FileParser.writeLinesToFile(filePath, lines);
    }



    /**
     * Parses a single line into a concrete {@link Task}. The following storage formats are supported:
     * <pre>
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | 2025-08-06T14:00 | 2025-08-06T16:00
     * E | 1 | standup | 2025-08-07T10:00 | 2025-08-07T11:00
     * </pre>
     * Notes:
     * <ul>
     *   <li>Pipes may be surrounded by arbitrary whitespace.</li>
     *   <li>The "done" flag accepts {@code 1} / {@code 0} (and {@code true}/{@code false} for marking done).</li>
     *   <li>Event timestamps are expected as ISO-like strings; the method does not parse them here—strings are passed through to {@link Event}.</li>
     * </ul>
     *
     * @param line   the raw line from storage
     * @param nextId the zero-based id to assign to the created task
     * @return a {@link Task} instance if parsing succeeds; {@code null} if the line is ignorable/invalid for non-Event types
     * @throws IllegalArgumentException if an Event line does not contain both start and end (minimum 5 parts)
     */
    private Task parseLineToTask(String line, int nextId) {
        assert nextId >= 0 : "nextId must be non-negative";
        if (line == null) return null;
        String trimmed = line.trim();
        if (trimmed.isEmpty()) return null;

        // Split on pipes with optional surrounding whitespace
        String[] parts = trimmed.split("\\s*\\|\\s*");
        // Expect at least: type | done | desc
        if (parts.length < 3) return null;

        String type = parts[0].trim().toUpperCase();
        String doneFlag = parts[1].trim();
        String desc = parts[2].trim();

        Task t = switch (type) {
            case "T" -> new Todo(desc, nextId);
            case "D" -> {
                String deadline = parts.length >= 4 ? parts[3].trim() : "";
                yield new Deadline(desc, nextId, deadline);
            }
            case "E" -> {

                if (parts.length < 5) {
                    throw new IllegalArgumentException(
                            "Invalid Event line. Expected: E | <0/1> | <desc> | yyyy-MM-dd'T'HH:mm | yyyy-MM-dd'T'HH:mm"
                    );
                }
                String start = parts[3].trim();
                String end   = parts[4].trim();
                yield new Event(desc, nextId, start, end);
            }
            default -> null;
        };

        if (t != null && ("1".equals(doneFlag) || "true".equalsIgnoreCase(doneFlag))) {
            t.markDone();
        }
        return t;
    }

    /**
     * Renders the current tasks as a user-facing list. If no tasks exist, returns a friendly message.
     * The numbering shown to the user is one-based, even though internal storage is zero-based.
     *
     * @return a {@link StringBuilder} containing the formatted list or a no-items message
     */
    public StringBuilder list() {
        if (tasks.isEmpty()) {
            return new StringBuilder("No tasks to list.");
        }
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            text.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return text;
    }

    /**
     * Adds a new task derived from a prompt according to the specified {@link TaskType}.
     * The {@code prompt} is split by the delimiter {@code " /"} to extract optional parts:
     * <ul>
     *   <li>{@code TODO}: {@code &lt;desc&gt;}</li>
     *   <li>{@code DEADLINE}: {@code &lt;desc&gt; /&lt;by&gt;}</li>
     *   <li>{@code EVENT}: {@code &lt;desc&gt; /&lt;from&gt; /&lt;to&gt;}</li>
     * </ul>
     * If the list is at capacity, returns a message and does not add anything.
     *
     * @param prompt raw user input to derive task fields
     * @param type   the type of task to create
     * @return a user-facing confirmation message including the created task and the new count
     */
    public StringBuilder add(String prompt, TaskType type) {
        assert type != null : "type must not be null";
        assertInvariants();
        if (tasks.size() >= limit) {
            return new StringBuilder("Too grindy bro — task list is full (limit " + limit + ").");
        }

        StringBuilder returnText = new StringBuilder();
        String[] parts = prompt.split(" /");

        Task task = null;
        switch (type) {
            case TODO -> {
                String desc = parts[0];
                task = new Todo(desc, tasks.size());
            }
            case DEADLINE -> {
                String desc = parts[0];
                String by = parts.length > 1 ? parts[1].trim() : "";
                // Accept both "/<when>" and "/by <when>"
                if (by.regionMatches(true, 0, "by ", 0, 3)) {
                    by = by.substring(3).trim();
                }
                task = new Deadline(desc, tasks.size(), by);
            }
            case EVENT -> {
                String desc = parts[0];
                String from = parts.length > 1 ? parts[1].trim() : "";
                String to = parts.length > 2 ? parts[2].trim() : "";
                // Accept both "/<from> /<to>" and "/from <from> /to <to>"
                if (from.regionMatches(true, 0, "from ", 0, 5)) {
                    from = from.substring(5).trim();
                }
                if (to.regionMatches(true, 0, "to ", 0, 3)) {
                    to = to.substring(3).trim();
                }
                task = new Event(desc, tasks.size(), from, to);
            }
        }

        assert task != null : "created task must not be null";
        tasks.add(task);
        assertInvariants();
        return returnText
                .append("Added task: ")
                .append(task).append("\n")
                .append(String.format("Total: %d", tasks.size()));
    }

    /**
     * Marks the task at the given zero-based index as done.
     *
     * @param id zero-based index into {@link #tasks}
     * @return a user-facing confirmation or an error message if the index is invalid
     */
    public StringBuilder markDone(int id) {
        assertInvariants();
        if (isInvalidIndex(id)) {
            return new StringBuilder("Invalid task index.");
        }
        Task t = tasks.get(id);
        t.markDone();
        assertInvariants();
        return new StringBuilder("Damn you not chill, completing tasks and stuff! I've marked this task as done:\n")
                .append(t);
    }

    /**
     * Marks the task at the given zero-based index as not done.
     *
     * @param id zero-based index into {@link #tasks}
     * @return a user-facing confirmation or an error message if the index is invalid
     */
    public StringBuilder markUndone(int id) {
        assertInvariants();
        if (isInvalidIndex(id)) {
            return new StringBuilder("Invalid task index.");
        }
        Task t = tasks.get(id);
        t.markUndone();
        assertInvariants();
        return new StringBuilder("Ah you were just lying to yourself. It's chill! I've marked this task as undone:\n")
                .append(t);
    }

    /**
     * Removes the task at the given zero-based index.
     *
     * @param idx zero-based index into {@link #tasks}
     * @return a user-facing confirmation containing the removed task and the remaining count,
     *         or an error message if the index is invalid
     */
    public StringBuilder delete(int idx) {
        assertInvariants();
        if (isInvalidIndex(idx)) {
            return new StringBuilder("Invalid task index.");
        }
        Task removed = tasks.remove(idx);
        StringBuilder sb = new StringBuilder();
        sb.append("Removed task:\n")
                .append("  ").append(removed).append("\n")
                .append("Remaining: ")
                .append(size()).append(" ")
                .append(size() == 1 ? "task" : "tasks");
        assertInvariants();
        return sb;
    }

    /**
     * Finds tasks using a "BetterSearch" strategy.
     * <p>
     * - Case-insensitive by default.
     * - Matches if all query terms match the task text, where a term matches if:
     *   - it is a quoted phrase present as a substring, OR
     *   - it is a single token that is a substring of any word (partial match), OR
     *   - it is within 1 edit (typo tolerance) of any word in the task text.
     * - Example:
     *   - query = book → matches "read a book" and also "booking" (partial)
     *   - query = proj Aug → matches tasks containing both tokens (in any order)
     *   - query = "team meeting" → matches phrase occurrences
     *
     * The numbering in the result corresponds to original task indices (1-based).
     */
    public StringBuilder find(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new StringBuilder("Gimme something to search for, bro.");
        }

        List<String> terms = extractQueryTerms(query);
        List<Integer> hits = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            String hay = tasks.get(i).toString();
            String normHay = normalize(hay);
            boolean allMatch = true;
            for (String term : terms) {
                boolean isPhrase = term.contains(" ");
                boolean matched = isPhrase
                        ? normHay.contains(term)
                        : tokenMatches(normHay, term);
                if (!matched) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) hits.add(i);
        }

        if (hits.isEmpty()) {
            return new StringBuilder("No matches found, bro. Try different keywords.");
        }

        StringBuilder sb = new StringBuilder("Here’s what I found:\n");
        for (int idx : hits) {
            sb.append(idx + 1).append(". ").append(tasks.get(idx)).append("\n");
        }
        return sb;
    }

    private static String normalize(String s) {
        return s == null ? "" : s.toLowerCase().trim();
    }

    private static List<String> extractQueryTerms(String raw) {
        String q = normalize(raw);
        List<String> terms = new ArrayList<>();
        int i = 0;
        while (i < q.length()) {
            char c = q.charAt(i);
            if (Character.isWhitespace(c)) { i++; continue; }
            if (c == '"') {
                int j = q.indexOf('"', i + 1);
                if (j > i + 1) {
                    terms.add(q.substring(i + 1, j).trim());
                    i = j + 1;
                    continue;
                }
            }
            int j = i + 1;
            while (j < q.length() && !Character.isWhitespace(q.charAt(j))) j++;
            terms.add(q.substring(i, j));
            i = j + 1;
        }
        // Remove empties
        terms.removeIf(t -> t.isEmpty());
        return terms;
    }

    private static boolean tokenMatches(String normHay, String token) {
        if (token.isEmpty()) return true;
        if (normHay.contains(token)) return true; // direct substring across the whole text

        final int MIN_PARTIAL = 3;
        final int MIN_TYPO_LEN = 4;

        // Compare against individual words
        String[] words = normHay.split("[^a-z0-9]+");
        for (String w : words) {
            if (w.isEmpty()) continue;
            // Partial match: token must be reasonably long, and match inside a word
            if (token.length() >= MIN_PARTIAL && w.length() >= MIN_PARTIAL && w.contains(token)) return true;
            // Typo tolerance: both sides reasonably long and within 1 edit
            if (token.length() >= MIN_TYPO_LEN && w.length() >= MIN_TYPO_LEN && isEditDistanceAtMostOne(w, token)) return true;
        }
        return false;
    }

    // Optimized check for Levenshtein distance <= 1
    private static boolean isEditDistanceAtMostOne(String a, String b) {
        int la = a.length(), lb = b.length();
        if (Math.abs(la - lb) > 1) return false;
        int i = 0, j = 0, edits = 0;
        while (i < la && j < lb) {
            if (a.charAt(i) == b.charAt(j)) { i++; j++; continue; }
            if (++edits > 1) return false;
            if (la == lb) { i++; j++; }          // substitution
            else if (la > lb) { i++; }            // deletion in a
            else { j++; }                         // insertion in a
        }
        // Account for trailing char in longer string
        if (i < la || j < lb) edits++;
        return edits <= 1;
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return current size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Determines whether the provided index is outside the bounds of the current list.
     *
     * @param id zero-based index to validate
     * @return {@code true} if {@code id} is negative or not less than {@link #size()}, otherwise {@code false}
     */
    public boolean isInvalidIndex(int id) {
        return id < 0 || id >= tasks.size();
    }

    /**
     * Internal consistency checks that should always hold for a valid TaskList instance.
     * These are enabled only when JVM assertions are turned on.
     */
    private void assertInvariants() {
        assert tasks != null : "tasks list must not be null";
        assert limit > 0 : "limit must be positive";
        assert tasks.size() <= limit : "tasks size must not exceed limit";
    }

}
