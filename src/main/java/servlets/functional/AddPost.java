package servlets.functional;

public class AddPost {
    public static String text = "hello world!";

    public AddPost(String text) {
        this.text = text;
    }
    public AddPost(){

    }
    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        AddPost.text = text;
    }
}
