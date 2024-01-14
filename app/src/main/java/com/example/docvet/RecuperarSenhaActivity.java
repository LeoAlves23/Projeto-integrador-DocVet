package com.example.docvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.docvet.model.Credenciais;
import com.example.docvet.services.PessoaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private EditText edtSenha, edtConfSenha, edtEmailrs;
    private Button btnSalvarNovaSenha;
    private Retrofit retrofit;
    private PessoaService service;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        // Inicializar elementos
        initViews();

        btnSalvarNovaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isSenhaValida = validarCadastro();

                if (isSenhaValida){
                    Credenciais crendencial = new Credenciais(edtEmailrs.getText().toString(), edtSenha.getText().toString());

                    Call<Void> call = service.alterarSenha(crendencial);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200){
                                Toast.makeText(RecuperarSenhaActivity.this, "Recuperação de senha realizada com sucesso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else if (response.code() == 404){
                                Toast.makeText(RecuperarSenhaActivity.this, "Usuário não cadastrado", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(RecuperarSenhaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

    private void initViews() {
        edtEmailrs = findViewById(R.id.edtNovaSenhaEmail);
        edtSenha = findViewById(R.id.edtNovaSenhaSenha);
        edtConfSenha = findViewById(R.id.edtConfSenhaDonoPet);
        btnSalvarNovaSenha = findViewById(R.id.btnSalvarNovaSenha);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8080/api/v1/pessoas/alterarSenha/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(PessoaService.class);

        context = this;
    }

    private Boolean validarCadastro() {
        String senha = edtSenha.getText().toString();
        String confSenha = edtConfSenha.getText().toString();

        if (senha.isEmpty() || confSenha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!senha.equals(confSenha)) {
            Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}