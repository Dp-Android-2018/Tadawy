package dp.com.tadawy.view.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import dp.com.tadawy.databinding.RadioButtonCountryBinding;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.viewmodel.ItemCountryViewModel;

public class CountryRadioViewHolder extends RecyclerView.ViewHolder {

    private RadioButtonCountryBinding binding;

    public CountryRadioViewHolder(RadioButtonCountryBinding binding) {
        super(binding.clRoot);
        this.binding=binding;
    }

    public void bindItemCountry(Country country){
        if(binding.getItemCountryViewModel()==null)
            binding.setItemCountryViewModel(new ItemCountryViewModel(country,(itemView.getContext()), null));
        else
            binding.getItemCountryViewModel().setCountryItem(country);
    }
}
