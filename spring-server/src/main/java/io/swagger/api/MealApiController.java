package io.swagger.api;

import io.swagger.exceptions.InvalidInputException;
import io.swagger.exceptions.ResourceAlreadyExistException;
import io.swagger.exceptions.ResourceDoesNotExistException;
import io.swagger.interfaces.MealApi;
import io.swagger.model.LoginInformation;
import io.swagger.model.Meal;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-28T06:14:18.281Z")

@Controller
public class MealApiController implements MealApi {

    private static final Logger log = LoggerFactory.getLogger(MealApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    @org.springframework.beans.factory.annotation.Autowired
    public MealApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private MealService mealService;

    public ResponseEntity<Meal> addMeal(@ApiParam(value = "meal object that needs to be added to the db" ,required=true )  @Valid @RequestBody Meal meal) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mealService.addMeal(meal));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Void> deleteMeal(@ApiParam(value = " to delete",required=true) @PathVariable("mealNo") long mealNo) {
        try {
            mealService.deleteMeal(mealNo);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<List<Meal>> getAllMeal() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mealService.getAllMeal());
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<List<Meal>> getMeal(@ApiParam(value = "information of a meal to return",required=true) @PathVariable("username") String username, @ApiParam(value = "information of a meal to return",required=true) @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate date) {
        try {
           return ResponseEntity.status(HttpStatus.OK).body(mealService.getMeal(username,date));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Meal> updateMeal(@ApiParam(value = "update single meal information",required=true) @PathVariable("mealNo") long mealNo,@ApiParam(value = "meal object that needs to be update" ,required=true )  @Valid @RequestBody Meal meal) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mealService.updateMeal(mealNo,meal));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<List<Meal>> getMonthMeal(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month,@ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year) {
        try {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(mealService.getMonthMeal(month,year));
            } catch (InvalidInputException e) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            }
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Double> getSumOfMonthMeal(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month,@ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mealService.getSumOfMonthMeal(month,year));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<List<Meal>> getUserMonthMeal(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month,@ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year,@ApiParam(value = "information of a user to return",required=true) @PathVariable("username") String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mealService.getUserMonthMeal(month,year,username));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<Double> getSumOfUserMonthMeal(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month,@ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year,@ApiParam(value = "information of a user to return",required=true) @PathVariable("username") String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mealService.getSumOfUserMonthMeal(month,year,username));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

}
