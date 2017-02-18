package www.com.inbridge.ecash24012017;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OTPActivity extends AppCompatActivity {

    Exam_Timer exam_Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Intent intent =getIntent();
        String mobilenumber = intent.getStringExtra("mobilenumber");
        String emailid = intent.getStringExtra("emailid");
        String otp=intent.getStringExtra("otp");
        exam_Timer = new Exam_Timer(120000, 1000);
        exam_Timer.start();




    }

    public void onClickNextotpbutton(View v) {
        EditText otpEditText = (EditText) findViewById(R.id.otp_edittext);
        String otp = otpEditText.getText().toString();
        Toast.makeText(OTPActivity.this, otp, Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String otp_pref = sharedPreferences.getString(Config.OTP_KEY, "otp");

        if (otp.equalsIgnoreCase(otp_pref)) {
            Intent i = new Intent(OTPActivity.this, MerchantRegistrationActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            Toast.makeText(OTPActivity.this, "wrong otp entered", Toast.LENGTH_LONG).show();
        }


    }


    public class Exam_Timer extends CountDownTimer {

        public Exam_Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {

            Button btn=(Button) findViewById(R.id.resendotp_button);
            btn.setVisibility(View.VISIBLE);

        }

    }



        public void onClickresendotpbutton(View v) {
            Intent i=new Intent(OTPActivity.this,SignupActivity.class);
             startActivity(i);

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(OTPActivity.this,LoginActivity.class);
        startActivity(i);
        finish();

    }
}

