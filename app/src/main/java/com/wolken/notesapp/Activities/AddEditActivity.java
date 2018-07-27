package com.wolken.notesapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wolken.notesapp.Adapters.TaskAdapter;
import com.wolken.notesapp.Models.Task;
import com.wolken.notesapp.R;
import com.wolken.notesapp.Utils.NotesAppDBHelper;

public class AddEditActivity extends AppCompatActivity {

    private FloatingActionButton mfloatingActionButton;

    private RecyclerView mtaskRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private NotesAppDBHelper notesAppDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        notesAppDBHelper = new NotesAppDBHelper(this);
        mtaskRecyclerView = (RecyclerView) findViewById(R.id.rv_task_list);

        mtaskRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mtaskRecyclerView.setLayoutManager(mLayoutManager);

        //ADAPTER SETTING AN ITEM CLICK LISTENER ON THE CLICK OF RECYCLER VIEW ITEM
        mAdapter = new TaskAdapter(notesAppDBHelper.getAllNotes(), getApplicationContext(), new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task task) {

                Toast.makeText(getApplicationContext(),"Item Clicked",Toast.LENGTH_SHORT).show();



                AlertDialog.Builder builder = new AlertDialog.Builder(AddEditActivity.this);
                builder.setMessage("How would you like to proceed?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Delete function to be called

                        //PERFORM DELETE OPERATION ON THE DATABASE SIDE


                    }
                });

                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Update function to be called

                        //PERFORM UPDATE OPERATION ON THE DB
                    }
                });
                //CREATE AN ALERT DIALOGUE OBJECT
                AlertDialog alert11 = builder.create();
                alert11.show();


            }
        });
        mtaskRecyclerView.setAdapter(mAdapter);

        mfloatingActionButton = findViewById(R.id.floatingActionButton2);


        /*FloatingActionButton ScreenBtn=(FloatingActionButton) findViewById(R.id.ScreenBtn);
        ScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent=new Intent(Addscreen.this,Editnote.class);
                startActivity(startIntent);
            }
*/

        mfloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getApplicationContext(), "Floating Action Button Toast", Toast.LENGTH_SHORT).show();


                View makeNewNote = findViewById(R.id.fab_add_note);



                makeNewNote.setVisibility(View.VISIBLE);



            }
        });

        FloatingActionButton makeNewNote = (FloatingActionButton) findViewById(R.id.fab_add_note);

        makeNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddEditActivity.this, NewNoteActivity.class));
            }
        });

        mtaskRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AddEditActivity.this, "Recycler view on clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
