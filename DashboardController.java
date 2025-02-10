package com.example.library_acccounting_system_v01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;


import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;



public class DashboardController implements Initializable {


    @FXML
    private AnchorPane main_form;

    @FXML
    private Button addBookAdd;

    @FXML
    private Button addBookClear;

    @FXML
    private Button addBookDelete;

    @FXML
    private Button addBookReport;

    @FXML
    private Button addBookUpdate;

    @FXML
    private TableColumn<BooksData, String> addBook_col_author;

    @FXML
    private TableColumn<BooksData, String> addBook_col_category;

    @FXML
    private TableColumn<BooksData, String> addBook_col_id;

    @FXML
    private TableColumn<BooksData, String> addBook_col_name;

    @FXML
    private TableColumn<BooksData, String> addBook_col_date;

    @FXML
    private TableColumn<BooksData, String> addBook_col_price;

    @FXML
    private AnchorPane addBook_form;

    @FXML
    private TextField addBook_search;

    @FXML
    private TableView<BooksData> addBook_tableView;

    @FXML
    private TextField add_bookAuthor;

    @FXML
    private ComboBox<?> add_bookCategory;

    @FXML
    private TextField add_bookID;

    @FXML
    private TextField add_bookName;

    @FXML
    private TextField add_bookPrice;

    @FXML
    private Button add_books_btn;

    @FXML
    private Button category_btn;

    @FXML
    private Button close;

    @FXML
    private Label home_availableBooks;

    @FXML
    private Label home_availableUsers;

    @FXML
    private Button home_btn;

    @FXML
    private AreaChart<?, ?> home_chart;

    @FXML
    private Label home_date;

    @FXML
    private Label home_totalIncome;

    @FXML
    private Button lent_btn;

    @FXML
    private Button logout;

    @FXML
    private Button minimize;

    @FXML
    private ComboBox<?> purchae_bookId;

    @FXML
    private TextField purchase_amount;

    @FXML
    private Label purchase_balance;

    @FXML
    private ComboBox<?> purchase_bookName;

    @FXML
    private Button purchase_btn_add;

    @FXML
    private Button purchase_btn_pay;
    @FXML
    private Button salest_excel_btn;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_author;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_bookName;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_category;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_id;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_price;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_publish_date;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_quantity;



    @FXML
    private AnchorPane purchase_form;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Spinner<Integer> purchase_quantity;

    @FXML
    private TableView<CustomerData> purchase_tableView;

    @FXML
    private Label purchase_total;

    @FXML
    private Label username_home;

    @FXML
    private Button users_btn;
    @FXML
    private Label home_time;

    private double x = 0;
    private  double y = 0;



    /* Get Connection sql Connection instance */

//    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;





