package attendance.ui;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputNickName() {
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String inputMenu() {
        return Console.readLine();
    }
}
