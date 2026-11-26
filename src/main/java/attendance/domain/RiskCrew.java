package attendance.domain;

public class RiskCrew {
    private final String nickName;
    private final AttendanceCount attendanceCount;
    private final String grade;

    public RiskCrew(String nickName, AttendanceCount attendanceCount, String grade) {
        this.nickName = nickName;
        this.attendanceCount = attendanceCount;
        this.grade = grade;
    }

    public String takeNickName() {
        return nickName;
    }

    public AttendanceCount takeAttendanceCount() {
        return attendanceCount;
    }

    public String takeGrade() {
        return grade;
    }

    public int takeScore() {
        int score = attendanceCount.takeAbsent() * 3;
        score += attendanceCount.takeLate();
        return score;
    }

    public int takeGradeOrder() {
        if (grade.equals("제적")) return 0;

        if (grade.equals("면담")) return 1;

        if (grade.equals("경고")) return 2;

        throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
    }
}
