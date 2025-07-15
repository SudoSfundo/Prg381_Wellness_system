// JavaBean for User entity
// - Fields: studentNumber, name, surname, email, phone, password
// - Getters and setters for each field
// - Optional: constructors and toString() method

package model;

public class User {
    private String studentNumber;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;

    public User() {
    }

    public User(String nameAndSurname, String email, String phone, String password) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.studentNumber = email.substring(0, 6);
        String[] parts = nameAndSurname.trim().split(" ", 2);
        this.name = parts.length > 0 ? parts[0] : "";
        this.surname = parts.length > 1 ? parts[1] : "";
    }

    public User(String studentNumber, String name, String surname, String email, String phone, String password) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "studentNumber='" + studentNumber + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';

    }
}
