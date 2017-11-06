package ahmed.sabih.com.astroassignment.network.bals;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import ahmed.sabih.com.astroassignment.models.Channel;
import ahmed.sabih.com.astroassignment.models.ChannelListResponse;
import ahmed.sabih.com.astroassignment.network.RestClient;
import ahmed.sabih.com.astroassignment.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sabih on 01-Nov-17.
 */

public class ChannelListBAL {
    //original call
    private static Call<ChannelListResponse> channelListRequest = RestClient.getAdapter().getChannelsList();

    public static void getChannels(final Context context, final ChannelListListener channelListListener) {

        //cloning original request call, else it will give already executed exception
        channelListRequest = channelListRequest.clone();

        channelListRequest.enqueue(new Callback<ChannelListResponse>() {
                @Override
                public void onResponse(Call<ChannelListResponse> call, Response<ChannelListResponse> response) {
                    channelListListener.onListReceived(response.body().getChannels());
                }

                @Override
                public void onFailure(Call<ChannelListResponse> call, Throwable throwable) {
                    if(channelListRequest.isCanceled()){
                        Log.d("","Request cancelled");
                    }else{
                        String resolvedError = ErrorUtils.getResolvedError(context, throwable);
                        channelListListener.onException(resolvedError);
                    }

                }
            });

    }




    public static void cancelRequest() {
        channelListRequest.cancel();

    }


    public interface ChannelListListener{
        void onListReceived(ArrayList<Channel> response);
        void onException(String resolvedError);
    }
}
