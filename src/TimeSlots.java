/**
 * Fixed daily clinic slots. Consultations run 08:00 to 16:00 in 30-minute
 * slots. Times are minutes from midnight so they can be ordered numerically.
 * This class only holds constants and helper methods; it is never instantiated.
 */
public final class TimeSlots {

    /** First slot hour, 24-hour clock. */
    public static final int FIRST_HOUR = 8;

    /** Last slot hour, 24-hour clock. */
    public static final int LAST_HOUR = 16;

    /** Slot spacing in minutes. */
    public static final int SPACING_MINUTES = 30;

    /** Utility class: no instances allowed. */
    private TimeSlots() {
    }

    /** Checks whether a minutes-from-midnight value is a valid clinic slot. */
    public static boolean slotOkay(int slotMinutes) {
        int start = FIRST_HOUR * 60;
        int end = LAST_HOUR * 60;
        if (slotMinutes < start || slotMinutes > end) {
            return false;
        }
        return slotMinutes % SPACING_MINUTES == 0;
    }

    /** Formats minutes from midnight as "HH:mm". */
    public static String displayText(int slotMinutes) {
        int hour = slotMinutes / 60;
        int minute = slotMinutes % 60;
        return String.format("%02d:%02d", hour, minute);
    }
}
