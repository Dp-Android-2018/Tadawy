package dp.com.tadawy.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ListItemStringBinding;
import dp.com.tadawy.view.holder.ListStringViewHolder;
import io.reactivex.internal.operators.observable.ObservableToList;

public class ListCompaniesAdapter extends RecyclerView.Adapter<ListStringViewHolder> {

    ObservableList<String> names;

    public ListCompaniesAdapter(ObservableList<String> names) {
        this.names = names;
    }

    @NonNull
    @Override
    public ListStringViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ListItemStringBinding binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.list_item_string,viewGroup,false);
        return new ListStringViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListStringViewHolder listStringViewHolder, int i) {
        listStringViewHolder.bindItem(names.get(i));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}
