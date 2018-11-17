package dp.com.tadawy.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import dp.com.tadawy.pojo.model.ReservationContent;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.activity.DetailItemApproveRequestActivity;
import dp.com.tadawy.view.activity.PendRequestDetailActivity;

public class NotificationItemViewModel {

    private Context context;
    private ReservationContent reservationContent;
    String dateTime;
    public ObservableField<String>date;
    public ObservableField<String>time;
    String status;

    public NotificationItemViewModel(Context context, ReservationContent reservationContent,String status) {
        this.context = context;
        this.reservationContent = reservationContent;
        this.status=status;
        date=new ObservableField<>();
        time=new ObservableField<>();
        dateTime=reservationContent.getDate();
        String[] separated = dateTime.split(" ");
        date.set(separated[0]);
        int x=Integer.parseInt(separated[1].substring(0,1));
        if(x>12){
            time.set(String.valueOf(x-12)+separated[1].substring(2)+"مساءاً");
        }
        else {
            time.set(separated[1]+"صباحاً");
        }
    }

    public void setReservationContent(ReservationContent reservationContent) {
        this.reservationContent = reservationContent;
    }

    public String getLogo(){
        return reservationContent.getCompany().getLogo();
    }

    public String getName(){
        return reservationContent.getCompany().getName();
    }

    public String getAddress(){
        return reservationContent.getCompany().getAddress();
    }

    public String getPhone(){
        return reservationContent.getCompany().getPhone();
    }

    public void itemClick(View view){
        if(status.equals(ConfigurationFile.Constants.ACCEPT)){
            Intent intent=new Intent(context,DetailItemApproveRequestActivity.class);
            intent.putExtra(ConfigurationFile.IntentConstants.REQUEST_ITEM_DATA,reservationContent);
            context.startActivity(intent);
        }else if(status.equals(ConfigurationFile.Constants.PEND)){
            Intent intent=new Intent(context,PendRequestDetailActivity.class);
            intent.putExtra(ConfigurationFile.IntentConstants.REQUEST_ITEM_DATA,reservationContent);
            context.startActivity(intent);
        }else {
            //Toast.makeText(context,"normal client",Toast.LENGTH_LONG).show();
        }
    }
}
