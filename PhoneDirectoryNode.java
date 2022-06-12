// --== CS400 File Header Information ==--
// Name: Young Yang
// Email: xyang532@wisc.edu
// Team: IC
// Role: Data Wranglers
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

/**
 * This class models a Phone Directory Node
 * 
 * @author Weiyu Kong
 */
public class PhoneDirectoryNode {
  private String phoneNumber;
  private String name;
  private String gender;
  public final static String[] GENDER_TYPES = {"Male", "Female", "N/A"};

  /**
   * Create a node without gender type
   * 
   * @param phoneNumber the number of this node
   * @param name        the name of this node
   */
  public PhoneDirectoryNode(String phoneNumber, String name) {
    this.phoneNumber = phoneNumber;
    this.name = name;
    this.gender = GENDER_TYPES[2];
  }

  /**
   * Create a node with gender type
   * 
   * @param phoneNumber the number of this node
   * @param name        the name of this node
   * @param gender      the gender type of this node
   */
  public PhoneDirectoryNode(String phoneNumber, String name, String gender) {
    this.phoneNumber = phoneNumber;
    this.name = name;
    setGender(gender);
  }

  /**
   * Return the name of this node
   * 
   * @return the name of this node
   */
  public String getName() {
    return this.name;
  }

  /**
   * Return the phone number of this node
   * 
   * @return the phone number of this node
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * Return the gender type of this node
   * 
   * @return the gender type of this node
   */
  public String getGender() {
    return this.gender;
  }

  /**
   * Change name of this node
   * 
   * @param name the new name of this node
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Change phone number of this node
   * 
   * @param number the new phone number of this node
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Change gender type of this node
   * 
   * @param gender the new gender type of this node
   */
  public void setGender(String gender) {
    if (gender.trim().toLowerCase().equals("male")) {
      this.gender = GENDER_TYPES[0];
    } else if (gender.trim().toLowerCase().equals("female")) {
      this.gender = GENDER_TYPES[1];
    } else {
      this.gender = GENDER_TYPES[2];
    }
  }
}
