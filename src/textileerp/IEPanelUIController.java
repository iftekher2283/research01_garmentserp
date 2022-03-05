package textileerp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import enums.Components;
import enums.Floors;
import enums.Lines;
import enums.McCodes;
import enums.McHelp;
import hibernatesingleton.HibernateSingleton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import md5.HashMD5;
import model.BulletinOperation;
import model.BulletinOperationDetails;
import model.BulletinOperationSummary;
import model.Employee;
import model.IEBulletin;
import model.Order;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author student
 */
public class IEPanelUIController implements Initializable {
    //Prepare Bulletin FXML
    @FXML
    private Text ieIdBulletinText;
    @FXML
    private Text ieIdCapacityText;
    @FXML
    private Text ieIdProductionText;
    @FXML
    private Text ieIdManpowerText;
    @FXML
    private Text ieIdMonitorText;
    @FXML
    private TextField buyeNameBulletinField;
    @FXML
    private ComboBox<String> orderIdBulletinBox;
    @FXML
    private TextArea orderDescriptionBulletinArea;
    @FXML
    private TextField orderQuantityBulletinField;
    @FXML
    private TextField allocatedQuantityBulletinField;
    @FXML
    private ComboBox<Floors> floorNoBulletinBox;
    @FXML
    private ComboBox<Lines> lineNoBulletinBox;
    @FXML
    private TextField planMpBulletinField;
    @FXML
    private TextField planTargetHrBulletinField;
    @FXML
    private TextField wminBulletinField;
    @FXML
    private TextField whrBulletinField;
    @FXML
    private TextField indTargetEffBulletinField;
    @FXML
    private TextField targetEffBulletinField;
    @FXML
    private TextField lclBulletinField;
    @FXML
    private TextField uclBulletinField;
    @FXML
    private TextField pitchTimeBulletinField;
    
    // Bulletin Operation
    @FXML
    private ComboBox<Components> componentBulletinBox;
    @FXML
    private ComboBox<McHelp> machineOrHelperBulletinBox;
    @FXML
    private ComboBox<McCodes> machineCodeBulletinBox;
    @FXML
    private TextField descriptionBulletinField;
    @FXML
    private TextField operatorBulletinField;
    @FXML
    private TextField machineQuantityBulletinField;
    @FXML
    private TextField indTargetBulletinField;
    @FXML
    private TextField hundredTargetBulletinField;
    @FXML
    private TextField remarksBulletinField;
    @FXML
    private TextField acTargetBulletinField;
    @FXML
    private TextField imBulletinField;
    @FXML
    private TextField helperBulletinField;
    @FXML
    private TextField mpAllocationBulletinField;
    @FXML
    private TextField requiredMpBulletinField;
    @FXML
    private TextField smvBulletinField;
    @FXML
    private TextField secondsCountBulletinField;
    
    //Bulletin Operation Description Table
    @FXML
    private TableView<BulletinOperation> bulletinOperationDescriptionTableView;
    @FXML
    private TableColumn<BulletinOperation, Number> slOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, String> componentsOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, String> operationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, String> mcHelpOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, String> mcCodeOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> smvOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> reqMpOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> mpAllOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> hundredTargetOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> indTargetOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> mcQuantityOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> operatorOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> helperOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> imOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> acTargetOperationDescriptionTableColumn;
    @FXML
    private TableColumn<BulletinOperation, String> remarksOperationDescriptionTableColumn;
    
    //Bulletin Operation Summary Table
    @FXML
    private TableView<BulletinOperation> bulletinOperationSummaryTableView;
    @FXML
    private TableColumn<BulletinOperation, String> componentsOperationSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> smvOperationSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> manPowerAllOperationSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> mcQuantityOperationSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> operatorOperationSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> helperOperationSummaryTableColumn;
    
    //Bulletin Summary Table
    @FXML
    private TableView<BulletinOperation> bulletinSummaryTableView;
    @FXML
    private TableColumn<BulletinOperation, String> areaBulletinSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> smvBulletinSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> mcOpBulletinSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> imBulletinSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> hpBulletinSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> totalBulletinSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> hudredTargetBulletinSummaryTableColumn;
    @FXML
    private TableColumn<BulletinOperation, Number> targetBulletinSummaryTableColumn;
    
