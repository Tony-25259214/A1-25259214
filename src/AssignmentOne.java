import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration class for the PROG2004 Assessment 1 consultation-booking system.
 *
 * <p>The main method runs a short scripted simulation: it introduces the
 * workers on the roster (Part 3), then books, filters, sorts and cancels
 * consultations through the agenda (Parts 4 to 6), printing every result to
 * the console. Both normal cases and error cases are shown.</p>
 *
 * <h2>Submission details</h2>
 * <ul>
 *     <li>Name: Tony</li>
 *     <li>SCU account: 25259214</li>
 *     <li>GitHub repository link: https://github.com/Tony-25259214/A1-25259214</li>
 *     <li>Demonstration video link (SCU OneDrive share): &lt;paste video link here&gt;</li>
 * </ul>
 *
 * <p>GenAI declaration: I acknowledge that I have used Generative AI tools
 * to complete this assessment in accordance with the conditions outlined in
 * the Assessment Brief. I used Kimi to summarise the assignment requirements,
 * explain OOP concepts, and guide my code design. I wrote all source code and
 * this submission myself.</p>
 */
public class AssignmentOne {

    /**
     * Entry point. Runs the roster display and the booking demonstration.
     *
     * @param args command-line arguments, unused
     */
    public static void main(String[] args) {
        showWorkers();
        runConsultations();
    }

    /** Prints a section heading. */
    private static void printHeader(String title) {
        System.out.println("=== " + title + " ===");
    }

    /**
     * Part 3: build workers of several types, store them under the common
     * HealthWorker type, and print details plus role summary for each.
     */
    private static void showWorkers() {
        printHeader("PART 3 - Workers on the roster");

        List<HealthWorker> roster = new ArrayList<>();
        roster.add(new GeneralPractitioner("1101", "Dr Sam Okafor", null, true));
        roster.add(new GeneralPractitioner("1102", "Dr Mei Tan", null, false));
        roster.add(new Optometrist("2201", "Dr Nina Cole", null, "Children's eye care", true));
        roster.add(new Podiatrist("3301", "Owen Ruiz", null, "Sports foot care", true));
        roster.add(new SpeechTherapist("4401", "Ella Shaw", null, "Child language delay", false));

        for (HealthWorker member : roster) {
            System.out.println(member);
            System.out.println("    Role: " + member.roleSummary());
        }
        System.out.println();
    }

    /**
     * Parts 4 to 6: book consultations, filter and sort them, show rejected
     * cases, then cancel real consultations and print what remains.
     */
    private static void runConsultations() {
        printHeader("PART 4-5 - Booking consultations");

        ConsultationAgenda agenda = new ConsultationAgenda();

        HealthWorker gpOkafor = new GeneralPractitioner("1101", "Dr Sam Okafor", null, true);
        HealthWorker gpTan = new GeneralPractitioner("1102", "Dr Mei Tan", null, false);
        HealthWorker optCole = new Optometrist("2201", "Dr Nina Cole", null, "Children's eye care", true);
        HealthWorker podRuiz = new Podiatrist("3301", "Owen Ruiz", null, "Sports foot care", true);
        HealthWorker stShaw = new SpeechTherapist("4401", "Ella Shaw", null, "Child language delay", false);

        Patient grace = new Patient("Grace Hill", "0412000111");
        Patient leo = new Patient("Leo Ward", "0412000222");
        Patient mia = new Patient("Mia Lane", "0412000333");
        Patient noah = new Patient("Noah Kim", "0412000444");
        Patient ivy = new Patient("Ivy Rose", "0412000555");

        System.out.println(agenda.bookConsultation(grace, gpOkafor, 480));
        System.out.println(agenda.bookConsultation(leo, optCole, 510));
        System.out.println(agenda.bookConsultation(mia, podRuiz, 540));
        System.out.println(agenda.bookConsultation(noah, gpTan, 570));
        System.out.println(agenda.bookConsultation(ivy, stShaw, 600));
        System.out.println();

        printHeader("Filter by worker ID 1101");
        for (Consultation consultation : agenda.filterByWorkerId("1101")) {
            System.out.println(consultation);
        }
        System.out.println();

        printHeader("Filter by contact 0412000222");
        for (Consultation consultation : agenda.filterByContact("0412000222")) {
            System.out.println(consultation);
        }
        System.out.println();

        printHeader("All consultations, earliest first");
        for (Consultation consultation : agenda.byTimeAscending()) {
            System.out.println(consultation);
        }
        System.out.println();

        printHeader("Subset for ID 1101, earliest first");
        for (Consultation consultation : agenda.byTimeAscendingSubset(agenda.filterByWorkerId("1101"))) {
            System.out.println(consultation);
        }
        System.out.println();

        printHeader("Rejected cases");
        Patient uma = new Patient("Uma Fox", "0412000999");
        System.out.println(agenda.bookConsultation(uma, gpOkafor, 480));
        System.out.println(agenda.bookConsultation(grace, gpTan, 1020));
        System.out.println(agenda.cancelByIdAndSlot("2201", 840));
        System.out.println(agenda.cancelByContact("0499999000"));
        System.out.println();

        printHeader("After cancellations");
        System.out.println(agenda.cancelByIdAndSlot("1101", 480));
        System.out.println(agenda.cancelByContact("0412000222"));
        System.out.println();
        for (Consultation consultation : agenda.byTimeAscending()) {
            System.out.println(consultation);
        }
    }
}
