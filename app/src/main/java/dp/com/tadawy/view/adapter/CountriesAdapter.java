package dp.com.tadawy.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import dp.com.tadawy.R;
import dp.com.tadawy.databinding.CountryListItemBinding;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.view.callback.CountryCallback;
import dp.com.tadawy.view.holder.CountriesViewHolder;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesViewHolder> {
    ObservableList<Country> countries;
    CountryCallback countryCallback;
    public CountriesAdapter(ObservableList<Country> countries, CountryCallback baseInterface) {
        this.countries = countries;
        this.countryCallback=baseInterface;
        System.out.println("size of countrie");
    }
    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CountryListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.country_list_item,parent,false);
        return new CountriesViewHolder(binding,countryCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        holder.bindItemCountry(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
