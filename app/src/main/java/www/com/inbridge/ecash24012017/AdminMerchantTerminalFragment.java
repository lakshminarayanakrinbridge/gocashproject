package www.com.inbridge.ecash24012017;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import static www.com.inbridge.ecash24012017.R.id.search;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminMerchantTerminalFragment extends android.app.Fragment {


    private RecyclerView recyclerView;
    private AdminMerchantTerminalViewAdapter adminMerchantTerminalViewAdapter;
    List<TerminalDataProvider> information = new ArrayList<>();
    EditText searchtext ;


    public AdminMerchantTerminalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout;
        layout=inflater.inflate(R.layout.fragment_admin_merchant_terminal, container, false);
        recyclerView=(RecyclerView) layout.findViewById(R.id.terminal_recyclerview);
        searchtext= (EditText) layout.findViewById(search);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.TERMINALLIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();

                        JSONArray jsonArray;
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("tlist");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject terminal = jsonArray.getJSONObject(i);
                                TerminalDataProvider adminData = new TerminalDataProvider(terminal.getString("tname"), terminal.getString("tnum"), terminal.getString("temail"),terminal.getString("tcode"));
                                information.add(adminData);
                                Log.e("value", Integer.toString(information.size()));

                            }
                            adminMerchantTerminalViewAdapter=new AdminMerchantTerminalViewAdapter(getActivity(),information);
                            recyclerView.setAdapter(adminMerchantTerminalViewAdapter);
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

                SharedPreferences sharedPreferences=getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
               // String mid=sharedPreferences.getString(Config.MERCHANT_KEY_SHARED_PREF,Config.KEY_MERCHANTID);

                params.put("m_id",sharedPreferences.getString(Config.KEY_MERCHANTID,"m_id"));

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        Log.e("value1", Integer.toString(information.size()));

        searchtext.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<TerminalDataProvider> filteredList = new ArrayList<>();

                for (int i = 0; i < information.size(); i++) {

                    final String text = information.get(i).getCashiername().toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(information.get(i));
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adminMerchantTerminalViewAdapter = new AdminMerchantTerminalViewAdapter(getActivity(),filteredList);
                recyclerView.setAdapter(adminMerchantTerminalViewAdapter);
                adminMerchantTerminalViewAdapter.notifyDataSetChanged();  // data set changed
            }
        });


        return layout;
    }

        @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Merchant Terminals");
    }

}
