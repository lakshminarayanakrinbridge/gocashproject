package www.com.inbridge.ecash24012017;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
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

import java.util.HashMap;
import java.util.Map;

public class TerminalActivity extends AppCompatActivity {

    ListView listView;

    TerminalAdapter terminalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal);
        listView=(ListView)findViewById(R.id.list_view);
        terminalAdapter=new TerminalAdapter(getApplicationContext(),R.layout.terminal_layout);
        listView.setAdapter(terminalAdapter);
        SharedPreferences sharedPreferences=TerminalActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME,MODE_PRIVATE);
            Toast.makeText(getApplicationContext(),sharedPreferences.getString(Config.MERCHANT_CODE_SHARED_PREF,"mcode"),Toast.LENGTH_LONG).show();


        StringRequest stringRequest=new StringRequest(Request.Method.POST,Config.TERMINALLIST_URL,new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                JSONArray jsonArray;
                JSONObject jsonObject;
                try {
                    jsonObject=new JSONObject(response);
                    jsonArray=jsonObject.getJSONArray("tlist");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject terminal = jsonArray.getJSONObject(i);
                        TerminalDataProvider dataProvider = new TerminalDataProvider(terminal.getString("tname"), terminal.getString("tnum"), terminal.getString( "temail"),terminal.getString(  "tcode"));
                        terminalAdapter.add(dataProvider);
                    }


                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }



            }
        },
                new Response.ErrorListener(){

                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }

                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences sharedPreferences=TerminalActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME,MODE_PRIVATE);


                params.put(Config.KEY_MERCHANT_CODE,sharedPreferences.getString(Config.MERCHANT_CODE_SHARED_PREF,"mcode"));
                return params;

            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);





    }


    //Logout function
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.USERNAME_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(TerminalActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClickAddnewterminalButton(View v)
    {
        Intent i=new Intent(TerminalActivity.this,CreateTerminalAdmin.class);
        startActivity(i);


    }




}
