package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exceptions.ApunableException;
import utils.DateTimeUtil;

/**
 * Represents a contact with personal and organizational details, including
 * phone numbers, emails, and other metadata like nickname or birthday.
 */
public class Contact {
    private String name;
    private String firstName;
    private String lastName;
    private ArrayList<Phone> phoneNumbers;
    private String address;
    private String organization;
    private ArrayList<String> emails;
    private String notes;
    private String nickName;
    private LocalDate birthday;

    /**
     * Constructs a {@code Contact} with basic information.
     *
     * @param phone The phone number of the contact.
     * @param name The full name of the contact.
     * @param first The first name of the contact.
     * @param last The last name of the contact.
     * @param addr The address of the contact.
     */
    public Contact(String phone, String name, String first, String last, String addr) {
        phoneNumbers = new ArrayList<>(List.of(new Phone(phone)));
        this.name = name;
        firstName = first;
        lastName = last;
        address = addr;
        organization = "";
        emails = new ArrayList<>();
        notes = "";
        nickName = "";
        birthday = null;
    }

    /**
     * Constructs a {@code Contact} from a map of details.
     *
     * @param details The map containing contact details (keys like name, phone, email, etc.).
     * @throws ApunableException if any email format is invalid.
     */
    public Contact(HashMap<String, String> details) throws ApunableException {
        name = details.getOrDefault("name", "");
        firstName = details.getOrDefault("firstname", "");
        lastName = details.getOrDefault("lastname", "");

        if (name.isEmpty()) {
            name = firstName + " " + lastName;
        }

        String[] phoneNumStrs = details.getOrDefault("phone", "").split(";");
        phoneNumbers = new ArrayList<>();
        for (String phoneNumStr : phoneNumStrs) {
            if (!phoneNumStr.isEmpty()) {
                phoneNumbers.add(new Phone(phoneNumStr));
            }
        }

        address = details.getOrDefault("address", "");
        organization = details.getOrDefault("organization", "");

        String[] emailStrs = details.getOrDefault("email", "").split(";");
        emails = new ArrayList<>();
        for (String emailStr : emailStrs) {
            if (emailStr.isEmpty()) continue;
            if (!emailStr.contains("@")) {
                throw new ApunableException("Invalid email format: " + emailStr);
            }
            emails.add(emailStr);
        }

        notes = details.getOrDefault("notes", "");
        nickName = details.getOrDefault("nickname", "");
        String birthdayStr = details.getOrDefault("birthday", "");

        birthday = birthdayStr.isEmpty() ? null : DateTimeUtil.parseDate(birthdayStr);
    }

    /**
     * Copy constructor.
     *
     * @param otherContact The contact to copy from.
     */
    public Contact(Contact otherContact) {
        name = otherContact.name;
        firstName = otherContact.firstName;
        lastName = otherContact.lastName;
        phoneNumbers = new ArrayList<>(otherContact.phoneNumbers);
        address = otherContact.address;
        organization = otherContact.organization;
        emails = new ArrayList<>(otherContact.emails);
        notes = otherContact.notes;
        nickName = otherContact.nickName;
        birthday = otherContact.birthday;
    }

    /**
     * Returns the headers for a contact in tabular format.
     *
     * @return An array of column names.
     */
    public static String[] getHeader() {
        return new String[]{
                "name", "firstname", "lastname", "phone", "address",
                "organization", "email", "notes", "nickname", "birthday"
        };
    }

    /**
     * Returns a formatted string representation of this contact.
     *
     * @return A formatted string.
     */
    public String getFormattedString() {
        String phoneStr = String.join(";", phoneNumbers.stream().map(Phone::toString).toList());
        String emailStr = String.join(";", emails);
        String bdayStr = birthday != null ? birthday.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "";
//        return String.join("\t| ",
//                new String[]{name, firstName, lastName, phoneStr, address,
//                        organization, emailStr, notes, nickName, bdayStr});

        return String.format("name: %s\n", name)
                + String.format("firstname: %s\n", firstName)
                + String.format("lastname: %s\n", lastName)
                + String.format("organization: %s\n", organization)
                + String.format("phone: %s\n", phoneStr)
                + String.format("email: %s\n", emailStr)
                + String.format("address: %s\n", address)
                + String.format("notes: %s\n", notes)
                + String.format("nickname: %s\n", nickName)
                + String.format("birthday: %s\n", bdayStr);
    }

