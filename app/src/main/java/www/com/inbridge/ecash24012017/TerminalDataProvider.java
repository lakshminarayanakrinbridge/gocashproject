package www.com.inbridge.ecash24012017;

/**
 * Created by USER on 2/6/2017.
 */


/**
 * Created by USER on 1/30/2017.
 */

public class TerminalDataProvider {


    private String cashiername;
    private String mobilenumber;
    private String emailid;
    private String terminalid;


    public TerminalDataProvider(String cashiername,String mobilenumber,String emailid,String terminalid)
    {
        this.setCashiername(cashiername);
        this.setMobilenumber(mobilenumber);
        this.setEmailid(emailid);
        this.setTerminalid(terminalid);

    }

    public String getCashiername() {
        return cashiername;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setCashiername(String cashiername) {
        this.cashiername = cashiername;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getEmailid() {
        return emailid;

    }

    public String getTerminalid() {
        return terminalid;
    }
}
