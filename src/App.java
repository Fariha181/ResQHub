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
            System.out.println("3. Search Incidents (Filter)");
            System.out.println("4. Update Incident Status");
            System.out.println("5. Delete Incident");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    printList(dao.getAllIncidents());
                    break;

                case 2:
                    System.out.print("\nEnter Location: ");
                    String location = scanner.nextLine().trim();
                    System.out.print("Enter Emergency Type: ");
                    String type = scanner.nextLine().trim();

                    if (location.isEmpty() || type.isEmpty()) {
                        System.out.println(" ERROR: Location and Type cannot be empty!");
                    } else if (dao.addIncident(location, type)) {
                        System.out.println(" SUCCESS: Incident reported successfully!");
                    } else {
                        System.out.println(" ERROR: Failed to report incident.");
                    }
                    break;

                case 3:
                    System.out.print("\nEnter keyword to search (Location / Type / Status): ");
                    String keyword = scanner.nextLine().trim();
                    printList(dao.searchIncidents(keyword));
                    break;

                case 4:
                    System.out.print("\nEnter Incident ID to Update: ");
                    int updateId = getIntInput(scanner);

                    if (!dao.existsById(updateId)) {
                        System.out.println(" ERROR: Incident ID " + updateId + " does not exist!");
                        break;
                    }

                    System.out.print("Enter New Status (Pending / In Progress / Resolved): ");
                    String newStatus = scanner.nextLine().trim();

                    if (dao.updateStatus(updateId, newStatus)) {
                        System.out.println(" SUCCESS: Status updated successfully!");
                    } else {
                        System.out.println(" ERROR: Failed to update status.");
                    }
                    break;

                case 5:
                    System.out.print("\nEnter Incident ID to Delete: ");
                    int deleteId = getIntInput(scanner);

                    if (!dao.existsById(deleteId)) {
                        System.out.println(" ERROR: Incident ID " + deleteId + " does not exist!");
                        break;
                    }

                    if (dao.deleteIncident(deleteId)) {
                        System.out.println(" SUCCESS: Incident deleted successfully!");
                    } else {
                        System.out.println(" ERROR: Failed to delete incident.");
                    }
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

    // Helper: Validates numeric integer inputs safely
    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print(" Invalid input! Please enter a valid number: ");
            scanner.next();
        }
        int number = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return number;
    }

    // Helper: Prints incident details neatly
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