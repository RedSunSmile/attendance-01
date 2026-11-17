package attendance.ui;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class OutputView {

    public void printFunction(){
        LocalDateTime dateTimes=DateTimes.now();
        System.out.println("오늘은 "+dateTimes.getMonthValue()+"월 "+ dateTimes.getDayOfMonth()+"일 "+dateTimes.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)+"입니다. 기능을 선택해 주세요.");
    }
    public void selectFunction() {
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
    }
}
