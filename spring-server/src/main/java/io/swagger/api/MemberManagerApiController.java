package io.swagger.api;

import io.swagger.exceptions.ResourceAlreadyExistException;
import io.swagger.exceptions.ResourceDoesNotExistException;
import io.swagger.interfaces.MemberManagerApi;
import io.swagger.model.MemberManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repository.MemberManagerRepository;
import io.swagger.service.MemberManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-28T06:14:18.281Z")

@Controller
public class MemberManagerApiController implements MemberManagerApi {

    private static final Logger log = LoggerFactory.getLogger(MemberManagerApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;


    @Autowired
    private MemberManagerService membermanagerService;


    @org.springframework.beans.factory.annotation.Autowired
    public MemberManagerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<MemberManager> addUser(@ApiParam(value = "daily activities object that needs to be added to the db" ,required=true )  @Valid @RequestBody MemberManager memberManager) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(membermanagerService.saveUser(memberManager));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "information of a user to delete",required=true) @PathVariable("username") String username) {
        try {
            membermanagerService.deleteUser(username);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<MemberManager> getSingleUserInformation(@ApiParam(value = "information of a user to return",required=true) @PathVariable("username") String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(membermanagerService.getSingleUser(username));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<List<MemberManager>> getUsers() {
        try {
            return ResponseEntity.ok().body(membermanagerService.getAllUser());
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<MemberManager> updateUser(@ApiParam(value = "information of a user to uupdate",required=true) @PathVariable("username") String username,@ApiParam(value = "Updated user" ,required=true )  @Valid @RequestBody MemberManager memberManager) {
        try {
            return ResponseEntity.ok().body(membermanagerService.update(username,memberManager));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
