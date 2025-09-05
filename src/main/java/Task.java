public abstract class Task {
    private boolean completed = false;
    private final String title;

    public Task(String title) {
        this.title = title;
    }

    public void markAsComplete() {
        this.completed = true;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }
    @Override
    public String toString() {
        return String.format("[" + this.convert() + "] " + title);
    }

    public abstract String serialise();

    private String convert() {
        return completed ? "X" : " ";
    }

    protected String baseSerialize(String type, String... extras) {
        StringBuilder sb = new StringBuilder();
        sb.append(type)
                .append(" | ")
                .append(completed ? "1" : "0")
                .append(" | ")
                .append(title);

        for (String extra : extras) {
            sb.append(" | ").append(extra == null ? "" : extra);
        }
        return sb.toString();
    }
}
