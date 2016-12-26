package com.minichat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.minichat.model.UserDetail;
import com.minichat.service.UserService;

@RestController
public class UserController {

	
	@Autowired
    public UserService userService;  //Service which will do all data retrieval/manipulation work
  
     
    //-------------------Retrieve All Users--------------------------------------------------------
      
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserDetail>> listAllUsers() {
        List<UserDetail> userDetails = userService.findAllUsers();
        try
        {    
        return new ResponseEntity<List<UserDetail>>(userDetails, HttpStatus.OK);
        }
        catch(Exception e)
        {
        return new ResponseEntity<List<UserDetail>>(HttpStatus.NO_CONTENT);
        }
    }
  
  
     
    //-------------------Retrieve Single UserDetail--------------------------------------------------------
      
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetail> getUser(@PathVariable("userId") int userId) {
        System.out.println("Fetching UserDetail with Id " + userId);
        UserDetail user = userService.findById(userId);
        if (user == null) {
            System.out.println("UserDetail with Id " + userId + " not found");
            return new ResponseEntity<UserDetail>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
    }
  
      
      
    //-------------------Create a UserDetail--------------------------------------------------------
      
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserDetail user,UriComponentsBuilder ucBuilder) {
        System.out.println("Creating UserDetail " + user.getUsername());
  
        if (userService.isUserExist(user)) {
            System.out.println("A UserDetail with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        Date date = new Date();
        user.setUserCreationDate(date);
        userService.saveUser(user);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{userId}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
     
      
    //------------------- Update a UserDetail --------------------------------------------------------
      
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<UserDetail> updateUser(@PathVariable("userId") int userId, @RequestBody UserDetail userDetail) {
        System.out.println("Updating UserDetail " + userId);
          
        UserDetail currentUser = userService.findById(userId);
          
        if (currentUser==null) {
            System.out.println("UserDetail with id " + userId + " not found");
            return new ResponseEntity<UserDetail>(HttpStatus.NOT_FOUND);
        }
  
        currentUser.setContactNo(userDetail.getContactNo());
        currentUser.setFirstName(userDetail.getFirstName());
        currentUser.setLastName(userDetail.getLastName());
        currentUser.setDob(userDetail.getDob());
        currentUser.setGender(userDetail.getGender());
        currentUser.setRole(userDetail.getRole());
        currentUser.setPassword(userDetail.getPassword());
        currentUser.setUsername(userDetail.getUsername());
        currentUser.setEmailId(userDetail.getEmailId());
          
        userService.updateUser(currentUser);
        return new ResponseEntity<UserDetail>(currentUser, HttpStatus.OK);
    }
  
     
     
    //------------------- Delete a UserDetail --------------------------------------------------------
      
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<UserDetail> deleteUser(@PathVariable("userId") int userId) {
        System.out.println("Fetching & Deleting UserDetail with id " + userId);
  
        UserDetail user = userService.findById(userId);
        if (user == null) {
            System.out.println("Unable to delete. UserDetail with id " + userId + " not found");
            return new ResponseEntity<UserDetail>(HttpStatus.NOT_FOUND);
        }
  
        userService.deleteUserById(userId);
        return new ResponseEntity<UserDetail>(HttpStatus.NO_CONTENT);
    }
  
      
     
    //------------------- Delete All Users --------------------------------------------------------
      
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<UserDetail> deleteAllUsers() {
        System.out.println("Deleting All Users");
  
        userService.deleteAllUsers();
        return new ResponseEntity<UserDetail>(HttpStatus.NO_CONTENT);
    }

}
