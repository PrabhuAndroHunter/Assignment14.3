package com.assignment;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = MainActivity.class.toString();
    private Button mSaveBtn, mCheckBtn, mDeleteBtn;
    private final String FILE_NAME = "Assignment143", DATA = "Hello Acadgild Team Thank you For your support";
    private String filePath;
    private FileOutputStream fileOutputStream = null;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init layout
        setContentView(R.layout.activity_main);
        // init button views
        mSaveBtn = (Button) findViewById(R.id.save_file);
        mCheckBtn = (Button) findViewById(R.id.check_file);
        mDeleteBtn = (Button) findViewById(R.id.delete_file);

        // set onclick listener on buttons
        mSaveBtn.setOnClickListener(this);
        mCheckBtn.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);
        filePath = getFilesDir().getAbsolutePath() + "/" + FILE_NAME;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();   // get viewID
        if (id == R.id.save_file) {
            try {
                fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE); // create file with specified  name
                fileOutputStream.write(DATA.getBytes()); // write data in file
                fileOutputStream.close();
                // show toast
                Toast.makeText(this, "File Saved Successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, "onClick: " + e.getMessage());
            }
        } else if (id == R.id.check_file) {
            file = new File(filePath); // get file reference object
            // check whether the file is exist or not
            if (file.exists()) {
                // file exist, show toast
                Toast.makeText(this, "File existed", Toast.LENGTH_SHORT).show();
            } else {
                // file exist, show toast
                Toast.makeText(this, "File not existed", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.delete_file) {
            file = new File(filePath); // get file reference object
            // check whether the file is exist or not
            if (file.exists()) {
                // file exist, show toast
                if (file.delete())
                    Toast.makeText(this, "File Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "File not Deleted", Toast.LENGTH_SHORT).show();
            } else {
                // file exist, show toast
                Toast.makeText(this, "File not existed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
