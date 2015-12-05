package br.com.rbarrelo.tabapp.fragments.form;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.rbarrelo.tabapp.R;
import io.realm.Realm;

public class FormFragment extends Fragment {

    private int corDefault;
    private ImageButton openColorButton;
    private TextView tvMarca;
    private TextView tvModelo;
    private TextView tvPlaca;
    private Button btnSalvar;
    private Button btnLimpar;
    private Realm realm;

    public FormFragment() {
        // Required empty public constructor
    }

    public static FormFragment newInstance() {
        FormFragment fragment = new FormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_form, container, false);
        openColorButton = (ImageButton) view.findViewById(R.id.btn_cor);
        openColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorDialog();
            }
        });

        tvMarca = (TextView) view.findViewById(R.id.text_marca);
        tvModelo = (TextView) view.findViewById(R.id.text_modelo);
        tvPlaca = (TextView) view.findViewById(R.id.text_placa);

        btnSalvar = (Button) view.findViewById(R.id.btn_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        btnLimpar = (Button) view.findViewById(R.id.btn_limpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaTela();
            }
        });

        this.usaCorSelecionada(getResources().getColor(R.color.color_primary_blue));
        return view;
    }

    private void openColorDialog(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ColorDialogFragment colorDialogFragment = new ColorDialogFragment(this, corDefault);
        colorDialogFragment.show(ft, "colorDialog");
    }

    public void turnOffDialogFragment(int color){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ColorDialogFragment colorDialogFragment = (
                ColorDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag("colorDialog");
        if(colorDialogFragment != null){
            colorDialogFragment.dismiss();
            ft.remove(colorDialogFragment);

            usaCorSelecionada(color);
        }
    }

    private  void usaCorSelecionada(int color){
        this.corDefault = color;
        this.openColorButton.setBackgroundColor(color);
    }

    private void limpaTela(){
        this.openColorButton.setBackgroundColor(getResources().getColor(R.color.color_primary_blue));
        this.tvMarca.setText("");
        this.tvModelo.setText("");
        this.tvPlaca.setText("");
    }

    private void salvar(){

    }
}
