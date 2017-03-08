package www.com.inbridge.ecash24012017;

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

import static www.com.inbridge.ecash24012017.R.id.cashiername_edittext;

public class CreateTerminalAdmin extends AppCompatActivity {
    EditText terminalname;
    EditText terminalmobile;
    EditText terminalemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_terminal_admin);
        getIntent();
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void onClickCancelButton(View v) {
        onBackPressed();
    }

    public void onClickSubmitButton(View v) {

        terminalname = (EditText) findViewById(cashiername_edittext);
        terminalmobile = (EditText) findViewById(R.id.mobilenumber_edittext);
        terminalemail = (EditText) findViewById(R.id.emailid_edittext);
        final String tname_edittext = terminalname.getText().toString().trim();
        final String tmobile_edittext = terminalmobile.getText().toString().trim();
        final String temail_edittext = terminalemail.getText().toString().trim();
        if (terminalname.getText().toString().trim().equals("")) {
            terminalname.setError("Cashier Name Is Required");
            terminalname.setHint("Please Enter The Cashier Name");
        } else if (terminalmobile.getText().toString().trim().equals("")) {
            terminalmobile.setError("Mobile Number is Required");
            terminalmobile.setHint("Please Enter The Mobile Number");
        } else if (terminalemail.getText().toString().trim().equals("")) {
            terminalemail.setError("Email Is Required");
            terminalemail.setText("Please Enter the E-mail Id");
        }
        if (terminalmobile.getText().toString().trim().length() != 10) {
            terminalmobile.setError("Enter Correct Mobile Number");
        } else if (!emailValidator(terminalemail.getText().toString().trim())) {
            terminalemail.setError("Enter Correct E-mail Id");
        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ADDTERMINAL_URL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(CreateTerminalAdmin.this, response, Toast.LENGTH_LONG).show();
                    String str = null;
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        str = jsonObject.getString("user_msg");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (response.equalsIgnoreCase(str)) {
                        Toast.makeText(CreateTerminalAdmin.this, str, Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            str = jsonObject.getString("user_msg");
                            Toast.makeText(CreateTerminalAdmin.this, str, Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(CreateTerminalAdmin.this,TerminalActivity.class);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            },
                    new Response.ErrorListener() {

                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want
                        }

                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    SharedPreferences sharedPreferences = CreateTerminalAdmin.this.getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);

                    params.put(Config.terminalname_shared_pref, tname_edittext);
                    params.put(Config.terminalmobile_shared_pref, tmobile_edittext);
                    params.put(Config.terminalemail_shared_pref, temail_edittext);
                    params.put(Config.KEY_MERCHANT_CODE, sharedPreferences.getString(Config.MERCHANT_CODE_SHARED_PREF, "mcode"));
                    return params;

                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            onBackPressed();
        }


    }
    @Override
    public void onBackPressed()
    {

        super.onBackPressed();
        Intent i=new Intent(CreateTerminalAdmin.this,ProfileActivity.class);
        finish();
        startActivity(i);


    }


}