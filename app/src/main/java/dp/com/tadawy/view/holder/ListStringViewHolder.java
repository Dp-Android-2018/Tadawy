package dp.com.tadawy.view.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import dp.com.tadawy.databinding.ListItemStringBinding;
import dp.com.tadawy.viewmodel.ListItemStringViewModel;

public class ListStringViewHolder extends RecyclerView.ViewHolder {
    ListItemStringBinding binding;

    public ListStringViewHolder(ListItemStringBinding binding) {
        super(binding.clRoot);
        this.binding=binding;
    }

    public void bindItem(String companyName){
        if (binding.getViewModel()==null){
            binding.setViewModel(new ListItemStringViewModel(companyName,itemView.getContext()));
        }else
            binding.getViewModel().setCompanyName(companyName);
    }
}
