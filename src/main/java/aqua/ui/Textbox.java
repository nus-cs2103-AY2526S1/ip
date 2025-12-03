package aqua.ui;

/**
 * Represents a textbox that can display messages with borders and indentation.
 */
public class Textbox {
    private final String message;
    private final int indentSize = 4;

    public Textbox(String message) {
        this.message = message;
    }

    /**
     * * Prints the message in a textbox format with borders and indentation.
     */
    public void print() {
        String[] lines = message.split("\n");
        int maxWidth = Integer.MIN_VALUE;

        for (int i = 0; i < lines.length; i++) {
            String s = lines[i];
            maxWidth = Math.max(s.length(), maxWidth);
            StringBuilder sb = new StringBuilder(s);
            lines[i] = sb.insert(0, indent(indentSize)).toString();
        }

        printBorder(maxWidth, indentSize);
        System.out.println(String.join("\n", lines));
        printBorder(maxWidth, indentSize);
    }

    private void printBorder(int width, int indentSize) {
        StringBuilder sb = new StringBuilder();

        sb.append(indent(indentSize));
        for (int i = 0; i < width; i++) {
            sb.append("_");
        }

        sb.append("\n");

        System.out.println(sb);
    }

    private String indent(int size) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
