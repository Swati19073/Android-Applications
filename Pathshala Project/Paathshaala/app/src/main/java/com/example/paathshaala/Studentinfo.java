package com.example.paathshaala;

public class Studentinfo {

    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    private String PhoneNo;
    private String DOB;
    private String Gender;
    private String Qualification;
    private String Skills;
    private String Experience;

    public String getCertificateurl() {
        return certificateurl;
    }

    public void setCertificateurl(String certificateurl) {
        this.certificateurl = certificateurl;
    }

    String certificateurl;

    public String getCoursepublished() {
        return coursepublished;
    }

    public void setCoursepublished(String coursepublished) {
        this.coursepublished = coursepublished;
    }

    String coursepublished;
    public String getCoursetaken() {
        return coursetaken;
    }

    public void setCoursetaken(String coursetaken) {
        this.coursetaken = coursetaken;
    }

    String coursetaken;


}
