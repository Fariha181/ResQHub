import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        IncidentDAO dao = new IncidentDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("     *** Welcome to ResQHub System ***    ");
            System.out.println("========================================");
            System.out.println("1. View All Incidents");
            System.out.println("2. Report New Incident (Create)");
            System.out.println("3. Update Incident Status");
            System.out.println("4. Delete Incident");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n--- INCIDENT LIST ---");
                    List<Incident> list = dao.getAllIncidents();
                    if (list.isEmpty()) {
                        System.out.println("No incidents found.");
                    } else {
                        for (Incident inc : list) {
                            System.out.println("ID: " + inc.getId() + 
                                               " | Location: " + inc.getLocation() + 
                                               " | Type: " + inc.getEmergencyType() + 
                                               " | Status: " + inc.getStatus() + 
                                               " | Time: " + inc.getCreatedAt());
                        }
                    }
                    break;

                case 2:
                    System.out.print("\nEnter Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter Emergency Type: ");
                    String type = scanner.nextLine();

                    if (dao.addIncident(location, type)) {
                        System.out.println(" SUCCESS: Incident reported successfully!");
                    } else {
                        System.out.println(" ERROR: Failed to report incident.");
                    }
                    break;

                case 3:
                    System.out.print("\nEnter Incident ID to Update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter New Status (Pending/In Progress/Resolved): ");
                    String newStatus = scanner.nextLine();

                    if (dao.updateStatus(updateId, newStatus)) {
                        System.out.println(" SUCCESS: Status updated successfully!");
                    } else {
                        System.out.println(" ERROR: Failed to update status.");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter Incident ID to Delete: ");
                    int deleteId = scanner.nextInt();

                    if (dao.deleteIncident(deleteId)) {
                        System.out.println(" SUCCESS: Incident deleted successfully!");
                    } else {
                        System.out.println(" ERROR: Failed to delete incident.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting ResQHub. Stay Safe!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println(" Invalid option! Please select between 1 and 5.");
            }
        }
    }
}