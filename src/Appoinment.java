class Appointment {
    String patientName, doctorName, dateTime, hospital;
    boolean completed = false;

    public Appointment(String patientName, String doctorName, String dateTime, String hospital) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.dateTime = dateTime;
        this.hospital = hospital;
    }
}