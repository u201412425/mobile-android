package pe.edu.upc.doggystyle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.fragments.MyPetsFragment;
import pe.edu.upc.doggystyle.interfaces.OnEntryClickListener;
import pe.edu.upc.doggystyle.models.PetEntry;

/**
 * Created by p6 on 18/05/2017.
 */

public class AdoptAdapter extends RecyclerView.Adapter<AdoptAdapter.ViewHolder> {
    private Context context;
    private List<PetEntry> petEntries;
    private OnEntryClickListener listener;
    public AdoptAdapter(Context context, List<PetEntry> petEntries, OnEntryClickListener listener) {
        this.context = context;
        this.petEntries = petEntries;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_adopt,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PetEntry petEntry = petEntries.get(position);
        holder.petName.setText(petEntry.getName());
        holder.petAge.setText("Edad: "+
                getAge(petEntry.getBirthYear(),
                        petEntry.getBirthMonth(),
                        petEntry.getBirthDay()));
        String vaccinesText = "";
        for(String vaccine: petEntry.getVaccines()){
            if(vaccinesText.length()>0) vaccinesText+=",";
            vaccinesText+=vaccine;
        }
        holder.petVaccines.setText("Vacunas: "+vaccinesText);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEntryClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return petEntries.size();
    }

    private int getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView petName;
        TextView petAge;
        TextView petVaccines;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            petName = (TextView)itemView.findViewById(R.id.petNameTextView);
            petAge = (TextView)itemView.findViewById(R.id.petAgeTextView);
            petVaccines = (TextView)itemView.findViewById(R.id.petVaccinesTextView);
        }
    }
}
