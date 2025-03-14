package Controller;
import java.util.*;

import Model.MedicalRecord;
import Model.User;
import View.*;


public class AdministratorMenuHandler implements MenuHandler {

    private final Menu HospitalStaffMenu;

    //constructor
    public AdministratorMenuHandler(Menu HospitalStaffMenu)
    {
        this.HospitalStaffMenu = HospitalStaffMenu;
    }


    @Override
    public void displayMenu()
    {
        System.out.println("Administrator Menu");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View appointment details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Update Medical Record");
        System.out.println("6. Log out");
    }

    public void handleMenuOption(int choice, User user)
    {
        Scanner scanner = new Scanner(System.in);
        IStaffController staffController = new StaffController();
        IAppointmentController appointmentController = new AppointmentController();
        ReplenishmentController replenishmentController = new ReplenishmentController();
        Menu pendingMenu = new PendingMenu();
        switch(choice)
        {
            case 1:
                while(true)
                {
                    HospitalStaffMenu.printMenu();
                    System.out.print("Enter your choice: ");
                    int staffChoice = scanner.nextInt();
                    switch (staffChoice) {
                        case 1:staffController.viewHospitalStaff();
                            System.out.println();
                            break;

                        case 2:staffController.addStaffMember();
                            System.out.println();
                            break;

                        case 3:staffController.updateStaffMember();
                            System.out.println();
                            break;

                        case 4:staffController.removeStaffMember();
                            System.out.println();
                            break;

                        case 5:
                            break;

                        default:
                            System.out.println("Invalid option. Please try again.");
                            continue;
                    }
                    if (staffChoice == 5) {
                        break; // 
                    }
                }
                break;
            case 2:

            ViewAllPatients patients = new ViewAllPatients();
            boolean hasPatients = patients.printAllPatients();
            if (!hasPatients) {
                System.out.println("No appointment details.");
                break;
            }
            Scanner s = new Scanner(System.in);
            while (true) {
                System.out.println("Enter the patient name to view the appointment details (or enter 1 to exit):");
                String patientName = s.next();
                if (patientName.equals("1")) {
                    break;
                }
                appointmentController.viewAppointmentsDetails(patientName);
            }
            break;
            case 3:
            while (true) {
                Menu mediciMenu = new MedicineInventoryMenu();
                mediciMenu.printMenu();
                System.out.print("Enter your choice: ");
                int Choice = scanner.nextInt();
                switch (Choice) {
                    case 1:
                        PrescriptionHandler prescriptionHandler = new PrescriptionServiceHandler();
                        prescriptionHandler.viewInventory();
                        System.out.println();
                        break;
                    case 2:
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Enter the medication name:");
                        String medicationName = sc.next();
                        IAdminUpdateInven adminUpdate = new AdminUpdateInven(medicationName);
                        adminUpdate.updateInventory();
                        System.out.println();

                        break;
                    case 3:
                        break; // Break out of the inner switch case
                    default:
                        System.out.println("Invalid option. Please try again.");
                        continue;
                }
                if (Choice == 3) {
                    break; // Break out of the while loop
                }
            }
            break;
            case 4:

                if (replenishmentController.printPendingRequests()) {
                    replenishmentController.approveReplenishmentRequest();
                }
                System.out.println();
                break;
            case 5:
                System.out.println("Enter the patient to De-active");
                String patientID = scanner.next();
                MedicalRecord record = CSVReader.findMedicalRecordByPatientID("External Data/MedicalRecord.csv", patientID);
                IUpdate adminUpdate = new AdminUpdateMed();
                UpdateController controller = new UpdateController();
                controller.update(adminUpdate, record);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
               
        }
    }
    
}
