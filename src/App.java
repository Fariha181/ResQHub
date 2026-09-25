import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/resqhub_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (conn != null) {
                System.out.println("SUCCESS: Connected to ResQHub Database!");

                String insertSQL = "INSERT INTO incidents (location, emergency_type) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(insertSQL);
                stmt.setString(1, "Mirpur 10, Dhaka");
                stmt.setString(2, "Medical Emergency");
                
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Data successfully inserted from Java!");
                }

                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}