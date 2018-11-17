package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;

import dp.com.tadawy.BR;
import dp.com.tadawy.R;
import dp.com.tadawy.pojo.request.CompanyRegisterRequest;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.view.activity.CompanyRegisterStep8Activity;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.view.callback.TaskMonitor;

public class CompanyRegisterStep7ViewModel extends BaseObservable implements TaskMonitor {

    Context context;
    BaseInterface callBack;
    private CompanyRegisterRequest companyRegisterRequest;
    private Bitmap picBitmap1;
    private Bitmap licience;
    private Bitmap selectedImageBitmap;
    Uri selectedImageUri = null;
    StorageReference storageReference;
    boolean flag=false;
    public ObservableList<String>images;

    public CompanyRegisterStep7ViewModel(Context context, BaseInterface callBack) {
        this.context = context;
        this.callBack = callBack;
        companyRegisterRequest=(CompanyRegisterRequest)((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST);
        storageReference= FirebaseStorage.getInstance().getReference();
        images=new ObservableArrayList<>();
    }

    public void next(View view){
        if(picBitmap1==null){
            callBack.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }
        if(images.size()>0){
            companyRegisterRequest.setImages(images);
        }
        Intent intent=new Intent(context,CompanyRegisterStep8Activity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST,companyRegisterRequest);
        context.startActivity(intent);
    }

    public void back(View view){
        ((Activity)context).finish();
    }

    @Bindable
    public Bitmap getPicBitmap() {
            return picBitmap1;
    }
    @Bindable
    public Bitmap getLisiense(){
        return licience;
    }

    public void uploadImage(View view){
        if(view.getId()==R.id.circleImageView2){
            flag=true;
        }else {
            flag=false;
        }
        callBack.updateUi(ConfigurationFile.Constants.GETIMAGE);
    }
    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        if(data!=null){
            if (requestCode == ConfigurationFile.Constants.PICK_IMAGE_REQUEST) {
                selectedImageUri = data.getData();
                CustomUtils.getInstance().showProgressDialog(context);
                CustomUtils.getInstance().uploadFireBasePic(storageReference, selectedImageUri,this);
                convertImageToBase64(data.getData());
            }
        }
    }
    public void convertImageToBase64(Uri ImageUri) {
        try {
            final InputStream imageStream = context.getContentResolver().openInputStream(ImageUri);
            selectedImageBitmap = BitmapFactory.decodeStream(imageStream);
            String RealPicturePath = CustomUtils.getInstance().uriToFilename(ImageUri,context);
            selectedImageBitmap = CustomUtils.getInstance().modifyOrientation(selectedImageBitmap, RealPicturePath);
            setPicBitmap(selectedImageBitmap);
            // encodedImage = CustomUtils.getInstance().encodeImage(selectedImageBitmap);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void taskCompleted(String photoUrl) {
        System.out.println("image url : "+photoUrl);
        if(flag){
            companyRegisterRequest.setLicenseImage(photoUrl);
        }else {
            images.add(photoUrl);
        }

        CustomUtils.getInstance().cancelDialog();
    }

    public void setPicBitmap(Bitmap picBitmap) {

        System.out.println("FLAG is : "+flag);
        if(flag){
            this.licience =picBitmap;
            notifyPropertyChanged(BR.lisiense);

        }else {
            this.picBitmap1 = picBitmap;
            notifyPropertyChanged(BR.picBitmap);
        }
    }

}
