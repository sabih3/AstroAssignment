package ahmed.sabih.com.astroassignment.screens.channel_list;

import java.util.ArrayList;

import ahmed.sabih.com.astroassignment.models.Channel;

/**
 * Created by sabih on 01-Nov-17.
 */

public interface ChannelListScreenView {


    void onListDataReceived(ArrayList<Channel> channelListData);

    void showProgress();

    void showEmptyListView();

    void showException(String resolvedErrorMessage);
}
