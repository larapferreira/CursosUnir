package com.example.cursosunir;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends  RecyclerView.Adapter<Adapter.MyViewHolder>{

    private ArrayList<Curso> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }

    public Adapter(ArrayList<Curso> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cursos, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtCurso.setText(list.get(position).getNome());
        holder.txtCampus.setText(list.get(position).getGrau());
        holder.txtTurno.setText(list.get(position).getCampus());
        Glide.with(holder.itemView.getContext()).load(list.get(position).getImagem()).into(holder.imgAvatar);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtCurso;
        TextView txtCampus;
        TextView txtTurno;
        ImageView imgAvatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCurso = itemView.findViewById(R.id.textCurso);
            txtCampus = itemView.findViewById(R.id.txtCampus);
            txtTurno = itemView.findViewById(R.id.txtTurno);
            imgAvatar = itemView.findViewById(R.id.imageAvatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
