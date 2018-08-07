//package com.manooz.telgramgroups.Current_Project;
//
//import android.graphics.Color;
//import android.graphics.drawable.GradientDrawable;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.manooz.telgramgroups.R;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//
//public class LinksAdapter2  extends RecyclerView.Adapter<LinksAdapter2.ViewHolder> {
//
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference noteRef = db.document("Groups/NewOne");
//    private List<Group_Object> linkList;
//    private Callback mCallback;
//
//
//
//    public LinksAdapter2(Callback callback) {
//        mCallback = callback;
//        linkList = new ArrayList<>();
//        // This Code For Save Links For EveryUser
//
//        noteRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//
//            }
//        });
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View fragmentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_new_group_activity, parent, false);
//        return new ViewHolder(fragmentView);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//        final GroupModel myModel = linkList.get(position);
////
////        holder.mGroupName.setText(myModel.getGroupName());
////        holder.mGroupDesc.setText(myModel.getlTitle());
////        holder.profile_image.setText(myModel.getGroupName().substring(0,1) + myModel.getlTitle().substring(0,1));
////
////        Random mRandom = new Random();
////
////        int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
////        ((GradientDrawable) holder.profile_image.getBackground()).setColor(color);
////
////
////        holder.edit.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                mCallback.onEdit(myModel);
////            }
////        });
////
////        holder.delete.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                firebaseRemove(linkList.get(position));
////                notifyDataSetChanged();
////
////            }
////        });
////        holder.send.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////
////            }
////        });
////        holder.share.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////
////            }
////        });
//    }
//
//    public void firebaseRemove(Group_Object movieQuote) {
//
////        databaseReference.child(movieQuote.getKey()).removeValue();
////
////        //TODO: Remove the next line(s) and use Firebase instead
////        linkList.firebaseRemove(movieQuote);
////        notifyDataSetChanged();
//    }
//
//    public void firebasePush(Group_Object movieQuote) {
//
////        databaseReference.push().setValue(movieQuote);
//
////        //TODO: Remove the next line(s) and use Firebase instead
////        linkList.firebasePush(0, movieQuote);
////        notifyDataSetChanged();
//
//    }
//
//    public void firebaseUpdate(Group_Object link, String title, String name) {
//
//
////        //TODO: Remove the next line(s) and use Firebase instead
//        link.setGroupName(title);
//        link.setlTitle(name);
////        databaseReference.child(link.getKey()).setValue(link);
//
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemCount() {
//        return linkList.size();
//    }
//
//    public interface Callback {
//        public void onEdit(Group_Object movieQuote);
//    }
//
//    class LinkChildEventListener implements ChildEventListener {
//
//        @Override
//        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
////
////            Group_Object myLink = dataSnapshot.getValue(Group_Object.class);
////            myLink.setKey(dataSnapshot.getKey());
////            linkList.add(0, myLink);
////            notifyDataSetChanged();
//        }
//
//        @Override
//        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
////
////            String key = dataSnapshot.getKey();
////            Group_Object update_link_object = dataSnapshot.getValue(Group_Object.class);
////
////            for (Group_Object mq : linkList) {
////                if (mq.getKey().equals(key)) {
////                    mq.setValues(update_link_object);
////                    notifyDataSetChanged();
////                }
////            }
//        }
//
//        @Override
//        public void onChildRemoved(DataSnapshot dataSnapshot) {
////
////            String key = dataSnapshot.getKey();
////
////            for (Group_Object mq : linkList) {
////                if (mq.getKey().equals(key)) {
////                    linkList.remove(mq);
////                    notifyDataSetChanged();
////                    return;
////                }
////            }
//        }
//
//        @Override
//        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//
//            Log.e("DATABASE ERROR", "onCancelled Method");
//        }
//    }
//
//    class mViewHolder extends RecyclerView.mViewHolder {
//        private TextView mGroupName;
//        private TextView mGroupDesc;
//        private TextView profile_image;
//        private ImageView send,edit,delete,share;
//
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            mGroupName = itemView.findViewById(R.id.username);
//            mGroupDesc = itemView.findViewById(R.id.desc);
//            profile_image = itemView.findViewById(R.id.profile_image);
//            send = itemView.findViewById(R.id.sendBtn);
//            edit = itemView.findViewById(R.id.editBtn);
//            delete = itemView.findViewById(R.id.deleteBtn);
//            share = itemView.findViewById(R.id.ratingImg);
//
//
//
//        }
//    }
//
//}
