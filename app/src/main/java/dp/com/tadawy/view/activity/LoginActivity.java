package dp.com.tadawy.view.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.acl.Group;

import dp.com.tadawy.R;
import dp.com.tadawy.view.fragment.LoginFragment;
import dp.com.tadawy.view.fragment.SignupFragment;

public class LoginActivity extends AppCompatActivity {
    Button login;
    Button signup;
    Fragment loginFragment=new LoginFragment();
    Fragment signupFragment=new SignupFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.bt_login);
        signup=findViewById(R.id.bt_signup);
        loginOrSignup(login);
    }
    public void loginOrSignup(View view){
        switch (view.getId()){
            case R.id.bt_login:{
                login.setTextColor(Color.WHITE);
                signup.setTextColor(getResources().getColor(R.color.text_color_unselected));
                login.setSelected(true);
                signup.setSelected(false);
                navigationFragments(loginFragment);
                break;
            }
            case R.id.bt_signup:{
                signup.setTextColor(Color.WHITE);
                login.setTextColor(getResources().getColor(R.color.text_color_unselected));
                signup.setSelected(true);
                login.setSelected(false);
                navigationFragments(signupFragment);
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
}
