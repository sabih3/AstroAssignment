package ahmed.sabih.com.astroassignment.db;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ahmed.sabih.com.astroassignment.adapters.ChannelsListAdapter;
import ahmed.sabih.com.astroassignment.models.Channel;

/**
 * Created by sabih on 03-Nov-17.
 * ahmed.engr90@gmail.com
 */

public class FavoritesDataManager {

    private static String TAG = "FavoritesDataManager";

    /**Method to get favourite channels
     *
     * @return
     */
    public static List<Channel> getAllFavourites() {
        List<Channel> favChannelsList = new ArrayList<>();
        try {
            favChannelsList = DBManager.getInstance().getDBHelper().getChannelDAO().queryForAll();
        } catch (SQLException e) {
            Log.e(TAG,"Error querying Favourites table");
        }

        return favChannelsList;
    }

    /**Method to check if passed channel already exists in favourites
     *
     * @param channel
     * @return
     */
    public static boolean checkIfChannelIsFavourite(Channel channel) {
        Channel existingChannel = null;
        try {
            existingChannel = DBManager.getInstance().getDBHelper().getChannelDAO().queryForSameId(channel);

        } catch (SQLException e) {
            Log.e(TAG,"Error querying Favourites table");
        }

        return existingChannel == null ? false: true;
    }

    /**Removes channel from favourite if exists
     *
     * @param channel
     */
    public static void removeChannelAsFavourite(Channel channel) {
        try {
            DBManager.getInstance().getDBHelper().getChannelDAO().delete(channel);
        } catch (SQLException e) {
            Log.e(TAG,"Error in Deleting Fav Channel");
        }
    }

    /**Sets channel as favourite
     *
     * @param channel
     */
    public static void setChannelAsFavourite(Channel channel) {
        try {
            DBManager.getInstance().getDBHelper().getChannelDAO().createOrUpdate(channel);
        } catch (SQLException e) {
            Log.e(TAG,"Error in setting channel as Fav");
        }
    }
}
