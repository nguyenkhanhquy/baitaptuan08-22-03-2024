package hcmute.edu.vn.baitaptuan08_21110282_demoasynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextParent;
    // tạo constructor
    public MyAsyncTask(Activity contextParent) {
        this.contextParent = contextParent;
    }
    // implement method

    @Override
    protected void onCancelled(Void unused) {
        super.onCancelled(unused);
        Toast.makeText(contextParent, "Tác vụ đã bị hủy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100);
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        ProgressBar progressBar = (ProgressBar) contextParent.findViewById(R.id.prbDemo);
        int number = values [0];
        progressBar.setProgress(number);
        TextView textView = (TextView) contextParent.findViewById(R.id.txtStatus);
        textView.setText(number + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(contextParent, "Đã hoàn thành, Finished", Toast.LENGTH_SHORT).show();
    }
}
