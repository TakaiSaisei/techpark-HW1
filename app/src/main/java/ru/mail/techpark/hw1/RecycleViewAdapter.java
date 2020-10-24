package ru.mail.techpark.hw1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private final List<Integer> mData;
    private final FragmentActivity activity;

    public RecycleViewAdapter(List<Integer> data, FragmentActivity mActivity) {
        mData = data;
        activity = mActivity;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int num = mData.get(position);
        if (num % 2 == 0) {
            holder.mNumber.setTextColor(Color.RED);
        } else {
            holder.mNumber.setTextColor(Color.BLUE);
        }
        holder.mNumber.setText(String.valueOf(num));
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("arg", Integer.parseInt(holder.mNumber.getText().toString()));
            NumberFragment fragment = new NumberFragment();
            fragment.setArguments(bundle);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(int number) {
        mData.add(number);
        notifyItemInserted(mData.size() - 1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNumber = itemView.findViewById(R.id.recycler_number);
        }
    }
}