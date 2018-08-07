package com.manooz.telgramgroups.Current_Project.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.manooz.telgramgroups.Current_Project.Adapter.RestaurantAdapter;
import com.manooz.telgramgroups.Current_Project.MainActivityViewModel;
import com.manooz.telgramgroups.Current_Project.POJO.Filters;
import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
import com.manooz.telgramgroups.Current_Project.addGroupActivity;
import com.manooz.telgramgroups.R;

import java.util.Objects;

import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION;
import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION2;

public class MainFragment extends Fragment implements RestaurantAdapter.OnRestaurantSelectedListener{

    public  MainFragment (){}


    private static final int LIMIT = 50;
    private static final String TAG = " Home";
    FragmentActivity listener;
    // ======================================== Val ============================================= \\
    // RecyclerView
    RecyclerView mRecyclerView;
    ImageView mFilterBtn, mClearBtn;
    // ======================================== Val ============================================= \\
    private Filters filters;
    private EditText mSearch_keyword;
    private RelativeLayout mCategoryAll, mTopCategory;
    //    Uri mUriImage;
    //    int mNumberOflikes, mNumOfViews, mNumOfComments;
    private FloatingActionButton mFloatingActionButton;
    //firebase
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference GroupsRef = db.collection(MAIN_COLLECTION2);
    private FirebaseFirestore mFirestore;
    private FirestoreRecyclerAdapter mFirestoreRecyclerAdapter;
    private RestaurantAdapter restaurantAdapter;
    private DocumentReference documentReference = db.document(MAIN_COLLECTION + "/Sport").collection("SportsGroups").document();
    private DocumentReference documentReference2 = db.document(MAIN_COLLECTION + "/Sport");

    private FilterDialogFragment mFilterDialog;

    private Context context;
    private MainActivityViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onStart() {
        super.onStart();
        // listener
//        mFirestoreRecyclerAdapter.startListening();
        restaurantAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
//        mFirestoreRecyclerAdapter.stopListening();
        restaurantAdapter.startListening();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = getLayoutInflater().inflate(R.layout.activity_main_fragment, container, false);


        mFilterDialog = new FilterDialogFragment();
        mRecyclerView = view.findViewById(R.id.mRv8);

        mGetMyListOfCats2();

        // Find Widgets

        mSearch_keyword = view.findViewById(R.id.search_keyword);
        mFloatingActionButton = (Objects.requireNonNull(getActivity()).findViewById(R.id.add_post_btn));
        mFilterBtn = view.findViewById(R.id.button_filter);
        mClearBtn = view.findViewById(R.id.button_clear_filter);
                // Click Events
        mClickListeners();


        return view;

    }

    private void mGetMyListOfCats2() {


        Query mQuery = FirebaseFirestore.getInstance()
                .collection(MAIN_COLLECTION)
                .document("Sport")
                .collection("SportsGroups")
                .orderBy("timestamp", Query.Direction.DESCENDING);
//
////        Query query = GroupsRef.orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Group_Object>
                options = new FirestoreRecyclerOptions.Builder<Group_Object>().setQuery(mQuery,Group_Object.class).build();
//
//        // TODO: 8/6/2018
//        List<Group_Object> group_objects = new ArrayList<>();
//        group_objects.add(new Group_Object());

        restaurantAdapter = new RestaurantAdapter(options,this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(restaurantAdapter);
//        restaurantAdapter.mShowOnlyTop(group_objects.subList(0,4));
    }

    private void mClickListeners() {

        mFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FilterDialogFragment().show(getActivity().getSupportFragmentManager(), "FilterDialog");

            }
        });


        mClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               mFilterDialog.resetFilters();

            }
        });




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), " Hello ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), addGroupActivity.class);
                startActivity(intent);


            }
        });
    }


    @Override
    public void onRestaurantSelected(DocumentSnapshot restaurant) {

    }

    //    =========================================== End LifeCycle ====================================== \\
//    ===========================================   My Methods    ======================================  \\
    //############# https://github.com/firebase/FirebaseUI-Android/tree/master/firestore ############## \\
