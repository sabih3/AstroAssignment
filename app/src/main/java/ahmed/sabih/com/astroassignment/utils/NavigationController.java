package ahmed.sabih.com.astroassignment.utils;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ahmed.sabih.com.astroassignment.R;
import ahmed.sabih.com.astroassignment.screens.channel_list.ChannelListFragment;
import ahmed.sabih.com.astroassignment.screens.favourites.FavouritesFragment;
import ahmed.sabih.com.astroassignment.screens.main_activity.MainActivity;
import ahmed.sabih.com.astroassignment.screens.tv_guide.TVGuideFragment;

/**
 * Created by sabih on 01-Nov-17.
 */

public class NavigationController {


    public static void showChannelListScreen(Context context,
                                             FragmentManager supportFragmentManager) {

        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content_layout,new ChannelListFragment());
        fragmentTransaction.commit();

    }

    public static void showTVGuideScreen(Context context,
                                         FragmentManager supportFragmentManager) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content_layout,new TVGuideFragment());
        fragmentTransaction.commit();

    }

    public static void showFavouritesScreen(Context context,
                                            FragmentManager supportFragmentManager) {

        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content_layout,new FavouritesFragment());
        fragmentTransaction.commit();

    }
}
