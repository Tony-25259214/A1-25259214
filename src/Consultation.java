/**
 * One consultation linking a patient to a worker at a fixed slot. The worker
 * reference uses the common HealthWorker type, so any subclass can be booked.
 * Implements Comparable so consultations can be ordered earliest to latest.
 */
public class Consultation implements Comparable<Consultation> {

    /** Patient attending. */
    private final Patient patient;

    /** Worker the patient sees. */
    private final HealthWorker worker;

    /** Slot as minutes from midnight, for example 510 = 08:30. */
    private final int slotMinutes;

    /**
     * Creates a consultation after confirming details and a valid slot.
     *
     * @param patient     attending patient
     * @param worker      chosen worker
     * @param slotMinutes slot as minutes from midnight
     */
    public Consultation(Patient patient, HealthWorker worker, int slotMinutes) {
        if (patient == null) {
            throw new IllegalArgumentException("Consultation must have a patient.");
        }
        if (worker == null) {
            throw new IllegalArgumentException("Consultation must have a worker.");
        }
        if (!TimeSlots.slotOkay(slotMinutes)) {
            throw new IllegalArgumentException("Invalid slot: " + TimeSlots.displayText(slotMinutes));
        }
        this.patient = patient;
        this.worker = worker;
        this.slotMinutes = slotMinutes;
    }

    /** Returns the patient. */
    public Patient getPatient() {
        return patient;
    }

    /** Returns the worker. */
    public HealthWorker getWorker() {
        return worker;
    }

    /** Returns the slot minutes. */
    public int getSlotMinutes() {
        return slotMinutes;
    }

    /** Orders consultations by time, earliest first. */
    @Override
    public int compareTo(Consultation other) {
        return Integer.compare(this.slotMinutes, other.slotMinutes);
    }

    @Override
    public String toString() {
        return "At " + TimeSlots.displayText(slotMinutes)
                + " | " + patient
                + " | " + worker.roleLabel()
                + " " + worker.getName()
                + " (ID " + worker.getId() + ")";
    }
}
