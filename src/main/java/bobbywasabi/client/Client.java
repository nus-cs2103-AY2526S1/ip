package bobbywasabi.client;

/**
 * Represents a client with basic personal information and insurance policy details.
 * <p>
 * Stores the client's name, contact number, age, occupation, and current policies.
 * Provides methods to format client data for storage or display purposes.
 */
public class Client {
    private String name;
    private String contactNumber;
    private int age;
    private String occupation;
    private String currentPolicies;

    /**
     * Constructs a new Client object with the specified details.
     *
     * @param name            The client's name (must not be empty or blank).
     * @param contactNumber   The client's contact number.
     * @param age             The client's age.
     * @param occupation      The client's occupation (must not be empty or blank).
     * @param currentPolicies The client's current policies (can be empty).
     * @throws AssertionError if name or occupation is empty.
     */
    public Client(String name, String contactNumber, int age,
                       String occupation, String currentPolicies) {
        assert !name.trim().isEmpty()
                : "Name given to client is empty!";
        assert !occupation.trim().isEmpty()
                : "Occupation given to client is empty!";

        this.name = name;
        this.contactNumber = contactNumber;
        this.age = age;
        this.occupation = occupation;
        this.currentPolicies = currentPolicies;
    }

    public String getName() {
        return this.name;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public int getAge() {
        return this.age;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public String getCurrentPolicies() {
        return this.currentPolicies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setCurrentPolicies(String currentPolicies) {
        this.currentPolicies = currentPolicies;
    }

    /**
     * Returns a formatted string of the client's data suitable for storage or export.
     * <p>
     * Fields are separated by "|" and follow the order:
     * name, contactNumber, age, occupation, currentPolicies.
     *
     * @return Formatted client data string.
     */
    public String getData() {
        return String.format("%s|%s|%d|%s|%s",
                this.name, this.contactNumber,
                this.age, this.occupation, this.currentPolicies);
    }

    /**
     * Returns a human-readable string representation of the client.
     * <p>
     * Includes name, contact number, age, occupation, and, if present,
     * current policies.
     *
     * @return Formatted client information string.
     */
    @Override
    public String toString() {
        String output = String.format("""
                Name: %s
                Contact Number: %s
                Age: %d
                Occupation: %s
                """,
                this.name, this.contactNumber,
                this.age, this.occupation);
        output += this.currentPolicies.trim().isEmpty()
                ? ""
                : "Current Policies: " + this.currentPolicies;
        return output;
    }

}
