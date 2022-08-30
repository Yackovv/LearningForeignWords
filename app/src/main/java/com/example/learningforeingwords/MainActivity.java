package com.example.learningforeingwords;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class MainActivity extends Activity {
    EditText englishWord, russianWord, otherWord;
    LinearLayout llMain;
    FirebaseFirestore mDataFireStore;
    int count = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        englishWord = findViewById(R.id.englishWord);
        russianWord = findViewById(R.id.russianWord);
        otherWord = findViewById(R.id.otherWord);
        llMain = findViewById(R.id.llMain);
        mDataFireStore = FirebaseFirestore.getInstance();
    }

    public void onClickSaveWords(View view) {
        /*
        String english = englishWord.getText().toString();
        String russian = russianWord.getText().toString();
        String other = otherWord.getText().toString();
        */

        for (int i = 1; i < count; i = i + 4) {

            EditText eEnglish = findViewById(i);
            String english = eEnglish.getText().toString();
            int temp = i + 1;
            EditText eRussian = findViewById(temp);
            String russian = eRussian.getText().toString();
            int temp1 = i + 2;
            EditText eOther = findViewById(temp1);
            String other = eOther.getText().toString();

            Translate translate = new Translate(english, russian, other);
            sendToFireStore(translate.addWordsToMap());
        }
    }

    public void onClickAddFields(View view) {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        EditText englishWord1 = new EditText(this);
        englishWord1.setHint("Input English word");
        englishWord1.setId(count);
        llMain.addView(englishWord1, lParams);

        EditText russianWord1 = new EditText(this);
        russianWord1.setHint("Input Russian word");
        russianWord1.setId(count + 1);
        llMain.addView(russianWord1, lParams);

        EditText otherWord1 = new EditText(this);
        otherWord1.setHint("Input other word");
        otherWord1.setId(count + 2);
        llMain.addView(otherWord1, lParams);

        Button delete = new Button(this);
        delete.setText("DELETE");
        delete.setId(count + 3);
        llMain.addView(delete, lParams);

        count += 4;

        otherWord.setText(String.valueOf(count));
    }

    public void onClickDelete(View view){

    }

    public void sendToFireStore(Map<String, Map<String, String>> words) {
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
}