package dp.com.tadawy.viewmodel;

import android.content.Context;

import dp.com.tadawy.pojo.model.CommentItem;

public class CommentItemViewModel {
    private CommentItem commentItem;
    Context context;
    public CommentItemViewModel(CommentItem commentItem,Context context) {
        this.commentItem = commentItem;
        this.context=context;
    }

    public String getLogo(){
        return commentItem.getClient().getLogo();
    }
    public String getName(){
        return commentItem.getClient().getName();
    }
    public String getComment(){
        return commentItem.getComment();
    }
    public Float getRate(){
        return commentItem.getRating();
    }

    public void setCommentItem(CommentItem commentItem) {
        this.commentItem = commentItem;
    }
}
