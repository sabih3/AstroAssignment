package ahmed.sabih.com.astroassignment.screens.tv_guide;

import java.util.List;

import ahmed.sabih.com.astroassignment.models.DescriptiveChannel;
import ahmed.sabih.com.astroassignment.models.EventsResponse;
import retrofit2.Response;

/**
 * Created by sabih on 05-Nov-17.
 */

public interface TVGuideView {
    void onEventsDataFetched(List<DescriptiveChannel> allChannels,
                             Response<EventsResponse> response);

    void onFailure(String resolvedError);

    void showProgress();

    void hideProgress();
}
