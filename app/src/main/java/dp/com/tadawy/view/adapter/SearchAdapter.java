package dp.com.tadawy.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ClinicListItemBinding;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.view.holder.SearchViewHolder;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private ObservableList<LoginResponseContent> response;

    public SearchAdapter(ObservableList<LoginResponseContent> response) {
        this.response = response;
        //System.out.println("Responses Size Adapter ::"+response.size());
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ClinicListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.clinic_list_item,parent,false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        if (response.get(position).getName()!=null)
        //System.out.println("Responses Size  Binding :"+response.get(position).getName());
        holder.bindItemCompany(response.get(position));
    }

    @Override
    public int getItemCount() {
       // System.out.println("Responses Size Adapter 2");
        return response.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
