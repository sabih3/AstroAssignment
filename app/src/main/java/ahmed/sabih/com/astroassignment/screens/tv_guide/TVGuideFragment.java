package ahmed.sabih.com.astroassignment.screens.tv_guide;

import android.annotation.TargetApi;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ahmed.sabih.com.astroassignment.R;
import ahmed.sabih.com.astroassignment.models.DescriptiveChannel;
import ahmed.sabih.com.astroassignment.models.EventsResponse;
import ahmed.sabih.com.astroassignment.utils.TimeSlots;
import ahmed.sabih.com.astroassignment.utils.TVGuideHelper;
import ahmed.sabih.com.astroassignment.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

/**
 *
 */
public class TVGuideFragment extends Fragment implements TVGuideView{


    private OnTvGuideFragmentInteraction mListener;
    private View rootView;
    private TVGuidePresenter presenter;

    @BindView(R.id.tv_guide_coordinator)CoordinatorLayout tvGuideCoordinator;
    @BindView(R.id.tv_guide_parent_layout)RelativeLayout tvGuideGrandParent;
    @BindView(R.id.channel_list)LinearLayout channelListLayout;
    @BindView(R.id.horizontal_scroll_time_slots)HorizontalScrollView timeSlotsHotizontalScroll;
    @BindView(R.id.horizontal_scroll_events)HorizontalScrollView eventsHorizontalScroll;
    @BindView(R.id.time_slots_layout)LinearLayout timeSlotsLayout;
    @BindView(R.id.events_parent)LinearLayout eventsParentLayout;
    @BindView(R.id.channel_list_scroller)NestedScrollView channelListScroller;
    @BindView(R.id.vertical_scroll_events)NestedScrollView eventsVerticalScroller;

    public TVGuideFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//        rootView = inflater.inflate(R.layout.fragment_tvguide, container, false);
        rootView = inflater.inflate(R.layout.tv_guide, container, false);
        ButterKnife.bind(this,rootView);

        presenter = new TVGuidePresenter(getContext(),this);

        setScrollListeners();

        presenter.getEvents();

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTvGuideFragmentInteraction) {
            mListener = (OnTvGuideFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTvGuideFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //TVGuidePresenter.getEvents
    @Override
    public void onEventsDataFetched(List<DescriptiveChannel> channelsList, Response<EventsResponse> response) {
        List<EventsResponse.Getevent> eventsList = response.body().getGetevent();
        drawTable(channelsList,eventsList);


    }

    //TVGuidePresenter.getEvents
    @Override
    public void onFailure(String resolvedError) {
        UIUtils.showSnackBar(tvGuideCoordinator, resolvedError, getContext().getResources().
                getString(R.string.action_retry), new UIUtils.SnackBarActionListener() {
            @Override
            public void onSnackBarAction() {
                presenter.getEvents();
            }
        });
    }

    //TVGuidePresenter.getEvents
    @Override
    public void showProgress() {
        UIUtils.showProgress(rootView,true);
    }

    //TVGuidePresenter.getEvents
    @Override
    public void hideProgress() {
        UIUtils.showProgress(rootView,false);
    }


    public interface OnTvGuideFragmentInteraction {

        void onFragmentInteraction();
    }


    private void drawTable(List<DescriptiveChannel> channelsList, List<EventsResponse.Getevent> eventsList) {
        drawTimeSlots();
        setChannelAndEvents(channelsList,eventsList);

        final int scrollPosition = TVGuideHelper.getScrollPosition();

        timeSlotsHotizontalScroll.post(new Runnable() {
            @Override
            public void run() {
                timeSlotsHotizontalScroll.scrollTo(scrollPosition,0);
            }
        });

    }


    private void drawTimeSlots() {
        ArrayList<String> timeSlots = TimeSlots.getTimeSlots();

        timeSlotsLayout.removeAllViews();
        for(String timeSlot: timeSlots){
            TextView tvTimeSlot = new TextView(getContext());
            tvTimeSlot.setText(timeSlot);
            tvTimeSlot.setWidth(TVGuideHelper.WIDTH_TIME_SLOT_PX);
            tvTimeSlot.setHeight(TVGuideHelper.HEIGHT_CONSISTENT_PX);
            tvTimeSlot.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.grey_box_background));
            timeSlotsLayout.addView(tvTimeSlot);
        }
    }

    private void setChannelAndEvents(List<DescriptiveChannel> channelsList,
                                     List<EventsResponse.Getevent> eventsList) {

        channelListLayout.removeAllViews();

        for(DescriptiveChannel channel : channelsList){

            channelListLayout.addView(TVGuideHelper.getChannelNameRow(getContext(),
                                      channel,channelListLayout));

            ArrayList<EventsResponse.Getevent> sortedEvents =
                    TVGuideHelper.getEventsForParticularChannel(channel, eventsList);

            sortEventsByDateTime(sortedEvents);

            LinearLayout eventsRow = TVGuideHelper.getEventsRow(getContext(),
                                     sortedEvents);

            eventsParentLayout.addView(eventsRow);
        }
    }

    private void sortEventsByDateTime(ArrayList<EventsResponse.Getevent> sortedEvents) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final Date[] event1Date = {new Date()};
        final Date[] event2Date = {new Date()};
        Collections.sort(sortedEvents, new Comparator<EventsResponse.Getevent>() {
            @Override
            public int compare(EventsResponse.Getevent event1, EventsResponse.Getevent event2) {
                try {

                    event1Date[0] = formatter.parse(event1.getDisplayDateTime());
                    event2Date[0] = formatter.parse(event2.getDisplayDateTime());


                }catch (ParseException parseExc){
                    Log.d("TVGuideFragment",parseExc.toString());
                }

                return event1Date[0].compareTo(event2Date[0]);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setScrollListeners() {

        eventsHorizontalScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                timeSlotsHotizontalScroll.post(new Runnable() {
                    @Override
                    public void run() {
                        timeSlotsHotizontalScroll.scrollTo(eventsHorizontalScroll.getScrollX(),0);
                    }
                });
            }
        });


        timeSlotsHotizontalScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                eventsHorizontalScroll.post(new Runnable() {
                    @Override
                    public void run() {
                        eventsHorizontalScroll.scrollTo(timeSlotsHotizontalScroll.getScrollX(), 0);
                    }
                });
            }
        });


        channelListScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                eventsVerticalScroller.post(new Runnable() {
                    public void run() {
                        eventsVerticalScroller.scrollTo(0,channelListScroller.getScrollY());
                    }
                });
            }
        });

        eventsVerticalScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                channelListScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        channelListScroller.scrollTo(0,eventsVerticalScroller.getScrollY());
                    }
                });
            }
        });
    }


}
