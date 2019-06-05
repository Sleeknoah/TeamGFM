package com.thegloriousfountainministries.exp2.custom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My HP on 3/31/2017.
 */

public class BlogModel {
    @SerializedName("blog_title")
    private String blogTopics;
    @SerializedName("blog_link")
    private String blogLink;

    @SerializedName("blog_pic")
    private String blog_pic;







    public BlogModel() {

    }

    public BlogModel(String blogTopics, String blogLink, String blog_pic) {
        this.blogTopics = blogTopics;
        this.blogLink =blogLink;
        this.blog_pic = blog_pic;
    }

    public void setBlogTopics(String blogTopics) {
        this.blogTopics = blogTopics;
    }

    public void setBlogLink(String blogLink) {
        this.blogLink = blogLink;
    }

    public void setBlog_pic(String blog_pic) {
        this.blog_pic = blog_pic;
    }

    public String getBlogTopics() {
        return blogTopics;
    }

    public String getBlogLink() {
        return blogLink;
    }

    public String getBlog_pic() {
        return blog_pic;
    }


}
