/***************************************************************************************
 *                         Instructor.java - Copyright rciampa
 * Title: Instructor
 * Abstract: Data structure for a school instructor
 *
 * @author rciampa
 * ID" 7470
 * Date: Sat Mar 7 2015 : 22:38:26
 * File: Instructor.java
 * Requires: ----
 *
 * This application created for the CST338 course at CSU Monterey Bay SP:2015 Semester.
 * This software is not licensed or warranted for any other purpose than the CSUMB
 * scholastic 2015 semester with Dr. Byun.
 *
 * Heading.java
 * /home/rciampa/Documents/Umbrello/headings/heading.java
 *
 ***************************************************************************************/



/**
 * Class Instructor
 */
public class Instructor {

  //
  // Fields
  //

  /**
   * The school ID of the instructor
   */
  private int id;
  /**
   * The instructors name
   */
  private String name;
  /**
   * Instructors email address
   */
  private String email;
  /**
   * Instructors phone number
   */
  private String phone;
  
  //
  // Constructors
  //
  public Instructor () {
	  setName("NEW INSTRUCTOR");
	  setId(0);
	  setEmail("");
	  setPhone("");
  }
  
  /**
   * Creates a configured instructor object
   * 
   * @param id The school ID of the instructor
   * @param name The name of the instructor
   * @param email The school email address
   * @param phone Phone number of instructor
   */
  public Instructor(int id, String name, String email, String phone){
	  setId(id);
	  setName(name);
	  setEmail(email);
	  setPhone(phone);
  }
  
  //
  // Accessor methods
  //

  /**
   * Set the value of id
   * The school ID of the instructor
   * @param newVar the new value of id
   */
  private void setId ( int newVar ) {
    id = newVar;
  }

  /**
   * Get the value of id
   * The school ID of the instructor
   * @return the value of id
   */
  private int getId ( ) {
    return id;
  }

  /**
   * Set the value of name
   * The instructors name
   * @param newVar the new value of name
   */
  private void setName ( String newVar ) {
    name = newVar;
  }

  /**
   * Get the value of name
   * The instructors name
   * @return the value of name
   */
  private String getName ( ) {
    return name;
  }

  /**
   * Set the value of email
   * Instructors email address
   * @param newVar the new value of email
   */
  private void setEmail ( String newVar ) {
    email = newVar;
  }

  /**
   * Get the value of email
   * Instructors email address
   * @return the value of email
   */
  private String getEmail ( ) {
    return email;
  }

  /**
   * Set the value of phone
   * Instructors phone number
   * @param newVar the new value of phone
   */
  private void setPhone ( String newVar ) {
    phone = newVar;
  }

  /**
   * Get the value of phone
   * Instructors phone number
   * @return the value of phone
   */
  private String getPhone ( ) {
    return phone;
  }

  //
  // Other methods
  //

  /**
   * Gets the instructors information as a string
   * @return       String
   */
  public String toString(){
	  String str;
	  str = "Name: " + getName() + "\n";
	  str += "ID: " + getId() + "\n";
	  str += "Email: " + getEmail() + "\n";
	  str += "Phone: " + getPhone() + "\n";
	  
	  return str;
  }


  /**
   * Compares the object pass via calling code to the current object
   * 
   * @return       boolean
   * @param        compObject The instructor object to compare
   */
  public boolean equals( Object compObject )
  {
	  boolean isEqual = false;
	  if(compObject instanceof Instructor){
		  Instructor p = (Instructor) compObject;
		  isEqual = (this.getName() == p.getName() &&
				     this.getId() == p.getId() &&
				     this.getEmail() == p.getEmail() &&
				     this.getPhone() == p.getPhone());
	  }
	  return isEqual;
  }
  
  /**
   * Retrieves the instructors name
   * 
   * @return The name of the instructor
   */
  public String retrieveInstructorName(){
	  return getName();
  }
  
  /**
   * Retrieves the ID number of the instructor
   *  
   * @return Current instructors ID
   */
  public int retrieveId(){
	  return getId();
  }

}
