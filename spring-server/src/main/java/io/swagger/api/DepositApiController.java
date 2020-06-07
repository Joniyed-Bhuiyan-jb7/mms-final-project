package io.swagger.api;

import io.swagger.exceptions.InvalidInputException;
import io.swagger.exceptions.ResourceAlreadyExistException;
import io.swagger.exceptions.ResourceDoesNotExistException;
import io.swagger.interfaces.DepositApi;
import io.swagger.model.DepositInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-28T06:14:18.281Z")

@Controller
public class DepositApiController implements DepositApi {

    private static final Logger log = LoggerFactory.getLogger(DepositApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    @org.springframework.beans.factory.annotation.Autowired
    public DepositApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private DepositService depositService;


    public ResponseEntity<DepositInformation> addDeposit(@ApiParam(value = "deposit object that needs to be added to the db" ,required=true )  @Valid @RequestBody DepositInformation depositInformation) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(depositService.addDeposit(depositInformation));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Void> deleteDeposit(@ApiParam(value = " to delete",required=true) @PathVariable("depositId") long depositNo) {
        try {
            depositService.deleteDeposit(depositNo);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<List<DepositInformation>> getMonthDeposit(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month, @ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(depositService.getMonthDepositList(month,year));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<Double> getSumOfMonthDeposit(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month, @ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(depositService.getSumOfMonthDepositList(month,year));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<List<DepositInformation>> getMonthDepositforMember(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month,@ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year,@ApiParam(value = "information of a user to return",required=true) @PathVariable("username") String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(depositService.getMonthDepositForMember(month,year,username));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<Double> getSumOfMonthDepositforMember(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month,@ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year,@ApiParam(value = "information of a user to return",required=true) @PathVariable("username") String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(depositService.getSumOfMonthDepositForMember(month,year,username));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<DepositInformation> updateDeposit(@ApiParam(value = " to update",required=true) @PathVariable("depositId") long depositId,@ApiParam(value = "deposit object that needs to be update" ,required=true )  @Valid @RequestBody DepositInformation depositInformation) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(depositService.updateDepositInformation(depositId,depositInformation));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
