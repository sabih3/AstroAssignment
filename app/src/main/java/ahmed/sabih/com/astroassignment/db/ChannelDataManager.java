package ahmed.sabih.com.astroassignment.db;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ahmed.sabih.com.astroassignment.models.DescriptiveChannel;

/**
 * Created by sabih on 07-Nov-17.
 * ahmed.engr90@gmail.com
 */

public class ChannelDataManager {

    public static String TAG = "ChannelDataManager";

    /**Method to Persist channels in db
     *
     * @param channelDescList
     */
    public static void persistChannels(ArrayList<DescriptiveChannel> channelDescList) {

        for(DescriptiveChannel channel: channelDescList) {
            if(channel.getChannelId()!=0){
                DescriptiveChannel channelToBeInserted = new DescriptiveChannel();

                channelToBeInserted.setChannelId(channel.getChannelId());
                channelToBeInserted.setChannelTitle(channel.getChannelTitle());
                channelToBeInserted.setChannelStbNumber(channel.getChannelStbNumber());
                channelToBeInserted.setChannelLogo(channel.getChannelLogo());
                try {
                    String logoURL = channel.getChannelExtRef().get(14).getValue();
                    channelToBeInserted.setChannelLogo(logoURL);
                }catch (Exception exc){
                    channelToBeInserted.setChannelLogo("");
                }

                try {
                    DBManager.getInstance().getDBHelper().getDescChannelDao().create(channelToBeInserted);
                } catch (SQLException e) {
                    Log.e(TAG, "Error in persisting descriptive channel");
                }

            }
        }

    }

    /**Method to return all persisted channels
     *
     * @return
     */
    public static List<DescriptiveChannel> getAllChannels() {
        List<DescriptiveChannel> descChannelsList = new ArrayList<>();

        try {
            descChannelsList = DBManager.getInstance().getDBHelper().getDescChannelDao().queryForAll();
        } catch (SQLException e) {
            Log.e(TAG,"Problem occured while fetching persisted Desc channels");
        }

        return descChannelsList;
    }

    /**Method to get certain number of channels
     *
     * @return
     */
    public static List<DescriptiveChannel> getPaginatedChannels() {
        List<DescriptiveChannel> records = new ArrayList<>();
        Dao<DescriptiveChannel, Integer> descChannelDao = DBManager.getInstance().getDBHelper().getDescChannelDao();
        QueryBuilder<DescriptiveChannel,Integer > queryBuilder =descChannelDao.queryBuilder();
        queryBuilder.limit(20);
        queryBuilder.orderBy(DescriptiveChannel.Columns.CHANNEL_NUMBER,true);
        try {
            records = descChannelDao.query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return records;
    }
}
