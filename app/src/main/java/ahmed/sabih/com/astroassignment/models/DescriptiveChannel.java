package ahmed.sabih.com.astroassignment.models;

import android.support.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import static ahmed.sabih.com.astroassignment.models.DescriptiveChannel.Columns.*;

/**
 * Created by sabih on 06-Nov-17.
 */

@DatabaseTable(tableName = DescriptiveChannel.Columns.TABLE_NAME)
public class DescriptiveChannel implements Serializable,Comparable{

    @DatabaseField(id=true,unique = true, columnName = CHANNEL_ID)
    private int channelId;

    private String siChannelId;

    @DatabaseField(columnName = Columns.CHANNEL_NAME)
    private String channelTitle;

    private String channelDescription;
    private String channelLanguage;
    private String channelColor1;
    private String channelColor2;
    private String channelColor3;
    private String channelCategory;

    @DatabaseField(columnName = Columns.CHANNEL_NUMBER)
    private String channelStbNumber;

    private boolean channelHD;
    private int hdSimulcastChannel;
    private String channelStartDate;
    private String channelEndDate;
    private ArrayList<ChannelExtRef> channelExtRef;
    private ArrayList<LinearOttMapping> linearOttMapping;

    @DatabaseField(columnName = Columns.CHANNEL_LOGO)
    public String channelLogo;

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

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public void setChannelStbNumber(String channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public void setChannelLogo(String channelLogo) {
        this.channelLogo = channelLogo;
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }


    public class ChannelExtRef {
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

    public interface Columns{
        String TABLE_NAME = "CHANNELS";
        String CHANNEL_ID ="channel_id";
        String CHANNEL_NAME = "name";
        String CHANNEL_NUMBER = "channel_number";
        String CHANNEL_LOGO = "logo";


    }
}
