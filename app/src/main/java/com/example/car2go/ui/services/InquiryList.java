package com.example.car2go.ui.services;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.car2go.R;

import java.util.List;

public class InquiryList extends ArrayAdapter<Inquiry> {
    private Activity context;
    private List<Inquiry> inquiryList;
    public InquiryList(Activity context, List<Inquiry> inquiryList){
        super(context, R.layout.inquiry_list, inquiryList);
        this.context = context;
        this.inquiryList = inquiryList;

    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.inquiry_list,null,true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewNIC = (TextView) listViewItem.findViewById(R.id.textViewNIC);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewPhone = (TextView) listViewItem.findViewById(R.id.textViewPhone);
        TextView textViewInquiry = (TextView) listViewItem.findViewById(R.id.textViewInquiry);


        Inquiry inquiry = inquiryList.get(position);
        textViewName.setText(inquiry.getCustomerName());
        textViewNIC.setText(inquiry.getCustomerNIC());
        textViewEmail.setText(inquiry.getCustomerEmail());
        textViewPhone.setText(inquiry.getCustomerPhone());
        textViewInquiry.setText(inquiry.getCustomerInquiry());
        return listViewItem;

    }
}
