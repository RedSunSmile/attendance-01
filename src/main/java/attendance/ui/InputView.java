package attendance.ui;

import attendance.domain.Crews;
import camp.nextstep.edu.missionutils.Console;

import java.time.DateTimeException;
import java.time.LocalTime;


public class InputView {

    public String inputNickName() {
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public LocalTime inputStartClassTime() {
        try {
            System.out.println("등교 시간을 입력해 주세요.");
            return LocalTime.parse(Console.readLine());
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }

    }

    public String inputMenu() {
        return Console.readLine();
    }

    public String inputModifyNickname() {
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public int inputModifyDay() {
        try {
            System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
            int day = Integer.parseInt(Console.readLine());
            if (day < 1 || day > 31) {
                throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
            }
            return day;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    public LocalTime inputModifyTime() {
        try {
            System.out.println("언제로 변경하겠습니까?");
            return LocalTime.parse(Console.readLine());
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    public String inputAssignOfEachCrew() {
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

}
