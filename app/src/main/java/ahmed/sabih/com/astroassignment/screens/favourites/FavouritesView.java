package ahmed.sabih.com.astroassignment.screens.favourites;

import java.util.List;

import ahmed.sabih.com.astroassignment.models.Channel;

/**
 * Created by sabih on 03-Nov-17.
 */

public interface FavouritesView {

    void onFavouritesFetched(List<Channel> allFavouritesList);
}
