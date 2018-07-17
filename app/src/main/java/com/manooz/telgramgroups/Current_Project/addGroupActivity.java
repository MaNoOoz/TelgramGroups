package com.manooz.telgramgroups.Current_Project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manooz.telgramgroups.R;

public class addGroupActivity extends AppCompatActivity {

    EditText mGroupName,mGroupDesc, mGroupLink,mUserName;
    Button mAddToMyDataBase;
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_group_activity);

        mGroupName = findViewById(R.id.mGroupName);
        mGroupLink = findViewById(R.id.mGroupLink);
        mGroupDesc = findViewById(R.id.mGroupDesc);
        mUserName = findViewById(R.id.mGroupUserName);

         s = mGroupName.getText().toString().trim();

        mAddToMyDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
