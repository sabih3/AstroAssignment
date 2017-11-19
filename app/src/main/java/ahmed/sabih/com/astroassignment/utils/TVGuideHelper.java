package ahmed.sabih.com.astroassignment.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ahmed.sabih.com.astroassignment.R;
import ahmed.sabih.com.astroassignment.models.DescriptiveChannel;
import ahmed.sabih.com.astroassignment.models.EventsResponse;

/**
 * Created by sabih on 11-Nov-17.
 */

public class TVGuideHelper {

    public static final int HEIGHT_CONSISTENT_PX = 280;
    public static final int HEIGHT_CHANNEL_NAME_PX = HEIGHT_CONSISTENT_PX;
    public static final int HEIGHT_EVENT_ROW_PX = HEIGHT_CHANNEL_NAME_PX;
    public static final int WIDTH_TIME_SLOT_PX = 600;
    public static ArrayList<EventsResponse.Getevent> getEventsForParticularChannel(DescriptiveChannel channel,
                                                                             List<EventsResponse.Getevent> eventsList) {
        ArrayList<EventsResponse.Getevent> sortedEvents = new ArrayList<>();
        for(EventsResponse.Getevent event : eventsList){
            if(channel.getChannelId()==event.getChannelId()){
                sortedEvents.add(event);
            }
        }

        return sortedEvents;
    }

    public static int getScrollPosition() {
        Date date = new Date();
        String currentTime = new java.text.SimpleDateFormat("HH:mm").format(date);
        String[] timeArray = currentTime.split(":");

        int currentHour = Integer.parseInt(timeArray[0]);
        int currentMins= Integer.parseInt((timeArray[1]));

        int scrollPosition = 0;

        if(currentMins < 30){
            scrollPosition = (currentHour * 600);
        }else{
            scrollPosition = (currentHour * 600)+(currentMins*10);
        }

        return scrollPosition;
    }

    public static int getWidthOfEvent(EventsResponse.Getevent event) {
        String displayDuration = event.getDisplayDuration();
        String[] tokens = new String[3];
        tokens = displayDuration.split(":");
        int hrs = Integer.parseInt(tokens[0]);
        int mins = Integer.parseInt(tokens[1]);

        int durationInMins = (60*hrs)+mins;
        int widthOfEvent = durationInMins * 10;
        return widthOfEvent;
    }

