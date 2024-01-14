package com.example.docvet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CadastroPet extends AppCompatActivity implements View.OnClickListener {

    TextView edtDepois;
    EditText nome, raca, peso, tamanho, idade, foto;
    Spinner tipoAnimal;
    CheckBox portePequeno, porteMedio, porteGrande, sexoF, sexoM, castradoSim, castradoNao;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);

        nome.findViewById(R.id.edtNomePet);
        raca.findViewById(R.id.edtRacaPet);
        peso.findViewById(R.id.edtPesoPet);
        tamanho.findViewById(R.id.edtTamanhoPet);
        idade.findViewById(R.id.edtIdadePet);
        foto.findViewById(R.id.edtFotoPet);

        tipoAnimal.findViewById(R.id.tipoAnimalSpinner);

        portePequeno.findViewById(R.id.checkPequenoPet);
        porteMedio.findViewById(R.id.checkMedioPet);
        porteGrande.findViewById(R.id.checkGrandePet);
        sexoF.findViewById(R.id.checkSexoPetF);
        sexoM.findViewById(R.id.checkSexoPetM);
        castradoSim.findViewById(R.id.checkSim);
        castradoNao.findViewById(R.id.checkNao);

        btnCadastrar.findViewById(R.id.btnCadastrarPet);
        btnCadastrar.setOnClickListener(this);

        edtDepois.findViewById(R.id.btnDepois);
    }


    @Override
    public void onClick(View view) {

    }
}