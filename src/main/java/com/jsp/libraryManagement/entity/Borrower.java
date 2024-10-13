package com.jsp.libraryManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="tbl_borrower")
public class Borrower {
 @Id
 private String membershipId;
 private String name;
 private String contact;


}