package www.com.inbridge.ecash24012017;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView newtoecashTextView;
    private TextView forgotpasswordTextView;
    private ProgressBar progressBar;
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar=(ProgressBar)findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.INVISIBLE);


    }


    public void onClickLogin(View v) {


        usernameEditText = (EditText) findViewById(R.id.username_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        loginButton = (Button) findViewById(R.id.login_button);
        progressBar=(ProgressBar)findViewById(R.id.pbHeaderProgress);

        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();


        if (usernameEditText.getText().toString().trim().equals(""))
        {
            usernameEditText.setError("UserName is required!");

            usernameEditText.setHint("Please Enter UserName");
        }
        else if (passwordEditText.getText().toString().trim().equals(""))
        {
            passwordEditText.setError(" Password is required!");

            passwordEditText.setHint("Please Enter Password");

        }
        else if(usernameEditText.getText().toString().trim().length()!=10)
        {
            Toast.makeText(LoginActivity.this,"Please Enter The Correct Mobile Number",Toast.LENGTH_LONG).show();
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String str=null;
                            String str1=null;
                            String str10=null;
                            String str4=null;
                            String str5=null;
                            String str6=null;
                            String str7=null;
                            String str8=null;
                            String str9=null;
                            String str11=null;
                            String str12="false";


                            try {
                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                JSONObject jsonObject = new JSONObject(response);
                                str1=jsonObject.getString("user_msg");
                                str12 = jsonObject.getString("success");


                            }
                            catch(JSONException e)
                            {
                                e.printStackTrace();
                            }

                            if (str12.equalsIgnoreCase(Config.LOGIN_SUCCESS)){


                            try
                            {
                                JSONObject jsonObject1 = new JSONObject(response);
                                str11=jsonObject1.getString("user_role");
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"Developer Exception 1",Toast.LENGTH_LONG).show();
                            }

                            if(str11.equals("merchant")) {
                                try {
                                    JSONObject jsonObject1 = new JSONObject(response);

                                    str = jsonObject1.getString("m_id");
                                    str4 = jsonObject1.getString("m_key");
                                    str5 = jsonObject1.getString("m_acc_name");
                                    str6 = jsonObject1.getString("m_account_no");
                                    str7 = jsonObject1.getString("m_ifsc_code");
                                    str8 = jsonObject1.getString("m_address");
                                    str9 = jsonObject1.getString("kyc_details");
                                    str10 = jsonObject1.getString("m_name");


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Developer Exception at json parsing", Toast.LENGTH_LONG).show();
                                }

                                //If we are getting success from server

                                SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                Toast.makeText(LoginActivity.this, str7, Toast.LENGTH_LONG).show();
                                Toast.makeText(LoginActivity.this, str, Toast.LENGTH_LONG).show();
                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(Config.MERCHANT_KEY_SHARED_PREF, str4);
                                editor.putString(Config.ACCOUNT_NAME_SHARED_PREF, str5);
                                editor.putString(Config.ACCOUNT_NUMBER_SHARED_PREF, str6);
                                editor.putString(Config.IFSCCODE_SHARED_PREF, str7);
                                editor.putString(Config.ADDRESS_SHARED_PREF, str8);
                                editor.putString(Config.KYCDETAILS_SHARED_PREF, str9);
                                editor.putString(Config.MERCHANT_NAME_SHARED_PREF, str10);
                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                editor.putString(Config.USERNAME_SHARED_PREF, username);
                                editor.putString(Config.KEY_MERCHANTID, str);
                                editor.putString(Config.MOBILENUMBER_SHARED_PREF, username);
                                editor.putString(Config.EMAILID_SHARED_PREF, "emailid");


                                //Saving values to editor
                                editor.commit();
                                // Toast.makeText(LoginActivity.this,sharedPreferences.getString(Config.USERNAME_SHARED_PREF,"username"),Toast.LENGTH_LONG).show();
                                // Toast.makeText(LoginActivity.this,sharedPreferences.getString(Config.ACCOUNT_NAME_SHARED_PREF,"aname"),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                progressBar.setVisibility(View.GONE);
                                startActivity(intent);
                                finish();
                            }
                                else // if(str11.equals("admin"))
                            {



                                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                progressBar.setVisibility(View.GONE);
                                    startActivity(intent);
                                    finish();


                                }


                            }
                            else
                            {
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                            //Starting profile activity

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
                    params.put(Config.KEY_USERNAME, username);
                    params.put(Config.KEY_PASSWORD, password);

                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    public void onClickNewtoEcash(View v) {
        // Toast.makeText(LoginActivity.this, "new to ecash clicked", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
        finish();


    }

    public void onClickForgotPassword(View v) {
        // Toast.makeText(LoginActivity.this, "forgot password clicked", Toast.LENGTH_SHORT).show();

    }
}



