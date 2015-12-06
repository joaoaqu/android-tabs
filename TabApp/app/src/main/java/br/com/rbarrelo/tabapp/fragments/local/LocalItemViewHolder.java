package br.com.rbarrelo.tabapp.fragments.local;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.rbarrelo.tabapp.R;
import br.com.rbarrelo.tabapp.eventbus.UpdateEvent;
import br.com.rbarrelo.tabapp.model.Veiculo;
import br.com.rbarrelo.tabapp.model.VeiculoHelper;
import br.com.rbarrelo.tabapp.util.Commom;
import de.greenrobot.event.EventBus;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class LocalItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvTitle;
    private final TextView tvSubTitle;
    private final ImageView imgCorLateral;
    private final TabLayout tabLayout;

    public LocalItemViewHolder(final View parent, final TextView tvTitle, TextView tvSubTitle,
                                ImageView imgCorLateral, TabLayout tabLayout) {
        super(parent);

        parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                excluir(parent);
                return false;
            }
        });
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualiza();
            }
        });

        this.tvTitle = tvTitle;
        this.tvSubTitle = tvSubTitle;
        this.imgCorLateral = imgCorLateral;
        this.tabLayout = tabLayout;
    }


    public static LocalItemViewHolder newInstance(View parent, Activity activity) {
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        TextView tvTitle = (TextView) parent.findViewById(R.id.local_title);
        TextView tvSub = (TextView) parent.findViewById(R.id.local_subtitle);
        ImageView imgLateral = (ImageView) parent.findViewById(R.id.local_cor_lateral);

        return new LocalItemViewHolder(parent, tvTitle, tvSub, imgLateral, tabLayout);
    }

    public void setTitle(CharSequence text) {
        tvTitle.setText(text);
    }

    public void setSub(CharSequence text) {
        tvSubTitle.setText(text);
    }

    public void setLateral(int cor) {
        imgCorLateral.setBackgroundColor(cor);
    }

    public void excluir(final View parent){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        VeiculoHelper helper = new VeiculoHelper();
                        String placa = tvTitle.getText().toString();
                        if (helper.remove(placa)){
                            Snackbar.make(parent,
                                          placa + " " +  parent.getResources().getString(R.string.operacao_excluido),
                                          Snackbar.LENGTH_SHORT).show();
                        }
                        helper.notificaAlteracaoNaLista().closeRealm();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
        builder.setMessage(parent.getResources().getString(R.string.exclui_veiculo))
                .setPositiveButton(parent.getResources().getString(R.string.dialog_veiculo_sim), dialogClickListener)
                .setNegativeButton(parent.getResources().getString(R.string.dialog_veiculo_nao), dialogClickListener).show();
    }

    private void atualiza() {
        VeiculoHelper helper = new VeiculoHelper();
        Veiculo veiculo = helper.get(tvTitle.getText().toString());
        helper.closeRealm();

        UpdateEvent event = new UpdateEvent(veiculo);
        EventBus.getDefault().post(event);
        TabLayout.Tab tab = tabLayout.getTabAt(Commom.TAB_CADASTRO);
        tab.select();
    }
}