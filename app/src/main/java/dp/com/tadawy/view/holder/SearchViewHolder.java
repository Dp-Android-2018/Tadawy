package dp.com.tadawy.view.holder;

import android.support.v7.widget.RecyclerView;
import dp.com.tadawy.databinding.ClinicListItemBinding;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.viewmodel.ClinicListItemViewModel;

public class SearchViewHolder extends RecyclerView.ViewHolder{
    private ClinicListItemBinding binding;
    public SearchViewHolder(ClinicListItemBinding binding) {
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
