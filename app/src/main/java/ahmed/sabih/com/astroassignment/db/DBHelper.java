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

/**
 * Created by sabih on 03-Nov-17.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper{

    private static String TAG = DBHelper.class.getSimpleName();
    private static String DATABASE_NAME = "astro-assignment";
    private static int DATABASE_VERSION = 3;
    private Dao<Channel,Integer> channelDAO;

    private static final Class<?> [] TABLES = {
            Channel.class
    };


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,TABLES[0]);
        }catch (SQLException exc){
            Log.e(TAG,"Error in table creation");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            try {
                TableUtils.dropTable(connectionSource,TABLES[0],false);
            } catch (SQLException exc) {
                Log.e(TAG,"Update DB: Error in dropping table");
            }

            try {
                TableUtils.createTable(connectionSource,TABLES[0]);
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
}
