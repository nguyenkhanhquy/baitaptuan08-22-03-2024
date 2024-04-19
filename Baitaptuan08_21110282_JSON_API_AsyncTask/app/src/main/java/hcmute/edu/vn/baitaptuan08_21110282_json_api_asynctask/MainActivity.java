package hcmute.edu.vn.baitaptuan08_21110282_json_api_asynctask;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ReadJSONObject(this).execute("https://jsonplaceholder.typicode.com/users");
    }
}