    public void homeChart(){
        home_chart.getData().clear();

        String sql = "SELECT date, SUM(total) FROM customer_info GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 9";

        connect = database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                chart.getData().add(new XYChart.Data(

                        result.getString(1),
                        result.getString(2)
                ));
            }
            home_chart.getData().add(chart);

        }catch (Exception e){
            e.printStackTrace();
        }

    }




    public void homeDisplayBook(){
        String sql = "SELECT COUNT(id) FROM books";

        connect = database.connectDB();
        int countBs = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                countBs = result.getInt("COUNT(id)");
            }
            home_availableBooks.setText(String.valueOf(countBs));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void homeDisplay(){

        String sql = "SELECT SUM(total) FROM customer_info";

        connect = database.connectDB();
        double sumTI = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                sumTI = result.getDouble("SUM(total)");
            }
            home_totalIncome.setText("€"+ String.valueOf(sumTI));

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void homeDisplayUser(){

        String sql = "SELECT COUNT(id) FROM admin";

        connect = database.connectDB();
        int user = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                user = result.getInt("COUNT(id)");
            }
            home_availableUsers.setText(String.valueOf(user));


        }catch (Exception e){
            e.printStackTrace();
        }
    }



    // PURCHASE BOOKS

    public void  purchaseBookId() {

        String sql = "Select * FROM books";

        connect = database.connectDB();


        try {

            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                listData.add(result.getString("bookId"));
            }

            purchae_bookId.setItems(listData);
            //System.out.println(listData);
            purchaseBookName();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void  purchaseBookName() {
      //  System.out.println("TEST");

        String sql = "SELECT * FROM books WHERE bookId = '" +purchae_bookId.getSelectionModel().getSelectedItem()+ "'";
        //String sql = "Select * FROM books";

        connect = database.connectDB();


        try {

            ObservableList newList = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                newList.add(result.getString("bookName"));
            }

            purchase_bookName.setItems(newList);
            System.out.println(newList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    //PURCHASE BOOKS END


    private int qty;
    private SpinnerValueFactory<Integer> spinner;

    public void purchaseSpinner() {

        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        purchase_quantity.setValueFactory(spinner);
    }
    public void purchaseQty(){
        qty = purchase_quantity.getValue();
    }

    public ObservableList<CustomerData> purchaseListData(){
        lentABookId();
        ObservableList<CustomerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customerlent WHERE customer_id = '" + customerId + "'";

        connect = database.connectDB();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            CustomerData customerD;
            while (result.next()) {
                customerD = new CustomerData(

                        result.getInt("customer_id"),
                        result.getInt("bookId"),
                        result.getString("bookName"),
                        result.getString("bookCategory"),
                        result.getString("bookAuthor"),
                        result.getInt("bookQuantity"),
                        result.getString("bookDate"),
                        result.getString("bookPrice"));

                listData.add(customerD);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  listData;
    }


    private ObservableList<CustomerData> purchaseList;
    public void purchaseShowListData () {
        purchaseList = purchaseListData();

        purchase_col_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        purchase_col_bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        purchase_col_category.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        purchase_col_author.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("bookQuantity"));
        purchase_col_publish_date.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
        purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));


        purchase_tableView.setItems(purchaseList);
    }

    private int customerId;
    public void lentABookId(){
        String sql = "SELECT customer_id FROM customerlent";

        connect = database.connectDB();

        int checkID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                customerId = result.getInt("customer_id");
            }

            String checkData = "SELECT * FROM customer_info";
            statement = connect.createStatement();
            result = statement.executeQuery(checkData);

            while (result.next()){
                checkID = result.getInt("customer_id");
            }
            if (customerId == 0){
                customerId += 1;
            } else if (customerId == checkID) {
                customerId +=1;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private  double totalPrice = 0;
    public void purchaseAdd(){
        lentABookId();


        //purchaseQty();
        String sql = "INSERT INTO  customerlent (customer_id, bookId, bookName, bookCategory, bookAuthor, bookQuantity,bookDate,bookPrice) "
                + "VALUES(?,?,?,?,?,?,?,?)";


        connect = database.connectDB();

        try {

            Alert alert;

            if (purchae_bookId.getSelectionModel().getSelectedItem() == null
            || purchase_bookName.getSelectionModel().getSelectedItem() == null || qty == 0){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Data");
                alert.showAndWait();
            }else {



                String bookCategory = "";
                String bookAuthor = "";
                double bookPrice = 0;
                String checkData = "SELECT * FROM books WHERE bookId ='" + purchae_bookId.getSelectionModel().getSelectedItem()+ "'";


                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if(result.next()){
                    bookCategory = result.getString("bookCategory");
                    bookAuthor = result.getString("bookAuthor");
                    //bookQuantity = result.getString("bookQuantity");
                    bookPrice = result.getDouble("bookPrice");


                }
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, String.valueOf(customerId));
                prepare.setString(2, (String) purchae_bookId.getSelectionModel().getSelectedItem());
                prepare.setString(3, (String) purchase_bookName.getSelectionModel().getSelectedItem());
                prepare.setString(4,bookCategory);
                prepare.setString(5,bookAuthor);
                prepare.setString(6, String.valueOf(qty));

                totalPrice = (bookPrice * qty);
                System.out.println(totalPrice);
                prepare.setString(8, String.valueOf(totalPrice));
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(7, String.valueOf(sqlDate));
                System.out.println(sqlDate);


                prepare.executeUpdate();
                purchaseShowListData();
                purchaseDisplayTotal();

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private double totalDisplay;
    public void purchaseDisplayTotal(){
        lentABookId();
        String sql = "SELECT SUM(bookPrice) FROM customerlent WHERE customer_id= '" +customerId + "'";

        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                totalDisplay = result.getDouble("SUM(bookPrice)");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        purchase_total.setText("€" + totalDisplay);
    }

    private double balance;
    private double amount;
    public void purchaseAmount(){
        System.out.println("filed");
        //amount = Double.parseDouble(purchase_amount.getText());

        Alert alert;
        if(totalDisplay == 0 || purchase_amount.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid : 3");
            alert.showAndWait();
        } else {

            amount = Double.parseDouble(purchase_amount.getText());
             if (amount <= totalDisplay) {
                //purchase_amount.setText("");
                balance = 0;


            }else {


                 balance = (amount - totalDisplay);

             }
        }
        purchase_balance.setText( "€"+String.valueOf(balance));


    }

    public void purchasePay(){
        lentABookId();
        String sql = "INSERT INTO customer_info (customer_id, total, date) " + "VALUES(?,?,?)";

        connect = database.connectDB();

        try {
            Alert alert;

            if (purchase_amount.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose product first");
                alert.showAndWait();
            }else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you Sure?");
                Optional<ButtonType> option = alert.showAndWait();



                if (option.get().equals(ButtonType.OK)){

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, String.valueOf(totalDisplay));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(3,String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful at new Index");
                    alert.showAndWait();

                    purchase_amount.setText("");
                    purchase_balance.setText("€0.0");
                    purchase_total.setText("0.0");
                }




            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }




























    /* Add books */

    public void addBooksAdd(){
         String sql = "INSERT INTO books (bookId,bookName,bookCategory,bookPrice,date,bookAuthor) VALUES(?,?,?,?,?,?)";

         connect = database.connectDB();

         //Date format




         try {
             Alert alert;

             if(add_bookID.getText().isEmpty() || add_bookName.getText().isEmpty() ||
                     add_bookAuthor.getText().isEmpty() ||
                     add_bookCategory.getSelectionModel().getSelectedItem() == null || add_bookPrice.getText().isEmpty()){
                 alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Error Message");
                 alert.setHeaderText(null);
                 alert.setContentText("Please fill all blank space");
                 alert.showAndWait();
             }else {

                 String checkData = "SELECT bookId FROM books WHERE  bookId = '" +add_bookID.getText()+ "'";

                 statement = connect.createStatement();
                 result = statement.executeQuery(checkData);

                 if(result.next()){
                     alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("Error Message");
                     alert.setHeaderText(null);
                     alert.setContentText("BOOK ID "+ add_bookID.getText() + "Was already exist");
                     alert.showAndWait();
                 }else {


                     Date date = new Date();
                     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                     String str = formatter.format(date);
                    // home_date.setText(str);





                     prepare = connect.prepareStatement(sql);
                     prepare.setString(1, add_bookID.getText());
                     prepare.setString(2,add_bookName.getText());

                     prepare.setString(3, (String) add_bookCategory.getSelectionModel().getSelectedItem());
                     prepare.setString(4, add_bookPrice.getText());
                     prepare.setString(5, str);
                     prepare.setString(6, add_bookAuthor.getText());


//                     Date date = new Date();
//                     java.sql.Date sqlDate = new java.sql.Date((date.getTime()));
//                     prepare.setString(6, String.valueOf(sqlDate)); // Here the date is automatic 😊

//                     Date date = new Date();
//                     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
//                     String str = formatter.format(date);


                     alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Success Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Data Inserted Successfully");
                     alert.showAndWait();


                     prepare.executeUpdate();


                     //TO UPDATE THE TABLE VIEW
                     addBookShowListData();

                     //TO CLEAR THE FIELDS
                     addBooksClear();

                 }



             }




         }catch (Exception e){
             e.printStackTrace();
         }
    }

    //Book Update
    public void addBookUpdate(){
        String sql = "UPDATE books SET bookName = '" +add_bookName.getText()+"', bookCategory = '" +
                add_bookCategory.getSelectionModel().getSelectedItem() +
                "', bookPrice = '"+add_bookPrice.getText()+
                "', bookAuthor = '" +add_bookAuthor.getText()+ "' WHERE bookId = '" +add_bookID.getText()+ "'";


        connect = database.connectDB();
        try {

            Alert alert;

            if(add_bookID.getText().isEmpty() || add_bookName.getText().isEmpty() ||
                    add_bookAuthor.getText().isEmpty() ||
                    add_bookCategory.getSelectionModel().getSelectedItem() == null || add_bookPrice.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank space");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sur to update bookId:"+add_bookID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.execute(sql);



                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Data Updated Successfully");
                    alert.showAndWait();

                    //To update the data
                    addBookShowListData();
                    addBooksClear();

                }
               // alert.showAndWait();
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addBookDelete(){

        String sql = "DELETE FROM books WHERE bookId = '" +add_bookID.getText() + "'";

        connect = database.connectDB();

        try {

            Alert alert;

            if(add_bookID.getText().isEmpty() || add_bookName.getText().isEmpty() ||
                    add_bookAuthor.getText().isEmpty() ||
                    add_bookCategory.getSelectionModel().getSelectedItem() == null || add_bookPrice.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("No space should be blank");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sur to delete bookId:"+add_bookID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.execute(sql);



                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Data Deleted Successfully");
                    alert.showAndWait();

                    //To update the data
                    addBookShowListData();
                    addBooksClear();

                }
                // alert.showAndWait();
            }

        }catch (Exception e){

        }
    }



    public void addBooksClear(){
        add_bookID.setText("");
        add_bookName.setText("");
        add_bookCategory.getSelectionModel().clearSelection();
        add_bookPrice.setText("");
        add_bookAuthor.setText("");

    }


    private  String[] categoryList = {"Fun","Action","Education","Love","Joy","Science","Art"};
    public void  addBookCategoryList(){
      List<String> listS = new ArrayList<>();

//      -----------------------------
        //sql

//
//        String sql = "SELECT * FROM category";
//        connect = database.connectDB();
//        try {
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//            ObservableList listData = FXCollections.observableArrayList();
//
//
//            while (result.next()){
//
//                String item = result.getString("categoryName");
//                 listData.add(item);
//
//
//                    add_bookCategory.setItems(listData);
//
//
//
//            }
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        //sql


//        -------------------------------------------
        for(String data: categoryList){
            listS.add(data);
        }
        ObservableList listD = FXCollections.observableArrayList(listS);
        add_bookCategory.setItems(listD);


    }


    public void exportToExcel(){

        String sql = "SELECT * FROM books";
        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("book details");
            HSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("bookId");
            header.createCell(1).setCellValue("bookName");
            header.createCell(2).setCellValue("bookCategory");
            header.createCell(3).setCellValue("bookPrice");
            header.createCell(4).setCellValue("date");
            header.createCell(5).setCellValue("bookAuthor");

            int index = 1;
            while (result.next()){
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(result.getString("bookId"));
                row.createCell(1).setCellValue(result.getString("bookName"));
                row.createCell(2).setCellValue(result.getString("bookCategory"));
                row.createCell(3).setCellValue(result.getString("bookPrice"));
                row.createCell(4).setCellValue(result.getString("date"));
                row.createCell(5).setCellValue(result.getString("bookAuthor"));

                index++;

            }

            FileOutputStream fileOut = new FileOutputStream("BooksDetails.xls");
            wb.write(fileOut);
            fileOut.close();

            Alert alert;

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS!!");
            alert.setHeaderText(null);
            alert.setContentText("XLSX FILE GENERATED SUCCESSFULLY");
            alert.showAndWait();

            prepare.close();
            result.close();



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void exportSalesToExcel(){

        String sql = "SELECT * FROM customerlent";
        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("book sales");
            HSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("customer_id");
            header.createCell(1).setCellValue("bookId");
            header.createCell(2).setCellValue("bookName");
            header.createCell(3).setCellValue("bookCategory");
            header.createCell(4).setCellValue("bookAuthor");
            header.createCell(5).setCellValue("bookQuantity");
            header.createCell(6).setCellValue("bookDate");
            header.createCell(7).setCellValue("bookPrice");


            int index = 1;
            while (result.next()){
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(result.getString("customer_id"));
                row.createCell(1).setCellValue(result.getString("bookId"));
                row.createCell(2).setCellValue(result.getString("bookName"));
                row.createCell(3).setCellValue(result.getString("bookCategory"));
                row.createCell(4).setCellValue(result.getString("bookAuthor"));
                row.createCell(5).setCellValue(result.getString("bookQuantity"));
                row.createCell(6).setCellValue(result.getString("bookDate"));
                row.createCell(7).setCellValue(result.getString("bookPrice"));


                index++;

            }
            FileOutputStream fileOut = new FileOutputStream("sales.xls");
            wb.write(fileOut);
            fileOut.close();

            Alert alert;

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS!!");
            alert.setHeaderText(null);
            alert.setContentText("XLSX FILE GENERATED SUCCESSFULLY");
            alert.showAndWait();

            prepare.close();
            result.close();



        }catch (Exception e){
            e.printStackTrace();
        }

    }





    public ObservableList<BooksData> addBookListData(){

        ObservableList<BooksData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM books";
        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            BooksData bookD;

            while (result.next()){
                bookD = new BooksData(

                        result.getInt("bookId"),
                        result.getString("bookName"),
                        result.getString("bookCategory"),
                        result.getDouble("bookPrice"),
                        result.getString("date"),
                        result.getString("bookAuthor"));

                listData.add(bookD);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return listData;
    }




    private ObservableList<BooksData> addBookList;
    public void addBookShowListData(){
        addBookList = addBookListData();


        addBook_col_id.setCellValueFactory( new PropertyValueFactory<>("bookId"));
        addBook_col_name.setCellValueFactory( new PropertyValueFactory<>("bookName"));
        addBook_col_category.setCellValueFactory( new PropertyValueFactory<>("bookCategory"));
        addBook_col_price.setCellValueFactory( new PropertyValueFactory<>("bookPrice"));
        addBook_col_date.setCellValueFactory( new PropertyValueFactory<>("bookDate"));
        addBook_col_author.setCellValueFactory( new PropertyValueFactory<>("bookAuthor"));

        addBook_tableView.setItems(addBookList);
    }
    //addBook Search
    public void  addBookSearch() {


            FilteredList<BooksData> filter = new FilteredList<>(addBookList, e-> true);
            addBook_search.textProperty().addListener((Observable, oldValue, newValue) -> {
                filter.setPredicate(predicateBookData ->{

                    if(newValue == null || newValue.isEmpty()){
                        return  true;
                    }

                    String searchKey = newValue.toLowerCase();
                    if(predicateBookData.getBookId().toString().contains(searchKey)){
                        return  true;
                    }
                else if (predicateBookData.getBookName().toLowerCase().contains(searchKey)) {
                   return  true;

                } else if (predicateBookData.getBookCategory().toLowerCase().contains(searchKey)) {
                    return  true;

               } else if (predicateBookData.getBookPrice().toString().contains(searchKey)) {
                    return  true;

                } else if (predicateBookData.getBookAuthor().toLowerCase().contains(searchKey)) {
                    return  true;

               }else{ return false;}
                });
            });
            System.out.println("Searching...");
            SortedList<BooksData> sortList = new SortedList<>(filter);
            sortList.comparatorProperty().bind(addBook_tableView.comparatorProperty());

            addBook_tableView.setItems(sortList);


    }


    public void addBookSelect(){
        BooksData bookD = addBook_tableView.getSelectionModel().getSelectedItem();
        int num = addBook_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) { return; }
        add_bookID.setText(String.valueOf(bookD.getBookId()));
        add_bookName.setText(bookD.getBookName());

        //add_bookCategory.getSelectionModel().getSelectedItem();
        add_bookPrice.setText(String.valueOf(bookD.getBookPrice()));
        add_bookAuthor.setText(bookD.getBookAuthor());
    }
















    /* Get the current date */
    public void getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String str = formatter.format(date);
        home_date.setText(str);
        System.out.println("Current Date is: " + str);
    }
    /* Get current time in second */

    public void getTimeInSecond(){
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localtime = LocalTime.now();
        String time = dateTime.format(localtime);
        home_time.setText(time);
    }

    /* SwitchForm Function START achillesoh15@gmail.com */

    public void switchForm(ActionEvent event){
        if(event.getSource() == home_btn){
            home_form.setVisible(true);
            addBook_form.setVisible(false);
            purchase_form.setVisible(false);

            home_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #52ae8b, #9a2d3d);");
            add_books_btn.setStyle("-fx-background-color:transparent");
            lent_btn.setStyle("-fx-background-color:transparent");


            homeDisplayBook();
            homeDisplay();
            homeDisplayUser();
           // homeChart();
        } else if (event.getSource() == add_books_btn) {

            home_form.setVisible(false);
            addBook_form.setVisible(true);
            purchase_form.setVisible(false);

            add_books_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #52ae8b, #9a2d3d);");
            home_btn.setStyle("-fx-background-color:transparent");
            lent_btn.setStyle("-fx-background-color:transparent");

            //SHOW UPDATED TABLE

            addBookShowListData();
            addBookCategoryList();
            addBookSearch();
        } else if (event.getSource() == lent_btn) {

            addBook_form.setVisible(false);
            home_form.setVisible(false);
            purchase_form.setVisible(true);



            lent_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #52ae8b, #9a2d3d);");
            add_books_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            purchaseBookId();
            purchaseBookName();
            purchaseSpinner();
            purchaseShowListData();
            purchaseDisplayTotal();
            
        }

    }
    /* SwitchForm Function END HERE @achillesoh15@gmail.com */




    /* Logout Function */
    public void logOut(){

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to (LOGOUT) ?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)){
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);




                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });
                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);


                stage.setScene(scene);
                stage.show();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void defaultNav(){
        home_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #52ae8b, #9a2d3d);");
    }

    public void displayUsername(){
        String user = getData.username;
        username_home.setText(user.substring(0,1).toLowerCase() + user.substring(1));
    }

    /* Close Function */
    public void close(){
        System.exit(0);
    }
    /* Minimize Function */
    public void minimize(){
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayUsername();
        defaultNav();
        homeDisplayBook();
        homeDisplay();
        homeDisplayUser();
        //homeChart();


       getCurrentDate();
       System.out.println(" ");
       getTimeInSecond();

       addBookShowListData();

        addBookCategoryList();
        //addBookUpdate();
        purchaseBookId();
        purchaseBookName();
        purchaseSpinner();
        purchaseShowListData();
        purchaseDisplayTotal();




    }


}
