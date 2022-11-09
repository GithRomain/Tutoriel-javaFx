package com.example.tutorieljavafx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    public List<Student> loadStudents(){
        List<Student> studentAll= new ArrayList<Student>();
        Connection myConn= this.Connector();
        try {
            Statement myStmt= myConn.createStatement();
            String sql = "select * from studenttable";
            ResultSet myRs= myStmt.executeQuery(sql);
            while (myRs.next()) {
                Student s= new Student(myRs.getInt("id"),myRs.getString("name"), myRs.getString("gender"), myRs.getInt("grade"));
                studentAll.add(s);
            }
            this.close(myConn, myStmt, myRs);
            return studentAll;
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection Connector(){
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=Europe%2FParis", "","");
            return connection;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try{
            if(myStmt!=null)
                myStmt.close();
            if(myRs!=null)
                myRs.close();
            if(myConn!=null)
                myConn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addStudent(Student student){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "INSERT INTO studenttable (name,gender,grade) VALUES (?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, student.getName());
            myStmt.setString(2, student.getGender());
            myStmt.setInt(3, student.getGrade());
            myStmt.execute();
            System.out.println("Adding ok");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }

    public void updateStudent(Student student, String newName, String newGender, int newGrade){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "UPDATE studenttable SET (name,gender,grade) VALUES (?, ?, ?) WHERE id = ?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, newName);
            myStmt.setString(2, newGender);
            myStmt.setInt(3, newGrade);
            myStmt.setInt(4, student.getId());
            myStmt.executeUpdate();
            System.out.println("Execute ok");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }

    public void deleteStudent(Student student){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "DELETE FROM studenttable WHERE id = ?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, student.getId());
            myStmt.execute();
            System.out.println("Deleting ok");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }
}
