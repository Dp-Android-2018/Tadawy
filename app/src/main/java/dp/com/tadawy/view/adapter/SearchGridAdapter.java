package dp.com.tadawy.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.GridItemLayoutBinding;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.view.holder.SearchGridViewHolder;

public class SearchGridAdapter extends RecyclerView.Adapter<SearchGridViewHolder> {

    private ObservableList<LoginResponseContent> response;

    public SearchGridAdapter(ObservableList<LoginResponseContent> response) {
        this.response = response;
    }

    @NonNull
    @Override
    public SearchGridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        GridItemLayoutBinding binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.grid_item_layout,viewGroup,false);
        return new SearchGridViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchGridViewHolder searchGridViewHolder, int i) {
        searchGridViewHolder.bindItemCompany(response.get(i));

    }

    @Override
    public int getItemCount() {
        return response.size();
    }
}
