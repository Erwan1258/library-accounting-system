package com.example.library_acccounting_system_v01;

import java.util.Date;

public class CustomerData {

    private  Integer customerId;
    private  Integer bookId;
    private String bookName;
    private  String bookCategory;
    private String bookAuthor;
    private Integer bookQuantity;
    private String bookDate;
    private  String bookPrice;


    public CustomerData(Integer customerId, Integer bookId, String bookName, String bookCategory, String bookAuthor, Integer bookQuantity
    ,String bookDate, String bookPrice){
            this.customerId = customerId;
            this.bookId = bookId;
            this.bookName = bookName;
            this.bookCategory =bookCategory;
            this.bookAuthor = bookAuthor;
            this.bookQuantity = bookQuantity;
            this.bookDate = bookDate;
            this.bookPrice = bookPrice;

    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }
}
