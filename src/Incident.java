public class Incident {
    private int id;
    private String location;
    private String emergencyType;
    private String status;
    private String createdAt;

    // Constructor
    public Incident(int id, String location, String emergencyType, String status, String createdAt) {
        this.id = id;
        this.location = location;
        this.emergencyType = emergencyType;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters
    public int getId() { return id; }
    public String getLocation() { return location; }
    public String getEmergencyType() { return emergencyType; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
}