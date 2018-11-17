package dp.com.tadawy.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.RadioButtonCountryBinding;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.view.holder.CountryRadioViewHolder;

public class CountryRadioAdapter extends RecyclerView.Adapter<CountryRadioViewHolder> {

    ObservableList<Country> countries;

    public CountryRadioAdapter(ObservableList<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryRadioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RadioButtonCountryBinding binding=DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.radio_button_country,viewGroup,false);
        return new CountryRadioViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryRadioViewHolder holder, int i) {
        holder.bindItemCountry(countries.get(i));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
