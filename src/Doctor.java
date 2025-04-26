import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Doctor extends User {
    String specialty, regNumber, contact;
    Map<String, List<String>> schedule = new HashMap<>();
    List<String> appointments = new ArrayList<>();

    public Doctor(String name, String id, String specialty, String regNumber, String contact) {
        super(name, id, "Doctor");
        this.specialty = specialty;
        this.regNumber = regNumber;
        this.contact = contact;
    }
}
