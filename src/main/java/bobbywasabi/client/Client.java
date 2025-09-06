package bobbywasabi.client;

public class Client {
    private String name;
    private String contactNumber;
    private int age;
    private String occupation;
    private String currentPolicies;

    public Client(String name, String contactNumber, int age,
                       String occupation, String currentPolicies) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.age = age;
        this.currentPolicies = currentPolicies;
        this.occupation = occupation;
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

    public void setCurrentPolicies(String CurrentPolicies) {
        this.currentPolicies = currentPolicies;
    }

    public String getData() {
        return String.format("%s|%s|%d|%s|%s",
                this.name, this.contactNumber,
                this.age, this.occupation, this.currentPolicies);
    }

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
