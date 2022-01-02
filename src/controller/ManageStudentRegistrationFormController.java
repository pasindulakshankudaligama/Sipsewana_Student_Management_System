package controller;

import Util.ValidationUtil;
import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.impl.StudentDAOImpl;
import dto.ProgramDTO;
import dto.StudentDTO;
import entity.Student;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

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
    public JFXCheckBox cb3;
    public JFXCheckBox cb2;
    public JFXCheckBox cb;
    public Button btnAdd;
    String cmb1;
    String cmb2;
    String cmb3;

    StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);
    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PROGRAM);
    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern studentIdPattern = Pattern.compile("^(C)[-]?[0-9]{3}$");
    Pattern studentNamePattern = Pattern.compile("^[A-z ]{1,30}$");
    Pattern studentNicPattern = Pattern.compile("^[0-9]{9}[v]|[0-9]{12}$");
    Pattern studentAddressPattern = Pattern.compile("^[A-z0-9/]{6,30}$");
    Pattern studentTeleNumberPattern = Pattern.compile("^[0-9]{10}$");
    Pattern studentAgePattern = Pattern.compile("^[0-9]{2}$");
    Pattern studentEmailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    Pattern studentDobPattern = Pattern.compile("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$");

    public void initialize() {
        loadDateAndTime();
        showStudentsOnTable();
        loadProgramId();
        setDisable();
        cmbProgramId1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtPName1, txtDuration1, txtFee1, newValue);
            cmb1=newValue;
        });

        cmbProgramId2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtPName2, txtDuration2, txtFee2, newValue);
            cmb2=newValue;
        });

        cmbProgramId3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtPName3, txtDuration3, txtFee3, newValue);
            cmb3=newValue;
        });

        storeValidations();
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
        window.setResizable(false);
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
            clear();
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

        Student student = new Student();
        student.setRegNumber(txtRegNum.getText());
        student.setName(txtName.getText());
        student.setAge(Integer.parseInt(txtAge.getText()));
        student.setContactNumber(txtContactNumber.getText());
        student.setAddress(txtAddress.getText());
        student.setDob(txtDOB.getText());
        student.setEmail(txtEmail.getText());
        student.setNic(txtNIC.getText());
        student.setGender(SelectGender());

        if (studentDAO.register(student, cmb1, cmb2, cmb3)) {
            new Alert(Alert.AlertType.CONFIRMATION, "StudentDTO Add To Database").show();
            showStudentsOnTable();
            clear();
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

        if (studentDAO.updateNatively(txtRegNum.getText(),cmb1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Program Added").show();
            showStudentsOnTable();
            clear();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    private void loadProgramId() {
        List<String> allProgramIds = programBO.getAllProgramIds();
        cmbProgramId1.getItems().addAll(allProgramIds);
        cmbProgramId2.getItems().addAll(allProgramIds);
        cmbProgramId3.getItems().addAll(allProgramIds);
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        if (cb2.isSelected()) {
            cmbProgramId2.setDisable(false);
            txtPName2.setDisable(false);
            txtDuration2.setDisable(false);
            txtFee2.setDisable(false);
        } else {
            cmbProgramId2.setDisable(true);
            txtPName2.setDisable(true);
            txtDuration2.setDisable(true);
            txtFee2.setDisable(true);
        }
        if (cb3.isSelected()) {
            cmbProgramId3.setDisable(false);
            txtPName3.setDisable(false);
            txtDuration3.setDisable(false);
            txtFee3.setDisable(false);
        } else {
            cmbProgramId3.setDisable(true);
            txtPName3.setDisable(true);
            txtDuration3.setDisable(true);
            txtFee3.setDisable(true);
        }
    }

    private void setDisable() {
        cmbProgramId2.setDisable(true);
        txtPName2.setDisable(true);
        txtDuration2.setDisable(true);
        txtFee2.setDisable(true);
        cmbProgramId3.setDisable(true);
        txtPName3.setDisable(true);
        txtDuration3.setDisable(true);
        txtFee3.setDisable(true);
    }

    private void setProgramData(JFXTextField enterProgram, JFXTextField enterDuration, JFXTextField enterFee, String ProgramID) {
        ProgramDTO programDetails = programBO.getProgramDetails(ProgramID);

        if (programDetails == null) {
        } else {
            enterProgram.setText(programDetails.getProgramName());
            enterDuration.setText(programDetails.getDuration());
            enterFee.setText(programDetails.getFee() + "");
        }
    }

    public void onMouseClick1(MouseEvent mouseEvent) {
        try {
            StudentTM selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
            txtRegNum.setText(selectedStudent.getRegNumber());
            txtName.setText(selectedStudent.getName());
            txtAge.setText("" + selectedStudent.getAge());
            txtContactNumber.setText(selectedStudent.getContactNumber());
            txtAddress.setText(selectedStudent.getAddress());
            txtDOB.setText(selectedStudent.getDob());
            txtEmail.setText(selectedStudent.getEmail());
            txtNIC.setText(selectedStudent.getNic());
            if (selectedStudent.getGender().equals("Male")){
                txtMale.setSelected(true);
            }else if (selectedStudent.getGender().equals("Female")){
                txtFemale.setSelected(true);
            }

        } catch (Exception e) {

        }
    }

    private void clear() {
        txtNIC.clear();
        txtEmail.clear();
        txtContactNumber.clear();
        txtDOB.clear();
        txtAddress.clear();
        txtAge.clear();
        txtName.clear();
        txtRegNum.clear();
        txtPName1.clear();
        txtPName2.clear();
        txtPName3.clear();
        txtDuration1.clear();
        txtDuration2.clear();
        txtDuration3.clear();
        txtFee1.clear();
        txtFee2.clear();
        txtFee3.clear();
    }

    private void storeValidations() {
        map.put(txtRegNum,studentIdPattern);
        map.put(txtName,studentNamePattern);
        map.put(txtAge,studentAgePattern);
        map.put(txtNIC,studentNicPattern);
        map.put(txtContactNumber,studentTeleNumberPattern);
        map.put(txtAddress,studentAddressPattern);
        map.put(txtDOB,studentDobPattern);
        map.put(txtEmail,studentEmailPattern);
    }

    public void studentKeyEvent(KeyEvent keyEvent) {
        btnAdd.setDisable(true);
        Object response = ValidationUtil.validate(map,btnAdd);
        if (keyEvent.getCode()== KeyCode.ENTER) {
            if (response instanceof TextField){
                TextField error  = (TextField) response;
                error.requestFocus();
            }else if (response instanceof Boolean){
                new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
            }
        }
    }
}


