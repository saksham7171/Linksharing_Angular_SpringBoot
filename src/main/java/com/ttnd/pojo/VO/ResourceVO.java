package com.ttnd.pojo.VO;

import com.ttnd.domain.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResourceVO {

    Long id;
    String description;
    String topic;
    String url;
    String filepath;
    Date dateCreated;
    Date lastUpdated;

    public ResourceVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public static ResourceVO convertResource(Resource resource) {
        ResourceVO resourceVO = new ResourceVO();
        resourceVO.setId(resource.getId());
        resourceVO.setTopic(resource.getTopic().getName());
        resourceVO.setUrl(resource.getUrl());
        resourceVO.setFilepath(resource.getFilepath());
        resourceVO.setDescription(resource.getDescription());
        resourceVO.setDateCreated(resource.getDateCreated());
        resourceVO.setLastUpdated(resource.getLastUpdated());
        return resourceVO;
    }

    public static List<ResourceVO> convertResource(List<Resource> resources) {
        List<ResourceVO> resourceVOs = new ArrayList<>();
        for (Resource resource : resources) {
            ResourceVO resourceVO = new ResourceVO();
            resourceVO.setId(resource.getId());
            resourceVO.setUrl(resource.getUrl());
            resourceVO.setFilepath(resource.getFilepath());
            resourceVO.setTopic(resource.getTopic().getName());
            resourceVO.setDescription(resource.getDescription());
            resourceVO.setDateCreated(resource.getDateCreated());
            resourceVO.setLastUpdated(resource.getLastUpdated());
            resourceVOs.add(resourceVO);
        }
        return resourceVOs;
    }

}