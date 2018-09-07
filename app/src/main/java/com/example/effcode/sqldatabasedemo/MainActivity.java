package com.example.effcode.sqldatabasedemo;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.effcode.sqldatabasedemo.DatabaseHandler.DatabaseHandler;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SQLite";
    DatabaseHandler mydb;

    EditText edit_id, edit_name, edit_surname, edit_marks;
    Button submit,show_data,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edit_id  = findViewById(R.id.et_ID);
        edit_name  = findViewById(R.id.et_name);
        edit_surname  = findViewById(R.id.et_Name);
        edit_marks  = findViewById(R.id.et_marks);

        submit=findViewById(R.id.bt_submit);
        show_data=findViewById(R.id.bt_view);
        update=findViewById(R.id.bt_update);
        delete=findViewById(R.id.bt_delete);

      mydb=new DatabaseHandler(this);
        addData();
        showData();
        updateData();
        deleteData();





    }

    private void deleteData() {

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer data=mydb.getDelete(edit_id.getText().toString());
                if(data > 0)
                {
                    Toast.makeText(MainActivity.this,"Delete Sucessfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Delete Failed",Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void updateData()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdate=mydb.updateData(edit_id.getText().toString(),
                        edit_name.getText().toString(),
                        edit_surname.getText().toString(),
                        edit_marks.getText().toString());

                if(isUpdate==true)
                {
                    Toast.makeText(MainActivity.this,"Update Sucessfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Update Failed",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void showData()
    {
        show_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res=mydb.getData();
                if(res.getCount()==0)
                {
                    showMessage("Error","Nothing Foun");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n");
                    buffer.append("Surname :"+ res.getString(2)+"\n");
                    buffer.append("Marks :"+ res.getString(3)+"\n");
                }

                // Show all data
                showMessage("Inserted Data",buffer.toString());
                Log.d(TAG, "data is "+buffer.toString());
            }
        });


    }

    public void showMessage(String title,String message)
    {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
        Log.d(TAG, "showMessage: "+builder.show());

    }

    public void addData()
    {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted=mydb.insertData(edit_name.getText().toString(),edit_surname.getText().toString(),edit_marks.getText().toString());
                if(isInserted==true)
                {
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                }


            }
        });


    }



}
