package dp.com.tadawy.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.view.View;

public class CompanyRegisterCounterViewModel {
    public ObservableInt current;
    public ObservableList<Integer>img;
    public ObservableInt img1Visibility;
    public ObservableInt img2Visibility;
    public ObservableInt img3Visibility;
    public ObservableInt img4Visibility;
    public ObservableInt img5Visibility;
    public ObservableInt img6Visibility;
    public ObservableInt img7Visibility;
    public ObservableInt img8Visibility;

    public CompanyRegisterCounterViewModel(int current) {
        initVariables();
        this.current.set(current);
        setImg();
    }

    public void initVariables(){
        img=new ObservableArrayList<>();
        current=new ObservableInt();
    }
    public void setImg(){
        for(int i=0;i<current.get()-1;i++){
            img.add(View.VISIBLE);
        }
        for(int i=current.get();i<=8;i++){
            img.add(View.GONE);
        }
    }



}
