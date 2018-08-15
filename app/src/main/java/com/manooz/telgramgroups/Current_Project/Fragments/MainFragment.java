package com.manooz.telgramgroups.Current_Project.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.manooz.telgramgroups.Current_Project.Adapter.GroupAdapter2;
import com.manooz.telgramgroups.Current_Project.FilterDialogFragment;
import com.manooz.telgramgroups.Current_Project.GroupDetails;
import com.manooz.telgramgroups.Current_Project.MainActivityViewModel;
import com.manooz.telgramgroups.Current_Project.POJO.Filters;
import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
import com.manooz.telgramgroups.Current_Project.addGroupActivity;
import com.manooz.telgramgroups.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION;
import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION2;

public class MainFragment extends Fragment implements GroupAdapter2.OnRestaurantSelectedListener {

    //,FilterDialogFragment.FilterListener
    public MainFragment() {
    }

    // ======================================== Statics Val ===================================== \\
    private static final int LIMIT = 50;
    private static final String TAG = "Home";
    //    FragmentActivity listener;
    // ======================================== Val ============================================= \\
    private RecyclerView mRecyclerView, mRecyclerView2;
    private GroupAdapter2 mAdapter, mAdapter2;
    private Filters filters;
    // ======================================== Widgets ========================================= \\
    private ImageView mFilterBtn, mClearBtn;
    private TextView mCurrentSearchView, mCurrentSortByView;
    private ImageView mSearchBtn;
    private EditText mSearch_keyword;
    private FloatingActionButton mFloatingActionButton;
    // ======================================== Firebase ======================================== \\
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private Query mQueryTimeStamp, mQueryTop;

    private CollectionReference GroupsRef = db.collection(MAIN_COLLECTION2);
    private List<Group_Object> group_objects;
    private FilterDialogFragment mFilterDialog;
    private Context context;
    private MainActivityViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = getLayoutInflater().inflate(R.layout.activity_main_fragment, container, false);

        mRecyclerView = view.findViewById(R.id.mRv8);
        mRecyclerView2 = view.findViewById(R.id.rv);
        group_objects = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        // View model
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        // Initialize Firestore and the main RecyclerView
        initFirestore();
        initRecyclerView();
        initRecyclerView2();
        // Filter Dialog
        mFilterDialog = new FilterDialogFragment();

        // testing =====================================

        // testing ===================================== end


//        GroupsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
//                    Group_Object group_object =queryDocumentSnapshot.toObject(Group_Object.class);
//                    group_object.setDocumentId(queryDocumentSnapshot.getId());
//                    String id = group_object.getDocumentId();
//                    Toast.makeText(getActivity(), "Hi" + id, Toast.LENGTH_SHORT).show();
//
//
//                }
//
//
//
//            }
//        });


//        mGetMyListOfCats2();

        // Find Widgets

        mSearch_keyword = view.findViewById(R.id.search_keyword);
        mFloatingActionButton = (Objects.requireNonNull(getActivity()).findViewById(R.id.add_post_btn));
        mFilterBtn = view.findViewById(R.id.button_filter);
        mClearBtn = view.findViewById(R.id.button_clear_filter);
        mSearchBtn = view.findViewById(R.id.mSearchBtn);
        mCurrentSearchView = view.findViewById(R.id.text_current_search);
        mCurrentSortByView = view.findViewById(R.id.text_current_sort_by);


        // Click Events
        mClickListeners();


