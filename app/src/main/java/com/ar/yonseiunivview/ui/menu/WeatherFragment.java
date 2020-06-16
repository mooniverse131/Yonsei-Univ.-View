package com.ar.yonseiunivview.ui.menu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ar.yonseiunivview.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherFragment extends Fragment {
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "e70b2a211c8d5acc99c685bff4fab094";
    public static String lat = "37";
    public static String lon = "128";
    public static String iconUrl;
    private TextView weatherData;
    private ImageView weatherIcon;
    private FloatingActionButton btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_weather, container, false);

        weatherIcon = root.findViewById(R.id.icon);
        weatherData = root.findViewById(R.id.weather);
        btn = root.findViewById(R.id.btn_weather);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentData();
            }

        });

        return root;
    }

    void getCurrentData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(lat, lon, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;

                    iconUrl = "http://openweathermap.org/img/w/" + weatherResponse.weather.get(0).icon + ".png";
                    double temp = Math.round((weatherResponse.main.temp - 273.15) * 10) / 10.0;
                    double temp_max = Math.round((weatherResponse.main.temp_max - 273.15) * 10) / 10.0;
                    double temp_min = Math.round((weatherResponse.main.temp_min - 273.15) * 10) / 10.0;
                    String stringBuilder = "지역: 원주시\n" +
                            "현재 기온 : " + temp + "℃\n" +
                            "최고/최저 기온 : " + temp_max + "℃ / " + temp_min + "℃\n" +
                            "날씨 : " + transferWeather(weatherResponse.weather.get(0).description) + "\n" +
                            "습도 : " + weatherResponse.main.humidity + "%\n";
                    weatherData.setText(stringBuilder);
                    new DownloadImageTask().execute(iconUrl);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                weatherData.setText(t.getMessage());
            }
        });
    }
    private Bitmap loadImageFromNetwork(String url){
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected Bitmap doInBackground(String... urls) {
            return loadImageFromNetwork(urls[0]);
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(Bitmap result) {
            weatherIcon.setImageBitmap(result);
        }
    }

    private String transferWeather(String weather){
        weather = weather.toLowerCase();

        if(weather.equals("haze")) {
            return "안개";
        }else if(weather.equals("fog")) {
            return "안개";
        }else if(weather.equals("clouds")) {
            return "구름";
        }else if(weather.equals("few clouds")) {
            return "구름 조금";
        }else if(weather.equals("scattered clouds")) {
            return "구름 낌";
        }else if(weather.equals("broken clouds")) {
            return "구름 많음";
        }else if(weather.equals("overcast clouds")) {
            return "구름 많음";
        }else if(weather.equals("clear sky") || weather.equals("clear")) {
            return "맑음";
        }else return weather;
    }
}





