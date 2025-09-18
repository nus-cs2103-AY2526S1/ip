package alune;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AluneTest {
    @Test
    public void aluneTest1() {
        String todoInput = "clear\ntodo feed plant\ndeadline water cat /by 25/08/2025 1111\nlist\nbye";
        ByteArrayInputStream in = new ByteArrayInputStream(todoInput.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Launcher.main(new String[] {});

        String actual = out.toString().trim();
        String expected = "__                       \n"
                + "_____  |  |  __ __  ____   ____  \n"
                + "\\__  \\ |  | |  |  \\/    \\_/ __ \\ \n"
                + " / __ \\|  |_|  |  /   |  \\  ___/ \n"
                + "(____  /____/____/|___|  /\\___  >\n"
                + "     \\/                \\/     \\/ \n" +
                "\n\nhi i'm alune~ what can i do for you? (⸝⸝ᵕᴗᵕ⸝⸝)" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\ncleared 2 tasks from the list! (￣^￣ )ゞ" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nadded: feed plant" +
                "\nyou have 1 task(s) now. („• ֊ •„)੭" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nadded: water cat" +
                "\nyou have 2 task(s) now. („• ֊ •„)੭" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nyour tasks: ᕙ( •̀ ᗜ •́ )ᕗ\n" +
                "1. [T][ ] feed plant\n" +
                "2. [D][ ] water cat\n" +
                "          (by: Aug 25, 2025, 11:11 AM)" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nbye, see you again~ ꉂ(˵˃ ᗜ ˂˵)" +
                "\n\n\n" +
                "«────────── « ⋅ʚ♡ɞ⋅ » ──────────»";

        assertEquals(Arrays.stream(expected.split("\n")).map(String::trim).collect(Collectors.joining("\n")),
                Arrays.stream(actual.split("\n")).map(String::trim).collect(Collectors.joining("\n")));
    }

    @Test
    public void aluneTest2() {
        String todoInput = "clear\ntodo make breakfast\nevent work /from nine /to 5pm\nevent work /from 25/08/2025 0900 /to 25/08/2025 1700\nlist\nmark 1\nlist\nbye";
        ByteArrayInputStream in = new ByteArrayInputStream(todoInput.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String actual = out.toString().trim();
        String expected = "__                       \n"
                + "_____  |  |  __ __  ____   ____  \n"
                + "\\__  \\ |  | |  |  \\/    \\_/ __ \\ \n"
                + " / __ \\|  |_|  |  /   |  \\  ___/ \n"
                + "(____  /____/____/|___|  /\\___  >\n"
                + "     \\/                \\/     \\/ \n" +
                "\n\nhi i'm alune~ what can i do for you? (⸝⸝ᵕᴗᵕ⸝⸝)" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\ncleared 2 tasks from the list! (￣^￣ )ゞ" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nadded: make breakfast" +
                "\nyou have 1 task(s) now. („• ֊ •„)੭" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nplease use dd/mm/yyyy hhmm format. (,,>﹏<,,)" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nadded: work" +
                "\nyou have 2 task(s) now. („• ֊ •„)੭" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nyour tasks: ᕙ( •̀ ᗜ •́ )ᕗ\n" +
                "1. [T][ ] make breakfast\n" +
                "2. [E][ ] work\n" +
                "          (from: Aug 25, 2025, 9:00 AM;\n" +
                "          to: Aug 25, 2025, 5:00 PM)" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nmarked as done. nice! (˵ •̀ ᴗ - ˵ )\n" +
                "[T][X] make breakfast" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nyour tasks: ᕙ( •̀ ᗜ •́ )ᕗ\n" +
                "1. [T][X] make breakfast\n" +
                "2. [E][ ] work\n" +
                "          (from: Aug 25, 2025, 9:00 AM;\n" +
                "          to: Aug 25, 2025, 5:00 PM)" +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nenter: " +
                "\n\n\n«────────── « ⋅ʚ♡ɞ⋅ » ──────────»\n" +
                "\n\nbye, see you again~ ꉂ(˵˃ ᗜ ˂˵)" +
                "\n\n\n" +
                "«────────── « ⋅ʚ♡ɞ⋅ » ──────────»";

        assertEquals(Arrays.stream(expected.split("\n")).map(String::trim).collect(Collectors.joining("\n")),
                Arrays.stream(actual.split("\n")).map(String::trim).collect(Collectors.joining("\n")));
    }
}
