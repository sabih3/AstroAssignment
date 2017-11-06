package ahmed.sabih.com.astroassignment.screens.tv_guide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ahmed.sabih.com.astroassignment.network.bals.TVGuideBAL;
import ahmed.sabih.com.astroassignment.models.EventsResponse;
import retrofit2.Response;

/**
 * Created by sabih on 05-Nov-17.
 */

public class TVGuidePresenter {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static String TIME_FORTMAT="HH:mm";
    private final TVGuideView tvGuideView;


    public TVGuidePresenter(TVGuideView tvGuideView) {
        this.tvGuideView = tvGuideView;
    }

    public void getEvents(String channelIDs) {
        Date date = new Date();

        String periodDate = new SimpleDateFormat(DATE_FORMAT).format(date);

        String currentTime = new SimpleDateFormat(TIME_FORTMAT).format(date);


        String endTime = "23:59";

        String periodStart = periodDate +" " +"00:00";

        String periodEnd = periodDate +" "+ endTime;



        TVGuideBAL.fetchEvents(periodStart,periodEnd,channelIDs, new TVGuideBAL.TVGuideListener() {
            @Override
            public void onEventsFetched(Response<EventsResponse> response) {
                tvGuideView.onEventsDataFetched(response);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
