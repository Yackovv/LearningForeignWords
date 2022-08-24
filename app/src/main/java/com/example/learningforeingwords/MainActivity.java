package com.example.learningforeingwords;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText englishWord, russianWord, otherWord;
    public LinearLayout llMain;
    private FirebaseFirestore mDataFireStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        englishWord = findViewById(R.id.englishWord);
        russianWord = findViewById(R.id.russianWord);
        otherWord = findViewById(R.id.otherWord);
        llMain = findViewById(R.id.llMain);
        mDataFireStore = FirebaseFirestore.getInstance();
    }

    public void onClickSave(View view){
        String english = englishWord.getText().toString();
        String russian = russianWord.getText().toString();
        String other = otherWord.getText().toString();
        Translate translate = new Translate(english, russian, other);
        var words = translate.getWordsForFireStore();

        mDataFireStore.collection("userss").add(words).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "documentSnapshot added with ID: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });
    }

    public void onClickDelete(View view){

    }

    public void onClickAdd(View view){
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        EditText newEditText = new EditText(this);
        newEditText.setText("Памагити мне плохо");
        llMain.addView(newEditText, lParams);
    }
}