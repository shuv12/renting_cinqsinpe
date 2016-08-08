package app.com.example.android.renting;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.com.example.android.renting.model.Rent;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
    private Context context;
    private List<Rent> rentList;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        TextView userName,daysLeft,noFavs,noViews,title_fav;
        ImageView imageView;
        CardView mainCV;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mainCV = (CardView) itemView.findViewById(R.id.card_fav);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageview_fav);
            this.userName = (TextView) itemView.findViewById(R.id.text_fav);
            this.daysLeft = (TextView) itemView.findViewById(R.id.daysLeft_fav);
            this.noFavs = (TextView) itemView.findViewById(R.id.fav_fav);
            this.title_fav = (TextView) itemView.findViewById(R.id.title_fav);
            this.noViews = (TextView) itemView.findViewById(R.id.views_fav);
        }
    }

    public FavouritesAdapter(Context context, List<Rent> rentList) {
        this.context = context;
        this.rentList = rentList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favourites_adapter, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Rent rent = (Rent) this.rentList.get(position);
        Picasso.with(this.context).load(rent.getImage()).placeholder((int) R.drawable.default_home_image).fit().into(holder.imageView);
        holder.userName.setText(rent.getUserProfileName());
        holder.daysLeft.setText(rent.getDaysLeft());
        holder.title_fav.setText(rent.getTitle());
        holder.noFavs.setText(rent.getFavs());
        holder.noViews.setText(rent.getViews());
    }

    public int getItemCount() {
        return this.rentList.size();
    }
}
