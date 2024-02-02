import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author natha
 */
public class ScheduleQueries {
    
    private static Connection connection;
    private static PreparedStatement addSchedule;
    private static PreparedStatement getSchedules;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getWaitlisted;
    private static PreparedStatement dropStudentSchedule;
    private static PreparedStatement dropSchedule;
    private static PreparedStatement update;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();        
        try
        {
            addSchedule = connection.prepareStatement("insert into app.schedule (semester,coursecode,studentid,status,timestamp) values (?,?,?,?,?)");
            addSchedule.setString(1, entry.getSemester());
            addSchedule.setString(2, entry.getCourseCode());
            addSchedule.setString(3, entry.getStudentID());
            addSchedule.setString(4, entry.getStatus());
            addSchedule.setTimestamp(5, entry.getCurrentTimestamp());


            addSchedule.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }   
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select * from app.schedule where semester = ? and studentid = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                schedule.add(new ScheduleEntry(resultSet.getString("semester"),resultSet.getString("courseCode"),resultSet.getString("studentID"),resultSet.getString("status")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;
        
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode){
        
        int count = 0;
        
        connection = DBConnection.getConnection();
        
        try
        {
            getSchedules = connection.prepareStatement("select count(studentID) from app.schedule where semester = ? and courseCode = ?");
            getSchedules.setString(1, currentSemester);
            getSchedules.setString(2, courseCode);
            resultSet = getSchedules.executeQuery();
            while(resultSet.next()){
            count = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;

        
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String semester, String courseCode)
    {
        
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> students = new ArrayList<ScheduleEntry>();
        try
        {
            getWaitlisted = connection.prepareStatement("select * from app.schedule where semester = ? and coursecode = ? AND status = 'Waitlisted' ORDER BY timestamp ASC");
            getWaitlisted.setString(1, semester);
            getWaitlisted.setString(2, courseCode);            
            
            resultSet = getWaitlisted.executeQuery();
            
            while(resultSet.next())
            {
                students.add(new ScheduleEntry(resultSet.getString("semester"),resultSet.getString("courseCode"),resultSet.getString("studentID"),resultSet.getString("status")));
            }
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return students;
        
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode)
    {
        
        connection = DBConnection.getConnection();
        try
        {
            dropStudentSchedule = connection.prepareStatement("DELETE FROM app.schedule WHERE semester = ? AND studentID = ? AND coursecode = ?");
            dropStudentSchedule.setString(1, semester);
            dropStudentSchedule.setString(2, studentID);
            dropStudentSchedule.setString(3, courseCode);

            dropStudentSchedule.executeUpdate();
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        try
        {
            dropSchedule = connection.prepareStatement("DELETE FROM app.schedule WHERE semester = ? AND coursecode = ?");
            dropSchedule.setString(1, semester);
            dropSchedule.setString(2, courseCode);

            dropSchedule.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
   
    public static void updateScheduleEntry(ScheduleEntry entry)
    {
        
        connection = DBConnection.getConnection();        
        try
        {
            update = connection.prepareStatement("UPDATE app.Schedule SET Status = ?, Timestamp = ? WHERE Semester = ? AND CourseCode = ? AND StudentID = ?");
            update.setString(1, "Scheduled");
            update.setTimestamp(2, entry.getCurrentTimestamp());
            update.setString(3, entry.getSemester());
            update.setString(4, entry.getCourseCode());
            update.setString(5, entry.getStudentID());

            update.executeUpdate();
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }

}