    public static LinearLayout getEventsRow(Context context,
                                            ArrayList<EventsResponse.Getevent> eventsList) {

        LinearLayout eventRowLayout = new LinearLayout(context);
        eventRowLayout.removeAllViews();
        eventRowLayout.setOrientation(LinearLayout.HORIZONTAL);

        for(int i=0; i<eventsList.size(); i++){
            EventsResponse.Getevent event = eventsList.get(i);

            if(i==0){
                //check the first event always, as 1st event is sometimes not starting from 00:00
                //which makes the guide not accurate

                if(!getTime(event.getDisplayDateTime()).equals("00:00")){

                    // calculate the difference in minutes
                    // create width of row = minutes times 10 ; (as 1 minute = 10px)
                    // add row, thats it
                    String time = getTime(event.getDisplayDateTime());
                    String[] actualTime = time.split(":");
                    int hrs = Integer.parseInt(actualTime[0]);
                    int mins = Integer.parseInt(actualTime[1]);

                    int durationInMins = (60*hrs)+mins;
                    int widthOfEmptySlot = durationInMins * 10;
                    String programmeTitleEmpty = "";
                    String displayTimeEmpty = "";


                    View view = LayoutInflater.from(context).inflate(R.layout.tv_event_name, null, false);
                    TextView tvEventName = view.findViewById(R.id.tv_event_name);

                    tvEventName.setHeight(HEIGHT_EVENT_ROW_PX);
                    tvEventName.setWidth(widthOfEmptySlot);
                    tvEventName.setMaxWidth(widthOfEmptySlot);
                    tvEventName.setBackground(ContextCompat.getDrawable(context, R.drawable.border_tv_guide));

                    tvEventName.setText(programmeTitleEmpty+"\n"
                            +displayTimeEmpty);
                    eventRowLayout.addView(tvEventName);


                    String programmeTitle = event.getProgrammeTitle();
                    String displayTime = getFormattedTime(event.getDisplayDateTime());
                    int widthOfEvent = getWidthOfEvent(event);

                    View actualLayout = LayoutInflater.from(context).inflate(R.layout.tv_event_name, null, false);
                    TextView tvEventNameActual = actualLayout.findViewById(R.id.tv_event_name);

                    tvEventNameActual.setHeight(HEIGHT_EVENT_ROW_PX);
                    tvEventNameActual.setWidth(widthOfEvent);
                    tvEventNameActual.setMaxWidth(widthOfEvent);
                    tvEventNameActual.setBackground(ContextCompat.getDrawable(context, R.drawable.border_tv_guide));

                    tvEventNameActual.setText(programmeTitle+"\n"
                            +displayTime);


                    eventRowLayout.addView(tvEventNameActual);
                }else{
                    // do normal work
                    String programmeTitle = event.getProgrammeTitle();
                    String displayTime = getFormattedTime(event.getDisplayDateTime());
                    int widthOfEvent = getWidthOfEvent(event);

                    View view = LayoutInflater.from(context).inflate(R.layout.tv_event_name, null, false);
                    TextView tvEventName = view.findViewById(R.id.tv_event_name);

                    tvEventName.setHeight(HEIGHT_EVENT_ROW_PX);
                    tvEventName.setWidth(widthOfEvent);
                    tvEventName.setMaxWidth(widthOfEvent);
                    tvEventName.setBackground(ContextCompat.getDrawable(context, R.drawable.border_tv_guide));

                    tvEventName.setText(programmeTitle+"\n"
                            +displayTime);



                    eventRowLayout.addView(tvEventName);
                }
            }else{
                //do work normally
                String programmeTitle = event.getProgrammeTitle();
                String displayTime = getFormattedTime(event.getDisplayDateTime());
                int widthOfEvent = getWidthOfEvent(event);

                View view = LayoutInflater.from(context).inflate(R.layout.tv_event_name, null, false);
                TextView tvEventName = view.findViewById(R.id.tv_event_name);

                tvEventName.setHeight(HEIGHT_EVENT_ROW_PX);
                tvEventName.setWidth(widthOfEvent);
                tvEventName.setMaxWidth(widthOfEvent);
                tvEventName.setBackground(ContextCompat.getDrawable(context, R.drawable.border_tv_guide));

                tvEventName.setText(programmeTitle+"\n"
                        +displayTime);



                eventRowLayout.addView(tvEventName);
            }

        }

        return eventRowLayout;
    }

    private static String getFormattedTime(String displayDateTime) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat requiredFormat = new SimpleDateFormat("h:mm a");
        String time = "";
        try {
            Date date = inputDateFormat.parse(displayDateTime);
            time = requiredFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return time;
    }

    private static String getTime(String displayDateTime) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat requiredFormat = new SimpleDateFormat("HH:mm");
        String time = "";
        try {
            Date date = inputDateFormat.parse(displayDateTime);
            time = requiredFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return time;
    }

    public static View getChannelNameRow(Context context, DescriptiveChannel channel,
                                         LinearLayout channelListLayout) {
        View row = LayoutInflater.from(context).inflate(R.layout.row_channel_name, null, false);
        LinearLayout rowParent = (LinearLayout) row.findViewById(R.id.channel_name_parent);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(channelListLayout.getWidth(),HEIGHT_CHANNEL_NAME_PX);
        rowParent.setLayoutParams(params);

        ImageView imageView = (ImageView) row.findViewById(R.id.channel_logo);

        TextView tvChannelName = (TextView)row.findViewById(R.id.tvGuide_channel_name);

        Picasso.with(context).load(channel.getChannelLogo()).into(imageView);

        //TextView tvChannelName = new TextView(context);
        tvChannelName.setText(channel.getChannelTitle()+"\n"+
                              "CH "+channel.getChannelStbNumber());
        tvChannelName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        tvChannelName.setWidth(channelListLayout.getWidth());
        //tvChannelName.setHeight(HEIGHT_CHANNEL_NAME_PX);

        row.setBackground(ContextCompat.getDrawable(context,R.drawable.border_tv_guide));

    return row;
    }
}
