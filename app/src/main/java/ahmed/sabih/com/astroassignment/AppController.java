package ahmed.sabih.com.astroassignment;

import android.app.Application;

import ahmed.sabih.com.astroassignment.db.DBManager;

/**
 * Created by sabih on 03-Nov-17.
 */

public class AppController extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.init(this);
        DBManager.getInstance().getDBHelper().getWritableDatabase();
    }
}
