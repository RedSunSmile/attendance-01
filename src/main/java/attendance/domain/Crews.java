package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crews {
    private final List<String> nickname;
    private final Map<String, List<LocalDateTime>> records;

    public Crews(List<String> nickname) {
        this.nickname = nickname;
        this.records = readRecords();
    }

    public List<String> takeNickname() {
        return nickname;
    }

    public static List<String> readNickname() {
        List<String> lines = readLines();
        List<String> namePart = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            if (!namePart.contains(parts[0])) {
                namePart.add(parts[0]);
            }
        }
        return namePart;
    }

    public Map<String, List<LocalDateTime>> readRecords() {
        List<String> lines = readLines();
        Map<String, List<LocalDateTime>> records = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] section = line.split(",");
            String name = section[0];
            LocalDateTime dateTime = LocalDateTime.parse(section[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            if (!records.containsKey(name)) {
                records.put(name, new ArrayList<>());
            }
            records.get(name).add(dateTime);
        }
        return records;
    }

    public void validNicknames(String name) {
        boolean invalidName = !(nickname.contains(name));
        if (invalidName) {
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }

    public static List<String> readLines() {
        try {
            return Files.readAllLines(Path.of("src/main/resources/attendances.csv"));
        } catch (IOException e) {
            throw new IllegalStateException("출석 파일을 읽을 수 없습니다.", e);
        }
    }

    public LocalDateTime findRecord(String basket, LocalDate date) {
        LocalDateTime oldRecord = null;
        for (LocalDateTime record : records.get(basket)) {
            if (record.toLocalDate().equals(date)) {
                oldRecord = record;
            }
        }
        return oldRecord;
    }

    public void addRecord(String name, LocalDateTime dateTime) {
        records.get(name).add(dateTime);
    }

    public void modifyRecord(String name, LocalDateTime oldRecord, LocalDateTime nowDate) {
        List<LocalDateTime> list = records.get(name);
        if (oldRecord != null) {
            list.remove(oldRecord);
        }
        list.add(nowDate);
    }

    public List<LocalDateTime> takeRecord(String name) {
        return records.get(name);
    }

    public AttendanceCount countAttendance(String name) {
        int attend = 0, late = 0, absent = 0;
        for (int day = 1; day < DateTimes.now().getDayOfMonth(); day++) {
            LocalDate date = DateTimes.now().toLocalDate().withDayOfMonth(day);
            if (date.getMonthValue() == 12 && day == 25) {
                continue;
            }
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                continue;
            }
            LocalDateTime record = findRecord(name, date);
            if (record == null) {
                absent++;
                continue;
            }
            String result = AttendanceJudge.judge(record);
            if (result.equals("출석")) attend++;
            if (result.equals("지각")) late++;
            if (result.equals("결석")) absent++;
        }
        return new AttendanceCount(attend, late, absent);
    }
}
