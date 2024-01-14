package com.example.docvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.docvet.model.Veterinario;
import com.example.docvet.services.VeterinarioService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VeterinariosActivity extends AppCompatActivity {

    private ImageView logoVet;
    private Spinner filtrosVet;
    private EditText edtPesquisar;
    private ListView listVeterinarios;

    Retrofit retrofit;
    VeterinarioService service;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinarios);

        // Inicializar elementos
        initViews();
    }

    private void initViews() {
        logoVet = findViewById(R.id.logoVet);
//        filtrosVet = findViewById(R.id.filtrosVet);
//        edtPesquisar = findViewById(R.id.edtPesquisar);
        listVeterinarios = findViewById(R.id.listVeterinarios);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8080/api/v1/veterinarios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(VeterinarioService.class);

        context = this;

        Call<List<Veterinario>> call = service.getVeterinarios();
        call.enqueue(new Callback<List<Veterinario>>() {
            @Override
            public void onResponse(Call<List<Veterinario>> call, Response<List<Veterinario>> response) {
                if (response.isSuccessful()){
                    List<Veterinario> vetLista = response.body();

                    Log.e("karol", vetLista.toString());

                    listaVetCustom listaCustom = new listaVetCustom(context, vetLista);
                    listVeterinarios.setAdapter(listaCustom);
                }
            }

            @Override
            public void onFailure(Call<List<Veterinario>> call, Throwable t) {
                Toast.makeText(VeterinariosActivity.this, "Ocorreu um erro na requisição", Toast.LENGTH_LONG).show();
                Log.e("Erro", t.getMessage());
            }
        });
    }
}
