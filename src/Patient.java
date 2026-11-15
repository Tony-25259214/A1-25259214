/** A patient attending the service, holding name and contact number. */
public class Patient {

    /** Patient's full name. */
    private final String patientName;

    /** Digits-only contact number. */
    private final String contactNumber;

    /**
     * Creates a patient after validating the contact details.
     *
     * @param patientName  full name, not blank
     * @param contactNumber 8 to 12 digits
     */
    public Patient(String patientName, String contactNumber) {
        if (patientName == null || patientName.isBlank()) {
            throw new IllegalArgumentException("Patient name must not be blank.");
        }
        if (contactNumber == null || !validPhone(contactNumber.trim())) {
            throw new IllegalArgumentException("Contact number must be 8 to 12 digits.");
        }
        this.patientName = patientName.trim();
        this.contactNumber = contactNumber.trim();
    }

    /** Checks the number is 8 to 12 digits and digits only. */
    private static boolean validPhone(String number) {
        if (number.length() < 8 || number.length() > 12) {
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /** Returns the patient name. */
    public String getPatientName() {
        return patientName;
    }

    /** Returns the contact number. */
    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public String toString() {
        return "Patient: " + patientName + " | Contact: " + contactNumber;
    }
}
