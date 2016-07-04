package pkg1;

import java.util.ArrayList;
/**
 * Used to store a set of data such as number of courses or max number of courses.
 * @author Chao Song
 *
 */
public class DataSet {
	
	private int numOfCourse;
	
	private int maxCoursePerSem;
	
	private ArrayList<Course> courseFinished;
	
	private ArrayList<Course> courseLeft;
	
	public DataSet() {
		courseFinished = new ArrayList<Course>();
		courseLeft = new ArrayList<Course>();
	}
	
	public void setNumOfCourse(int num) {
		this.numOfCourse = num;
	}
	
	public void setMaxNumOfCourse(int max) {
		this.maxCoursePerSem = max;
	}
	
	public void addLeftCourse(Course c) {
		
		courseLeft.add(c);
		
		if(courseFinished.contains(c)) {
			courseFinished.remove(c);
		}

	}
	
	public void addFinishedCourse(Course cf) {
		courseFinished.add(cf);
		courseLeft.remove(cf);
	}
	
	public ArrayList<Course> getCourseFinished() {
		return courseFinished;
	}
	
	public ArrayList<Course> getCourseLeft() {
		return courseLeft;
	}
	
	public int getNumOfCourse() {
		return this.numOfCourse;
	}
	
	public int getMaxCoursePerSem() {
		return this.maxCoursePerSem;
	}
	
}

