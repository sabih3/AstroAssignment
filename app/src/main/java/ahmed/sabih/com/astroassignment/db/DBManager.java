package ahmed.sabih.com.astroassignment.db;

import android.content.Context;

/**
 * Created by sabih on 03-Nov-17.
 */

public class DBManager {

    private static DBManager instance ;
    private DBHelper dbHelper;
    private Context mContext;

    private DBManager(Context context){

        dbHelper = new DBHelper(context);

    }


    public static void init(Context context){
        if(instance == null){
            instance = new DBManager(context);
        }
    }

    public static DBManager getInstance(){
        return instance;
    }

    public DBHelper getDBHelper(){
        return dbHelper;
    }
}
