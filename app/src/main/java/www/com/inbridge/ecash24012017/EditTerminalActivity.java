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

public class EditTerminalActivity extends AppCompatActivity {
    EditText terminalname;
    EditText terminalid;
    EditText terminalemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_terminal);
        Intent i=getIntent();

    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void onClickSubmitButton(View v) {

        terminalname = (EditText) findViewById(R.id.cashiername_edittext);
        terminalid = (EditText) findViewById(R.id.terminalid_edittext);
        terminalemail = (EditText) findViewById(R.id.emailid_edittext);
        final String tname_edittext = terminalname.getText().toString().trim();
        final String tid_edittext = terminalid.getText().toString().trim();
        final String temail_edittext = terminalemail.getText().toString().trim();


        if (tname_edittext.equals("")) {
            terminalname.setError("Cashier Name Is Required");
            terminalname.setHint("Please Enter The Cashier Name");
        } else if (tid_edittext.equals("")) {
            terminalid.setError("Terminal Id is Required");
            terminalid.setHint("Please Enter The Terminal Id");
        } else if (temail_edittext.equals("")) {
            terminalemail.setError("Email Is Required");
            terminalemail.setText("Please Enter the E-mail Id");
        } if (!emailValidator(terminalemail.getText().toString().trim())) {
            terminalemail.setError("Enter Correct E-mail Id");
        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.UPDATETERMINALLIST_URL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(EditTerminalActivity.this, response, Toast.LENGTH_LONG).show();
                    String str = null;

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        str = jsonObject.getString("user_msg");
                        Toast.makeText(EditTerminalActivity.this, str, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
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
                    SharedPreferences sharedPreferences = EditTerminalActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);

                    params.put(Config.terminalname_shared_pref, tname_edittext);
                    //params.put(TerminalConfig.terminalmobile_shared_pref,tid_edittext);
                    params.put("t_id", tid_edittext);
                    params.put(Config.terminalemail_shared_pref, temail_edittext);
                    params.put(Config.MERCHANTID_SHARED_PREF, sharedPreferences.getString(Config.KEY_MERCHANTID, "m_id"));
                    return params;

                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
            Intent i = new Intent(EditTerminalActivity.this, ProfileActivity.class);
            startActivity(i);

        }
    }
    public void onClickCancelButton(View v)
    {
        onBackPressed();
    }

    @Override
    public void onBackPressed()
    {

        super.onBackPressed();
        Intent i=new Intent(EditTerminalActivity.this,ProfileActivity.class);
        finish();
        startActivity(i);


    }


}

