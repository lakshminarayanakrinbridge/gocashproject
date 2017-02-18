package www.com.inbridge.ecash24012017;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
       /* holder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AdminTerminalList data = admindata.get(position);
                strcode = data.merchantcodeter;
                Toast.makeText(view.getContext(), data.merchantcodeter, Toast.LENGTH_LONG).show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.APPROVED_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                                Toast.makeText(view.getContext(), response, Toast.LENGTH_LONG).show();


                                JSONObject jsonObject;
                                String string = null;
                                String boolval = null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    string = jsonObject.getString("user_msg");
                                    boolval = jsonObject.getString("success");

                                    if (boolval.equalsIgnoreCase("TRUE")) {
                                        Toast.makeText(view.getContext(), string, Toast.LENGTH_LONG).show();

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

                        params.put("m_id",strcode);
                        //returning parameter
                        return params;
                    }
                };

                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
                requestQueue.add(stringRequest);

            }
        });

        holder.textedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AdminTerminalList data = admindata.get(position);
                strcode = data.merchantcodeter;
                Toast.makeText(view.getContext(), data.merchantcodeter, Toast.LENGTH_LONG).show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.APPROVED_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                                Toast.makeText(view.getContext(), response, Toast.LENGTH_LONG).show();


                                JSONObject jsonObject;
                                String string = null;
                                String boolval = null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    string = jsonObject.getString("user_msg");
                                    boolval = jsonObject.getString("success");

                                    if (boolval.equalsIgnoreCase("TRUE")) {
                                        Toast.makeText(view.getContext(), string, Toast.LENGTH_LONG).show();

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

                        params.put("m_id",strcode);
                        //returning parameter
                        return params;
                    }
                };

                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
                requestQueue.add(stringRequest);

            }
        });*/




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

    public static class MyFragment extends Fragment
    {


    }
}

