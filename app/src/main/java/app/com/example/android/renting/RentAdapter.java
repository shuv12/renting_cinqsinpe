package app.com.example.android.renting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.com.example.android.renting.model.Rent;



public class RentAdapter extends RecyclerView.Adapter<RentAdapter.ViewHolder> implements Filterable {
    static Context context;
    private CustomFilter filter;
    private List<Rent> filterList;
    private List<Rent> rentList;

    class CustomFilter extends Filter {
        CustomFilter() {
        }

        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() <= 0) {
                results.count = RentAdapter.this.filterList.size();
                results.values = RentAdapter.this.filterList;
            } else {
                constraint = constraint.toString().toUpperCase();
                List<Rent> filters = new ArrayList();
                for (int i = 0; i < RentAdapter.this.filterList.size(); i++) {
                    if (((Rent) RentAdapter.this.filterList.get(i)).getTitle().toUpperCase().contains(constraint)) {
                        Rent rent = new Rent();
                        rent.setUserProfilePic(((Rent) RentAdapter.this.filterList.get(i)).getUserProfilePic());
                        rent.setUserProfileName(((Rent) RentAdapter.this.filterList.get(i)).getUserProfileName());
                        rent.setTitle(((Rent) RentAdapter.this.filterList.get(i)).getTitle());
                        rent.setDaysLeft(((Rent) RentAdapter.this.filterList.get(i)).getDaysLeft());
                        rent.setImage(((Rent) RentAdapter.this.filterList.get(i)).getImage());
                        rent.setDescription(((Rent) RentAdapter.this.filterList.get(i)).getDescription());
                        rent.setPrice(((Rent) RentAdapter.this.filterList.get(i)).getPrice());
                        rent.setFavs(((Rent) RentAdapter.this.filterList.get(i)).getFavs());
                        rent.setViews(((Rent) RentAdapter.this.filterList.get(i)).getViews());
                        filters.add(rent);
                    }
                }
                results.count = RentAdapter.this.filterList.size();
                results.values = filters;
            }
            return results;
        }

        protected void publishResults(CharSequence constraint, FilterResults results) {
            RentAdapter.this.rentList = (List) results.values;
            RentAdapter.this.notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        TextView daysLeftTV;
        TextView descriptionTV;
        TextView favsTV;
        ImageView imageView;
        TextView priceTV;
        TextView profileNameTV;
        ImageView profilePicIV;
        TextView titleTV;
        TextView viewTV;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
           // this.mainCV = (CardView) itemView.findViewById(R.id.rAdMainCV);
            this.profilePicIV = (ImageView) itemView.findViewById(R.id.postProfilePic);
            this.profileNameTV = (TextView) itemView.findViewById(R.id.postTextUserName);
            this.titleTV = (TextView) itemView.findViewById(R.id.postTextTitle);
            this.daysLeftTV = (TextView) itemView.findViewById(R.id.postTextDaysLeft);
            this.imageView = (ImageView) itemView.findViewById(R.id.postPropertyImage);
            this.descriptionTV = (TextView) itemView.findViewById(R.id.postTextDescription);
            this.priceTV = (TextView) itemView.findViewById(R.id.postTextPrice);
            this.favsTV = (TextView) itemView.findViewById(R.id.postTextNoFav);
            this.viewTV = (TextView) itemView.findViewById(R.id.postTextNoViews);
            RentAdapter.context = itemView.getContext();
        }
    }

    public RentAdapter(Context context, List<Rent> rentList) {
        context = context;
        this.rentList = rentList;
       // Log.i("TAG", "CONStrUCTOR");
        //Log.i("TAG", String.valueOf(rentList.size()));
        this.filterList = rentList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false), viewType);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        final Rent rent = (Rent) this.rentList.get(position);
        Picasso.with(context).load(rent.getUserProfilePic()).placeholder((int) R.drawable.ic_android_black).resize(150,150).into(holder.profilePicIV);
        holder.profileNameTV.setText(rent.getUserProfileName());
        holder.titleTV.setText(rent.getTitle());
        holder.daysLeftTV.setText(rent.getDaysLeft());
        Picasso.with(context).load(rent.getImage()).placeholder((int) R.drawable.default_home_image).resize(1050,750).into(holder.imageView);
        holder.descriptionTV.setText(rent.getDescription());
        holder.priceTV.setText(rent.getPrice());
        holder.favsTV.setText(rent.getFavs());
        holder.viewTV.setText(rent.getViews());
        /*holder.mainCV.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RentAdapter.context, RentDetailActivity.class);
                intent.putExtra("KEY_RENT", rent);
                RentAdapter.context.startActivity(intent);
                Activity activity = RentAdapter.context;
            }
        });*/
    }

    public int getItemCount() {
        return this.rentList.size();
    }

    public Filter getFilter() {
        if (this.filter == null) {
            this.filter = new CustomFilter();
        }
        return this.filter;
    }
}
