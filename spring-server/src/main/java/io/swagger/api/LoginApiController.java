package io.swagger.api;

import io.swagger.exceptions.ResourceAlreadyExistException;
import io.swagger.exceptions.ResourceDoesNotExistException;
import io.swagger.interfaces.LoginApi;
import io.swagger.model.LoginInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.UserWithRole;
import io.swagger.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-28T06:14:18.281Z")

@Controller
public class LoginApiController implements LoginApi {

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private LoginService loginService;

    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    public ResponseEntity<Void> deleteLoginInforation(@ApiParam(value = " to delete",required=true) @PathVariable("username") String username) {
        try {
            loginService.deleteLoginInformation(username);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<LoginInformation> getLoginInformation(@ApiParam(value = "information of a user to return",required=true) @PathVariable("username") String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(loginService.getLoginInformation(username));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<LoginInformation> saveLoginInformation(@ApiParam(value = "login_information object that needs to be added to the db" ,required=true )  @Valid @RequestBody LoginInformation loginInformation) {
        try {
            LoginInformation savedLoginInformation = loginService.saveLoginInformation(loginInformation);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLoginInformation);
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<LoginInformation> updateLoginInformation(@ApiParam(value = "",required=true) @PathVariable("username") String username,@ApiParam(value = "Login inforation object that needs to be update" ,required=true )  @Valid @RequestBody LoginInformation loginInformation) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(loginService.updateLoginInformation(username,loginInformation));
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    public ResponseEntity<Void> deleteAllLoginInformation(){
        try {
            loginService.deleteAll();
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<UserWithRole>> getAllMeal() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(loginService.getUerAndRole());
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
