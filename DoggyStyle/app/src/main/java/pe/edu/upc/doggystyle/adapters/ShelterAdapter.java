package pe.edu.upc.doggystyle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.interfaces.OnEntryClickListener;
import pe.edu.upc.doggystyle.models.Shelter;

/**
 * Created by Ricardo on 21/06/2017.
 */

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.ViewHolder> {

    private Context context;
    private List<Shelter> shelterEntries;
    private OnEntryClickListener listener;

    public ShelterAdapter(Context context, List<Shelter> shelterEntries, OnEntryClickListener listener) {
        this.context = context;
        this.shelterEntries = shelterEntries;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_shelter, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Shelter shelterEntry = shelterEntries.get(position);
        holder.nameTextView.setText(shelterEntry.getName());
        holder.locationTextView.setText(shelterEntry.getLocation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEntryClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return shelterEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView profileImageView;
        TextView nameTextView;
        TextView locationTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            profileImageView = (ANImageView) itemView.findViewById(R.id.profileImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            locationTextView = (TextView) itemView.findViewById(R.id.locationTextView);
        }
    }
}
