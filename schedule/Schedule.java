package schedule;

public class Schedule {
    private String doctorId;
    private String doctorName;
    private String speciality;
    private String day;
    private String shift;
    private String startTime;
    private String endTime;

    public Schedule(String doctorId, String doctorName, String speciality, String day, String shift, String startTime, String endTime) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.day = day;
        this.shift = shift;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDoctorId() { return doctorId; }
    public String getDoctorName() { return doctorName; }
    public String getSpeciality() { return speciality; }
    public String getDay() { return day; }
    public String getShift() { return shift; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }

    public String toString() {
        return doctorId + "|" + doctorName + "|" + speciality + "|" + day + "|" + shift + "|" + startTime + "|" + endTime;
    }
}