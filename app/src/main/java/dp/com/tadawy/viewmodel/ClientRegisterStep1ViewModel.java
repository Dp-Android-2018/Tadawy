package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import dp.com.tadawy.BR;
import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.request.CheckMailRequest;
import dp.com.tadawy.pojo.request.ClientRegisterRequest;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.utils.ValidationUtils;
import dp.com.tadawy.view.activity.ClientRegisterStep2Activity;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.view.callback.TaskMonitor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ClientRegisterStep1ViewModel extends BaseObservable implements TaskMonitor {
    public ObservableField<String> name;
    public ObservableField<String> mail;
    public ObservableField<String> password;
    public ObservableField<String> passwordConfirmation;
    private Context context;
    private Bitmap picBitmap;
    private Bitmap selectedImageBitmap;
    private BaseInterface callBack;
    private CheckMailRequest mailRequest;
    private ClientRegisterRequest clientRegisterRequest;
    Uri selectedImageUri = null;
    StorageReference storageReference;
    public ClientRegisterStep1ViewModel(Context context,BaseInterface callBack) {
        this.context = context;
        this.callBack=callBack;
        initVariables();
    }

    public void initVariables(){
        name=new ObservableField<>();
        mail=new ObservableField<>();
        password=new ObservableField<>();
        passwordConfirmation=new ObservableField<>();
        clientRegisterRequest=new ClientRegisterRequest();
        storageReference= FirebaseStorage.getInstance().getReference();
    }
    public void checkData(View view) {
        if (ValidationUtils.isEmpty(name.get()) ||
                ValidationUtils.isEmpty(mail.get()) ||
                ValidationUtils.isEmpty(password.get()) ||
                ValidationUtils.isEmpty(passwordConfirmation.get())||
                picBitmap==null) {
            callBack.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
            }else{
            if(!ValidationUtils.isMail(mail.get())){
                callBack.updateUi(ConfigurationFile.Constants.INVALED_EMAIL);
            }else{
                if(password.get().length()<8){
                    callBack.updateUi(ConfigurationFile.Constants.PASSWORD_LENGTH_ERROR);
                }else {
                    if(!password.get().equals(passwordConfirmation.get())){
                        callBack.updateUi(ConfigurationFile.Constants.PASSWORD_CONFIRMATION_ERROR);
                    }else {
                        mailRequest=new CheckMailRequest(mail.get());
                        checkExistMail();
                    }
                }
            }
        }

    }



    public void checkExistMail(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            System.out.println("Register Success Mail :"+mail.get());
            ApiClient.getClient().create(EndPoints.class).checkMail(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,mailRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(integerResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Register Success Mail CODE:" + integerResponse.code());
                        System.out.println("Response : "+integerResponse.message());
                        if (integerResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                            if (integerResponse.body() == 0) {
                                System.out.println("Register Success Mail");
                                clientRegisterRequest.setName(name.get());
                                clientRegisterRequest.setEmail(mail.get());
                                clientRegisterRequest.setPassword(password.get());
                                clientRegisterRequest.setPasswordConfirmation(passwordConfirmation.get());
                                System.out.println("Request1 is : "+clientRegisterRequest.getLogo());
                                System.out.println("Request1 is : "+clientRegisterRequest.getName());
                                System.out.println("Request1 is : "+clientRegisterRequest.getEmail());
                                System.out.println("Request1 is : "+clientRegisterRequest.getPassword());
                                System.out.println("Request1 is : "+clientRegisterRequest.getPasswordConfirmation());

                                Intent intent=new Intent(context, ClientRegisterStep2Activity.class);
                                intent.putExtra(ConfigurationFile.IntentConstants.CLIENT_REQUEST,clientRegisterRequest);
                                context.startActivity(intent);

                            } else {
                                callBack.updateUi(ConfigurationFile.Constants.EXISET_MAIL_CODE);
                            }
                        }
                        },throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("exiset mail Ex:"+throwable.getMessage());

                    });
        }else{
            callBack.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }
    @Bindable
    public Bitmap getPicBitmap() {
        return picBitmap;
    }

    public void uploadImage(View view){
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
        clientRegisterRequest.setLogo(photoUrl);
        CustomUtils.getInstance().cancelDialog();
    }

    public void setPicBitmap(Bitmap picBitmap) {
        this.picBitmap = picBitmap;
        notifyPropertyChanged(BR.picBitmap);
    }

    public void back(View view){
        ((Activity)context).finish();
    }
}
