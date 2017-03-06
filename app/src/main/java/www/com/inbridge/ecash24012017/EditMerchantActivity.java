package www.com.inbridge.ecash24012017;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class EditMerchantActivity extends AppCompatActivity {
    EditText merchantnameEdittext;
    EditText merchantkeyEdittext;
    EditText accountnameEdittext;
    EditText ifsccodeEdittext;
    EditText accountnumberEdittext;
    EditText addressEdittext;
    EditText kycdetailseEdittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_merchant);
        SharedPreferences sharedPreferences = EditMerchantActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        merchantnameEdittext = (EditText) findViewById(R.id.merchantname_edittext);
         merchantkeyEdittext = (EditText) findViewById(R.id.merchantkey_edittext);
         accountnameEdittext = (EditText) findViewById(R.id.accountname_edittext);
       ifsccodeEdittext = (EditText) findViewById(R.id.ifsccode_edittext);
         accountnumberEdittext = (EditText) findViewById(R.id.accountnumber_edittext);
       addressEdittext = (EditText) findViewById(R.id.address_edittext);
        kycdetailseEdittext = (EditText) findViewById(R.id.kycdetails_edittext);


        merchantnameEdittext.setText(sharedPreferences.getString(Config.MERCHANT_NAME_SHARED_PREF,"mname"));
        merchantkeyEdittext.setText(sharedPreferences.getString(Config.MERCHANT_KEY_SHARED_PREF,"mkey"));
        //memail.setText(sharedPreferences.getString(Config.EMAILID_SHARED_PREF,"emailid"));
        // mmobile.setText(sharedPreferences.getString(Config.MOBILENUMBER_SHARED_PREF,"mobilenumber"));
        ifsccodeEdittext.setText(sharedPreferences.getString(Config.IFSCCODE_SHARED_PREF,"ifsccode"));
        accountnumberEdittext.setText(sharedPreferences.getString(Config.ACCOUNT_NUMBER_SHARED_PREF,"anumber"));
        accountnameEdittext.setText(sharedPreferences.getString(Config.ACCOUNT_NAME_SHARED_PREF,"aname"));
        addressEdittext.setText(sharedPreferences.getString(Config.ADDRESS_SHARED_PREF,"address"));
        kycdetailseEdittext.setText(sharedPreferences.getString(Config.KYCDETAILS_SHARED_PREF,"kycdetails"));
    }

    public void onClickCancelbutton(View v) {
       onBackPressed();
    }

    public void onClickSubmitbutton(View v) {

        EditText merchantnameEdittext = (EditText) findViewById(R.id.merchantname_edittext);
        EditText merchantkeyEdittext = (EditText) findViewById(R.id.merchantkey_edittext);
        EditText accountnameEdittext = (EditText) findViewById(R.id.accountname_edittext);
        EditText ifsccodeEdittext = (EditText) findViewById(R.id.ifsccode_edittext);
        EditText accountnumberEdittext = (EditText) findViewById(R.id.accountnumber_edittext);
        EditText addressEdittext = (EditText) findViewById(R.id.address_edittext);
        EditText kycdetailseEdittext = (EditText) findViewById(R.id.kycdetails_edittext);


        if (merchantnameEdittext.getText().toString().trim().equals("")) {
            merchantnameEdittext.setError("Merchant Name is required!");

            merchantnameEdittext.setHint("please enter Merchant Name");
        } else if (merchantkeyEdittext.getText().toString().trim().equals("")) {
            merchantkeyEdittext.setError(" Merchant Key  is required!");

            merchantkeyEdittext.setHint("please enter Merchant Key");

        } else if (accountnameEdittext.getText().toString().trim().equals("")) {
            accountnameEdittext.setError("Account Name is required!");
            accountnameEdittext.setHint("please enter Account name");
        } else if (accountnumberEdittext.getText().toString().trim().equals("")) {
            accountnumberEdittext.setError("Account Number is required!");
            accountnumberEdittext.setHint("please enter Account number");
        } else if (ifsccodeEdittext.getText().toString().trim().equals("")) {
            ifsccodeEdittext.setError("IFSC code is required!");
            ifsccodeEdittext.setHint("please enter IFSC code");
        } else if (addressEdittext.getText().toString().trim().equals("")) {
            addressEdittext.setError("address is required!");
            addressEdittext.setHint("please enter addresss");
        } else if (kycdetailseEdittext.getText().toString().trim().equals("")) {
            kycdetailseEdittext.setError("KYC details  is required!");
            kycdetailseEdittext.setHint("please enter KYC details");
        } else {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.EDIT_MERCHANT_URL,
                    new Response.Listener<String>() {
                        JSONObject obj;
                        String str2;

                        @Override
                        public void onResponse(String response) {
                            try {
                                obj = new JSONObject(response);
                                str2 = obj.getString("user_msg");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //If we are getting success from server
                            if (str2.equalsIgnoreCase("merchant updated sucessfully")) {
                                //Creating a shared preference
                                //Starting profile activity
                                Intent intent = new Intent(EditMerchantActivity.this, ProfileActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                //If the server response is not success
                                //Displaying an error message on toast
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    String str = obj.getString("user_msg");
                                    Toast.makeText(EditMerchantActivity.this, str, Toast.LENGTH_LONG).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


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

                    SharedPreferences sharedPreferences = EditMerchantActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    EditText merchantnameEdittext = (EditText) findViewById(R.id.merchantname_edittext);
                    EditText merchantkeyEdittext = (EditText) findViewById(R.id.merchantkey_edittext);
                    EditText accountnameEdittext = (EditText) findViewById(R.id.accountname_edittext);
                    EditText ifsccodeEdittext = (EditText) findViewById(R.id.ifsccode_edittext);
                    EditText accountnumberEdittext = (EditText) findViewById(R.id.accountnumber_edittext);
                    EditText addressEdittext = (EditText) findViewById(R.id.address_edittext);
                    EditText kycdetailseEdittext = (EditText) findViewById(R.id.kycdetails_edittext);


                    String merchantname = merchantnameEdittext.getText().toString();
                    String merchantkey = merchantkeyEdittext.getText().toString();
                    String accountname = accountnameEdittext.getText().toString();
                    String ifsccode = ifsccodeEdittext.getText().toString();
                    String accontnumber = accountnumberEdittext.getText().toString();
                    String address = addressEdittext.getText().toString();
                    String kycdetails = kycdetailseEdittext.getText().toString();
                    //Adding parameters to request


                    params.put("m_name", merchantname);
                    params.put("m_key", merchantkey);
                    params.put("m_acc_name", accountname);
                    params.put("m_account_no", accontnumber);
                    params.put("m_ifsc_code", ifsccode);
                    params.put("m_address", address);
                    params.put("kyc_details", kycdetails);
                    params.put("m_id", sharedPreferences.getString(Config.KEY_MERCHANTID, "m_id"));


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
    public void onBackPressed()
    {

        super.onBackPressed();
        Intent i=new Intent(EditMerchantActivity.this,MyprofileActivity.class);
        finish();
        startActivity(i);


    }


    }

