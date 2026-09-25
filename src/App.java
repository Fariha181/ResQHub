import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/resqhub_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (conn != null) {
                System.out.println("SUCCESS: Connected to ResQHub Database!\n");

                // ১. নতুন একটি টেস্ট ডাটা ইনসার্ট করা
                String insertSQL = "INSERT INTO incidents (location, emergency_type) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(insertSQL);
                stmt.setString(1, "Uttara Sector 3, Dhaka");
                stmt.setString(2, "Flood Relief");
                stmt.executeUpdate();
                System.out.println("--- New Data Inserted Successfully ---");

                // ২. ডাটাবেস থেকে সব ডাটা নিয়ে এসে প্রদর্শন করা (VIEW)
                String selectSQL = "SELECT * FROM incidents";
                Statement selectStmt = conn.createStatement();
                ResultSet rs = selectStmt.executeQuery(selectSQL);

                System.out.println("\n================ RESQHUB INCIDENTS LIST ================");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String location = rs.getString("location");
                    String type = rs.getString("emergency_type");
                    String status = rs.getString("status");
                    String time = rs.getString("created_at");

                    System.out.println("ID: " + id + " | Location: " + location + " | Type: " + type + " | Status: " + status + " | Time: " + time);
                }
                System.out.println("========================================================\n");

                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}