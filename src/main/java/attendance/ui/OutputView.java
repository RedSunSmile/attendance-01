package attendance.ui;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class OutputView {

    public void printFunction(){
        LocalDateTime dateTimes=DateTimes.now();
        System.out.println("오늘은 "+dateTimes.getMonthValue()+"월 "+ dateTimes.getDayOfMonth()+"일 "+dateTimes.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)+"입니다. 기능을 선택해 주세요.");
    }

    public void printAttendance(LocalDateTime nowDate,String result) {
        System.out.printf("%d월 %02d일 %s %s (%s)%n",
                nowDate.getMonthValue(),
                nowDate.getDayOfMonth(),
                nowDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN),
                nowDate.toLocalTime(),
                result);
    }

    public void printModify(LocalDateTime oldRecord, String oldResult, LocalDateTime nowDate, String newResult){
       if(oldRecord==null){
           System.out.printf("%d월 %02d일 %s --:-- (%s) -> %s (%s) 수정 완료!%n",
                   nowDate.getMonthValue(),
                   nowDate.getDayOfMonth(),
                   nowDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN),
                   oldResult,
                   nowDate.toLocalTime(),
                   newResult);
           return;
       }
        System.out.printf("%d월 %02d일 %s %s (%s) -> %s (%s) 수정 완료!%n",
                oldRecord.getMonthValue(),
                oldRecord.getDayOfMonth(),
                oldRecord.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN),
                oldRecord.toLocalTime(),
                oldResult,
                nowDate.toLocalTime(),
                newResult);
    }
    public void printAssignAttendance(LocalDateTime nowDate,String result){
        System.out.println("이번 달 빙티의 출석 기록입니다.");
        System.out.printf("%d월 %02d일 %s %s (%s)%n",
                nowDate.getMonthValue(),
                nowDate.getDayOfMonth(),
                nowDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN),
                nowDate.toLocalTime(),
                result);
    }

    public void selectFunction() {
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
    }


}
