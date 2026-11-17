package attendance;


import attendance.domain.AttendanceJudge;
import attendance.ui.InputView;
import attendance.ui.OutputView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> lines = readLines();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        OutputView outputView = new OutputView();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            String nickname = parts[0];
            String datetime = parts[1];

            LocalDateTime time = LocalDateTime.parse(datetime, formatter);

        }
        while (true) {
            outputView.printFunction();
            outputView.selectFunction();
            InputView inputView = new InputView();
            String input = inputView.inputMenu();
            System.out.println(input);
            if (input.equals("Q")) {
                break;
            }
            if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {
                throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
            }
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
