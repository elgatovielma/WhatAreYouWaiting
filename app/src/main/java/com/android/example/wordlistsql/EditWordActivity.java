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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;




/**
 * Activity to edit an existing or create a new word.
 */
public class EditWordActivity extends AppCompatActivity {

    private static final String TAG = EditWordActivity.class.getSimpleName();

    private static final int NO_ID = -99;
    private static final String NO_WORD = "";

    private EditText mEditWordView;
    private TextView mFecha;
    private TextView mTiempo;
    private CheckBox mCheck;

    // Unique tag for the intent reply.
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY_TIEMPO = "com.example.android.wordlistsql.TIEMPO";
    public static final String EXTRA_REPLY_FECHA = "com.example.android.wordlistsql.FECHA";
    public static final String EXTRA_REPLY_CHECK = "com.example.android.wordlistsql.CHECK";

    int mId = MainActivity.WORD_ADD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word);

        mEditWordView = (EditText) findViewById(R.id.tareainsertada);
        mFecha = (TextView) findViewById(R.id.fechacreada);
        mTiempo = (TextView) findViewById(R.id.horacreada);
        mCheck = (CheckBox) findViewById(R.id.idcheckbox);

        String creacionFecha = creacionFecha();
        String creacionTiempo = creacionTiempo();
        mFecha.setText(creacionFecha);
        mTiempo.setText(creacionTiempo);

        // Get data sent from calling activity.
        Bundle extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (extras != null) {
            int id = extras.getInt(WordListAdapter.EXTRA_ID, NO_ID);
            String word = extras.getString(WordListAdapter.EXTRA_WORD, NO_WORD);
            if (id != NO_ID && word != NO_WORD) {
                mId = id;
                mEditWordView.setText(word);
            }
        } // Otherwise, start with empty fields.
    }


    /**
     *  Click handler for the Save button.
     *  Creates a new intent for the reply, adds the reply message to it as an extra,
     *  sets the intent result, and closes the activity.
     */
    public void returnReply(View view) {
        String word = ((EditText) findViewById(R.id.tareainsertada)).getText().toString();
        String fecha = ((TextView) findViewById(R.id.fechacreada)).getText().toString();
        String tiempo = ((TextView) findViewById(R.id.horacreada)).getText().toString();



        if (mCheck.isChecked()){
            Toast.makeText( getApplicationContext(),
                    "Check works", Toast.LENGTH_SHORT).show();

        }

        Intent replyIntent = new Intent();
        replyIntent.putExtra("HOLA",  mCheck.isChecked());
        replyIntent.putExtra(EXTRA_REPLY, word);
        replyIntent.putExtra(EXTRA_REPLY_FECHA, fecha);
        replyIntent.putExtra(EXTRA_REPLY_TIEMPO, tiempo);
        replyIntent.putExtra(WordListAdapter.EXTRA_ID, mId);
        setResult(RESULT_OK, replyIntent);
        finish();
    }


    private String creacionTiempo() {

        Date dt = new Date();
        int hours = dt.getHours();
        int minutes = dt.getMinutes();
        String tiempoActual= hours + ":" + minutes;
        return  tiempoActual;
    }

    private String creacionFecha() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual =  mdformat.format(calendar.getTime());

        return  fechaActual;
    }

}

