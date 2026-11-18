package attendance.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Crews {
    private final List<String> nickname;

    public Crews(List<String> nickname) {
        this.nickname = nickname;
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

    public void validNicknames(String name){
        boolean invalidName=!(nickname.contains(name));
        if(invalidName){
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
}
