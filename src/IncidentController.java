import java.util.List;

public class IncidentController {
    private IncidentDAO dao;

    public IncidentController() {
        this.dao = new IncidentDAO();
    }

    // Handle Create Incident using Request Object
    public String createIncident(IncidentRequest request) {
        if (request.getLocation() == null || request.getLocation().trim().isEmpty() ||
            request.getEmergencyType() == null || request.getEmergencyType().trim().isEmpty()) {
            return "ERROR: Location and Emergency Type cannot be empty!";
        }
        
        boolean success = dao.addIncident(request.getLocation(), request.getEmergencyType());
        return success ? "SUCCESS: Incident reported successfully!" : "ERROR: Failed to report incident.";
    }

    // Handle Fetch All
    public List<Incident> getAllIncidents() {
        return dao.getAllIncidents();
    }

    // Handle Search Filter
    public List<Incident> searchIncidents(String keyword) {
        return dao.searchIncidents(keyword);
    }

    // Handle Status Update using Request Object
    public String updateIncidentStatus(int id, IncidentRequest request) {
        if (!dao.existsById(id)) {
            return "ERROR: Incident ID " + id + " does not exist!";
        }
        
        boolean success = dao.updateStatus(id, request.getStatus());
        return success ? "SUCCESS: Status updated successfully!" : "ERROR: Failed to update status.";
    }

    // Handle Delete Incident
    public String deleteIncident(int id) {
        if (!dao.existsById(id)) {
            return "ERROR: Incident ID " + id + " does not exist!";
        }

        boolean success = dao.deleteIncident(id);
        return success ? "SUCCESS: Incident deleted successfully!" : "ERROR: Failed to delete incident.";
    }
}