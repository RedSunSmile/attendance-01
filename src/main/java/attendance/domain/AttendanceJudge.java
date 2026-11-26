package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class AttendanceJudge {
    public static String judge(LocalDateTime time) {
        LocalTime start = LocalTime.of(10, 0);
        int count = 0;
        if (time == null) {
            return "결석";
        }
        if (time.getDayOfWeek() == DayOfWeek.MONDAY) {
            start = LocalTime.of(13, 0);
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

    public static String judgeCrew(int late, int absent) {
        int total = absent + late / 3;
        if (total > 5) {
            return "제적";
        }
        if (total >= 3) {
            return "면담";
        }

        if (total >= 2) {
            return "경고";
        }
        return "";
    }

    public static boolean isNotSchoolDay(LocalDate today) {
        return today.getDayOfWeek() == DayOfWeek.SATURDAY
                || today.getDayOfWeek() == DayOfWeek.SUNDAY
                || (today.getMonthValue() == 12 && today.getDayOfMonth() == 25);
    }
}