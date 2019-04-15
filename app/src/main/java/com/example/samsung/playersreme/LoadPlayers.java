package com.example.samsung.playersreme;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LoadPlayers extends AsyncTask<String, Void, ArrayList<Player>> {

    private static final String API_URL = "http://uttdevelopers2019.com/remedial/players/";
    private static final String KEY_PLAYERS = "players";
    private static final String KEY_NAME = "name";
    private static final String KEY_POSITION = "position";
    private static final String KEY_POSITIONNAME = "name";
    private static final String KEY_JERSEYNUMBER = "jerseyNumber";
    private static final String KEY_MEASURABLES = "measurables";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_AGE = "age";
    private static final String KEY_PHOTO = "photo";



    private Activity activity;
    private ProgressDialog progress;

    public LoadPlayers(Activity activity) { this.activity = activity; }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(this.activity);
        progress.setMessage("Loading...");
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    protected ArrayList<Player> doInBackground(String... strings) {
        ArrayList<Player> list = new ArrayList<>();
        InputStream data;
        String result = "";
        JSONObject resultJSON = null;
        boolean error = false;
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(API_URL);
            connection = (HttpURLConnection)url.openConnection();
            data = connection.getInputStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(data));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
            Log.d("result", result);
        }
        catch (MalformedURLException ex) {
            error = true;
            Log.e("Error", ex.getMessage());
        }
        catch (IOException ex) {
            error = true;
            Log.e("Error", ex.getMessage());
        }
        finally {
            if (connection != null) connection.disconnect();
        }

        if (!error) {
            try {
                resultJSON = new JSONObject(result);
            }
            catch (JSONException ex) {
                error = true;
                Log.e("Error", ex.getMessage());
            }
        }

        if(!error) {
            try {
                JSONArray array = resultJSON.getJSONArray(KEY_PLAYERS);
                for(int i=0;i<array.length();i++) {
                    JSONObject item = array.getJSONObject(i);
                    JSONObject position = item.getJSONObject(KEY_POSITION);
                    JSONObject measurables = item.getJSONObject(KEY_MEASURABLES);

                    String Name = item.getString(KEY_NAME);
                    String PositionName = position.getString(KEY_POSITIONNAME);
                    int JerseyNumber = item.getInt(KEY_JERSEYNUMBER);
                    String Height = measurables.getString(KEY_HEIGHT);
                    int Weight = measurables.getInt(KEY_WEIGHT);
                    int Age = measurables.getInt(KEY_AGE);
                    String Photo = item.getString(KEY_PHOTO);

                    Player p = new Player(Name, PositionName, JerseyNumber, Height, Weight, Age, Photo);
                    list.add(p);
                }
            }
            catch (JSONException ex) {
                error = true;
                Log.e("Error", ex.getMessage());
            }
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<Player> list) {
        ListView listOperatingSystems = this.activity.findViewById(R.id.playerlist);
        ListAdapter adapter = new ListAdapter(list, this.activity);
        listOperatingSystems.setAdapter(adapter);
        progress.dismiss();
    }
}
