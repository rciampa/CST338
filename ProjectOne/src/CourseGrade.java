
public class CourseGrade {
	
	/**
	 * 
	 */
	private char letter;
	/**
	 * 
	 */
	private double score;
	/**
	 * 
	 */
	private int studentId;
	/**
	 * Name of student
	 */
	private String name;
	
	/**
	 * Default constructor
	 */
	public CourseGrade(){
		setLetter('X');
		setScore(0.0);
	}
	
	/**
	 * Constructor
	 * 
	 * @param grade The letter grade for course
	 * @param score The score for the course
	 */
	public CourseGrade(char grade, double score, int id, String name){
		setLetter(grade);
		setScore(score);
		setStudentId(id);
		setName(name);
	}
	
	/**
	 * Set the name of the student
	 * 
	 * @param newName
	 */
	private void setName(String newName){
		this.name = newName;
	}
	
	/**
	 * Gets the name of the student
	 * 
	 * @return Name of student
	 */
	private String getName(){
		return this.name;
	}
	
	/**
	 * Sets the letter grade
	 * 
	 * @param newValue The letter grade to set
	 */
	private void setLetter(char newValue){
		letter = newValue;
	}
	
	/**
	 * Sets the score value
	 * 
	 * @param newValue The value of score to set
	 */
	private void setScore(double newValue){
		score = newValue;
	}
	
	/**
	 * Gets the current course letter grade
	 * 
	 * @return The letter grade
	 */
	private char getLetter(){
		return this.letter;
	}
	
	/**
	 * Gets the current course score
	 * 
	 * @return The course score
	 */
	private double getScore(){
		return this.score;
	}
	
	/**
	 * Sets the student ID
	 * 
	 * @param id Student ID to set
	 */
	private void setStudentId(int id){
		this.studentId = id;
	}
	
	/**
	 * Gets the ID of the student
	 * 
	 * @return Students ID
	 */
	private int getStudentID(){
		return this.studentId;
	}
	
	/**
	 * 
	 */
	public String toString(){
		return String.format("%1.2f ", getScore()) + "(" + getLetter() + ")";
	}
	
	/**
	 * Updates the course letter grade
	 * 
	 * @param grade The grade to enter for course
	 */
	public void updateLetterGrade(char grade){
		setLetter(grade);
	}
	
	/**
	 * Retrieves the letter grade for the course
	 * 
	 * @return The course letter grade
	 */
	public char retrieveLetterGrade(){
		return getLetter();
	}
	
	/**
	 * Updates the course score
	 * 
	 * @param score The score value to enter
	 */
	public void updateScore(double score){
		setScore(score);
	}
	
	/**
	 * Retrieves the score for the course
	 * 
	 * @return The course score
	 */
	public double retrieveScore(){
		return getScore();
	}
	
	/**
	 * Updates the student ID for the course grade
	 * 
	 * @param id The ID to update
	 */
	public void updateStudentId(int id){
		setStudentId(id);
	}
	
	/**
	 * Retrieves the students ID
	 * 
	 * @return Student ID
	 */
	public int retrieveStudentId(){
		return getStudentID();
	}
	
	/**
	 * Retrieves the students name
	 * 
	 * @return Name of student
	 */
	public String retrieveStudentName(){
		return getName();
	}
	
	/**
	 * Updates the student name
	 * 
	 * @param newName
	 */
	public void updateStudentName(String newName){
		setName(newName);
	}
}
