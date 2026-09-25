public class IncidentRequest {
    private String location;
    private String emergencyType;
    private String status;

    // Constructor for Creation
    public IncidentRequest(String location, String emergencyType) {
        this.location = location;
        this.emergencyType = emergencyType;
    }

    // Constructor for Status Update
    public IncidentRequest(String status) {
        this.status = status;
    }

    // Getters
    public String getLocation() { return location; }
    public String getEmergencyType() { return emergencyType; }
    public String getStatus() { return status; }
}
