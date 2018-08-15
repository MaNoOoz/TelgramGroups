//package com.manooz.telgramgroups.Current_Project.Utily;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.Query;
//import com.manooz.telgramgroups.Current_Project.Adapter.GroupAdapter;
//import com.manooz.telgramgroups.Current_Project.Fragments.HomeFragment;
//import com.manooz.telgramgroups.Current_Project.ListOfGroups;
//import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.manooz.telgramgroups.Current_Project.Utily.mConstants.MAIN_COLLECTION;
//
//public class CategoryListActivity extends ListOfGroups implements GroupAdapter.OnRestaurantSelectedListener {
//
//    Query mQuery = FirebaseFirestore.getInstance()
//            .collection(MAIN_COLLECTION)
//            .document("Sport")
//            .collection("SportsGroups")
//            .orderBy("timestamp", Query.Direction.DESCENDING);
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference mLikesdocumentReference = db.document(MAIN_COLLECTION + "/Sport").collection("SportsGroups").document();
//
//    @Override
//    protected void setAdapter() {
//        ArrayList<Group_Object> categoriesList = new HomeFragment().getCategoriesList();
//
//
//        if (categoriesList != null && !categoriesList.isEmpty()) {
//            onCategoriesLoad(categoriesList);
//        } else {
////            new CategoryDataManager().loadDataFromServer(this,true);
//            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
//
//        }
//    }
//
//    @Override
//    public void onCategoryListInteraction(Group_Object item) {
//        Intent newIntent = new Intent(this, ListOfGroups.class);
//        Bundle bundle = new Bundle();
//        bundle.putString(mConstants.CATEGORY, item.getCategories());
//        bundle.putString(mConstants.TITLE, item.getGroupName());
//        newIntent.putExtra(mConstants.DATA, bundle);
//        newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(newIntent);
//    }
//
//    @Override
//    public void onCategoriesLoad(List<Group_Object> items) {
//        getRecyclerView().setAdapter(new GroupAdapter(mQuery, this));
//    }
//
//    @Override
//    public void onRestaurantSelected(DocumentSnapshot restaurant) {
//
//
//    }
//}
//
//
