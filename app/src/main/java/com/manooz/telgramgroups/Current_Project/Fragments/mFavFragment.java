package com.manooz.telgramgroups.Current_Project.Fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.manooz.telgramgroups.Current_Project.POJO.Filters;
import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
import com.manooz.telgramgroups.Current_Project.Adapter.GroupAdapter;
import com.manooz.telgramgroups.Current_Project.Utily.mConstants;
import com.manooz.telgramgroups.R;

import java.util.ArrayList;
import java.util.Objects;

public class mFavFragment extends Fragment
//        implements
//        GroupAdapter.OnRestaurantSelectedListener
//        FilterDialogFragment.FilterListener
{

    private ArrayList<Group_Object> mGroups;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference notebookref= db.collection(mConstants.MAIN_COLLECTION);
    RecyclerView mRecyclerView;
    GroupAdapter groupAdapter;
    RecyclerView.ItemDecoration itemDecoration;
    Query query ;
    private static final int LIMIT = 50;

    public mFavFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = getLayoutInflater().inflate(R.layout.favfragment, container, false);
        mRecyclerView = view.findViewById(R.id.mRv3);

//        setupRecycleView();

        return view;
    }

//    private void setupRecycleView() {
//        query = notebookref.orderBy("avgRating", Query.Direction.DESCENDING)
//                .limit(LIMIT);
//
//
//        mGroups =  new ArrayList<Group_Object>();
//        groupAdapter = new GroupAdapter(query,this);
//        itemDecoration = new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL);
//        mRecyclerView.addItemDecoration(itemDecoration);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setAdapter(groupAdapter);
//
//
//    }

//    @Override
//    public void onFilter(Filters filters) {
//        // TODO(developer): Construct new query
//
//        // Construct query basic query
//        Query query = mFirestore.collection("restaurants");
//        // Category (equality filter)
//        if (filters.hasCategory()) {
//            query = query.whereEqualTo("category", filters.getCategory());
//        }
//
//        // City (equality filter)
//        if (filters.hasCity()) {
//            query = query.whereEqualTo("city", filters.getCity());
//        }
//
//        // Price (equality filter)
//        if (filters.hasPrice()) {
//            query = query.whereEqualTo("price", filters.getPrice());
//        }
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
//        mQuery = query;
//        mAdapter.setQuery(query);
//
//        // Set header
//        mCurrentSearchView.setText(Html.fromHtml(filters.getSearchDescription(this)));
//        mCurrentSortByView.setText(filters.getOrderDescription(this));
//
//        // Save filters
//        mViewModel.setFilters(filters);
//        showTodoToast();
//
//        // Set header
//        mCurrentSearchView.setText(Html.fromHtml(filters.getSearchDescription(this)));
//        mCurrentSortByView.setText(filters.getOrderDescription(this));
//
//        // Save filters
//        mViewModel.setFilters(filters);
//    }

    @Override
    public void onStart() {
        super.onStart();
        // listener
//        groupAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
//        groupAdapter.stopListening();
    }

//
//    @Override
//    public void onRestaurantSelected(DocumentSnapshot restaurant) {
//        Toast.makeText(getActivity(), "Hi U clicked me", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(getActivity(), GroupDetails.class);
//        startActivity(intent);
//    }
}
