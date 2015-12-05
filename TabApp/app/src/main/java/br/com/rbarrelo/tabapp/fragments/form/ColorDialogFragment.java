package br.com.rbarrelo.tabapp.fragments.form;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.larswerkman.lobsterpicker.LobsterPicker;
import com.larswerkman.lobsterpicker.sliders.LobsterShadeSlider;

import br.com.rbarrelo.tabapp.R;

/**
 * Created by rafaelbarrelo on 12/4/15.
 */
@SuppressLint("ValidFragment")
public class ColorDialogFragment extends DialogFragment {

    private FormFragment instancia;
    private LobsterPicker lobsterPicker;
    private int defaultColor;

    @SuppressLint("ValidFragment")
    public ColorDialogFragment(FormFragment instancia, int color){
        this.instancia = instancia;
        this.defaultColor = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.color_dialog, container);
        Button btCancel = (Button) view.findViewById(R.id.btn_cancel);
        btCancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
               instancia.turnOffDialogFragment(defaultColor);
            }
        });

        Button btEscolhe = (Button) view.findViewById(R.id.btn_escolher_cor);
        btEscolhe.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                instancia.turnOffDialogFragment(lobsterPicker.getColor());
            }
        });

        this.configuraColorPicker(view);

        return(view);
    }

    private void configuraColorPicker(View view){

        lobsterPicker = (LobsterPicker) view.findViewById(R.id.lobsterpicker);
        LobsterShadeSlider shadeSlider = (LobsterShadeSlider) view.findViewById(R.id.shadeslider);
        //To connect them
        lobsterPicker.addDecorator(shadeSlider);
        lobsterPicker.setHistory(defaultColor);
    }

}
