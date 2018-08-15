/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manooz.telgramgroups.Current_Project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;
import com.manooz.telgramgroups.Current_Project.GroupDetails;
import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
import com.manooz.telgramgroups.Current_Project.POJO.Upload;
import com.manooz.telgramgroups.Current_Project.Utily.mConstants;
import com.manooz.telgramgroups.R;

import java.util.List;
import java.util.Objects;

import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION;
import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.NumOfLiks;

/**
 * RecyclerView adapter for a list of Restaurants.
 */
public class RestaurantAdapter extends FirestoreRecyclerAdapter<Group_Object, RestaurantAdapter.mViewHolder> {

    private String[] mArrayOfdocumentRefrences   = new String[]{"Sport",""};
    private String[] mArrayOfCollictionRefrences = new String[]{"SportsGroups",""};
    private static final String TAG = "RestaurantAdapter";
    private List<Group_Object> group_objects;
    private Context context;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference collectionReference = db.collection(MAIN_COLLECTION);

    private CollectionReference supCollectionReference = db.collection(MAIN_COLLECTION).document(mArrayOfdocumentRefrences[0]).collection(mArrayOfCollictionRefrences[0]);

    private DocumentReference documentReference =
            db.document(MAIN_COLLECTION + "/Sport").collection("SportsGroups").document();


    private OnRestaurantSelectedListener mListener;
    class mViewHolder extends RecyclerView.ViewHolder {
        TextView mUserName, mCatogries, mGroupLink, mGroupDesc, mGroupName;
        TextView mNumOfComments, mNumOfLikes, mNumOfViews, mRateText;
        ImageButton mGroupEnterBtn, mShareGroupBtn, mFavBtn, mLikeBtn, mCommentBtn, mViewsBtn, mReportBtn;
        ImageView mGroupImage;
        me.zhanghai.android.materialratingbar.MaterialRatingBar materialRatingBar;


        public mViewHolder(View itemView) {

            super(itemView);
            mGroupName = itemView.findViewById(R.id.mName);
            mGroupDesc = itemView.findViewById(R.id.mGroupDesc);
            mGroupLink = itemView.findViewById(R.id.mGroupLink);
            mUserName = itemView.findViewById(R.id.mCatPic);
            mCatogries = itemView.findViewById(R.id.mCatogaries);
            mGroupImage = itemView.findViewById(R.id.mGroupImage);
            //Texts
            mNumOfComments = itemView.findViewById(R.id.mNumOfComments);
            mNumOfLikes = itemView.findViewById(R.id.mNumOfLiksTxt);
            mNumOfViews = itemView.findViewById(R.id.mNumOfViews);
            mRateText = itemView.findViewById(R.id.mRateTxt);
            //Buttons
            mGroupEnterBtn = itemView.findViewById(R.id.mEnterBtn);
            mShareGroupBtn = itemView.findViewById(R.id.mShareBtn);
            mFavBtn = itemView.findViewById(R.id.mFavBtn);
            mReportBtn = itemView.findViewById(R.id.mReportBtn);
            mLikeBtn = itemView.findViewById(R.id.mLikesIcon);
            mCommentBtn = itemView.findViewById(R.id.mCommentsIcon);
            mViewsBtn = itemView.findViewById(R.id.mViewsIcon);


            materialRatingBar = itemView.findViewById(R.id.mRatingBar);
        }


    }

    public RestaurantAdapter(@NonNull FirestoreRecyclerOptions<Group_Object> options, OnRestaurantSelectedListener mListener) {
        super(options);
        this.mListener = mListener;
    }


    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);

