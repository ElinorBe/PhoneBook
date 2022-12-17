package JavaTest2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class PhoneBook {
    ArrayList<Contact> PhoneBook;
    ArrayList<Contact> temp;
    boolean flag;

    PhoneBook() {
        PhoneBook = new ArrayList<>();
    }
    public void AddContact(Contact c) {
        PhoneBook.add(c);
        System.out.println("Contact added successfully!\n\n\n");
        temp = PhoneBook; /** temp gets' phonebook for future changes - we don't want that the changes will be on the original arrayList (PhoneBook) */

    }

    /** function gets a name from the user -
    Into the loop I'm checking if the name is equal to contact name - if it is, the contact will be removed and a message will be shown. */
    public void DeleteContactByName(String name) {
        flag = false; /** The boolean variable is to know if there was at least 1 user that removed. If so, then the flag gets true, if not the flag stay false and that's how I know that no contact has been removed. */
        if (PhoneBook.size() > 0) {
            for (int i = 0; i < PhoneBook.size(); i++)
                if ((PhoneBook.get(i).getContactName().equals(name))) {
                    PhoneBook.remove(i);
                    flag = true;
                    break;
                }
            if (!flag) System.out.println("Contact doesn't exist!");
            else {
                System.out.println("Contact deleted successfully");
                temp = PhoneBook;
            }
        } else System.out.println("PhoneBook is empty!");
    }

    public void PrintPhoneBook() { /** Print PhoneBook */
        if (PhoneBook.size() > 0) {
            System.out.println("PhoneBook:");
            for (Contact c : PhoneBook) {
                System.out.print(c);
            }
        } else System.out.println("PhoneBook is empty!!");
    }

    /** function gets a name from the user -
    Into the loop I'm checking if the name is equal to contact name - if it is, the contact will be printed. */
    public void SearchContactByName(String name) { /**/
        flag = false; /** The boolean variable is to know if at least 1 user has found. If so, then the flag gets true, if not the flag stay false and that's how I know that no contact has been found. */
        if (PhoneBook.size() > 0) {
            for (Contact c : PhoneBook)
                if (c.getContactName().equals(name)) {
                    System.out.println(c);
                    flag = true;
                }
            if (!flag) System.out.println("The name '" + name + "' doesn't exist in the phonebook.");
        } else System.out.println("PhoneBook is empty!!");
    }

    /** Function sorting Phonebook by ascending order (from small to large), by name, using "Collections.sort".
     (Operations are done on temp variable in order to not change the original phonebook)

    "Sort" Function gets the phonebook to be sorted, and c1 & c2 are the comparators to determine the order of the list.
     The comparators are being compared each one to the next one. */
    public void SortPhonebookAscending() {
        if (temp.size() > 0) {
            System.out.println(temp);
            Collections.sort(temp, (c1, c2) -> c1.getContactName().compareTo(c2.getContactName()));
            System.out.print(temp);
            temp = PhoneBook;
        } else System.out.println("PhoneBook is empty!!");
    }

    /** Function sorting Phonebook by descending order (from large to small), by name, using "Collections.sort" + "Collections.reverseOrder" .
     (Operations are done on temp variable in order to not change the original phonebook)

     After sorting the Phonebook I used "reverseOrder" to change the order from ascending to descending */
    public void SortPhonebookDescending() {
        if (temp.size() > 0) {
            System.out.println(temp);
            Collections.sort(temp, Collections.reverseOrder((c1, c2) -> c1.getContactName().compareTo(c2.getContactName())));
            System.out.println(temp);
            temp = PhoneBook;
        } else System.out.println("PhoneBook is empty!!");
    }

    /** Function organize phonebook by reverse order - from the end to the beginning.
    Operations are done on temp variable in order to not change the original phonebook */
    public void organizeReverse() {
        if (temp.size() > 0) {
            System.out.println(temp);
            Collections.reverse(temp);
            System.out.println(temp);
            temp = PhoneBook;
        } else System.out.println("PhoneBook is empty!!");
    }

    /** Function checking if there are duplicates contact - if so they will be removed.

     * In the next 2D loop the examination start with the current contact (i) compare to the next contact (j).
     * The first loop (i) is stay with the same value until the second loop (j) gets to the end of arrayList (j < PhoneBook.size()).
     * For each step j is growing by 1 and the comparison is being on the next contact.
     * When j gets to the end of arrayList, the first loop (i) is growing by one and j start from the beginning again - one by one. */
    public void DeleteDuplicateContact() {
        if (PhoneBook.size() > 0) {
            System.out.println("Phonebook before delete duplicate contact:\n" + PhoneBook);
            for (int i = 0; i < PhoneBook.size(); i++)
                for (int j = i + 1; j < PhoneBook.size(); j++)
                    if (PhoneBook.get(i).getContactName().equals(PhoneBook.get(j).getContactName()) && PhoneBook.get(i).getContactPhoneNumber().equals(PhoneBook.get(j).getContactPhoneNumber())) {
                        System.out.println(PhoneBook.get(i).getContactName() + PhoneBook.get(i).getContactPhoneNumber());
                        PhoneBook.remove(j);
                        j--; /** When a contact is being removed the arrayList is getting smaller by one, so it is necessary to decrement j by one so there is no skipping of one contact. */
                    }
            System.out.println(PhoneBook);
            temp = PhoneBook;
        } else System.out.println("PhoneBook is empty!!");
    }

    /** In this function I create new file with the above name, and at the same time I check if the file is already exists.
     * Then, a loop will go through each line from the phonebook and write it to the file. */
    public void saveToTxtFile(){
        if (PhoneBook.size() > 0) {
            File myPhoneBook = new File("PhoneBook.txt"); /*New file named "PhoneBook.txt" */
            try {
                if (myPhoneBook.createNewFile()) System.out.println("File created: " + myPhoneBook.getName());
                else System.out.println("File already exists. Data will add to the existing file.");
                FileWriter wr = new FileWriter("PhoneBook.txt");
                for (Contact c : PhoneBook)
                    wr.write(c.getContactName() + "\t" + c.getContactPhoneNumber() + "\n");
                System.out.println("Data added successfully");
                wr.close();
            } catch (IOException e) {
                 System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else System.out.println("PhoneBook is empty!!");
    }


    /** explanation on while loop:
     * "bufReader.readLine()" is reading the information line to "strCurrentLine" until it get to the end of line (null).

     * Into the loop I created a new contact, and I send to it the contact name (before "\t")  and the contact phone number (after "\t")
     using split method. split method is divide the string to an array with 2 places,
     which in this case the first place contain the information before char "\t" ,
     and the second place contain the information after char "\t". */
    public void LoadFileToPhoneBook(String path) {
        boolean flag = false;
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(path)); // Path of file.
            String strCurrentLine;
            while ((strCurrentLine = bufReader.readLine()) != null) {
                Contact c = new Contact(strCurrentLine.split("\t")[0], strCurrentLine.split("\t")[1]);
                PhoneBook.add(c);
                flag = true;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        if (flag) System.out.println("Phonebook loaded successfully!");
    }
}

