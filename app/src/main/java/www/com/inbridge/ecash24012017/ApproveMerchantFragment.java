package www.com.inbridge.ecash24012017;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApproveMerchantFragment extends Fragment  {

    private RecyclerView recyclerView;
    private AdminAdapter adminAdapter;
   List<AdminData> information = new ArrayList<>();


    public ApproveMerchantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout;
        layout=inflater.inflate(R.layout.fragment_approve_merchant, container, false);
        recyclerView=(RecyclerView) layout.findViewById(R.id.merchant_recyclerview);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.APPROVE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();

                        JSONArray jsonArray;
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("list");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject terminal = jsonArray.getJSONObject(i);
                                AdminData adminData = new AdminData(terminal.getString("m_name"), terminal.getString("category_name"), terminal.getString("m_code"));
                                information.add(adminData);
                                Log.e("value", Integer.toString(information.size()));

                            }
                            adminAdapter=new AdminAdapter(getActivity(),information);
                            recyclerView.setAdapter(adminAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


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


                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        Log.e("value1", Integer.toString(information.size()));



        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Approve Merchant");
    }




}
