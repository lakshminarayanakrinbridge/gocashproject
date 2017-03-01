package www.com.inbridge.ecash24012017;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminMerchantEditFragment extends android.app.Fragment {




    public AdminMerchantEditFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view;
        view=inflater.inflate(R.layout.fragment_admin_merchant_edit,container,false);
        final TextView mobilenumber=(TextView) view.findViewById(R.id.mobilenumber_textview);
        final TextView emailidtext=(TextView) view.findViewById(R.id.emailid_textview);
        final TextView approvalstatus=(TextView) view.findViewById(R.id.approvedStatus_textview);
        final EditText name=(EditText) view.findViewById(R.id.merchantname_edittext);
        final EditText key=(EditText) view.findViewById(R.id.merchantkey_edittext);
        final EditText accname=(EditText) view.findViewById(R.id.accountname_edittext);
        final EditText accno=(EditText) view.findViewById(R.id.accountnumber_edittext);
        final EditText ifsc=(EditText) view.findViewById(R.id.ifsccode_edittext);
        final EditText lat=(EditText) view.findViewById(R.id.lat_edittext);
        final EditText lng=(EditText) view.findViewById(R.id.lng_edittext);
        final TextView cat=(TextView) view.findViewById(R.id.cat_textview);
        final EditText kyc=(EditText) view.findViewById(R.id.kycdetails_edittext);
        final EditText cashbal=(EditText) view.findViewById(R.id.ecashbal_edittext);
        final EditText cashbackbal=(EditText) view.findViewById(R.id.ecashcashback_edittext);
        final EditText address=(EditText) view.findViewById(R.id.address_edittext);

        Button btn=(Button)view.findViewById(R.id.submit_button);
        Button btn_cancel=(Button) view.findViewById(R.id.cancel_button);






        SharedPreferences sharedPreferences=getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String mid=sharedPreferences.getString(Config.MERCHANTID_SHARED_PREF,"mid");
        String mno=sharedPreferences.getString(Config.MOBILENUMBER_SHARED_PREF,"mobilenumber");
        String emailid=sharedPreferences.getString(Config.EMAILID_SHARED_PREF,"emailid");
        String status=sharedPreferences.getString(Config.APPROVAL_STATUS,"approvalstatus");
        String category=sharedPreferences.getString(Config.CATEGORY_SHARED_PREF,"mcategory");
        String mname=sharedPreferences.getString(Config.MERCHANT_NAME_SHARED_PREF,"mname");
        String mkey=sharedPreferences.getString(Config.MERCHANT_KEY_SHARED_PREF,"mkey");
        String maccname=sharedPreferences.getString(Config.ACCOUNT_NAME_SHARED_PREF,"aname");
        String maccno=sharedPreferences.getString(Config.ACCOUNT_NUMBER_SHARED_PREF,"anumber");
        String mkyc=sharedPreferences.getString(Config.KYCDETAILS_SHARED_PREF,"kycdetails");
        String mifsc=sharedPreferences.getString(Config.IFSCCODE_SHARED_PREF,"ifsccode");
        String mcash=sharedPreferences.getString(Config.ECASH_CASH_BAL_SHARED_PREF,"cashbal");
        String mcashbackbal=sharedPreferences.getString(Config.ECASH_CASH_BACK_BAL_SHARED_PREF,"cashbackbal");
        String mlat=sharedPreferences.getString(Config.LAT_SHARED_PREF,"lat");
        String mlng=sharedPreferences.getString(Config.LNG_SHARED_PREF,"lng");
        String maddr=sharedPreferences.getString(Config.ADDRESS_SHARED_PREF,"address");


        mobilenumber.setText(mno);
        emailidtext.setText(emailid);
        approvalstatus.setText(status);
        cat.setText(category);
        name.setText(mname);
        key.setText(mkey);
        accname.setText(maccname);
        accno.setText(maccno);
        ifsc.setText(mifsc);
        lat.setText(mlat);
        lng.setText(mlng);
        kyc.setText(mkyc);
        cashbal.setText(mcash);
        cashbackbal.setText(mcashbackbal);
        address.setText(maddr);

        btn_cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Fragment fragment=new SearchMerchantFragment();
                FragmentManager fm = ((Activity)view.getContext()).getFragmentManager();
                //fm.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();

            }
        } );

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Toast.makeText(getActivity(), mid,Toast.LENGTH_LONG).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ADMIN_MERCHANT_UPDATE_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                                //Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();


                                JSONObject jsonObject;
                                String string = null;
                                String boolval = null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    string = jsonObject.getString("user_msg");
                                    boolval = jsonObject.getString("success");

                                    if (boolval.equalsIgnoreCase("TRUE")) {
                                        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();

                                      /*  Fragment fragment=new SearchMerchantFragment();
                                     FragmentManager fm = ((Activity)view.getContext()).getFragmentManager();
                                        //fm.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();*/



                                    } else {
                                        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
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

                        params.put("m_code",mid);
                        params.put("m_key",key.getText().toString().trim());
                        params.put("m_name",name.getText().toString().trim());
                        params.put("m_acc_name",accname.getText().toString().trim());
                        params.put("m_account_no",accno.getText().toString().trim());
                        params.put("m_ifsc_code",ifsc.getText().toString().trim());
                        params.put("m_address",address.getText().toString().trim());
                        params.put("kyc_details",kyc.getText().toString().trim());
                        //returning parameter
                        return params;
                    }
                };

                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
                requestQueue.add(stringRequest);

            }
        });



        return view;
    }

}
