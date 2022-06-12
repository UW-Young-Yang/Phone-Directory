// --== CS400 File Header Information ==--
// Name: Young Yang
// Email: xyang532@wisc.edu
// Team: IC
// Role: Data Wranglers
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * This class tests all fundamental methods in the front end and back end. edit() is not included
 * because it is a combination of remove() and add(). For each method, their performances under
 * normal input and illegal input are tested.
 * 
 * @author Tong Jiao
 *
 */
class TestDirectory {

  /**
   * Tests whether the PhoneDirectoryNode performs as expected. This ensures that basic information
   * storage unit works well.
   */
  @Test
  void testPhoneDirectoryNode() {
    PhoneDirectoryNode testWithoutGender = new PhoneDirectoryNode("test1", "test2");
    PhoneDirectoryNode testWithGender = new PhoneDirectoryNode("test1", "test2", "male");
    PhoneDirectoryNode testIllegalGender = new PhoneDirectoryNode("test1", "test2", "dwed");

    assertEquals(testWithoutGender.getPhoneNumber(), "test1");
    assertEquals(testWithoutGender.getName(), "test2");
    assertEquals(testWithoutGender.getGender(), "N/A");
    assertEquals(testWithGender.getGender(), "Male");
    assertEquals(testWithoutGender.getPhoneNumber(), "test1");
    assertEquals(testIllegalGender.getGender(), "N/A");
  }

  /**
   * Tests whether add() method successfully add the given node into the list.
   */
  @Test
  void testAdd() {
    String[] testNode = {"1234567890", "name", "N/A"};
    Main.add(testNode);
    String expectedName = "name";
    String expectedGender = "N/A";
    String actualName = Main.search("1234567890")[0];
    String actualGender = Main.search("1234567890")[1];
    Main.clear();
    assertTrue(expectedName.equals(actualName));
    assertTrue(expectedGender.equals(actualGender));
  }

  /**
   * Tests whether add() method successfully identify an input is illegal and do nothing to the
   * list.
   */
  @Test
  void testAddIllegalInput() {
    String[] illegalNumberNode = {"cwefq1234", "name", "N/A"};
    try {
      Main.add(illegalNumberNode);
    } catch (IllegalArgumentException e) {
      ;
    }

    try {
      Main.search("cwefq1234");
    } catch (NoSuchElementException e) {
      ;
    }

    String[] illegalNameNode = {"1234567890", "diqu123!", "N/A"};
    try {
      Main.add(illegalNameNode);
      fail("This item should not be added");
    } catch (IllegalArgumentException e) {
      ;
    }

    try {
      Main.search("cwefq1234");
      fail("This item should not be added");
    } catch (NoSuchElementException e) {
      ;
    }
    Main.clear();
  }

  /**
   * Tests whether remove() method successfully removes the specified key-value pair.
   */
  @Test
  void testRemove() {
    String[] testNode1 = {"1234567891", "nameA", "N/A"};
    String[] testNode2 = {"1234567892", "nameB", "Male"};
    String[] testNode3 = {"1234567893", "nameC", "Female"};
    String[] testNode4 = {"1234567894", "nameD", "Male"};

    Main.add(testNode1);
    Main.add(testNode2);
    Main.add(testNode3);
    Main.add(testNode4);

    Main.remove("1234567893");
    try {
      Main.search("1234567893");
      fail("This item should be removed");
    } catch (NoSuchElementException e) {
      ;
    }

    try {
      Main.search("1234567891");
      Main.search("1234567892");
      Main.search("1234567894");
    } catch (NoSuchElementException e) {
      fail("Unexpectedly remove items that should not be removed!");
    }
    Main.clear();
  }

  /**
   * Tests whether remove() identifies an illegal input and do nothing to the list.
   */
  @Test
  void testIllegalRemove() {
    String[] testNode1 = {"1234567891", "nameA", "N/A"};
    String[] testNode2 = {"1234567892", "nameB", "Male"};
    String[] testNode3 = {"1234567893", "nameC", "Female"};
    String[] testNode4 = {"1234567894", "nameD", "Male"};

    Main.add(testNode1);
    Main.add(testNode2);
    Main.add(testNode3);
    Main.add(testNode4);

    try {
      Main.search("1234567895");
      fail("This item does not exist");
    } catch (NoSuchElementException e) {
      ;
    } finally {
      Main.clear();
    }
  }

  /**
   * Tests whether search() successfully give a specified phone number's owner and the owner's
   * gender.
   */
  @Test
  void testSearch() {
    String[] testNode = {"1234567891", "name", "N/A"};
    Main.add(testNode);
    String expectedName = "name";
    String expectedGender = "N/A";
    String actualName = Main.search("1234567891")[0];
    String actualGender = Main.search("1234567891")[1];
    Main.clear();

    assertTrue(expectedName.equals(actualName));
    assertTrue(expectedGender.equals(actualGender));
  }

  /**
   * Tests whether search() successfully throws NoSuchElementException when the phone number is
   * found in the hash table.
   */
  @Test
  void testSearchIllegal() {

    try {
      Main.search("12d2d2891");
      fail("An illegal element should not be found");
    } catch (NoSuchElementException e) {
      ;
    } finally {
      Main.clear();
    }
  }

  /**
   * Tests whether clear() method cleans everything in the hash table.
   */
  @Test
  void testClear() {
    String[] testNode1 = {"1234567891", "nameA", "N/A"};
    String[] testNode2 = {"1234567892", "nameB", "Male"};
    String[] testNode3 = {"1234567893", "nameC", "Female"};
    String[] testNode4 = {"1234567894", "nameD", "Male"};

    Main.add(testNode1);
    Main.add(testNode2);
    Main.add(testNode3);
    Main.add(testNode4);
    Main.clear();

    try {
      Main.search("1234567891");
      fail("This element should be removed");
    } catch (NoSuchElementException e) {
      ; // expected exception
    }

    try {
      Main.search("1234567892");
      fail("This element should be removed");
    } catch (NoSuchElementException e) {
      ; // expected exception
    }

    try {
      Main.search("1234567893");
      fail("This element should be removed");
    } catch (NoSuchElementException e) {
      ; // expected exception
    }

    try {
      Main.search("1234567894");
      fail("This element should be removed");
    } catch (NoSuchElementException e) {
      ; // expected exception
    }

    Main.clear();

  }

  /**
   * Tests whether addFile() successfully find a specified file and extract all the data inside and
   * add them into the hash table.
   */
  @Test
  void testAddFile() {
    String filePath = "contact.txt";
    try {
      Main.addFile(filePath);
      String expectedName1 = "Jared J Seeber";
      String expectedGender1 = "Male";
      String actualName1 = Main.search("6089366467")[0];
      String actualGender1 = Main.search("6089366467")[1];
      assertTrue(expectedName1.equals(actualName1));
      assertTrue(expectedGender1.equals(actualGender1));

      String expectedName2 = "Nancy K Hopkins";
      String expectedGender2 = "N/A";
      String actualName2 = Main.search("6085512037")[0];
      String actualGender2 = Main.search("6085512037")[1];
      assertTrue(expectedName2.equals(actualName2));
      assertTrue(expectedGender2.equals(actualGender2));
    } catch (IOException e) {
      fail("Failed to read from the given file");
    }
  }

}
