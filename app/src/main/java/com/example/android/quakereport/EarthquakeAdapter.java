package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.android.quakereport.R.id.magnitude;


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    private static final String LOCATION_SEPARATOR = " of ";


    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }


    @NonNull
    @Override
    /**Check if there is an existing list item view (called convertView) that we can reuse,
     otherwise, if convertView is null, then inflate a new list item layout.*/
    public View getView(int position, View contentView, @NonNull ViewGroup parent) {

        View listItemView = contentView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }


        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = listItemView.findViewById(magnitude);


        double formattedMagnitude = formatMagnitude(currentEarthquake != null ? currentEarthquake.getmMagnitude() : 0);

        magnitudeView.setText((int) formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        int magnitudeColor = getMagnitudeColor(currentEarthquake != null ? currentEarthquake.getmMagnitude() : 0);

        magnitudeCircle.setColor(magnitudeColor);


        String originalLocation = currentEarthquake != null ? currentEarthquake.getmLocation() : null;

        String primaryLocation;
        String locationOffset;


        if (originalLocation != null && originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);

            primaryLocation = originalLocation;
        }


        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);

        primaryLocationView.setText(primaryLocation);


        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);

        locationOffsetView.setText(locationOffset);


        Date dateObject = new Date(currentEarthquake != null ? currentEarthquake.getTimeInMilliseconds() : 0);

        TextView dateView =  listItemView.findViewById(R.id.date);

        String formattedDate = formatDate(dateObject);

        dateView.setText(formattedDate);

        TextView timeView =  listItemView.findViewById(R.id.time);

        String formattedTime = formatTime(dateObject);

        timeView.setText(formattedTime);


        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(Double.parseDouble(String.valueOf(magnitude)));
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


    private double formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.00");
        return Double.parseDouble(magnitudeFormat.format(magnitude));
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}