package ahmed.sabih.com.astroassignment.screens.channel_list;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

import ahmed.sabih.com.astroassignment.db.ChannelDataManager;
import ahmed.sabih.com.astroassignment.db.DBManager;
import ahmed.sabih.com.astroassignment.models.Channel;
import ahmed.sabih.com.astroassignment.models.ChannelDescResponse;
import ahmed.sabih.com.astroassignment.models.DescriptiveChannel;
import ahmed.sabih.com.astroassignment.network.RestClient;
import ahmed.sabih.com.astroassignment.network.bals.ChannelListBAL;
import ahmed.sabih.com.astroassignment.utils.CompareUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sabih on 01-Nov-17.
 */

public class ChannelListPresenter {

    private final ChannelListScreenView view;
    private final Context mContext;
    private ArrayList<DescriptiveChannel> channelDescList;

    public ChannelListPresenter(Context context, ChannelListScreenView listScreenView) {
        this.mContext = context;
        this.view = listScreenView;
    }

    public void fetchChannelsList() {
        ChannelListBAL.getChannels(mContext,new ChannelListBAL.ChannelListListener() {
            @Override
            public void onListReceived(ArrayList<Channel> channelsList) {
                view.onListDataReceived(channelsList);
            }

            @Override
            public void onException(String resolvedError) {
                view.showException(resolvedError);
            }
        });
    }




    public void destroyNetworkCall() {
        ChannelListBAL.cancelRequest();
    }
}
