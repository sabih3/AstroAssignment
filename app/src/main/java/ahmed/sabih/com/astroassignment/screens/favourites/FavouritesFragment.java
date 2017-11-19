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
import ahmed.sabih.com.astroassignment.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/** Class used to display Favourites Channels in list
 *
 */
public class FavouritesFragment extends Fragment implements FavouritesView,
                                                 FavouritesAdapter.onFavouriteClickListener{

    private View rootView;
    @BindView(R.id.fav_list)RecyclerView favouritesList;
    private FavouritesPresenter presenter;
    private FavouritesAdapter favouriteAdapter;
    private List<Channel> favouritesDataset;

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
        favouritesDataset = allFavouritesList;
        favouriteAdapter = new FavouritesAdapter(favouritesDataset);
        favouriteAdapter.setFavouriteClickListener(FavouritesFragment.this);
        favouritesList.setLayoutManager(new LinearLayoutManager(getContext()));
        favouritesList.setAdapter(favouriteAdapter);
    }

    @Override
    public void onFavouriteRemoved(final Channel channel) {
        String message = getResources().getString(R.string.msg_remove_fav);
        String positiveBtn= getResources().getString(R.string.btn_positive);
        String negaveBtn = getResources().getString(R.string.btn_negavtive);

        UIUtils.showMessageDialog(getContext(), message, positiveBtn, negaveBtn, new UIUtils.DialogButtonListener() {
            @Override
            public void onPositiveButtonClicked() {
                favouritesDataset.remove(channel);
                FavoritesDataManager.removeChannelAsFavourite(channel);
                favouriteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNegativeButtonClicked() {

            }
        });



    }
}
