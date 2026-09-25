import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IncidentDAO {

    // 1. CREATE: Add New Incident
    public boolean addIncident(String location, String emergencyType) {
        String sql = "INSERT INTO incidents (location, emergency_type) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, location);
            stmt.setString(2, emergencyType);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. READ: Get All Incidents
    public List<Incident> getAllIncidents() {
        List<Incident> list = new ArrayList<>();
        String sql = "SELECT * FROM incidents";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Incident(
                    rs.getInt("id"),
                    rs.getString("location"),
                    rs.getString("emergency_type"),
                    rs.getString("status"),
                    rs.getString("created_at")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. UPDATE: Update Incident Status
    public boolean updateStatus(int id, String newStatus) {
        String sql = "UPDATE incidents SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. DELETE: Remove Incident
    public boolean deleteIncident(int id) {
        String sql = "DELETE FROM incidents WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}