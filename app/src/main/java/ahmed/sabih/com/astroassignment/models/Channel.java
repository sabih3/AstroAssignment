package ahmed.sabih.com.astroassignment.models;

import android.support.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by sabih on 03-Nov-17.
 */

@DatabaseTable(tableName = Channel.Columns.TABLENAME_FAVOURITES)
public class Channel implements Comparable, Serializable {

//    @DatabaseField(id = true,unique = true, columnName = Columns.FAVOURITE_ID)
//    public int favouriteID;

    @DatabaseField(id = true,unique = true,columnName = Columns.CHANNEL_ID)
    public int channelId;

    @DatabaseField(columnName = Columns.CHANNEL_NAME)
    public String channelTitle;

    @DatabaseField(columnName = Columns.CHANNEL_NUMBER)
    public int channelStbNumber;


    public int getChannelId() {
        return channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public int getChannelStbNumber() {
        return channelStbNumber;
    }


    @Override
    public int compareTo(@NonNull Object o) {

        return ((Channel) o).getChannelTitle().compareTo(this.getChannelTitle());
    }

    public interface Columns{
        String TABLENAME_FAVOURITES = "favourite_channels";
        String CHANNEL_ID="channel_id";
        String CHANNEL_NAME = "channel_name";
        String CHANNEL_NUMBER = "channel_number";

    }
}
