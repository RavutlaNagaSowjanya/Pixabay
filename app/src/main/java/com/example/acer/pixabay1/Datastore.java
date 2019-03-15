package com.example.acer.pixabay1;

class Datastore {
    String image,likes,views,comments,tags;

    public Datastore(String image, String likes, String views, String comments, String tags) {
        this.image = image;
        this.likes = likes;
        this.views = views;
        this.comments = comments;
        this.tags = tags;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
