/* 
Chloe Bower, student/12162700, 19 May 2023
RockyCollegeMenu.java
A program for Unit Coordinators at
Rocky College to process student
results and display statistics
*/

import java.util.Scanner;

public class RockyCollegeMenu extends Student
{
	//constants
	final int ENTER_STUDENT = 1;
	final int DISPLAY_STUDENTS = 2;
	final int DISPLAY_STATISTICS = 3;
	final int SEARCH_STUDENTS = 4;
	final int SORT_STUDENTS = 5;
	final int EXIT = 6;
	private final int MAX_STUDENTS = 10;
	String unitCode;

	//array of Student objects
	private Student[] students = new Student[MAX_STUDENTS];
	//variable for the current student entered
	private int numStudents = 0;

	static Scanner scanner = new Scanner(System.in);

	public String enterUnitCode;{
	System.out.print("Enter the unit code: ");
	String unitCode = scanner.nextLine();
	}

	public String getUnitCode(){
        return unitCode;
    }
	public void setUnitCode(String code){
        this.unitCode = code;
    }

	Scanner inputMenuChoice = new Scanner(System.in);

	private int getMenuItem()
	{
		System.out.println("\nPlease select from the following");
		System.out.println(ENTER_STUDENT + ". Enter student name, student ID and mark");
		System.out.println(DISPLAY_STUDENTS + ". Display all student names, IDs, marks, grades and rebates");
		System.out.println(DISPLAY_STATISTICS + ". Display Statistics");
		System.out.println(SEARCH_STUDENTS + ". Search for student by ID");
		System.out.println(SORT_STUDENTS + ". Sort the students by name");
		System.out.println(EXIT + ". Exit the application");
		System.out.print("Enter choice==> ");

		String choice = inputMenuChoice.nextLine();
		while (choice.equals("") || !isStringNumeric(choice))
		{
			System.out.println("Error - Menu selection name cannot be blank and must be numeric");

			System.out.print("Enter choice==> ");

			choice = inputMenuChoice.nextLine();
		}

		return Integer.parseInt(choice);
	}

	private boolean isStringNumeric(String str)
	{
		for (int i = 0; i < str.length(); i++)
		{
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}

		return true;
	}

	private void processStudents()
	{
		int choice = getMenuItem();
		while (choice != EXIT)
		{
			switch (choice)
			{
				case ENTER_STUDENT:
					enterStudent();
					break;
				case DISPLAY_STUDENTS:
					displayAllStudents();
					break;
				case DISPLAY_STATISTICS:
					displayStatistics();
					break;
				case SEARCH_STUDENTS:
					searchStudents();
					break;
				case SORT_STUDENTS:
					sortStudents();
					break;
				default:
					System.out.println("ERROR - choice not recognised");
			}
			choice = getMenuItem();		
		}
		if (choice == EXIT){ //exit message
				System.out.println("Thank you for using the Rocky College Management System");
				System.out.println("Program created by 12162700");
			}
	}

	
	private void enterStudent()
	{
		//check if maximum students has been reached
		if (numStudents >= MAX_STUDENTS){
			System.out.println("Maximum number of students reached.");
		}
		//read in the student name
		System.out.print("Enter student name: ");
		String name = scanner.nextLine();
		//validation loop
		while (name == ""){
			System.out.print("ERROR student name cannot be blank.");
			System.out.print("Enter student name: "); //prompt for student name
            name = scanner.nextLine(); //read student name
		}

		//read in the student ID
		System.out.print("Enter the student ID: ");
		int id = scanner.nextInt();
		//validation loop
		while (id == 0){
			System.out.print("ERROR student id cannot be blank.");
			System.out.print("Enter"+name+"'s id: "); //prompt for student id
            id = scanner.nextInt(); //read student id
		}

		//read in the mark
		System.out.print("Enter the student mark: ");
		double mark = scanner.nextDouble();
		scanner.nextLine();
		//validation loop
		while (mark <0 || mark >100){ //continually prompt if mark is impossible
			System.out.printf("ERROR Student mark must be between 0.00 and 100.00%n");
			System.out.print("Enter"+name+"'s mark: "); //prompt for student mark
			mark = scanner.nextDouble(); //read student mark  
			 }
		// adds the data to the array
		Student student = new Student(name, id, mark);
		
		displayHeading();

		//displays the student name, student ID, mark, grade and the rebate
		System.out.printf("%-30s%-12d%-11.2f%-6s$%5.2f\n",
                student.getStudentName(), student.getStudentID(), student.getStudentMark(), 
				student.calculateGrade(), student.calculateRebate());
		//increments the current student variable for the next entry
		students[numStudents] = student;
		numStudents++;
	}


