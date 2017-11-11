package ahmed.sabih.com.astroassignment.utils;

import java.util.Comparator;

import ahmed.sabih.com.astroassignment.models.Channel;
import ahmed.sabih.com.astroassignment.models.DescriptiveChannel;

/**
 * Created by sabih on 02-Nov-17.
 */

public class CompareUtils {

    public static Comparator<Channel> channelNameComparator = new Comparator<Channel>() {
        @Override
        public int compare(Channel channel1, Channel channel2) {
            String channel1Name = channel1.getChannelTitle().toUpperCase();
            String channel2Name = channel2.getChannelTitle().toUpperCase();


            return channel1Name.compareTo(channel2Name);
        }
    };


    public static Comparator<Channel> channelNumberComparator = new Comparator<Channel>() {
        @Override
        public int compare(Channel channel, Channel channel2) {

            int channelStbNumber1 = channel.getChannelStbNumber();
            int channelStbNumber2 = channel2.getChannelStbNumber();

            return channelStbNumber1 - channelStbNumber2;
        }
    };


    public static Comparator<DescriptiveChannel> channelDescNumberComparator = new Comparator<DescriptiveChannel>() {
        @Override
        public int compare(DescriptiveChannel channel, DescriptiveChannel channel2) {

            int channelStbNumber1 = Integer.parseInt(channel.getChannelStbNumber());
            int channelStbNumber2 = Integer.parseInt(channel2.getChannelStbNumber());

            return channelStbNumber1 - channelStbNumber2;
        }
    };
}
