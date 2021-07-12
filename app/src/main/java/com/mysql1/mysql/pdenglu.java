package com.mysql1.mysql;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.mysql1.MyApplication;
import com.mysql1.R;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.mysql1.insert.Fragment4;

public class pdenglu extends AppCompatActivity implements View.OnClickListener{

    //login
    public static final int REQUEST_SIGN_IN_LOGIN = 1002;
    private AccountAuthService mAuthService;
    private AccountAuthParams mAuthParam;
    private final String TAG = "pdenglu";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denglu);
        findViewById(R.id.hwid_signin).setOnClickListener(this);

    }
    private void signIn() {
        mAuthParam = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setIdToken()
                .setAccessToken()
                .createParams();
        mAuthService = AccountAuthManager.getService(pdenglu.this, mAuthParam);
        startActivityForResult(mAuthService.getSignInIntent(), REQUEST_SIGN_IN_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Process the sign-in authorization result and obtain an ID token from AuthHuaweiId.
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGN_IN_LOGIN) {
            Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
            if (authAccountTask.isSuccessful()) {
                // The sign-in is successful, and the user's HUAWEI ID information and ID token are obtained.
                AuthAccount authAccount = authAccountTask.getResult();
               Log.i(TAG, "idToken:" + authAccount.getIdToken());
                Log.i(TAG, "AccessToken:\n" + authAccount.getAccessToken());
                Log.i(TAG, "OpenId:\n" + authAccount.getOpenId().replace("M","*"));
                Log.i(TAG, "Email:\n" + authAccount.getEmail());
                Log.i(TAG, "UnionId:\n" + authAccount.getUnionId().replace("a","*").replace("Q","*"));
//                textView.setText("sign in successfully");
               Intent intent = new Intent(pdenglu.this,huiyuan.class);
                //启动
                startActivity(intent);
            } else {
                // The sign-in failed. No processing is required. Logs are recorded to facilitate fault locating.
//                Log.e(TAG, "sign in failed : " +((ApiException)authAccountTask.getException()).getStatusCode());
//               textView.setText("sign in failed");
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hwid_signin:
                signIn();
                break;
            default:
                break;
        }
    }


    public void reg(View view){

        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));

    }


    public void login(View view){

        EditText EditTextname = (EditText)findViewById(R.id.name);
        EditText EditTextpassword = (EditText)findViewById(R.id.password);
        String send=EditTextname.getText().toString();
        User user =new User();
        user.setName(EditTextname.getText().toString());
        MyApplication.getInstance().login(user);

       String vip="0";
        new Thread(){
            @Override
            public void run() {
                UserDao userDao = new UserDao();

                boolean aa = userDao.login(EditTextname.getText().toString(),EditTextpassword.getText().toString(),vip);
                int msg = 0;
                if(aa){
                    msg = 1;
                }

                hand1.sendEmptyMessage(msg);


            }
        }.start();


    }
    final Handler hand1 = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            if(msg.what == 1)
            {
                Toast.makeText(pdenglu.this,"登录成功",Toast.LENGTH_LONG).show();
                startActivity(new Intent(pdenglu.this,huiyuan.class));
            }
            else
            {
                Toast.makeText(pdenglu.this,"登录失败，密码错误，或已成为会员",Toast.LENGTH_LONG).show();
            }
        }
    };
}

