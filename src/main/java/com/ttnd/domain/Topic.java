package com.ttnd.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ttnd.Utils.enums.Visibility;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User createdBy;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Resource> resources = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    Visibility visibility;

    @Column(nullable = true)
    Date dateCreated;

    @Column(nullable = true)
    Date lastUpdated;

    public Topic() {}

    public Topic(String name, User createdBy, Visibility visibility, Date dateCreated, Date lastUpdated) {
        this.name = name;
        this.createdBy = createdBy;
        this.visibility = visibility;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

}
