package meo;

import meo.data.TextList;

public class Sorter {
    public void sortAscByName(TextList taskList) {
        taskList.sortList(1);
    }

    public void sortDescByName(TextList taskList) {
        taskList.sortList(-1);
    }
}
