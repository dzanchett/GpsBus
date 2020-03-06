package com.cefet.gpsbus;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private Context context;
    private TextView statusCarregamento;

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ){//Can add more as per requirement

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            checkPermission();
        }

        DbHelper mDbHelper = new DbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        DB.setDataBase(db);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Log.d("GPSS", "ok");
            Intent intent = new Intent(this, NotificacaoCampoVazio.class);
            intent.putExtra("TEXT", "Por favor ative o GPS do seu celular para o funcionamento correto deste aplicativo!");
            startActivity(intent);
        }else {

            boolean primeiroUso = sharedPref.getBoolean(getString(R.string.primeiroUso), true);

            TextView textView = findViewById(R.id.textView);

            if (primeiroUso == false) {
                textView.setText("Carregando...");
                if (isNetworkAvailable()) {
                    //se tiver internet;
                    Intent intent = new Intent(context, TelaInicialSeHaInternet.class);
                    startActivity(intent);
                } else {
                    //se não tivet intenet;
                    Intent intent = new Intent(context, TelaInicialSeNaoHaInternet.class);
                    startActivity(intent);
                }
            } else {
                statusCarregamento = (TextView) findViewById(R.id.statusCarregamento);
                statusCarregamento.setVisibility(View.VISIBLE);

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        statusCarregamento.post(new Runnable() {
                            @Override
                            public void run() {
                                statusCarregamento.setText(DB.getStatus());
                            }
                        });

                        if(!DB.getStatus().equals("100%")) {
                            handler.postDelayed(this, 100);
                        }
                    }
                }, 100);

                new MakeDataBase().execute();
            }
        }
    }

    private class MakeDataBase extends AsyncTask<Integer, Void, Void> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */

        protected Void doInBackground(Integer... params) {
            boolean primeiroUso = sharedPref.getBoolean(getString(R.string.primeiroUso), true);

            DbHelper mDbHelper = new DbHelper(context);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            DB.setDataBase(db);

            if (primeiroUso == true) {
                try {
                    DB.createDb(db, getBaseContext());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean(getString(R.string.primeiroUso), false);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            onPostExecute();
            return null;
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute() {

            if (isNetworkAvailable()) {
                //se tiver internet;
                Intent intent = new Intent(context, TelaInicialSeHaInternet.class);
                startActivity(intent);
            } else {
                //se não tivet intenet;
                Intent intent = new Intent(context, TelaInicialSeNaoHaInternet.class);
                startActivity(intent);
            }
        }
    }
}