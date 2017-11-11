package ahmed.sabih.com.astroassignment.models;

import java.util.ArrayList;

/**
 * Created by sabih on 06-Nov-17.
 */

public class ChannelDescResponse {
    private String responseCode;
    private String responseMessage;
    private ArrayList<DescriptiveChannel> channel;

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public ArrayList<DescriptiveChannel> getChannel() {
        return channel;
    }

}
