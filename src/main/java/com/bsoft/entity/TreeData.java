package com.bsoft.entity;

import java.util.List;
import java.util.Map;

/**
 *@ClassName TreeData
 *@Description TODO
 *@Author Vee
 *@Date 2020/1/8 10:51
 *@Version 1.0
 **/
public class TreeData {
    private String key;
    private String text;
    private Map<String,Object> properties;
    private List<TreeData> items;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public List<TreeData> getItems() {
        return items;
    }

    public void setItems(List<TreeData> items) {
        this.items = items;
    }
    public TreeData(){}
    public TreeData(String key, String text, Map<String, Object> properties,List<TreeData> items) {
        this.key = key;
        this.text = text;
        this.properties = properties;
        this.items = items;
    }
}
