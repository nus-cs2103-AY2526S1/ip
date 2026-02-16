package chatbot.client;

/**
 * Client represents a client object with a name, mobile number and the date a user last contacted the client.
 * A Client has a String name, String mobileNumber and String lastContacted.
 */
public class Client {
    private final String name;
    private String mobileNumber;
    private String lastContactedDate;

    /**
     * Constructor for Client class.
     *
     * @param name String name of the Client.
     * @param mobileNumber String mobile number of the Client.
     * @param lastContactedDate String date of last contact with Client.
     */
    public Client(String name, String mobileNumber, String lastContactedDate) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.lastContactedDate = lastContactedDate;
    }

    /**
     * Returns a String of the new last contacted date.
     *
     * @param newLastContactedDate New date of last contact with Client.
     * @return True if update was successful.
     */
    public boolean updateLastContactedDate(String newLastContactedDate) {
        this.lastContactedDate = newLastContactedDate;
        return true;
    }

    /**
     * Returns a String of the new last contacted date.
     *
     * @param newMobileNumber New date of last contact with Client.
     * @return True if update was successful.
     */
    public boolean updateMobileNumber(String newMobileNumber) {
        this.mobileNumber = newMobileNumber;
        return true;
    }

    private String getName() {
        return this.name;
    }

    private String getMobileNumber() {
        return this.mobileNumber;
    }

    private String getLastContactedDate() {
        return this.lastContactedDate;
    }

    /**
     * Return a formatted String of Client information to be saved in ClientStorage
     * @return Client information
     */
    public String toSaveFormat() {
        return getName() + " | " + getMobileNumber() + " | " + getLastContactedDate();
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n"
                + "Mobile Number: " + getMobileNumber() + "\n"
                + "Last Contacted: " + getLastContactedDate();
    }
}
