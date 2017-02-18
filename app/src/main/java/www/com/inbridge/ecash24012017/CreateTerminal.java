package www.com.inbridge.ecash24012017;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTerminal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_terminal);
        Intent i=getIntent();
    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void onClickSubmitButton(View v) {

        EditText cashiernameEdittext = (EditText) findViewById(R.id.cashiername_edittext);
        EditText mobilenumberEdittext = (EditText) findViewById(R.id.mobilenumber_edittext);
        EditText emailidEdittext = (EditText) findViewById(R.id.emailid_edittext);

        if (cashiernameEdittext.getText().toString().trim().equals("")) {
            cashiernameEdittext.setError("Cashier Name Is Required");
            cashiernameEdittext.setHint("Please Enter The Cashier Name");
        } else if (cashiernameEdittext.getText().toString().trim().equals("")) {
            mobilenumberEdittext.setError("Mobile Number is Required");
            mobilenumberEdittext.setHint("Please Enter The Terminal Id");
        } else if (emailidEdittext.getText().toString().trim().equals("")) {
            emailidEdittext.setError("Email Is Required");
            emailidEdittext.setText("Please Enter the E-mail Id");
        }
        if (mobilenumberEdittext.getText().toString().trim().length() != 10) {
            mobilenumberEdittext.setError("Enter Correct Mobile Number");
        } else if (!emailValidator(emailidEdittext.getText().toString().trim())) {
            emailidEdittext.setError("Enter Correct E-mail Id");
        } else {


                SharedPreferences sharedPreferences = CreateTerminal.this.getSharedPreferences(Merchantconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Merchantconfig.KEY_CASHIERNAME1, cashiernameEdittext.getText().toString().trim());
                editor.putString(Merchantconfig.KEY_CASHIERMOBILE1, mobilenumberEdittext.getText().toString().trim());
                editor.putString(Merchantconfig.KEY_CASHIEREMAIL1, emailidEdittext.getText().toString().trim());
                editor.commit();
                onBackPressed();
                Toast.makeText(CreateTerminal.this, sharedPreferences.getString(Merchantconfig.KEY_CASHIERNAME1, "cashier1"), Toast.LENGTH_SHORT).show();

            }


    }
    public void onClickCancelButton(View v)
    {
        onBackPressed();
    }

}


