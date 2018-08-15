package com.manooz.telgramgroups.Current_Project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;
import com.manooz.telgramgroups.Current_Project.GroupDetails;
import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
import com.manooz.telgramgroups.Current_Project.POJO.Rating;
import com.manooz.telgramgroups.Current_Project.Utily.mConstants;
import com.manooz.telgramgroups.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.ButterKnife;

import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION;
import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION2;
import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.NumOfLiks;

public class GroupAdapter2 extends FirestoreAdapter<GroupAdapter2.ViewHolder> {

    private Context context;
    private static final String TAG = "GroupAdapter2";
    private OnRestaurantSelectedListener mListener;
    public interface OnRestaurantSelectedListener {

        void onRestaurantSelected(DocumentSnapshot restaurant);

    }


    public GroupAdapter2(Query query, OnRestaurantSelectedListener listener) {
        super(query);
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.list_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);

    }

    //====================================== ViewHolder =========================================\\

    static class ViewHolder extends RecyclerView.ViewHolder {

        private FirebaseAuth auth = FirebaseAuth.getInstance();
        private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        private DocumentReference mRestaurantRef;
        private ListenerRegistration mRestaurantRegistration;
        private RatingAdapter mRatingAdapter;
        private CollectionReference GroupsRef = mFirestore.collection(MAIN_COLLECTION);


        CardView cardView;
        TextView mUserName, mCatogries, mGroupLink, mGroupDesc, mGroupName, mCity;
        TextView mNumOfComments, mNumOfLikes, mNumOfViews, mRateText;
        ImageButton mGroupEnterBtn, mShareGroupBtn, mFavBtn, mLikeBtn, mCommentBtn, mViewsBtn, mReportBtn;
        ImageView mGroupImage;
        me.zhanghai.android.materialratingbar.MaterialRatingBar materialRatingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @SuppressLint("SetTextI18n")
        public void bind(final DocumentSnapshot snapshot,
                         final OnRestaurantSelectedListener listener) {


            //ViewGroup
            cardView = itemView.findViewById(R.id.cardViewGroup);
            // Views
            mGroupName = itemView.findViewById(R.id.mName);
            mGroupDesc = itemView.findViewById(R.id.mGroupDesc);
            mGroupLink = itemView.findViewById(R.id.mGroupLink);
            mUserName = itemView.findViewById(R.id.mCatPic);
            mCatogries = itemView.findViewById(R.id.mCatogaries);
            mGroupImage = itemView.findViewById(R.id.mGroupImage);
            mCity = itemView.findViewById(R.id.mCity);
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

            final Group_Object groupObject = snapshot.toObject(Group_Object.class);

            Resources resources = itemView.getResources();
            final String blogPostId = groupObject.GroupId;
            final String cuurentUser =  auth.getCurrentUser().getUid();



            mGroupName.setText(groupObject.getGroupName() + "");
            final String mFirst = mGroupName.getText().toString();
            mGroupDesc.setText(groupObject.getGroupDesc() + "");
            final String mSecond = mGroupDesc.getText().toString();
            mUserName.setText(groupObject.getUserName() + "");
            mGroupLink.setText(groupObject.getGroupLink() + "");
            final String mLink = mGroupLink.getText().toString();

            mCatogries.setText(groupObject.getCategories() + "");
//            mCatogries.setText(groupObject.getDocumentId() + "");

            materialRatingBar.setRating((float) groupObject.getRatings());
            mNumOfLikes.setText(groupObject.getmNumOfLiks() + "");
            mNumOfComments.setText(groupObject.getmNumOfComments() + "");
            materialRatingBar.setNumStars(5);
            mRateText.setText(resources.getString(R.string.fmt_num_ratings,
                    groupObject.getmNumOfRatings()));
            mNumOfViews.setText(groupObject.getmNumOfViews() + "");
            mCity.setText(groupObject.getCity() + "");


            //Get Likes Count
            mFirestore.collection("Groups/" + blogPostId + "/Likes").addSnapshotListener( new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    if(!documentSnapshots.isEmpty()){

                        int count = documentSnapshots.size();

                        updateLikesCount(count);

                    } else {

                        updateLikesCount(0);

                    }

                }

                private void updateLikesCount(int count) {
                    mNumOfLikes = itemView.findViewById(R.id.mNumOfLiksTxt);
                    mNumOfLikes.setText(count + " Likes");
                }
            });

            //Get Likes
            mFirestore.collection("Groups/" + blogPostId + "/Likes").document(cuurentUser).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                    if(documentSnapshot.exists()){

                        mLikeBtn.setBackground(itemView.getContext().getDrawable(R.mipmap.action_like_accent));

                    } else {

                        mLikeBtn.setBackground(itemView.getContext().getDrawable(R.mipmap.action_like_gray));

                    }

                }
            });

            // Clicks Handling ======================================================================================================
            // Likes Feature
            mLikeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {



                    mFirestore.collection("Groups/" + blogPostId + "/Likes").document(cuurentUser).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            if(!task.getResult().exists()){

                                Map<String, Object> likesMap = new HashMap<>();
                                likesMap.put("timestamp", FieldValue.serverTimestamp());

                                mFirestore.collection("Groups/" + blogPostId + "/Likes").document(cuurentUser).set(likesMap);

                            } else {

                                mFirestore.collection("Groups/" + blogPostId + "/Likes").document(cuurentUser).delete();

                            }

                        }
                    }).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Toast.makeText(v.getContext(), "Liked", Toast.LENGTH_SHORT).show();
                            Log.e(TAG,documentSnapshot.getId()+"" );

                        }
                    });
                }
            });








            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), GroupDetails.class);
                    v.getContext().startActivity(intent);
                }
            });
            mShareGroupBtn.setOnClickListener(new View.OnClickListener() {
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

            // Go To Telegram
            mGroupEnterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        intentMessageTelegram(v.getContext(), Uri.parse(groupObject.getGroupLink()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            // Comments
            mCommentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), GroupDetails.class);
                    v.getContext().startActivity(intent);


                }
            });



            // Report
            mReportBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), " شكرا لك", Toast.LENGTH_SHORT).show();


                }
            });


            // Load image
//            Glide.with(imageView.getContext())
//                    .load(restaurant.getPhoto())
//                    .into(imageView);
//
//            nameView.setText(restaurant.getName());
//            ratingBar.setRating((float) restaurant.getAvgRating());
//            cityView.setText(restaurant.getCity());
//            categoryView.setText(restaurant.getCategory());
//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings,
//                    restaurant.getNumRatings()));
//            priceView.setText(RestaurantUtil.getPriceString(restaurant));

            // Click listener
            // Not Working
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onRestaurantSelected(snapshot);
                        Toast.makeText(view.getContext(), " Halaa", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(view.getContext(), GroupDetails.class);
                        view.getContext().startActivity(intent);
                    }
                }
            });

        }

    }

    //====================================== My Methods =========================================\\
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
    public static void intentMessageTelegram(Context context, Uri msg) {
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

}
