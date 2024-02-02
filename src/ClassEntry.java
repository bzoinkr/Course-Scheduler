/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natha
 */
public class ClassEntry {
    
    private String Semester;
    private String courseCode;
    private int seats;

    public ClassEntry(String Semester, String courseCode, int seats) {
        this.Semester = Semester;
        this.courseCode = courseCode;
        this.seats = seats;
    }

    public String getSemester() {
        return Semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getSeats() {
        return seats;
    }
    
}
