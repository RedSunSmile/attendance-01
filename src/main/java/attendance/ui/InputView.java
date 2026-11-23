package attendance.ui;

import attendance.domain.Crews;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputView {

    public String inputNickName() {
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String inputStartClassTime() {
        System.out.println("등교 시간을 입력해 주세요.");
        return Console.readLine();
    }

    public String inputMenu() {
        return Console.readLine();
    }

    public String inputModifyNickname(){
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String inputModifyDay(){
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
        return Console.readLine();
    }

     public String inputModifyTime(){
         System.out.println("언제로 변경하겠습니까?");
         return Console.readLine();
     }

    public String inputAssignOfEachCrew(){
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

}
