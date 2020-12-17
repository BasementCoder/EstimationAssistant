package com.garagonic.estimationAssistant.old.database.course;

import com.garagonic.estimationAssistant.old.database.Topic.Topic;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
public class Course {
//    @Id
//    @Size(max = 10)
    private int id;
    private String sname;
    private String description;
    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;


    public Course() {


    }

    public Course(int id, String name, String description, Integer topicId) {
        super();
        this.id = id;
        this.sname = name;
        this.description = description;
        this.topic = new Topic(topicId, "", "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsName() {
        return sname;
    }

    public void setsName(String sName) {
        this.sname = sName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }


}
