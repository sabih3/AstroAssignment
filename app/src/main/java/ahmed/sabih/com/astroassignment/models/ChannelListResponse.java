package ahmed.sabih.com.astroassignment.models;

import java.util.ArrayList;

/**
 * Created by sabih on 31-Oct-17.
 * ahmed.engr90@gmail.com
 */

public class ChannelListResponse extends GeneralResponse{

    public ArrayList<Channel> channels;

    public ArrayList<Channel> getChannels() {
        return channels;
    }

}