//    @NonNull
//    public FirestoreRecyclerAdapter getFirestoreRecyclerAdapter() {
////
//        Query mQuery = FirebaseFirestore.getInstance()
//                .collection(MAIN_COLLECTION)
//                .document("Sport")
//                .collection("SportsGroups")
//                .orderBy("timestamp", Query.Direction.DESCENDING);
//
//
//        FirestoreRecyclerOptions<Group_Object> options = new FirestoreRecyclerOptions.Builder<Group_Object>()
//                .setQuery(mQuery, Group_Object.class).build();
//
//
//        return new FirestoreRecyclerAdapter<Group_Object, UserViewHolder>(options) {
//            @SuppressLint("SetTextI18n")
//            @Override
//            protected void onBindViewHolder(@NonNull final UserViewHolder holder, int position, @NonNull final Group_Object model) {
//                Log.d("USER", "DATA: " + model.getGroupName());
////                holder.BindView(position, model);
//
//
//                try {
//                    holder.mGroupName.setText(model.getGroupName() + "");
//                    final String mFirst = holder.mGroupName.getText().toString();
//                    holder.mGroupDesc.setText(model.getGroupDesc() + "");
//                    final String mSecond = holder.mGroupDesc.getText().toString();
//                    holder.mUserName.setText(String.format("%s", model.getUserName()));
//                    holder.mGroupLink.setText(model.getGroupLink() + "");
//                    final String mLink = holder.mGroupLink.getText().toString();
//                    holder.mCatogries.setText(model.getCatogries() + "");
//                    holder.materialRatingBar.setRating((float) model.getRatings());
//                    holder.mNumOfLikes.setText(model.getmNumOfLiks() + "");
//                    holder.mNumOfComments.setText(model.getmNumOfComments() + "");
//                    holder.materialRatingBar.setNumStars(5);
//                    holder.mRateText.setText(holder.materialRatingBar.getNumStars() + "");
//                    holder.mNumOfViews.setText(model.getmNumOfViews() + "");
//
//
//                    holder.mShareGroupBtn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Uri uri2 = Uri.parse("https://play.google.com/store/apps/com.manooz.telgramgroups");
//                            Uri uri3 = Uri.parse(mLink);
//
//
//                            String mMessageBody =
//                                    mFirst  + " \n \n " + mSecond + " " + " \n \n \n "
//                                            + " رابط القناة :  " + "\n \n " + uri3 + " \n \n " +
//                                            " من تطبيق قروبات تليجرام  \n \n    حمل التطبيق الآن من الرابط : \n   \n " + "\n " + uri2;
//
//                            Intent ShareIntent = new Intent(Intent.ACTION_SEND);
//                            ShareIntent.putExtra(Intent.EXTRA_TEXT, mMessageBody);
//                            ShareIntent.setType("text/plain");
//                            startActivity(Intent.createChooser(ShareIntent, "Share via"));
//
//
//                        }
//                    });
//                    holder.mCommentBtn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            Intent intent = new Intent(getActivity(), GroupDetails.class);
//                            startActivity(intent);
//
//
//                        }
//                    });
//
//                    holder.mLikeBtn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//
//
////                            GroupsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
////                                @Override
////                                public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots,
////                                                    @javax.annotation.Nullable FirebaseFirestoreException e) {
////                                    if (e !=null) {
////                                        return;
////                                    }
////
////                                   for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
////
////                                   Group_Object group_object =documentSnapshot.toObject(Group_Object.class);
////                                   group_object.setDocumentId(documentSnapshot.getId());
////                                   String documentLikesNum = group_object.getDocumentId();
////
////                                   group_object.setmNumOfLiks(mNumberOflikes + 1);
////                                       mNumberOflikes = group_object.getmNumOfLiks();
////                                       holder.mLikeBtn.setBackground(getResources().getDrawable(R.mipmap.action_like_accent));
////                                       int i = model.getmNumOfLiks();
////                                       holder.mNumOfLikes.setText(i+"");
////
////                                   }
////                                }
////                            });
//
//
////                            Map<String,Object> objectMap = new HashMap<>();
////                            objectMap.put("timestamp", FieldValue.serverTimestamp());
////                            db.collection(MAIN_COLLECTION).document().collection("likes").document().set(objectMap);
////                        GroupsRef.orderBy()
//
//
//                        }
//                    });
//
//
////                    // Go To Telegram
////                    holder.mGroupEnterBtn.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////
////                            try {
//////
////                                intentMessageTelegram(Uri.parse(model.getGroupLink()));
////                            } catch (Exception e) {
////                                e.printStackTrace();
////                            }
////                        }
////                    });
//
//
//                } catch (NullPointerException e) {
//                    e.printStackTrace();
//                }
//
//
//                // ============================ImageLink====================================== \\
//
//
////                holder.mGroupImage.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
//
//                Upload mCurrentUpload = new Upload();
////                Picasso.with(getContext()).load(model.getPhoto()).into(holder.mGroupImage);
//                Log.e(" TAG ", " Clicked" + mCurrentUpload.getImageUrl() + " ");
////
////              holder.AnotherCatogries.setText(model.getGroupName().substring(0,1)  + " A " + model.getGroupDesc().substring(0,1) + " B");
////              Random mRandom = new Random();
////             int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
////             ((GradientDrawable) holder.AnotherCatogries.getBackground()).setColor(color);
//
//            }
//
//            @NonNull
//            @Override
//            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
//
//                return new UserViewHolder(view);
//            }
//        };
//    }


//    private void Filters() {
//
//        Task task1 = GroupsRef.whereGreaterThan("mNumOfLikes", 5).orderBy("mNumOfLikes").get();
//        Task task2 = GroupsRef.whereGreaterThan("mNumOfLikes", 1).orderBy("mNumOfLikes").get();
//        Task<List<QuerySnapshot>> mAllTasks = Tasks.whenAllSuccess(task1, task2);
//        mAllTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
//            @Override
//            public void onSuccess(List<QuerySnapshot> querySnapshot) {
//                for (QuerySnapshot querySnapshot1 : querySnapshot) {
//                    for (QueryDocumentSnapshot documentSnapshot : querySnapshot1) {
////
//                        Group_Object group_object = documentSnapshot.toObject(Group_Object.class);
//                        group_object.setDocumentId(documentSnapshot.getId());
//
//
////
//                    }
//                }
//                Toast.makeText(getActivity(), " Hala ", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }


    // ======================================== My Methods ============================================= \\
//    https://stackoverflow.com/questions/21627167/how-to-send-a-intent-with-telegram

}
