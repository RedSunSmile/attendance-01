package attendance.ui;

import attendance.domain.RiskCrew;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class OutputView {

    public void printFunction() {
        LocalDateTime dateTimes = DateTimes.now();
        System.out.println("오늘은 " + dateTimes.getMonthValue() + "월 " + dateTimes.getDayOfMonth() + "일 " + dateTimes.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN) + "입니다. 기능을 선택해 주세요.");
    }

    public void printAttendance(LocalDateTime nowDate, String result) {
        System.out.println();
        System.out.printf("%d월 %02d일 %s %s (%s)%n",
                nowDate.getMonthValue(),
                nowDate.getDayOfMonth(),
                nowDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN),
                nowDate.toLocalTime(),
                result);
        System.out.println();
    }

    public void printModify(LocalDateTime oldRecord, String oldResult, LocalDateTime nowDate, String newResult) {
        System.out.println();
        if (oldRecord == null) {
            System.out.printf("%d월 %02d일 %s --:-- (%s) -> %s (%s) 수정 완료!%n",
                    nowDate.getMonthValue(),
                    nowDate.getDayOfMonth(),
                    nowDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN),
                    oldResult,
                    nowDate.toLocalTime(),
                    newResult);
            System.out.println();
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
        System.out.println();
    }

    public void printRecordTitle(String nickname) {
        System.out.println();
        System.out.println("이번 달 " + nickname + "의 출석 기록입니다.");
        System.out.println();
    }

    public void printCount(int attend, int late, int absent) {
        System.out.println();
        System.out.println("출석: " + attend + "회");
        System.out.println("지각: " + late + "회");
        System.out.println("결석: " + absent + "회");
        System.out.println();
    }

    public void printResultRiskCrew() {
        System.out.println();
        System.out.println("제적 위험자 조회 결과");
    }

    public void printRiskCrew(RiskCrew riskCrew) {
        System.out.println("- " + riskCrew.takeNickName() + ": " + "결석 " +
                riskCrew.takeAttendanceCount().takeAbsent() + "회, " + "지각 " +
                riskCrew.takeAttendanceCount().takeLate() + "회 (" + riskCrew.takeGrade() + ")");
    }

    public void printAssignAttendance(LocalDateTime nowDate, String result) {
        System.out.printf("%d월 %02d일 %s %s (%s)%n",
                nowDate.getMonthValue(),
                nowDate.getDayOfMonth(),
                nowDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN),
                nowDate.toLocalTime(),
                result);
    }

    public void printAbsent(LocalDate date) {
        System.out.printf("%d월 %02d일 %s --:-- (결석)%n",
                date.getMonthValue(),
                date.getDayOfMonth(),
                date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN));
    }

    public void printTargetCrew(String judge) {
        if (judge.isEmpty()) {
            return;
        }
        System.out.println();
        System.out.println(judge + " 대상자입니다.");
        System.out.println();
    }

    public void selectFunction() {
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
    }
}
