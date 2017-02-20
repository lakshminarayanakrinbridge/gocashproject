package www.com.inbridge.ecash24012017;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2/20/2017.
 */

//AdminMerchantTerminalViewAdapter

public class AdminMerchantTerminalViewAdapter extends RecyclerView.Adapter<AdminMerchantTerminalViewAdapter.MyHolder> {
  /*  @Override
    public void onBindViewHolder(AdminSearchAdapter.MyHolder holder, int position) {

    }*/

    private LayoutInflater inflater;
    List<TerminalDataProvider> admindata = new ArrayList<>();

    public AdminMerchantTerminalViewAdapter(Context context, List<TerminalDataProvider> admindata) {
        inflater = LayoutInflater.from(context);
        this.admindata = admindata;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.admin_terminal_list, parent, false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    String strcode;

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        TerminalDataProvider data = admindata.get(position);
        holder.textterminalcode.setText(data.getTerminalid());
        holder.textmerchantname.setText(data.getCashiername());
        holder.textmobile.setText(data.getMobilenumber());
        holder.textemail.setText(data.getEmailid());





    }


    @Override
    public int getItemCount() {
        return this.admindata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textterminalcode;
        TextView textmerchantname;
        TextView textmobile;
        TextView textemail;

        public MyHolder(View itemView) {
            super(itemView);
            textterminalcode = (TextView) itemView.findViewById(R.id.terminalid_textview);
            textmerchantname = (TextView) itemView.findViewById(R.id.cashiername_textview);
            textmobile = (TextView) itemView.findViewById(R.id.mobilenumber_textview);
            textemail = (TextView) itemView.findViewById(R.id.emailid_textview);



        }
    }


}