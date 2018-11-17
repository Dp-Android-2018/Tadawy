package dp.com.tadawy.view.holder;

import android.support.v7.widget.RecyclerView;

import dp.com.tadawy.databinding.CountryListItemBinding;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.view.callback.CountryCallback;
import dp.com.tadawy.viewmodel.ItemCountryViewModel;

public class CountriesViewHolder extends RecyclerView.ViewHolder {


    private CountryListItemBinding binding;
    private CountryCallback countryCallback;
    public CountriesViewHolder(CountryListItemBinding binding, CountryCallback baseInterface) {
        super(binding.rlParent);
        this.binding=binding;
        this.countryCallback =baseInterface;
    }

    public void bindItemCountry(Country country){
        if(binding.getItemCountryViewModel()==null)
            binding.setItemCountryViewModel(new ItemCountryViewModel(country,(itemView.getContext()), countryCallback));
        else
            binding.getItemCountryViewModel().setCountryItem(country);
    }


}
