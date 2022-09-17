package com.example.learningforeingwords;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsViewHolder>{

  //  String[] arr = new String[10];
    ArrayList<String> arrayList;
    int count = 0;

    public WordsAdapter(ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public WordsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        arrayList.add("");
        Context context = viewGroup.getContext();
        int layoutIdWordsItem = R.layout.words_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdWordsItem, viewGroup, false);
        WordsViewHolder viewHolder = new WordsViewHolder(view, new MyCustomListener());

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WordsViewHolder wordsViewHolder, int i) {

        wordsViewHolder.myCustomListener.updatePosition(wordsViewHolder.getBindingAdapterPosition());

        wordsViewHolder.etEnglishWords.setText(arrayList.get(wordsViewHolder.getBindingAdapterPosition()));
        wordsViewHolder.etRussianWords.setText(arrayList.get(wordsViewHolder.getBindingAdapterPosition()));
        wordsViewHolder.etOtherWords.setText(arrayList.get(wordsViewHolder.getBindingAdapterPosition()));

      //  wordsViewHolder.etEnglishWords.setText(arr[wordsViewHolder.getBindingAdapterPosition()]);
      //  wordsViewHolder.etRussianWords.setText(arr[wordsViewHolder.getBindingAdapterPosition()]);
      //  wordsViewHolder.etOtherWords.setText(arr[wordsViewHolder.getBindingAdapterPosition()]);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    class WordsViewHolder extends RecyclerView.ViewHolder{

        EditText etEnglishWords;
        EditText etRussianWords;
        EditText etOtherWords;
        Button bDeleteFields;
        public MyCustomListener myCustomListener;

        public WordsViewHolder(View itemView, MyCustomListener myCustomListener) {
            super(itemView);

            this.myCustomListener = myCustomListener;

            etEnglishWords = itemView.findViewById(R.id.etEnglishWords);
            etEnglishWords.addTextChangedListener(myCustomListener);
            etRussianWords = itemView.findViewById(R.id.etRussianWords);
            etRussianWords.addTextChangedListener(myCustomListener);
            etOtherWords = itemView.findViewById(R.id.etOtherWords);
            etOtherWords.addTextChangedListener(myCustomListener);
            bDeleteFields = itemView.findViewById(R.id.bDeleteFields);

            bDeleteFields.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        etEnglishWords.setVisibility(View.GONE);
                        etRussianWords.setVisibility(View.GONE);
                        etOtherWords.setVisibility(View.GONE);
                        bDeleteFields.setVisibility(View.GONE);
                        arrayList.remove(myCustomListener.returnPosition());
                        count--;
                    }
            });
        }
    }
    private class MyCustomListener implements TextWatcher{

        private int position;

        public void updatePosition(int position){
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //arr[position] = s.toString();
            arrayList.set(position, s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        public int returnPosition(){
            return position;
        }
    }
}