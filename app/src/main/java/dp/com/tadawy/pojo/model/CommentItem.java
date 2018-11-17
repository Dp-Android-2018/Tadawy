package dp.com.tadawy.pojo.model;

import com.google.gson.annotations.SerializedName;

public class CommentItem {
    @SerializedName("id")
    private int id;

    @SerializedName("rating")
    private Float rating;

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @SerializedName("client")
    private Client client;

    @SerializedName("comment")
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
