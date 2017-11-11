package ahmed.sabih.com.astroassignment.network.bals;

import android.content.Context;

import ahmed.sabih.com.astroassignment.network.RestClient;
import ahmed.sabih.com.astroassignment.models.EventsResponse;
import ahmed.sabih.com.astroassignment.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sabih on 05-Nov-17.
 */

public class TVGuideBAL {

    public static void fetchEvents(final Context context, String periodStart,
                                   String periodEnd, String channelIDs,
                                   final TVGuideListener tvGuideListener) {

        Call<EventsResponse> eventsRequest = RestClient.getAdapter().getEvents(periodStart, periodEnd, channelIDs);

        eventsRequest.enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                if(response.body() != null){
                    tvGuideListener.onEventsFetched(response);
                }else{
                    tvGuideListener.onException();
                }

            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {
                String resolvedError = ErrorUtils.getResolvedError(context, t);
                tvGuideListener.onFailure(resolvedError);
            }
        });

    }

    public interface TVGuideListener{
        void onEventsFetched(Response<EventsResponse> eventsResponse);
        void onException();
        void onFailure(String resolvedError);
    }
}
