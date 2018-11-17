package dp.com.tadawy.view.holder;

import android.support.v7.widget.RecyclerView;

import dp.com.tadawy.databinding.CommentItemBinding;
import dp.com.tadawy.pojo.model.CommentItem;
import dp.com.tadawy.viewmodel.CommentItemViewModel;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private CommentItemBinding binding;
    public CommentViewHolder(CommentItemBinding binding) {
        super(binding.clRoot);
        this.binding=binding;
    }

    public void bindItemComment(CommentItem comment){
        //if (comment!=null && advert.getTitle()!=null && advert.getContent()!=null)
            //System.out.println("Responses Size  companyInfo :"+companyInfo.getName()+" "+companyInfo.getCity().getName());
            if(binding.getViewModel()==null)
                binding.setViewModel(new CommentItemViewModel(comment,itemView.getContext()));
            else
                binding.getViewModel().setCommentItem(comment);

    }
}
