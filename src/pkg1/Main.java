package pkg1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		System.out.println("Input starts: ");
		String first = in.nextLine();
		int dataSetCounter = 0;
		
		while (dataSetCounter <= 25 && first.indexOf("-1 -1") == -1) {
			DataSet tempCase = new DataSet();
			String[] CourseNumAndMax = first.split(" ");
			tempCase.setNumOfCourse(Integer.valueOf(CourseNumAndMax[0]));
			tempCase.setMaxNumOfCourse(Integer.valueOf(CourseNumAndMax[1]));
			String courseNames = in.nextLine();
			for(int i = 0; i < tempCase.getNumOfCourse(); i++) {
				Course tempCourse = new Course(in.nextLine());
				tempCase.addCourse(tempCourse);
			}
			ArrayList<Course> temp = tempCase.getCourses();
			for(int i = 0; i < temp.size(); i++) {
				System.out.println(temp.get(i).toString());
			}
			
			int semestersNeeded = calculateSemester(tempCase);
			System.out.println("The minimum number of semesters required to graduate is " + semestersNeeded);
			first = in.nextLine();
			dataSetCounter++;
			
		}
		
		in.close();
		
	}
	
	public static int calculateSemester(DataSet c) {
		int numOfSem = 0;
		String[] finished = {};
		
		return 0;
	}
	
	public static int chooseCoursesForNextSemester(ArrayList<Course> all,ArrayList<String> finished, ArrayList<Course> left,int numOfSem) {
		
		if(finished.size() == all.size()) {
			return numOfSem;
		} else {
			ArrayList<Course> validCourses = getValidCourses(finished, left, numOfSem);
			
		}
		return 0;
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
		
		for(Course c : valid) {
			if(!preFinished(cf, c)) {
				valid.remove(c);
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
