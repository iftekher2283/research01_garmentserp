/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textileerp;

import com.itextpdf.text.Document;
import enums.BankCode;
import enums.BloodGroup;
import enums.BranchID;
import enums.DepartmentCode;
import enums.Designation;
import enums.Gender;
import enums.MaritalStatus;
import enums.Religion;
import hibernatesingleton.HibernateSingleton;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.jnlp.PrintService;
import javax.print.DocFlavor;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import md5.HashMD5;
import model.Address;
import model.Employee;
import model.PersonalInformation;
import model.Salary;
import model.SalaryDetails;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import printpdfs.PdfEmployee;

/**
 * FXML Controller class
 *
 * @author iftekher
 */
public class HRPanelUIController implements Initializable {
    @FXML
    private ComboBox<Integer> companyNoBox;
    @FXML
    private ComboBox<Designation> designationBox;
    @FXML
    private ComboBox<DepartmentCode> deptCodeBox;
    @FXML
    private ComboBox<Gender> genderBox;
    @FXML
    private ComboBox<BranchID> branchIDBox;
    @FXML
    private DatePicker confirmationDatePicker;
    @FXML
    private DatePicker joiningDatePicker;
    @FXML
    private TextField employeeNameField;
    @FXML
    private TextField idNoField;
    @FXML
    private TextField grossSalaryField;
    @FXML
    private TextField basicSalaryField;
    @FXML
    private TextField houseRentField;
    @FXML
    private TextField runningBasicField;
    @FXML
    private TextField medicalAllowField;
    @FXML
    private TextField othersAllowField;
    @FXML
    private TextField conveyanceField;
    @FXML
    private TextField carAllowField;
    @FXML
    private TextField accountNoField;
    @FXML
    private ComboBox<BankCode> bankCodeBox;
    @FXML
    private TextField fathersNameField;
    @FXML
    private TextField mothersNameField;
    @FXML
    private TextField presHouseNoField;
    @FXML
    private TextField presRoadNoField;
    @FXML
    private TextField presVillageField;
    @FXML
    private TextField presPOField;
    @FXML
    private TextField presPSField;
    @FXML
    private TextField presDistField;
    @FXML
    private TextField presPhnField;
    @FXML
    private TextField educationalQualificationField;
    @FXML
    private TextField technicalQualificationField;
    @FXML
    private TextField nationalIdNoField;
    @FXML
    private TextField mobileNoField;
    @FXML
    private TextField birthPlaceField;
    @FXML
    private TextField tinNoField;
    @FXML
    private TextField emailAddressField;
    @FXML
    private TextField passportNoField;
    @FXML
    private TextField emergencyContNo;
    @FXML
    private TextField permHouseNoField;
    @FXML
    private TextField permRoadNoField;
    @FXML
    private TextField permVillageField;
    @FXML
    private TextField permPOField;
    @FXML
    private TextField permPSField;
    @FXML
    private TextField permDistField;
    @FXML
    private TextField permPhnField;
    @FXML
    private ComboBox<Religion> religionBox;
    @FXML
    private ComboBox<MaritalStatus> maritalStatusBox;
    @FXML
    private ComboBox<BloodGroup> bloodGroupBox;
    @FXML
    private TextField ageField;
    @FXML
    private TextField spouseField;
    @FXML
    private TextField nationalityField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField childrenField;
    @FXML
    private TextField birthDateField;
    @FXML
    private TextField birthMonthField;
    @FXML
    private TextField birthYearField;
    @FXML
    private TableView<Employee> employeeViewTable;
    @FXML
    private TableColumn<Employee, String> employeeIdColumn;
    @FXML
    private TableColumn<Employee, String> employeeNameColumn;
    @FXML
    private TableColumn<Employee, String> employeeSexColumn;
    @FXML
    private TableColumn<Employee, Number> employeeCompanyNoColumn;
    @FXML
    private TableColumn<Employee, String> deptCodeColumn;
    @FXML
    private TableColumn<Employee, String> branchIdColumn;
    @FXML
    private TableColumn<Employee, String> joiningDateColumn;
    @FXML
    private TableColumn<Employee, String> employeeDesignationColumn;
    @FXML
    private Text hrmIdText;
    
    
    
    //Salary Details Tab
    @FXML
    private TableView<SalaryDetails> salaryDetailsTable;
    @FXML
    private TableColumn<SalaryDetails, String> employeeIdSalaryDetailsColumn;
    @FXML
    private TableColumn<SalaryDetails, Number> basicSalaryColumn;
    @FXML
    private TableColumn<SalaryDetails, Number> runningBasicColumn;
    @FXML
    private TableColumn<SalaryDetails, Number> houseRentColumn;
    @FXML
    private TableColumn<SalaryDetails, Number> medicalColumn;
    @FXML
    private TableColumn<SalaryDetails, Number> otherColumn;
    @FXML
    private TableColumn<SalaryDetails, Number> conveyanceColumn;
    @FXML
    private TableColumn<SalaryDetails, Number> carAllowColumn;
    @FXML
    private TableColumn<SalaryDetails, Number> grossSalaryColumn;
    @FXML
    private TableColumn<SalaryDetails, String> bankCodeColumn;
    @FXML
    private TableColumn<SalaryDetails, String> acNoColumn;
    @FXML
    private Text hrmSalariesIdText;    
    
