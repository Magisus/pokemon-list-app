package edu.lclark.mdreyer.pokemonlistapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Magisus on 2/22/2016.
 */
public class NetworkAsyncTask extends AsyncTask<String, Integer, JSONObject> {

    private final NetworkListener listener;

    public interface NetworkListener {
        public void onNetworkTaskComplete();
    }

    private static final String TAG = NetworkAsyncTask.class.getSimpleName();

    public NetworkAsyncTask(NetworkListener listener) {
        this.listener = listener;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }

        StringBuilder responseBuilder = new StringBuilder();
        JSONObject jsonResponse = null;

        String pokemonId = params[0];
        try {
            URL url = new URL("http://pokeapi.co/api/v2/pokemon/" + pokemonId + "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStream);

            if (isCancelled()) {
                return null;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);

                if (isCancelled()) {
                    return null;
                }
            }

            jsonResponse = new JSONObject(responseBuilder.toString());
        } catch (IOException | JSONException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }

        return jsonResponse;
    }

    @Override
    protected void onCancelled(JSONObject pokemonJson) {
        super.onCancelled(pokemonJson);
        Log.d(TAG, "AsyncTask cancelled");
    }

    @Override
    protected void onPostExecute(JSONObject pokemonJson) {
        super.onPostExecute(pokemonJson);
        if (pokemonJson == null) {
            Log.e(TAG, "Resulting JSON is null");
        } else {

        }
    }
}
