package attendance;

import attendance.controller.AttendanceController;
import attendance.domain.Crews;

public class Application {
    public static void main(String[] args) {
        Crews crews = new Crews(Crews.readNickname());
        AttendanceController attendanceController = new AttendanceController(crews);
        attendanceController.run();
    }
}
