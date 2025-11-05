package sunoo.command;

import sunoo.task.TaskList;
import sunoo.ui.Ui;

/**
 * Represents an executable command to respond to user according to the title track entered.
 */
public class EnhypenCommand extends Command {
    private final String titleTrack;

    /**
     * Creates a new EnhypenCommand with a title track string.
     *
     * @param titleTrack Title track entered by user.
     */
    public EnhypenCommand(String titleTrack) {
        this.titleTrack = titleTrack;
    }

    /**
     * {@inheritDoc}
     * <p>Shows a specific message corresponding to the title track.</p>
     */
    @Override
    public String execute(TaskList tasks) {
        String result = switch (titleTrack) {
        case "given-taken", "given taken" -> "Red blood 저 왕관에\nThat blood 흐르는 피";
        case "drunk-dazed", "drunk dazed" -> "Can’t control my body dance dance dance";
        case "tamed-dashed", "tamed dashed" -> "Like hot summer (na, na, na)";
        case "blessed-cursed", "blessed cursed" -> "Just stop, don't tell me what to do";
        case "future perfect", "future perfect (pass the mic)" -> "\"Walk the line\" (ayy), I hate that line (ayy)";
        case "bite me" -> "This blood's pumpin' crazy";
        case "sweet venom" -> "Dying in your arms\nFeeling like on a cloud";
        case "xo", "xo (only if you say yes)" -> "Can I kiss you? Can I hug you?";
        case "no doubt" -> "No worries, 난 괜찮아";
        case "bad desire", "bad desire (with or without you)" -> "Tell me all your deepest\nAll your bad desires";
        default -> "ENGENE, you should listen to us more!";
        };
        return Ui.wrapWithHorizontalLines(result);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
