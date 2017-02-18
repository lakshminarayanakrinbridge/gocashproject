package www.com.inbridge.ecash24012017;

/**
 * Created by USER on 2/17/2017.
 */

public class AdminTerminalList {

    String merchantcodeter;
    String categoryter;
    String merchantnameter;
    String ecashbalanceter;


    public AdminTerminalList()
    {

    }

    public AdminTerminalList(String merchantname,String category,String merchantcode,String ecashbalance)
    {
        this.merchantnameter=merchantname;
        this.categoryter=category;
        this.merchantcodeter=merchantcode;
        this.ecashbalanceter=ecashbalance;

    }

}
