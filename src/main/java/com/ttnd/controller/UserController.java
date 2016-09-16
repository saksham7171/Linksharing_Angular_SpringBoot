package com.ttnd.controller;

import com.ttnd.domain.User;
import com.ttnd.pojo.Response;
import com.ttnd.pojo.CO.UserCO;
import com.ttnd.repository.TopicRepository;
import com.ttnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

     @Autowired
     TopicRepository topicRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> login(@RequestBody User user) {
        User userObj = userRepository.findByUsername(user.getUsername());
        if (userObj != null && userObj.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<Response>(new Response("ok", userObj.getId().toString()), HttpStatus.OK);
        }
        return new ResponseEntity<Response>(new Response("fail", "Wrong Credentials"), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> register(@RequestBody User user) {
        Response response = new Response();
        User userObj = userRepository.findByUsername(user.getUsername());
        if (userObj == null) {
            user.setImage(defaultImage());
            userRepository.save(user);
            response.setStatus("ok");
            response.setMessage("Successfully Registered ! you can Log In Now");
        } else {
            response.setStatus("fail");
            response.setMessage("Username already exists !");
        }
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> update(@RequestHeader(value = "userId") String id, @RequestBody User user) {
        Response response = new Response();
        User userObj = userRepository.getOne(new Long(id));
        try {
            userObj.setFirstName(user.getFirstName());
            userObj.setLastName(user.getLastName());
            userObj.setEmail(user.getEmail());
            userRepository.save(userObj);
            response.setStatus("ok");
            response.setMessage("Profile Updated !");
        } catch (Exception e) {
            response.setStatus("fail");
            response.setMessage("Sorry Can't Update. Try Later !");
        }
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping("/uploadProfileImage/{id}")
    public ResponseEntity<Response> handleFileUpload(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        Response response = new Response();
        User userObj = userRepository.getOne(id);
        try {
            userObj.setImage(file.getBytes());
            userRepository.save(userObj);
            response.setStatus("ok");
            response.setMessage("Image Updated !");
        } catch (Exception e) {
            response.setStatus("fail");
            response.setMessage("Sorry Can't Update. Try Later !");
        }
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping("/userImage/{id}")
    @ResponseBody
    public byte[] userImage(@PathVariable("id") Long id) {
        User userObj = userRepository.getOne(id);
        return userObj.getImage();
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updatePassword(@RequestHeader(value = "userId") String id, @RequestBody User user) {
        Response response = new Response();
        User userObj = userRepository.getOne(new Long(id));
        try {
            userObj.setPassword(user.getPassword());
            userRepository.save(userObj);
            response.setStatus("ok");
            response.setMessage("Password Updated !");

        } catch (Exception e) {
            response.setStatus("fail");
            response.setMessage("Sorry Can't Update. Try Later !");
        }

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCO> profile(@RequestHeader(value = "userId") String id) {
        User user = userRepository.getOne(new Long(id));
        return new ResponseEntity<UserCO>(new UserCO().convertUserToCO(user), HttpStatus.OK);
    }

    byte[] defaultImage() {
        Path path = Paths.get("/home/saksham/advancedSpring/linksharing/blue/app/images/flat-avatar.png");
        byte[] output = new byte[0];
        try {
            output = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

}
