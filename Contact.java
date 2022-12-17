package JavaTest2;

import java.util.Scanner;

public class Contact {
    String contactName;
    String contactPhoneNumber;

    Contact(String contactName, String contactPhoneNumber) {
        this.contactName = contactName;
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public void setContactName(String name) {
        this.contactName = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactPhoneNumber(String phone) {
        this.contactPhoneNumber = phone;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public String toString() {
        return "[Contact name: '" + this.contactName + "', " + "Contact phone number: '" + this.getContactPhoneNumber() +"']\n";
    }

    public void equals() {

    }
}
