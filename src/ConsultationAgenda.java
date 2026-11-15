import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores all consultations, checks new bookings, and supports filtering,
 * ordering and cancellation. Every rejected action returns a warning message
 * instead of stopping the program.
 */
public class ConsultationAgenda {

    /** Consultations held by this agenda. */
    private final List<Consultation> consults = new ArrayList<>();

    /**
     * Books a consultation after validation.
     *
     * @param patient     attending patient
     * @param worker      chosen worker
     * @param slotMinutes slot minutes from midnight
     * @return success message, or a rejection warning
     */
    public String bookConsultation(Patient patient, HealthWorker worker, int slotMinutes) {
        if (patient == null) {
            return "Rejected: patient details are missing.";
        }
        if (worker == null) {
            return "Rejected: a worker must be chosen.";
        }
        if (!TimeSlots.slotOkay(slotMinutes)) {
            return "Rejected: " + TimeSlots.displayText(slotMinutes) + " falls outside daily slots.";
        }
        for (Consultation existing : consults) {
            if (existing.getWorker().equals(worker)
                    && existing.getSlotMinutes() == slotMinutes) {
                return "Rejected: " + worker.getName()
                        + " already has a booking at " + TimeSlots.displayText(slotMinutes) + ".";
            }
        }
        consults.add(new Consultation(patient, worker, slotMinutes));
        return "OK: Consultation booked for " + patient.getPatientName()
                + " with " + worker.getName()
                + " at " + TimeSlots.displayText(slotMinutes) + ".";
    }

    /** Returns an unmodifiable list of all consultations. */
    public List<Consultation> listAll() {
        return Collections.unmodifiableList(consults);
    }

    /** Returns consultations for the worker with the given ID. */
    public List<Consultation> filterByWorkerId(String workerId) {
        List<Consultation> matched = new ArrayList<>();
        for (Consultation consultation : consults) {
            if (consultation.getWorker().getId().equals(workerId)) {
                matched.add(consultation);
            }
        }
        return matched;
    }

    /** Returns consultations for the patient with the given contact number. */
    public List<Consultation> filterByContact(String phone) {
        List<Consultation> matched = new ArrayList<>();
        for (Consultation consultation : consults) {
            if (consultation.getPatient().getContactNumber().equals(phone)) {
                matched.add(consultation);
            }
        }
        return matched;
    }

    /** Returns all consultations sorted earliest first. */
    public List<Consultation> byTimeAscending() {
        List<Consultation> ordered = new ArrayList<>(consults);
        Collections.sort(ordered);
        return ordered;
    }

    /** Sorts the supplied subset earliest first without touching stored data. */
    public List<Consultation> byTimeAscendingSubset(List<Consultation> subset) {
        List<Consultation> ordered = new ArrayList<>(subset);
        Collections.sort(ordered);
        return ordered;
    }

    /**
     * Cancels the consultation for a worker ID and slot.
     *
     * @return success message, or a warning when none matches
     */
    public String cancelByIdAndSlot(String workerId, int slotMinutes) {
        for (int i = 0; i < consults.size(); i++) {
            Consultation consultation = consults.get(i);
            if (consultation.getWorker().getId().equals(workerId)
                    && consultation.getSlotMinutes() == slotMinutes) {
                consults.remove(i);
                return "OK: Cancelled consultation for worker ID " + workerId
                        + " at " + TimeSlots.displayText(slotMinutes) + ".";
            }
        }
        return "Rejected: no consultation for worker ID " + workerId
                + " at " + TimeSlots.displayText(slotMinutes) + ".";
    }

    /**
     * Cancels every consultation for the given contact number.
     *
     * @return success message, or a warning when none matches
     */
    public String cancelByContact(String phone) {
        int before = consults.size();
        List<Consultation> kept = new ArrayList<>();
        for (Consultation consultation : consults) {
            if (!consultation.getPatient().getContactNumber().equals(phone)) {
                kept.add(consultation);
            }
        }
        consults.clear();
        consults.addAll(kept);
        int removed = before - consults.size();
        if (removed == 0) {
            return "Rejected: no consultation found for contact " + phone + ".";
        }
        return "OK: Cancelled " + removed + " consultation(s) for contact " + phone + ".";
    }
}
