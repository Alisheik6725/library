package com.jsp.libraryManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.libraryManagement.entity.Borrower;
@Repository
public interface BorrowerRepository extends JpaRepository<com.jsp.libraryManagement.entity.Borrower, String> {
	
}
