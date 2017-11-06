package ahmed.sabih.com.astroassignment.models;

import java.util.ArrayList;

/**
 * Created by sabih on 06-Nov-17.
 */

public class ChannelDescResponse {
    public String responseCode;
    public String responseMessage;
    public ArrayList<Channel> channel;

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public ArrayList<Channel> getChannel() {
        return channel;
    }

    public class Channel
    {
        public int channelId;
        public String siChannelId;
        public String channelTitle;
        public String channelDescription;
        public String channelLanguage;
        public String channelColor1;
        public String channelColor2;
        public String channelColor3;
        public String channelCategory;
        public String channelStbNumber;
        public boolean channelHD;
        public int hdSimulcastChannel;
        public String channelStartDate;
        public String channelEndDate;
        public ArrayList<ChannelExtRef> channelExtRef;
        public ArrayList<LinearOttMapping> linearOttMapping;

        public int getChannelId() {
            return channelId;
        }

        public String getSiChannelId() {
            return siChannelId;
        }

        public String getChannelTitle() {
            return channelTitle;
        }

        public String getChannelDescription() {
            return channelDescription;
        }

        public String getChannelLanguage() {
            return channelLanguage;
        }

        public String getChannelColor1() {
            return channelColor1;
        }

        public String getChannelColor2() {
            return channelColor2;
        }

        public String getChannelColor3() {
            return channelColor3;
        }

        public String getChannelCategory() {
            return channelCategory;
        }

        public String getChannelStbNumber() {
            return channelStbNumber;
        }

        public boolean isChannelHD() {
            return channelHD;
        }

        public int getHdSimulcastChannel() {
            return hdSimulcastChannel;
        }

        public String getChannelStartDate() {
            return channelStartDate;
        }

        public String getChannelEndDate() {
            return channelEndDate;
        }

        public ArrayList<ChannelExtRef> getChannelExtRef() {
            return channelExtRef;
        }

        public ArrayList<LinearOttMapping> getLinearOttMapping() {
            return linearOttMapping;
        }

        private class ChannelExtRef {
            public String system;
            public String subSystem;
            public String value;

            public String getSystem() {
                return system;
            }

            public String getSubSystem() {
                return subSystem;
            }

            public String getValue() {
                return value;
            }
        }

        private class LinearOttMapping {
            public String platform;
        }
    }
}
