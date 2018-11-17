package dp.com.tadawy.view.holder;

import android.support.v7.widget.RecyclerView;
import dp.com.tadawy.databinding.CityListItemBinding;
import dp.com.tadawy.pojo.model.City;
import dp.com.tadawy.view.callback.CityCallback;
import dp.com.tadawy.viewmodel.ItemCityViewModel;

public class CityViewHolder extends RecyclerView.ViewHolder {

    private CityListItemBinding binding;
    private CityCallback cityCallback;

    public CityViewHolder(CityListItemBinding binding, CityCallback cityCallback) {
        super(binding.rlParent);
        this.binding = binding;
        this.cityCallback=cityCallback;
    }

    public void bindItemCity(City city){
        if(binding.getItemCityViewModel()==null)
            binding.setItemCityViewModel(new ItemCityViewModel(itemView.getContext(),city,cityCallback));
        else
            binding.getItemCityViewModel().setCity(city);
    }
}
