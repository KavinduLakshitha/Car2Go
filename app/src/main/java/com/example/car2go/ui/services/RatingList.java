package com.example.car2go.ui.services;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.car2go.R;

import java.util.List;

public class RatingList extends ArrayAdapter<Rating> {
    private Activity context;
    private List<Rating> ratingList;
    public RatingList(Activity context, List<Rating>ratingList){
        super(context, R.layout.list_rating);
        this.context = context;
        this.ratingList =ratingList;




    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_rating, null, true);
        TextView textViewCusID = (TextView) listViewItem.findViewById(R.id.textViewCusID);
        TextView textViewCusName = (TextView) listViewItem.findViewById(R.id.textViewCusName);
        TextView textViewRate = (TextView) listViewItem.findViewById(R.id.textViewRate);
        TextView textViewReview = (TextView) listViewItem.findViewById(R.id.textViewReview);
        Rating rating = ratingList.get(position);

        textViewCusID.setText(rating.getCustomerID());
        textViewCusName.setText(rating.getCustomerName());
        textViewCusID.setText(rating.getRate());
        textViewCusID.setText(rating.getReview());
        return listViewItem;
    }
}
