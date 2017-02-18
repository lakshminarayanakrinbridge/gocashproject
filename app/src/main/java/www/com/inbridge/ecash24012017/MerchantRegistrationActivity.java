package www.com.inbridge.ecash24012017;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MerchantRegistrationActivity extends AppCompatActivity {


    private Spinner spinner;

    //An ArrayList for Spinner Items
    private ArrayList<String> students;
    private long category_id=0;
    //JSON Array
    private JSONArray result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_registration);

        Intent i=getIntent();
        SharedPreferences sharedPreferences = MerchantRegistrationActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String mobilenumber1 = sharedPreferences.getString(Config.MOBILENUMBER_SHARED_PREF, "mobilenumber");
        String emailid1 = sharedPreferences.getString(Config.EMAILID_SHARED_PREF, "emailid");
        TextView mobilenumberTextView=(TextView) findViewById(R.id.mobilenumber_textview);
        TextView emailidTextView=(TextView) findViewById(R.id.emailid_textview);
        mobilenumberTextView.setText(mobilenumber1);
        emailidTextView.setText(emailid1);

        students = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.category_spinner);
        getData();
        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
       // spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, students);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.category_spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(MerchantRegistrationActivity.this, Long.toString(id),
                            Toast.LENGTH_SHORT).show();
                    category_id=id;
                }
                Toast.makeText(MerchantRegistrationActivity.this, "Selected",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });


    }




    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Config.CATEGORY_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(Config.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getStudents(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j){
        //Traversing through all the items in the json array

            students.add("Select Category");
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                students.add(json.getString(Config.TAG_USERNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(MerchantRegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, students));
    }




    public void onClickGetlatitudelongitudebutton(View v)
    {
        Intent i=new Intent(MerchantRegistrationActivity.this,MapsActivity.class);
        startActivity(i);
    }


    public void onClickCreateterminal(View v) {
        Intent i = new Intent(MerchantRegistrationActivity.this, CreateTerminal.class);
        startActivity(i);

    }

    public void onClickCreateterminal1(View v)
    {
        Intent i = new Intent(MerchantRegistrationActivity.this, CreateTerminal1.class);
        startActivity(i);
    }


    public void onClickUploadprofileimagebutton(View v)
    {

    }

    public void onClickCancelbutton(View v)
    {
        Intent i=new Intent(MerchantRegistrationActivity.this,LoginActivity.class);
        startActivity(i);
    }

    public void onClickSubmitbutton(View v) {
        SharedPreferences sharedPreferences = MerchantRegistrationActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
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
        } else if (accountnameEdittext.getText().toString().trim().equals("")) {
            accountnameEdittext.setError("Account Name is required!");
            accountnameEdittext.setHint("please enter Account name");
        } else if
                (
                (sharedPreferences.getString(Merchantconfig.LATITUDE_SHARED_PREF, "lat").equals("lat")) ||( sharedPreferences.getString(Merchantconfig.LONGITUDE_SHARED_PREF, "lng").equals("lng")))
                {

                    Toast.makeText(MerchantRegistrationActivity.this,"Please Select The Proper Latitude And Longitude!",Toast.LENGTH_LONG).show();
                }
         else {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DATA_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //If we are getting success from server
                            if (response.equalsIgnoreCase("Created Successfully")) {
                                //Creating a shared preference

                                //Starting profile activity
                                Intent intent = new Intent(MerchantRegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                //If the server response is not success
                                //Displaying an error message on toast
                                try{
                                JSONObject obj=new JSONObject(response);
                                String str=obj.getString("user_msg");
                                    Toast.makeText(MerchantRegistrationActivity.this,str,Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(MerchantRegistrationActivity.this,LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                catch(JSONException e)
                                {
                                    e.printStackTrace();
                                }
                                Toast.makeText(MerchantRegistrationActivity.this,response,Toast.LENGTH_LONG).show();

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

                    SharedPreferences sharedPreferences = MerchantRegistrationActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    String mobilenumber1 = sharedPreferences.getString(Config.MOBILENUMBER_SHARED_PREF, "mobilenumber");
                    String emailid1 = sharedPreferences.getString(Config.EMAILID_SHARED_PREF, "emailid");
                    SharedPreferences sharedPreferences1=MerchantRegistrationActivity.this.getSharedPreferences(Merchantconfig.SHARED_PREF_NAME,Context.MODE_PRIVATE);


                    EditText merchantnameEdittext=(EditText) findViewById(R.id.merchantname_edittext) ;
                    EditText merchantkeyEdittext=(EditText) findViewById(R.id.merchantkey_edittext) ;
                    EditText accountnameEdittext=(EditText) findViewById(R.id.accountname_edittext) ;
                    EditText ifsccodeEdittext=(EditText) findViewById(R.id.ifsccode_edittext) ;
                    EditText accountnumberEdittext=(EditText) findViewById(R.id.accountnumber_edittext) ;
                    EditText addressEdittext=(EditText) findViewById(R.id.address_edittext) ;
                    EditText kycdetailseEdittext=(EditText) findViewById(R.id.kycdetails_edittext) ;


                    String merchantname=merchantnameEdittext.getText().toString();
                    String merchantkey=merchantkeyEdittext.getText().toString();
                    String accountname=accountnameEdittext.getText().toString();
                    String ifsccode=ifsccodeEdittext.getText().toString();
                    String accontnumber=accountnumberEdittext.getText().toString();
                    String address=addressEdittext.getText().toString();
                    String kycdetails=kycdetailseEdittext.getText().toString();
                    //Adding parameters to request

                    params.put("mer_name",merchantname);
                    params.put("m_key",merchantkey);
                    params.put("Rmobile",mobilenumber1);
                    params.put("Remail",emailid1);
                    params.put("acc_name",accountname);
                    params.put("acc_num",accontnumber);
                    params.put("ifsc_code",ifsccode);
                    params.put("address",address);
                    params.put("kyc_detail",kycdetails);
                    params.put("lat",sharedPreferences.getString(Merchantconfig.LATITUDE_SHARED_PREF,"lat"));
                    params.put("lng",sharedPreferences.getString(Merchantconfig.LONGITUDE_SHARED_PREF,"lng"));
                    params.put("mer_type","0");
                    params.put("category_list",Long.toString(category_id));
                    if(sharedPreferences1.getString(Merchantconfig.KEY_CASHIERNAME1,"cashiername1").equalsIgnoreCase("cashiername1"))
                    {

                    }
                    else {
                        params.put(Merchantconfig.TERMINAL1_CASHIERNAME, sharedPreferences1.getString(Merchantconfig.KEY_CASHIERNAME1, "cashiername1"));
                        params.put(Merchantconfig.TERMINAL1_MOBILENUMBER, sharedPreferences1.getString(Merchantconfig.KEY_CASHIERMOBILE1, "cashiermobile1"));
                        params.put(Merchantconfig.TERMINAL1_EMAILID, sharedPreferences1.getString(Merchantconfig.KEY_CASHIEREMAIL1, "cashieremail1"));
                    }
                    if(sharedPreferences1.getString(Merchantconfig.KEY_CASHIERNAME2,"cashiername2").equalsIgnoreCase("cashiername2"))
                    {

                    }
                    else {
                        params.put(Merchantconfig.TERMINAL2_CASHIERNAME, sharedPreferences1.getString(Merchantconfig.KEY_CASHIERNAME2, "cashiername2"));
                        params.put(Merchantconfig.TERMINAL2_MOBILENUMBER, sharedPreferences1.getString(Merchantconfig.KEY_CASHIERMOBILE2, "cashiermobile2"));
                        params.put(Merchantconfig.TERMINAL2_EMAILID, sharedPreferences1.getString(Merchantconfig.KEY_CASHIEREMAIl2, "cashieremail2"));
                    }


                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);



        }

    }

}
