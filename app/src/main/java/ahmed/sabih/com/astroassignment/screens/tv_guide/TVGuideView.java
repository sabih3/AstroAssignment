package ahmed.sabih.com.astroassignment.screens.tv_guide;

import ahmed.sabih.com.astroassignment.models.EventsResponse;
import retrofit2.Response;

/**
 * Created by sabih on 05-Nov-17.
 */

public interface TVGuideView {
    void onEventsDataFetched(Response<EventsResponse> response);
}
