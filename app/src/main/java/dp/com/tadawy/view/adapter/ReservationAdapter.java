package dp.com.tadawy.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.AcceptRequestItemBinding;
import dp.com.tadawy.databinding.NotificationItemBinding;
import dp.com.tadawy.databinding.PendRequestItemBinding;
import dp.com.tadawy.pojo.model.ReservationContent;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.holder.ReservationViewHolder;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder> {

    ObservableList<ReservationContent> reservations;
    String status;

    public ReservationAdapter(ObservableList<ReservationContent> reservations,String status) {
        this.reservations = reservations;
        this.status=status;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(status.equals(ConfigurationFile.Constants.PEND)){
            PendRequestItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.pend_request_item,viewGroup,false);
            return new ReservationViewHolder(binding,status);
        }else if(status.equals(ConfigurationFile.Constants.ACCEPT)){
            AcceptRequestItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.accept_request_item,viewGroup,false);
            return new ReservationViewHolder(binding,status);
        }else {
            NotificationItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.notification_item, viewGroup, false);
            return new ReservationViewHolder(binding,status);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int i) {
        holder.bindItemComment(reservations.get(i));

    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }
}
