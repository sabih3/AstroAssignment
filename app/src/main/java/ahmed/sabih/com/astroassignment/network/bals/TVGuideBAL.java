package ahmed.sabih.com.astroassignment.network.bals;

import ahmed.sabih.com.astroassignment.network.RestClient;
import ahmed.sabih.com.astroassignment.models.EventsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sabih on 05-Nov-17.
 */

public class TVGuideBAL {

    public static void fetchEvents(String periodStart, String periodEnd, String channelIDs, final TVGuideListener tvGuideListener) {

        Call<EventsResponse> eventsRequest = RestClient.getAdapter().getEvents(periodStart, periodEnd, channelIDs);

        eventsRequest.enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                tvGuideListener.onEventsFetched(response);
            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {
                tvGuideListener.onFailure();
            }
        });

    }

    public interface TVGuideListener{
        void onEventsFetched(Response<EventsResponse> eventsResponse);
        void onFailure();
    }
}
