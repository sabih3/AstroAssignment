package ahmed.sabih.com.astroassignment.screens.tv_guide;

import android.content.Context;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ahmed.sabih.com.astroassignment.db.ChannelDataManager;
import ahmed.sabih.com.astroassignment.models.ChannelDescResponse;
import ahmed.sabih.com.astroassignment.models.DescriptiveChannel;
import ahmed.sabih.com.astroassignment.network.RestClient;
import ahmed.sabih.com.astroassignment.network.bals.TVGuideBAL;
import ahmed.sabih.com.astroassignment.models.EventsResponse;
import ahmed.sabih.com.astroassignment.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sabih on 05-Nov-17.
 */

public class TVGuidePresenter {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static String TIME_FORTMAT="HH:mm";

    private final TVGuideView tvGuideView;
    private final Context mContext;

    public TVGuidePresenter(Context context, TVGuideView tvGuideView) {
        this.mContext = context;
        this.tvGuideView = tvGuideView;
    }

    public void getEvents() {

        //Todo:Refactor to method block
        Date date = new Date();

        String periodDate = new SimpleDateFormat(DATE_FORMAT).format(date);

        String currentTime = new SimpleDateFormat(TIME_FORTMAT).format(date);


        String endTime = "23:59";

        String periodStart = periodDate +" " +"00:00";

        String periodEnd = periodDate +" "+ endTime;
        //Refactor Block end

        if(!ChannelDataManager.getAllChannels().isEmpty()){
            final List<DescriptiveChannel> allChannels = ChannelDataManager.getAllPaginatedChannels();
            ArrayList<String> channelIDsList = new ArrayList<>();


            for(DescriptiveChannel channel : allChannels){
                if(channel.getChannelId()!= 0){
                    channelIDsList.add(String.valueOf(channel.getChannelId()));
                }

            }

            String channelIDs = TextUtils.join(",", channelIDsList);

            tvGuideView.showProgress();
            //Todo: show progress here with message that fetching channels events
            TVGuideBAL.fetchEvents(mContext,periodStart,periodEnd,channelIDs, new TVGuideBAL.TVGuideListener() {
                @Override
                public void onEventsFetched(Response<EventsResponse> response) {
                    tvGuideView.hideProgress();
                    tvGuideView.onEventsDataFetched(allChannels,response);
                }

                @Override
                public void onException() {
                    tvGuideView.hideProgress();
                }

                @Override
                public void onFailure(String resolvedError) {
                    tvGuideView.hideProgress();
                    tvGuideView.onFailure(resolvedError);
                }
            });
        }else{
            //Todo: show progress here with message that preparing channels for events
            tvGuideView.showProgress();
            fetchChannelDesc();

        }
    }

    private void fetchChannelDesc(){


        Call<ChannelDescResponse> request = RestClient.getAdapter().getChannels();

        request.enqueue(new Callback<ChannelDescResponse>() {
            @Override
            public void onResponse(Call<ChannelDescResponse> call, Response<ChannelDescResponse> response) {
                ArrayList<DescriptiveChannel> channelDescList = new ArrayList<>();
                channelDescList = response.body().getChannel();

                ChannelDataManager.persistChannels(channelDescList);
                getEvents();

            }

            @Override
            public void onFailure(Call<ChannelDescResponse> call, Throwable t) {
                tvGuideView.hideProgress();
                String resolvedError = ErrorUtils.getResolvedError(mContext, t);
                tvGuideView.onFailure(resolvedError);
            }
        });




    }
}
