package io.swagger.api;

import io.swagger.exceptions.InvalidInputException;
import io.swagger.exceptions.ResourceAlreadyExistException;
import io.swagger.exceptions.ResourceDoesNotExistException;
import io.swagger.interfaces.DailyActivitiesApi;
import io.swagger.model.DailyActivities;
import io.swagger.model.DepositInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.DailyActivitiesService;
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
public class DailyActivitiesApiController implements DailyActivitiesApi {

    private static final Logger log = LoggerFactory.getLogger(DailyActivitiesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public DailyActivitiesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private DailyActivitiesService dailyActivitiesService;

    public ResponseEntity<DailyActivities> addDailyActivities(@ApiParam(value = "daily activities object that needs to be added to the db" ,required=true )  @Valid @RequestBody DailyActivities dailyActivities) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dailyActivitiesService.addDailyActivities(dailyActivities));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Void> deleteActivity(@ApiParam(value = "information of a user to return",required=true) @PathVariable("activity_no") long activityNo) {
        try {
            dailyActivitiesService.deleteActivity(activityNo);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<List<DailyActivities>> getAllForMonth(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month, @ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dailyActivitiesService.getAllForMonth(month,year));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
    public ResponseEntity<Double> getSumOfAllForMonth(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month, @ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dailyActivitiesService.getSumOfAllForMonth(month,year));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @Override
    public ResponseEntity<List<DailyActivities>> getDailyActivitiesOfUserForMonth(@ApiParam(value = "information of a user to return",required=true) @PathVariable("month") int month,@ApiParam(value = "information of a user to return",required=true) @PathVariable("year") int year,@ApiParam(value = "information of a user to return",required=true) @PathVariable("username") String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dailyActivitiesService.getDailyActivitiesOfUserForMonth(month,year,username));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<DailyActivities> updateActivity(@ApiParam(value = "information of a user to return",required=true) @PathVariable("serialNo") long serial_no,@ApiParam(value = "daily activities object that needs to be update" ,required=true )  @Valid @RequestBody DailyActivities dailyActivities) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dailyActivitiesService.updateDailyActivities(serial_no,dailyActivities));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
