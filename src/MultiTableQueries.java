/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author natha
 */
public class MultiTableQueries {
    
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement getAllClassDescriptions;
    private static PreparedStatement getScheduled;
    private static PreparedStatement getWaitlisted;
   
    private static ResultSet resultSet;
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> descriptions = new ArrayList<ClassDescription>();
        try
        {
            getAllClassDescriptions = connection.prepareStatement("select app.class.courseCode, description, seats from app.class, app.course where semester = ? and app.class.courseCode = app.course.courseCode order by app.class.courseCode");
            getAllClassDescriptions.setString(1,semester);
            resultSet = getAllClassDescriptions.executeQuery();
            
            while(resultSet.next())
            {
                descriptions.add(new ClassDescription(resultSet.getString("courseCode"),resultSet.getString("description"),resultSet.getInt("seats")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return descriptions;
        
    }
   
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode)
    {
        
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> scheduled = new ArrayList<StudentEntry>();
        
        try
        {
            getScheduled = connection.prepareStatement("SELECT * FROM app.Student WHERE StudentID IN (SELECT StudentID FROM app.Schedule WHERE Semester = ? AND CourseCode = ? AND Status = 'Scheduled')");
            getScheduled.setString(1,semester);
            getScheduled.setString(2,courseCode);
           
            resultSet = getScheduled.executeQuery();
            
            while(resultSet.next())
            {
                scheduled.add(new StudentEntry(resultSet.getString("studentid"),resultSet.getString("firstname"),resultSet.getString("lastname")));

            }
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduled;
        
    }
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode)
    {
        
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> waitlisted = new ArrayList<StudentEntry>();
        
        try
        {
            getWaitlisted = connection.prepareStatement("SELECT * FROM app.Student WHERE StudentID IN (SELECT StudentID FROM app.Schedule WHERE Semester = ? AND CourseCode = ? AND Status = 'Waitlisted')");
            getWaitlisted.setString(1,semester);
            getWaitlisted.setString(2,courseCode);

            resultSet = getWaitlisted.executeQuery();
            
            while(resultSet.next())
            {
                waitlisted.add(new StudentEntry(resultSet.getString("studentid"),resultSet.getString("firstname"),resultSet.getString("lastname")));
            }
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlisted;
        
    }
}