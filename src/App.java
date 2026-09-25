import java.util.List;

public class App {
    public static void main(String[] args) {
        IncidentDAO dao = new IncidentDAO();

        System.out.println("--- Adding New Incident ---");
        dao.addIncident("Gulshan 2, Dhaka", "Fire Rescue");

        System.out.println("\n--- Updating Status ---");
        dao.updateStatus(1, "Resolved");

        System.out.println("\n================ RESQHUB INCIDENTS LIST ================");
        List<Incident> incidents = dao.getAllIncidents();
        for (Incident inc : incidents) {
            System.out.println("ID: " + inc.getId() + 
                               " | Location: " + inc.getLocation() + 
                               " | Type: " + inc.getEmergencyType() + 
                               " | Status: " + inc.getStatus() + 
                               " | Time: " + inc.getCreatedAt());
        }
        System.out.println("========================================================\n");
    }
}