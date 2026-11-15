import java.util.List;

/** A general practitioner who may accept referred patients. */
public class GeneralPractitioner extends HealthWorker {

    /** Whether referred patients are accepted. */
    private final boolean acceptsReferrals;

    /**
     * Creates a general practitioner.
     *
     * @param workerId         numeric ID
     * @param workerName       full name
     * @param workingDays      working weekdays
     * @param acceptsReferrals true if referrals are accepted
     */
    public GeneralPractitioner(String workerId, String workerName, List<String> workingDays,
                               boolean acceptsReferrals) {
        super(workerId, workerName, workingDays);
        this.acceptsReferrals = acceptsReferrals;
    }

    @Override
    public String roleLabel() {
        return "General Practitioner";
    }

    @Override
    public String roleSummary() {
        if (acceptsReferrals) {
            return "Takes booked referrals and routine consultations.";
        }
        return "Sees routine booked consultations.";
    }

    @Override
    public String toString() {
        return commonDetails() + " | Referrals: " + (acceptsReferrals ? "Yes" : "No");
    }
}
