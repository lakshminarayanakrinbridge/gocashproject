package www.com.inbridge.ecash24012017;

import android.content.Context;
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
 * Created by USER on 2/16/2017.
 */

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyHolder> {


    private  LayoutInflater inflater;
   List<AdminData> admindata=new ArrayList<>();

    public AdminAdapter(Context context,List<AdminData> admindata)
    {
        inflater=LayoutInflater.from(context);
        this.admindata=admindata;
    }




    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view= inflater.inflate(R.layout.recycler_layout,parent,false);
        MyHolder myholder=new MyHolder(view);
        return myholder;
    }
    String strcode;

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        AdminData data=admindata.get(position);
        holder.textMerchantcode.setText(data.merchantcode);
        holder.textmerchantname.setText(data.merchantname);
        holder.textcategory.setText(data.merchantcategory);
        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AdminData data=admindata.get(position);
                strcode=data.merchantcode;
                Toast.makeText(view.getContext(),data.merchantcode,Toast.LENGTH_LONG).show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.APPROVED_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                                Toast.makeText(view.getContext(), response, Toast.LENGTH_LONG).show();


                                JSONObject jsonObject;
                                String string=null;
                                String boolval=null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    string=jsonObject.getString("user_msg");
                                    boolval=jsonObject.getString("success");

                                    if(boolval.equalsIgnoreCase("TRUE"))
                                    {
                                        Toast.makeText(view.getContext(),string,Toast.LENGTH_LONG).show();
                                        holder.approve.setText("Approved");
                                    }
                                    else
                                    {
                                        Toast.makeText(view.getContext(),string,Toast.LENGTH_LONG).show();
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

    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView textMerchantcode;
        TextView textcategory;
        TextView textmerchantname;
        TextView approve;

        public MyHolder(View itemView) {
            super(itemView);
            textMerchantcode=(TextView) itemView.findViewById(R.id.merchantcode_textview);
            textcategory=(TextView) itemView.findViewById(R.id.merchantcategory_textview);
            textmerchantname=(TextView) itemView.findViewById(R.id.merchantname_textview);
            approve=(TextView) itemView.findViewById(R.id.approve_textview);


        }
    }
}
