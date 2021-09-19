package nordhw3;
//Loklin Nord, 9/19/21, HW 3.

public class CourseOutcome {
	
	//Instance variables
	private String studentID;
	private String courseName;
	private double grade;
	private int hours;
	
	//Constructor
	CourseOutcome (String studentID, String courseName, double grade, int hours) {
		this.studentID = studentID;
		this.courseName = courseName;
		this.grade = grade;
		this.hours = hours;
	}
	
	//Public default string return
	public String toString() {
		return studentID + " scored " + String.valueOf(grade) + " in " + courseName + ", " + String.valueOf(hours) + " hours.";
	}
	
	//Equal outcome method
	public boolean equals(CourseOutcome otherCourse) {
		if (this.toString().equals(otherCourse.toString())) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
