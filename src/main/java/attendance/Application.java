package attendance;


import attendance.domain.AttendanceJudge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> lines=readLines();
        for(int i=1;i<lines.size();i++) {
            String line=lines.get(i);
            String[] parts = line.split(",");

            String nickname = parts[0];
            String datetime = parts[1];
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime time=LocalDateTime.parse(datetime,formatter);
            System.out.println(nickname+" / "+time+" / "+ AttendanceJudge.judge(time));
        }
    }

    public static List<String> readLines() {
        try {
            return Files.readAllLines(Path.of("src/main/resources/attendances.csv"));
        } catch (IOException e) {
            throw new IllegalArgumentException("출석 파일을 읽을 수 없습니다.", e);
        }
    }
}
