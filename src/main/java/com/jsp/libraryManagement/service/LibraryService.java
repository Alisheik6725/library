package com.jsp.libraryManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jsp.libraryManagement.entity.Book;
import com.jsp.libraryManagement.entity.Borrower;
import com.jsp.libraryManagement.entity.Borrowing;
import com.jsp.libraryManagement.repo.BookRepository;
import com.jsp.libraryManagement.repo.BorrowerRepository;
import com.jsp.libraryManagement.repo.BorrowingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

 @Autowired
 private BookRepository bookRepository;

 @Autowired
 private BorrowerRepository borrowerRepository;

 @Autowired
 private BorrowingRepository borrowingRepository;


 public Book addBook(Book book) {
     return bookRepository.save(book);
 }

 public void updateBook(Long id, Book bookDetails) {
     Book book = bookRepository.findById(id).orElseThrow();
     book.setTitle(bookDetails.getTitle());
     book.setAuthor(bookDetails.getAuthor());
     book.setQuantity(bookDetails.getQuantity());
     bookRepository.save(book);
 }

 public String removeBook(Long id) {
	Optional<Book> db=bookRepository.findById(id);
	if(db.isPresent()) {
	 bookRepository.deleteById(id);
	 
	 return "deleted successfuly";
	}
	return "id not exist";
 }

 public List<Book> searchBooks(String title, String author, String genre) {
    
     return bookRepository.findAll(); 
 }

 // Borrower Management
 public Borrower addBorrower(Borrower borrower) {
     return borrowerRepository.save(borrower);
 }

 public void updateBorrower(String membershipId, Borrower borrowerDetails) {
     Borrower borrower = borrowerRepository.findById(membershipId).orElseThrow();
     borrower.setContact(borrowerDetails.getContact());
     borrowerRepository.save(borrower);
 }

 public String removeBorrower(String membershipId) {
	Optional<Borrower> db= borrowerRepository.findById(membershipId);
	if(db.isPresent()) {
     borrowerRepository.deleteById(membershipId);
     
     return "deleted successfully";
	}
	return "id does not exists";
 }

 // Borrowing and Returning
 public String borrowBook(String membershipId, String isbn) {
     Borrower borrower = borrowerRepository.findById(membershipId).orElseThrow();
     System.out.println("********"+borrower);
     Book book = bookRepository.findByIsbn(isbn);
     System.out.println("********"+book);

     if (book != null && book.borrow()) {
         Borrowing borrowing = new Borrowing();
         borrowing.setBorrower(borrower);
         borrowing.setBook(book);
         borrowing.setBorrowDate(LocalDate.now());
         borrowing.setDueDate(LocalDate.now().plusDays(14)); 
         System.out.println("********"+borrowing);

         borrowingRepository.save(borrowing);
         return book.getTitle() + " borrowed successfully";
     }
     return "No copies available to borrow.";
 }

 public String returnBook(String membershipId, String isbn) {
     Borrowing borrowing = borrowingRepository.findByBorrower_MembershipIdAndBook_Isbn(membershipId, isbn);
     if (borrowing != null) {
         Book book = borrowing.getBook();
         book.returnBook();
         System.out.println("********"+book);
         bookRepository.save(book);
         borrowingRepository.delete(borrowing);
         return book.getTitle() + " returned successfully.";
     }
     return "This book was not borrowed.";
 }
}