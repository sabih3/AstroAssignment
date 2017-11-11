package ahmed.sabih.com.astroassignment.network;

import ahmed.sabih.com.astroassignment.models.ChannelDescResponse;
import ahmed.sabih.com.astroassignment.models.ChannelListResponse;
import ahmed.sabih.com.astroassignment.models.EventsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by sabih on 31-Oct-17.
 * ahmed.engr90@gmail.com
 */

public interface ServicesInterface {

    @GET(Endpoints.CHANNELS_LIST)
    Call<ChannelListResponse> getChannelsList();

    @GET(Endpoints.CHANNEL_DESC)
    Call<ChannelDescResponse> getChannels();


    @GET(Endpoints.EVENTS)
    Call<EventsResponse> getEvents(@Query("periodStart")String periodStart,
                                   @Query("periodEnd")String periodEnd, @Query("channelId")String channelId);
}
