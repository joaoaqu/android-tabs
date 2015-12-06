package br.com.rbarrelo.tabapp.fragments.form;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.rbarrelo.tabapp.R;
import br.com.rbarrelo.tabapp.eventbus.LocalEvent;
import br.com.rbarrelo.tabapp.eventbus.UpdateEvent;
import br.com.rbarrelo.tabapp.model.Veiculo;
import br.com.rbarrelo.tabapp.model.VeiculoHelper;
import br.com.rbarrelo.tabapp.util.Commom;
import de.greenrobot.event.EventBus;

public class FormFragment extends Fragment {

    private int corDefault;
    private ImageButton openColorButton;
    private TextView tvMarca;
    private TextView tvModelo;
    private TextView tvPlaca;
    private TextView tvLabel;
    private Button btnSalvar;
    private Button btnLimpar;
    private TabLayout tabLayout;
    private VeiculoHelper helper;

    public FormFragment() {
        // Required empty public constructor
    }

    public static FormFragment newInstance() {
        FormFragment fragment = new FormFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        helper = new VeiculoHelper();
    }

    @Override
    public void onStop() {
        super.onStop();
        helper.closeRealm();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);

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
        tvLabel = (TextView) view.findViewById(R.id.label_cadastro);

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

        this.limpaTela();
        return view;
    }

    private void openColorDialog(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ColorDialogFragment colorDialogFragment = new ColorDialogFragment(this, corDefault);
        colorDialogFragment.show(ft, "colorDialog");
    }

    public void fechaDialogCor(int color){
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
        this.usaCorSelecionada(getResources().getColor(R.color.color_black));
        this.tvLabel.setText(getString(R.string.label_cadastro));
        this.tvMarca.setText("");
        this.tvModelo.setText("");
        this.tvPlaca.setText("");
    }

    private void salvar(){
        if (isValid()){
            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca(tvPlaca.getText().toString());
            veiculo.setMarca(tvMarca.getText().toString());
            veiculo.setModelo(tvModelo.getText().toString());
            veiculo.setCor(corDefault);

            if (helper.existe(veiculo.getPlaca())){
                atualiza(veiculo);
            }else{
                insereOuAtualiza(veiculo, getResources().getString(R.string.operacao_adicionado));
            }
        }
    }

    private void atualiza(final Veiculo veiculo) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        insereOuAtualiza(veiculo, getResources().getString(R.string.operacao_atualizado));
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getResources().getString(R.string.atualiza_veiculo))
                .setPositiveButton(getResources().getString(R.string.dialog_veiculo_sim), dialogClickListener)
                .setNegativeButton(getResources().getString(R.string.dialog_veiculo_nao), dialogClickListener).show();
    }

    public void insereOuAtualiza(Veiculo veiculo, String operacao){
        helper.insereOuAtualiza(veiculo)
              .notificaAlteracaoNaLista();

        limpaTela();
        TabLayout.Tab tab = tabLayout.getTabAt(Commom.TAB_LISTA_LOCAL);
        tab.select();
        Snackbar.make(getView(), veiculo.getPlaca() + " " + operacao + "!", Snackbar.LENGTH_SHORT).show();
    }

    public void onEventMainThread(final UpdateEvent event) {
        Log.i(Commom.TAG, "[FormFragment] onEventMainThread");

        Veiculo veiculo = event.getVeiculo();
        this.tvLabel.setText(getString(R.string.label_update));
        this.tvMarca.setText(veiculo.getMarca());
        this.tvModelo.setText(veiculo.getModelo());
        this.tvPlaca.setText(veiculo.getPlaca());
        this.usaCorSelecionada(veiculo.getCor());
    }

    public boolean isValid() {

        if (tvPlaca.getText() == null || tvPlaca.getText().toString().trim().equals("")){
            mostraSnackErro(getResources().getString(R.string.erro_placa_vazia));
            return false;
        }

        if (tvModelo.getText() == null || tvModelo.getText().toString().trim().equals("")){
            mostraSnackErro(getResources().getString(R.string.erro_modelo_vazio));
            return false;
        }

        if (tvMarca.getText() == null || tvMarca.getText().toString().trim().equals("")){
            mostraSnackErro(getResources().getString(R.string.erro_marca_vazia));
            return false;
        }

        return true;

    }

    public void mostraSnackErro(String mensagem){
        Snackbar.make(getView(), mensagem, Snackbar.LENGTH_LONG).show();
    }
}
