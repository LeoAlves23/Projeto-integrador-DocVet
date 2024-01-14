package com.example.docvet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.docvet.model.Veterinario;
import com.example.docvet.services.VeterinarioService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroVeterinario extends AppCompatActivity {

    EditText nomeVet, emailVet, cpfVet, crmVet, telefoneVet, senhaVet, senhaConfVet;
    CheckBox portePequeno, porteMedio, porteGrande;
    Spinner especialidade;
    Button btnCadastrar;

    Retrofit retrofit;

    VeterinarioService service;

    Context context;

    Veterinario vet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veterinario);

        initViews();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Boolean camposValidados = validarCadastro();

                if (camposValidados){
                    Call<Void> call = service.cadastrarVeterinario(vet);

                    Log.e("teste karol", vet.getNome().toString());
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 201){
                                Toast.makeText(CadastroVeterinario.this, "Seu cadastro foi realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                limparCadastro();
                            }else if (response.code() == 500){
                                Toast.makeText(CadastroVeterinario.this, "Não foi possível realizar o cadastro. Tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(CadastroVeterinario.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });
    }

    void initViews(){
        nomeVet = findViewById(R.id.edtNomeVeterinario);
        emailVet = findViewById(R.id.edtEmailVeterinario);
        cpfVet = findViewById(R.id.edtCpfVeterinario);
        crmVet = findViewById(R.id.edtCRM);
        telefoneVet = findViewById(R.id.edtTelefoneVeterinario);
        senhaVet = findViewById(R.id.edtSenhaVeterinario);
        senhaConfVet = findViewById(R.id.edtConfSenhaVeterinario);

        portePequeno = findViewById(R.id.checkPequenoVeterinario);
        porteMedio = findViewById(R.id.checkMedioVeterinario);
        porteGrande = findViewById(R.id.checkGrandeVeterinario);

        especialidade = findViewById(R.id.especialidadesVet);

        btnCadastrar = findViewById(R.id.btnCadastrarVeterinario);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8080/api/v1/veterinarios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(VeterinarioService.class);

        context = this;

    }

    private Boolean validarCadastro() {
        String nome = nomeVet.getText().toString();
        String email = emailVet.getText().toString();
        String cpf = cpfVet.getText().toString();
        String crm = crmVet.getText().toString();
        String telefone = telefoneVet.getText().toString();

        String senha = senhaVet.getText().toString();
        String confSenha = senhaConfVet.getText().toString();

        if (senha.isEmpty() || confSenha.isEmpty() || nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || crm.isEmpty() || telefone.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!senha.equals(confSenha)) {
            Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            List<String> telefones = new ArrayList<String>();
            telefones.add(telefone);
            vet = new Veterinario(nome, cpf, email, senha, crm, telefones);
            Log.e("teste karol", nomeVet.getText().toString());
            Log.e("teste karol", vet.getNome());
            return true;
        }
    }

    private void limparCadastro(){
        nomeVet.setText("");
        emailVet.setText("");
        cpfVet.setText("");
        crmVet.setText("");
        telefoneVet.setText("");
        senhaVet.setText("");
        senhaConfVet.setText("");

        portePequeno.setChecked(false);
        porteMedio.setChecked(false);
        porteGrande.setChecked(false);
    }
}