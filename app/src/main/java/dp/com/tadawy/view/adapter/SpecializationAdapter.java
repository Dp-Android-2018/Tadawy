package dp.com.tadawy.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.SpecializationListItemBinding;
import dp.com.tadawy.pojo.model.Specialization;
import dp.com.tadawy.view.callback.SpecializationCallback;
import dp.com.tadawy.view.holder.SpecializationViewHolder;

public class SpecializationAdapter extends RecyclerView.Adapter<SpecializationViewHolder> {
    private List<Specialization> specializationList;
    private SpecializationCallback specializationCallback;

    public SpecializationAdapter(List<Specialization> specializationList, SpecializationCallback specializationCallback) {
        this.specializationList = specializationList;
        this.specializationCallback = specializationCallback;
        System.out.println("Specialization size adapter :"+specializationList.size());
    }

    @NonNull
    @Override
    public SpecializationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SpecializationListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.specialization_list_item,parent,false);
        return new SpecializationViewHolder(binding,specializationCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecializationViewHolder holder, int position) {
        holder.bindItemSpecialization(specializationList.get(position));

    }

    @Override
    public int getItemCount() {
        return specializationList.size();
    }
}
