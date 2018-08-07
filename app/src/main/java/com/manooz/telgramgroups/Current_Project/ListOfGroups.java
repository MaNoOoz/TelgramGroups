package com.manooz.telgramgroups.Current_Project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.manooz.telgramgroups.Current_Project.Adapter.GroupAdapter;
import com.manooz.telgramgroups.Current_Project.POJO.Catogery;
import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
import com.manooz.telgramgroups.Current_Project.Utily.mConstants;
import com.manooz.telgramgroups.R;

import java.util.List;

public class ListOfGroups extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
    }


    private RecyclerView recyclerView;
    private GroupAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_groups);

        if( getSupportActionBar() != null && getIntent().getBundleExtra(mConstants.DATA) != null
                &&getIntent().getBundleExtra(mConstants.DATA).getString(mConstants.TITLE) != null) {
            getSupportActionBar().setTitle(Html.fromHtml(getIntent().getBundleExtra(mConstants.DATA).getString(mConstants.TITLE)));

        }

        initViews();
        setAdapter();

    }
    public void onCategoriesLoad(List<Group_Object> items) {

    }

    public void onCategoryListInteraction(Group_Object item) {

    }
    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

    }

    protected void setAdapter() {

    }

    protected void showNoData(){
        recyclerView.setAdapter(null);
//        noData.setVisibility(View.VISIBLE);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }


}
