package dp.com.tadawy.view.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import dp.com.tadawy.databinding.GridItemLayoutBinding;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.viewmodel.ClinicListItemViewModel;

public class SearchGridViewHolder extends RecyclerView.ViewHolder {

    GridItemLayoutBinding binding;
    public SearchGridViewHolder(GridItemLayoutBinding binding) {
        super(binding.cvRoot);
        this.binding=binding;
    }

    public void bindItemCompany(LoginResponseContent data){
        if(binding.getViewModel()==null)
            binding.setViewModel(new ClinicListItemViewModel(data,itemView.getContext()));
        else
            binding.getViewModel().setData(data);
    }
}
