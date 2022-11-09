package com.example.tutorieljavafx;

public class Student {
    private int id;
    private String Name;
    private String Gender;
    private int Grade;

    //Constructor
    public Student(String Name, String Gender, int Grade) throws Exception {
        this.Name = Name;
        this.Gender = Gender;
        if (Grade >= 0){
            this.Grade = Grade;
        }
        else {
            throw new Exception("Grade is negative");
        }
    }

    public Student(int id, String Name, String Gender, int Grade) throws Exception {
        this.id = id;
        this.Name = Name;
        this.Gender = Gender;
        if (Grade >= 0){
            this.Grade = Grade;
        }
        else {
            throw new Exception("Grade is negative");
        }
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public int getGrade() {
        return Grade;
    }

    @Override
    public String toString() {
        return Name + ", " + Gender + ", " + Grade;
    }
}
