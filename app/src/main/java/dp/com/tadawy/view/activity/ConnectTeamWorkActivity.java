package dp.com.tadawy.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dp.com.tadawy.R;

public class ConnectTeamWorkActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_team_work);
    }

    public void back(View view){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}
