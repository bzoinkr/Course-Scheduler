import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author natha
 */
public class ClassQueries {
    
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getClassList;
    private static PreparedStatement getSeats;
    private static PreparedStatement dropClass;

    private static ResultSet resultSet;
    
    public static void addClass(ClassEntry Class)
    {
        connection = DBConnection.getConnection();
        try
        {
            addClass = connection.prepareStatement("insert into app.Class (coursecode,semester,seats) values (?,?,?)");
            addClass.setString(1, Class.getCourseCode());
            addClass.setString(2, Class.getSemester());
            addClass.setInt(3, Class.getSeats());

            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static void dropClass(String semester,String courseCode)
    {
        connection = DBConnection.getConnection();
        try
        {
            dropClass = connection.prepareStatement("DELETE FROM app.Class WHERE semester = ? AND coursecode = ?");
            dropClass.setString(1, semester);
            dropClass.setString(2, courseCode);

            dropClass.executeUpdate(); 
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> course = new ArrayList<String>();
        try
        {
            getClassList = connection.prepareStatement("select coursecode from app.class where semester = ? order by coursecode");
            getClassList.setString(1, semester);
            
            resultSet = getClassList.executeQuery();
            
            while(resultSet.next())
            {
                course.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return course;
        
    }
    
    public static int getClassSeats(String semester, String courseCode){
        
        connection = DBConnection.getConnection();
        try
        {
            getSeats = connection.prepareStatement("select seats from app.class where semester = ? and coursecode = ?");
            getSeats.setString(1, semester);
            getSeats.setString(2, courseCode);

            resultSet = getSeats.executeQuery();
            
            if (resultSet.next()) {
                
                return resultSet.getInt(1);

            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return -1;
        
    }
    

}
