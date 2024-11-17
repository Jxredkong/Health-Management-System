package Model;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.CSVReader;
import Controller.MenuHandler;
import View.PatientMenu;
import Type.Gender;
import Type.Role;
import Controller.MenuHandler;

public class Patient extends User {

    //attributes
    private MedicalRecord medicalRecord;
    private ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private ArrayList<Appointment> appointments;
    private final MenuHandler menuHandler;

    //constructor

    public Patient(String userID, String userPass, Gender gender, String age, MenuHandler menuHandler){
        super(userID, userPass, Role.PATIENT, gender, age);
        this.medicalRecord = CSVReader.findMedicalRecordByPatientID("External Data/MedicalRecord.csv", userID);
        this.appointmentOutcomeRecords = CSVReader.getAppointmentOutcomeRecords("External Data/AppointmentOutcomeRecord.csv", userID);
        this.appointments = CSVReader.getAppointments("External Data/Appointments.csv", userID);
        this.menuHandler = menuHandler;
    }

    //getters and setters
    public MedicalRecord getMedicalRecord(){
        return this.medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecord = medicalRecord;
    }

    public ArrayList<AppointmentOutcomeRecord> getAppointmentOutcomeRecords(){
        return this.appointmentOutcomeRecords;
    }

    public void setAppointmentOutcomeRecords(ArrayList<AppointmentOutcomeRecord> appointmentOutcomeRecords){
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
    }

    public ArrayList<Appointment> getAppointments(){
        return this.appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments){
        this.appointments = appointments;
    }

    public void runModule() {
        boolean exit = false;
        while (!exit) {
            menuHandler.displayMenu();
            System.out.print("Enter your choice: ");
            int choice = new Scanner(System.in).nextInt();


            if (choice == 9) {
                exit = true;
            } else {
                menuHandler.handleMenuOption(choice, this);
            }
        }
    }

}