    // Profile FXML
    @FXML
    private Text profileDesignationText;
    @FXML
    private Text profileJoiningDateText;
    @FXML
    private Text profileConfirmationDateText;
    @FXML
    private Text profileBranchText;
    @FXML
    private Text profileDepartmentText;
    @FXML
    private Text profileCompanyNoText;
    @FXML
    private Text profileNameText;
    @FXML
    private Text profileEmployeeIdText;
    @FXML
    private Text profileNidNoText;
    @FXML
    private Text profileEmailText;
    @FXML
    private Text profilePhoneText;
    @FXML
    private Text profileEduQualiText;
    @FXML
    private Text profilePassportNoText;
    @FXML
    private Text profileReligionText;
    @FXML
    private Text profileGenderText;
    @FXML
    private Text profileDateOfBirthText;
    @FXML
    private Text profileMotherNameText;
    @FXML
    private Text profileFatherNameText;
    @FXML
    private Text profileBloodGroupText;
    @FXML
    private Text profileNationalityText;
    @FXML
    private PasswordField profileOldPasswordField;
    @FXML
    private PasswordField profileRetypeNewPasswordField;
    @FXML
    private PasswordField profileNewPasswordField;
    @FXML
    private Text profileUsernameText;
    @FXML
    private Text hrmChangePasswordMessageText;
    @FXML
    private Text hrmProfileIdText;
    
    //Session Variables to Access Hibernate
    private static SessionFactory factory;
    private static Session session;
    
    //Required Lists
    private List<Employee> employees;
    private List<User> users;
    
    //Required ObservableLists
    private ObservableList<Employee> employeesTable;
    private ObservableList<Integer> companyNo;
    private ObservableList<SalaryDetails> salaries;
    
    //Required Global Variables
    private String employeeId;
    private Employee employee;
    private User user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize Session Variables to Access Hibernate
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        
        // Set Values of Combo Boxes
        companyNo = FXCollections.observableArrayList();
        companyNo.remove(0, companyNo.size());
        for (int i = 1; i <= 5; i++){
            companyNo.add(i);
        }
        companyNoBox.setItems(companyNo);
        designationBox.getItems().addAll(Designation.values());
        deptCodeBox.getItems().addAll(DepartmentCode.values());
        genderBox.getItems().addAll(Gender.values());
        branchIDBox.getItems().addAll(BranchID.values());
        bankCodeBox.getItems().addAll(BankCode.values());
        religionBox.getItems().addAll(Religion.values());
        maritalStatusBox.getItems().addAll(MaritalStatus.values());
        bloodGroupBox.getItems().addAll(BloodGroup.values());
        
