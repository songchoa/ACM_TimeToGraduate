package pkg1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		
//		Scanner in = new Scanner(System.in);
//		System.out.println("Input starts: ");
//		String first = in.nextLine();
//		int dataSetCounter = 0;
//		
//		while (dataSetCounter <= 25 && first.indexOf("-1 -1") == -1) {
//			DataSet tempCase = new DataSet();
//			String[] CourseNumAndMax = first.split(" ");
//			tempCase.setNumOfCourse(Integer.valueOf(CourseNumAndMax[0]));
//			tempCase.setMaxNumOfCourse(Integer.valueOf(CourseNumAndMax[1]));
//			String courseNames = in.nextLine();
//			for(int i = 0; i < tempCase.getNumOfCourse(); i++) {
//				Course tempCourse = new Course(in.nextLine());
//				tempCase.addCourse(tempCourse);
//			}
//			ArrayList<Course> temp = tempCase.getCourses();
//			for(int i = 0; i < temp.size(); i++) {
//				System.out.println(temp.get(i).toString());
//			}
//			
//			int semestersNeeded = calculateSemester(tempCase);
//			System.out.println("The minimum number of semesters required to graduate is " + semestersNeeded);
//			first = in.nextLine();
//			dataSetCounter++;
//			
//		}
//		
//		in.close();
//		
		DataSet ds = new DataSet();
		ds.setNumOfCourse(5);
		ds.setMaxNumOfCourse(6);
		ds.addCourse(new Course("mt42 F 0"));
		ds.addCourse(new Course("cs123 S 0"));
		ds.addCourse(new Course("cs456 S 2 cs123 mt42"));
		ds.addCourse(new Course("cs789 B 1 cs456"));
		ds.addCourse(new Course("cs999 F 3 cs123 cs456 cs789"));
		int semesterNeeded = calculateSemester(ds);
//		DataSet ds = new DataSet();
//		ds.setNumOfCourse(3);
//		ds.setMaxNumOfCourse(6);
//		ds.addCourse(new Course("comp3 S 1 comp2"));
//		ds.addCourse(new Course("math1 S 0"));
//		ds.addCourse(new Course("comp2 F 1 math1"));
//		int semesterNeeded = calculateSemester(ds);
		System.out.println("Semester needed: " + semesterNeeded);
	}
	
	public static int calculateSemester(DataSet ds) {
//		int numOfSem = 0;
//		String[] finished = {};
		chooseCoursesForNextSemester(ds,0);
		return 0;
	}
	
//	public static int chooseCoursesForNextSemester(ArrayList<Course> all,ArrayList<String> finished, ArrayList<Course> left,int maxCoursePerSem, int numOfSem) {
//		
//		if(finished.size() == all.size()) {
//			return numOfSem;
//		} else {
//			ArrayList<Course> validCourses = getValidCourses(finished, left, numOfSem);
//			if(validCourses.size() < maxCoursePerSem) {
//				
//			}
//			
//		}
//		return 0;
//	}
	
	public static void chooseCoursesForNextSemester(DataSet ds, int sem) {
		
		if(ds.getCourseLeft().size() == 0) {
			System.out.println(sem);
		} else {
			ArrayList<Course> validCourses = getValidCourses(ds.getCourseFinished(),ds.getCourseLeft(),sem);
			if(validCourses.size() <= ds.getMaxCoursePerSem()) {
				for(Course c : validCourses) {
					ds.addFinishedCourse(c);
				}
				
				sem = sem + 1;
				chooseCoursesForNextSemester(ds, sem);
			}
		}
	}
	
	public static ArrayList<Course> getValidCourses(ArrayList<String> cf, ArrayList<Course> cl, int numOfSem) {

		ArrayList<Course> valid = new ArrayList<Course>();
		char sem = ' ';
		
		if(numOfSem % 2 == 0) {
			sem = 'F';
		} else {
			sem = 'S';
		}
		
		for(Course c : cl) {
			if(c.getSemester() == sem || c.getSemester() == 'B') {
				valid.add(c);
			}
		}
		
		for(int i = 0; i < valid.size(); i++) {
			if(!preFinished(cf, valid.get(i))) {
				valid.remove(i);
				i = i - 1;
			}
		}
		
		return valid;
		
	}
	
	public static boolean preFinished(ArrayList<String> cf, Course c) {
		for(String cname : c.getPreCourses()) {
			if(!cf.contains(cname)) {
				return false;
			}
		}
		
		return true;
	}
	
}
