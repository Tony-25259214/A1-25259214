import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Shared base class for every worker at the service. Holds the details common
 * to all roles: a numeric ID, a name, and working weekdays. It is abstract, so
 * a generic unspecialised worker cannot be created directly.
 */
public abstract class HealthWorker {

    /** Digits-only identifier, for example "1101". */
    private final String workerId;

    /** Worker's full name. */
    private String workerName;

    /** Weekdays the worker is available. */
    private final List<String> workingDays;

    /**
     * Creates a worker after validating the shared details.
     *
     * @param workerId    numeric-only ID
     * @param workerName  full name, not blank
     * @param workingDays working weekdays; Tuesday, Thursday, Sunday if empty
     */
    public HealthWorker(String workerId, String workerName, List<String> workingDays) {
        if (workerId == null || !digitsOnly(workerId)) {
            throw new IllegalArgumentException("Worker ID must contain digits only.");
        }
        if (workerName == null || workerName.isBlank()) {
            throw new IllegalArgumentException("Worker name must not be blank.");
        }
        this.workerId = workerId;
        this.workerName = workerName.trim();

        if (workingDays == null || workingDays.isEmpty()) {
            this.workingDays = new ArrayList<>();
            this.workingDays.add("Tuesday");
            this.workingDays.add("Thursday");
            this.workingDays.add("Sunday");
        } else {
            // Defensive copy keeps the internal list safe from outside changes.
            this.workingDays = new ArrayList<>(workingDays);
        }
    }

    /** Checks text is non-empty and digits only. */
    private static boolean digitsOnly(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /** Returns the worker ID. */
    public String getId() {
        return workerId;
    }

    /** Returns the worker name. */
    public String getName() {
        return workerName;
    }

    /** Updates the name after a non-blank check. */
    public void setWorkerName(String workerName) {
        if (workerName == null || workerName.isBlank()) {
            throw new IllegalArgumentException("Worker name must not be blank.");
        }
        this.workerName = workerName.trim();
    }

    /** Returns an unmodifiable copy of the working weekdays. */
    public List<String> getWorkingDays() {
        return Collections.unmodifiableList(workingDays);
    }

    /** Short role label, implemented by each subclass. */
    public abstract String roleLabel();

    /** One-line summary of the role's distinctive capability. */
    public abstract String roleSummary();

    /** Shared detail line reused by subclass toString methods. */
    protected String commonDetails() {
        return "ID: " + workerId
                + " | Name: " + workerName
                + " | Role: " + roleLabel()
                + " | Days: " + String.join(", ", workingDays);
    }

    /** Equal when the two workers carry the same ID. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof HealthWorker)) {
            return false;
        }
        HealthWorker other = (HealthWorker) o;
        return this.workerId.equals(other.workerId);
    }

    @Override
    public int hashCode() {
        return workerId.hashCode();
    }

    @Override
    public String toString() {
        return commonDetails();
    }
}