//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        return new mViewHolder(inflater.inflate(R.layout.list_item2, parent, false));
        return new mViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull final mViewHolder holder, int position, @NonNull final Group_Object model) {

        try {
            holder.mGroupName.setText(model.getGroupName() + "");
            final String mFirst = holder.mGroupName.getText().toString();
            holder.mGroupDesc.setText(model.getGroupDesc() + "");
            final String mSecond = holder.mGroupDesc.getText().toString();
            holder.mUserName.setText( model.getUserName()+"");
            holder.mGroupLink.setText(model.getGroupLink() + "");
            final String mLink = holder.mGroupLink.getText().toString();
            holder.mCatogries.setText(model.getCategories() + "");
            holder.materialRatingBar.setRating((float) model.getRatings());
            holder.mNumOfLikes.setText(model.getmNumOfLiks() + "");
            holder.mNumOfComments.setText(model.getmNumOfComments() + "");
            holder.materialRatingBar.setNumStars(5);
            holder.mRateText.setText(holder.materialRatingBar.getNumStars() + "");
            holder.mNumOfViews.setText(model.getmNumOfViews() + "");


            holder.mShareGroupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri2 = Uri.parse("https://play.google.com/store/apps/com.manooz.telgramgroups");
                    Uri uri3 = Uri.parse(mLink);


                    String mMessageBody =
                            mFirst + " \n \n " + mSecond + " " + " \n \n \n "
                                    + " رابط القناة :  " + "\n \n " + uri3 + " \n \n " +
                                    " من تطبيق قروبات تليجرام  \n \n    حمل التطبيق الآن من الرابط : \n   \n " + "\n " + uri2;

                    Intent ShareIntent = new Intent(Intent.ACTION_SEND);
                    ShareIntent.putExtra(Intent.EXTRA_TEXT, mMessageBody);
                    ShareIntent.setType("text/plain");
                    v.getContext().startActivity(Intent.createChooser(ShareIntent, "Share via"));


                }
            });
            holder.mCommentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), GroupDetails.class);
                    v.getContext().startActivity(intent);


                }
            });

            holder.mLikeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
////
////                    DocumentReference s = supCollectionReference.document("01vAq1QkMmFq2Nb7jGhp");
//                    Group_Object group_object = new Group_Object();
//                    int num = 0;
//                    final int ss = group_object.getmNumOfLiks() + num++ ;
//                    Map<String,Object> objectMap = new HashMap<>();
//                            objectMap.put(Group_Object.FIELD_NumOfLiks, ss);
//
//
//                    ////                            db.collection(MAIN_COLLECTION).document().collection("likes").document().set(objectMap);
//////                        GroupsRef.orderBy()
//
////                    collectionReference.document("he").set(objectMap, SetOptions.merge());
//                    supCollectionReference.document("01vAq1QkMmFq2Nb7jGhp").update(Group_Object.FIELD_NumOfLiks,ss);
//                    Toast.makeText(v.getContext(), "hello" + objectMap , Toast.LENGTH_SHORT).show();
//                    getDocument();

                    db.collection("Groups")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d(TAG, document.getId() + " => " + document.getData());
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

                    final DocumentReference document = supCollectionReference.document("01vAq1QkMmFq2Nb7jGhp");
                    final Group_Object group_object = new Group_Object();

                    db.runTransaction(new Transaction.Function<Void>() {
                        @NonNull
                        @Override
                        public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                            DocumentSnapshot asd = transaction.get(document);
                            String  newLike = asd.getString(mConstants.NumOfLiks);
                            int i = Integer.parseInt(newLike) +1;
                            transaction.update(document,NumOfLiks,i);
                            Toast.makeText(v.getContext(), "hello" + i, Toast.LENGTH_SHORT).show();
                            // Success
                            return null;
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "Transaction success!");
                            Toast.makeText(v.getContext(), "hello"  , Toast.LENGTH_SHORT).show();
                            holder.mLikeBtn.setBackground(v.getContext().getDrawable(R.mipmap.action_like_accent));

                        }
                    });
////
////                    documentReference.addSnapshotListener(v.getContext(),new EventListener<DocumentSnapshot>() {
////                                @Override
////                                public void onEvent(DocumentSnapshot documentSnapshot , FirebaseFirestoreException e) {
////                                    if (e !=null) {
////                                        Log.d(" TAG ", " Clicked");
////                                        return;
////                                    }
////
//////                                   for (:queryDocumentSnapshots){
//////
//////                                   Group_Object group_object =documentSnapshot.toObject(Group_Object.class);
//////                                   group_object.setDocumentId(documentSnapshot.getId());
//////                                   String documentLikesNum = group_object.getDocumentId();
//////
//////                                   group_object.setmNumOfLiks(mNumberOflikes + 1);
//////                                       mNumberOflikes = group_object.getmNumOfLiks();
//////                                       holder.mLikeBtn.setBackground(getResources().getDrawable(R.mipmap.action_like_accent));
//////                                       int i = model.getmNumOfLiks();
//////                                       holder.mNumOfLikes.setText(i+"");
////
//////                                   }
////
////                                }
////                            });
//
//
////                            Map<String,Object> objectMap = new HashMap<>();
////                            objectMap.put("timestamp", FieldValue.serverTimestamp());
////                            db.collection(MAIN_COLLECTION).document().collection("likes").document().set(objectMap);
////                        GroupsRef.orderBy()



                }
            });


