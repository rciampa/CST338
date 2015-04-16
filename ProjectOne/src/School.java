import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/***************************************************************************************
 *                         School.java - Copyright rciampa
 * Title: School
 * Abstract: Holds the operation and data structures for the SchoolDemo.java driver.
 * 			 Allows a user to read in instructors, courses and students from a flat
 * 			 file. You are able to add and update relative course information, graduate
 * 			 a student and get detail on the school, course or student
 * @author rciampa
 * ID" 7470
 * Date: Sat Mar 7 2015 : 22:38:26
 * File: School.java
 * Requires: Course.java, Student.java and Instructor.java
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
 * Class School
 */
public class School {

	//
	// Fields
	//
	private String name;

	private HashMap<Integer, Course> courses;

	private HashMap<Integer, Student> students;

	private HashMap<Integer, Instructor> instructors;

	//
	// Constructors
	//
	public School() {
		name = "";
		courses = new HashMap<>();
		students = new HashMap<>();
		instructors = new HashMap<>();
		Student s = new Student();
		s.setReferenceOfCourses(courses);
	};

	/**
	 * Constructor with name parameter
	 * 
	 * @param name
	 *            The name of the school
	 */
	public School(String name) {
		setSchoolName(name);
		courses = new HashMap<>();
		students = new HashMap<>();
		instructors = new HashMap<>();
		Student s = new Student();
		s.setReferenceOfCourses(courses);
	}

	//
	// Methods
	//

	//
	// Accessor methods
	//

	/**
	 * Set the value of the school name
	 * 
	 * @param value
	 *            The name of the school
	 */
	private void setSchoolName(String value) {
		this.name = value;
	}

	/**
	 * Gets the schools name
	 * 
	 * @return School name
	 */
	private String getSchoolName() {
		return this.name;
	}

