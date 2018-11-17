package dp.com.tadawy.pojo.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dp.com.tadawy.pojo.model.CommentItem;

public class CommentResponse {
    @SerializedName("data")
    private List<CommentItem>comments;

    @SerializedName("commentable")
    private boolean commentable;

    public boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }

    public List<CommentItem> getComments() {
        return comments;
    }

    public void setComments(List<CommentItem> comments) {
        this.comments = comments;
    }
}
