package attendance.domain;

import net.bytebuddy.asm.Advice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static List<String> readNickname() {
        List<String> lines = readLines();
        List<String> namePart = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            namePart.add(parts[0]);
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

    public void addRecord(String name, LocalDateTime dateTime){
        records.get(name).add(dateTime);
    }

    public void modifyRecord(String name, LocalDateTime oldRecord, LocalDateTime nowDate) {
        List<LocalDateTime> list = records.get(name);
        if (oldRecord != null) {
            list.remove(oldRecord);
        }
        list.add(nowDate);
    }

    public List<LocalDateTime> takeRecord(String name){
        return  records.get(name);
    }


}
