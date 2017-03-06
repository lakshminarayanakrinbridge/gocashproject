package www.com.inbridge.ecash24012017;

/**
 * Created by USER on 1/24/2017.
 */

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
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    //Textview to show currently logged in user
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Initializing textview
        textView = (TextView) findViewById(R.id.user_textview);

        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Config.USERNAME_SHARED_PREF,"Not Available");
        Toast.makeText(ProfileActivity.this,username,Toast.LENGTH_LONG).show();

        //Showing the current logged in email to textview
        textView.setText(username);
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
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.USERNAME_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
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


    public void onClickDashboardButton(View v)
    {
       Intent i=new Intent(ProfileActivity.this,DashboardActivity.class);
        startActivity(i);
        finish();



    }

    public void onClickTerminalButton(View v)
    {
        Intent i=new Intent(ProfileActivity.this,TerminalActivity.class);
        startActivity(i);
        finish();

    }

    public void onClickMyprofileButton(View v)
    {
        Intent i=new Intent(ProfileActivity.this,MyprofileActivity.class);
        startActivity(i);
        finish();

    }

    public void onClickEditterminalButton(View v) {
        Intent i = new Intent(ProfileActivity.this, EditTerminalActivity.class);
        startActivity(i);
        finish();


    }

    @Override
    public void onBackPressed()
    {

        super.onBackPressed();
        Intent i=new Intent(ProfileActivity.this,LoginActivity.class);
        finish();
        startActivity(i);


    }

}