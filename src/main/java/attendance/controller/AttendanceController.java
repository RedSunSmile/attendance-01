package attendance.controller;

import attendance.domain.AttendanceJudge;
import attendance.domain.Crews;
import attendance.ui.InputView;
import attendance.ui.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public class AttendanceController {
    private final InputView inputView = new InputView();
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
            if (input.equals("Q")) {
                    break;
                }
                if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {
                    throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
                }

        }
    }

    private void checkAssignAttendanceOfEachCrew(String input, InputView inputView) {
        if (input.equals("3")) {
            String basket = inputView.inputAssignOfEachCrew();
            crews.validNicknames(basket);
            List<LocalDateTime> dateTimes = crews.takeRecord(basket);
            for (LocalDateTime dateTime : dateTimes) {
                outputView.printAssignAttendance(dateTime, AttendanceJudge.judge(dateTime));
            }
        }
    }

    private void checkAttendance (String input, InputView inputView){
            if (input.equals("1")) {
                String basket = inputView.inputNickName();
                crews.validNicknames(basket);
                String timeInput = inputView.inputStartClassTime();
                LocalTime time = LocalTime.parse(timeInput);
                LocalDate today = DateTimes.now().toLocalDate();
                LocalDateTime nowDate = LocalDateTime.of(today, time);
                outputView.printAttendance(nowDate, AttendanceJudge.judge(nowDate));
                crews.addRecord(basket, nowDate);
            }
        }

        private void modifyAttendance (String input, InputView inputView){
            if (input.equals("2")) {
                String basket = inputView.inputModifyNickname();
                crews.validNicknames(basket);
                String day = inputView.inputModifyDay();
                String time = inputView.inputModifyTime();
                LocalDate date = DateTimes.now().toLocalDate().withDayOfMonth(Integer.parseInt(day));
                LocalDateTime oldRecord = crews.findRecord(basket, date);

                LocalDateTime nowDate = LocalDateTime.of(date, LocalTime.parse(time));
                outputView.printModify(oldRecord, AttendanceJudge.judge(oldRecord), nowDate, AttendanceJudge.judge(nowDate));
                crews.modifyRecord(basket, oldRecord, nowDate);
            }
        }

    }