//            // Go To Telegram
            holder.mGroupEnterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
////
                        intentMessageTelegram(v.getContext(),Uri.parse(model.getGroupLink()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        // ============================ImageLink====================================== \\

        // TODO: 7/24/2018

//                holder.mGroupImage.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));

        Upload mCurrentUpload = new Upload();
//                Picasso.with(getContext()).load(model.getPhoto()).into(holder.mGroupImage);
        Log.e(" TAG ", " Clicked" + mCurrentUpload.getImageUrl() + " ");
//
//              holder.AnotherCatogries.setText(model.getGroupName().substring(0,1)  + " A " + model.getGroupDesc().substring(0,1) + " B");
//              Random mRandom = new Random();
//             int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
//             ((GradientDrawable) holder.AnotherCatogries.getBackground()).setColor(color);

    }

//    @Override
//    public void onBindViewHolder(mViewHolder holder, int position) {
//        holder.bind(getSnapshot(position), mListener);
//    }
//    public void getDocument() {
//        // [START get_document]
//        final DocumentReference document = supCollectionReference.document("01vAq1QkMmFq2Nb7jGhp");
//        document.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                    } else {
//                        Log.d(TAG, "No such document");
//                    }
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
//        // [END get_document]
//    }

    // ============================= My Methods  =========================\\

    public void deleteitem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();

    }

    public void mShowOnlyTop(List<Group_Object> mTopCats) {
        group_objects = mTopCats;

    }

    public interface OnRestaurantSelectedListener {
        void onRestaurantSelected(DocumentSnapshot restaurant);

    }

    /**
     * Indicates whether the specified app ins installed and can used as an intent. This
     * method checks the package manager for installed packages that can
     * respond to an intent with the specified app. If no suitable package is
     * found, this method returns false.
     *
     * @param context The application's environment.
     * @param appName The name of the package you want to check
     * @return True if app is installed
     */
    public static boolean isAppAvailable(Context context, String appName) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Intent to send a telegram message
     *
     * @param msg
     */
    public void intentMessageTelegram(Context context,Uri msg) {
        final String appName = "org.telegram.messenger";
        final boolean isAppInstalled = isAppAvailable(Objects.requireNonNull(context), appName);
        if (isAppInstalled) {
            Intent intent = new Intent(Intent.ACTION_VIEW, msg);
            context.startActivity(intent);

//            Intent myIntent = new Intent(Intent.ACTION_SEND);
//            myIntent.setType("text/plain");
//            myIntent.setPackage(appName);
//            myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
//            Objects.requireNonNull(getContext()).startActivity(Intent.createChooser(myIntent, "Share with"));
        } else {
            Toast.makeText(context, "Telegram Not Installed In Your Device", Toast.LENGTH_SHORT).show();
        }
    }


//    static class mViewHolder extends RecyclerView.mViewHolder {
//
//        @BindView(R.id.restaurant_item_image)
//        ImageView imageView;
//
//        @BindView(R.id.restaurant_item_name)
//        TextView nameView;
//
//        @BindView(R.id.restaurant_item_rating)
//        MaterialRatingBar ratingBar;
//
//        @BindView(R.id.restaurant_item_num_ratings)
//        TextView numRatingsView;
//
//        @BindView(R.id.restaurant_item_price)
//        TextView priceView;
//
//        @BindView(R.id.restaurant_item_category)
//        TextView categoryView;
//
//        @BindView(R.id.restaurant_item_city)
//        TextView cityView;
//
//        public mViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//
//        public void bind(final DocumentSnapshot snapshot,
//                         final OnRestaurantSelectedListener listener) {
//
//            Group restaurant = snapshot.toObject(Group.class);
//            Resources resources = itemView.getResources();
//
//            // Load image
//            Glide.with(imageView.getContext())
//                    .load(restaurant.getPhoto())
//                    .into(imageView);
//
//            nameView.setText(restaurant.getName());
//            ratingBar.setRating((float) restaurant.getAvgRating());
//            cityView.setText(restaurant.getLikes());
//            categoryView.setText(restaurant.getCategory());
//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings,
//                    restaurant.getNumRatings()));
//            priceView.setText(RestaurantUtil.getPriceString(restaurant));
//
//            // Click listener
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        listener.onRestaurantSelected(snapshot);
//                    }
//                }
//            });
//        }
//
//    }
}
