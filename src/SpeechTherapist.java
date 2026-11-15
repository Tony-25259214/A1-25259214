import java.util.List;

/** A speech therapist focused on a therapy area, running either groups or one-to-one. */
public class SpeechTherapist extends HealthWorker {

    /** Therapy focus, for example "Child language delay". */
    private final String therapyFocus;

    /** Whether group sessions are run. */
    private final boolean runsGroups;

    /**
     * Creates a speech therapist.
     *
     * @param workerId     numeric ID
     * @param workerName   full name
     * @param workingDays  working weekdays
     * @param therapyFocus therapy focus
     * @param runsGroups   true if group sessions are run
     */
    public SpeechTherapist(String workerId, String workerName, List<String> workingDays,
                           String therapyFocus, boolean runsGroups) {
        super(workerId, workerName, workingDays);
        if (therapyFocus == null || therapyFocus.isBlank()) {
            throw new IllegalArgumentException("Therapy focus must not be blank.");
        }
        this.therapyFocus = therapyFocus.trim();
        this.runsGroups = runsGroups;
    }

    @Override
    public String roleLabel() {
        return "Speech Therapist";
    }

    @Override
    public String roleSummary() {
        String text = "Focuses on " + therapyFocus.toLowerCase();
        if (runsGroups) {
            return text + ", and runs group sessions.";
        }
        return text + ", and works one-to-one.";
    }

    @Override
    public String toString() {
        return commonDetails()
                + " | Focus: " + therapyFocus
                + " | Groups: " + (runsGroups ? "Yes" : "No");
    }
}
