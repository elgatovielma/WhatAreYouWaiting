/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.example.wordlistsql;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Implements a simple Adapter for a RecyclerView.
 * Demonstrates how to add a click handler for each item in the ViewHolder.
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    WordListOpenHelper mDB;


    /**
     *  Custom view holder with a text view and two buttons.
     */
    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;
        public final TextView fechaItemView;
        public final TextView horaItemView;
        public final CheckBox checkBoxItemView;
        public final TextView completadoItemView;


        public WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.tareamostrada);
            fechaItemView = (TextView) itemView.findViewById(R.id.fechamostrada);
            horaItemView = (TextView) itemView.findViewById(R.id.horamostrada);
            checkBoxItemView = (CheckBox) itemView.findViewById(R.id.tareaterminada);
            completadoItemView= (TextView) itemView.findViewById(R.id.completado);

        }
    }

    private static final String TAG = WordListAdapter.class.getSimpleName();

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_WORD = "WORD";

    private final LayoutInflater mInflater;
    Context mContext;

    public WordListAdapter(Context context, WordListOpenHelper db ) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDB = db;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        WordItem current = mDB.query(position);

        holder.wordItemView.setText(current.getWord());
        holder.fechaItemView.setText(current.getDay());
        holder.horaItemView.setText(current.getHour());

        // Keep a reference to the view holder for the click listener
        //final WordViewHolder h = holder; // needs to be final for use in callback

        // Attach a click listener to the DELETE button.
        /**
        holder.delete_button.setOnClickListener(
                new MyButtonOnClickListener(current.getId(),null){

                    @Override
                    public void onClick(View v ) {
                        int deleted = mDB.delete(id);
                        if (deleted >= 0)
                            notifyItemRemoved(h.getAdapterPosition());
                    }
        });   */
    }

    @Override
    public int getItemCount() {
        // Placeholder so we can see some mock data.
        return (int) mDB.count();
    }
}


