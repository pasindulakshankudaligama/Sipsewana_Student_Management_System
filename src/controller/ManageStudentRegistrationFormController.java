package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.ProgramTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ManageStudentRegistrationFormController {
    public AnchorPane srContext;
    public Label lblDate;
    public Label lblTime;
    public TableView<StudentTM> tblStudent;
    public TableColumn colRegNum;
    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colNIC;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colDOB;
    public TableColumn colEmail;
    public TableColumn colGender;
    public JFXTextField txtRegNum;
    public JFXTextField txtName;
    public JFXTextField txtDOB;
    public JFXTextField txtContactNumber;
    public JFXTextField txtAddress;
    public JFXTextField txtAge;
    public JFXTextField txtEmail;

    public JFXTextField txtPName1;
    public JFXTextField txtFee1;
    public JFXTextField txtDuration1;

    public JFXTextField txtPName2;
    public JFXTextField txtFee2;
    public JFXTextField txtDuration2;

    public JFXTextField txtPName3;
    public JFXTextField txtFee3;
    public JFXTextField txtDuration3;
    public JFXTextField txtNIC;
    public JFXRadioButton txtMale;
    public ToggleGroup gender;
    public RadioButton txtFemale;
    public JFXTextField txtSearch;
    public JFXComboBox<String> cmbProgramId1;
    public JFXComboBox<String> cmbProgramId2;
    public JFXComboBox<String> cmbProgramId3;


    StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);
    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PROGRAM);

    public void initialize() {
        loadDateAndTime();
        showStudentsOnTable();
        loadProgramId();

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void btnBackToHomeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) srContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
        //window.setResizable(false);
    }

    public void keyEvent(KeyEvent keyEvent) {
        ObservableList<StudentTM> search = studentBO.search(txtSearch.getText());
        tblStudent.setItems(search);
    }

    public void removeProgramOnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        String studentId = selectedItem.getRegNumber();

        if (studentBO.delete(studentId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            showStudentsOnTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public String SelectGender() {
        String selectGender = null;
        if (txtMale.isSelected()) {
            selectGender = "Male";
        } else if (txtFemale.isSelected()) {
            selectGender = "Female";
        }
        return selectGender;
    }

    public void saveProgramOnAction(ActionEvent actionEvent) {

        StudentDTO studentDTO = new StudentDTO(
                txtRegNum.getText(),
                txtName.getText(),
                Integer.parseInt(txtAge.getText()),
                txtContactNumber.getText(),
                txtAddress.getText(),
                txtDOB.getText(),
                txtEmail.getText(),
                txtNIC.getText(),
                SelectGender()
        );
        if (studentBO.add(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "StudentDTO Add To Database").show();
            showStudentsOnTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    private void showStudentsOnTable() {
        ObservableList<StudentTM> list = studentBO.find();

        colRegNum.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblStudent.setItems(list);
    }


    public void updateProgramOnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        String studentId = selectedItem.getRegNumber();

        StudentDTO studentDTO = new StudentDTO(
                txtRegNum.getText(),
                txtName.getText(),
                Integer.parseInt(txtAge.getText()),
                txtContactNumber.getText(),
                txtAddress.getText(),
                txtDOB.getText(),
                txtEmail.getText(),
                txtNIC.getText(),
                SelectGender()
        );
        if (studentBO.update(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Program Updated").show();
            showStudentsOnTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
    private void loadProgramId(){
        List<String> allProgramIds = programBO.getAllProgramIds();
        cmbProgramId1.getItems().addAll(allProgramIds);
        cmbProgramId2.getItems().addAll(allProgramIds);
        cmbProgramId3.getItems().addAll(allProgramIds);

    }
}

