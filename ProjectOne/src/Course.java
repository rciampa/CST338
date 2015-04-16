import java.util.HashMap;

/***************************************************************************************
 *                         Course.java - Copyright rciampa
 * Title: Course
 * Abstract: Course data structure to hold a school course with related information
 *
 * @author rciampa
 * ID" 7470
 * Date: Sat Mar 7 2015 : 22:38:26
 * File: Course.java
 * Requires: CourseGrade.java
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
 * Class Course
 * Data structure for the School class course information
 */
public class Course {

  //
  // Fields
  //

  /**
   * The course number
   */
  private int number = 0;
  /**
   * Name of course
   */
  private String name = "";
  /**
   * The ID number of the instructor that is teach this course
   */
  private int instructorId = 0;
  /**
   * Room that this course is held in
   */
  private String classroom = "";
  /**
   * The name of the instructor
   */
  private String instructorName;
  /**
   *The course grades 
   */
  private HashMap<Integer, CourseGrade> grades;

  //
  // Constructors
  //
  public Course () {
	  //Set the defaults
	  setName("NEW COURSE");
	  setNumber(0);
	  setInstructorId(0);
	  setClassroom("");
	  setInstructorName("");
	  grades = new HashMap<>();
  }
  
  /**
   * 
   * @param number
   * @param name
   * @param instructor
   * @param room
   */
  public Course (int number, String name, int instructorId, String room){
	  setNumber(number);
	  setName(name);
	  setInstructorId(instructorId);
	  setClassroom(room);
	  grades = new HashMap<>();
  }
  
  //
  // Accessor methods
  //

  /**
   * Set the value of number
   * The course number
   * @param newVar the new value of number
   */
  private void setNumber ( int newVar ) {
    number = newVar;
  }

  /**
   * Get the value of number
   * The course number
   * @return the value of number
   */
  private int getNumber ( ) {
    return number;
  }

  /**
   * Set the value of name
   * Name of course
   * @param newVar the new value of name
   */
  private void setName ( String newVar ) {
    name = newVar;
  }

  /**
   * Get the value of name
   * Name of course
   * @return the value of name
   */
  private String getName ( ) {
    return name;
  }

  /**
   * Set the value of instructorId
   * The ID number of the instructor that is teach this course
   * @param newVar the new value of instructorId
   */
  private void setInstructorId ( int newVar ) {
    instructorId = newVar;
  }

  /**
   * Get the value of instructorId
   * The ID number of the instructor that is teach this course
   * @return the value of instructorId
   */
  private int getInstructorId ( ) {
    return instructorId;
  }

  /**
   * Set the value of classroom
   * Room that this course is held in
   * @param newVar the new value of classroom
   */
  private void setClassroom ( String newVar ) {
    classroom = newVar;
  }

  /**
   * Get the value of classroom
   * Room that this course is held in
   * @return the value of classroom
   */
  private String getClassroom ( ) {
    return classroom;
  }
  
  /**
   * 
   * @return
   */
  private String getInstructorName(){
	  return this.instructorName;
  }
  
  /**
   * 
   * @param val
   */
  private void setInstructorName(String val){
	  this.instructorName = val;
  }
  

  //
  // Other methods
  //
  
  /**
   * Retrieves the course number
   * @return
   */
  public int retrieveCourseNumber(){
	  return getNumber();
  }
  
  /**
   * Generates a string used by the calling code
   * 
   * @return String
   */
  public String toString(){
	  String str;
	  
	  str = "Course Number: " + this.getNumber() + "\n";
	  str += "Instructor: " + this.getInstructorName() + "\n";
	  str += "Course Title: " + this.getName() + "\n";
	  str += "Room: " + this.getClassroom() + "\n";
	  str += "Enrolled Students:\n";
	  //str += list students
	  for(CourseGrade cg: grades.values()){
		  str += "\t" + cg.retrieveStudentName() + ": " + cg.toString() + "\n";
	  }
	  str += "Course Average: " + String.format("%1.2f", courseAverageScore())
			  + "\n";
	  
	  return str;
  }


  /**
   * Compares course object passed by the calling code
   * 
   * @return  boolean
   * @param   compObject The course object to compare
   */
  public boolean equals( Object compObject )
  {
	  boolean isEqual = false;
	  if(compObject instanceof Course){
		  Course c = (Course) compObject;
		  isEqual = (this.getName() == c.getName() &&
				  this.getNumber() == c.getNumber() &&
				  this.getInstructorId() == c.getInstructorId() &&
				  this.getClassroom() == c.getClassroom());
	  }
	  return isEqual;
  }
  
  /**
   * Updates the course location
   * 
   * @param location The building and classroom of course
   */
  public void updateLocation(String location){
	  setClassroom(location);
  }

  /**
   * Calculates the average score
   * 
   * @return Average score
   */
  public double courseAverageScore(){
	  double sum = 0.0;
	  for(CourseGrade grade: grades.values()){
		  sum += grade.retrieveScore();
	  }
	  if(grades.size() == 0){
	     return 0.0;
	  }
	  
	  return sum/grades.size();
  }
  
  /**
   * Checks to see f the course is empty
   * 
   * @return True if course is empty, otherwise false
   */
  public boolean isCourseEmpty(){
	  return (grades.size() == 0);
  }
  
  /**
   * Retrieves the number of students in the course
   * 
   * @return Student count
   */
  public int retrieveCourseSize(){
	  return grades.size();
  }
  
  /**
   * Retrieves the course name
   * 
   * @return The course name
   */
  public String retrieveCourseName(){
	  return getName();
  }
  
  /**
   * Sets the instructors name for the course
   * 
   * @param name Name of instructor
   */
  public void updateInstructor(String name){
	  setInstructorName(name);
  }
  
  /**
   * Adds a student grade to the course
   * 
   * @param letter The letter grade
   * @param score The score
   * @param id Students ID
   */
  public void addStudentGrade(String letter, double score, int id, String name){
	  CourseGrade grade = new CourseGrade(letter.charAt(0), score, id, name);
	  grades.put(grade.retrieveStudentId(), grade);
  }
  
  /**
   * Retrieves the grade for the student
   * 
   * @return
   */
  public CourseGrade retrieveCourseGrade(int studentId){
	  return grades.get(studentId);
  }
  
  /**
   * Remove the students grade from this course
   * 
   * @param studentId The student ID
   */
  public void deleteStudentGrade(int studentId){
	  grades.remove(studentId);
  }
  
}
