package com.example.innov8tif.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innov8tif.Model.CommentsData;
import com.example.innov8tif.R;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> implements Filterable {

    private List<CommentsData> mData;
    private List<CommentsData> CommentsModelListFiltered;
    private Context mContext;
    private CommentsAdapter.OnItemClickListener mOnItemClickListener;

    public CommentsAdapter(Context context , List<CommentsData> data , CommentsAdapter.OnItemClickListener onItemClickListener) {
        mData = data;
        CommentsModelListFiltered = data;
        mContext = context;
        mOnItemClickListener = onItemClickListener;
    }

    // Called when a new view for an item must be created. This method does not return the view of
    // the item, but a ViewHolder, which holds references to all the elements of the view.
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // The view for the item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_comments, parent, false);
        CommentsAdapter.ViewHolder viewHolder = new CommentsAdapter.ViewHolder(listItem);
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
    public void onBindViewHolder(CommentsAdapter.ViewHolder vh, int position) {
        CommentsData commentsData = mData.get(position);

        vh.tvTitle.setText(getSafeString(commentsData.getName()));
        vh.tvEmail.setText(getSafeString(commentsData.getEmail()));
        vh.tvBody.setText(getSafeString(commentsData.getBody()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
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
        TextView tvEmail;
        public CardView cardView;

        // Create a viewHolder for the passed view (item view)
        ViewHolder(View view) {
            super(view);
            tvBody = (TextView) view.findViewById(R.id.tvBody);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvEmail = (TextView) view.findViewById(R.id.tvEmail);
            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = CommentsModelListFiltered.size();
                    filterResults.values = CommentsModelListFiltered;

                }else{
                    List<CommentsData> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(CommentsData itemsModel:CommentsModelListFiltered){
                        if(itemsModel.getName().contains(searchStr) || itemsModel.getEmail().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mData = (List<CommentsData>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
