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

                // ১. UPDATE: ID = 1 এর Status 'Pending' থেকে 'Resolved' করা
                String updateSQL = "UPDATE incidents SET status = ? WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
                updateStmt.setString(1, "Resolved");
                updateStmt.setInt(2, 1);
                
                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println(">>> SUCCESS: Incident ID 1 Status Updated to 'Resolved'!");
                }

                // ২. DELETE: উদাহরণ হিসেবে নির্দিষ্ট ID মুছে ফেলা (প্রয়োজন হলে নিচে আইডি পরিবর্তন করতে পারেন)
                // String deleteSQL = "DELETE FROM incidents WHERE id = ?";
                // PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);
                // deleteStmt.setInt(1, 2); // ID 2 ডিলিট করবে
                // deleteStmt.executeUpdate();

                // ৩. VIEW ALL: হালনাগাদ ডাটাবেসের তালিকা দেখানো
                printAllIncidents(conn);

                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ডাটা প্রদর্শনের জন্য হেলপার মেথড
    private static void printAllIncidents(Connection conn) throws Exception {
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
    }
}