//package com.manooz.telgramgroups.Current_Project;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.manooz.telgramgroups.R;
//
//import java.util.ArrayList;
//
//public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.mViewHolder> {
//
//
//    private ArrayList<GroupModel> mExampleList;
//    private OnItemClickListener mListener;
//
//    public GroupAdapter(ArrayList<GroupModel> exampleList) {
//        mExampleList = exampleList;
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return mExampleList.size();
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//
//    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        private TextView mGroupName;
//        private TextView mGroupDesc;
//        private TextView profile_image;
//        private ImageView rating;
//
//
//        mViewHolder(View itemView) {
//            super(itemView);
//
//            mGroupName  =  itemView.findViewById(R.id.username);
//            mGroupDesc   =  itemView.findViewById(R.id.desc);
//            profile_image  =  itemView.findViewById(R.id.profile_image);
//            rating         =  itemView.findViewById(R.id.ratingImg);
//        }
//        @Override
//        public void onClick(View v) {
//            int clickedPostion = getAdapterPosition();
//            mListener.onItemClick(clickedPostion);
//
//        }
//    }
//
//
//}
//
//
