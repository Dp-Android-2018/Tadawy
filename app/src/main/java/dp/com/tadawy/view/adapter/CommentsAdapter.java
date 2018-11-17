package dp.com.tadawy.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import dp.com.tadawy.R;
import dp.com.tadawy.databinding.CommentItemBinding;
import dp.com.tadawy.pojo.model.CommentItem;
import dp.com.tadawy.view.holder.CommentViewHolder;

public class CommentsAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private ObservableList<CommentItem> comments;

    public CommentsAdapter(ObservableList<CommentItem> comments) {
        this.comments=comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommentItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_item,parent,false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bindItemComment(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
