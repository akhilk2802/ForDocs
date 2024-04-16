package com.csye6220.finalProject.dto;

public class PostDto {

    private String postName;
    private String url;
    private String description;

    public PostDto(String postName, String url, String description) {
        this.postName = postName;
        this.url = url;
        this.description = description;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
