package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.Contact;

public class ContactBuilder {
    String name;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    public HashMap<String, String> params = new HashMap<>();
    
    public static final String DEFAULT_NAME = "David Dave";
    public static final String DEFAULT_FIRST_NAME = "David";
    public static final String DEFAULT_LAST_NAME = "Dave";
    public static final String DEFAULT_EMAIL = "dave@gmail.com";
    public static final String DEFAULT_PHONE = "12345678";
    public static final String DEFAULT_ADDRESS = "21 Lower Kent Ridge Rd, Singapore 119077";

    public ContactBuilder() {
        this.name = DEFAULT_NAME;
        this.firstName = DEFAULT_FIRST_NAME;
        this.lastName = DEFAULT_LAST_NAME;
        this.email = DEFAULT_EMAIL;
        this.phone = DEFAULT_PHONE;
        this.address = DEFAULT_ADDRESS;

        this.params = new HashMap<>();
        params.put("name", name);
        params.put("firstname", firstName);
        params.put("lastname", lastName);
        params.put("email", email);
        params.put("phone", phone);
        params.put("address", address);
    }
    
    public void withName(String name) {
        this.name = name;
        params.put("name", name);
    }
    
    public void withFirstName(String firstName) {
        this.firstName = firstName;
        params.put("firstname", firstName);
    }
    
    public void withLastName(String lastName) {
        this.lastName = lastName;
        params.put("lastname", lastName);
    }
    
    public void withEmail(String email) {
        this.email = email;
        params.put("email", email);
    }
    
    public void withPhone(String phone) {
        this.phone = phone;
        params.put("phone", phone);
    }
    
    public void withAddress(String address) {
        this.address = address;
        params.put("address", address);
    }
    
    public Contact build() throws ApunableException {
        return new Contact(this.params);
    }
}
