package model;

public class Article {
    private int id;
    private int UserId;
    private String text;
    private int rating;

    Article(int id, int userId, String text, int rating){
        this.id = id;
        this.UserId = userId;
        this.text = text;
        this.rating = rating;
    }
    public Article(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
