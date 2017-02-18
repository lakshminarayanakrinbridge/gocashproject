package www.com.inbridge.ecash24012017;

/**
 * Created by USER on 2/6/2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 1/30/2017.
 */

public class TerminalAdapter extends ArrayAdapter {
    List list=new ArrayList();



    static class DataHandler
    {
        TextView cashiername;
        TextView mobilenumber;
        TextView emailid;
        TextView terminalid;
    }


    public TerminalAdapter(Context context, int resource) {
        super(context, resource);
    }
    @Override
    public void add(Object object)
    {
        super.add(object);
        list.add(object);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row=convertView;
        DataHandler handler;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.terminal_layout,parent,false);
            handler=new DataHandler();
            handler.cashiername=(TextView)row.findViewById(R.id.cashiername_textview);
            handler.mobilenumber=(TextView)row.findViewById(R.id.mobilenumber_textview);
            handler.emailid=(TextView)row.findViewById(R.id.emailid_textview);
            handler.terminalid=(TextView)row.findViewById(R.id.terminalid_textview);
            row.setTag(handler);


        }
        else
        {
            handler= (DataHandler)row.getTag();
        }

        TerminalDataProvider terminalDataProvider;
        terminalDataProvider=(TerminalDataProvider)this.getItem(position);
        handler.cashiername.setText(terminalDataProvider.getCashiername());
        handler.mobilenumber.setText(terminalDataProvider.getMobilenumber());
        handler.emailid.setText(terminalDataProvider.getEmailid());
        handler.terminalid.setText(terminalDataProvider.getTerminalid());
        return row;






    }

    @Override
    public int getCount()
    {
        return this.list.size();
    }



}