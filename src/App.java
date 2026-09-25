import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        IncidentController controller = new IncidentController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("     *** Welcome to ResQHub System ***    ");
            System.out.println("========================================");
            System.out.println("1. View All Incidents");
            System.out.println("2. Report New Incident (Create)");
            System.out.println("3. Search Incidents (Filter)");
            System.out.println("4. Update Incident Status");
            System.out.println("5. Delete Incident");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    printList(controller.getAllIncidents());
                    break;

                case 2:
                    System.out.print("\nEnter Location: ");
                    String location = scanner.nextLine().trim();
                    System.out.print("Enter Emergency Type: ");
                    String type = scanner.nextLine().trim();

                    // Creating Request Object to pass data into Controller
                    IncidentRequest createReq = new IncidentRequest(location, type);
                    String createResponse = controller.createIncident(createReq);
                    System.out.println(createResponse);
                    break;

                case 3:
                    System.out.print("\nEnter keyword to search (Location / Type / Status): ");
                    String keyword = scanner.nextLine().trim();
                    printList(controller.searchIncidents(keyword));
                    break;

                case 4:
                    System.out.print("\nEnter Incident ID to Update: ");
                    int updateId = getIntInput(scanner);

                    System.out.print("Enter New Status (Pending / In Progress / Resolved): ");
                    String newStatus = scanner.nextLine().trim();

                    // Creating Request Object for Update
                    IncidentRequest updateReq = new IncidentRequest(newStatus);
                    String updateResponse = controller.updateIncidentStatus(updateId, updateReq);
                    System.out.println(updateResponse);
                    break;

                case 5:
                    System.out.print("\nEnter Incident ID to Delete: ");
                    int deleteId = getIntInput(scanner);

                    String deleteResponse = controller.deleteIncident(deleteId);
                    System.out.println(deleteResponse);
                    break;

                case 6:
                    System.out.println("Exiting ResQHub. Stay Safe!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println(" Invalid option! Please select between 1 and 6.");
            }
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print(" Invalid input! Please enter a valid number: ");
            scanner.next();
        }
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    private static void printList(List<Incident> list) {
        System.out.println("\n--- INCIDENT LIST ---");
        if (list.isEmpty()) {
            System.out.println("No matching incidents found.");
        } else {
            for (Incident inc : list) {
                System.out.println("ID: " + inc.getId() + 
                                   " | Location: " + inc.getLocation() + 
                                   " | Type: " + inc.getEmergencyType() + 
                                   " | Status: " + inc.getStatus() + 
                                   " | Time: " + inc.getCreatedAt());
            }
        }
    }
}