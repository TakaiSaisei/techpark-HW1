package ru.mail.techpark.hw1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private static final String SIZE = "size";
    private List<Integer> mNumbers;
    private RecycleViewAdapter mAdapter;
    private int mCntNum = 100;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mCntNum = savedInstanceState.getInt(SIZE);
        }
        mNumbers = new ArrayList<>();
        for (int i = 1; i <= mCntNum; i++) {
            mNumbers.add(i);
        }
        mAdapter = new RecycleViewAdapter(mNumbers, getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int colNum;

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            colNum = getResources().getInteger(R.integer.portrait_col);
        } else colNum = getResources().getInteger(R.integer.landscape_col);

        recyclerView.setLayoutManager(new GridLayoutManager(inflater.getContext(), colNum));
        recyclerView.setAdapter(mAdapter);

        Button button = view.findViewById(R.id.add_button);
        button.setOnClickListener(v -> mAdapter.addData(mNumbers.size() + 1));
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SIZE, mNumbers.size());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAdapter = null;
        mNumbers = null;
    }
}
