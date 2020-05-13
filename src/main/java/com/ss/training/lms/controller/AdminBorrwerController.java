package com.ss.training.lms.controller;

import java.sql.SQLException;
import java.util.List;

import javax.websocket.server.PathParam;

import com.ss.training.lms.entity.Borrower;
import com.ss.training.lms.service.admin.AdminBorrowerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="lms/admin")
public class AdminBorrwerController {

    @Autowired
    AdminBorrowerService adminBorrowerService;

    @PostMapping(path = "/borrowers")
    public ResponseEntity<Borrower> addABorrower(@RequestBody Borrower borrower) {
        HttpStatus status = HttpStatus.CREATED;

        if (borrower == null || borrower.getName().length() > 45) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Borrower>(borrower, status);
        }
        try {
            adminBorrowerService.addABorrower(borrower);
        } catch (SQLException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<Borrower>(borrower, status);
        }
        return new ResponseEntity<Borrower>(borrower, status);
    }
    

    @DeleteMapping(path = "/borrowers")
    public ResponseEntity<Borrower> deleteABorrower(@RequestBody Borrower borrower) {
        HttpStatus status = HttpStatus.ACCEPTED;

        if (borrower == null || borrower.getCardNo() == null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Borrower>(borrower, status);
        }
        try {
        	Borrower checkTable = adminBorrowerService.readABorrower(borrower.getCardNo());
        	if (checkTable == null)
        	{
        		status = HttpStatus.NOT_FOUND;
                return new ResponseEntity<Borrower>(borrower, status);
        	}
            adminBorrowerService.deleteABorrower(borrower);
        } catch (SQLException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<Borrower>(borrower, status);
        }
        return new ResponseEntity<Borrower>(borrower, status);
    }

    @PutMapping(path = "/borrowers")
    public ResponseEntity<Borrower> updateABorrower(@RequestBody Borrower borrower) {
        HttpStatus status = HttpStatus.ACCEPTED;

        if (borrower == null || borrower.getCardNo() == null || borrower.getName().length() > 45) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Borrower>(borrower, status);
        }
        try {
        	Borrower findBorrower = adminBorrowerService.readABorrower(borrower.getCardNo());
        	if (findBorrower == null)
        	{
        		status = HttpStatus.NOT_FOUND;
                return new ResponseEntity<Borrower>(borrower, status);
        	}
            adminBorrowerService.updateABorrower(borrower);
        } catch (SQLException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<Borrower>(borrower, status);
        }
        return new ResponseEntity<Borrower>(borrower, status);
    }

    @RequestMapping(path = "/borrowers/{cardNo}")
    public ResponseEntity<Borrower> readABorrower(@PathVariable int cardNo) {
        HttpStatus status = HttpStatus.OK;
        Borrower borrower = null;

        try {
            borrower = adminBorrowerService.readABorrower(cardNo);
            if (borrower == null)
            {
                status = HttpStatus.NOT_FOUND;
                return new ResponseEntity<Borrower>(borrower, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<Borrower>(borrower, status);
        }
        return new ResponseEntity<Borrower>(borrower, status);
    }

    @RequestMapping(path = "/borrowers")
    public ResponseEntity<List<Borrower>> readAllBorrowers() {
        HttpStatus status = HttpStatus.OK;
        List<Borrower> borrowers = null;

        try {
            borrowers = adminBorrowerService.readAllBorrowers();
            if (borrowers == null)
            {
                status = HttpStatus.NO_CONTENT;
                return new ResponseEntity<List<Borrower>>(borrowers, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<List<Borrower>>(borrowers, status);
        }
        return new ResponseEntity<List<Borrower>>(borrowers, status);
    }
}