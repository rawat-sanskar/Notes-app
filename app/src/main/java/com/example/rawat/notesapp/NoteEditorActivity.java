package com.example.rawat.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

import static com.example.rawat.notesapp.MainActivity.notes;

public class NoteEditorActivity extends AppCompatActivity {
    Button save;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        final EditText editText =findViewById(R.id.editText);
        save=findViewById(R.id.save);
        Intent intent = getIntent();
        final int noteId=intent.getIntExtra("noteId",-1);
        if(noteId!=-1)
        {
            editText.setText(notes.get(noteId));
            x=noteId;
        }
        else{
            notes.add("");
            x=notes.size()-1;
        }
       /* editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             MainActivity.notes.set(noteId,String.valueOf(charSequence));
             MainActivity.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        */
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              String msg= editText.getText().toString();
              editText.setText(msg);
               notes.set(x,msg);
               MainActivity.arrayAdapter.notifyDataSetChanged();
               SharedPreferences sharedPreferences =getApplicationContext().getSharedPreferences("package com.example.rawat.notesapp", Context.MODE_PRIVATE);
               HashSet<String> set = new HashSet<>(MainActivity.notes);
               sharedPreferences.edit().putStringSet("notes",set).apply();
               Intent intent=new Intent(getApplicationContext(),MainActivity.class);
               startActivity(intent);
               Toast.makeText(NoteEditorActivity.this, "saved", Toast.LENGTH_SHORT).show();


           }
       });
    }
}
