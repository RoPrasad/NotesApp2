package com.wolken.notesapp.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.wolken.notesapp.R;
import com.wolken.notesapp.Utils.DatabaseContract;
import com.wolken.notesapp.Utils.NotesAppDBHelper;

import java.util.Calendar;

public class NewNoteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = NewNoteActivity.class.getSimpleName();
    private NotesAppDBHelper mDBHelper;
    private Button SubmitBtn;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView TaskName;
    private TextView TaskDescription;
    private Long id;

    TextView dateofText;
    TextView datePicker;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);


        SubmitBtn = findViewById(R.id.submitbtn);
        datePicker = findViewById(R.id.tv_date);
        TaskName = findViewById(R.id.TaskName);
        TaskDescription = findViewById(R.id.TaskDescription);
        mDBHelper = new NotesAppDBHelper(getApplicationContext());

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TaskName.getText().toString().isEmpty()){
                    TaskName.setError("Please fill in a task name.");
                } else if (TaskDescription.getText().toString().isEmpty()) {
                    TaskDescription.setError("Please fill in a task description.");
                }
                else if (datePicker.getText().toString().matches("")){
                    datePicker.setError("Please enter a task date.");
                } else {

                    id = mDBHelper.insertData(TaskName.getText().toString(), TaskDescription.getText().toString(), dateofText.getText().toString());

                    if (id > 0) {


                        Log.d(getApplicationContext().toString(), "Data Fetching:" + mDBHelper.getAllNotes().toString());
                        Intent EditIntent = new Intent(NewNoteActivity.this, AddEditActivity.class);
                        startActivity(EditIntent);
                        Toast.makeText(NewNoteActivity.this, "Submitted", Toast.LENGTH_SHORT).show();

                        // Log.d(TAG,"Data Fetching From The Database"+mDBHelper.getAllNotes().toString());
                    } else {

                        Toast.makeText(getApplicationContext(), "Not Submitted", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        NewNoteActivity.this,
                        mDateSetListener,
                        year, month, day);

                dialog.show();


            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = day + "/" + month + "/" + year;

                dateofText= findViewById(R.id.tv_date);
                dateofText.setText(date);


            }
        };

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }






}
