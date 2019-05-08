////
////package com.manooz.telgramgroups.Current_Project.Fragments;
////
////import android.app.Activity;
////import android.arch.lifecycle.ViewModelProviders;
////import android.content.Context;
////import android.content.Intent;
////import android.support.annotation.NonNull;
////import android.support.annotation.Nullable;
////import android.support.design.widget.FloatingActionButton;
////import android.support.v4.app.Fragment;
////import android.support.v4.app.FragmentActivity;
////import android.support.v7.app.AppCompatActivity;
////import android.os.Bundle;
////import android.support.v7.widget.DividerItemDecoration;
////import android.support.v7.widget.GridLayoutManager;
////import android.support.v7.widget.LinearLayoutManager;
////import android.support.v7.widget.RecyclerView;
////import android.util.Log;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.EditText;
////import android.widget.ImageView;
////import android.widget.RelativeLayout;
////import android.widget.Toast;
////
////import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
////import com.firebase.ui.firestore.FirestoreRecyclerOptions;
////import com.google.firebase.firestore.CollectionReference;
////import com.google.firebase.firestore.DocumentReference;
////import com.google.firebase.firestore.DocumentSnapshot;
////import com.google.firebase.firestore.FirebaseFirestore;
////import com.google.firebase.firestore.Query;
////import com.manooz.telgramgroups.Current_Project.Adapter.CategoriesAdapter;
////import com.manooz.telgramgroups.Current_Project.Adapter.RestaurantAdapter;
////import com.manooz.telgramgroups.Current_Project.MainActivityViewModel;
////import com.manooz.telgramgroups.Current_Project.POJO.Catogery;
////import com.manooz.telgramgroups.Current_Project.POJO.Filters;
////import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
////import com.manooz.telgramgroups.Current_Project.RecyclerItemClickListener;
////import com.manooz.telgramgroups.Current_Project.addGroupActivity;
////import com.manooz.telgramgroups.R;
////
////import java.util.ArrayList;
////import java.util.List;
////import java.util.Objects;
////
////import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
////import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
////
////import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION;
////import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION2;
////
////public class MainFragment extends Fragment implements RestaurantAdapter.OnRestaurantSelectedListener{
////
////    private static final int LIMIT = 50;
////    private static final String TAG = " Home";
////    FragmentActivity listener;
////    // ======================================== Val ============================================= \\
////    // RecyclerView
////    RecyclerView mRecyclerView, mRecyclerView2;
////    ImageView mFilterBtn, mClearBtn;
////    // ======================================== Val ============================================= \\
////    private Filters filters;
////    private EditText mSearch_keyword;
////    private RelativeLayout mCategoryAll, mTopCategory;
////    //    Uri mUriImage;
////    //    int mNumberOflikes, mNumOfViews, mNumOfComments;
////    private FloatingActionButton mFloatingActionButton;
////    //firebase
////    private FirebaseFirestore db = FirebaseFirestore.getInstance();
////    private CollectionReference GroupsRef = db.collection(MAIN_COLLECTION2);
////    private FirebaseFirestore mFirestore;
////    private FirestoreRecyclerAdapter mFirestoreRecyclerAdapter;
////    private RestaurantAdapter restaurantAdapter;
////    private DocumentReference documentReference = db.document(MAIN_COLLECTION + "/Sport").collection("SportsGroups").document();
////    private DocumentReference documentReference2 = db.document(MAIN_COLLECTION + "/Sport");
////
////    private FilterDialogFragment mFilterDialog;
////
////    private Context context;
////    private MainActivityViewModel mViewModel;
////
////    @Override
////    public void onCreate(@Nullable Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setHasOptionsMenu(true);
////
////    }
////
////    @Override
////    public void onStart() {
////        super.onStart();
////        // listener
//////        mFirestoreRecyclerAdapter.startListening();
////        restaurantAdapter.startListening();
////
////    }
////
////    @Override
////    public void onStop() {
////        super.onStop();
//////        mFirestoreRecyclerAdapter.stopListening();
////        restaurantAdapter.startListening();
////    }
////
////    @Override
////    public void onAttach(Context context) {
////        super.onAttach(context);
////        if (context instanceof Activity) {
////            this.listener = (FragmentActivity) context;
////        }
////    }
////
////    @Override
////    public void onDetach() {
////        super.onDetach();
////        this.listener = null;
////    }
////
////    @Override
////    public void onActivityCreated(Bundle savedInstanceState) {
////        super.onActivityCreated(savedInstanceState);
////    }
////
////    @Nullable
////    @Override
////    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//////        https://www.coderefer.com/android-parcelable-example/
////
////        final View view = getLayoutInflater().inflate(R.layout.activity_main_fragment, container, false);
////
////        // View model
////        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
////        mFilterDialog = new FilterDialogFragment();
////
////        // Create mFirestoreRecyclerAdapter passing in the sample user data
//////        mFirestoreRecyclerAdapter = getFirestoreRecyclerAdapter();
////
////        // Lookup the recyclerview in activity layout
////
////        mRecyclerView = view.findViewById(R.id.mRv1);
////        mRecyclerView2 = view.findViewById(R.id.mRv4);
////        // get my first list
////        mGetMyListOfCats();
////        // get my second list
////        mGetMyListOfCats2();
////        // Find Widgets
////        mSearch_keyword = view.findViewById(R.id.search_keyword);
////        mFloatingActionButton = (Objects.requireNonNull(getActivity()).findViewById(R.id.add_post_btn));
////        mFilterBtn = view.findViewById(R.id.button_filter);
////        mClearBtn = view.findViewById(R.id.button_clear_filter);
////        mCategoryAll = view.findViewById(R.id.categories_all);
////        mTopCategory = view.findViewById(R.id.categories_all2);
////
////        // Click Events
////        mClickListeners();
////
////
////        return view;
////
////    }
////
////    private void mGetMyListOfCats2() {
////
////
////        Query mQuery = FirebaseFirestore.getInstance()
////                .collection(MAIN_COLLECTION)
////                .document("Sport")
////                .collection("SportsGroups")
////                .orderBy("timestamp", Query.Direction.DESCENDING);
//////
////////        Query query = GroupsRef.orderBy("timestamp", Query.Direction.DESCENDING);
////        FirestoreRecyclerOptions<Group_Object>
////                options = new FirestoreRecyclerOptions.Builder<Group_Object>().setQuery(mQuery,Group_Object.class).build();
//////
//////        // TODO: 8/6/2018
//////        List<Group_Object> group_objects = new ArrayList<>();
//////        group_objects.add(new Group_Object());
////
////        restaurantAdapter = new RestaurantAdapter(options,this);
////        mRecyclerView.setHasFixedSize(true);
////        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
////        mRecyclerView.setAdapter(restaurantAdapter);
//////        restaurantAdapter.mShowOnlyTop(group_objects.subList(0,4));
////    }
////
////    private void mClickListeners() {
////
////        mFilterBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                new FilterDialogFragment().show(getActivity().getSupportFragmentManager(), "FilterDialog");
////
////            }
////        });
////
////
////        mClearBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//////               mFilterDialog.resetFilters();
////
////            }
////        });
////
////        mCategoryAll.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//////                Intent detailActiivtyIntent = new Intent(getActivity(), ListOfGroups.class);
//////                Bundle bundle = new Bundle();
//////                bundle.putString(mConstants.TITLE, getString(R.string.all_categories));
//////                detailActiivtyIntent.putExtra(mConstants.DATA, bundle);
//////                startActivity(detailActiivtyIntent);
////            }
////        });
////
////        mTopCategory.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////
////            }
////        });
////
////
////    }
////
////
////    @Override
////    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
////        super.onViewCreated(view, savedInstanceState);
////
////        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(getActivity(), " Hello ", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(getActivity(), addGroupActivity.class);
////                startActivity(intent);
////
////
////            }
////        });
////    }
////
////
////    private void mGetMyListOfCats() {
////
////
////        List<Catogery> catogeries = new ArrayList<>();
//////        final ArrayList<Catogery> mTopCat = new ArrayList<>();
////        try {
////            catogeries = new ArrayList<>(10);
////            catogeries.add(new Catogery("Sport",
////                    getString(R.string.groups_names), R.drawable.sports));
////            catogeries.add(new Catogery("Jobs",
////                    getString(R.string.groups_names), R.drawable.jobs));
////            catogeries.add(new Catogery("Educations",
////                    getString(R.string.groups_names), R.drawable.educations));
////            catogeries.add(new Catogery("Tech",
////                    getString(R.string.groups_names), R.drawable.tech));
////            catogeries.add(new Catogery("Health",
////                    getString(R.string.groups_names), R.drawable.health));
////            catogeries.add(new Catogery("Shopping",
////                    getString(R.string.groups_names), R.drawable.shopping));
////            catogeries.add(new Catogery("RealState",
////                    getString(R.string.groups_names), R.drawable.realstate));
////            catogeries.add(new Catogery("Islamic",
////                    getString(R.string.groups_names), R.drawable.islamic));
////            catogeries.add(new Catogery("Games",
////                    getString(R.string.groups_names), R.drawable.games));
////            catogeries.add(new Catogery("Business",
////                    getString(R.string.groups_names), R.drawable.business));
////            catogeries.add(new Catogery("Jokes",
////                    getString(R.string.groups_names), R.drawable.jokes));
////            catogeries.add(new Catogery("Pics",
////                    getString(R.string.groups_names), R.drawable.pics));
////            catogeries.add(new Catogery("Cooks",
////                    getString(R.string.groups_names), R.drawable.cooking));
////            catogeries.add(new Catogery("Anime",
////                    getString(R.string.groups_names), R.drawable.anime));
////            catogeries.add(new Catogery("UnSorted",
////                    getString(R.string.groups_names), R.drawable.unsorted));
////
////
////        } catch (ArrayIndexOutOfBoundsException e) {
////            e.printStackTrace();
////            Log.d(TAG, catogeries.size() + " " + e.getMessage());
////
////        }
////
////
////        // Set layout manager to position the items
////        mRecyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), 2));
////        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(catogeries);
////        // show only first 4 items
////        categoriesAdapter.mShowOnlyTop(catogeries.subList(0, 4));
////
////
//////        mRecyclerView2.setHasFixedSize(true);
//////        mRecyclerView2.setItemViewCacheSize(3);
//////        categoriesAdapter.notifyDataSetChanged();
////
////        // Attach the mFirestoreRecyclerAdapter to the recyclerview to populate items
//////        mRecyclerView2.setAdapter(categoriesAdapter);
////        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
////        mRecyclerView2.addItemDecoration(itemDecoration);
////
////        //Animations
////        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(categoriesAdapter);
////        alphaAdapter.setDuration(500);
////        mRecyclerView2.setAdapter(new ScaleInAnimationAdapter(categoriesAdapter));
////
////        // ======================================== Clicks Handling  ============================================= \\
////
//////        https://stackoverflow.com/questions/28515049/android-content-context-getpackagename-on-a-null-object-reference
////        mRecyclerView2.addOnItemTouchListener(new RecyclerItemClickListener(context, mRecyclerView2, new RecyclerItemClickListener.OnItemClickListener() {
////            @Override
////            public void onItemClick(View view, int position) {
////                switch (position) {
////                    case 0:
////                        Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
////                        Query mQuerySport = FirebaseFirestore.getInstance()
////                                .collection(MAIN_COLLECTION)
////                                .document("Sport")
////                                .collection("SportsGroups")
////                                .orderBy("timestamp", Query.Direction.DESCENDING);
////
////
////
////                        break;
////
////                    case 1:
////                        Toast.makeText(getContext(), "Hello2", Toast.LENGTH_SHORT).show();
////                        Query mQueryJobs = FirebaseFirestore.getInstance()
////                                .collection(MAIN_COLLECTION)
////                                .document("Jobs")
////                                .collection("JobsGroups")
////                                .orderBy("timestamp", Query.Direction.DESCENDING);
////
////
////                        break;
////                    case 2:
////                        Toast.makeText(getContext(), "Hello3", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////
////                    case 3:
////                        Toast.makeText(getContext(), "Hello4", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////                    case 4:
////                        Toast.makeText(getContext(), "Hello5", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////
////                    case 5:
////                        Toast.makeText(getContext(), "Hello6", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////                    case 6:
////                        Toast.makeText(getContext(), "Hello7", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////
////                    case 7:
////                        Toast.makeText(getContext(), "Hello8", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////                    case 8:
////                        Toast.makeText(getContext(), "Hello9", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////
////                    case 9:
////                        Toast.makeText(getContext(), "Hello11", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////                    case 10:
////                        Toast.makeText(getContext(), "Hello12", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////
////                    case 11:
////                        Toast.makeText(getContext(), "Hello1232", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////                    case 12:
////                        Toast.makeText(getContext(), "Hello231", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////
////                    case 13:
////                        Toast.makeText(getContext(), "Hello242", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////                    case 14:
////                        Toast.makeText(getContext(), "Hello213", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////
////                    default:
////                        Toast.makeText(getContext(), "Hello2123", Toast.LENGTH_SHORT).show();
////
////
////                        break;
////
////                }
////            }
////
////            @Override
////            public void onLongItemClick(View view, int position) {
////
////            }
////        }));
////
////
////    }
////
////    public ArrayList<Group_Object> getCategoriesList() {
////        return new ArrayList<Group_Object>();
////
////    }
////
////    @Override
////    public void onRestaurantSelected(DocumentSnapshot restaurant) {
////
////    }
////
////    //    =========================================== End LifeCycle ====================================== \\
//////    ===========================================   My Methods    ======================================  \\
////    //############# https://github.com/firebase/FirebaseUI-Android/tree/master/firestore ############## \\
//////    @NonNull
//////    public FirestoreRecyclerAdapter getFirestoreRecyclerAdapter() {
////////
//////        Query mQuery = FirebaseFirestore.getInstance()
//////                .collection(MAIN_COLLECTION)
//////                .document("Sport")
//////                .collection("SportsGroups")
//////                .orderBy("timestamp", Query.Direction.DESCENDING);
//////
//////
//////        FirestoreRecyclerOptions<Group_Object> options = new FirestoreRecyclerOptions.Builder<Group_Object>()
//////                .setQuery(mQuery, Group_Object.class).build();
//////
//////
//////        return new FirestoreRecyclerAdapter<Group_Object, UserViewHolder>(options) {
//////            @SuppressLint("SetTextI18n")
//////            @Override
//////            protected void onBindViewHolder(@NonNull final UserViewHolder holder, int position, @NonNull final Group_Object model) {
//////                Log.d("USER", "DATA: " + model.getGroupName());
////////                holder.BindView(position, model);
//////
//////
//////                try {
//////                    holder.mGroupName.setText(model.getGroupName() + "");
//////                    final String mFirst = holder.mGroupName.getText().toString();
//////                    holder.mGroupDesc.setText(model.getGroupDesc() + "");
//////                    final String mSecond = holder.mGroupDesc.getText().toString();
//////                    holder.mUserName.setText(String.format("%s", model.getUserName()));
//////                    holder.mGroupLink.setText(model.getGroupLink() + "");
//////                    final String mLink = holder.mGroupLink.getText().toString();
//////                    holder.mCatogries.setText(model.getCategories() + "");
//////                    holder.materialRatingBar.setRating((float) model.getRatings());
//////                    holder.mNumOfLikes.setText(model.getmNumOfLiks() + "");
//////                    holder.mNumOfComments.setText(model.getmNumOfComments() + "");
//////                    holder.materialRatingBar.setNumStars(5);
//////                    holder.mRateText.setText(holder.materialRatingBar.getNumStars() + "");
//////                    holder.mNumOfViews.setText(model.getmNumOfViews() + "");
//////
//////
//////                    holder.mShareGroupBtn.setOnClickListener(new View.OnClickListener() {
//////                        @Override
//////                        public void onClick(View v) {
//////                            Uri uri2 = Uri.parse("https://play.google.com/store/apps/com.manooz.telgramgroups");
//////                            Uri uri3 = Uri.parse(mLink);
//////
//////
//////                            String mMessageBody =
//////                                    mFirst  + " \n \n " + mSecond + " " + " \n \n \n "
//////                                            + " رابط القناة :  " + "\n \n " + uri3 + " \n \n " +
//////                                            " من تطبيق قروبات تليجرام  \n \n    حمل التطبيق الآن من الرابط : \n   \n " + "\n " + uri2;
//////
//////                            Intent ShareIntent = new Intent(Intent.ACTION_SEND);
//////                            ShareIntent.putExtra(Intent.EXTRA_TEXT, mMessageBody);
//////                            ShareIntent.setType("text/plain");
//////                            startActivity(Intent.createChooser(ShareIntent, "Share via"));
//////
//////
//////                        }
//////                    });
//////                    holder.mCommentBtn.setOnClickListener(new View.OnClickListener() {
//////                        @Override
//////                        public void onClick(View v) {
//////
//////                            Intent intent = new Intent(getActivity(), GroupDetails.class);
//////                            startActivity(intent);
//////
//////
//////                        }
//////                    });
//////
//////                    holder.mLikeBtn.setOnClickListener(new View.OnClickListener() {
//////                        @Override
//////                        public void onClick(View v) {
//////
//////
//////
////////                            GroupsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
////////                                @Override
////////                                public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots,
////////                                                    @javax.annotation.Nullable FirebaseFirestoreException e) {
////////                                    if (e !=null) {
////////                                        return;
////////                                    }
////////
////////                                   for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
////////
////////                                   Group_Object group_object =documentSnapshot.toObject(Group_Object.class);
////////                                   group_object.setDocumentId(documentSnapshot.getId());
////////                                   String documentLikesNum = group_object.getDocumentId();
////////
////////                                   group_object.setmNumOfLiks(mNumberOflikes + 1);
////////                                       mNumberOflikes = group_object.getmNumOfLiks();
////////                                       holder.mLikeBtn.setBackground(getResources().getDrawable(R.mipmap.action_like_accent));
////////                                       int i = model.getmNumOfLiks();
////////                                       holder.mNumOfLikes.setText(i+"");
////////
////////                                   }
////////                                }
////////                            });
//////
//////
////////                            Map<String,Object> objectMap = new HashMap<>();
////////                            objectMap.put("timestamp", FieldValue.serverTimestamp());
////////                            db.collection(MAIN_COLLECTION).document().collection("likes").document().set(objectMap);
////////                        GroupsRef.orderBy()
//////
//////
//////                        }
//////                    });
//////
//////
////////                    // Go To Telegram
////////                    holder.mGroupEnterBtn.setOnClickListener(new View.OnClickListener() {
////////                        @Override
////////                        public void onClick(View v) {
////////
////////                            try {
//////////
////////                                intentMessageTelegram(Uri.parse(model.getGroupLink()));
////////                            } catch (Exception e) {
////////                                e.printStackTrace();
////////                            }
////////                        }
////////                    });
//////
//////
//////                } catch (NullPointerException e) {
//////                    e.printStackTrace();
//////                }
//////
//////
//////                // ============================ImageLink====================================== \\
//////
//////
////////                holder.mGroupImage.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
//////
//////                Upload mCurrentUpload = new Upload();
////////                Picasso.with(getContext()).load(model.getPhoto()).into(holder.mGroupImage);
//////                Log.e(" TAG ", " Clicked" + mCurrentUpload.getImageUrl() + " ");
////////
////////              holder.AnotherCatogries.setText(model.getGroupName().substring(0,1)  + " A " + model.getGroupDesc().substring(0,1) + " B");
////////              Random mRandom = new Random();
////////             int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
////////             ((GradientDrawable) holder.AnotherCatogries.getBackground()).setColor(color);
//////
//////            }
//////
//////            @NonNull
//////            @Override
//////            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//////                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
//////
//////                return new UserViewHolder(view);
//////            }
//////        };
//////    }
////
////
//////    private void Filters() {
//////
//////        Task task1 = GroupsRef.whereGreaterThan("mNumOfLikes", 5).orderBy("mNumOfLikes").get();
//////        Task task2 = GroupsRef.whereGreaterThan("mNumOfLikes", 1).orderBy("mNumOfLikes").get();
//////        Task<List<QuerySnapshot>> mAllTasks = Tasks.whenAllSuccess(task1, task2);
//////        mAllTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
//////            @Override
//////            public void onSuccess(List<QuerySnapshot> querySnapshot) {
//////                for (QuerySnapshot querySnapshot1 : querySnapshot) {
//////                    for (QueryDocumentSnapshot documentSnapshot : querySnapshot1) {
////////
//////                        Group_Object group_object = documentSnapshot.toObject(Group_Object.class);
//////                        group_object.setDocumentId(documentSnapshot.getId());
//////
//////
////////
//////                    }
//////                }
//////                Toast.makeText(getActivity(), " Hala ", Toast.LENGTH_SHORT).show();
//////            }
//////        });
//////
//////    }
////
////
////    // ======================================== My Methods ============================================= \\
//////    https://stackoverflow.com/questions/21627167/how-to-send-a-intent-with-telegram
////
////}
////
////
//////    =========================================== End LifeCycle ====================================== \\
//////    ===========================================   My Methods    ======================================  \\
//////############# https://github.com/firebase/FirebaseUI-Android/tree/master/firestore ############## \\
//////    @NonNull
//////    public FirestoreRecyclerAdapter getFirestoreRecyclerAdapter() {
////////
//////        Query mQuery = FirebaseFirestore.getInstance()
//////                .collection(MAIN_COLLECTION)
//////                .document("Sport")
//////                .collection("SportsGroups")
//////                .orderBy("timestamp", Query.Direction.DESCENDING);
//////
//////
//////        FirestoreRecyclerOptions<Group_Object> options = new FirestoreRecyclerOptions.Builder<Group_Object>()
//////                .setQuery(mQuery, Group_Object.class).build();
//////
//////
//////        return new FirestoreRecyclerAdapter<Group_Object, UserViewHolder>(options) {
//////            @SuppressLint("SetTextI18n")
//////            @Override
//////            protected void onBindViewHolder(@NonNull final UserViewHolder holder, int position, @NonNull final Group_Object model) {
//////                Log.d("USER", "DATA: " + model.getGroupName());
////////                holder.BindView(position, model);
//////
//////
//////                try {
//////                    holder.mGroupName.setText(model.getGroupName() + "");
//////                    final String mFirst = holder.mGroupName.getText().toString();
//////                    holder.mGroupDesc.setText(model.getGroupDesc() + "");
//////                    final String mSecond = holder.mGroupDesc.getText().toString();
//////                    holder.mUserName.setText(String.format("%s", model.getUserName()));
//////                    holder.mGroupLink.setText(model.getGroupLink() + "");
//////                    final String mLink = holder.mGroupLink.getText().toString();
//////                    holder.mCatogries.setText(model.getCategories() + "");
//////                    holder.materialRatingBar.setRating((float) model.getRatings());
//////                    holder.mNumOfLikes.setText(model.getmNumOfLiks() + "");
//////                    holder.mNumOfComments.setText(model.getmNumOfComments() + "");
//////                    holder.materialRatingBar.setNumStars(5);
//////                    holder.mRateText.setText(holder.materialRatingBar.getNumStars() + "");
//////                    holder.mNumOfViews.setText(model.getmNumOfViews() + "");
//////
//////
//////                    holder.mShareGroupBtn.setOnClickListener(new View.OnClickListener() {
//////                        @Override
//////                        public void onClick(View v) {
//////                            Uri uri2 = Uri.parse("https://play.google.com/store/apps/com.manooz.telgramgroups");
//////                            Uri uri3 = Uri.parse(mLink);
//////
//////
//////                            String mMessageBody =
//////                                    mFirst  + " \n \n " + mSecond + " " + " \n \n \n "
//////                                            + " رابط القناة :  " + "\n \n " + uri3 + " \n \n " +
//////                                            " من تطبيق قروبات تليجرام  \n \n    حمل التطبيق الآن من الرابط : \n   \n " + "\n " + uri2;
//////
//////                            Intent ShareIntent = new Intent(Intent.ACTION_SEND);
//////                            ShareIntent.putExtra(Intent.EXTRA_TEXT, mMessageBody);
//////                            ShareIntent.setType("text/plain");
//////                            startActivity(Intent.createChooser(ShareIntent, "Share via"));
//////
//////
//////                        }
//////                    });
//////                    holder.mCommentBtn.setOnClickListener(new View.OnClickListener() {
//////                        @Override
//////                        public void onClick(View v) {
//////
//////                            Intent intent = new Intent(getActivity(), GroupDetails.class);
//////                            startActivity(intent);
//////
//////
//////                        }
//////                    });
//////
//////                    holder.mLikeBtn.setOnClickListener(new View.OnClickListener() {
//////                        @Override
//////                        public void onClick(View v) {
//////
//////
//////
////////                            GroupsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
////////                                @Override
////////                                public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots,
////////                                                    @javax.annotation.Nullable FirebaseFirestoreException e) {
////////                                    if (e !=null) {
////////                                        return;
////////                                    }
////////
////////                                   for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
////////
////////                                   Group_Object group_object =documentSnapshot.toObject(Group_Object.class);
////////                                   group_object.setDocumentId(documentSnapshot.getId());
////////                                   String documentLikesNum = group_object.getDocumentId();
////////
////////                                   group_object.setmNumOfLiks(mNumberOflikes + 1);
////////                                       mNumberOflikes = group_object.getmNumOfLiks();
////////                                       holder.mLikeBtn.setBackground(getResources().getDrawable(R.mipmap.action_like_accent));
////////                                       int i = model.getmNumOfLiks();
////////                                       holder.mNumOfLikes.setText(i+"");
////////
////////                                   }
////////                                }
////////                            });
//////
//////
////////                            Map<String,Object> objectMap = new HashMap<>();
////////                            objectMap.put("timestamp", FieldValue.serverTimestamp());
////////                            db.collection(MAIN_COLLECTION).document().collection("likes").document().set(objectMap);
////////                        GroupsRef.orderBy()
//////
//////
//////                        }
//////                    });
//////
//////
////////                    // Go To Telegram
////////                    holder.mGroupEnterBtn.setOnClickListener(new View.OnClickListener() {
////////                        @Override
////////                        public void onClick(View v) {
////////
////////                            try {
//////////
////////                                intentMessageTelegram(Uri.parse(model.getGroupLink()));
////////                            } catch (Exception e) {
////////                                e.printStackTrace();
////////                            }
////////                        }
////////                    });
//////
//////
//////                } catch (NullPointerException e) {
//////                    e.printStackTrace();
//////                }
//////
//////
//////                // ============================ImageLink====================================== \\
//////
//////
////////                holder.mGroupImage.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
//////
//////                Upload mCurrentUpload = new Upload();
////////                Picasso.with(getContext()).load(model.getPhoto()).into(holder.mGroupImage);
//////                Log.e(" TAG ", " Clicked" + mCurrentUpload.getImageUrl() + " ");
////////
////////              holder.AnotherCatogries.setText(model.getGroupName().substring(0,1)  + " A " + model.getGroupDesc().substring(0,1) + " B");
////////              Random mRandom = new Random();
////////             int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
////////             ((GradientDrawable) holder.AnotherCatogries.getBackground()).setColor(color);
//////
//////            }
//////
//////            @NonNull
//////            @Override
//////            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//////                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
//////
//////                return new UserViewHolder(view);
//////            }
//////        };
//////    }
////
////
//////    private void Filters() {
//////
//////        Task task1 = GroupsRef.whereGreaterThan("mNumOfLikes", 5).orderBy("mNumOfLikes").get();
//////        Task task2 = GroupsRef.whereGreaterThan("mNumOfLikes", 1).orderBy("mNumOfLikes").get();
//////        Task<List<QuerySnapshot>> mAllTasks = Tasks.whenAllSuccess(task1, task2);
//////        mAllTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
//////            @Override
//////            public void onSuccess(List<QuerySnapshot> querySnapshot) {
//////                for (QuerySnapshot querySnapshot1 : querySnapshot) {
//////                    for (QueryDocumentSnapshot documentSnapshot : querySnapshot1) {
////////
//////                        Group_Object group_object = documentSnapshot.toObject(Group_Object.class);
//////                        group_object.setDocumentId(documentSnapshot.getId());
//////
//////
////////
//////                    }
//////                }
//////                Toast.makeText(getActivity(), " Hala ", Toast.LENGTH_SHORT).show();
//////            }
//////        });
//////
//////    }
////
////
////// ======================================== My Methods ============================================= \\
//////    https://stackoverflow.com/questions/21627167/how-to-send-a-intent-with-telegram
////
////
//////}
////
////
////// =================================== References ================================================================== \\
////
//////https://stackoverflow.com/questions/47280055/how-to-set-recyclerview-to-show-only-4-tiles
////
////
////// ======================================== BackUp ============================================= \\
//////private void addToFireBase() {
//////
//////        Random randomLongString = new Random();
//////        Map<String, String> stringMap = new HashMap<>();
//////
//////        for (int i = 0; i < 2; i++) {
//////        stringMap.put("name", " " + randomLongString.nextInt(50));
//////        stringMap.put(" Ok ", " Hell9o" + randomLongString.nextInt(50));
//////
//////        db.collection("group_objectArrayList").add(stringMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//////@Override
//////public void onSuccess(DocumentReference documentReference) {
//////
//////        }
//////        });
//////
//////
//////
//////
//////        }
//////
//////
//////        }
//////private void loadFromFirebase() {
//////        db.collection("group_objectArrayList").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//////@Override
//////public void onComplete(@NonNull Task<QuerySnapshot> task) {
//////        for (DocumentSnapshot documentSnapshot : task.getResult()){
//////        User user = new User(
//////        documentSnapshot.getString("name"),
//////        documentSnapshot.getString("status"));
//////        list.add(user);
//////
//////        }
//////        myRecycleAdapter = new myRecycleAdapter(HomeFragment.this,list);
//////        mRecyclerView.setAdapter(myRecycleAdapter);
//////        }
//////        }).addOnFailureListener(new OnFailureListener() {
//////@Override
//////public void onFailure(@NonNull Exception e) {
//////        Log.d(" TAG ", " Clicked");
//////        }
//////        });
//////
//////
//////
//////        }
////
////
//////    @Override
//////    public void onRestaurantSelected(int positionOfIndex) {
//////        Toast.makeText(getActivity(), " Hello", Toast.LENGTH_SHORT).show();
//////    }
////
//////    @Override
//////    public void onRestaurantSelected(int position) {
//////        Toast.makeText(context, " Hello Guyz ", Toast.LENGTH_SHORT).show();
//////        Log.e(TAG,"Clciked");
//////    }
////// ===================================================================================================== \\
////
//////  if (e !=null) {
//////          return;
//////          }
//////          if (queryDocumentSnapshots !=null){
//////          for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
//////
//////          Group_Object group_object =documentSnapshot.toObject(Group_Object.class);
//////        group_object.setDocumentId(documentSnapshot.getId());
//////        String documentLikesNum = group_object.getDocumentId();
//////        mNumberOflikes = group_object.getmNumOfLiks();
//////
//////        Toast.makeText(getActivity(), "Hello" + documentLikesNum, Toast.LENGTH_SHORT).show();
//////
//////        }
////
////
//////        GroupsRef.addSnapshotListener(Objects.requireNonNull(getActivity()), new EventListener<QuerySnapshot>() {
//////            @Override
//////            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
//////                if (e != null) {
//////                    return;
//////
//////                }
//////                if (queryDocumentSnapshots != null) {
//////                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//////                        Group_Object group_object = documentSnapshot.toObject(Group_Object.class);
//////                        group_object.setDocumentId(documentSnapshot.getId());
//////
//////                        Log.d(" TAG ", " Clicked " + documentSnapshot + " ");
//////                        list.add(group_object);
//////                    }
//////                }
//////
//////            }
//////        });
//
//<?xml version="1.0" encoding="utf-8"?>
//<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
//        xmlns:app="http://schemas.android.com/apk/res-auto"
//        xmlns:tools="http://schemas.android.com/tools"
//        style="@style/Widget.Design.CoordinatorLayout"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:tag="homeFragment"
//        tools:context=".Current_Project.Fragments.HomeFragment"
//        tools:layout_editor_absoluteY="25dp">
//
//<!-- TODO: Update blank fragment layout -->
//
//<!--<android.support.v7.widget.RecyclerView-->
//<!--android:id="@+id/blog_list_view"-->
//<!--android:layout_width="match_parent"-->
//<!--android:layout_height="match_parent" />-->
//<ScrollView
//        android:layout_width="match_parent"
//                android:layout_height="match_parent"
//                app:layout_constraintBottom_toBottomOf="parent"
//                app:layout_constraintEnd_toEndOf="parent"
//                app:layout_constraintStart_toStartOf="parent"
//                app:layout_constraintTop_toTopOf="parent">
//
//<LinearLayout
//            android:layout_width="match_parent"
//                    android:layout_height="wrap_content"
//                    android:orientation="vertical"
//                    app:layout_constraintTop_toTopOf="parent">
//
//<android.support.constraint.ConstraintLayout
//        android:layout_width="match_parent"
//        android:layout_height="match_parent">
//
//<RelativeLayout
//                    android:id="@+id/filter_bar_container"
//                            android:layout_width="match_parent"
//                            android:layout_height="wrap_content"
//                            android:layout_marginEnd="8dp"
//                            android:layout_marginStart="8dp"
//                            android:layout_marginTop="8dp"
//                            android:background="?attr/colorPrimary"
//                            app:layout_constraintEnd_toEndOf="parent"
//                            app:layout_constraintStart_toStartOf="parent"
//                            app:layout_constraintTop_toTopOf="parent">
//
//<android.support.v7.widget.CardView
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:layout_gravity="center"
//        android:elevation="8dp"
//        app:cardCornerRadius="10dp">
//
//<android.support.constraint.ConstraintLayout
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:background="?attr/colorPrimary">
//
//<ImageView
//                                android:id="@+id/button_clear_filter"
//                                        android:layout_width="wrap_content"
//                                        android:layout_height="wrap_content"
//                                        android:layout_alignParentEnd="true"
//                                        android:layout_alignParentTop="true"
//                                        android:layout_marginBottom="8dp"
//                                        android:layout_marginEnd="8dp"
//                                        android:layout_marginStart="8dp"
//                                        android:layout_marginTop="8dp"
//                                        android:padding="8dp"
//                                        android:tint="#ffffff"
//                                        app:layout_constraintBottom_toBottomOf="parent"
//                                        app:layout_constraintEnd_toEndOf="parent"
//                                        app:layout_constraintStart_toEndOf="@+id/search_keyword"
//                                        app:layout_constraintTop_toTopOf="parent"
//                                        app:srcCompat="@drawable/ic_close_black_24dp" />
//
//<EditText
//                                android:id="@+id/search_keyword"
//                                        android:layout_width="0dp"
//                                        android:layout_height="wrap_content"
//                                        android:layout_marginEnd="8dp"
//                                        android:layout_marginStart="8dp"
//                                        android:background="@android:color/white"
//                                        android:focusedByDefault="false"
//                                        android:fontFamily="@font/ca"
//                                        android:hint="@string/hint_keyword"
//                                        android:maxLines="1"
//                                        android:padding="5dp"
//                                        android:textAlignment="center"
//                                        android:textColorHint="@color/hint_color"
//                                        android:textDirection="anyRtl"
//                                        app:layout_constraintBottom_toBottomOf="parent"
//                                        app:layout_constraintEnd_toStartOf="@+id/button_clear_filter"
//                                        app:layout_constraintStart_toEndOf="@+id/button_filter"
//                                        app:layout_constraintTop_toTopOf="parent" />
//
//<ImageView
//                                android:id="@+id/button_filter"
//                                        android:layout_width="wrap_content"
//                                        android:layout_height="wrap_content"
//                                        android:layout_alignParentStart="true"
//                                        android:layout_alignParentTop="true"
//                                        android:layout_marginBottom="8dp"
//                                        android:layout_marginEnd="8dp"
//                                        android:layout_marginStart="8dp"
//                                        android:layout_marginTop="8dp"
//                                        android:padding="8dp"
//                                        android:tint="#ffffff"
//                                        app:layout_constraintBottom_toBottomOf="parent"
//                                        app:layout_constraintEnd_toStartOf="@+id/search_keyword"
//                                        app:layout_constraintStart_toStartOf="parent"
//                                        app:layout_constraintTop_toTopOf="parent"
//                                        app:srcCompat="@drawable/ic_filter_list_white_24px" />
//</android.support.constraint.ConstraintLayout>
//
//</android.support.v7.widget.CardView>
//
//</RelativeLayout>
//
//<Button
//                    android:id="@+id/mSearchBtn"
//                            android:layout_width="wrap_content"
//                            android:layout_height="wrap_content"
//                            android:layout_marginEnd="8dp"
//                            android:layout_marginStart="8dp"
//                            android:layout_marginTop="24dp"
//                            android:background="@drawable/button_background"
//                            android:gravity="center"
//                            android:minWidth="130dp"
//                            android:padding="5dp"
//                            android:text="@string/title_search_now"
//                            android:textAlignment="center"
//                            android:textColor="@android:color/background_light"
//                            android:textStyle="bold"
//                            app:layout_constraintEnd_toEndOf="parent"
//                            app:layout_constraintHorizontal_bias="0.498"
//                            app:layout_constraintStart_toStartOf="parent"
//                            app:layout_constraintTop_toBottomOf="@+id/filter_bar_container" />
//
//<RelativeLayout
//                    android:id="@+id/categories_all2"
//                            android:layout_width="match_parent"
//                            android:layout_height="match_parent"
//                            android:layout_alignParentStart="true"
//                            android:layout_alignParentTop="true"
//                            android:layout_marginBottom="8dp"
//                            android:layout_marginEnd="8dp"
//                            android:layout_marginStart="8dp"
//                            android:layout_marginTop="40dp"
//                            app:layout_constraintBottom_toBottomOf="parent"
//                            app:layout_constraintEnd_toEndOf="parent"
//                            app:layout_constraintHorizontal_bias="0.0"
//                            app:layout_constraintStart_toStartOf="parent"
//                            app:layout_constraintTop_toBottomOf="@+id/cardView">
//
//<LinearLayout
//                        android:layout_width="match_parent"
//                                android:layout_height="match_parent"
//                                android:orientation="vertical">
//
//<LinearLayout
//                            android:layout_width="match_parent"
//                                    android:layout_height="wrap_content"
//                                    android:layout_gravity="center"
//                                    android:gravity="center"
//                                    android:orientation="horizontal">
//
//<TextView
//                                android:layout_width="match_parent"
//                                        android:layout_height="wrap_content"
//                                        android:layout_centerHorizontal="true"
//                                        android:layout_weight="1"
//                                        android:fontFamily="@font/ca"
//                                        android:text="Top Groups"
//                                        android:textColor="@android:color/black"
//                                        android:textSize="15sp" />
//
//<TextView
//                                android:id="@+id/mViewAll2"
//                                        android:layout_width="match_parent"
//                                        android:layout_height="wrap_content"
//                                        android:layout_weight="2"
//                                        android:drawableEnd="@drawable/ic_chevron_right"
//                                        android:drawablePadding="5dp"
//                                        android:fontFamily="@font/ca"
//                                        android:gravity="center"
//                                        android:text="@string/title_view_all"
//                                        android:textColor="@color/blue_300"
//                                        android:textSize="14sp"
//                                        android:textStyle="bold" />
//
//</LinearLayout>
//
//<android.support.v7.widget.CardView
//        android:id="@+id/cardView2"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:layout_margin="5dp"
//        android:layout_marginTop="10dp"
//        app:cardCornerRadius="10dp"
//        app:cardElevation="8dp">
//
//<android.support.v7.widget.RecyclerView
//        android:id="@+id/mRv1"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:layout_margin="10dp"
//        android:padding="5dp" />
//
//</android.support.v7.widget.CardView>
//
//</LinearLayout>
//
//</RelativeLayout>
//
//<android.support.v7.widget.CardView
//        android:id="@+id/cardView"
//        android:layout_width="0dp"
//        android:layout_height="wrap_content"
//        android:layout_alignParentStart="true"
//        android:layout_alignParentTop="true"
//        android:layout_marginBottom="8dp"
//        android:layout_marginEnd="16dp"
//        android:layout_marginStart="8dp"
//        android:layout_marginTop="8dp"
//        app:cardCornerRadius="10dp"
//        app:cardElevation="6dp"
//        app:layout_constraintBottom_toTopOf="@+id/categories_all2"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintStart_toStartOf="parent"
//        app:layout_constraintTop_toBottomOf="@+id/categories_all">
//
//<android.support.v7.widget.RecyclerView
//        android:id="@+id/mRv4"
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        android:layout_margin="10dp"
//        android:padding="5dp" />
//
//</android.support.v7.widget.CardView>
//
//<RelativeLayout
//                    android:id="@+id/categories_all"
//                            android:layout_width="match_parent"
//                            android:layout_height="41dp"
//                            android:layout_alignParentStart="true"
//                            android:layout_alignParentTop="true"
//                            android:layout_marginBottom="8dp"
//                            android:layout_marginEnd="8dp"
//                            android:layout_marginStart="8dp"
//                            android:layout_marginTop="32dp"
//                            app:layout_constraintBottom_toTopOf="@+id/cardView"
//                            app:layout_constraintEnd_toEndOf="parent"
//                            app:layout_constraintHorizontal_bias="1.0"
//                            app:layout_constraintStart_toStartOf="parent"
//                            app:layout_constraintTop_toBottomOf="@+id/mSearchBtn">
//
//<TextView
//                        android:layout_width="wrap_content"
//                                android:layout_height="match_parent"
//                                android:layout_alignParentStart="true"
//                                android:layout_alignParentTop="true"
//                                android:fontFamily="@font/ca"
//                                android:gravity="center"
//                                android:padding="3dp"
//                                android:text="@string/title_top_categories"
//                                android:textColor="@android:color/black"
//                                android:textSize="15sp" />
//
//<TextView
//                        android:id="@+id/mViewAll"
//                                android:layout_width="wrap_content"
//
//                                android:layout_height="match_parent"
//                                android:layout_alignParentEnd="true"
//                                android:layout_alignParentTop="true"
//                                android:layout_gravity="end|center"
//                                android:drawableEnd="@drawable/ic_chevron_right"
//                                android:drawablePadding="5dp"
//                                android:fontFamily="@font/ca"
//                                android:gravity="center"
//                                android:text="@string/title_view_all"
//                                android:textColor="@color/blue_300"
//                                android:textSize="14sp"
//                                android:textStyle="bold" />
//
//</RelativeLayout>
//
//</android.support.constraint.ConstraintLayout>
//</LinearLayout>
//</ScrollView>
//
//
//</android.support.constraint.ConstraintLayout>