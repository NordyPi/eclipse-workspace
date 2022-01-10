package intro;

public class Student implements Comparable<Student> {
	private String name;
	private int score;
	
	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String toString() {
		return name + " : " + score;
	}
	
	public boolean equals(Object o) {
		Student s = (Student) o;
		
		return ((this.name.equals(s.name)) &&
				(this.score == s.score));
	}

	@Override
	public int compareTo(Student s) {
		// a.compareTo(b)
		int result = this.score - s.score;
		
		return result;
	}
}
