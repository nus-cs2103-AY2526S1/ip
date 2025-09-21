package hachiware;

public enum Priority {
    HIGH,
    MEDIUM,
    LOW,
    UNTAGGED;

    @Override
    public String toString() {
        return name();
    }
}