    // Capacity Study FXML
    @FXML
    private TextField buyerNameCapacityField;
    @FXML
    private ComboBox<?> orderIdCapacityBox;
    @FXML
    private TextField daysRunCapacityField;
    @FXML
    private TextField graphNoCapacityField;
    @FXML
    private TextField operatorCapacityField;
    @FXML
    private TextField helperCapacityField;
    @FXML
    private TextField totalMpCapacityField;
    @FXML
    private TextField smvCapacityField;
    @FXML
    private TextField currentPcsPerHrCapacityField;
    @FXML
    private TextField targetCapacityField;
    @FXML
    private TextField totalCycleTimeCapacityField;
    @FXML
    private TextField basicPitchTimeCapacityField;
    @FXML
    private TextField workPotentialCapacityField;
    @FXML
    private TextField productivityGapCapacityField;
    @FXML
    private ComboBox<?> floorNoCapacityBox;
    @FXML
    private ComboBox<?> lineNoCapacityBox;
    @FXML
    private ComboBox<?> studiedByCapacityBox;
    @FXML
    private DatePicker dateCapacityDatePicker;
    @FXML
    private TextField operatorName1CapacityField;
    @FXML
    private TextField operatorName2CapacityField;
    @FXML
    private TextField machineCodeCapacityField;
    @FXML
    private TextField capacityPerHr1op1CapacityField;
    @FXML
    private TextField ct1op1CapacityField;
    @FXML
    private TextField ProcessNameCapacityField;
    @FXML
    private TextField secondsCounts5op1CapacityField;
    @FXML
    private TextField secondsCount1op1CapacityField;
    @FXML
    private TextField secondsCount4op1CapacityField;
    @FXML
    private TextField secondsCount2op1CapacityField;
    @FXML
    private TextField secondsCount3op1CapacityField;
    @FXML
    private TextField capacityPerHr3op3CapacityField;
    @FXML
    private TextField secondsCount4op3CapacityField;
    @FXML
    private TextField secondsCount5op3CapacityField;
    @FXML
    private TextField operatorName3op3CapacityField;
    @FXML
    private TextField ct3op3CapacityField;
    @FXML
    private TextField secondsCount2op3op3CapacityField;
    @FXML
    private TextField secondsCount3op3CapacityField;
    @FXML
    private TextField secondsCount1op3CapacityField;
    @FXML
    private TextField capacityPerHrop2CapacityField;
    @FXML
    private TextField secondsCount5op2CapacityField;
    @FXML
    private TextField secondsCount4op2CapacityField;
    @FXML
    private TextField ct2op2CapacityField;
    @FXML
    private TextField secoundsCount3op2CapacityField;
    @FXML
    private TextField secondsCount2op2CapacityField;
    @FXML
    private TextField secondsCount1op2CapacityField;
    // Capacity Study Table View
    @FXML
    private TableView<?> capacityStudyDetailsCapacityTableView;
    @FXML
    private TableColumn<?, ?> slCapacityTableColumn;
    @FXML
    private TableColumn<?, ?> processNameCapacityTableColumn;
    @FXML
    private TableColumn<?, ?> operator1CapacityTableColumn;
    @FXML
    private TableColumn<?, ?> ct1CapacityTableColumn;
    @FXML
    private TableColumn<?, ?> capacityPerHr1CapacityTableColumn;
    @FXML
    private TableColumn<?, ?> operator2CapacityTableColumn;
    @FXML
    private TableColumn<?, ?> ct2CapacityTableColumn;
    @FXML
    private TableColumn<?, ?> capacityPerHr2CapacityTableColumn;
    @FXML
    private TableColumn<?, ?> operator3CapacityTableColumn;
    @FXML
    private TableColumn<?, ?> ct3CapacityTableColumn;
    @FXML
    private TableColumn<?, ?> capacityPerHr3CapacityTableColumn;
    
    // Production Study FXML
    @FXML
    private TextField buyerNameProductionField;
    
