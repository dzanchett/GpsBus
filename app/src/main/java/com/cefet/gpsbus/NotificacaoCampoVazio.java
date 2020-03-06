package com.cefet.gpsbus;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotificacaoCampoVazio extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao_campo_vazio);

        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        setTitle("Atenção:");

        String text = getIntent().getStringExtra("TEXT");

        TextView textView = (TextView) findViewById(R.id.textViewNotificacaoCampoVazio);
        textView.setText(text);
    }

    public void ok(View view){
        finish();
    }
}
