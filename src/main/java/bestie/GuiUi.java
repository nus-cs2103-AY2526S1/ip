package bestie;

/**
 * Collects UI messages for display inside the GUI instead of printing them to
 * the console.
 */
class GuiUi extends Ui {
    private final StringBuilder buffer = new StringBuilder();

    @Override
    protected void write(String... messages) {
        for (String message : messages) {
            buffer.append(message).append(System.lineSeparator());
        }
    }

    /**
     * Returns the accumulated messages produced since creation.
     *
     * @return formatted output ready for rendering in the GUI
     */
    public String flush() {
        return buffer.toString().stripTrailing();
    }
}
