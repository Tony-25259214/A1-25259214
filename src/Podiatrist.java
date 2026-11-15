import java.util.List;

/** A podiatrist who works on a foot-care area and may visit patients at home. */
public class Podiatrist extends HealthWorker {

    /** Foot-care area, for example "Sports foot care". */
    private final String footCareArea;

    /** Whether home visits are made. */
    private final boolean visitsHome;

    /**
     * Creates a podiatrist.
     *
     * @param workerId     numeric ID
     * @param workerName   full name
     * @param workingDays  working weekdays
     * @param footCareArea foot-care area
     * @param visitsHome   true if home visits are made
     */
    public Podiatrist(String workerId, String workerName, List<String> workingDays,
                      String footCareArea, boolean visitsHome) {
        super(workerId, workerName, workingDays);
        if (footCareArea == null || footCareArea.isBlank()) {
            throw new IllegalArgumentException("Foot-care area must not be blank.");
        }
        this.footCareArea = footCareArea.trim();
        this.visitsHome = visitsHome;
    }

    @Override
    public String roleLabel() {
        return "Podiatrist";
    }

    @Override
    public String roleSummary() {
        String text = "Works on " + footCareArea.toLowerCase();
        if (visitsHome) {
            return text + ", and makes home visits.";
        }
        return text + ", and sees patients at the clinic.";
    }

    @Override
    public String toString() {
        return commonDetails()
                + " | Area: " + footCareArea
                + " | Home visits: " + (visitsHome ? "Yes" : "No");
    }
}
