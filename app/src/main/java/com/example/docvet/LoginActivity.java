package com.example.docvet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.docvet.model.Credenciais;
import com.example.docvet.services.PessoaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private View view;
    private ImageView imgLogo, imgPatinhas1, imgPatinhas2, imgPatinhas3, imgPatinhas4;
    private TextView txtLogin, txtvEsqSenha, txtvCriarConta, txtvCriarConta2;
    private EditText edtEmail, edtSenha;
    private Button btnEntrar, btnDepois;

    private Retrofit retrofit;
    private PessoaService service;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar elementos
        initViews();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Credenciais credencial = new Credenciais(edtEmail.getText().toString(), edtSenha.getText().toString());

                Call<Void> call = service.login(credencial);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200){
                            Toast.makeText(LoginActivity.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, VeterinariosActivity.class);
                            startActivity(intent);
                            edtSenha.setText("");
                            edtEmail.setText("");
                        }else if (response.code() == 404){
                            Toast.makeText(LoginActivity.this, "Usuário não cadastrado. Tente novamente ou efetue o cadastro.", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Ocorreu um erro na requisição", Toast.LENGTH_LONG).show();
                        Log.e("Erro API", t.getMessage());
                    }
                });
            }
        });

        txtvCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        txtvCriarConta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroVeterinario.class);
                startActivity(intent);
            }
        });


        txtvEsqSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RecuperarSenhaActivity.class);
                startActivity(intent);
            }
        });

        btnDepois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, VeterinariosActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        view = findViewById(R.id.view);
        imgLogo = findViewById(R.id.ImgLogo);
        imgPatinhas1 = findViewById(R.id.imageView);
        imgPatinhas2 = findViewById(R.id.imageView2);
        imgPatinhas3 = findViewById(R.id.imageView3);
        imgPatinhas4 = findViewById(R.id.imageView4);
        txtLogin = findViewById(R.id.txtLogin);
        txtvEsqSenha = findViewById(R.id.txtvEsqSenha);
        Log.e("guilherme",txtvEsqSenha.getText().toString());
        txtvCriarConta = findViewById(R.id.txtvCriarConta);
        txtvCriarConta2 = findViewById(R.id.txtvCriarConta2);
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtSenha = findViewById(R.id.edtLoginSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnDepois = findViewById(R.id.btnDepois);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8080/api/v1/pessoas/login/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PessoaService.class);

        context = this;
    }
}
