/* 
Chloe Bower, student/12162700, 21 April 2023
RockyCollege.java
A program for Unit Coordinators at
Rocky College to process student
results and display statistics
*/

import java.util.Scanner; // import scanner class

public class RockyCollege {
    //main method begins execution of the Java program
    public static void main(String[] args){

        //initialize totals 
        double total = 0; 
        double rebate = 0; 
        double rTotal = 0;
        double studentMark = 0;
        double minimum = 100;
        double maximum = 0;
        String studentName;
        String unitCode;
        String minStudent = "";
        String maxStudent = "";
        int resultCounter = 0;

        System.out.printf("Welcome to the Rocky College Management System%n%n");
        Scanner inputUnit = new Scanner(System.in); // declare first scanner
        System.out.print("Enter Unit Code: "); //prompt for unit code
        unitCode = inputUnit.nextLine(); // read unit code
            
        while (unitCode == ""){ //continuously prompt while unit code is empty
            System.out.printf("ERROR Unit Code can not be empty%n");
            System.out.print("Enter Unit Code: "); //prompt for unit code
            unitCode = inputUnit.nextLine(); // read unit code
            }
        
        //loop n times
        for (int n = 1; n <= 7; n++){

            Scanner inputStudent = new Scanner(System.in); //declare second scanner
            System.out.printf("%nEnter student name %s: ", n); //prompt for student name
            studentName = inputStudent.nextLine(); //read student name  
                while (studentName == ""){ //continuously prompt while student name is empty
                    System.out.printf("ERROR Student name can not be blank%n");
                    System.out.printf("%nEnter student name %s: ", n); //prompt for student name
                    studentName = inputStudent.nextLine(); //read student name
                    } 

            Scanner inputMark = new Scanner(System.in); //declare third scanner
            System.out.printf("Enter %s's mark: ", studentName); //prompt for student mark
            studentMark = inputMark.nextDouble(); //read student mark
                while (studentMark <0 || studentMark >100){ //continually prompt if mark is impossible
                    System.out.printf("ERROR Student mark must be between 0.00 and 100.00%n");
                    System.out.printf("Enter %s's mark: ", studentName); //prompt for student mark
                    studentMark = inputMark.nextDouble(); //read student mark  
                     }  

            if (studentMark < minimum){
                minimum = (minimum - minimum + studentMark); //calculate minimum marks
                minStudent = studentName; //store student name
            }

            if (studentMark > maximum){
                maximum = (maximum - maximum + studentMark); //calculate maximum marks
                maxStudent = studentName; //store student name
            }
 
            total += studentMark; //add marks together
            rTotal += rebate; //calculate total rebates
            resultCounter++; //calculate number of results

                if (studentMark >= 85){
                    rebate = ((2000/100)*10); //calculate rebate
                    System.out.printf("%nThe grade for %s with a mark of %.2f is a HD %nThe rebate for %s is $%.2f%n", studentName, studentMark, studentName, rebate);
                }
                    else if (studentMark >= 75){
                        rebate = ((2000/100)*5); //calculate rebate
                        System.out.printf("%nThe grade for %s with a mark of %.2f is a D %nThe rebate for %s is $%.2f%n", studentName, studentMark, studentName, rebate);
                    }
                    else if (studentMark >= 65){
                        rebate = ((2000/100)*2); //calculate rebate
                        System.out.printf("%nThe grade for %s with a mark of %.2f is a C %nThe rebate for %s is $%.2f%n", studentName, studentMark, studentName, rebate);
                    }
                    else if (studentMark >= 50){
                        rebate = 0;
                        System.out.printf("%nThe grade for %s with a mark of %.2f is a P %nThere is no rebate for %s%n", studentName, studentMark, studentName);
                    }
                    else{
                        rebate = 0;
                        System.out.printf("%nThe grade for %s with a mark of %.2f is an F %nThere is no rebate for %s%n", studentName, studentMark, studentName);
                    }        
        } //close for loop
            if (resultCounter != 0){
                rTotal += rebate; //add final rebate to the total
                double average = (double) total / resultCounter; //calculate average mark
                System.out.printf("%nStatistical information for %s%n%n", unitCode);
                System.out.printf("%s has the minimum marks with %.2f%n marks", minStudent, minimum);
                System.out.printf("%s has the maximum marks with %.2f%n marks", maxStudent, maximum);
                System.out.printf("The average number of marks per student is: %.2f%nThe total rebates given is $%.2f%n", average, rTotal);
                System.out.printf("%nThank you for using the Rocky College Management System%nProgram written by 12162700");
            }   
    }
}
