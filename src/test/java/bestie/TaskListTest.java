package bestie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class TaskListTest {
    @Test
    void find_keywordMatchesTasksInInsertionOrder() {
        TaskList taskList = new TaskList();
        Task readBook = new Todo("read book");
        Task returnBook = new Deadline("return book", "2023-06-06 1800");
        Task picnic = new Event("family picnic", "2023-06-07 0900", "2023-06-07 1200");
        taskList.add(readBook);
        taskList.add(returnBook);
        taskList.add(picnic);

        List<Task> matches = taskList.find("book");

        assertEquals(List.of(readBook, returnBook), matches);
    }

    @Test
    void find_isCaseInsensitive() {
        TaskList taskList = new TaskList();
        Task writeEssay = new Todo("Write Essay");
        taskList.add(writeEssay);

        List<Task> matches = taskList.find("essay");
        List<Task> upperMatches = taskList.find("ESSAY");

        assertEquals(matches, upperMatches);
        assertEquals(List.of(writeEssay), matches);
    }

    @Test
    void find_matchesTagsIgnoringHash() {
        TaskList taskList = new TaskList();
        Task picnic = new Todo("family picnic");
        picnic.addTags(List.of("#Fun", "outdoors"));
        taskList.add(picnic);

        assertEquals(List.of(picnic), taskList.find("#fun"));
        assertEquals(List.of(picnic), taskList.find("OUTDOORS"));
    }
}