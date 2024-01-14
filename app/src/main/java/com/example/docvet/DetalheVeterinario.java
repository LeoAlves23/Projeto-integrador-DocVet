package com.example.docvet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.docvet.model.Especialidades;

import java.util.ArrayList;

public class DetalheVeterinario extends AppCompatActivity {

    TextView nome, descricao, crmv, telefone, especialidades, endereco;
    ImageView imagemVet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_veterinario);

        imagemVet = findViewById(R.id.id_foto_detalhes);
        nome = findViewById(R.id.id_nome_vet_desc);
        descricao = findViewById(R.id.id_descricao);
        crmv = findViewById(R.id.id_crmv_descricao);
        telefone = findViewById(R.id.id_telefone_detalhes);
        especialidades = findViewById(R.id.id_especialidades_detalhes);
//        endereco = findViewById(R.id.id_end_atend_detalhe);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            Glide.with(this)
                    .load(extras.getString("foto"))
                    .transform(new CircleCrop())
                    .into(imagemVet);


            String nomeVeterinario = extras.getString("nome");
            nome.setText(nomeVeterinario);
            String crmVeterinario = extras.getString("crmv");
            crmv.setText("CRMV: " + crmVeterinario);
            String descricaoVet = extras.getString("descricao");
            descricao.setText(descricaoVet);
            ArrayList<String> telefonesVet = extras.getStringArrayList("telefones");
            ArrayList<String> especialidadesVet = extras.getStringArrayList("especialidades");
            ArrayList<String> endVet = extras.getStringArrayList("endereco");

            if (especialidadesVet != null){
                for (String especialidade : especialidadesVet){
                    String espe = especialidades.getText().toString();
                    especialidades.setText(espe + especialidade + "\n");
                }
            }else{
                especialidades.setText("Este veterinário não possui telefones cadastrados");
            }


            if (telefonesVet != null){
                for (String tel : telefonesVet){
                    String texto = telefone.getText().toString();
                    telefone.setText(texto + tel + "\n");
                }
            }else{
                telefone.setText("Este veterinário ainda não possui telefones cadastrados");
            }

            if (endVet != null){
                for (String e : endVet){
                    String texto = endereco.getText().toString();
                    endereco.setText(texto + e + "\n");
                }
            }

        }
    }
}