    // =========================
    // Getters and Setters
    // =========================

    /**
     * @return The full name of the contact.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the contact's full name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The first name of the contact.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the contact's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The last name of the contact.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the contact's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The contact's phone numbers as a semicolon-separated string.
     */
    public String getPhonesStr() {
        return String.join(";", phoneNumbers.stream().map(Phone::toString).toList());
    }

    /**
     * @return The contact's phone number list.
     */
    public List<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * Sets the list of phone numbers.
     */
    public void setPhoneNumbers(List<Phone> phoneNumbers) {
        this.phoneNumbers = new ArrayList<>(phoneNumbers);
    }

    /**
     * @return The contact's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the contact's address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The contact's organization.
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets the contact's organization.
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return The contact's emails as a semicolon-separated string.
     */
    public String getEmailsStr() {
        return String.join(";", emails);
    }

    /**
     * @return The list of email addresses.
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     * Sets the contact's emails.
     */
    public void setEmails(List<String> emails) {
        this.emails = new ArrayList<>(emails);
    }

    /**
     * @return The contact's notes.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the contact's notes.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return The contact's nickname.
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets the contact's nickname.
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return The contact's birthday.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Sets the contact's birthday.
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    // =========================
    // Functional / Utility Methods
    // =========================

    /**
     * Checks whether this contact matches all given criteria.
     *
     * @param criteria A map of attributes to check against.
     * @return true if all specified criteria match.
     */
    public boolean fit(HashMap<String, String> criteria) {
        boolean isFit = true;
        isFit &= name.equals(criteria.getOrDefault("name", name));
        isFit &= firstName.equals(criteria.getOrDefault("firstname", firstName));
        isFit &= lastName.equals(criteria.getOrDefault("lastname", lastName));
        isFit &= (!criteria.containsKey("phone") || phoneNumbers.contains(new Phone(criteria.get("phone"))));
        isFit &= address.equals(criteria.getOrDefault("address", address));
        isFit &= organization.equals(criteria.getOrDefault("organization", organization));
        isFit &= (!criteria.containsKey("email") || emails.contains(criteria.get("email")));
        isFit &= notes.equals(criteria.getOrDefault("notes", notes));
        isFit &= nickName.equals(criteria.getOrDefault("nickname", nickName));
        isFit &= (!criteria.containsKey("birthday") || birthday.format(DateTimeFormatter.ofPattern("dd MMM"))
                .equals(criteria.get("birthday")));
        return isFit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact that)) return false;
        return name.equals(that.name)
                && firstName.equals(that.firstName)
                && lastName.equals(that.lastName)
                && address.equals(that.address)
                && organization.equals(that.organization)
                && emails.equals(that.emails)
                && nickName.equals(that.nickName)
                && (birthday == that.birthday || birthday.equals(that.birthday))
                && notes.equals(that.notes)
                && phoneNumbers.equals(that.phoneNumbers);
    }

    /**
     * @return A concise summary including name, organization, phone, and email.
     */
    public String basicInfo() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append(!organization.isEmpty() ? String.format("(org: %s)\n", organization) : "\n")
                .append("phone: ")
                .append(String.join(", ", phoneNumbers.stream().map(Phone::toString).toList()))
                .append("\nemail: ")
                .append(String.join(", ", emails));
        return builder.toString();
    }

    /**
     * @return A more detailed summary including names and address.
     */
    public String detailedInfo() {
        return basicInfo() + String.format("\nfirstname: %s, lastname: %s\naddress: %s",
                firstName, lastName, address);
    }

    /**
     * @return The full detailed info including notes, nickname, and birthday.
     */
    public String allInfo() {
        String bdayStr = (birthday != null)
                ? birthday.format(DateTimeFormatter.ofPattern("dd MMM"))
                : "N/A";
        return detailedInfo() + String.format("\nnotes: %s\nnickname: %s\nbirthday: %s",
                notes, nickName, bdayStr);
    }
}
