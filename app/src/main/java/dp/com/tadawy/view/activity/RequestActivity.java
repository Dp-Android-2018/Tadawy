package dp.com.tadawy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import dp.com.tadawy.R;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.view.fragment.AcceptRequestFragment;
import dp.com.tadawy.view.fragment.PendRequestFragment;

public class RequestActivity extends BaseActivity {
    private Button myRequest;
    private Button sentRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        myRequest=findViewById(R.id.bt_my_request);
        sentRequest=findViewById(R.id.bt_sent_request);
        selectRequest(myRequest);
        Toolbar myToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out:{
                logOut();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void selectRequest(View view){
        switch (view.getId()){
            case R.id.bt_my_request:{
                myRequest.setTextColor(Color.WHITE);
                sentRequest.setTextColor(Color.BLACK);
                myRequest.setPressed(true);
                sentRequest.setPressed(false);
                PendRequestFragment pendRequestFragment=new PendRequestFragment();
                navigationFragments(pendRequestFragment);
             break;
            }
            case R.id.bt_sent_request:{
                myRequest.setTextColor(Color.BLACK);
                sentRequest.setTextColor(Color.WHITE);
                myRequest.setPressed(false);
                sentRequest.setPressed(true);
                AcceptRequestFragment fragment=new AcceptRequestFragment();
                navigationFragments(fragment);
                break;
            }
        }


    }

    public void navigationFragments(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

    public void logOut() {

        CustomUtils.getInstance().clearSharedPref(this);
        Intent intent=new Intent(this,LoginActivity.class);
        this.startActivity(intent);
        finishAffinity();
    }
}
