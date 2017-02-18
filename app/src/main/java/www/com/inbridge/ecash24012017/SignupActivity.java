package www.com.inbridge.ecash24012017;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Intent i = getIntent();

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

    public  void onClickNextbutton(View v) {
        EditText mobilenumberEditText = (EditText) findViewById(R.id.mobilenumber_edittext);
        EditText emailidEditText = (EditText) findViewById(R.id.emailaddress_edittext);

        final String mobilenumber = mobilenumberEditText.getText().toString();
        final String emailid = emailidEditText.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Adding values to editor
        editor.putString(Config.MOBILENUMBER_SHARED_PREF, mobilenumber);
        //Saving values to editor
        editor.commit();

        final String mobilenumber1 = sharedPreferences.getString(Config.MOBILENUMBER_SHARED_PREF, "mobilenumber");

        if (mobilenumber.trim().length() != 10) {
            mobilenumberEditText.setError("Enter Correct Mobile Number");
        }
        else  if (!emailValidator(emailid)) {
            emailidEditText.setError("Enter Correct E-mail Id");
        }
         else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.OTP_URL,
                    new Response.Listener<String>() {

                        String otp = null;


                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(SignupActivity.this, response, Toast.LENGTH_LONG).show();
                            //If we are getting success from server
                            String user_msg=null;

                            try {
                                JSONObject jsonObject=new JSONObject(response);

                            }
                            catch (JSONException e)
                            {
                                Toast.makeText(getApplicationContext(),"Developer Exception While Json Object",Toast.LENGTH_LONG).show();
                            }
                            if (response.equalsIgnoreCase(Config.LOGIN_FAILURE1)) {
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toast.makeText(SignupActivity.this, Config.LOGIN_FAILURE1 + mobilenumber1, Toast.LENGTH_SHORT).show();
                            } else if (response.equalsIgnoreCase(Config.LOGIN_FAILURE2)) {
                                Toast.makeText(SignupActivity.this, "Check Your Network Connection", Toast.LENGTH_SHORT).show();
                            } else if (response.equalsIgnoreCase(Config.LOGIN_FAILURE3)) {
                                Toast.makeText(SignupActivity.this, Config.LOGIN_FAILURE3, Toast.LENGTH_SHORT).show();
                            } else if (response.equalsIgnoreCase(Config.LOGIN_FAILURE4)) {
                                Toast.makeText(SignupActivity.this, Config.LOGIN_FAILURE4, Toast.LENGTH_SHORT).show();
                            } else {
                                //json parsing
                                try {

                                    JSONObject root = new JSONObject(response);
                                    // Toast.makeText(SignupActivity.this,"entered try",Toast.LENGTH_SHORT).show();
                                    if (root.has("OTP")) {
                                        otp = root.getString("OTP");

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }

                                SharedPreferences sharedPreferences = SignupActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                //Adding values to editor
                                editor.putString(Config.MOBILENUMBER_SHARED_PREF, mobilenumber);
                                editor.putString(Config.EMAILID_SHARED_PREF, emailid);

                                editor.putString(Config.OTP_KEY, otp);


                                //Saving values to editor
                                editor.commit();

                                Intent i = new Intent(SignupActivity.this, OTPActivity.class);
                                i.putExtra("mobilenumber", mobilenumber);
                                i.putExtra("emailid", emailid);
                                i.putExtra("otp", otp);

                                startActivity(i);
                                finish();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put(Config.KEY_MOBILENUMBER, mobilenumber);

                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}