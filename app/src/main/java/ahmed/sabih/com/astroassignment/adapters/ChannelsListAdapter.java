package ahmed.sabih.com.astroassignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ahmed.sabih.com.astroassignment.R;
import ahmed.sabih.com.astroassignment.db.FavoritesDataManager;
import ahmed.sabih.com.astroassignment.models.Channel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sabih on 01-Nov-17.
 */

public class ChannelsListAdapter extends RecyclerView.Adapter<ChannelsListAdapter.
                                                              ChannelListHolder>{

    private final ArrayList<Channel> mDataset;
    private Context mContext;

    public ChannelsListAdapter(ArrayList<Channel> data) {
        this.mDataset = data;
    }

    @Override
    public ChannelListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_channel_list,
                                       parent, false);
        ChannelListHolder holder = new ChannelListHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChannelListHolder holder, int position) {
        Channel channel = mDataset.get(position);

        holder.tv_channelName.setText(channel.getChannelTitle());
        holder.tv_channelNumber.setText(Integer.toString(channel.getChannelStbNumber()));

        setFavouriteWhileRendering(holder,channel);
        holder.icon_favourite.setOnClickListener(new FavouriteIconListener(channel,holder));

    }


    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }



    public class ChannelListHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.row_channel_name)TextView tv_channelName;
        @BindView(R.id.row_channel_number)TextView tv_channelNumber;
        @BindView(R.id.icon_favourite)ImageView icon_favourite;

        public ChannelListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

    private class FavouriteIconListener implements View.OnClickListener {

        private final Channel channel;
        private final ChannelListHolder mHolder;
        private boolean isFavourite = false;

        public FavouriteIconListener(Channel channel,
                                     ChannelListHolder holder) {
            this.channel = channel;
            this.mHolder = holder;
        }

        @Override
        public void onClick(View view) {
            updateFavouriteInDB(mHolder,channel);

        }
    }

    /**This method changes fav icon if channel is already marked as favourite
     *
     * @param holder
     * @param channel
     */
    private void setFavouriteWhileRendering(ChannelListHolder holder, Channel channel) {

        if(FavoritesDataManager.checkIfChannelIsFavourite(channel)){
            holder.icon_favourite.setImageResource(R.drawable.ic_favorite_black_24px);
        }else{
            holder.icon_favourite.setImageResource(R.drawable.ic_favorite_border_black_24px);
        }
    }


    private void updateFavouriteInDB(ChannelListHolder mHolder, Channel channel) {

        if(FavoritesDataManager.checkIfChannelIsFavourite(channel)){
            FavoritesDataManager.removeChannelAsFavourite(channel);
            mHolder.icon_favourite.setImageResource(R.drawable.ic_favorite_border_black_24px);

        }else{
            FavoritesDataManager.setChannelAsFavourite(channel);
            mHolder.icon_favourite.setImageResource(R.drawable.ic_favorite_black_24px);
        }

    }
}