	/**
	 * Set the value of courses
	 * 
	 * @return True if course was added, false otherwise
	 * @param newVar
	 *            the new value of courses
	 */
	private boolean newCourse(Course newCourse) {
		if (courses.put(newCourse.retrieveCourseNumber(), newCourse) == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get the value of courses
	 * 
	 * @return the value of courses
	 */
	private Course getCourseClass(int courseNum) {
		return courses.get(courseNum);
	}

	//
	// Other methods
	//

	/**
	 * Retrieves the school name
	 * 
	 * @return
	 */
	public String retieveSchoolName() {
		return getSchoolName();
	}

	/**
	 * @return boolean
	 * @param courseNumber
	 *            The number of the course in the school catalog
	 * @param courseName
	 *            The course catalog number and name
	 * @param instructorId
	 *            The course instructors ID
	 * @param room
	 *            The room the course is held in
	 */
	public boolean addCourse(int courseNumber, String courseName,
			int instructorId, String room) {
		Course c = new Course(courseNumber, courseName, instructorId, room);
		c.updateInstructor(instructors.get(instructorId).retrieveInstructorName());
		return newCourse(c);
	}

	/**
	 * Used to add an instructor to the school
	 * 
	 * @return boolean
	 * @param id
	 *            The instructors school ID
	 * @param instructorName
	 *            The instructors name
	 * @param email
	 *            The email address for the instructor
	 * @param phone
	 *            Instructors phone number
	 */
	public boolean addInstructor(int id, String instructorName, String email,
			String phone) {
		boolean isAdded = false;
		if (!instructors.containsKey(id)) {
			// Create the new instructor
			Instructor currInst = new Instructor(id, instructorName, email,
					phone);
			// Add the instructor to the system
			instructors.put(currInst.retrieveId(), currInst);
			// Check the entry
			isAdded = instructors.containsKey(id);
		} else {
			System.out.println("Instructor addition failed - already exist");
		}
		return isAdded;
	}

	/**
	 * Adds a student to the school and the selected course or just the course
	 * if the student is existing
	 * 
	 * @param id
	 *            Students school ID
	 * @param name
	 *            Name of student
	 * @param course
	 *            The class to enroll in
	 * @param score
	 *            Current score
	 * @param grade
	 *            Letter grade
	 * @return True if student was added, false otherwise
	 */
	public boolean addStudent(int id, String name, int course, double score,
			String grade) {
		boolean isAdded = false;
		if (!students.containsKey(id)) {
			Student currStudent = new Student(id, name);
			//Add the course to the student's list
			currStudent.addCourse(course);
			students.put(currStudent.retrieveId(), currStudent);
			isAdded = students.containsKey(id);
			//Add student to course
			courses.get(course).addStudentGrade(grade, score, id, name);
		} else {
			if(!students.get(id).isExistingCourse(course)){
				students.get(id).addCourse(course);
				courses.get(course).addStudentGrade(grade, score, id, name);
				isAdded = students.get(id).isExistingCourse(course);
			}else{
				System.out.println("Student addtion failed - allready exist");
			}
		}
		return isAdded;
	}

	/**
	 * @param couseNumber
	 *            The number of the course to get information for
	 */
	public void courseInfo(int courseNumber) {
		Course c = getCourse(courseNumber);
		System.out.println(c.toString());
	}

	/**
	 * Prints the school course information
	 */
	public void courseInfo() {
		String strTemp;
		strTemp = "Number of Courses: " + courses.size() + "\n";
		for (Course currCourse : courses.values()) {
			strTemp += "\t" + currCourse.retrieveCourseNumber() + ": "
					+ currCourse.retrieveCourseSize() + " enrolled\n";
		}
		System.out.println(strTemp);
	}

	/**
	 * Deletes a course from the school
	 * 
	 * @return boolean
	 * @param courseNumber
	 *            The course number to delete
	 */
	public boolean deleteCourse(int courseNumber) {
		boolean isDeleted = false;
		if (courses.containsKey(courseNumber)) {
			if (courses.get(courseNumber).isCourseEmpty()) {
				courses.remove(courseNumber);
				isDeleted = true;
			} else {
				System.out
						.println("Course deletion failed – Enrolled student(s) in the class");
			}
		} else {
			System.out.println("Course deletion failed – Does not exist");
		}
		return isDeleted;
	}

	/**
	 * Gets the course by course number
	 * 
	 * @return Course
	 * @param courseNumber
	 *            The number of the course to retrieve
	 */
	public Course getCourse(int courseNumber) {
		return getCourseClass(courseNumber);
	}

	/**
	 * @return boolean
	 * @param studentId
	 *            The school ID of the student
	 */
	public boolean graduateStudent(int studentId) {
		boolean isGrad = false;
		if (students.containsKey(studentId) && !students.get(studentId).isGraduated()) {
			students.get(studentId).graduateStudent();
			isGrad = students.get(studentId).isGraduated();
			for(Course c: courses.values()){
				c.deleteStudentGrade(studentId);
			}
		}else{
			System.out.println("Student graduation failed - Non existing student");
		}
		return isGrad;
	}

	/**
	 * Prints the schools information to the console
	 */
	public void schoolInfo() {
		String strTemp;
		strTemp = "School Name: " + getSchoolName() + "\n";
		strTemp += "Instructor Information\n";
		// Add each instructor to the string
		for (Instructor instructor : instructors.values()) {
			strTemp += "\t" + instructor.retrieveInstructorName() + "\n";
		}
		strTemp += "Course Information\n";
		// Add each course to the string
		for (Course currCourse : courses.values()) {
			strTemp += "\t" + currCourse.retrieveCourseName() + "\n";
		}
		strTemp += "Student Information\n";
		// Add each student to the string
		for (Student pupil : students.values()) {
			ArrayList<Integer> courseList = pupil.retrieveCourseList();
			for(int c: courseList){
				strTemp += "\t" + pupil.retrieveName() + ": "
			            + courses.get(c).retrieveCourseName() + "\n";
			}
			
			
		}
		// Print to the console
		System.out.println(strTemp);
	}

	/**
	 * Gets detailed information on a student in the school
	 * 
	 * @return boolean
	 * @param studentId
	 *            The school ID of the student
	 */
	public boolean studentInfo(int studentId) {
		boolean hasInfo = false;
		if(students.containsKey(studentId)){
			System.out.print(students.get(studentId).toString());
			
		}
		return hasInfo;
	}

	/**
	 * Used by the client application to read data from a file.
	 * 
	 * @param filePath
	 *            The file name and path to open for reading
	 */
	public void readData(String filePath) throws IOException,
			FileNotFoundException {

		String line;
		//filePath = "/home/rciampa/Documents/CST338/pj1/file";
		BufferedReader wordFileBuffer = null;

		try {
			wordFileBuffer = new BufferedReader(new FileReader(filePath));

			for (int s = 1; s <= 3; s++) {

				line = wordFileBuffer.readLine();
				int loop = Integer.parseInt(line.trim());
				for (int i = 0; i < loop; i++) {
					line = wordFileBuffer.readLine();
					String[] strTemp = line.split("\\,");

					/*
					 * Switch catches each section of the school data file and
					 * inserts the line data with a appropriate method
					 */

					switch (s) {
					case 1:
						addInstructor(Integer.parseInt(strTemp[0]), strTemp[1],
								strTemp[2], strTemp[3]);
						break;
					case 2:
						addCourse(Integer.parseInt(strTemp[0]), strTemp[1],
								Integer.parseInt(strTemp[2]), strTemp[3]);
						break;
					case 3:
						addStudent(Integer.parseInt(strTemp[0]), strTemp[1],
								Integer.parseInt(strTemp[2]),
								Double.parseDouble(strTemp[3]), strTemp[4]);
						break;
					}
				}
			}
		} catch (FileNotFoundException fnfEx) {// Extended exception
			System.err.println("There was an error accessing the file.");
			System.err.println(fnfEx.toString());
			System.exit(0);
		} catch (IOException ioEx) {// Base exception
			System.err.println(ioEx.toString());
			System.exit(0);
		} finally {
			if (wordFileBuffer != null) {
				wordFileBuffer.close();
			}
		}

	}
}
