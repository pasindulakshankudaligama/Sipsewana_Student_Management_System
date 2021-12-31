package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student implements SuperEntity {
    @Id
    private String regNumber;
    private String name;
    private int age;
    private String contactNumber;
    private String address;
    private String dob;
    private String email;
    private String nic;
    private String gender;

    @ManyToMany
    private List<Program> programList = new ArrayList<>();

    public Student() {
    }

    public Student(String regNumber, String name, int age, String contactNumber, String address, String dob, String email, String nic, String gender) {
        this.setRegNumber(regNumber);
        this.setName(name);
        this.setAge(age);
        this.setContactNumber(contactNumber);
        this.setAddress(address);
        this.setDob(dob);
        this.setEmail(email);
        this.setNic(nic);
        this.setGender(gender);
    }

    public Student(String regNumber, String name, int age, String contactNumber, String address, String dob, String email, String nic, String gender, List<Program> programList) {
        this.regNumber = regNumber;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.nic = nic;
        this.gender = gender;
        this.programList = programList;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "regNumber='" + regNumber + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", nic='" + nic + '\'' +
                ", gender='" + gender + '\'' +
                ", programList=" + programList +
                '}';
    }
}
