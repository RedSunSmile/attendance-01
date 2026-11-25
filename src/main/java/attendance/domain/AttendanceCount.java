package attendance.domain;

public class AttendanceCount {
    private final int attend;
    private final int late;
    private final int absent;

    public AttendanceCount(int attend, int late, int absent) {
        this.attend = attend;
        this.late = late;
        this.absent = absent;
    }

    public int takeAttend() {
        return attend;
    }

    public int takeLate() {
        return late;
    }

    public int takeAbsent() {
        return absent;
    }
}
