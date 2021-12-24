package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RegistrationDetails implements SuperEntity {
    @Id
    private String programID;
    @ManyToOne
    @JoinColumn(name = "registerNumber", referencedColumnName = "regNumber")
    private String regNumber;
    private String joiningDate;

    public RegistrationDetails() {
    }

    public RegistrationDetails(String programID, String regNumber, String joiningDate) {
        this.setProgramID(programID);
        this.setRegNumber(regNumber);
        this.setJoiningDate(joiningDate);
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {
        return "RegistrationDetails{" +
                "programID='" + programID + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                '}';
    }
}
