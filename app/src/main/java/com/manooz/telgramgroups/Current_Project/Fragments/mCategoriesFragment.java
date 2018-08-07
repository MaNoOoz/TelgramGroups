package com.manooz.telgramgroups.Current_Project.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.manooz.telgramgroups.Current_Project.Adapter.RestaurantAdapter;
import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
import com.manooz.telgramgroups.R;

import java.util.Objects;

import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION;

public class mCategoriesFragment extends Fragment implements RestaurantAdapter.OnRestaurantSelectedListener{

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RestaurantAdapter restaurantAdapter;
    private RecyclerView recyclerView ;
    private Context context;

    //    CollectionReference notebookref= db.collection(mConstants.MAIN_COLLECTION);
    //    RecyclerView mRecyclerView;
    //    GroupAdapter groupAdapter;

    public mCategoriesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = getLayoutInflater().inflate(R.layout.catogaries_fragment, container, false);
        recyclerView = view.findViewById(R.id.mRv2);

//        mGetMyListOfCats2();




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // listener
//        mFirestoreRecyclerAdapter.startListening();
//        restaurantAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
//        mFirestoreRecyclerAdapter.stopListening();
//        restaurantAdapter.startListening();
    }
    private void mGetMyListOfCats2() {
//
////
////        mFirestoreRecyclerAdapter.s
////        mRecyclerView.setAdapter(mFirestoreRecyclerAdapter);
//
//
//        Query mQuery = FirebaseFirestore.getInstance()
//                .collection(MAIN_COLLECTION)
//                .document("Sport")
//                .collection("SportsGroups")
//                .orderBy("timestamp", Query.Direction.DESCENDING);
////
//////        Query query = GroupsRef.orderBy("timestamp", Query.Direction.DESCENDING);
//        FirestoreRecyclerOptions<Group_Object>
//                options = new FirestoreRecyclerOptions.Builder<Group_Object>().setQuery(mQuery,Group_Object.class).build();
////
////        // TODO: 8/6/2018
////        List<Group_Object> group_objects = new ArrayList<>();
////        group_objects.add(new Group_Object());
//
//        restaurantAdapter = new RestaurantAdapter(options,this);
//       recyclerView.setHasFixedSize(true);
//       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//       recyclerView.setAdapter(restaurantAdapter);
////        restaurantAdapter.mShowOnlyTop(group_objects.subList(0,4));


    }

    private void setupRecycleView() {
//        Query query =notebookref.orderBy("timestamp", Query.Direction.DESCENDING);
//        FirestoreRecyclerOptions<Group_Object> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Group_Object>()
//                .setQuery(query,Group_Object.class).build();

//        groupAdapter = new GroupAdapter(firestoreRecyclerOptions);
////        firestoreRecyclerAdapter = getFirestoreRecyclerAdapter();
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setAdapter(groupAdapter);


    }

    @Override
    public void onRestaurantSelected(DocumentSnapshot restaurant) {

    }
}


