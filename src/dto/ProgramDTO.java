package dto;

public class ProgramDTO {
    private String programID;
    private String programName;
    private String duration;
    private double fee;

    public ProgramDTO() {
    }

    public ProgramDTO(String programID, String programName, String duration, double fee) {
        this.setProgramID(programID);
        this.setProgramName(programName);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "ProgramDTO{" +
                "programID='" + programID + '\'' +
                ", programName='" + programName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