        return view;

    }


    //===================================== My Methods ================================== \\

    private void initFirestore() {
//        // TODO(developer): Implement
        if (firebaseAuth.getCurrentUser() != null) {
            firebaseFirestore = FirebaseFirestore.getInstance();


        }

        final Query firstQuery = db.collection("Groups").orderBy("timestamp", Query.Direction.DESCENDING).limit(3);
        firstQuery.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        String mGroupId = doc.getDocument().getId();
                        Group_Object group_object1 = doc.getDocument().toObject(Group_Object.class).withId(mGroupId);
                        group_objects.add(group_object1);

                        mAdapter.setQuery(firstQuery);


                    }
                }
            }
        });


        mQueryTimeStamp = db.collection(MAIN_COLLECTION)
                .orderBy(Group_Object.FIELD_NumOfRatings, Query.Direction.DESCENDING)
                .limit(LIMIT);

        mQueryTop = db.collection(MAIN_COLLECTION)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(LIMIT);

    }

    private void initRecyclerView2() {

        if (mQueryTop == null) {
            Log.w(TAG, "No query, not initializing RecyclerView");
        }

        mAdapter2 = new GroupAdapter2(mQueryTop, this) {

            @Override
            protected void onDataChanged() {
                // Show/hide content if the query returns empty.
                if (getItemCount() == 0) {
//                    mRecyclerView2.setVisibility(View.GONE);
//                    mEmptyView.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "onDataChanged = items = 0", Toast.LENGTH_SHORT).show();
                } else {
                    mRecyclerView.setVisibility(View.VISIBLE);
//                    mEmptyView.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "onDataChanged", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                // Show a snackbar on errors
//                Snackbar.make(getContext().findViewById(android.R.id.content),
//                        "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
                Toast.makeText(getContext(), " check logs for info ", Toast.LENGTH_SHORT).show();
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mAdapter2);
    }

    private void initRecyclerView() {
        if (mQueryTimeStamp == null) {
            Log.w(TAG, "No query, not initializing RecyclerView");
        }

        mAdapter = new GroupAdapter2(mQueryTimeStamp, this) {

            @Override
            protected void onDataChanged() {
                // Show/hide content if the query returns empty.
                if (getItemCount() == 0) {
//                    mRecyclerView2.setVisibility(View.GONE);
//                    mEmptyView.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "onDataChanged = items = 0", Toast.LENGTH_SHORT).show();
                } else {
                    mRecyclerView2.setVisibility(View.VISIBLE);
//                    mEmptyView.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "onDataChanged", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                // Show a snackbar on errors
//                Snackbar.make(getContext().findViewById(android.R.id.content),
//                        "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
                Toast.makeText(getContext(), " check logs for info ", Toast.LENGTH_SHORT).show();
            }
        };
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView2.setAdapter(mAdapter);


    }


    // TODO: 8/15/2018
//    @Override
//    public void onFilter(Filters filters) {
//
//        // Construct query basic query
//        Query query = db.collection(MAIN_COLLECTION);
//        // Category (equality filter)
//        if (filters.hasCategory()) {
//            query = query.whereEqualTo(Group_Object.FIELD_CATEGORY, filters.getCategory());
//        }
////
////        // City (equality filter)
////        if (filters.haslikes()) {
////            query = query.whereEqualTo("city", filters.getCity());
////        }
////
////        // Price (equality filter)
////        if (filters.haslikes()) {
////            query = query.whereEqualTo("price", filters.getLikes());
////        }
//
//        // Sort by (orderBy with direction)
//        if (filters.hasSortBy()) {
//            query = query.orderBy(filters.getSortBy(), filters.getSortDirection());
//        }
//
//        // Limit items
//        query = query.limit(LIMIT);
//
//        // Update the query
//        mQueryTimeStamp = query;
//        mAdapter.setQuery(query);
//
//        // Set header
//        mCurrentSearchView.setText(filters.getSearchDescription(getContext()));
//        mCurrentSortByView.setText(filters.getOrderDescription(getContext()));
//
//        // Save filters
//        mViewModel.setFilters(filters);
//
//
//    }
//    private void mGetMyListOfCats2() {
//
//
//        Query mQueryTimeStamp = FirebaseFirestore.getInstance()
//                .collection(MAIN_COLLECTION)
//                .orderBy("timestamp", Query.Direction.DESCENDING);
////
//////        Query query = GroupsRef.orderBy("timestamp", Query.Direction.DESCENDING);
//        FirestoreRecyclerOptions<Group_Object>
//                options = new FirestoreRecyclerOptions.Builder<Group_Object>().setQuery(mQueryTimeStamp,Group_Object.class).build();
////
////        // TODO: 8/6/2018
//        List<Group_Object> group_objects = new ArrayList<>();
//        group_objects.add(new Group_Object());
//
////        restaurantAdapter = new RestaurantAdapter(options,this);
////        mRecyclerView.setHasFixedSize(true);
////        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
////        mRecyclerView.setAdapter(restaurantAdapter);
//
//
//        // // TODO: 8/13/2018
//        // Delete Items From RecycleView And Firestore When SWIPE
//
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
//                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//
//                restaurantAdapter.deleteitem(viewHolder.getAdapterPosition());
//            }
//        }).attachToRecyclerView(mRecyclerView);
//
//        // Show Only 4 Items
////        Not Working
////        if (group_objects.size()>0){
////            restaurantAdapter.mShowOnlyTop(group_objects.subList(0,4));
////        }
//
//    }

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
                mFilterDialog.resetFilters();
                // TODO: 8/15/2018
//                onFilter(Filters.getDefault());
//            }
//        });

                mSearchBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mSearchedText = mSearch_keyword.getText().toString();
                        Query mSearchQuery = db.collection(MAIN_COLLECTION);
                        mSearchQuery.whereEqualTo(Group_Object.FIELD_GroupName, mSearchedText);
                        mAdapter.setQuery(mSearchQuery);


                    }
                });
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

                //
