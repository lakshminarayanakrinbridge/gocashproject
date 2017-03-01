package www.com.inbridge.ecash24012017;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateMerchantFragment extends Fragment {

    private Spinner spinner;
    EditText merchantnameEdittext;
    EditText merchantkeyEdittext;
    EditText accountnameEdittext;
    EditText ifsccodeEdittext;
    EditText accountnumberEdittext;
    EditText addressEdittext;
    EditText kycdetailseEdittext;
    EditText mobilenumberEdittext;
    EditText emailidEdittext;
    EditText latEdittext;
    EditText lngEdittext;





    //An ArrayList for Spinner Items
    private ArrayList<String> students;
    private long category_id=0;
    //JSON Array
    private JSONArray result;

    public CreateMerchantFragment() {
        // Required empty public constructor
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       final View v = inflater.inflate(R.layout.fragment_create_merchant, container, false);


        merchantnameEdittext = (EditText) v.findViewById(R.id.merchantname_edittext);
         merchantkeyEdittext = (EditText) v.findViewById(R.id.merchantkey_edittext);
       accountnameEdittext = (EditText) v.findViewById(R.id.accountname_edittext);
         ifsccodeEdittext = (EditText) v.findViewById(R.id.ifsccode_edittext);
         accountnumberEdittext = (EditText) v.findViewById(R.id.accountnumber_edittext);
        addressEdittext = (EditText) v.findViewById(R.id.address_edittext);
         kycdetailseEdittext = (EditText) v.findViewById(R.id.kycdetails_edittext);
        mobilenumberEdittext=(EditText) v.findViewById(R.id.merchantmobile_edittext);
       emailidEdittext=(EditText) v.findViewById(R.id.merchantemail_edittext);
         latEdittext=(EditText) v.findViewById(R.id.lat_edittext);
         lngEdittext=(EditText) v.findViewById(R.id.lng_edittext);






        // Inflate the layout for this fragment
        students = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) v.findViewById(R.id.category_spinner);
        getData();
        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        // spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, students);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) v.findViewById(R.id.category_spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    //Toast.makeText(getActivity(),Long.toString(position),Toast.LENGTH_SHORT).show();
                    category_id=id;
                }
                //Toast.makeText(getActivity(), "Selected",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });


        Button submitButton=(Button) v.findViewById(R.id.submit_button);
        Button btn_cancel=(Button) v.findViewById(R.id.cancel_button);

        btn_cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,new AdminDashboardFragment()).commit();
            }
        });




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(mobilenumberEdittext.getText().toString().trim().equals("")) {
                    mobilenumberEdittext.setError("Mobile Number Required");
                    mobilenumberEdittext.setHint("Please Enter The Mobile Number");
                }else  if(emailidEdittext.getText().toString().trim().equals("")){
                    emailidEdittext.setError("E-mail Id is Required");
                    emailidEdittext.setHint("Please Enter The E-mail Id");

                }else if (merchantnameEdittext.getText().toString().trim().equals("")) {
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
                } else if(latEdittext.getText().toString().equals("")) {
                    latEdittext.setError("Latitude Of The Location Is required!");
                    latEdittext.setHint("Please Enter The Proper Latitude");

                } else if(lngEdittext.getText().toString().equals("")) {
                    accountnameEdittext.setError("Account Name is required!");
                    accountnameEdittext.setHint("please enter The Proper Longitude");
                } else if(!emailValidator(emailidEdittext.getText().toString()))
                {
                    emailidEdittext.setError("E-mail Id is Incorrect");
                    emailidEdittext.setHint("Please Enter The E-mail Id");
                }

                else {


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DATA_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //If we are getting success from server
                                    String str=null;
                                    try{
                                        JSONObject obj=new JSONObject(response);
                                         str=obj.getString("user_msg");


                                    }
                                    catch(JSONException e)
                                    {
                                        e.printStackTrace();
                                    }

                                    //Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
                                    if (str.equals("Merchant Created Successfully.")) {
                                        //Creating a shared preference
                                        Toast.makeText(getActivity(),str,Toast.LENGTH_LONG).show();
                                        //Starting profile activity
                                        FragmentManager fragmentManager=getFragmentManager();
                                        fragmentManager.beginTransaction().replace(R.id.content_frame,new AdminDashboardFragment()).commit();


                                    } else {
                                        //If the server response is not success
                                        //Displaying an error message on toast

                                        Toast.makeText(getActivity(),str,Toast.LENGTH_LONG).show();

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

                            String merchantname=null;
                            String merchantkey=null;
                            String accountname=null;
                            String ifsccode=null;
                            String accontnumber=null;
                            String address=null;
                            String kycdetails=null;
                            String mobilenumber1=null;
                            String emailid1=null;
                            String latitude=null;
                            String longitude=null;

                            merchantname=merchantnameEdittext.getText().toString().trim();
                            merchantkey=merchantkeyEdittext.getText().toString().trim();
                            accountname=accountnameEdittext.getText().toString().trim();
                            ifsccode=ifsccodeEdittext.getText().toString().trim();
                            accontnumber=accountnumberEdittext.getText().toString().trim();
                            address=addressEdittext.getText().toString().trim();
                            kycdetails=kycdetailseEdittext.getText().toString().trim();
                            mobilenumber1=mobilenumberEdittext.getText().toString().trim();
                            emailid1=emailidEdittext.getText().toString().trim();
                            latitude=latEdittext.getText().toString().trim();
                            longitude=lngEdittext.getText().toString().trim();


                            params.put("mer_name",merchantname);
                            params.put("m_key",merchantkey);
                            params.put("Rmobile",mobilenumber1);
                            params.put("Remail",emailid1);
                            params.put("acc_name",accountname);
                            params.put("acc_num",accontnumber);
                            params.put("ifsc_code",ifsccode);
                            params.put("address",address);
                            params.put("kyc_detail",kycdetails);
                            params.put("lat",latitude);
                            params.put("lng",longitude);
                            params.put("mer_type","1");
                            params.put("category_list",Long.toString(category_id));


                            //returning parameter
                            return params;
                        }
                    };

                    //Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);



                }

            }





        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Create Merchant");


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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

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
        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, students));
    }





}
