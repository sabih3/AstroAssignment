package ahmed.sabih.com.astroassignment.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ahmed.sabih.com.astroassignment.models.Channel;
import ahmed.sabih.com.astroassignment.models.DescriptiveChannel;

/**
 * Created by sabih on 03-Nov-17.
 * ahmed.engr90@gmail.com
 */

public class DBHelper extends OrmLiteSqliteOpenHelper{

    private static String TAG = DBHelper.class.getSimpleName();
    private static String DATABASE_NAME = "astro-assignment";
    private static int DATABASE_VERSION = 4;
    private Dao<Channel,Integer> channelDAO;
    private Dao<DescriptiveChannel,Integer> descriptiveChannelDAO;

    private static final Class<?> [] TABLES = {
            Channel.class,
            DescriptiveChannel.class
    };


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,TABLES[0]);
            TableUtils.createTable(connectionSource,TABLES[1]);
        }catch (SQLException exc){
            Log.e(TAG,"Error in table creation");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            try {
                TableUtils.dropTable(connectionSource,TABLES[0],false);
                TableUtils.dropTable(connectionSource,TABLES[1],false);

            } catch (SQLException exc) {
                Log.e(TAG,"Update DB: Error in dropping table");
            }

            try {
                TableUtils.createTable(connectionSource,TABLES[0]);
                TableUtils.createTable(connectionSource,TABLES[1]);
            }catch (SQLException exc){
                Log.e(TAG,"Update DB: Error in table creation");
            }
        }
    }

    public Dao<Channel,Integer> getChannelDAO(){
        if(channelDAO == null){

            try {
                channelDAO = getDao(Channel.class);
            } catch (SQLException e) {
                Log.e(TAG,"Error fetching CHANNEL DAO");
            }
        }

        return channelDAO;
    }

    public Dao<DescriptiveChannel,Integer> getDescChannelDao(){
        if(descriptiveChannelDAO == null){

            try {
                descriptiveChannelDAO = getDao(DescriptiveChannel.class);
            }catch (SQLException exc){
                Log.e(TAG,"Error fetching Desc CHANNEL DAO");
            }
        }

        return descriptiveChannelDAO;
    }
}
