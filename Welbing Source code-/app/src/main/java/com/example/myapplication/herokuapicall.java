package com.example.myapplication;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class herokuapicall {

    public  String apicall(ArrayList<Integer> prediction){
        String pre="unable to receive";

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
            String url = "https://welbing.herokuapp.com/";
            URL object = new URL(url);

            HttpsURLConnection con = (HttpsURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");


            String data = "{\"list\": \""+prediction.toString()+"\"}";




            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeChars(data);

            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            InputStream inputStream;
            if (200 <= responseCode && responseCode <= 299) {
                inputStream = con.getInputStream();
            } else {
                inputStream = con.getErrorStream();
            }



            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            inputStream));

            StringBuilder response = new StringBuilder();
            String currentLine;

            while ((currentLine = in.readLine()) != null)
                response.append(currentLine);

            in.close();


            pre = response.toString();
            return pre;


        }
        catch(Exception ex){
            Log.e("failed due to ", "apicall: "+ex.toString() );
            return null;
        }



    }
}
