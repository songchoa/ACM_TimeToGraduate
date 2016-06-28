package pkg1;

import java.util.ArrayList;

public class DataSet {
	
	private int numOfCourse;
	
	private int maxCoursePerSem;
	
	private ArrayList<Course> courses;
	
	private ArrayList<String> courseFinished;
	
	private ArrayList<Course> courseLeft;
	
	public DataSet() {
		courses = new ArrayList<Course>();
		courseFinished = new ArrayList<String>();
		courseLeft = new ArrayList<Course>();
	}
	
	public void setNumOfCourse(int num) {
		this.numOfCourse = num;
	}
	
	public void setMaxNumOfCourse(int max) {
		this.maxCoursePerSem = max;
	}
	
	public boolean addCourse(Course c) {
		
		courses.add(c);
		courseLeft.add(c);
		return courses.indexOf(c) != -1;
	}
	
	public void addFinishedCourse(Course cf) {
		courseFinished.add(cf.getCourseName());
		courseLeft.remove(cf);
	}
	
	public ArrayList<String> getCourseFinished() {
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
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
}
