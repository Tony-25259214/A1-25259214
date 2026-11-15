import java.util.List;

/** An optometrist who focuses on an eye-care area and may issue prescriptions. */
public class Optometrist extends HealthWorker {

    /** Eye-care focus, for example "Children's eye care". */
    private final String eyeCareFocus;

    /** Whether optical prescriptions are issued. */
    private final boolean writesPrescriptions;

    /**
     * Creates an optometrist.
     *
     * @param workerId            numeric ID
     * @param workerName          full name
     * @param workingDays         working weekdays
     * @param eyeCareFocus        eye-care focus
     * @param writesPrescriptions true if prescriptions are issued
     */
    public Optometrist(String workerId, String workerName, List<String> workingDays,
                       String eyeCareFocus, boolean writesPrescriptions) {
        super(workerId, workerName, workingDays);
        if (eyeCareFocus == null || eyeCareFocus.isBlank()) {
            throw new IllegalArgumentException("Eye-care focus must not be blank.");
        }
        this.eyeCareFocus = eyeCareFocus.trim();
        this.writesPrescriptions = writesPrescriptions;
    }

    @Override
    public String roleLabel() {
        return "Optometrist";
    }

    @Override
    public String roleSummary() {
        String text = "Specialises in " + eyeCareFocus.toLowerCase();
        if (writesPrescriptions) {
            return text + " and issues optical prescriptions.";
        }
        return text + " and does not issue prescriptions.";
    }

    @Override
    public String toString() {
        return commonDetails()
                + " | Focus: " + eyeCareFocus
                + " | Prescriptions: " + (writesPrescriptions ? "Yes" : "No");
    }
}
