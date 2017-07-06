package pe.edu.upc.doggystyle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import pe.edu.upc.doggystyle.DoggyStyleApp;
import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.interfaces.OnEntryClickListener;
import pe.edu.upc.doggystyle.models.PetEntry;

/**
 * Created by goman on 7/2/2017.
 */

public class MyPetsAdapter extends RecyclerView.Adapter<MyPetsAdapter.ViewHolder> {
    private Context context;
    private List<PetEntry> petEntries;
    private OnEntryClickListener listener;

    public MyPetsAdapter(Context context, List<PetEntry> petEntries, OnEntryClickListener listener) {
        this.context = context;
        this.setPetEntries(petEntries);
        this.listener = listener;
    }
    @Override
    public MyPetsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyPetsAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_adopt,parent,false));
    }

    @Override
    public void onBindViewHolder(final MyPetsAdapter.ViewHolder holder, final int position) {
        PetEntry petEntry = getPetEntries().get(position);
        holder.petNameTextView.setText(petEntries.get(position).getNamePet());
        holder.petAgeTextView.setText(""+petEntries.get(position).getAge());
        holder.petDescriptionTextView.setText(petEntries.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PetEntry pet = petEntries.get(position);
                DoggyStyleApp.getInstance().setCurrentPet(pet);
                listener.onEntryClick(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getPetEntries().size();
    }


    public List<PetEntry> getPetEntries() {
        return petEntries;
    }

    public MyPetsAdapter setPetEntries(List<PetEntry> petEntries) {
        this.petEntries = petEntries;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView petNameTextView;
        TextView petAgeTextView;
        TextView petDescriptionTextView;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            petNameTextView = (TextView)itemView.findViewById(R.id.petNameTextView);
            petAgeTextView = (TextView)itemView.findViewById(R.id.petAgeTextView);
            petDescriptionTextView = (TextView)itemView.findViewById(R.id.petDescriptionTextView);
        }
    }
}
