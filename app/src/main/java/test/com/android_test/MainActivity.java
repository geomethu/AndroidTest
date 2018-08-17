package test.com.android_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void readJsonData() {

        new Thread(new Runnable() {
            public void run(){
                try {
                    // get json data
                    JSONArray jArray = new JSONArray("my_json_data");
                    int dataLength = (jArray.length() <= 1500000) ? jArray.length() : 1500000;
                    for(int i = 0; i < dataLength; i++){
                        // get object(s) from the data array
                        JSONObject jObj = jArray.getJSONObject(i);
                        // get client id
                        String clientID = jObj.getString("Custom_ID");
                        // check aganist the bank API
                        // ... pass the client ID
                        JSONObject jObjBankScoring = new JSONObject();
                        // create a new json file
                        writeData(clientID, jObjBankScoring);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    private boolean writeData(String fileName, JSONObject data){
        boolean res = false;
        Writer output = null;
        File file = new File("path/"+fileName+".txt");
        try {
            output = new BufferedWriter(new FileWriter(file));
            output.write(data.toString());
            output.close();
            res = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
