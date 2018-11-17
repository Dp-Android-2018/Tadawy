package dp.com.tadawy.viewmodel;

import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

import dp.com.tadawy.R;
import dp.com.tadawy.pojo.model.FullDay;
import dp.com.tadawy.pojo.model.Shift;
import io.reactivex.internal.operators.observable.ObservableInternalHelper;

public class WorkDayViewModel {
    public ObservableField<String>mFrom;
    public ObservableField<String>mTo;
    public ObservableField<String>nFrom;
    public ObservableField<String>nTo;
    private Context context;
    Shift morning;
    Shift night;

    public WorkDayViewModel(Context context) {
        this.context=context;
        initVariables();
    }
    public void initVariables(){
        mFrom=new ObservableField<>("00:00");
        mTo=new ObservableField<>("00:00");
        nFrom=new ObservableField<>("00:00");
        nTo=new ObservableField<>("00:00");
        morning=new Shift();
        night=new Shift();
    }

    public void pickTime(View view){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, (timePicker, selectedHour, selectedMinute) -> {
            switch (view.getId()){
                case R.id.tv_mfrom:{
                    mFrom.set(selectedHour + ":" + selectedMinute);
                    morning.setFrom(mFrom.get());
                    break;
                }
                case R.id.tv_mto:{
                    mTo.set(selectedHour + ":" + selectedMinute);
                    morning.setTo(mTo.get());
                    break;
                }
                case R.id.tv_nfrom:{
                    nFrom.set(selectedHour + ":" + selectedMinute);
                    night.setFrom(nFrom.get());
                    break;
                }
                case R.id.tv_nto:{
                    nTo.set(selectedHour + ":" + selectedMinute);
                    night.setTo(nTo.get());
                    break;
                }
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public Shift getMorning() {
        return morning;
    }

    public Shift getNight() {
        return night;
    }
}