//                onAddItemsClicked();


            }
        });
    }


    // dummyData Testing
//    private void onAddItemsClicked() {
//        CollectionReference restaurants = db.collection(MAIN_COLLECTION);
//        for (int i = 0; i < 10; i++) {
//            //Get a randomLongString restraunt POJO
//            Group_Object restaurant = GroupsUtil.getRandom(getActivity());
//            // Add a new document to the restaurants collection
//            restaurants.add(restaurant);
//        }
//    }


    @Override
    public void onRestaurantSelected(DocumentSnapshot snapshot) {
        // Go to the details page for the selected Group
        Intent intent = new Intent(getActivity(), GroupDetails.class);
        intent.putExtra(GroupDetails.KEY_RESTAURANT_ID, snapshot.getId());
        MainFragment.this.startActivity(intent);

    }


    //===================================== LifeCycle =================================== \\

    @Override
    public void onStart() {
        super.onStart();
        // listener
//        mFirestoreRecyclerAdapter.startListening();
        // Apply filters
//        onFilter(mViewModel.getFilters());
        if (mAdapter != null) {
            mAdapter.startListening();
        }
        if (mAdapter2 != null) {
            mAdapter2.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        mFirestoreRecyclerAdapter.stopListening();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
        if (mAdapter2 != null) {
            mAdapter2.startListening();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof Activity) {
//            this.listener = (FragmentActivity) context;
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // TODO: 8/14/2018
//        this.listener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

//    =========================================== End LifeCycle ====================================== \\
//    ===========================================   My Methods    ======================================  \\
//############# https://github.com/firebase/FirebaseUI-Android/tree/master/firestore ############## \\
//    @NonNull
//    public FirestoreRecyclerAdapter getFirestoreRecyclerAdapter() {
////
//        Query mQueryTimeStamp = FirebaseFirestore.getInstance()
//                .collection(MAIN_COLLECTION)
//                .document("Sport")
//                .collection("SportsGroups")
//                .orderBy("timestamp", Query.Direction.DESCENDING);
//
//
//        FirestoreRecyclerOptions<Group_Object> options = new FirestoreRecyclerOptions.Builder<Group_Object>()
//                .setQuery(mQueryTimeStamp, Group_Object.class).build();
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
//                    holder.mCatogries.setText(model.getCategory() + "");
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
