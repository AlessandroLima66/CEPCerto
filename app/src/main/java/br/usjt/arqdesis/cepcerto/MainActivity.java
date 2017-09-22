package br.usjt.arqdesis.cepcerto;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private EditText cep;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cep = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.resposta);

    }

    public void consultarCEP(View view){

        String consulta = "http://viacep.com.br/ws/"+cep.getText().toString()+"/json/ ";

        new ConsultarCep().execute(consulta);

    }


    private class ConsultarCep extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... strings) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();

            Response response = null;
            String resposta ="";
            try {
                response = client.newCall(request).execute();
                resposta = response.body().string();
            } catch (IOException e) {
                Log.e("Erro: ", e.getMessage());
            }

            return resposta;
        }

        @Override
        protected void onPostExecute(String result){
            textView.setText(result);
        }
    }

}