    @FXML
    private ComboBox<?> orderIdBulletinBox1;
    @FXML
    private ComboBox<?> orderIdBulletinBox2;
    @FXML
    private ComboBox<?> orderIdBulletinBox3;
    
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
    private Text ieChangePasswordMessageText;
    @FXML
    private Text ieProfileIdText;
    
    // Required Lists
    private List<Order> orders;
    private List<IEBulletin> ieBulletins;
    private List<BulletinOperation> bulletinOperations;
    private List<Employee> employees;
    private List<User> users;
    
    // Required ObservableLists
    private ObservableList<String> orderIds;
    private ObservableList<BulletinOperation> bulletinOperationDetails;
    private ObservableList<BulletinOperation> bulletinOperationSummaries;
    
    // Hibernate Variables
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;
    
    // Required Global Variables
    private String ieId;
    private Order order;
    private IEBulletin ieBulletin;
    private BulletinOperation bulletinOperation;
    private Employee employee;
    private User user;
    
    // MySQL Connecting Variables
    private String DB_URL = "jdbc:mysql://127.0.0.1/textileerpdb";
    private String DB_USER = "root";
    private String DB_PASS = "123";
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set ComboBox Values With Enums
        /* Prepare Bulletin */
        floorNoBulletinBox.getItems().addAll(Floors.values());
        lineNoBulletinBox.getItems().addAll(Lines.values());
        componentBulletinBox.getItems().addAll(Components.values());
        machineOrHelperBulletinBox.getItems().addAll(McHelp.values());
        machineCodeBulletinBox.getItems().addAll(McCodes.values());
        
        // Initialize ArrayLists 
        orders = new ArrayList<>();
        ieBulletins = new ArrayList<>();
        users = new ArrayList<>();
        
        // Initialize ObservableLists
        orderIds = FXCollections.observableArrayList();
        
        
        // Prepare Hibernate to Work
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        
        // Handle Actions in Database
        try{
          //  orders = session.createCriteria(Order.class).list();
            ieBulletins = session.createCriteria(IEBulletin.class).list();
            users = session.createCriteria(User.class).list();
            orders = session.createCriteria(Order.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
            System.out.println("RollBacked");
        }
        session.close();
        
        // Add Order Ids to ObservableList and Set it To ComboBox
        for(int i = 0; i < orders.size(); i++){
            orderIds.add(orders.get(i).getOrderId() + "");
        }
        orderIdBulletinBox.setItems(orderIds);
    }    

