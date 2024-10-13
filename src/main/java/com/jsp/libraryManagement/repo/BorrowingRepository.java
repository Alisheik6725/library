package com.jsp.libraryManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.jsp.libraryManagement.entity.Borrowing;
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long>{
	
    Borrowing findByBorrower_MembershipIdAndBook_Isbn(String membershipId, String isbn);


}