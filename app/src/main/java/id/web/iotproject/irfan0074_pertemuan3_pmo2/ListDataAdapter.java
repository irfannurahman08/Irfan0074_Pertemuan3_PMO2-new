package id.web.iotproject.irfan0074_pertemuan3_pmo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.MyHolder>{
    private Context context;
    ArrayList<String> mNama = new ArrayList<>();
    ArrayList<String> mPosisi = new ArrayList<>();
    ArrayList<String> mClub = new ArrayList<>();

    public ListDataAdapter(Context context, ArrayList<String> mNama, ArrayList<String> mPosisi, ArrayList<String> mClub) {
        this.context = context;
        this.mNama = mNama;
        this.mPosisi = mPosisi;
        this.mClub = mClub;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.tvNama.setText(mNama.get(position));
        holder.tvPosisi.setText(mPosisi.get(position));
        holder.tvNama.setText(mClub.get(position));

        holder.layout_list_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, mNama.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNama.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView tvNama, tvPosisi, tvClub;
        LinearLayout layout_list_data;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.textViewNama);
            tvPosisi = itemView.findViewById(R.id.textViewPosisi);
            tvClub = itemView.findViewById(R.id.textViewClub);
            layout_list_data =itemView.findViewById(R.id.layout_list_recView);
        }
    }
}