    @FXML
    private void handleBulletinSignOutAction(ActionEvent event) {
        try {
            ieId = "";
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
    private void handleCapacitySignOutAction(ActionEvent event) {
        try {
            ieId = "";
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
    private void handleProductionSignOutAction(ActionEvent event) {
        try {
            ieId = "";
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
    private void handleManpowerSignOutAction(ActionEvent event) {
        try {
            ieId = "";
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
    private void handleMonitorSignOutAction(ActionEvent event) {
        try {
            ieId = "";
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
    private void handleSelectBulletinDescriptionAction(MouseEvent event) {
    }

    @FXML
    private void handleCalculateSmvBulletinAction(KeyEvent event) {
        String getText = secondsCountBulletinField.getText();
        if(getText != null && !getText.equals("")){
            double seconds = Double.parseDouble(getText);
            double smv = seconds / 60;
            smvBulletinField.setText(smv + "");
        }
    }

    @FXML
    private void handleBulletinSaveAction(ActionEvent event) {
    }

    @FXML
    private void handleBulletinRemoveAction(ActionEvent event) {
    }

    @FXML
    private void handleBulletinRefreshAction(ActionEvent event) {
    }

    @FXML
    private void handleOrderIdBulletinAction(ActionEvent event) {
        // Get Order ID From FXML
        int orderId = Integer.parseInt(orderIdBulletinBox.getSelectionModel().getSelectedItem());
       
        // Set Order Information To FXML If Valid Order
        for(int i = 0; i < orders.size(); i++){
            if(orders.get(i).getOrderId() == orderId){
                order = orders.get(i);
                buyeNameBulletinField.setText(order.getBuyerName());
                orderDescriptionBulletinArea.setText(order.getOrderDescription());
                orderQuantityBulletinField.setText(order.getOrderQuantity() + "");
                floorNoBulletinBox.getSelectionModel().select(Floors.valueOf(order.getOrderFloorNo()));
                lineNoBulletinBox.getSelectionModel().select(Lines.valueOf(order.getOrderLineNo()));
                
                break;
            }
        }
        
        // Check Bulletin Availability
        int isBulletinFound = 0;
        for(int i = 0; i < ieBulletins.size(); i++){
            if(ieBulletins.get(i).getOrderId() == orderId){
                isBulletinFound = 1;
                ieBulletin = ieBulletins.get(i);
                break;
            }
        }
        
        // Get Ready To Manage Bulletin Operations' Data
        if(isBulletinFound == 0){
            bulletinOperations = new ArrayList<>();
            bulletinOperationDetails = FXCollections.observableArrayList();
            bulletinOperationSummaries = FXCollections.observableArrayList();
            ieBulletin = new IEBulletin();
            ieBulletin.setOrderId(orderId);
        }
        else{
            bulletinOperations = new ArrayList<>();
            bulletinOperationDetails = FXCollections.observableArrayList();
            bulletinOperationSummaries = FXCollections.observableArrayList();
            
            bulletinOperations = ieBulletin.getOperationDetails();
            for(int i = 1; i <= bulletinOperations.size(); i++){
                bulletinOperation = bulletinOperations.get(i);
                int id = i;
                String component = bulletinOperation.getComponent();
                String description = bulletinOperation.getDescription();
                String mcOrHelper = bulletinOperation.getMcOrHelper();
                String machineCode = bulletinOperation.getMachineCode();
                int secondsCount = bulletinOperation.getSecondsCount();
                double requiredManpower = bulletinOperation.getRequiredManpower();
                double manpowerAllocation = bulletinOperation.getManpowerAllocation();
                int hundredTarget = bulletinOperation.getHundredTarget();
                int individualTarget = bulletinOperation.getIndividualTarget();
                double machineQuantity = bulletinOperation.getMachineQuantity();
                double operator = bulletinOperation.getOperator();
                double helper = bulletinOperation.getHelper();
                double im = bulletinOperation.getIm();
                int acTarget = bulletinOperation.getAcTarget();
                String remarks = bulletinOperation.getRemarks();
                
                BulletinOperation bulletinOperation = new BulletinOperation(id, component, description, mcOrHelper, machineCode, secondsCount, requiredManpower, manpowerAllocation, hundredTarget, individualTarget, machineQuantity, operator, helper, im, acTarget, remarks);
                bulletinOperations.add(bulletinOperation);
                bulletinOperationDetails.add(bulletinOperation);
            }
            
            BulletinOperationSummary bulletinSummaries = new BulletinOperationSummary();
            bulletinOperationSummaries = bulletinSummaries.getSummaries();
            
            bulletinOperationDescriptionTableView.setItems(bulletinOperationDetails);
            slOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()));
            componentsOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getComponent()));
            mcHelpOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMcOrHelper()));
            mcCodeOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMachineCode()));
            smvOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getSmv()));
            reqMpOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getRequiredManpower()));
            mpAllOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getManpowerAllocation()));
            hundredTargetOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getHundredTarget()));
            indTargetOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getIndividualTarget()));
            mcQuantityOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getMachineQuantity()));
            operatorOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getOperator()));
            helperOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getHelper()));
            imOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getIm()));
            acTargetOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getAcTarget()));
            remarksOperationDescriptionTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRemarks()));
            
            bulletinOperationSummaryTableView.setItems(bulletinOperationSummaries);
            componentsOperationSummaryTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getComponent()));
            smvOperationSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getSmv()));
            manPowerAllOperationSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getManpowerAllocation()));
            mcQuantityOperationSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getMachineQuantity()));
            operatorOperationSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getOperator()));
            helperOperationSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getHelper()));
            
            bulletinSummaryTableView.setItems(bulletinOperationSummaries);
            areaBulletinSummaryTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getComponent()));
            smvBulletinSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getSmv()));
            mcOpBulletinSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getMachineQuantity() + d.getValue().getOperator()));
            imBulletinSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getIm()));
            hpBulletinSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getHelper()));
            totalBulletinSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getManpowerAllocation()));
            hudredTargetBulletinSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getHundredTarget()));
            targetBulletinSummaryTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getAcTarget()));
        }        
    }    

    @FXML
    private void handleAddBulletinOperationAction(ActionEvent event) {
        if (ieBulletin != null){
            int id = 0;
            String component = componentBulletinBox.getSelectionModel().getSelectedItem() + "";
            String mcOrHelper = machineOrHelperBulletinBox.getSelectionModel().getSelectedItem() + "";
            String machineCode = machineCodeBulletinBox.getSelectionModel().getSelectedItem() + "";
            String description = descriptionBulletinField.getText();
            double operator = Double.parseDouble(operatorBulletinField.getText());
            double machineQuantity = Double.parseDouble(machineQuantityBulletinField.getText());
            int individualTarget = Integer.parseInt(indTargetBulletinField.getText());
            int hundredTarget = Integer.parseInt(hundredTargetBulletinField.getText());
            String remarks = remarksBulletinField.getText();
            int acTarget = Integer.parseInt(acTargetBulletinField.getText());
            double im = Double.parseDouble(imBulletinField.getText());
            double helper = Double.parseDouble(helperBulletinField.getText());
            double manpowerAllocation = Double.parseDouble(mpAllocationBulletinField.getText());
            double requiredManpower = Double.parseDouble(requiredMpBulletinField.getText());
            double smv = Double.parseDouble(smvBulletinField.getText());
            int secondsCount = Integer.parseInt(secondsCountBulletinField.getText());
            
            BulletinOperation bulletinOperation = new BulletinOperation(id, component, description, mcOrHelper, machineCode, secondsCount, requiredManpower, manpowerAllocation, hundredTarget, individualTarget, machineQuantity, operator, helper, im, acTarget, remarks);
            ieBulletin.addBulletinOperation(bulletinOperation);
            bulletinOperations.add(bulletinOperation);
            
            BulletinOperationSummary bulletinSummary = new BulletinOperationSummary(bulletinOperations);
            bulletinOperationSummaries = bulletinSummary.getSummaries();
        }
    }

    @FXML
    private void handleRefreshBulletinOperationAction(ActionEvent event) {
        buyeNameBulletinField.setText("");
        orderIdBulletinBox.getSelectionModel().clearSelection();
        orderDescriptionBulletinArea.setText("");
        orderQuantityBulletinField.setText("");
        allocatedQuantityBulletinField.setText("");
        floorNoBulletinBox.getSelectionModel().clearSelection();
        lineNoBulletinBox.getSelectionModel().clearSelection();
        planMpBulletinField.setText("");
        planTargetHrBulletinField.setText("");
        wminBulletinField.setText("");
        whrBulletinField.setText("");
        indTargetEffBulletinField.setText("");
        targetEffBulletinField.setText("");
        lclBulletinField.setText("");
        uclBulletinField.setText("");
        pitchTimeBulletinField.setText("");
        componentBulletinBox.getSelectionModel().clearSelection();
        machineOrHelperBulletinBox.getSelectionModel().clearSelection();
        machineCodeBulletinBox.getSelectionModel().clearSelection();
        descriptionBulletinField.setText("");
        operatorBulletinField.setText("");
        machineQuantityBulletinField.setText("");
        indTargetBulletinField.setText("");
        hundredTargetBulletinField.setText("");
        remarksBulletinField.setText("");
        acTargetBulletinField.setText("");
        imBulletinField.setText("");
        helperBulletinField.setText("");
        mpAllocationBulletinField.setText("");
        requiredMpBulletinField.setText("");
        smvBulletinField.setText("");
        secondsCountBulletinField.setText("");
    }

    @FXML
    private void handleRemoveBulletinOperationAction(ActionEvent event) {
    }

    @FXML
    private void handleUpdateBulletinOperationAction(ActionEvent event) {
    }



    @FXML
    private void handleSelectCapacityDetailsAction(MouseEvent event) {
    }

    @FXML
    private void handleAddCapacityAction(ActionEvent event) {
    }

    @FXML
    private void handleUpdateCapacityAction(ActionEvent event) {
    }

    @FXML
    private void handleRemoveCapacityAction(ActionEvent event) {
    }

    @FXML
    private void handleRefreshCapacityAction(ActionEvent event) {
        buyerNameCapacityField.setText("");
        orderIdCapacityBox.getSelectionModel().clearSelection();
        daysRunCapacityField.setText("");
        graphNoCapacityField.setText("");
        operatorCapacityField.setText("");
        totalMpCapacityField.setText("");
        smvCapacityField.setText("");
        currentPcsPerHrCapacityField.setText("");
        targetCapacityField.setText("");
        totalCycleTimeCapacityField.setText("");
        basicPitchTimeCapacityField.setText("");
        workPotentialCapacityField.setText("");
        productivityGapCapacityField.setText("");
        floorNoCapacityBox.getSelectionModel().clearSelection();
        lineNoCapacityBox.getSelectionModel().clearSelection();
        studiedByCapacityBox.getSelectionModel().clearSelection();
        dateCapacityDatePicker.getEditor().setText("");
        operatorName1CapacityField.setText("");
        operatorName2CapacityField.setText("");
        machineCodeCapacityField.setText("");
        capacityPerHr1op1CapacityField.setText("");
        ct1op1CapacityField.setText("");
        ProcessNameCapacityField.setText("");
        secondsCounts5op1CapacityField.setText("");
        secondsCount1op1CapacityField.setText("");
        secondsCount4op1CapacityField.setText("");
        secondsCount2op1CapacityField.setText("");
        secondsCount3op1CapacityField.setText("");
        capacityPerHr3op3CapacityField.setText("");
        secondsCount4op3CapacityField.setText("");
        secondsCount5op3CapacityField.setText("");
        operatorName3op3CapacityField.setText("");
        ct3op3CapacityField.setText("");
        secondsCount2op3op3CapacityField.setText("");
        secondsCount3op3CapacityField.setText("");
        secondsCount1op3CapacityField.setText("");
        capacityPerHrop2CapacityField.setText("");
        secondsCount5op2CapacityField.setText("");
        secondsCount4op2CapacityField.setText("");
        ct2op2CapacityField.setText("");
        secoundsCount3op2CapacityField.setText("");
        secondsCount2op2CapacityField.setText("");
        secondsCount1op2CapacityField.setText("");
    }

    @FXML
    private void handleSecCount5op1CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op1CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op1CapacityField.getText());
        int sec3 = Integer.parseInt(secondsCount3op1CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op1CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCounts5op1CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct1op1CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount1op1CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op1CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op1CapacityField.getText());
        int sec3 = Integer.parseInt(secondsCount3op1CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op1CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCounts5op1CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct1op1CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount4op1CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op1CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op1CapacityField.getText());
        int sec3 = Integer.parseInt(secondsCount3op1CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op1CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCounts5op1CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct1op1CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount2op1CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op1CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op1CapacityField.getText());
        int sec3 = Integer.parseInt(secondsCount3op1CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op1CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCounts5op1CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct1op1CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount3op1CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op1CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op1CapacityField.getText());
        int sec3 = Integer.parseInt(secondsCount3op1CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op1CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCounts5op1CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct1op1CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount4op3CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op3CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op3op3CapacityField.getText());
        int sec3 = Integer.parseInt(secondsCount3op3CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op3CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCount5op3CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct3op3CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount5op3CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op3CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op3op3CapacityField.getText());
        int sec3 = Integer.parseInt(secondsCount3op3CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op3CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCount5op3CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct3op3CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount2op3CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op3CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op3op3CapacityField.getText());
        int sec3 = Integer.parseInt(secondsCount3op3CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op3CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCount5op3CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct3op3CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount3op3CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op3CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op3op3CapacityField.getText());
        int sec3 = Integer.parseInt(secondsCount3op3CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op3CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCount5op3CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct3op3CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount1op3CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op3CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op3op3CapacityField.getText());
        int sec3 = Integer.parseInt(secondsCount3op3CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op3CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCount5op3CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct3op3CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount5op2CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op2CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op2CapacityField.getText());
        int sec3 = Integer.parseInt(secoundsCount3op2CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op2CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCount5op2CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct2op2CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount4op2CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op2CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op2CapacityField.getText());
        int sec3 = Integer.parseInt(secoundsCount3op2CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op2CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCount5op2CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct2op2CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount3op2CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op2CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op2CapacityField.getText());
        int sec3 = Integer.parseInt(secoundsCount3op2CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op2CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCount5op2CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct2op2CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount2op2CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op2CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op2CapacityField.getText());
        int sec3 = Integer.parseInt(secoundsCount3op2CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op2CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCount5op2CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct2op2CapacityField.setText(cycleTime + "");
    }

    @FXML
    private void handleSecCount1op2CapacityAction(KeyEvent event) {
        int sec1 = Integer.parseInt(secondsCount1op2CapacityField.getText());
        int sec2 = Integer.parseInt(secondsCount2op2CapacityField.getText());
        int sec3 = Integer.parseInt(secoundsCount3op2CapacityField.getText());
        int sec4 = Integer.parseInt(secondsCount4op2CapacityField.getText());
        int sec5 = Integer.parseInt(secondsCount5op2CapacityField.getText());
        
        int cycleTime = (sec1 + sec2 + sec3 + sec4 + sec5) / 5;
        ct2op2CapacityField.setText(cycleTime + "");
    }
    
    public void setIeId(String username){
        this.ieId = username;
        
        ieIdBulletinText.setText("IE ID: " + username);
        ieIdCapacityText.setText("IE ID: " + username);
        ieIdProductionText.setText("IE ID: " + username);
        ieIdManpowerText.setText("IE ID: " + username);
        ieIdMonitorText.setText("IE ID: " + username);
        ieProfileIdText.setText("IE ID: " + username);
        
        employees = new ArrayList<>();
        
        // Prepare Hibernate
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        
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
    private void handleOrderIdCapacityAction(ActionEvent event) {
    }

    @FXML
    private void handleChangePasswordReMatchAction(KeyEvent event) {
        ieChangePasswordMessageText.setText("");
        String password = profileNewPasswordField.getText();
        String reTypePassword = profileRetypeNewPasswordField.getText();
        
        int isMatched = 0;
        if(!password.equals("") && !reTypePassword.equals("") && password.equals(reTypePassword)){
            isMatched = 1;
        }
        else if(!password.equals("") && !reTypePassword.equals("") && !password.equals(reTypePassword) && password != null && reTypePassword != null){
            ieChangePasswordMessageText.setText("Please Enter Same Password in Both Fields");
        }
    }

    @FXML
    private void handleChangePasswordMatchAction(KeyEvent event) {
        ieChangePasswordMessageText.setText("");
        String password = profileNewPasswordField.getText();
        String reTypePassword = profileRetypeNewPasswordField.getText();
        
        int isMatched = 0;
        if(!password.equals("") && !reTypePassword.equals("") && password.equals(reTypePassword)){
            isMatched = 1;
        }
        else if(!password.equals("") && !reTypePassword.equals("") && !password.equals(reTypePassword) && password != null && reTypePassword != null){
            ieChangePasswordMessageText.setText("Please Enter Same Password in Both Fields");
        }
    }

    @FXML
    private void handleIeChangePasswordAction(ActionEvent event) {
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
        transaction = session.beginTransaction();
        
        // Database Actions
        try{
            session.update(user);
            transaction.commit();
        }catch(Exception e){
            ieChangePasswordMessageText.setText(e + "");
            transaction.rollback();
        }
        session.close();
        
        // Refresh FXML
        profileOldPasswordField.setText("");
        profileNewPasswordField.setText("");
        profileRetypeNewPasswordField.setText("");
    }

    @FXML
    private void handleIeProfileSignOutAction(ActionEvent event) {
        ieChangePasswordMessageText.setText("");
        try {
            ieId = "";
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
}
