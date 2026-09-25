import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/resqhub_db";
        String user = "root";
        String password = "";

        try {
            // Explicitly load the MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("✅ SUCCESS: Connected to ResQHub Database!");
            }
        } catch (Exception e) {
            System.out.println("❌ FAILED: Could not connect to database!");
            e.printStackTrace();
        }
    }
}