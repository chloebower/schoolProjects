public class Student {
    private String studentName; // instance variable
    private int studentID; //instance variable
    private double studentMark;//instance variable

    public Student(){
        //default constructor
    }

    public Student(String name, int id, double mark){
        this.studentName = name;
        this.studentID = id;
        this.studentMark = mark;
    }

    public String getStudentName(){
        return studentName;
    }
    public void setStudentName(String name){
        this.studentName = name;
    }

    public int getStudentID(){
        return studentID;
    }
    public void setStudentID(int id){
        this.studentID = id;
    }

    public double getStudentMark(){
        return studentMark;
    }
    public void setStudentMark(double mark){
        this.studentMark = mark;
    }

    public String calculateGrade(){
        if (studentMark >= 85){
            return "HD";          
        }
            else if (studentMark >= 75){
                return "D";
            }
            else if (studentMark >= 65){
                return "C";
            }
            else if (studentMark >= 50){
                return "P";
            }
            else {
                return "F";
            }
    }

    public double calculateRebate(){
        double chargePerUnit = 2000.;
        if (calculateGrade().equals("HD")){
            return (chargePerUnit/100)*10;
        }
            else if (calculateGrade().equals("D")){
                return (chargePerUnit/100)*5;
            }
            else if (calculateGrade().equals("C")){
                return (chargePerUnit/100)*2;
            }
            else{
                return 0;
            }
    }
}
