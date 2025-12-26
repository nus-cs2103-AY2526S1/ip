package Baozii;

public record Action(ActionType type, Task task, Integer index, String pattern, String tag) {
}
