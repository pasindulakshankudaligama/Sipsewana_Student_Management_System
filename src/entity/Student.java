package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student implements SuperEntity {
    @Id
    private String regNumber;
    private String name;
    private int age;
    private String contactNumber;
    private String address;
    private String email;


    public Student() {
    }

    public Student(String regNumber, String name, int age, String contactNumber, String address, String email) {
        this.setRegNumber(regNumber);
        this.setName(name);
        this.setAge(age);
        this.setContactNumber(contactNumber);
        this.setAddress(address);
        this.setEmail(email);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "regNumber='" + regNumber + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