        //Initialize Employees' List and Get Stored Information of All Employees
        employees = new ArrayList<>();
        users = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        try {
            employees = session.createCriteria(Employee.class).list();
            users = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        //Display Employees' Information on the Table View
        employeesTable = FXCollections.observableArrayList();
        employeesTable.remove(0, (employeesTable.size() - 1));
        for (int i = 0; i < employees.size(); i++){
            if(employees.get(i).getIsDeleted() == 0){
                employeesTable.add(employees.get(i));
            }
        }
        employeeViewTable.setItems(employeesTable);
        employeeIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getId()));
        employeeNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        employeeSexColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSex()));
        employeeCompanyNoColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getCompanyNo()));
        deptCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartmentCode()));
        branchIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBranchId()));
        joiningDateColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getJoiningDate()));
        employeeDesignationColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDesignation()));
        
        //Display Employees' Salary Information on the Table View
        salaries = FXCollections.observableArrayList();
        salaries.remove(0, (salaries.size() - 1));
        for (int i = 0; i < employees.size(); i++){
            SalaryDetails salaryDetails = new SalaryDetails(employees.get(i).getId(), employees.get(i).getSalaryInfo().getBasic_salary(), employees.get(i).getJoiningDate(), employees.get(i).getSalaryInfo().getBank_code(), employees.get(i).getSalaryInfo().getAc_no());
            salaries.add(salaryDetails);
        }
        salaryDetailsTable.setItems(salaries);
        employeeIdSalaryDetailsColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getId()));
        basicSalaryColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getBasicSalary()));
        runningBasicColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getRunningBasic()));
        houseRentColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getHouseRent()));
        medicalColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getMedical()));
        otherColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getOthers()));
        conveyanceColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getConveyance()));
        carAllowColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getCarAllow()));
        grossSalaryColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getGrossSalary()));
        bankCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBankCode()));
        acNoColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAcNo()));
    }    

    @FXML
    private void handleIDAction(ActionEvent event) {
        String id = idNoField.getText();
        Employee employee = null;
        for (int i = 0; i < employees.size(); i++){
            if(employees.get(i).getId() == id){
                employee = employees.get(i);
                break;
            }
        }
        if(!employee.getName().equals("")){
            employeeNameField.setText(employee.getName());
            companyNoBox.getSelectionModel().select(employee.getCompanyNo() - 1);
            designationBox.getSelectionModel().select(Designation.valueOf(employee.getDesignation()));
            confirmationDatePicker.getEditor().setText(employee.getConfirmationDate());
            deptCodeBox.getSelectionModel().select(DepartmentCode.valueOf(employee.getDepartmentCode()));
            joiningDatePicker.getEditor().setText(employee.getJoiningDate());
            genderBox.getSelectionModel().select(Gender.valueOf(employee.getSex()));
            branchIDBox.getSelectionModel().select(BranchID.valueOf(employee.getBranchId()));
            
            double basic_salary = employee.getSalaryInfo().getBasic_salary();
            String join_date = joiningDatePicker.getEditor().getText();
            String join_date_tokens[] = join_date.split("/");
            int join_year = Integer.parseInt(join_date_tokens[2]);
            int join_month = Integer.parseInt(join_date_tokens[1]);
            Date get_year = new Date();
            SimpleDateFormat years = new SimpleDateFormat("yyyy");
            int pres_year = Integer.parseInt(years.format(get_year));
            Date get_months = new Date();
            SimpleDateFormat months = new SimpleDateFormat("MM");
            int pres_month = Integer.parseInt(months.format(get_months));
            int job_years = 0;
            if(join_month > pres_month){
                job_years = pres_year - (join_year + 1);
            }
            else{
                job_years = pres_year - join_year;
            }

            double running_basic = 0;
            if(job_years > 0){
                running_basic = basic_salary + (basic_salary * job_years  * (5.0 / 100));
            }
            else{
                running_basic = basic_salary;
            }
            double house_rent = (running_basic * 40) / 100;
            double medical = (running_basic * 10) / 100;
            double others = (running_basic * 5) / 100;
            double conveyance = (running_basic * 5) / 100;
            double car_allow = (running_basic * 10) / 100;
            double gross_salary = running_basic + house_rent + medical + others + conveyance + car_allow;
            
            basicSalaryField.setText("" + basic_salary);            
            runningBasicField.setText("" + running_basic);
            houseRentField.setText("" + house_rent);
            medicalAllowField.setText("" + medical);
            othersAllowField.setText("" + others);
            conveyanceField.setText("" + conveyance);
            carAllowField.setText("" + car_allow);
            grossSalaryField.setText("" + gross_salary);
            bankCodeBox.getSelectionModel().select(BankCode.valueOf(employee.getSalaryInfo().getBank_code()));
            accountNoField.setText("" + employee.getSalaryInfo().getAc_no());
            
            fathersNameField.setText(employee.getPersonalInfo().getFathersName());
            mothersNameField.setText(employee.getPersonalInfo().getMothersName());
            religionBox.getSelectionModel().select(Religion.valueOf(employee.getPersonalInfo().getReligion()));
            maritalStatusBox.getSelectionModel().select(MaritalStatus.valueOf(employee.getPersonalInfo().getMaritalStatus()));
            String dateOfBirth = employee.getPersonalInfo().getDateOfBirth();
            String birth_date_tokens[] = dateOfBirth.split("-");
            birthDateField.setText(birth_date_tokens[0]);
            birthMonthField.setText(birth_date_tokens[1]);
            birthYearField.setText(birth_date_tokens[2]);
            int birth_month = Integer.parseInt(birth_date_tokens[1]);
            int birth_year = Integer.parseInt(birth_date_tokens[2]);
            int age;
            if(birth_month > pres_month){
                age = pres_year - (birth_year + 1);
            }
            else{
                age = pres_year - birth_year;
            }
            ageField.setText("" + age);
            spouseField.setText(employee.getPersonalInfo().getSpouse());
            bloodGroupBox.getSelectionModel().select(BloodGroup.valueOf(employee.getPersonalInfo().getBloodGroup()));
            nationalityField.setText(employee.getPersonalInfo().getNationality());
            heightField.setText("" + employee.getPersonalInfo().getHeight());
            weightField.setText("" + employee.getPersonalInfo().getWeight());
            childrenField.setText("" + employee.getPersonalInfo().getChildren());
            educationalQualificationField.setText(employee.getPersonalInfo().getEduQuali());
            technicalQualificationField.setText(employee.getPersonalInfo().getTechQuali());
            nationalIdNoField.setText(employee.getPersonalInfo().getNidNo());
            mobileNoField.setText(employee.getPersonalInfo().getMobileNo());
            birthPlaceField.setText(employee.getPersonalInfo().getBirthPlace());
            tinNoField.setText(employee.getPersonalInfo().getTinNo());
            emailAddressField.setText(employee.getPersonalInfo().getEmail());
            passportNoField.setText(employee.getPersonalInfo().getPassportNo());
            emergencyContNo.setText(employee.getPersonalInfo().getEmerContNo());
            
            presHouseNoField.setText(employee.getAddress().getPresHouseNo());
            presRoadNoField.setText(employee.getAddress().getPresRoadNo());
            presVillageField.setText(employee.getAddress().getPresVillage());
            presPOField.setText(employee.getAddress().getPresPO());
            presPSField.setText(employee.getAddress().getPresPS());
            presDistField.setText(employee.getAddress().getPresDist());
            presPhnField.setText(employee.getAddress().getPresPhn());
            permHouseNoField.setText(employee.getAddress().getPermHouseNo());
            permRoadNoField.setText(employee.getAddress().getPermRoadNo());
            permVillageField.setText(employee.getAddress().getPermVillage());
            permPOField.setText(employee.getAddress().getPermPO());
            permPSField.setText(employee.getAddress().getPermPS());
            permDistField.setText(employee.getAddress().getPresDist());
            permPhnField.setText(employee.getAddress().getPermPhn());
        }
    }

    @FXML
    private void handleSalaryAction(ActionEvent event) {
        double basic_salary = Double.parseDouble(basicSalaryField.getText());
        runningBasicField.setText("");
        houseRentField.setText("");
        medicalAllowField.setText("");
        othersAllowField.setText("");
        conveyanceField.setText("");
        carAllowField.setText("");
        grossSalaryField.setText("");
        
        String join_date = joiningDatePicker.getEditor().getText();
        String join_date_tokens[] = join_date.split("/");
        int join_year = Integer.parseInt(join_date_tokens[2]);
        int join_month = Integer.parseInt(join_date_tokens[1]);
        Date get_year = new Date();
        SimpleDateFormat years = new SimpleDateFormat("yyyy");
        int pres_year = Integer.parseInt(years.format(get_year));
        Date get_months = new Date();
        SimpleDateFormat months = new SimpleDateFormat("MM");
        int pres_month = Integer.parseInt(months.format(get_months));
        int job_years = 0;
        if(join_month > pres_month){
            job_years = pres_year - (join_year + 1);
        }
        else{
            job_years = pres_year - join_year;
        }
        
        double running_basic = 0;
        if(job_years > 0){
        running_basic = basic_salary + (basic_salary * job_years  * (5.0 / 100));
        }
        else{
            running_basic = basic_salary;
        }
        runningBasicField.setText("" + running_basic);
        double house_rent = (running_basic * 40) / 100;
        houseRentField.setText("" + house_rent);
        double medical = (running_basic * 10) / 100;
        medicalAllowField.setText("" + medical);
        double others = (running_basic * 5) / 100;
        othersAllowField.setText("" + others);
        double conveyance = (running_basic * 5) / 100;
        conveyanceField.setText("" + conveyance);
        double car_allow = (running_basic * 5) / 100;
        carAllowField.setText("" + car_allow);
        double gross_salary = running_basic + house_rent + medical + others + conveyance + car_allow;
        grossSalaryField.setText("" + gross_salary);
    }

    @FXML
    private void handleAgeAction(ActionEvent event) {
        int birth_year = Integer.parseInt(birthYearField.getText());
        int birth_month = Integer.parseInt(birthMonthField.getText());
        Date months = new Date();
        SimpleDateFormat month = new SimpleDateFormat("MM");
        int pres_month = Integer.parseInt(month.format(months));
        Date years = new Date();
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        int pres_year = Integer.parseInt(year.format(years));
        int age;
        if(birth_month > pres_month){
            age = pres_year - (birth_year + 1);
        }
        else{
            age = pres_year - birth_year;
        }
        ageField.setText("" + age);
    }
    
    @FXML
    private void handleSaveAction(ActionEvent event) {
        //Collect Necessary Data for Employee
        String id = idNoField.getText();
        String name = employeeNameField.getText();
        int company_no = Integer.parseInt(companyNoBox.getSelectionModel().getSelectedItem() + "");
        String designation = designationBox.getSelectionModel().getSelectedItem() + "";
        String confirmation_date = confirmationDatePicker.getEditor().getText();
        String dept_code = deptCodeBox.getSelectionModel().getSelectedItem() + "";
        String joining_date = joiningDatePicker.getEditor().getText();
        String sex = genderBox.getSelectionModel().getSelectedItem() + "";
        String branch_id = branchIDBox.getSelectionModel().getSelectedItem() + "";
        
        //Collect Necessary Data and Instantiate Salary
        double basic_salary = Double.parseDouble(basicSalaryField.getText());
        String bank_code = bankCodeBox.getSelectionModel().getSelectedItem() + "";
        String ac_no = accountNoField.getText();
        Salary salary = new Salary(basic_salary, bank_code, ac_no);
        
        //Collect Necessary Data and Instantiate PersonalInformation
        String fathers_name = fathersNameField.getText();
        String mothers_name = mothersNameField.getText();
        String religion = religionBox.getSelectionModel().getSelectedItem() + "";
        String marital_status = maritalStatusBox.getSelectionModel().getSelectedItem() + "";
        String date_of_birth = birthDateField.getText() + "-" + birthMonthField.getText() + "-" + birthYearField.getText();
        String spouse = spouseField.getText();
        String blood_group = bloodGroupBox.getSelectionModel().getSelectedItem() + "";
        String nationality = nationalityField.getText();
        double height = Double.parseDouble(heightField.getText());
        double weight = Double.parseDouble(weightField.getText());
        int children = Integer.parseInt(childrenField.getText());
        String edu_quali = educationalQualificationField.getText();
        String tech_quali = technicalQualificationField.getText();
        String nid_no = nationalIdNoField.getText();
        String mobile_no = mobileNoField.getText();
        String birth_place = birthPlaceField.getText();
        String tin_no = tinNoField.getText();
        String email = emailAddressField.getText();
        String passport_no = passportNoField.getText();
        String emer_cont_no = emergencyContNo.getText();
        PersonalInformation personal_info = new PersonalInformation(fathers_name, mothers_name, date_of_birth, nationality, birth_place, blood_group, religion, marital_status, spouse, children, height, weight, edu_quali, tech_quali, nid_no, passport_no, tin_no, email, mobile_no, emer_cont_no);
        
        //Collect Necessary Data and Instantiate Address
        String pres_house_no = presHouseNoField.getText();
        String pres_road_no = presRoadNoField.getText();
        String pres_village = presVillageField.getText();
        String pres_po = presPOField.getText();
        String pres_ps = presPSField.getText();
        String pres_dist = presDistField.getText();
        String pres_phn = presPhnField.getText();
        String perm_house_no = permHouseNoField.getText();
        String perm_road_no = permRoadNoField.getText();
        String perm_village = permVillageField.getText();
        String perm_po = permPOField.getText();
        String perm_ps = permPSField.getText();
        String perm_dist = permDistField.getText();
        String perm_phn = permPhnField.getText();
        
        Address address = new Address(pres_house_no, pres_road_no, pres_village, pres_po, pres_ps, pres_dist, pres_phn, perm_house_no, perm_road_no, perm_village, perm_po, perm_ps, perm_dist, perm_phn);
        
        // Get User ID
        String getIdText = hrmIdText.getText();
        String idTokens[] = getIdText.split(" ");
        String user = idTokens[2];
        String addedBy = user;
        String lastUpdatedBy = user;
        
        // Instantiate Employee
        Employee employee = new Employee(id, name, sex, company_no, branch_id, dept_code, designation, confirmation_date, joining_date, salary, personal_info, address, addedBy, lastUpdatedBy);
        
        // Check if employee is already in database
        int isAddedBefore = 0;
        for (int i = 0; i < employees.size(); i++){
            if(employees.get(i).getId().equals(employee.getId())){
                isAddedBefore = 1;
                break;
            }
        }
        employees.removeAll(employees);
        
        // Prepare Hibernate
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        // Database Action
        try {
            if(isAddedBefore == 0){
                session.save(employee);
            }else if(isAddedBefore == 1){
                employee.setId(this.employee.getId());
                employee.setAddedBy(this.employee.getAddedBy());
                session.update(employee);
            }
            employees = session.createCriteria(Employee.class).list();
            transaction.commit();
        } catch (Exception e) {
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        //Display Employees' Information on the Table View
        employeesTable.remove(0, (employeesTable.size()));
        for (int i = 0; i < employees.size(); i++){
            if(employees.get(i).getIsDeleted() == 0){
                employeesTable.add(employees.get(i));
            }
        }
        employeeViewTable.setItems(employeesTable);
        employeeIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getId()));
        employeeNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        employeeSexColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSex()));
        employeeCompanyNoColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getCompanyNo()));
        deptCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartmentCode()));
        branchIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBranchId()));
        joiningDateColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getJoiningDate()));
        employeeDesignationColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDesignation()));
        
        // Make All the Fields Blank for Further Use
        idNoField.setText("");
        employeeNameField.setText("");
        companyNoBox.getSelectionModel().clearSelection();
        designationBox.getSelectionModel().clearSelection();
        confirmationDatePicker.getEditor().setText("");
        deptCodeBox.getSelectionModel().clearSelection();
        joiningDatePicker.getEditor().setText("");
        genderBox.getSelectionModel().clearSelection();
        branchIDBox.getSelectionModel().clearSelection();
        
        basicSalaryField.setText("");
        runningBasicField.setText("");
        houseRentField.setText("");
        medicalAllowField.setText("");
        othersAllowField.setText("");
        conveyanceField.setText("");
        carAllowField.setText("");
        grossSalaryField.setText("");
        bankCodeBox.getSelectionModel().clearSelection();
        accountNoField.setText("");
       
        fathersNameField.setText("");
        mothersNameField.setText("");
        religionBox.getSelectionModel().clearSelection();
        maritalStatusBox.getSelectionModel().clearSelection();
        birthDateField.setText("");
        birthMonthField.setText("");
        birthYearField.setText("");
        ageField.setText("");
        spouseField.setText("");
        bloodGroupBox.getSelectionModel().clearSelection();
        nationalityField.setText("");
        heightField.setText("");
        weightField.setText("");
        childrenField.setText("");
        educationalQualificationField.setText("");
        technicalQualificationField.setText("");
        nationalIdNoField.setText("");
        mobileNoField.setText("");
        birthPlaceField.setText("");
        tinNoField.setText("");
        emailAddressField.setText("");
        passportNoField.setText("");
        emergencyContNo.setText("");
               
        presHouseNoField.setText("");
        presRoadNoField.setText("");
        presVillageField.setText("");
        presPOField.setText("");
        presPSField.setText("");
        presDistField.setText("");
        presPhnField.setText("");
        permHouseNoField.setText("");
        permRoadNoField.setText("");
        permVillageField.setText("");
        permPOField.setText("");
        permPSField.setText("");
        permDistField.setText("");
        permPhnField.setText("");
    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        String id = "";
        String name = employeeNameField.getText();
        int company_no = Integer.parseInt(companyNoBox.getSelectionModel().getSelectedItem() + "");
        String designation = designationBox.getSelectionModel().getSelectedItem() + "";
        String confirmation_date = confirmationDatePicker.getEditor().getText();
        String dept_code = deptCodeBox.getSelectionModel().getSelectedItem() + "";
        String joining_date = joiningDatePicker.getEditor().getText();
        String sex = genderBox.getSelectionModel().getSelectedItem() + "";
        String branch_id = branchIDBox.getSelectionModel().getSelectedItem() + "";
        
        //Collect Necessary Data and Instantiate Salary
        double basic_salary = Double.parseDouble(basicSalaryField.getText());
        String bank_code = bankCodeBox.getSelectionModel().getSelectedItem() + "";
        String ac_no = accountNoField.getText();
        Salary salary = new Salary(basic_salary, bank_code, ac_no);
        
        //Collect Necessary Data and Instantiate PersonalInformation
        String fathers_name = fathersNameField.getText();
        String mothers_name = mothersNameField.getText();
        String religion = religionBox.getSelectionModel().getSelectedItem() + "";
        String marital_status = maritalStatusBox.getSelectionModel().getSelectedItem() + "";
        String date_of_birth = birthDateField.getText() + "-" + birthMonthField.getText() + "-" + birthYearField.getText();
        String spouse = spouseField.getText();
        String blood_group = bloodGroupBox.getSelectionModel().getSelectedItem() + "";
        String nationality = nationalityField.getText();
        double height = Double.parseDouble(heightField.getText());
        double weight = Double.parseDouble(weightField.getText());
        int children = Integer.parseInt(childrenField.getText());
        String edu_quali = educationalQualificationField.getText();
        String tech_quali = technicalQualificationField.getText();
        String nid_no = nationalIdNoField.getText();
        String mobile_no = mobileNoField.getText();
        String birth_place = birthPlaceField.getText();
        String tin_no = tinNoField.getText();
        String email = emailAddressField.getText();
        String passport_no = passportNoField.getText();
        String emer_cont_no = emergencyContNo.getText();
        PersonalInformation personal_info = new PersonalInformation(fathers_name, mothers_name, date_of_birth, nationality, birth_place, blood_group, religion, marital_status, spouse, children, height, weight, edu_quali, tech_quali, nid_no, passport_no, tin_no, email, mobile_no, emer_cont_no);
        
        //Collect Necessary Data and Instantiate Address
        String pres_house_no = presHouseNoField.getText();
        String pres_road_no = presRoadNoField.getText();
        String pres_village = presVillageField.getText();
        String pres_po = presPOField.getText();
        String pres_ps = presPSField.getText();
        String pres_dist = presDistField.getText();
        String pres_phn = presPhnField.getText();
        String perm_house_no = permHouseNoField.getText();
        String perm_road_no = permRoadNoField.getText();
        String perm_village = permVillageField.getText();
        String perm_po = permPOField.getText();
        String perm_ps = permPSField.getText();
        String perm_dist = permDistField.getText();
        String perm_phn = permPhnField.getText();
        Address address = new Address(pres_house_no, pres_road_no, pres_village, pres_po, pres_ps, pres_dist, pres_phn, perm_house_no, perm_road_no, perm_village, perm_po, perm_ps, perm_dist, perm_phn);
        
        // Get User ID
        String getIdText = hrmIdText.getText();
        String idTokens[] = getIdText.split(" ");
        String user = idTokens[2];
        String addedBy = "";
        String lastUpdatedBy = user;
        
        // Instantiate Employee
        Employee employee = new Employee(id, name, sex, company_no, branch_id, dept_code, designation, confirmation_date, joining_date, salary, personal_info, address, addedBy, lastUpdatedBy);
        employee.setId(this.employee.getId());
        employee.setAddedBy(this.employee.getAddedBy());
        employee.setIsDeleted(1);
        employees.removeAll(employees);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(employee);
            employees = session.createCriteria(Employee.class).list();
            transaction.commit();
        } catch (Exception e) {
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        //Display Employees' Information on the Table View
        employeesTable.remove(0, (employeesTable.size()));
        for (int i = 0; i < employees.size(); i++){
            if(employees.get(i).getIsDeleted() == 0){
                employeesTable.add(employees.get(i));
            }
        }
        employeeViewTable.setItems(employeesTable);
        employeeIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getId()));
        employeeNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        employeeSexColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSex()));
        employeeCompanyNoColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getCompanyNo()));
        deptCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartmentCode()));
        branchIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBranchId()));
        joiningDateColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getJoiningDate()));
        employeeDesignationColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDesignation()));
        
        // Make All the Fields Blank for Further Use
        idNoField.setText("");
        employeeNameField.setText("");
        companyNoBox.getSelectionModel().clearSelection();
        designationBox.getSelectionModel().clearSelection();
        confirmationDatePicker.getEditor().setText("");
        deptCodeBox.getSelectionModel().clearSelection();
        joiningDatePicker.getEditor().setText("");
        genderBox.getSelectionModel().clearSelection();
        branchIDBox.getSelectionModel().clearSelection();
        
        basicSalaryField.setText("");
        runningBasicField.setText("");
        houseRentField.setText("");
        medicalAllowField.setText("");
        othersAllowField.setText("");
        conveyanceField.setText("");
        carAllowField.setText("");
        grossSalaryField.setText("");
        bankCodeBox.getSelectionModel().clearSelection();
        accountNoField.setText("");
       
        fathersNameField.setText("");
        mothersNameField.setText("");
        religionBox.getSelectionModel().clearSelection();
        maritalStatusBox.getSelectionModel().clearSelection();
        birthDateField.setText("");
        birthMonthField.setText("");
        birthYearField.setText("");
        ageField.setText("");
        spouseField.setText("");
        bloodGroupBox.getSelectionModel().clearSelection();
        nationalityField.setText("");
        heightField.setText("");
        weightField.setText("");
        childrenField.setText("");
        educationalQualificationField.setText("");
        technicalQualificationField.setText("");
        nationalIdNoField.setText("");
        mobileNoField.setText("");
        birthPlaceField.setText("");
        tinNoField.setText("");
        emailAddressField.setText("");
        passportNoField.setText("");
        emergencyContNo.setText("");
               
        presHouseNoField.setText("");
        presRoadNoField.setText("");
        presVillageField.setText("");
        presPOField.setText("");
        presPSField.setText("");
        presDistField.setText("");
        presPhnField.setText("");
        permHouseNoField.setText("");
        permRoadNoField.setText("");
        permVillageField.setText("");
        permPOField.setText("");
        permPSField.setText("");
        permDistField.setText("");
        permPhnField.setText("");
    }

    @FXML
    private void handleNewAction(ActionEvent event) {
        idNoField.setText("");
        employeeNameField.setText("");
        companyNoBox.getSelectionModel().clearSelection();
        designationBox.getSelectionModel().clearSelection();
        confirmationDatePicker.getEditor().setText("");
        deptCodeBox.getSelectionModel().clearSelection();
        joiningDatePicker.getEditor().setText("");
        genderBox.getSelectionModel().clearSelection();
        branchIDBox.getSelectionModel().clearSelection();
        
        basicSalaryField.setText("");
        runningBasicField.setText("");
        houseRentField.setText("");
        medicalAllowField.setText("");
        othersAllowField.setText("");
        conveyanceField.setText("");
        carAllowField.setText("");
        grossSalaryField.setText("");
        bankCodeBox.getSelectionModel().clearSelection();
        accountNoField.setText("");
       
        fathersNameField.setText("");
        mothersNameField.setText("");
        religionBox.getSelectionModel().clearSelection();
        maritalStatusBox.getSelectionModel().clearSelection();
        birthDateField.setText("");
        birthMonthField.setText("");
        birthYearField.setText("");
        ageField.setText("");
        spouseField.setText("");
        bloodGroupBox.getSelectionModel().clearSelection();
        nationalityField.setText("");
        heightField.setText("");
        weightField.setText("");
        childrenField.setText("");
        educationalQualificationField.setText("");
        technicalQualificationField.setText("");
        nationalIdNoField.setText("");
        mobileNoField.setText("");
        birthPlaceField.setText("");
        tinNoField.setText("");
        emailAddressField.setText("");
        passportNoField.setText("");
        emergencyContNo.setText("");
               
        presHouseNoField.setText("");
        presRoadNoField.setText("");
        presVillageField.setText("");
        presPOField.setText("");
        presPSField.setText("");
        presDistField.setText("");
        presPhnField.setText("");
        permHouseNoField.setText("");
        permRoadNoField.setText("");
        permVillageField.setText("");
        permPOField.setText("");
        permPSField.setText("");
        permDistField.setText("");
        permPhnField.setText("");
    }

    @FXML
    private void handleSelectEmployeeAction(MouseEvent event) {
        employee = employeeViewTable.getSelectionModel().getSelectedItem();
        if(!employee.getName().equals("")){
            idNoField.setText(employee.getId());
            employeeNameField.setText(employee.getName());
            companyNoBox.getSelectionModel().select(employee.getCompanyNo() - 1);
            designationBox.getSelectionModel().select(Designation.valueOf(employee.getDesignation()));
            confirmationDatePicker.getEditor().setText(employee.getConfirmationDate());
            deptCodeBox.getSelectionModel().select(DepartmentCode.valueOf(employee.getDepartmentCode()));
            joiningDatePicker.getEditor().setText(employee.getJoiningDate());
            genderBox.getSelectionModel().select(Gender.valueOf(employee.getSex()));
            branchIDBox.getSelectionModel().select(BranchID.valueOf(employee.getBranchId()));
            
            double basic_salary = employee.getSalaryInfo().getBasic_salary();
            String join_date = joiningDatePicker.getEditor().getText();
            String join_date_tokens[] = join_date.split("/");
            int join_year = Integer.parseInt(join_date_tokens[2]);
            int join_month = Integer.parseInt(join_date_tokens[1]);
            Date get_year = new Date();
            SimpleDateFormat years = new SimpleDateFormat("yyyy");
            int pres_year = Integer.parseInt(years.format(get_year));
            Date get_months = new Date();
            SimpleDateFormat months = new SimpleDateFormat("MM");
            int pres_month = Integer.parseInt(months.format(get_months));
            int job_years = 0;
            if(join_month > pres_month){
                job_years = pres_year - (join_year + 1);
            }
            else{
                job_years = pres_year - join_year;
            }

            double running_basic = 0;
            if(job_years > 0){
                running_basic = basic_salary + (basic_salary * job_years  * (5.0 / 100));
            }
            else{
                running_basic = basic_salary;
            }
            double house_rent = (running_basic * 40) / 100;
            double medical = (running_basic * 10) / 100;
            double others = (running_basic * 5) / 100;
            double conveyance = (running_basic * 5) / 100;
            double car_allow = (running_basic * 10) / 100;
            double gross_salary = running_basic + house_rent + medical + others + conveyance + car_allow;
            
            basicSalaryField.setText("" + basic_salary);            
            runningBasicField.setText("" + running_basic);
            houseRentField.setText("" + house_rent);
            medicalAllowField.setText("" + medical);
            othersAllowField.setText("" + others);
            conveyanceField.setText("" + conveyance);
            carAllowField.setText("" + car_allow);
            grossSalaryField.setText("" + gross_salary);
            bankCodeBox.getSelectionModel().select(BankCode.valueOf(employee.getSalaryInfo().getBank_code()));
            accountNoField.setText("" + employee.getSalaryInfo().getAc_no());
            
            fathersNameField.setText(employee.getPersonalInfo().getFathersName());
            mothersNameField.setText(employee.getPersonalInfo().getMothersName());
            religionBox.getSelectionModel().select(Religion.valueOf(employee.getPersonalInfo().getReligion()));
            maritalStatusBox.getSelectionModel().select(MaritalStatus.valueOf(employee.getPersonalInfo().getMaritalStatus()));
            String dateOfBirth = employee.getPersonalInfo().getDateOfBirth();
            String birth_date_tokens[] = dateOfBirth.split("-");
            birthDateField.setText(birth_date_tokens[0]);
            birthMonthField.setText(birth_date_tokens[1]);
            birthYearField.setText(birth_date_tokens[2]);
            int birth_month = Integer.parseInt(birth_date_tokens[1]);
            int birth_year = Integer.parseInt(birth_date_tokens[2]);
            int age;
            if(birth_month > pres_month){
                age = pres_year - (birth_year + 1);
            }
            else{
                age = pres_year - birth_year;
            }
            ageField.setText("" + age);
            spouseField.setText(employee.getPersonalInfo().getSpouse());
            bloodGroupBox.getSelectionModel().select(BloodGroup.valueOf(employee.getPersonalInfo().getBloodGroup()));
            nationalityField.setText(employee.getPersonalInfo().getNationality());
            heightField.setText("" + employee.getPersonalInfo().getHeight());
            weightField.setText("" + employee.getPersonalInfo().getWeight());
            childrenField.setText("" + employee.getPersonalInfo().getChildren());
            educationalQualificationField.setText(employee.getPersonalInfo().getEduQuali());
            technicalQualificationField.setText(employee.getPersonalInfo().getTechQuali());
            nationalIdNoField.setText(employee.getPersonalInfo().getNidNo());
            mobileNoField.setText(employee.getPersonalInfo().getMobileNo());
            birthPlaceField.setText(employee.getPersonalInfo().getBirthPlace());
            tinNoField.setText(employee.getPersonalInfo().getTinNo());
            emailAddressField.setText(employee.getPersonalInfo().getEmail());
            passportNoField.setText(employee.getPersonalInfo().getPassportNo());
            emergencyContNo.setText(employee.getPersonalInfo().getEmerContNo());
            
            presHouseNoField.setText(employee.getAddress().getPresHouseNo());
            presRoadNoField.setText(employee.getAddress().getPresRoadNo());
            presVillageField.setText(employee.getAddress().getPresVillage());
            presPOField.setText(employee.getAddress().getPresPO());
            presPSField.setText(employee.getAddress().getPresPS());
            presDistField.setText(employee.getAddress().getPresDist());
            presPhnField.setText(employee.getAddress().getPresPhn());
            permHouseNoField.setText(employee.getAddress().getPermHouseNo());
            permRoadNoField.setText(employee.getAddress().getPermRoadNo());
            permVillageField.setText(employee.getAddress().getPermVillage());
            permPOField.setText(employee.getAddress().getPermPO());
            permPSField.setText(employee.getAddress().getPermPS());
            permDistField.setText(employee.getAddress().getPermDist());
            permPhnField.setText(employee.getAddress().getPermPhn());
        }
    }

    @FXML
    private void handleSignOutAction(ActionEvent event) {
        try {
            employeeId = "";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HomePageUI.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            
            TextileERP.getMainStage().setScene(scene);
            TextileERP.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setEmployeeId(String username){
        this.employeeId = username;
        hrmIdText.setText("HRM ID: " + username);
        hrmSalariesIdText.setText("HRM ID: " + username);
        hrmProfileIdText.setText("HRM ID: " + username);
        
        employees = new ArrayList<>();
        
        // Prepare Hibernate
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        // Database Actions
        try{
            employees = session.createCriteria(Employee.class).list();
            transaction.commit();
        }catch(Exception e){
            System.out.println(e);
            transaction.rollback();
        }
        session.close();
        
        // Get Employee Information Using ID
        for(int i = 0; i < employees.size(); i++){
            if(username.equals(employees.get(i).getId())){
                employee = employees.get(i);
            }
        }
        
        // Set Profile Information To FXML
        profileEmployeeIdText.setText(employee.getId());
        profileDesignationText.setText(employee.getDesignation());
        profileJoiningDateText.setText(employee.getJoiningDate());
        profileConfirmationDateText.setText(employee.getConfirmationDate());
        profileDepartmentText.setText(employee.getDepartmentCode());
        profileCompanyNoText.setText(employee.getCompanyNo() + "");
        profileNameText.setText(employee.getName());
        profileNidNoText.setText(employee.getPersonalInfo().getNidNo());
        profileEmailText.setText(employee.getPersonalInfo().getEmail());
        profilePhoneText.setText(employee.getPersonalInfo().getMobileNo());
        profileEduQualiText.setText(employee.getPersonalInfo().getEduQuali());
        profilePassportNoText.setText(employee.getPersonalInfo().getPassportNo());
        profileReligionText.setText(employee.getPersonalInfo().getReligion());
        profileGenderText.setText(employee.getSex());
        profileDateOfBirthText.setText(employee.getPersonalInfo().getDateOfBirth());
        profileMotherNameText.setText(employee.getPersonalInfo().getMothersName());
        profileFatherNameText.setText(employee.getPersonalInfo().getFathersName());
        profileBloodGroupText.setText(employee.getPersonalInfo().getBloodGroup());
        profileNationalityText.setText(employee.getPersonalInfo().getNationality());
        profileUsernameText.setText(employee.getId());
        profileBranchText.setText(employee.getBranchId());
    }

    @FXML
    private void handleUpdateSalaryAction(MouseEvent event) {
    }

    @FXML
    private void handleSalariesSignOutAction(ActionEvent event) {
        try {
            employeeId = "";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HomePageUI.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            
            TextileERP.getMainStage().setScene(scene);
            TextileERP.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleChangePasswordReMatchAction(KeyEvent event) {
        hrmChangePasswordMessageText.setText("");
        String password = profileNewPasswordField.getText();
        String reTypePassword = profileRetypeNewPasswordField.getText();
        
        int isMatched = 0;
        if(!password.equals("") && !reTypePassword.equals("") && password.equals(reTypePassword)){
            isMatched = 1;
        }
        else if(!password.equals("") && !reTypePassword.equals("") && !password.equals(reTypePassword) && password != null && reTypePassword != null){
            hrmChangePasswordMessageText.setText("Please Enter Same Password in Both Fields");
        }
    }

    @FXML
    private void handleChangePasswordMatchAction(KeyEvent event) {
        hrmChangePasswordMessageText.setText("");
        String password = profileNewPasswordField.getText();
        String reTypePassword = profileRetypeNewPasswordField.getText();
        
        int isMatched = 0;
        if(!password.equals("") && !reTypePassword.equals("") && password.equals(reTypePassword)){
            isMatched = 1;
        }
        else if(!password.equals("") && !reTypePassword.equals("") && !password.equals(reTypePassword) && password != null && reTypePassword != null){
            hrmChangePasswordMessageText.setText("Please Enter Same Password in Both Fields");
        }
    }

    @FXML
    private void handleHrmChangePasswordAction(ActionEvent event) {
        // Get Values From FXML
        String username = profileUsernameText.getText();
        String getPass = profileOldPasswordField.getText();
        
        String getNewPass = profileNewPasswordField.getText();
        String getNewRePass = profileRetypeNewPasswordField.getText();
        
        // Encrypt Passwords
        HashMD5 encPass = new HashMD5(getPass);
        String password = encPass.getHash();
        
        HashMD5 encNewPass = new HashMD5(getNewPass);
        String newPassword = encNewPass.getHash();
        
        // Get The User
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmployeeId().equals(username) && users.get(i).getPassword().equals(password)){
                user = users.get(i);
                break;
            }
        }
        
        // Set User's New Password
        if(getNewPass.equals(getNewRePass)){
            user.setPassword(newPassword);
        }
        
        // Prepare Hibernate
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        // Database Actions
        try{
            session.update(user);
            transaction.commit();
        }catch(Exception e){
            hrmChangePasswordMessageText.setText(e + "");
            transaction.rollback();
        }
        session.close();
        
        // Refresh FXML
        profileOldPasswordField.setText("");
        profileNewPasswordField.setText("");
        profileRetypeNewPasswordField.setText("");
    }

    @FXML
    private void handleHrmProfileSignOutAction(ActionEvent event) {
        try {
            employeeId = "";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HomePageUI.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            
            TextileERP.getMainStage().setScene(scene);
            TextileERP.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handlePrintAction(ActionEvent event) {
        PdfEmployee pdfEmployee = new PdfEmployee(this.employee);
        Document employeePdfDoc = new Document();
        employeePdfDoc = pdfEmployee.getPdfDocumet();
        
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
        PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
        patts.add(Sides.DUPLEX);
        javax.print.PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
        
        PrinterJob job = PrinterJob.getPrinterJob();
        try {
            job.setPrintService(ps[0]);
        } catch (PrinterException ex) {
            Logger.getLogger(HRPanelUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
