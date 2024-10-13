package com.jsp.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jsp.libraryManagement.entity.Book;
import com.jsp.libraryManagement.entity.Borrower;
import com.jsp.libraryManagement.service.LibraryService;

import java.util.List;

@RestController
//@RequestMapping("/api/library")
public class LibraryController {

 @Autowired
 private LibraryService libraryService;


 @PostMapping("/books")
 public Book addBook(@RequestBody Book book) {
     return libraryService.addBook(book);
 }

 @PutMapping("/books/{id}")
 public void updateBook(@PathVariable(name = "id") Long id, @RequestBody Book book) {
     libraryService.updateBook(id, book);
 }

 @DeleteMapping("/books/{id}")
 public String removeBook(@PathVariable(name="id") Long id) {
     return libraryService.removeBook(id);
 }

 @GetMapping("/books/search")
 public List<Book> searchBooks(
     @RequestParam(name = "title", required = false) String title,
     @RequestParam(name = "author", required = false) String author,
     @RequestParam(name = "genre", required = false) String genre) {
     return libraryService.searchBooks(title, author, genre);
 }

 @PostMapping("/borrowers")
 public Borrower addBorrower(@RequestBody Borrower borrower) {
     return libraryService.addBorrower(borrower);
 }

 @PutMapping("/borrowers/{membershipId}")
 public void updateBorrower(@PathVariable(name="membershipId") String membershipId, @RequestBody Borrower borrower) {
     libraryService.updateBorrower(membershipId, borrower);
 }

 @DeleteMapping("/borrowers/{membershipId}")
 public String removeBorrower(@PathVariable(name="membershipId") String membershipId) {
     return libraryService.removeBorrower(membershipId);
 }

 @PostMapping("/borrow")
 public String borrowBook(@RequestParam(name="membershipId") String membershipId, @RequestParam(name="isbn") String isbn) {
     return libraryService.borrowBook(membershipId, isbn);
 }

 @PostMapping("/return")
 public String returnBook(@RequestParam(name="membershipId") String membershipId, @RequestParam(name="isbn") String isbn) {
     return libraryService.returnBook(membershipId, isbn);
 }
}