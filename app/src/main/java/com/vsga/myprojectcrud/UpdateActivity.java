package com.vsga.myprojectcrud;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input,age_input,address_input;
    Button update_button,delete_button;

    String id,name, address;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        age_input = findViewById(R.id.age_input2);
        address_input = findViewById(R.id.address_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);


        //first we call this
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update data
                name = name_input.getText().toString().trim();
                address = address_input.getText().toString().trim();
                try {
                    age = Integer.parseInt(age_input.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(UpdateActivity.this, "Invalid age format", Toast.LENGTH_SHORT).show();
                    return;
                }

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id, name, age, address);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")
                && getIntent().hasExtra("age") && getIntent().hasExtra("address")) {

            // Getting Data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            address = getIntent().getStringExtra("address");
            try {
                age = Integer.parseInt(getIntent().getStringExtra("age"));
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid age format in intent", Toast.LENGTH_SHORT).show();
                return;
            }

            // Setting Intent Data
            name_input.setText(name);
            age_input.setText(String.valueOf(age));
            address_input.setText(address);

        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ? ");
        builder.setMessage("Are you sure want to delete?" + name + " ? ");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}