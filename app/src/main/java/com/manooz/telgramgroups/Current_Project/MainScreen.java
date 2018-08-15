package com.manooz.telgramgroups.Current_Project;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.manooz.telgramgroups.Current_Project.Fragments.HomeFragment;
import com.manooz.telgramgroups.Current_Project.Fragments.MainFragment;
import com.manooz.telgramgroups.Current_Project.Fragments.mCategoriesFragment;
import com.manooz.telgramgroups.Current_Project.Fragments.mFavFragment;
import com.manooz.telgramgroups.R;

import java.util.Collections;

public class MainScreen extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private MainActivityViewModel mViewModel;

    BottomNavigationView navigation;
    FloatingActionButton addPostBtn;
    Toolbar mainToolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    // Fragments
    private HomeFragment homeFragment;
    private mFavFragment mFavFragment;
    private mCategoriesFragment mCategoriesFragment;
    private MainFragment mainFragment;

    private FilterDialogFragment mFilterDialog;
    //
    private TextView mTextMessage;
    private CardView filter_bar;
    private ImageView mClearBtn;
    FragmentTransaction fragmentTransaction;
    // ======================================== Activity LifeCycle ============================================= \\

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        mainToolbar = findViewById(R.id.main_toolbar);
        // Enable Firestore logging
        FirebaseFirestore.setLoggingEnabled(true);
        // View model
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        CreateMyNaveBottom();
        myWidgets();
        mClickListeners();
        initializeFragment();

    }
    // ======================================== Listeners ============================================= \\

    private void myWidgets() {


        addPostBtn = findViewById(R.id.add_post_btn);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private void mClickListeners() {


        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent newPostIntent = new Intent(MainScreen.this, NewPostActivity.class);
//                startActivity(newPostIntent);
                Toast.makeText(MainScreen.this, " Hi ", Toast.LENGTH_SHORT).show();


            }
        });


    }


    // ======================================== ToolBar ============================================= \\

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout_btn:
                AuthUI.getInstance().signOut(this);
                startSignIn();
                break;

            case R.id.action_settings_btn:
//
//                Intent settingsIntent = new Intent(FireBaseTest.this, SetupActivity.class);
//                startActivity(settingsIntent);
                return true;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);

    }



    // ======================================== My Methods ============================================= \\

    private void initializeFragment() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.main_container, homeFragment);
        fragmentTransaction.add(R.id.main_container, mFavFragment);
        fragmentTransaction.add(R.id.main_container, mCategoriesFragment);
        fragmentTransaction.add(R.id.main_container, mainFragment);

        fragmentTransaction.hide(mFavFragment);
        fragmentTransaction.hide(mCategoriesFragment);
        fragmentTransaction.hide(homeFragment);

        fragmentTransaction.commit();

    }

    private void replaceFragment(Fragment fragment, Fragment currentFragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (fragment == homeFragment) {
            addPostBtn.setVisibility(View.INVISIBLE);

            fragmentTransaction.hide(mCategoriesFragment);
            fragmentTransaction.hide(mFavFragment);
            fragmentTransaction.hide(mainFragment);

        }

        if (fragment == mCategoriesFragment) {

            addPostBtn.setVisibility(View.INVISIBLE);

            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(mFavFragment);
            fragmentTransaction.hide(mainFragment);

        }

        if (fragment == mFavFragment) {
              addPostBtn.setVisibility(View.INVISIBLE);
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(mCategoriesFragment);
            fragmentTransaction.hide(mainFragment);

        }
        if (fragment == mainFragment) {
            addPostBtn.setVisibility(View.VISIBLE);

            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(mCategoriesFragment);
            fragmentTransaction.hide(mFavFragment);

        }
        fragmentTransaction.show(fragment);

        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();

    }

    private boolean shouldStartSignIn() {
        return (!mViewModel.getIsSigningIn() && FirebaseAuth.getInstance().getCurrentUser() == null);
    }
    private void startSignIn() {
        // Sign in with FirebaseUI
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(Collections.singletonList(
                        new AuthUI.IdpConfig.EmailBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build();

        startActivityForResult(intent, RC_SIGN_IN);
        mViewModel.setIsSigningIn(true);
    }
    @Override
    protected void onStart() {
        super.onStart();

        // Start sign in if necessary
        if (shouldStartSignIn()) {
            startSignIn();
            return;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    void CreateMyNaveBottom() {
        //FRAGMENTS
        homeFragment = new HomeFragment();
        mFavFragment = new mFavFragment();
        mCategoriesFragment = new mCategoriesFragment();
        mainFragment = new MainFragment();

        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                 Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();


                switch (item.getItemId()) {
                    case R.id.homeFragment:

//                        mTextMessage.setText(R.string.title_home);
                        replaceFragment(homeFragment, currentFragment);
                        fragmentTransaction.hide(mCategoriesFragment);
                        fragmentTransaction.hide(mFavFragment);
                        fragmentTransaction.hide(mainFragment);

                        return true;


                    case R.id.CategoriesFragment:
                        replaceFragment(mCategoriesFragment, currentFragment);
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(mFavFragment);
                        fragmentTransaction.hide(mainFragment);

                        return true;


                    case R.id.mFaveFragment:

                        replaceFragment(mFavFragment, currentFragment);
//                        mTextMessage.setText(R.string.title_notifications);
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(mCategoriesFragment);
                        fragmentTransaction.hide(mainFragment);

                        return true;
                    case R.id.mainFragment:
                        replaceFragment(mainFragment, currentFragment);
//                        mTextMessage.setText(R.string.title_notifications);
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(mCategoriesFragment);
                        fragmentTransaction.hide(mainFragment);
                        return true;

                    default:
                        return false;

                }
            }


        };


    }


    //    private void logOut() {
//
//
//        mAuth.signOut();
//        sendToLogin();
//    }
//
//    private void sendToLogin() {
//
//        Intent loginIntent = new Intent(FireBaseTest.this, LoginActivity.class);
//        startActivity(loginIntent);
//        finish();
//
//    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//
//    }
}