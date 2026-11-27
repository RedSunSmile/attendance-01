package attendance.controller;

import attendance.domain.AttendanceJudge;
import attendance.domain.Crews;
import attendance.domain.RiskCrew;
import attendance.ui.InputView;
import attendance.ui.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class AttendanceController {
    private final OutputView outputView = new OutputView();
    private Crews crews;

    public AttendanceController(Crews crews) {
        this.crews = crews;
    }

    public void run() {

        while (true) {
            outputView.printFunction();
            outputView.selectFunction();
            InputView inputView = new InputView();
            String input = inputView.inputMenu();
            checkAttendance(input, inputView);
            modifyAttendance(input, inputView);
            checkAssignAttendanceOfEachCrew(input, inputView);
            checkCrewOfBeingAbleToOut(input);
            if (input.equals("Q")) {
                break;
            }
            if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {
                throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
            }

        }
    }

    private void checkCrewOfBeingAbleToOut(String input) {
        if (!input.equals("4")) {
            return;
        }
        outputView.printResultRiskCrew();
        List<RiskCrew> riskCrews = crews.takeRiskCrews();
        for (RiskCrew riskCrew : riskCrews) {
            outputView.printRiskCrew(riskCrew);
        }
    }

    private void checkAssignAttendanceOfEachCrew(String input, InputView inputView) {
        if (!input.equals("3")) {
            return;
        }
        String basket = inputView.inputAssignOfEachCrew();
        crews.validNicknames(basket);
        outputView.printRecordTitle(basket);
        int attend = 0, late = 0, absent = 0;
        for (int day = 1; day < DateTimes.now().getDayOfMonth(); day++) {
            LocalDate date = DateTimes.now().toLocalDate().withDayOfMonth(day);
            if (date.getMonthValue() == 12 && day == 25) {
                continue;
            }

            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                continue;
            }

            LocalDateTime record = crews.findRecord(basket, date);
            if (record == null) {
                outputView.printAbsent(date);
                absent++;
                continue;
            }
            String result = AttendanceJudge.judge(record);
            if (result.equals("출석")) attend++;
            if (result.equals("지각")) late++;
            if (result.equals("결석")) absent++;
            outputView.printAssignAttendance(record, result);
        }
        outputView.printCount(attend, late, absent);
        outputView.printTargetCrew(AttendanceJudge.judgeCrew(late, absent));
    }


    private void checkAttendance(String input, InputView inputView) {
        LocalDate today = DateTimes.now().toLocalDate();
        if (input.equals("1")) {
            if (AttendanceJudge.isNotSchoolDay(today)) {
                throw new IllegalArgumentException("[ERROR] " +
                        today.getMonthValue() + "월 " +
                        today.getDayOfMonth() + "일 " +
                        today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN) +
                        "은 등교일이 아닙니다.");
            }
            String basket = inputView.inputNickName();
            crews.validNicknames(basket);
            if (crews.findRecord(basket, today) != null) {
                throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
            }
            LocalTime time = inputView.inputStartClassTime();
            if (time.isBefore(LocalTime.of(8, 0)) || time.isAfter(LocalTime.of(23, 0))) {
                throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
            }
            LocalDateTime nowDate = LocalDateTime.of(today, time);
            outputView.printAttendance(nowDate, AttendanceJudge.judge(nowDate));
            crews.addRecord(basket, nowDate);
        }
    }

    private void modifyAttendance(String input, InputView inputView) {
        if (input.equals("2")) {
            String basket = inputView.inputModifyNickname();
            crews.validNicknames(basket);
            int day = inputView.inputModifyDay();
            LocalDate date = DateTimes.now().toLocalDate().withDayOfMonth(day);
            if(AttendanceJudge.isNotSchoolDay(date)){
                throw new IllegalArgumentException("[ERROR] " +
                        date.getMonthValue() + "월 " +
                        date.getDayOfMonth() + "일 " +
                        date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN) +
                        "은 등교일이 아닙니다.");
            }
            if (day > DateTimes.now().getDayOfMonth()) {
                throw new IllegalArgumentException("[ERROR] 아직 수정할 수 없습니다.");
            }
            LocalTime time = inputView.inputModifyTime();

            LocalDateTime oldRecord = crews.findRecord(basket, date);

            LocalDateTime nowDate = LocalDateTime.of(date, time);
            outputView.printModify(oldRecord, AttendanceJudge.judge(oldRecord), nowDate, AttendanceJudge.judge(nowDate));
            crews.modifyRecord(basket, oldRecord, nowDate);
        }
    }
}
