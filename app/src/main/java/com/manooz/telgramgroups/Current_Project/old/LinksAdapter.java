//package com.manooz.telgramgroups.Current_Project;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.GradientDrawable;
//import android.support.annotation.NonNull;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.manooz.telgramgroups.R;
//
//import java.util.List;
//import java.util.Random;
//
//
//
//public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.ViewHolder>  {
//
//
//    // ====================== View Holder Class ============================  \\
//
//      class mViewHolder extends RecyclerView.mViewHolder implements View.OnClickListener{
//
//        private TextView titleTextView;
//        private TextView mGroupName;
//        private TextView profile_image;
//        private ImageView rating;
//
//
//        ViewHolder(View itemView) {
//            super(itemView);
//
//            titleTextView =  itemView.findViewById(R.id.mGroupName);
//            mGroupName =   itemView.findViewById(R.id.desc);
//            profile_image =  itemView.findViewById(R.id.profile_image);
//            rating =         itemView.findViewById(R.id.ratingImg);
//        }
//          @Override
//          public void onClick(View v) {
//              int clickedPostion = getAdapterPosition();
//              mListener.onRestaurantSelected(clickedPostion);
//
//          }
//    }
//
//    // ====================== Adapter Class ============================  \\
//
//    private List<Group_Object> linkList;
//
//     private Context context;
//
//    // Clean Code To Use In Other Projects
//    public interface OnRestaurantSelectedListener {
//        void onRestaurantSelected(int positionOfIndex);
//    }
//    // ===========Listner==============\\
//    private OnRestaurantSelectedListener mListener;
//
//
//    public LinksAdapter(List<Group_Object> linkList, Context context, OnRestaurantSelectedListener mListener) {
//        this.linkList = linkList;
//        this.context = context;
//        this.mListener = mListener;
//
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View fragmentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
//
//        //
//        return new ViewHolder(fragmentView);
//        //@OC
////        return new mViewHolder(fragmentView);
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//        final Group_Object movieQuote = linkList.get(position);
//        holder.titleTextView.setText(movieQuote.getGroupName());
//        holder.mGroupName.setText(movieQuote.getlTitle());
//        // For Set The First letter from each text as background
//        holder.profile_image.setText(movieQuote.getGroupName().substring(0,1) + movieQuote.getlTitle().substring(0,1));
//        // Randoom Color
//        Random mRandom = new Random();
//        int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
//        ((GradientDrawable) holder.profile_image.getBackground()).setColor(color);
//
//        // ======================= Handle Clicks========================== \\
//        // Star Btn
//        holder.rating.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (holder.rating.getColorFilter() != null) {
//                    holder.rating.clearColorFilter();
//                } else  if (context !=null){
//
//                    holder.rating.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));
//                }
//            }
//        });
//        // Delete Btn
//
//        // Send Btn
//
//        // Edit Btn
//
//
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return linkList.size();
//    }
//
//
//
//
//}
