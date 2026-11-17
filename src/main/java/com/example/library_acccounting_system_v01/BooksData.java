package com.example.library_acccounting_system_v01;

public class BooksData {

    private  Integer bookId;
    private String bookName;
    private String bookCategory;
    private Double bookPrice;
    private String bookDate;
    private String bookAuthor;


    public BooksData(Integer bookId, String bookName, String bookCategory, Double bookPrice, String bookDate, String bookAuthor) {

        this.bookId = bookId;
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.bookPrice = bookPrice;
        this.bookDate = bookDate;
        this.bookAuthor = bookAuthor;
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

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}
