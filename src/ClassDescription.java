/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natha
 */
public class ClassDescription {
    
    private String courseCode;
    private String Description;
    private int seats;

    public ClassDescription(String courseCode, String Description, int seats) {
        this.courseCode = courseCode;
        this.Description = Description;
        this.seats = seats;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDescription() {
        return Description;
    }

    public int getSeats() {
        return seats;
    }
    
}
