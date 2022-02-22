import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Exercise02Test {

    Exercise02 instance = new Exercise02();

    @Test
    void getNow() {
        LocalTime expected = LocalTime.now();
        LocalTime actual = instance.getNow();

        // So, testing a current time is fraught with issues.
        // It's unlikely, but if we run the test near an hour/minute/second change-over,
        // the test can fail.
        // Take failure with a grain of salt.
        assertEquals(expected.getHour(), actual.getHour());
        assertEquals(expected.getMinute(), actual.getMinute());
    }

    @Test
    void getTeaTime() {
        LocalTime expected = LocalTime.of(16, 0);
        assertEquals(expected, instance.getTeaTime());
    }

    @Test
    void add12Hours() {
        LocalTime time = LocalTime.now();
        assertEquals(time.plusHours(12), instance.add12Hours(time));

        time = LocalTime.of(16, 15);
        assertEquals(time.plusHours(12), instance.add12Hours(time));

        time = LocalTime.of(4, 15);
        assertEquals(time.plusHours(12), instance.add12Hours(time));
    }

    @Test
    void getQuarterHourRefImpl() {
        LocalTime time = LocalTime.of(16, 6, 32);
        List<LocalTime> expected = getQuarterHourRefImpl(time);
        List<LocalTime> actual = instance.getQuarterHourAppointments(time);
        assertEquals(expected, actual);

        time = LocalTime.of(3, 0, 1);
        expected = getQuarterHourRefImpl(time);
        actual = instance.getQuarterHourAppointments(time);
        assertEquals(expected, actual);

        time = LocalTime.of(4, 30);
        expected = getQuarterHourRefImpl(time);
        actual = instance.getQuarterHourAppointments(time);
        assertEquals(expected, actual);

        time = LocalTime.of(5, 55);
        expected = getQuarterHourRefImpl(time);
        actual = instance.getQuarterHourAppointments(time);
        assertEquals(expected, actual);
    }

    List<LocalTime> getQuarterHourRefImpl(LocalTime time) {
        ArrayList<LocalTime> result = new ArrayList<>();
        int delta = time.getMinute() % 15;
        if (delta > 0) {
            time = time.plusMinutes(15 - delta);
        }
        time = LocalTime.of(time.getHour(), time.getMinute());
        for (int i = 0; i < 4; i++) {
            result.add(time);
            time = time.plusMinutes(15);
        }
        return result;
    }
}