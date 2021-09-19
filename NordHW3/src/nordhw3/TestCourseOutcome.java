package nordhw3;
//Loklin Nord, 9/19/21, HW 3.

public class TestCourseOutcome {

    public static void main(String[] args) {
        // Test the constructors, practice creating an array of objects, 
        CourseOutcome[] student1Courses = new CourseOutcome[3];
        student1Courses[0] = new CourseOutcome("879333121", "Intro to CS 1", 3.0, 4);
        student1Courses[1] = new CourseOutcome("879333121", "Environmental Systems", 3.7, 4);
        student1Courses[2] = new CourseOutcome("879333121", "Intro to CS 2", 4.0, 4);
        for (int i = 0; i < student1Courses.length; i++) {
            System.out.println(student1Courses[i]);
        }
        
        // now test the equals method:
        CourseOutcome testoutcome1 = new CourseOutcome("873", "Course 1", 3.0, 4);
        CourseOutcome testoutcome2 = new CourseOutcome("872", "Course 1", 3.0, 4);
        CourseOutcome testoutcome3 = new CourseOutcome("873", "Course 2", 3.0, 4);
        CourseOutcome testoutcome4 = new CourseOutcome("873", "Course 1", 3.3, 4);
        CourseOutcome testoutcome5 = new CourseOutcome("873", "Course 1", 3.0, 4);
        if (!testoutcome1.equals(testoutcome2) &&
            !testoutcome1.equals(testoutcome2) &&
            !testoutcome1.equals(testoutcome3) &&
            !testoutcome1.equals(testoutcome4) &&
            testoutcome1.equals(testoutcome5)) {
            System.out.println("The equals method passes a rudimentary test");
        } else  {
            System.out.println("The equals method is not working");
        }

    }

}