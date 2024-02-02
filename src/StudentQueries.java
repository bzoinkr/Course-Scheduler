import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author natha
 */
public class StudentQueries {
    
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static PreparedStatement getAllStudents;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        
        try
        {
            addStudent = connection.prepareStatement("insert into app.Student (studentid,firstname,lastname) values (?,?,?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());

            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static void dropStudent(String StudentID){
        
        connection = DBConnection.getConnection();
        
        try
        {
            dropStudent = connection.prepareStatement("DELETE FROM app.student WHERE studentid = ? ");
            dropStudent.setString(1, StudentID);

            dropStudent.executeUpdate(); 
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static StudentEntry getStudent(String StudentID){
        
       try 
       {          
            connection = DBConnection.getConnection();
            getStudent = connection.prepareStatement("SELECT * FROM app.student WHERE studentid = ?");
            getStudent.setString(1, StudentID);

            resultSet = getStudent.executeQuery();

            if (resultSet.next()) 
            {
                String first = resultSet.getString("firstname");
                String last = resultSet.getString("lastname");
                return new StudentEntry(StudentID,first,last);   
            }
        } 
       catch (SQLException sqlException) 
       {
        sqlException.printStackTrace();
       } 
        
        return null;
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<>();
        
        try 
        {
            connection = DBConnection.getConnection();
            getAllStudents = connection.prepareStatement("SELECT * FROM app.student");

            resultSet = getAllStudents.executeQuery();
            while (resultSet.next()) {
                String studentID = resultSet.getString("studentid"); 
                String firstName = resultSet.getString("firstName");  
                String lastName = resultSet.getString("lastName");   
                students.add(new StudentEntry(studentID, firstName, lastName));
            }
        }   
        catch (SQLException sqlException) 
        {
            sqlException.printStackTrace();
        }
        return students;
        
    }
    

}
