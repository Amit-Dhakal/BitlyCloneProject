package com.example.BitlyCloneApplication.DTO;

import lombok.Data;

@Data
public class UrlMapData {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}


//key and value
//set the key and value from the form
//