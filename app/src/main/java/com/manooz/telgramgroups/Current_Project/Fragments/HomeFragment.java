package com.manooz.telgramgroups.Current_Project.Fragments;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.manooz.telgramgroups.Current_Project.Adapter.CategoriesAdapter;
import com.manooz.telgramgroups.Current_Project.Adapter.RestaurantAdapter;
import com.manooz.telgramgroups.Current_Project.MainActivityViewModel;
import com.manooz.telgramgroups.Current_Project.POJO.Catogery;
import com.manooz.telgramgroups.Current_Project.POJO.Filters;
import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
import com.manooz.telgramgroups.Current_Project.RecyclerItemClickListener;
import com.manooz.telgramgroups.Current_Project.addGroupActivity;
import com.manooz.telgramgroups.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION;
import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION2;

public class HomeFragment extends Fragment  implements RestaurantAdapter.OnRestaurantSelectedListener {


    // ======================================== Constrictor  ============================================= \\

    public HomeFragment() {
    }


    // ======================================== Fragment LifeCycle ============================================= \\

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        https://www.coderefer.com/android-parcelable-example/

        final View view = getLayoutInflater().inflate(R.layout.homefragment, container, false);
        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onRestaurantSelected(DocumentSnapshot restaurant) {

    }
}