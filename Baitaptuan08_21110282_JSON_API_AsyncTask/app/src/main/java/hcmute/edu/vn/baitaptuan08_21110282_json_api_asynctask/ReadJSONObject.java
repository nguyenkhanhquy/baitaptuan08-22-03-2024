package hcmute.edu.vn.baitaptuan08_21110282_json_api_asynctask;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

//Tạo Class đọc Json
public class ReadJSONObject extends AsyncTask<String,Void,String> {
    private MainActivity activity;

    // Constructor để truyền tham chiếu đến MainActivity
    public ReadJSONObject(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new
                    InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
//    @Override
//    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//        //phân tích JSON
//        try {
//            JSONObject object = new JSONObject(s);
//            String name = object.getString("name");
//            String desc = object.getString("desc");
//            String pic = object.getString("pic");
//            String kq = name + "\n" + desc + "\n" + pic;
//            Toast.makeText(activity,kq,Toast.LENGTH_SHORT).show();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // Phân tích JSON
        try {
            JSONObject object = new JSONObject(s);
            // Kiểm tra xem object có chứa key "monhoc" hay không
            if (object.has("monhoc")) {
                // Xử lý mảng
                JSONArray array = object.getJSONArray("monhoc");
                // Duyệt các phần tử trong mảng
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object1 = array.getJSONObject(i);
                    String name = object1.getString("name");
                    String desc = object1.getString("desc");
                    String pic = object1.getString("pic");
                    String kq = name + "\n" + desc + "\n" + pic;
                    // Hiển thị thông tin từ mảng JSON bằng Toast
                    Toast.makeText(activity, kq, Toast.LENGTH_SHORT).show();
                }
            } else {
                // Nếu không tìm thấy key "monhoc", hiển thị dữ liệu JSON lên Toast
                Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
