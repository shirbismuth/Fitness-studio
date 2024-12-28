package gym.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Dates {
    /**
     * Checks if the session time has passed.
     *
     * @param lessonTime the time of the session
     * @return true if the session time has passed, otherwise false
     */
    public static boolean isPassed(String lessonTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime givenDateTime = LocalDateTime.parse(lessonTime, formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.isAfter(givenDateTime);
    }

    /**
     * Converts the time to a specific format.
     *
     * @param time the time to be converted
     * @return the formatted time
     */
    public static String timeToFormat(String time) {
        DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter resultFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(time, originalFormat);
        return dateTime.format(resultFormat);
    }

    /**
     * Calculates the age of the person.
     *
     * @return the age of the person
     */
    public static int ageCalculator(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(birthday, formatter);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
