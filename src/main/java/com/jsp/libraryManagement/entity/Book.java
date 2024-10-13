package com.jsp.libraryManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="tbl_book")
public class Book {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String title;
 private String author;
 private String isbn;
 private String genre;
 private int quantity;
 private int borrowedCount;


 public int getAvailability() {
     return quantity - borrowedCount;
 }

 public boolean borrow() {
     if (quantity > borrowedCount) {
         borrowedCount++;
         return true;
     }
     return false;
 }

 public boolean returnBook() {
     if (borrowedCount > 0) {
         borrowedCount--;
         return true;
     }
     return false;
 }
}
