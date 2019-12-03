package com.example.testauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogActivity extends AppCompatActivity {
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        database= FirebaseDatabase.getInstance();



        findViewById(R.id.btn_inserer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText=findViewById(R.id.post_text);
                DatabaseReference myRef = database.getReference("message");

                String putText=editText.getText().toString();
                myRef.setValue(putText);



            }
        });
        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference("message");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value=dataSnapshot.getValue(String.class);
                        Toast.makeText(LogActivity.this,"this is "+value,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });
            }
        });



    }
}
