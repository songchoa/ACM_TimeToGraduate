package pkg1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class for the TimeToGraduate Calculator
 * @author Chao Song
 *
 */
public class Main {

	private static ArrayList<String> result;

	private static int min = 1000;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String first = in.nextLine();
		int dataSetCounter = 0;

		while (dataSetCounter <= 25 && first.indexOf("-1 -1") == -1) {
			DataSet tempCase = new DataSet();
			String[] CourseNumAndMax = first.split(" ");
			tempCase.setNumOfCourse(Integer.valueOf(CourseNumAndMax[0]));
			tempCase.setMaxNumOfCourse(Integer.valueOf(CourseNumAndMax[1]));
			String courseNames = in.nextLine();
			for (int i = 0; i < tempCase.getNumOfCourse(); i++) {
				Course tempCourse = new Course(in.nextLine());
				tempCase.addLeftCourse(tempCourse);
			}
			min = 1000;
			calculateSemester(tempCase);
			System.out
					.println("The minimum number of semesters required to graduate is "
							+ min + ".");
			first = in.nextLine();
			dataSetCounter++;

		}

		in.close();

	}

	public static int calculateSemester(DataSet ds) {
		chooseCoursesForNextSemester(ds, 0);
		return 0;
	}

	public static void chooseCoursesForNextSemester(DataSet ds, int sem) {

		if (ds.getCourseLeft().size() == 0) {
			min = Math.min(sem, min);
		} else {

			ArrayList<Course> validCourses = getValidCourses(
					ds.getCourseFinished(), ds.getCourseLeft(), sem);

			if (validCourses.size() <= ds.getMaxCoursePerSem()) {
				for (int i = 0; i < validCourses.size(); i++) {
					ds.addFinishedCourse(validCourses.get(i));
				}

				sem = sem + 1;
				chooseCoursesForNextSemester(ds, sem);
				sem = sem - 1;
				for (int i = 0; i < validCourses.size(); i++) {
					ds.addLeftCourse(validCourses.get(i));
				}

			} else {

				result = new ArrayList<String>();
				combinations(validCourses.size(), ds.getMaxCoursePerSem());
				ArrayList<ArrayList<Course>> resultStr = numberResultToNameResult(
						result, validCourses);

				for (int i = 0; i < resultStr.size(); i++) {

					for (int j = 0; j < resultStr.get(i).size(); j++) {
						ds.addFinishedCourse(resultStr.get(i).get(j));
					}
					sem = sem + 1;

					chooseCoursesForNextSemester(ds, sem);
					sem = sem - 1;
					for (int j = 0; j < resultStr.get(i).size(); j++) {
						ds.addLeftCourse(resultStr.get(i).get(j));
					}

				}

			}
		}
	}

	public static ArrayList<Course> getValidCourses(ArrayList<Course> cf,
			ArrayList<Course> cl, int numOfSem) {

		ArrayList<Course> tempValid = new ArrayList<Course>();
		ArrayList<Course> valid = new ArrayList<Course>();
		char sem = ' ';

		if (numOfSem % 2 == 0) {
			sem = 'F';
		} else {
			sem = 'S';
		}

		for (Course c : cl) {
			if (c.getSemester() == sem || c.getSemester() == 'B') {
				tempValid.add(c);
			}
		}

		for (int i = 0; i < tempValid.size(); i++) {
			if (preFinished(cf, tempValid.get(i))) {
				valid.add(tempValid.get(i));
			}
		}

		return valid;

	}

	public static boolean preFinished(ArrayList<Course> cf, Course c) {
		int counter = 0;
		ArrayList<String> preList = c.getPreCourses();
		if (preList.size() > cf.size()) {
			return false;
		}
		for (int i = 0; i < preList.size(); i++) {
			String pre = preList.get(i);

			for (Course cTemp : cf) {
				if (cTemp.getCourseName().equals(pre)) {
					counter++;
				}
			}

			if (counter == 0) {
				return false;
			} else {
				counter = 0;
			}
		}

		return true;
	}
	
	public static void combinations(int n, int k) {
		int[] temp = new int[k];
		generate(temp, 0, 0, k, n);
	}

	public static void generate(int[] s, int position, int nextInt, int k, int n) {
		if (position == k) {
			String temp = "";
			for (int i = 0; i < s.length - 1; i++) {
				temp = temp + s[i] + " ";
			}

			temp = temp + s[s.length - 1];

			result.add(temp);
			return;
		}
		for (int i = nextInt; i < n; i++) {
			s[position] = i;
			generate(s, position + 1, i + 1, k, n);
		}
	}

	public static ArrayList<ArrayList<Course>> numberResultToNameResult(
			ArrayList<String> numResult, ArrayList<Course> vCourse) {
		ArrayList<ArrayList<Course>> tempResult = new ArrayList<ArrayList<Course>>();

		for (int j = 0; j < numResult.size(); j++) {
			String[] arr = numResult.get(j).split(" ");
			tempResult.add(new ArrayList<Course>());
			for (int i = 0; i < arr.length; i++) {
				tempResult.get(j).add(vCourse.get(Integer.valueOf(arr[i])));
			}

		}

		return tempResult;
	}

}

