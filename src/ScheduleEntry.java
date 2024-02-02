import java.sql.Timestamp;
import java.util.Calendar;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author natha
 */
public class ScheduleEntry {
    
    private String Semester;
    private String courseCode;
    private String studentID;
    private String status;
    private java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

    public ScheduleEntry(String Semester, String courseCode, String studentID, String status) {
        this.Semester = Semester;
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.status = status;
    }

    public String getSemester() {
        
        return Semester;
    }

    public String getCourseCode() {
        
        return courseCode;
    }

    public String getStudentID() {
        
        return studentID;
    }

    public String getStatus() {
        
        return status;
    }

    public Timestamp getCurrentTimestamp() {
        
        return currentTimestamp;
    }
         
    @Override
    public String toString(){
        
        return courseCode;
        
    }
    
    
}
