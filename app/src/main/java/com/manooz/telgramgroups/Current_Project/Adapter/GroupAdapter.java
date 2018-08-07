package com.manooz.telgramgroups.Current_Project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
import com.manooz.telgramgroups.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class GroupAdapter extends FirestoreAdapter<GroupAdapter.mGroupHolder>{

    private OnRestaurantSelectedListener mListener;
    private ArrayList<Group_Object> mGroups;


    // Define the mListener interface
    public interface OnRestaurantSelectedListener {
        void onRestaurantSelected(DocumentSnapshot restaurant);
    }

    public GroupAdapter(Query query, OnRestaurantSelectedListener listener) {
        super(query);
        mListener = listener;

    }
//
//    public GroupAdapter(ArrayList<Group_Object> groupObjects) {
//        mGroups = groupObjects;
//
//    }

//    // Define the method that allows the parent activity or fragment to define the mListener
//    public void setOnItemClickListener(OnRestaurantSelectedListener listener) {
//        this.mListener = listener;
//    }

    @NonNull
    @Override
    public mGroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
//        mGroupHolder mGroupHolder = new mGroupHolder(view, mListener);
//        return mGroupHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new mGroupHolder(inflater.inflate(R.layout.list_item2, parent, false));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull mGroupHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);

    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

//    public interface OnRestaurantSelectedListener {
//        void onRestaurantSelected(View itemView, int position);
//    }

    public static class mGroupHolder extends RecyclerView.ViewHolder {


            @BindView(R.id.mName)
            TextView mName;
            @BindView(R.id.mGroupDesc)
            TextView mGroupDesc;
            @BindView(R.id.mGroupLink)
            TextView mGroupLink;
            @BindView(R.id.mCatPic)
            TextView mCatPic;
            @BindView(R.id.mCatogaries)
            TextView mCatogaries;
            @BindView(R.id.mGroupImage)
            de.hdodenhof.circleimageview.CircleImageView
                mGroupImage;
            @BindView(R.id.mNumOfComments)
            TextView mNumOfComments;
            @BindView(R.id.mNumOfLikes)
            TextView mNumOfLikes;
            @BindView(R.id.mNumOfViews)
            TextView mNumOfViews;
            @BindView(R.id.mRateTxt)
            TextView mRateTxt;
            @BindView(R.id.mGroupUserName)
            TextView mGroupUserName;
            @BindView(R.id.mEnterBtn)
            ImageButton mEnterBtn;
            @BindView(R.id.mShareBtn)
            ImageButton mShareBtn;
            @BindView(R.id.mFavBtn)
            ImageButton mFavBtn;
            @BindView(R.id.mReportBtn)
            ImageButton mReportBtn;
            @BindView(R.id.mLikesIcon)
            ImageButton mLikesIcon;
            @BindView(R.id.mCommentsIcon)
            ImageButton mCommentsIcon;
            @BindView(R.id.mViewsIcon)
            ImageButton mViewsIcon;
            @BindView(R.id.mRatingBar)
        me.zhanghai.android.materialratingbar.MaterialRatingBar mRatingBar;



        public mGroupHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
        public void bind(final DocumentSnapshot snapshot,
                         final OnRestaurantSelectedListener listener) {

            Group_Object restaurant = snapshot.toObject(Group_Object.class);
            Resources resources = itemView.getResources();

            // Load image
            Glide.with(mGroupImage.getContext())
                    .load(restaurant.getPhoto())
                    .into(mGroupImage);

            // TODO: 8/2/2018
            mName.setText(restaurant.getGroupName());
            mGroupDesc.setText(restaurant.getGroupDesc());
            mGroupLink.setText(restaurant.getGroupLink());
            mCatogaries.setText(restaurant.getCatogries());
            mNumOfComments.setText(restaurant.getmNumOfComments());
            mNumOfLikes.setText(restaurant.getmNumOfLiks()+"");
            mNumOfViews.setText(restaurant.getmNumOfViews());
            mRateTxt.setText(restaurant.getmNumOfRatings());
            mCatPic.setText(restaurant.getPhoto());
            mGroupUserName.setText(String.format("%s", restaurant.getUserName()));
            mRatingBar.setRating((float) restaurant.getRatings());

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onRestaurantSelected(snapshot);
                    }
                }
            });
        }
    }
}



