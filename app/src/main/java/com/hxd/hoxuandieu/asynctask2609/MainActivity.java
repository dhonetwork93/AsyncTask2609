package com.hxd.hoxuandieu.asynctask2609;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtKetQua;
    Button btnXuLy;
    ProgressBar pbXuly;

    public void AnhXa() {
        btnXuLy = (Button) findViewById(R.id.btnXuLy);
        txtKetQua = (TextView) findViewById(R.id.txtKetQua);
        pbXuly = (ProgressBar) findViewById(R.id.progressBar);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        pbXuly.setVisibility(View.INVISIBLE);//an progressBar
        btnXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new XuLyCongViec().execute(); //chay class
                    }
                });//tien trinh nho
            }
        });
    }
    private class XuLyCongViec extends AsyncTask<Void, String , String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtKetQua.setText("Bất Đầu!!!");
            pbXuly.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(Void... voids) {

            for (int i = 1; i<= 5; i++) {
                publishProgress("Xong Viec " + i );//chi 1 phan tu
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                return "Thực Hiện Xong!!";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtKetQua.append("\n" + s);// noois thêm
            pbXuly.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtKetQua.append("\n"+values[0]); // phai chon phan tu 0;
        }
    }
}
