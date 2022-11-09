package com.example.tutorieljavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    DBManager manager;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private ListView<Student> lvStudents;

    @FXML
    private TextField txtName;

    @FXML
    private TextField intGrade;

    @FXML
    private TextField txtAverage;

    private void displayStudentDetails(Student selectedStudent) {
        if(selectedStudent!=null){
            txtName.setText(selectedStudent.getName());
            cmbGender.setValue(selectedStudent.getGender());
            intGrade.setText("" + selectedStudent.getGrade());
        }
    }

    public void fetchStudents() {
        List<Student> listStudents = manager.loadStudents();
        if (listStudents != null) {
            ObservableList<Student> students;
            students = FXCollections.observableArrayList(listStudents);
            lvStudents.setItems(students.sorted(Comparator.comparing(Student::getGrade)));
            calculateAverage();
        }
    }

    public void calculateAverage(){
        int avg = 0;
            for (Student student : lvStudents.getItems()){
                avg += student.getGrade();
        }
        this.txtAverage.setText("" + avg/lvStudents.getItems().size());
    }

    public void onNew(){
        lvStudents.getSelectionModel().clearSelection();
        this.txtName.setText("");
        this.cmbGender.setValue(null);
        this.intGrade.setText("");
    }

    public void onCancel(){
        lvStudents.getSelectionModel().selectFirst();
    }

    public void onSave() throws Exception {
        Student s = new Student(txtName.getText(), cmbGender.getValue(), Integer.parseInt(intGrade.getText()));
        manager.addStudent(s);
        fetchStudents();
    }

    public void onDelete() throws Exception {
        manager.deleteStudent(lvStudents.getSelectionModel().getSelectedItem());
        fetchStudents();
        this.onCancel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manager = new DBManager();
        List<String> gvalues = new ArrayList<String>();
        gvalues.add("Male");
        gvalues.add("Female");
        ObservableList<String> gender = FXCollections.observableArrayList(gvalues);
        cmbGender.setItems(gender);
        fetchStudents();
    }
}