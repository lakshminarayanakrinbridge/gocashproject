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
import android.widget.TextView;

public class MyprofileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        TextView mcategoryname=(TextView) findViewById(R.id.mcategory_textview);
        TextView mname=(TextView) findViewById(R.id.mname_textview);
        TextView mkey=(TextView) findViewById(R.id.mkey_textview);
        TextView memail=(TextView) findViewById(R.id.memail_textview);
        TextView mmobile=(TextView) findViewById(R.id.mmobile_textview);
        TextView mifsc=(TextView) findViewById(R.id.mifsc_textview);
        TextView maccountnumber=(TextView) findViewById(R.id.maccountnumber_textview);
        TextView maccountname=(TextView) findViewById(R.id.maccountname_textview);
        TextView maddress=(TextView) findViewById(R.id.maddress_textview);
        TextView mkycdetails=(TextView) findViewById(R.id.mkyc_textview);
        SharedPreferences sharedPreferences=MyprofileActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME,MODE_PRIVATE);
        mcategoryname.setText(sharedPreferences.getString(Config.CATEGORY_SHARED_PREF,"mcategory"));
        mname.setText(sharedPreferences.getString(Config.MERCHANT_NAME_SHARED_PREF,"mname"));
        mkey.setText(sharedPreferences.getString(Config.MERCHANT_KEY_SHARED_PREF,"mkey"));
        memail.setText(sharedPreferences.getString(Config.EMAILID_SHARED_PREF,"emailid"));
        mmobile.setText(sharedPreferences.getString(Config.MOBILENUMBER_SHARED_PREF,"mobilenumber"));
        mifsc.setText(sharedPreferences.getString(Config.IFSCCODE_SHARED_PREF,"ifsccode"));
        maccountnumber.setText(sharedPreferences.getString(Config.ACCOUNT_NUMBER_SHARED_PREF,"anumber"));
        maccountname.setText(sharedPreferences.getString(Config.ACCOUNT_NAME_SHARED_PREF,"aname"));
        maddress.setText(sharedPreferences.getString(Config.ADDRESS_SHARED_PREF,"address"));
        mkycdetails.setText(sharedPreferences.getString(Config.KYCDETAILS_SHARED_PREF,"kycdetails"));




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
                        Intent intent = new Intent(MyprofileActivity.this, LoginActivity.class);
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


    public void onClickEditMerchantButton(View v)
    {
        Intent i=new Intent(MyprofileActivity.this,EditMerchantActivity.class);
        startActivity(i);
        finish();
    }

    public void onClickOkButton(View v)
    {
        onBackPressed();
    }



}
