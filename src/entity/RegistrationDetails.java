/*
package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RegistrationDetails implements SuperEntity {
    @Id
    private String Id;
    @ManyToOne
    @JoinColumn(name = "registerNumber", referencedColumnName = "regNumber")
    private Student regNumber;
    private String joiningDate;

    @ManyToOne
    @JoinColumn(name= "programID",referencedColumnName = "programID")
      private Program program;
    public RegistrationDetails() {
    }


    public RegistrationDetails(String Id, Student regNumber, String joiningDate, Program program) {
        this.setId(Id);
        this.setRegNumber(regNumber);
        this.setJoiningDate(joiningDate);
        this.setProgram(program);
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public Student getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(Student regNumber) {
        this.regNumber = regNumber;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "RegistrationDetails{" +
                "Id='" + Id + '\'' +
                ", regNumber=" + regNumber +
                ", joiningDate='" + joiningDate + '\'' +
                ", program=" + program +
                '}';
    }
}
*/
