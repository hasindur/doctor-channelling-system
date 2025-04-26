import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<String> hospitals = new ArrayList<>();
    static List<Doctor> doctors = new ArrayList<>();
    static List<Patient> patients = new ArrayList<>();
    static List<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.print("-- v --\n\n1. Admin \n2. Doctor \n3. Patient \n4. Exit \nChoose Your Role : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 : adminMenu();
                case 2 : doctorMenu();
                case 3 : patientMenu();
                case 4 : System.exit(0);
                default: System.out.println("Invalid input");
            }
        }
    }

    static void adminMenu() {
        System.out.print("Enter admin password: ");
        if (!sc.nextLine().equals("admin123")) {
            System.out.println("Wrong password");
            return;
        }
        System.out.print("Enter hospital name to add: ");
        hospitals.add(sc.nextLine());
        System.out.println("Hospital added successfully.");
    }

    static void doctorMenu() {
        System.out.print("1. Register \n2. Login \nOption : ");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1) {
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("Specialty: ");
            String spec = sc.nextLine();
            System.out.print("Reg number: ");
            String reg = sc.nextLine();
            System.out.print("Contact: ");
            String contact = sc.nextLine();
            doctors.add(new Doctor(name, id, spec, reg, contact));
            System.out.println("Doctor registered.");
        } else {
            System.out.print("Enter ID to login: ");
            String id = sc.nextLine();
            Doctor d = null;
            for (Doctor doc : doctors) {
                if (doc.id.equals(id)) {
                    d = doc;
                    break;
                }
            }
            if (d == null) {
                System.out.println("Doctor not found.");
                return;
            }
            doctorPanel(d);
        }
    }

    static void doctorPanel(Doctor d) {
        while (true) {
            System.out.println("1. Set Availability 2. View Appointments 3. Logout");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1 : {
                    System.out.print("Hospital: ");
                    String hosp = sc.nextLine();
                    System.out.print("Date and Time (e.g. 2025-04-22 10AM): ");
                    String dateTime = sc.nextLine();
                    d.schedule.computeIfAbsent(hosp, k -> new ArrayList<>()).add(dateTime);
                    System.out.println("Availability set.");
                }
                case 2 : {
                    for (Appointment a : appointments) {
                        if (a.doctorName.equals(d.name)) {
                            System.out.println(a.dateTime + " at " + a.hospital + " - " + a.patientName +
                                    (a.completed ? " [Completed]" : ""));
                        }
                    }
                    System.out.print("Mark appointment as completed (Enter patient name or 'skip'): ");
                    String pname = sc.nextLine();
                    for (Appointment a : appointments) {
                        if (a.doctorName.equals(d.name) && a.patientName.equals(pname)) {
                            a.completed = true;
                            System.out.println("Marked as completed.");
                        }
                    }
                }
                case 3 : {
                    return;
                }
                default : System.out.println("Invalid input.");
            }
        }
    }

    static void patientMenu() {
        System.out.println("1. Register \n2. Login \nOption : ");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1) {
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("ID: ");
            String id = sc.nextLine();
            patients.add(new Patient(name, id));
            System.out.println("Patient registered.");
        } else {
            System.out.print("Enter ID to login: ");
            String id = sc.nextLine();
            Patient p = null;
            for (Patient pat : patients) {
                if (pat.id.equals(id)) {
                    p = pat;
                    break;
                }
            }
            if (p == null) {
                System.out.println("Patient not found.");
                return;
            }
            patientPanel(p);
        }
    }

    static void patientPanel(Patient p) {
        while (true) {
            System.out.println("1. Search Doctors 2. Schedule Appointment 3. Logout");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1 : {
                    System.out.print("Specialty: ");
                    String spec = sc.nextLine();
                    System.out.print("Hospital: ");
                    String hosp = sc.nextLine();
                    System.out.print("Date (e.g. 2025-04-22): ");
                    String date = sc.nextLine();
                    for (Doctor d : doctors) {
                        if (d.specialty.equals(spec) && d.schedule.containsKey(hosp)) {
                            for (String dt : d.schedule.get(hosp)) {
                                if (dt.contains(date)) {
                                    System.out.println("Dr. " + d.name + " at " + dt + " in " + hosp);
                                }
                            }
                        }
                    }
                }
                case 2 : {
                    System.out.print("Doctor name: ");
                    String dname = sc.nextLine();
                    System.out.print("Date and Time: ");
                    String dt = sc.nextLine();
                    System.out.print("Hospital: ");
                    String hosp = sc.nextLine();
                    appointments.add(new Appointment(p.name, dname, dt, hosp));
                    System.out.println("Appointment scheduled.");
                }
                case 3 : {
                    return;
                }
                default : System.out.println("Invalid input.");
            }
        }
    }
}
