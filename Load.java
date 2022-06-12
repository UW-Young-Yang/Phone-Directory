// --== CS400 File Header Information ==--
// Name: Young Yang
// Email: xyang532@wisc.edu
// Team: IC
// Role: Data Wranglers
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

/**
 * This class reads contact information from a txt file. Each line of this line contains an individual's
 * phone number, name, and gender.
 * 
 * @author Hongru Zhou, Young Yang
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Load {

/**
 * the method prints out the name, phone number, and gender of each person in the contact list
 * 
 * @param contact an ArrayList of PhoneDirectoryNodes holding each individual's information
 */
  public static void printContent(ArrayList<PhoneDirectoryNode> contact) {
    for (PhoneDirectoryNode node : contact)
      System.out.println(node.getPhoneNumber() + " " + node.getName() + " " + node.getGender());
  }

  /**
   * the method scans a file and record the information in each line in an ArrayList
   * 
   * @param file the file to load
   */
  public static void loadFile(File file) throws IOException {

    Scanner scnrForFile = new Scanner(file);

    while (scnrForFile.hasNextLine()) {
      String Line = scnrForFile.nextLine();
      String[] tempContactNode = Line.split(",");
      String[] contactNode = {"", "", ""};

      for (int i = 0; i < tempContactNode.length; ++i) {
        contactNode[i] = tempContactNode[i];
      }

      Main.add(contactNode);
    }

    scnrForFile.close();

  }

}
