import java.util.ArrayList;
import java.util.HashMap;

/***************************************************************************************
 *                         Student.java - Copyright rciampa
 * Title: Student
 * Abstract: The student data structure
 *
 * @author rciampa
 * ID" 7470
 * Date: Sat Mar 7 2015 : 22:38:26
 * File: Student.java
 * Requires: Course.java
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
 * Class Student Data structure to hold School student information
 */
public class Student {

	//
	// Fields
	//

	/**
	 * The students ID
	 */
	private int Id = 0;
	/**
	 * Name of student
	 */
	private String name = "";
	/**
	 * Flag for student is graduated
	 */
	private boolean isGraduate;
	/**
	 * 
	 */
	private ArrayList<Integer> courses;
	/**
	 * School courses
	 */
	private static HashMap<Integer, Course> schoolCourses;

	//
	// Constructors
	//
	public Student() {
		setId(0);
		setIsGraduated(false);
		setName("");
		courses = new ArrayList<>();
		//schoolCourses = new HashMap<>();
	}
	
	public Student(int id, String name){
		setId(id);
		setName(name);
		setIsGraduated(false);
		courses = new ArrayList<>();
		//schoolCourses = new HashMap<>();
	}

	//
	// Methods
	//

	//
	// Accessor methods
	//

	/**
	 * Set the value of Id The students ID
	 * 
	 * @param newVar
	 *            the new value of Id
	 */
	private void setId(int newVar) {
		Id = newVar;
	}

	/**
	 * Get the value of Id The students ID
	 * 
	 * @return the value of Id
	 */
	private int getId() {
		return Id;
	}

	/**
	 * Set the value of name Name of student
	 * 
	 * @param newVar
	 *            the new value of name
	 */
	private void setName(String newVar) {
		name = newVar;
	}

	/**
	 * Get the value of name Name of student
	 * 
	 * @return the value of name
	 */
	private String getName() {
		return name;
	}
	
	/**
	 * Gets the graduation status of the student
	 * 
	 * @return True if student has graduated, false otherwise
	 */
	private boolean getIsGraduated(){
		return this.isGraduate;
	}
	
	/**
	 * Sets the state of a students graduation status
	 * 
	 * @param newVal True for graduate or false otherwise
	 */
	private void setIsGraduated(boolean newVal){
		this.isGraduate = newVal;
	}
	
	/**
	 * Adds a course to the students course list
	 * 
	 * @param newCourse The course number to add
	 */
	private void addCourses(int newCourse){
		courses.add(newCourse);
	}
	
	/**
	 * Removes a course from the list
	 * 
	 * @param index The index of the course to remove
	 */
	private void deleteCourse(int index){
		courses.remove(index);
	}
	
	/**
	 * Sets a reference to the schools courses
	 * 
	 * @param sCourses The schools courses
	 */
	private void setSchoolCourses(HashMap<Integer, Course> sCourses){
		Student.schoolCourses = sCourses;
	}

	//
	// Other methods
	//
	
	/**
	 * Retrieves the students school ID
	 * 
	 * @return The ID of the student
	 */
	public int retrieveId(){
		return getId();
	}
	
	/**
	 * Retrieves the graduation status of the student
	 * 
	 * @return True if student is a graduate, false otherwise
	 */
	public boolean isGraduated(){
		return getIsGraduated();
	}
	
	/**
	 * Retrieves the name of the student
	 * 
	 * @return Students name
	 */
	public String retrieveName(){
		return getName();
	}
	
	/**
	 * Graduate the student
	 */
	public void graduateStudent(){
		setIsGraduated(true);
	}
	
	/**
	 * Removes a course from the students courses
	 * 
	 * @param course The course number to remove
	 */
	public void removeCourse(int course){
		int count = 0;
		for(Integer c: courses){
			if(c.intValue() == course){
				deleteCourse(count);
				break;
			}
			count++;
		}
	}

	/**
	 * Checks for an existing course
	 * 
	 * @param course The course number to check
	 * @return True if course exist, false otherwise
	 */
	public boolean isExistingCourse(int course){
		boolean isExisting = false;
		for(Integer c: courses){
			if(c.intValue() == course){
				isExisting = true;
				break;
			}
		}
		return isExisting;
	}

	/**
	 * Adds a new course to the students course list
	 * 
	 * @param course The course number to add
	 */
	public void addCourse(int course){
		if(!isExistingCourse(course)){
			addCourses(course);
		}
	}
	
	/**
	 * Sets up access to the schools courses for the student class
	 * 
	 * @param sCourses The schools courses
	 */
	public void setReferenceOfCourses(HashMap<Integer, Course> sCourses){
		setSchoolCourses(sCourses);
	}
	
	/**
	 * Returns a list of courses the student is enrolled in
	 * 
	 * @return
	 */
	public ArrayList<Integer> retrieveCourseList(){
		ArrayList<Integer> temp = courses;
		return temp;
	}
	
	/**
	 * The string representing the data fields in the class
	 * 
	 * @return String
	 */
	public String toString() {
		String strTemp;
		strTemp = "Student Number: " + getId() + "\n";
		if (!getIsGraduated()) {
			strTemp += "Name: " + getName() + "\n";
			strTemp += "Courses Enrolled:\n";
			// Add courses
			double sum = 0.0;
			int count = 0;
			for(int courseNum: courses){
				CourseGrade cg = schoolCourses.get(courseNum).retrieveCourseGrade(getId());
			    strTemp += "\t" + courseNum + ": " + cg.toString() + "\n";
			    sum += cg.retrieveScore();
			    count++;
			}
			strTemp += "Course Average: " + String.format("%1.2f", (sum/count)) + "\n";
		}else{
			strTemp += "No Student Info!\n";
		}
		return strTemp;
	}

	/**
	 * @return boolean
	 * @param compObject
	 *            The student object to compare passed by the calling code
	 */
	public boolean equals(Object compObject) {
		
		boolean isEqual = false;
		if(compObject instanceof Student){
			Student s = (Student) compObject;
			isEqual = (this.getName() == s.getName() &&
					   this.getId() == s.getId() &&
					   this.getIsGraduated() == s.getIsGraduated());
		}
		return isEqual;
	}

}
