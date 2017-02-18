package www.com.inbridge.ecash24012017;

import android.util.Log;

/**
 * Created by USER on 2/16/2017.
 */

public class AdminData {

    public String merchantname;
    public String merchantcode;
    public String merchantcategory;
    public AdminData()
    {

    }

    public AdminData(String merchantname,String merchantcategory,String merchantcode)
    {
        this.merchantname=merchantname;
        this.merchantcategory=merchantcategory;
        this.merchantcode=merchantcode;
        Log.e("code", this.merchantcode );
    }
}
