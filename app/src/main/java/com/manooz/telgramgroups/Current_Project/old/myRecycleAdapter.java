//package com.manooz.telgramgroups.Current_Project.adapter;
//
//import android.graphics.Color;
//import android.graphics.drawable.GradientDrawable;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
//import com.manooz.telgramgroups.R;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class myRecycleAdapter extends RecyclerView.Adapter<mVh> {
//
//    // Clean Code To Use In Other Projects
//    public interface OnItemClickListener {
//        void onItemClick(int positionOfIndex);
//    }
////    // ===========Listner==============\\
//    private OnItemClickListener mListener;
//
//   public ArrayList<Group_Object> group_objectArrayList;
//   public Fragment addGroupActivity;
//
//    // TODO: 7/19/2018
//    public myRecycleAdapter(Fragment addGroupActivity, ArrayList<Group_Object> users) {
//        this.group_objectArrayList = users;
//        this.addGroupActivity = addGroupActivity;
//    }
//
//    @NonNull
//    @Override
//    public mVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = addGroupActivity.getLayoutInflater();
//        android.view.View view  = layoutInflater.inflate(R.layout.list_item,parent,false);
//
//        return new mVh(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull mVh holder, int position) {
//
//        final Group_Object group_object = group_objectArrayList.get(position);
//
//        holder.mGroupName.setText(group_object.getGroupName());
//        holder.mGroupDesc.setText(group_object.getGroupDesc());
//       // For Set The First letter from each text as background
//        holder.profile_image.setText("f");
//        // Random Color
//        Random mRandom = new Random();
//        int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
//        ((GradientDrawable) holder.profile_image.getBackground()).setColor(color);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return group_objectArrayList.size();
//
//    }
//}
