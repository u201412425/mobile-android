package pe.edu.upc.doggystyle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.doggystyle.DoggyStyleApp;
import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.interfaces.OnEntryClickListener;
import pe.edu.upc.doggystyle.models.Request;

/**
 * Created by goman on 7/6/2017.
 */

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
    private Context context;
    private List<Request> requestList;
    private OnEntryClickListener listener;

    public RequestAdapter(Context context, OnEntryClickListener listener) {
        this.context = context;
        requestList = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RequestAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_request,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameTextView.setText(getRequestList().get(position).getName());
        holder.descriptionTextView.setText(getRequestList().get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("AdoptionRequestId", getRequestList().get(position).getAdoptionRequestId() );
                        jsonObject.put("UserId", DoggyStyleApp.getInstance().getCurrentSession().getId() );
                        jsonObject.put("PetId", 0);
                        jsonObject.put("Description", "");
                        jsonObject.put("State", "ACT");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    AndroidNetworking.put("http://doggystyle.vcsoft.pe/api/adoptionrequest/"+DoggyStyleApp.getInstance().getCurrentRequest().getAdoptionRequestId())
                            .addJSONObjectBody(jsonObject) // posting json
                            .setTag("test")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONArray(new JSONArrayRequestListener() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    // do anything with response
                                }
                                @Override
                                public void onError(ANError error) {
                                    // handle error
                                    Log.d("Error", error.getLocalizedMessage());
                                }
                            });

                DoggyStyleApp.getInstance().setCurrentRequest(requestList.get(position));
                listener.onEntryClick(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getRequestList().size();
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public RequestAdapter setRequestList(List<Request> requestList) {
        this.requestList = requestList;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView)itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
