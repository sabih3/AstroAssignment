package ahmed.sabih.com.astroassignment.screens.tv_guide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ahmed.sabih.com.astroassignment.R;
import ahmed.sabih.com.astroassignment.models.EventsResponse;
import ahmed.sabih.com.astroassignment.utils.TableMainLayout;
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

    @BindView(R.id.tv_guide_parent)LinearLayout parentLayout;


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


        rootView = inflater.inflate(R.layout.fragment_tvguide, container, false);

        ButterKnife.bind(this,rootView);

        presenter = new TVGuidePresenter(this);



        presenter.getEvents("287");


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

    @Override
    public void onEventsDataFetched(Response<EventsResponse> response) {
        List<EventsResponse.Getevent> eventsList = response.body().getGetevent();
        View tableMainLayout = new TableMainLayout(getContext(),eventsList);
        parentLayout.removeAllViews();
        parentLayout.addView(tableMainLayout);
    }


    public interface OnTvGuideFragmentInteraction {

        void onFragmentInteraction();
    }
}
