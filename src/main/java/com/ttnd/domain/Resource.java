package com.ttnd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = true)
    String description;

    @Column(nullable = true)
    String url;

    @Column(nullable = true)
    String filepath;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    User createdBy;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "topic_id")
    Topic topic;

    @Column(nullable = false)
    Date dateCreated;

    @Column(nullable = false)
    Date lastUpdated;

    public Resource(){}

    public Resource(String description, String url, String filepath, User createdBy, Topic topic, Date dateCreated, Date lastUpdated) {
        this.description = description;
        this.url = url;
        this.filepath = filepath;
        this.createdBy = createdBy;
        this.topic = topic;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
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

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }}
