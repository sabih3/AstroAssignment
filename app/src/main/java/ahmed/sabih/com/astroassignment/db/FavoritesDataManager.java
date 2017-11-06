package ahmed.sabih.com.astroassignment.db;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ahmed.sabih.com.astroassignment.adapters.ChannelsListAdapter;
import ahmed.sabih.com.astroassignment.models.Channel;

/**
 * Created by sabih on 03-Nov-17.
 */

public class FavoritesDataManager {

    private static String TAG = "FavoritesDataManager";

    public static List<Channel> getAllFavourites() {
        List<Channel> favChannelsList = new ArrayList<>();
        try {
            favChannelsList = DBManager.getInstance().getDBHelper().getChannelDAO().queryForAll();
        } catch (SQLException e) {
            Log.e(TAG,"Error querying Favourites table");
        }

        return favChannelsList;
    }

    public static boolean checkIfChannelIsFavourite(Channel channel) {
        Channel existingChannel = null;
        try {
            existingChannel = DBManager.getInstance().getDBHelper().getChannelDAO().queryForSameId(channel);

        } catch (SQLException e) {
            Log.e(TAG,"Error querying Favourites table");
        }

        return existingChannel == null ? false: true;
    }

    public static void removeChannelAsFavourite(Channel channel) {
        try {
            DBManager.getInstance().getDBHelper().getChannelDAO().delete(channel);
        } catch (SQLException e) {
            Log.e(TAG,"Error in Deleting Fav Channel");
        }
    }

    public static void setChannelAsFavourite(Channel channel) {
        try {
            DBManager.getInstance().getDBHelper().getChannelDAO().createOrUpdate(channel);
        } catch (SQLException e) {
            Log.e(TAG,"Error in setting channel as Fav");
        }
    }
}
