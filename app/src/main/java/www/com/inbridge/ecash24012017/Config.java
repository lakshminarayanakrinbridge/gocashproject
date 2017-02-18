package www.com.inbridge.ecash24012017;

/**
 * Created by USER on 1/24/2017.
 */

public class Config {


    public static  final String URL= "http://3dedn.com/E-Cash/app/";

    public static final String LOGIN_URL = URL+"login_update.php";

    public static final String OTP_URL=URL+"OTP_android.php";

    public static final String  CATEGORY_URL=URL+"create_merchant.php";

    public static final String DATA_URL=URL+"insert_merchant.php";

    public static final String APPROVE_URL=URL+"admin_merchant_unapproved.php";

    public static final String APPROVED_URL=URL+"admin_merchant_approve.php";

    public static final String EDIT_MERCHANT_URL =URL+"update_merchant.php";

    public static final String ADMIN_LIST_MERCHANT_URL =URL+"admin_merchant_list.php";


    public static final String ADDTERMINAL_URL=URL+"addTerminal.php";

    public static final String TERMINALLIST_URL=URL+"TerminalList.php";


    public static final String UPDATETERMINALLIST_URL=URL+"update_terminal.php";


    public static final String TAG_USERNAME = "name";
    public static final String JSON_ARRAY = "categories";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    //Keys for email and password as defined in our $_POST['mobile_no'] in https://3dedn.com/E-Cash/app/OTP_android.php
    public static final String KEY_MOBILENUMBER="mobile_no";
    //public static final String KEY_EMAILID="emailid";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "true";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "ecash";

    //This would be used to store the email of current logged in user
    public static final String USERNAME_SHARED_PREF = "username";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";




    public static final String MERCHANTID_SHARED_PREF="m_id";

    public static final String KEY_MERCHANTID="m_id";

   // public static final String OTPTIMESTAMP_SHARED_PREF="otp_timestamp";




    public static final String terminalname_shared_pref="tname";
    public static final String terminalmobile_shared_pref="tnum";
    public static final String terminalemail_shared_pref="tmail";




    public static final String LOGIN_FAILURE1="You will receive an OTP only on this number";
    public static final String LOGIN_FAILURE2="OTP not delivered";
    public static final String LOGIN_FAILURE3="Register Number already Exists";
    public static final String LOGIN_FAILURE4="Parameter missing";
    public static final String OTP_KEY="otp";

    public static final String MERCHANT_NAME_SHARED_PREF="mname";
    public static final String MERCHANT_KEY_SHARED_PREF="mkey";
    public static final String ACCOUNT_NAME_SHARED_PREF="aname";
    public static final String ACCOUNT_NUMBER_SHARED_PREF="anumber";
    public static final String IFSCCODE_SHARED_PREF="ifsccode";
    public static final String ADDRESS_SHARED_PREF="address";
    public static final String KYCDETAILS_SHARED_PREF="kycdetails";
    public static final String CATEGORY_SHARED_PREF="mcategory";
    public static final String MOBILENUMBER_SHARED_PREF="mobilenumber";
    public static final String EMAILID_SHARED_PREF="emailid";






}
