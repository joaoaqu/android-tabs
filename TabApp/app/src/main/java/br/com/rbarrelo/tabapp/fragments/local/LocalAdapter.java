package br.com.rbarrelo.tabapp.fragments.local;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rbarrelo.tabapp.R;
import br.com.rbarrelo.tabapp.model.Veiculo;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class LocalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Veiculo> veiculoList;
    private final Activity activity;

    public LocalAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setVeiculoList(List<Veiculo> veiculoList) {
        this.veiculoList = veiculoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.local_item, parent, false);
        return LocalItemViewHolder.newInstance(view, activity);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        LocalItemViewHolder holder = (LocalItemViewHolder) viewHolder;

        holder.setTitle(veiculoList.get(position).getPlaca());
        holder.setSub(veiculoList.get(position).getModelo());
        holder.setLateral(veiculoList.get(position).getCor());
    }

    @Override
    public int getItemCount() {
        return veiculoList == null ? 0 : veiculoList.size();
    }
}
