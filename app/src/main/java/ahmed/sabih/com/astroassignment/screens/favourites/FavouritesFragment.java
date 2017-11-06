package ahmed.sabih.com.astroassignment.screens.favourites;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ahmed.sabih.com.astroassignment.R;
import ahmed.sabih.com.astroassignment.adapters.FavouritesAdapter;
import ahmed.sabih.com.astroassignment.db.FavoritesDataManager;
import ahmed.sabih.com.astroassignment.models.Channel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class FavouritesFragment extends Fragment implements FavouritesView{

    private View rootView;
    @BindView(R.id.fav_list)RecyclerView favouritesList;

    FavouritesPresenter presenter;
    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_favourites, container, false);

        ButterKnife.bind(this,rootView);

        presenter = new FavouritesPresenter(this);

        presenter.getAllFavourites();



        return rootView;
    }

    @Override
    public void onFavouritesFetched(List<Channel> allFavouritesList) {
        showDataInListView(allFavouritesList);
    }

    private void showDataInListView(List<Channel> allFavouritesList) {

        FavouritesAdapter adapter = new FavouritesAdapter(allFavouritesList);
        favouritesList.setLayoutManager(new LinearLayoutManager(getContext()));
        favouritesList.setAdapter(adapter);
    }
}
