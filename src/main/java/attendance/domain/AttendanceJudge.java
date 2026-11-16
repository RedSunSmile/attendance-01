package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class AttendanceJudge {
    public static String judge(LocalDateTime time) {
        LocalTime start = LocalTime.of(10, 0);
        if (time.getDayOfWeek() == DayOfWeek.MONDAY) {
            start = LocalTime.of(13,0);
        }
        LocalTime arrival = time.toLocalTime();
        if (arrival.isAfter(start.plusMinutes(30))) {
            return "결석";
        }
        if (arrival.isAfter(start.plusMinutes(5))) {
            return "지각";
        }
        return "출석";
    }
}