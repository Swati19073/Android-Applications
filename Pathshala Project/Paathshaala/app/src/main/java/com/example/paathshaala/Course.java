package com.example.paathshaala;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable
{
    private String key;
    private String courseName;
    private String instructorName;
    private String startDate;
    private String fee;
    Course(){}
    public Course(String key, String courseName, String instructorName, String startDate, String fee) {
        this.key = key;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.startDate = startDate;
        this.fee = fee;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
