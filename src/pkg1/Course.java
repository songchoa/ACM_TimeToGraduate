package pkg1;

import java.util.ArrayList;

/**
 * Course class used to store all the information about one specific course
 * @author Chao Song
 *
 */
public class Course {
	
	private String courseName;
	
	private String courseStrInfo;
	
	private ArrayList<String> preCourses;
	
	private int numOfPre;
	
	private char semester;
	
	public Course(String courseStrInfo) {
		preCourses = new ArrayList<String>();
		this.courseStrInfo = courseStrInfo;
		parseCourseInfo(courseStrInfo);
	}
	
	private void parseCourseInfo(String courseInfo) {
		String[] courseInfoArr = courseInfo.split(" ");
		courseName = courseInfoArr[0];
		semester = courseInfoArr[1].charAt(0);
		numOfPre = Integer.valueOf(courseInfoArr[2]);
		for(int i = 0; i < numOfPre; i++) {
			preCourses.add(courseInfoArr[3 + i]);
		}
	}

	public void setNumOfPre(int num) {
		
		this.numOfPre = num;
	}
	
	public int getNumOfPrerequisite() {
		return this.numOfPre;
	}
	
	public ArrayList<String> getPreCourses() {
		return preCourses;
	}
	
	public void setSemester(char s) {
		this.semester = s;
	}
	
	public char getSemester() {
		return this.semester;
	}
	
	public boolean addPrerequisite(String courseName ) {
		
		this.preCourses.add(courseName);
		
		return this.preCourses.indexOf(courseName) != -1;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public String toString() {
		return courseStrInfo;
	}
}
