import java.io.FileNotFoundException;
import java.io.IOException;


public class SchoolDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException{
		School itcd = new School("ITCD");
		Course course1;
		
		System.out.println("===== Read Data =====");
		itcd.readData("C:\\tmp\\test1.txt");
		//Test: /home/rciampa/Documents/CST338/pj1/file
		
		System.out.println("\n====== School Info ======");
		itcd.schoolInfo();
		
		itcd.addInstructor(700, "E. Tao", "tao@csumb.edu", "777-777-1234");
		itcd.addCourse(300, "CST300 – ProSem", 700, "MLC110");
		itcd.addCourse(231, "CST231 – Intro C++", 100, "MLC104");
		
		System.out.println("\n===== Detailed Course Info =====");
		itcd.courseInfo(306);
		
		course1 = itcd.getCourse(205);
		course1.updateLocation("Library 104");
		System.out.println("\n===== Detailed Course Info 2 =====");
		itcd.courseInfo(205);
		
		System.out.println("\n===== Detailed Course Info 3 =====");
		itcd.courseInfo();
		itcd.deleteCourse(231);
		itcd.deleteCourse(336);
		itcd.deleteCourse(338);
		System.out.println("\n===== Detailed Course Info 4 =====");
		itcd.courseInfo();
		
		itcd.addStudent(5555, "Chris Watson", 205, 85.50, "B");
		System.out.println("\n===== Detailed Course Info 5 =====");
		itcd.courseInfo(205);
		System.out.println("\n===== Detailed Student Info =====");
		itcd.studentInfo(7777);
		itcd.graduateStudent(7777);
		System.out.println("\n===== Detailed Student Info 2 =====");
		itcd.studentInfo(7777);
		itcd.graduateStudent(5555);
		System.out.println("\n===== Detailed Course Info 6 =====");
		itcd.courseInfo(205);
		itcd.graduateStudent(5555);
		System.out.println("\n===== Good Job! Bye! =====");


		

	}

}
