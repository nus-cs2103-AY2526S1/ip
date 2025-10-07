package jarvis.tasks;

public class PlaceTask extends Task {
    private final String place;

    public PlaceTask(String desc, String place) {
        super(desc);
        assert place != null && !place.isBlank() : "place must not be blank";
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return "[P]" + super.toString() + " (at: " + place + ")";
    }

    @Override
    public String getTypeIcon() {
        return "P";
    }

    @Override
    public String toStorageString() {
        // Format: P | done | desc | place
        return String.format("P | %d | %s | %s",
                isDone() ? 1 : 0,
                description,
                place);
    }
}
