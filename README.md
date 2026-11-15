# PROG2004 Assessment 1 — Consultation Booking System

- Name: Tony
- SCU account: 25259214
- GitHub repository: https://github.com/Tony-25259214/A1-25259214

A short Java console application that models workers at a health service and
manages their consultations. There is no UI, file storage, or database; the
`main` method runs a scripted demonstration that prints normal and rejected
cases to the console.

## Source files

| File | Role |
| --- | --- |
| `HealthWorker.java` | Abstract base class holding shared ID, name and working days |
| `GeneralPractitioner.java` | GP that may accept referrals |
| `Optometrist.java` | Optometrist with eye-care focus and prescription rights |
| `Podiatrist.java` | Podiatrist with foot-care area and home-visit option |
| `SpeechTherapist.java` | Speech therapist with therapy focus and group option |
| `Patient.java` | Patient name and contact number |
| `Consultation.java` | One consultation linking a patient, worker and slot; `Comparable` |
| `TimeSlots.java` | Fixed 08:00–16:00 timetable, 30-minute slots |
| `ConsultationAgenda.java` | Booking, filtering, ordering and cancellation |
| `AssignmentOne.java` | Main demonstration class |

## How to run

```text
javac -encoding UTF-8 -d out src\*.java
java -cp out AssignmentOne
```

## Checklist

- [x] Abstract base class with numeric ID, name, working days and controlled access
- [x] Four concrete worker types, including one general practitioner
- [x] Common-type roster storing all subtypes; details and role summary printed
- [x] Consultation class linking an independent Patient to any worker subtype
- [x] Validation of incomplete or illegal bookings; double booking rejected
- [x] Filter by worker ID and patient contact; whole list and subset sorting
- [x] Cancellation by ID plus slot, and by contact number; warnings on failure
- [x] Main demonstration covering normal and error cases
- [ ] WakaTime activity report
- [x] Short demonstration video link added to the comments
