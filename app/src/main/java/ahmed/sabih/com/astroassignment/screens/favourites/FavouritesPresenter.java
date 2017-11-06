package ahmed.sabih.com.astroassignment.screens.favourites;

import java.util.List;

import ahmed.sabih.com.astroassignment.db.FavoritesDataManager;
import ahmed.sabih.com.astroassignment.models.Channel;

/**
 * Created by sabih on 03-Nov-17.
 */

public class FavouritesPresenter {

    private final FavouritesView favouritesView;

    public FavouritesPresenter(FavouritesView favouritesView) {
        this.favouritesView = favouritesView;
    }

    public void getAllFavourites() {
        List<Channel> allFavouritesList = FavoritesDataManager.getAllFavourites();
        favouritesView.onFavouritesFetched(allFavouritesList);
    }
}
