// --== CS400 File Header Information ==--
// Name: Young Yang
// Email: xyang532@wisc.edu
// Team: IC
// Role: Data Wranglers
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The running application of phone directory
 * 
 * @author Aoron Chen, Weiyu Kong
 *
 */
public class Main {
  private static Scanner scnr = new Scanner(System.in);
  public static HashTableMap<String, PhoneDirectoryNode> store = new HashTableMap<>();

  /**
   * Add a contact into the phone directory
   * 
   * @param node the information of the contact
   */
  public static void add(String[] node) {
    String tempPhoneNumber = node[0].replaceAll("\\s+", "");
    String tempName = node[1].replaceAll("\\s+", "");

    if (!(tempName.matches("[a-zA-Z ]+") && tempPhoneNumber.matches("[0-9]+"))) {
      throw new IllegalArgumentException("Illegal data type, illegal length");
    }
    PhoneDirectoryNode list = new PhoneDirectoryNode(node[0], node[1], node[2]);
    store.put(tempPhoneNumber, list);
  }

  /**
   * Add a file with contacts information
   * 
   * @param fileName the name of the specific file
   */
  public static void addFile(String fileName) throws IOException {
    java.io.File file = new java.io.File(fileName);

    Load.loadFile(file);
  }

  /**
   * Remove the contact represented by a phone number
   * 
   * @param phoneNumber a contact's phone number
   */
  public static void remove(String phoneNumber) {
    if (!store.containsKey(phoneNumber)) {
      throw new NoSuchElementException("This contact number does not exist.");
    }
    store.remove(phoneNumber);
  }

  /**
   * Search the contact represented by a phone number
   * 
   * @param phoneNumber a contact's phone number
   * @return return the name and gender of the contact represented by a phone number
   */
  public static String[] search(String phoneNumber) {
    if (!store.containsKey(phoneNumber)) {
      throw new NoSuchElementException("This contact number does not exist.");
    }
    PhoneDirectoryNode Temp = store.get(phoneNumber);
    String[] searchResult = {Temp.getName(), Temp.getGender()};
    return searchResult;
  }

  /**
   * Clear the phone directory
   * 
   */
  public static void clear() {
    store.clear();
  }

  /**
   * Edit the contact represented by a phone number
   * 
   * @param phoneNumber a contact's phone number
   */
  public static void edit(String phoneNumber) {
    if (!store.containsKey(phoneNumber)) {
      throw new NoSuchElementException("This contact number does not exist.");
    }
    store.remove(phoneNumber);
    System.out.print("Please type the name: ");
    scnr.nextLine();
    String name = scnr.nextLine();
    System.out.println();
    System.out.print("Please type the gender: ");
    String gender = scnr.next();
    System.out.println();
    String[] contactNode = {phoneNumber, name, gender};
    add(contactNode);
  }

  /**
   * Run the phone directory app
   * 
   * @param args user input
   */
  public static void main(String[] args) throws IOException {
    String y = "";

    do {
      System.out.println("Please type add, remove, search, edit, clear or quit to end.");
      y = scnr.next();

      // add method------------------------------------------------

      if (y.trim().toLowerCase().equals("add")) {
        int x = 1;
        System.out.println("Manually, please type manual. By text file, please type text");
        String option = scnr.next();
        if (option.equals("manual")) {
          while (x == 1) {
            System.out.print("Please type the phone number: ");
            String phoneNumber = scnr.next();
            System.out.println();
            System.out.print("Please type the Name: ");
            scnr.nextLine();
            String name = scnr.nextLine();
            System.out.println();
            System.out.print("Please type the Gender: ");
            String gender = scnr.next();
            System.out.println();
            String[] contactNode = {phoneNumber, name, gender};
            add(contactNode);
            System.out.println("Type 0 when you are finish or type 1 to continue.");
            x = scnr.nextInt();
          }
        } else {
          System.out.println("Please enter the file location.");
          String fileLocation = scnr.next();
          addFile(fileLocation);
        }


        // remove method------------------------------------------------

      } else if (y.trim().toLowerCase().equals("remove")) {
        System.out.print("Please the phone number that you want to remove: ");
        String phoneNumber = scnr.next();
        remove(phoneNumber);

        // search method------------------------------------------------

      } else if (y.trim().toLowerCase().equals("search")) {
        System.out.print("Please type the phone number that you want to search: ");
        String phoneNumber = scnr.next();
        String[] searchResults = search(phoneNumber);
        System.out.println("Name: " + searchResults[0]);
        System.out.println("Gender: " + searchResults[1]);

        // clear method------------------------------------------------

      } else if (y.trim().toLowerCase().equals("clear")) {
        clear();
        System.out.print("You have cleared the list.");

        // edit method------------------------------------------------

      } else if (y.trim().toLowerCase().equals("edit")) {
        System.out.print("Please type the phone number that you want to edit: ");
        String phoneNumber = scnr.next();
        edit(phoneNumber);
      }
    } while (!(y.trim().toLowerCase().equals("quit")));
  }
}
