package dp.com.tadawy.view.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import dp.com.tadawy.databinding.AcceptRequestItemBinding;
import dp.com.tadawy.databinding.NotificationItemBinding;
import dp.com.tadawy.databinding.PendRequestItemBinding;
import dp.com.tadawy.pojo.model.ReservationContent;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.viewmodel.NotificationItemViewModel;

public class ReservationViewHolder extends RecyclerView.ViewHolder {
    private NotificationItemBinding notificationItemBinding;
    private PendRequestItemBinding pendRequestItemBinding;
    private AcceptRequestItemBinding acceptRequestItemBinding;

    String status;
    public ReservationViewHolder(NotificationItemBinding binding,String status) {
        super(binding.cvRoot);
        this.notificationItemBinding=binding;
        this.status=status;
    }
    public ReservationViewHolder(PendRequestItemBinding binding, String status) {
        super(binding.cvRoot);
        this.pendRequestItemBinding=binding;
        this.status=status;
    }
    public ReservationViewHolder(AcceptRequestItemBinding binding, String status) {
        super(binding.cvRoot);
        this.acceptRequestItemBinding=binding;
        this.status=status;
    }



    public void bindItemComment(ReservationContent reservation){
        //if (comment!=null && advert.getTitle()!=null && advert.getContent()!=null)
        //System.out.println("Responses Size  companyInfo :"+companyInfo.getName()+" "+companyInfo.getCity().getName());
        if(status.equals(ConfigurationFile.Constants.ACCEPT)){
            if(acceptRequestItemBinding.getViewModel()==null)
                acceptRequestItemBinding.setViewModel(new NotificationItemViewModel(itemView.getContext(),reservation,status));
            else
                acceptRequestItemBinding.getViewModel().setReservationContent(reservation);
        }else if(status.equals(ConfigurationFile.Constants.PEND))
        {
            if(pendRequestItemBinding.getViewModel()==null)
                pendRequestItemBinding.setViewModel(new NotificationItemViewModel(itemView.getContext(),reservation,status));
            else
                pendRequestItemBinding.getViewModel().setReservationContent(reservation);
        }else {
            if (notificationItemBinding.getViewModel() == null)
                notificationItemBinding.setViewModel(new NotificationItemViewModel(itemView.getContext(), reservation, status));
            else
                notificationItemBinding.getViewModel().setReservationContent(reservation);
        }

    }
}
