package com.ttnd.controller;

import com.ttnd.domain.*;
import com.ttnd.pojo.CO.ResourceCO;
import com.ttnd.pojo.VO.ResourceVO;
import com.ttnd.repository.ResourceRepository;
import com.ttnd.repository.TopicRepository;
import com.ttnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public String resource() {
        User dummyUser = userRepository.getOne(1L);
        Topic topic = topicRepository.getOne(1L);
        Resource resource1 = new Resource("Document Resoource1", "https://www.google.co.in",null, dummyUser, topic, new Date(), new Date());
        Resource resource2 = new Resource("Document Resoource2",null, "path", dummyUser, topic, new Date(), new Date());
        resourceRepository.save(resource1);
        resourceRepository.save(resource2);
        dummyUser.getResources().add(resource1);
        dummyUser.getResources().add(resource2);
        topic.getResources().add(resource1);
        topic.getResources().add(resource2);
        userRepository.save(dummyUser);
        return "Success";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResourceVO>> list(@RequestHeader("userId") Long id) {
        User user = userRepository.getOne(id);
        return new ResponseEntity<List<ResourceVO>>(ResourceVO.convertResource(user.getResources()), HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResourceVO>> edit(@RequestHeader("userId") Long id, @RequestBody ResourceCO resourceCO) {
        User user = userRepository.getOne(id);
        Resource resource=resourceRepository.getOne(resourceCO.getId());
        resource.setDescription(resourceCO.getDescription());
        resourceRepository.save(resource);
        return new ResponseEntity<List<ResourceVO>>(ResourceVO.convertResource(user.getResources()), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResourceVO>> save(@RequestHeader("userId") Long id, @RequestBody ResourceCO resourceCO) {
        User user = userRepository.getOne(id);

        return new ResponseEntity<List<ResourceVO>>(ResourceVO.convertResource(user.getResources()), HttpStatus.OK);
    }
}
