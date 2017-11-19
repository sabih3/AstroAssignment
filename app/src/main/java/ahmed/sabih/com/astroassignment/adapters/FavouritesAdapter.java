package ahmed.sabih.com.astroassignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ahmed.sabih.com.astroassignment.R;
import ahmed.sabih.com.astroassignment.db.FavoritesDataManager;
import ahmed.sabih.com.astroassignment.models.Channel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sabih on 03-Nov-17.
 * ahmed.engr90@gmail.com
 */

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouriteHolder>{

    private final List<Channel> mDataset;
    private Context mContext;
    private onFavouriteClickListener favouriteClickListener;
    public FavouritesAdapter(List<Channel> allFavouritesList) {
        this.mDataset= allFavouritesList;

    }

    public void setFavouriteClickListener(onFavouriteClickListener favouriteClickListener) {
        this.favouriteClickListener = favouriteClickListener;
    }

    @Override
    public FavouriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View row = LayoutInflater.from(mContext).inflate(R.layout.row_favourite, parent, false);

        FavouriteHolder holder = new FavouriteHolder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(FavouriteHolder holder, int position) {

        Channel channel = mDataset.get(position);

        holder.tv_favChannelName.setText(channel.getChannelTitle());
        holder.tv_favChannelNumber.setText(Integer.toString(channel.getChannelStbNumber()));
        holder.favIcon.setOnClickListener(new FavIconListener(channel));

    }

    @Override
    public int getItemCount() {
        return mDataset.isEmpty() ? 0 : mDataset.size() ;
    }


    public class FavouriteHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.row_fav_channel_name)public TextView tv_favChannelName;
        @BindView(R.id.row_fav_channel_number)public TextView tv_favChannelNumber;
        @BindView(R.id.icon_favlist_favourite)public ImageView favIcon;

        public FavouriteHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    private class FavIconListener implements View.OnClickListener {
        private final Channel channel;

        public FavIconListener(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void onClick(View view) {
            favouriteClickListener.onFavouriteRemoved(channel);
        }
    }

    public interface onFavouriteClickListener{
        void onFavouriteRemoved(Channel channel);
    }
}

