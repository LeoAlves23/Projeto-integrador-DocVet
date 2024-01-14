package com.example.docvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.docvet.model.Cidade;
import com.example.docvet.model.Endereco;
import com.example.docvet.model.Especialidades;
import com.example.docvet.model.Estado;
import com.example.docvet.model.Veterinario;

import java.util.ArrayList;
import java.util.List;

public class listaVetCustom extends BaseAdapter {

    private LayoutInflater minInflater;
    private List lista;

    private ImageView imagemVet;
    private TextView txt1, txt2;
    private Button setaDireita;

    public listaVetCustom(Context context, List lista){
        this.lista = lista;
        minInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Object obj = lista.get(position);
        view = minInflater.inflate(R.layout.activity_lista_vet_custom, null);

        imagemVet = (ImageView) view.findViewById(R.id.imagem_lista);
        txt1 = (TextView) view.findViewById(R.id.titulo_lista);
        txt2 = (TextView) view.findViewById(R.id.descricao_lista) ;

        setaDireita = (Button) view.findViewById(R.id.seta_direita_lista);

        if (obj instanceof Veterinario){
            try{
                if (((Veterinario) obj).getFoto().isEmpty() || ((Veterinario) obj).getFoto() == null){
                    imagemVet.setImageResource(R.drawable.docvet_cinza);
                }else {
                    Glide.with(view.getContext())
                            .load(((Veterinario) obj).getFoto())
                            .transform(new CircleCrop())
                            .into(imagemVet);
                }
            }catch (Exception e){
                imagemVet.setImageResource(R.drawable.docvet_cinza);
            }

            txt1.setText(((Veterinario) obj).getNome());
            txt2.setText(((Veterinario) obj).getDescricao());
            setaDireita.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    abrirDetalhes(v, (Veterinario) obj);
                }
            });
        }
        return view;
    }

    private void abrirDetalhes(View view, Veterinario veterinario) {
        Intent intent = new Intent(view.getContext(), DetalheVeterinario.class);

        Bundle bundle = new Bundle();

        bundle.putString("foto", veterinario.getFoto());
        bundle.putString("nome", veterinario.getNome());
        bundle.putString("crmv", veterinario.getCrmv());
        Log.e("karol", veterinario.getTelefones().toString());
        bundle.putStringArrayList("telefones", new ArrayList<>(veterinario.getTelefones()));
        bundle.putString("descricao", veterinario.getDescricao());

//        List<String> listaEnd = new ArrayList<String>();
//        if (veterinario.getEnderecos().size() > 0) {
//            for (Endereco e : veterinario.getEnderecos()) {
//                listaEnd.add(e.getClinica());
//
//                if (e.getCep() != null) {
//                    listaEnd.add(e.getCep());
//                } else {
//                    listaEnd.add("CEP não disponível");
//                }
//
//                if (e.getCidade() != null) {
//                    // Verificar se a cidade é um objeto antes de acessar suas propriedades
//                    if (e.getCidade() instanceof Cidade) {
//                        Cidade cidade = (Cidade) e.getCidade();
//
//                        if (cidade.getEstado() != null && cidade.getEstado() instanceof Estado) {
//                            listaEnd.add(cidade.getEstado().getNome());
//                        } else {
//                            listaEnd.add("Estado não disponível");
//                        }
//
//                        listaEnd.add(cidade.getNome());
//                    } else {
//                        listaEnd.add("Cidade não disponível");
//                    }
//                } else {
//                    listaEnd.add("Informações de cidade não disponíveis");
//                }
//
//                if (e.getBairro() != null) {
//                    listaEnd.add(e.getBairro());
//                } else {
//                    listaEnd.add("Bairro não disponível");
//                }
//
//                if (e.getLogradouro() != null) {
//                    listaEnd.add(e.getLogradouro());
//                } else {
//                    listaEnd.add("Logradouro não disponível");
//                }
//
//                if (e.getNumero() != null) {
//                    listaEnd.add(e.getNumero());
//                } else {
//                    listaEnd.add("Número não disponível");
//                }
//            }
//        }





//        bundle.putStringArrayList("endereco", new ArrayList<>(listaEnd));

        List<String> listaEspec = new ArrayList<String>();
        for (Especialidades espec : veterinario.getEspecialidades()){
            listaEspec.add(espec.getEspecialidade());
        }

        bundle.putStringArrayList("especialidades", new ArrayList<>(listaEspec));

        intent.putExtras(bundle);

        view.getContext().startActivity(intent);
    }
}