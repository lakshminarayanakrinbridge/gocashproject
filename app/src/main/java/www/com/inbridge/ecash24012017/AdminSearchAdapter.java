package www.com.inbridge.ecash24012017;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2/17/2017.
 */

public class AdminSearchAdapter extends RecyclerView.Adapter<AdminSearchAdapter.MyHolder> {


    private LayoutInflater inflater;
    List<AdminTerminalList> admindata = new ArrayList<>();

    public AdminSearchAdapter(Context context, List<AdminTerminalList> admindata) {
        inflater = LayoutInflater.from(context);
        this.admindata = admindata;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.complete_merchant_list_layout, parent, false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    String strcode;

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        AdminTerminalList data = admindata.get(position);
        holder.textMerchantcode.setText(data.merchantcodeter);
        holder.textmerchantname.setText(data.merchantnameter);
        holder.textcategory.setText(data.categoryter);
        holder.ecashbalance.setText(data.ecashbalanceter);
        holder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AdminTerminalList data = admindata.get(position);
                strcode = data.merchantcodeter;
                Config.newstr=strcode;
                Toast.makeText(view.getContext(), data.merchantcodeter, Toast.LENGTH_LONG).show();

               Fragment fragment=new AdminMerchantTerminalFragment();
                SharedPreferences sharedPreferences=view.getContext().getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(Config.KEY_MERCHANTID,strcode);
                    editor.commit();
               android.app.FragmentManager fm = ((Activity)view.getContext()).getFragmentManager();
               fm.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();


          


            }
        });

        holder.textedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AdminTerminalList data = admindata.get(position);
                strcode = data.merchantcodeter;
                Toast.makeText(view.getContext(), data.merchantcodeter, Toast.LENGTH_LONG).show();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ADMIN_MERCHANT_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                               // Toast.makeText(view.getContext(), response, Toast.LENGTH_LONG).show();


                                JSONObject jsonObject;
                                String string = null;
                                String boolval = null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    string = jsonObject.getString("user_msg");
                                    boolval = jsonObject.getString("success");

                                    if (boolval.equalsIgnoreCase("TRUE")) {
                                        Toast.makeText(view.getContext(), string, Toast.LENGTH_LONG).show();
                                        SharedPreferences sharedPreferences=view.getContext().getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                                        SharedPreferences.Editor e=sharedPreferences.edit();
                                        e.putString(Config.MOBILENUMBER_SHARED_PREF,jsonObject.getString("m_mobilenumber"));
                                        e.putString(Config.EMAILID_SHARED_PREF,jsonObject.getString("m_emailaddress"));
                                        e.putString(Config.MERCHANT_NAME_SHARED_PREF,jsonObject.getString("m_name"));
                                        e.putString(Config.MERCHANT_KEY_SHARED_PREF,jsonObject.getString("m_key"));
                                        e.putString(Config.ACCOUNT_NAME_SHARED_PREF,jsonObject.getString("m_accname"));
                                        e.putString(Config.ACCOUNT_NUMBER_SHARED_PREF,jsonObject.getString("m_accnumber"));
                                        e.putString(Config.KYCDETAILS_SHARED_PREF,jsonObject.getString("m_kyc"));
                                        e.putString(Config.LAT_SHARED_PREF,jsonObject.getString("m_lat"));
                                        e.putString(Config.LNG_SHARED_PREF,jsonObject.getString("m_lng"));
                                        e.putString(Config.ECASH_CASH_BAL_SHARED_PREF,jsonObject.getString("m_ecashbal"));
                                        e.putString(Config.ECASH_CASH_BACK_BAL_SHARED_PREF,jsonObject.getString("m_ecashcashback"));
                                        e.putString(Config.ADDRESS_SHARED_PREF,jsonObject.getString("m_address"));
                                        e.putString(Config.IFSCCODE_SHARED_PREF,jsonObject.getString("m_ifsc"));
                                        e.putString(Config.CATEGORY_SHARED_PREF,jsonObject.getString("cat_name"));
                                        e.commit();
                                        Fragment fragment=new AdminMerchantEditFragment();
                                        android.app.FragmentManager fm = ((Activity)view.getContext()).getFragmentManager();
                                        fm.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();


                                    } else {
                                        Toast.makeText(view.getContext(), string, Toast.LENGTH_LONG).show();
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

                        params.put("mid",strcode);
                        //returning parameter
                        return params;
                    }
                };

                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
                requestQueue.add(stringRequest);

            }
        });





    }


    @Override
    public int getItemCount() {
        return this.admindata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textMerchantcode;
        TextView textcategory;
        TextView textmerchantname;
        TextView textedit;
        TextView textview;
        TextView ecashbalance;

        public MyHolder(View itemView) {
            super(itemView);
            textMerchantcode = (TextView) itemView.findViewById(R.id.merchantcode_textview);
            textcategory = (TextView) itemView.findViewById(R.id.merchantcategory_textview);
            textmerchantname = (TextView) itemView.findViewById(R.id.merchantname_textview);
            textedit = (TextView) itemView.findViewById(R.id.Edit_textview);
            textview = (TextView) itemView.findViewById(R.id.viewterminal_textview);
            ecashbalance = (TextView) itemView.findViewById(R.id.ecashbalance_textview);


        }
    }


}

