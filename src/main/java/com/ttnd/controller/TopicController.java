package com.ttnd.controller;

import com.ttnd.Utils.enums.Visibility;
import com.ttnd.domain.*;
import com.ttnd.pojo.CO.TopicCO;
import com.ttnd.repository.ResourceRepository;
import com.ttnd.repository.TopicRepository;
import com.ttnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    TopicRepository topicRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Topic>> list(@RequestHeader("userId") Long id) {
        User user = userRepository.getOne(id);
        return new ResponseEntity<List<Topic>>(user.getTopics(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Topic>> create(@RequestHeader("userId") Long id, @RequestBody TopicCO topicCO) {
        User user = userRepository.getOne(id);
        Topic topic = new Topic(topicCO.getName(), user, Visibility.valueOf(topicCO.getVisibility()), new Date(), new Date());
        topicRepository.save(topic);
        user.getTopics().add(topic);
        userRepository.save(user);
        return new ResponseEntity<List<Topic>>(user.getTopics(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Topic>> delete(@RequestHeader("userId") Long id, @RequestBody TopicCO topicCO) {
        Topic topic=topicRepository.getOne(topicCO.getId());
        User user = userRepository.getOne(id);
        user.getTopics().remove(topic);
        topicRepository.delete(topic);
        return new ResponseEntity<List<Topic>>(user.getTopics(), HttpStatus.OK);
    }

    @RequestMapping(value = "/namelist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> nameList() {
        List<String> topicNames=new ArrayList<>();
        List<Topic> topics=topicRepository.findAll();
        for(Topic topic:topics){
            topicNames.add(topic.getName());
        }
        return new ResponseEntity<List<String>>(topicNames, HttpStatus.OK);
    }

}