	private void displayHeading()
	{
		System.out.printf("%-30s%-12s%-11s%-6s%-6s\n", "Student Name", "Student ID", "Mark", "Grade", "Rebate");
	}


	private void displayAllStudents()
	{
		//checks if there has been a student entered
		if (numStudents == 0){
			System.out.println("No students have been entered yet.");
		}
		//displays all of the entries entered so far
		displayHeading();
		for (int i = 0; i < numStudents; i++){
            Student student = students[i];
            System.out.printf("%-30s%-12d%-11.2f%-6s$%5.2f\n",
                    student.getStudentName(), student.getStudentID(), student.getStudentMark(),
                    student.calculateGrade(), student.calculateRebate());
        }
	}


	private void displayStatistics()
	{
		//checks if there has been an student entered
		if (numStudents == 0){
			System.out.println("No students have been entered yet.");
		}
		
		double totalMarks = 0;
		double totalRebate = 0;

		String highestStudent = students[0].getStudentName();
		String lowestStudent = students[0].getStudentName();
		String code = getUnitCode();

		double highestMark = students[0].getStudentMark();
		double lowestMark = students[0].getStudentMark();

		//loops though the current entries in the array to calculate and display the statistics
		for (int i = 0; i < numStudents; i++) {
            Student student = students[i];
            double mark = student.getStudentMark();
            totalMarks += mark;
			double rebate = student.calculateRebate();
			totalRebate += rebate;
		      
            if (mark > highestMark) {
                highestMark = mark;
				String name = student.getStudentName();
				highestStudent = name;
            }
            
            if (mark < lowestMark) {
                lowestMark = mark;
				String name = student.getStudentName();
				lowestStudent = name;
            }

			double averageMark = (totalMarks/numStudents);
		
		System.out.println("\nStatistics for "+code+":");
        System.out.println(""+highestStudent+"has the highest mark: " + highestMark);
        System.out.println(""+lowestStudent+"has the lowest mark: " + lowestMark);
        System.out.println("Average marks par student: " + averageMark);
		System.out.println("Total Rebates: " + totalRebate);
		}
	}


	private void searchStudents()
	{
		//checks if there has been a student entered
		if (numStudents == 0){
			System.out.println("No students have been entered yet.");
		}
		//reads the search key (student ID)
		System.out.print("Enter the student's id: ");
		int searchID = scanner.nextInt();
		scanner.nextLine();
		// TODO -- loop though the current entries in the array to search for the search key
		for (int i = 0; i < numStudents; i++) {
            Student student = students[i];
            if (student.getStudentID() == searchID) {                 
      
		//display the found item or report it has not been found
		displayHeading();
		System.out.printf("%-30s%-12d%-11.2f%-6s$%5.2f\n", student.getStudentName(), student.getStudentID(), student.getStudentMark(),
		student.calculateGrade(), student.calculateRebate());			
			}
			else{
				System.out.println(""+searchID+" was not found.");
			}
		}	
	}

	private void sortStudents()
	{
		//checks if there has been two students entered
		if (numStudents <2 ){
			System.out.print("At least two students must be entered in order to sort.");
		}
		// TODO -- sort the entries based on the student name (case insensitve)
		/**for (int i = 0; i < numStudents - 1; i++){
			for (int x = 0; x < numStudents - i - 1; x++){
				String name1 = students[x].getStudentName().toLowerCase(null);
				String name2 = students[x + 1].getStudentName().toLowerCase(null);
			}
		}**/
		//display the sorted list
		System.out.println("Students sorted by name: ");
		displayHeading();
	}


	public static void main(String [] args)
	{
		RockyCollegeMenu app = new RockyCollegeMenu();

		// TODO -- read in the unit code (this should be a separate method)

		app.processStudents();
	}
}