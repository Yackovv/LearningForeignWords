package com.example.learningforeingwords;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsVeiwHolder>{

    ArrayList<String> arr;
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            arr.add(String.valueOf(s));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    @NonNull
    @androidx.annotation.NonNull
    @Override
    public WordsVeiwHolder onCreateViewHolder(@NonNull @androidx.annotation.NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        int layoutIdWordsItem = R.layout.words_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdWordsItem, viewGroup, false);
        WordsVeiwHolder viewHolder = new WordsVeiwHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @androidx.annotation.NonNull WordsVeiwHolder wordsVeiwHolder, int i) {

        wordsVeiwHolder.etEnglishWords.setText(arr.get(wordsVeiwHolder.getAdapterPosition()));
        wordsVeiwHolder.etRussianWords.setText(arr.get(wordsVeiwHolder.getAdapterPosition()));
        wordsVeiwHolder.etOtherWords.setText(arr.get(wordsVeiwHolder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class WordsVeiwHolder extends RecyclerView.ViewHolder{

        EditText etEnglishWords;
        EditText etRussianWords;
        EditText etOtherWords;
        Button bDeleteFields;

        public WordsVeiwHolder(View itemView) {
            super(itemView);

            etEnglishWords = itemView.findViewById(R.id.etEnglishWords);
            etEnglishWords.addTextChangedListener(textWatcher);
            etRussianWords = itemView.findViewById(R.id.etRussianWords);
            etRussianWords.addTextChangedListener(textWatcher);
            etOtherWords = itemView.findViewById(R.id.etOtherWords);
            etOtherWords.addTextChangedListener(textWatcher);
            bDeleteFields = itemView.findViewById(R.id.bDeleteFields);

            bDeleteFields.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etEnglishWords.setVisibility(View.GONE);
                    etRussianWords.setVisibility(View.GONE);
                    etOtherWords.setVisibility(View.GONE);
                    bDeleteFields.setVisibility(View.GONE);
                }
            });
        }
    }
}