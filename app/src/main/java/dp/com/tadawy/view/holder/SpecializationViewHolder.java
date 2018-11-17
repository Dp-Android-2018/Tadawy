package dp.com.tadawy.view.holder;

import android.support.v7.widget.RecyclerView;
import dp.com.tadawy.databinding.SpecializationListItemBinding;
import dp.com.tadawy.pojo.model.Specialization;
import dp.com.tadawy.view.callback.SpecializationCallback;
import dp.com.tadawy.viewmodel.ItemSpecializationViewModel;

public class SpecializationViewHolder extends RecyclerView.ViewHolder {

    SpecializationListItemBinding binding;
    SpecializationCallback specializationCallback;

    public SpecializationViewHolder(SpecializationListItemBinding binding, SpecializationCallback specializationCallback) {
        super(binding.rlParent);
        this.binding = binding;
        this.specializationCallback = specializationCallback;
    }

    public void bindItemSpecialization(Specialization specialization){
        if(binding.getItemSpecializationViewModel()==null)
            binding.setItemSpecializationViewModel(new ItemSpecializationViewModel(specialization,specializationCallback));
        else
            binding.getItemSpecializationViewModel().setSpecialization(specialization);
    }
}
