package io.swagger.api;

import io.swagger.exceptions.InvalidInputException;
import io.swagger.exceptions.ResourceAlreadyExistException;
import io.swagger.exceptions.ResourceDoesNotExistException;
import io.swagger.interfaces.EvaluationApi;
import io.swagger.model.DepositInformation;
import io.swagger.model.Evaluation;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.EvaluationService;
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
public class EvaluationApiController implements EvaluationApi {

    private static final Logger log = LoggerFactory.getLogger(EvaluationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EvaluationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private EvaluationService evaluationService;

    public ResponseEntity<Evaluation> addEvaluation(@ApiParam(value = "evaluation object that needs to be added to the db" ,required=true )  @Valid @RequestBody Evaluation evaluation) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(evaluationService.addEvaluation(evaluation));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Double> getAvgEvaluation(@ApiParam(value = "information of a manager evaluation to return",required=true) @PathVariable("month") int month,@ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year,@ApiParam(value = "information of a user to return",required=true) @PathVariable("username") String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(evaluationService.getAvgEvaluation(month,year,username));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<List<Evaluation>> getEvaluation(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month, @ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year, @ApiParam(value = "information of a user to return",required=true) @PathVariable("managerUserName") String managerUserName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(evaluationService.getMonthEvaluation(month,year,managerUserName));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

}
