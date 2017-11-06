package ahmed.sabih.com.astroassignment.screens.channel_list;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;

import ahmed.sabih.com.astroassignment.R;
import ahmed.sabih.com.astroassignment.adapters.ChannelsListAdapter;
import ahmed.sabih.com.astroassignment.models.Channel;
import ahmed.sabih.com.astroassignment.utils.CompareUtils;
import ahmed.sabih.com.astroassignment.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class ChannelListFragment extends Fragment implements ChannelListScreenView {

    private View rootView;
    private ChannelListPresenter channelListPresenter;
    @BindView(R.id.coordinator_layout)CoordinatorLayout coordinatorLayout;
    @BindView(R.id.channel_list_recycler)RecyclerView channelListView;
    @BindView(R.id.icon_sort) ImageView iconSort;
    @BindView(R.id.icon_sort_alphabet)ImageView iconSortAlphabet;
    @BindView(R.id.list_actions_layout)LinearLayout actionsLayout;

    public ChannelListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_channel_list,
                                    container, false);

        ButterKnife.bind(this,rootView);
        channelListPresenter = new ChannelListPresenter(getContext(),this);
        channelListPresenter.fetchChannelsList();

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        channelListPresenter.destroyNetworkCall();
    }

    //ChannelListScreenView.onListDataReceived
    @Override
    public void onListDataReceived(ArrayList<Channel> channelsList) {
        actionsLayout.setVisibility(View.VISIBLE);
        iconSortAlphabet.setOnClickListener(new SortByAlphabetListener(channelsList));
        iconSort.setOnClickListener(new SortByNumberListener(channelsList));
        UIUtils.showProgress(rootView,false);
        renderListView(channelsList);
    }

    //ChannelListScreenView.showProgress
    @Override
    public void showProgress() {
        UIUtils.showProgress(rootView,true);
    }

    //ChannelListScreenView.showEmptyListView
    @Override
    public void showEmptyListView() {

    }

    //ChannelListScreenView.showException
    @Override
    public void showException(String resolvedErrorMessage) {
        UIUtils.showProgress(rootView,false);
        UIUtils.showSnackBar(coordinatorLayout,resolvedErrorMessage);
    }


    /**==========================Helper Methods / Inner Classes=========================================**/

    /** Method used for setting list data in recycler view
     *
     * @param channelsList
     */
    private void renderListView(ArrayList<Channel> channelsList) {
        channelListView.setLayoutManager(new LinearLayoutManager(getContext()));
        ChannelsListAdapter listAdapter = new ChannelsListAdapter(channelsList);
        channelListView.setAdapter(listAdapter);
    }

    private class SortByNumberListener implements View.OnClickListener {

        private final ArrayList<Channel> dataset;

        public SortByNumberListener(ArrayList<Channel> channelsList) {
            this.dataset = channelsList;
        }

        @Override
        public void onClick(View view) {

            Collections.sort(dataset, CompareUtils.channelNumberComparator);

            renderListView(dataset);
        }
    }

    private class SortByAlphabetListener implements View.OnClickListener {
        private final ArrayList<Channel> dataset;

        public SortByAlphabetListener(ArrayList<Channel> channelsList) {
            this.dataset = channelsList;
        }

        @Override
        public void onClick(View view) {
            Collections.sort(dataset,CompareUtils.channelNameComparator);
            renderListView(dataset);
        }
    }
}
