package com.manooz.telgramgroups.Current_Project.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manooz.telgramgroups.Current_Project.POJO.Catogery;
import com.manooz.telgramgroups.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.mViewHolder> {
    Context context;
    List<Catogery> myModels;
    boolean userClicked = false;

    //    private final View.OnClickListener mOnClickListener = new MyOnClickListener();
    public CategoriesAdapter(List<Catogery> items) {

        this.myModels = items;
    }

    public void mShowOnlyTop(List<Catogery> mTopCats) {
        myModels = mTopCats;

    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);


        return new mViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {

        Catogery Catogery = myModels.get(position);
        holder.mCatName.setText(Catogery.getmCatName());
        holder.mCatDesc.setText(Catogery.getmCatDesc());
        holder.mCatPic.setImageResource(Catogery.getmCatPic());

//        Picasso.with(holder.mCatPic.getContext()).load(R.drawable.placeholder).placeholder(R.drawable.placeholder).into(holder.mCatPic);

//        if (getLayoutPosition()==0){
//            Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, " Clicked");
//        }


    }


    @Override
    public int getItemCount() {

        return myModels.size();

    }


//    =================================== Classes ================================================  \\

    class mViewHolder extends RecyclerView.ViewHolder {

        public TextView mCatName, mCatDesc;
        ImageView mCatPic;
        RelativeLayout parentLayout;

        private String TAG = "ViewHolder";

        private mViewHolder(View view) {
            super(view);
            mCatName = view.findViewById(R.id.mCatName);
            mCatDesc = view.findViewById(R.id.mCatDesc);
            mCatPic = view.findViewById(R.id.mCatPic);
            parentLayout = view.findViewById(R.id.pl);


        }


    }
}

