package br.com.rbarrelo.tabapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.rbarrelo.tabapp.R;
import de.greenrobot.event.EventBus;

/**
 * Created by rafaelbarrelo on 11/28/15.
 */
public abstract class ListaFragment extends Fragment {

    protected RecyclerView recyclerView;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_lista, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupRecyclerView();
        return recyclerView;
    }

    protected abstract void setupRecyclerView();
}