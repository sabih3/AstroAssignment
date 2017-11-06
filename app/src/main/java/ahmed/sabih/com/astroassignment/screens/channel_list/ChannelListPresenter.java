package ahmed.sabih.com.astroassignment.screens.channel_list;

import android.content.Context;

import java.util.ArrayList;

import ahmed.sabih.com.astroassignment.models.Channel;
import ahmed.sabih.com.astroassignment.network.bals.ChannelListBAL;

/**
 * Created by sabih on 01-Nov-17.
 */

public class ChannelListPresenter {

    private final ChannelListScreenView view;
    private final Context mContext;

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
