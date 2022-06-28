package com.example.innov8tif.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innov8tif.Model.PostData;
import com.example.innov8tif.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<PostData> mData;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;


    public PostAdapter(Context context , List<PostData> data , OnItemClickListener onItemClickListener) {
        mData = data;
        mContext = context;
        mOnItemClickListener = onItemClickListener;
    }

    // Called when a new view for an item must be created. This method does not return the view of
    // the item, but a ViewHolder, which holds references to all the elements of the view.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // The view for the item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        // Create a ViewHolder for this view and return it
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    // Populate the elements of the passed view (represented by the ViewHolder) with the data of
    // the item at the specified position.
    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        PostData postData = mData.get(position);

        vh.tvTitle.setText(getSafeString(postData.getTitle()));
        vh.tvBody.setText(getSafeString(postData.getBody()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private String getSafeString(String str) {
        if (str == null)
            return "";
        else
            return str;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBody;
        TextView tvTitle;
        public CardView cardView;

        // Create a viewHolder for the passed view (item view)
        ViewHolder(View view) {
            super(view);
            tvBody = (TextView) view.findViewById(R.id.tvBody);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
