package com.example.car2go.ui.rentCar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.car2go.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Car> {

    public ListAdapter (Context context, ArrayList<Car> carArrayList){

        super(context, R.layout.list_item, carArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Car car = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.image);
        TextView name = convertView.findViewById(R.id.name);
        TextView cost = convertView.findViewById(R.id.cost);
        TextView color = convertView.findViewById(R.id.color);

        imageView.setImageResource(car.imageId);
        name.setText(car.name);
        cost.setText(car.cost);
        color.setText(car.color);

        return convertView;
    }
}
