package hcmute.edu.vn.baitaptuan08_21110282_json_api_asynctask;

import android.os.AsyncTask;
import android.util.Log;
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
            Log.d("MalformedURLException", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("IOException", e.toString());
            e.printStackTrace();
        }
        Log.d("Content", content.toString());
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
//            e.printStackTrace();}
//    }
//    @Override
//    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//        // Phân tích JSON
//        try {
//            JSONObject object = new JSONObject(s);
//            // Kiểm tra xem object có chứa key "monhoc" hay không
//            if (object.has("monhoc")) {
//                // Xử lý mảng
//                JSONArray array = object.getJSONArray("monhoc");
//                // Duyệt các phần tử trong mảng
//                for (int i = 0; i < array.length(); i++) {
//                    JSONObject object1 = array.getJSONObject(i);
//                    String name = object1.getString("name");
//                    String desc = object1.getString("desc");
//                    String pic = object1.getString("pic");
//                    String kq = name + "\n" + desc + "\n" + pic;
//                    // Hiển thị thông tin từ mảng JSON bằng Toast
//                    Toast.makeText(activity, kq, Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                // Nếu không tìm thấy key "monhoc", hiển thị dữ liệu JSON lên Toast
//                Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // Phân tích JSON
        try {
            // Kiểm tra xem dữ liệu JSON có hợp lệ không
            if (s != null && !s.isEmpty()) {
                // Phân tích mảng JSON
                JSONArray jsonArray = new JSONArray(s);
                // Duyệt từng đối tượng trong mảng
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    // Lấy thông tin của người dùng từ đối tượng JSON
                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    String username = jsonObject.getString("username");
                    String email = jsonObject.getString("email");
                    // Hiển thị thông tin của người dùng bằng Toast
                    String userInfo = "ID: " + id + "\nName: " + name + "\nUsername: " + username + "\nEmail: " + email;
                    //Toast.makeText(activity, userInfo, Toast.LENGTH_SHORT).show();
                    Log.d("userInfo", userInfo);
                }
            } else {
                // Nếu dữ liệu JSON không hợp lệ, thông báo lỗi
                Toast.makeText(activity, "Invalid JSON data", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
