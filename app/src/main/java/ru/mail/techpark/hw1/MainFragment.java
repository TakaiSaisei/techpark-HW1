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
    private List<Integer> numbers;
    private RecycleViewAdapter adapter;
    private int cntNum = 100;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            cntNum = savedInstanceState.getInt(SIZE);
        }
        numbers = new ArrayList<>();
        for (int i = 1; i <= cntNum; i++) {
            numbers.add(i);
        }
        adapter = new RecycleViewAdapter(numbers, getActivity());

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
        recyclerView.setAdapter(adapter);

        Button button = view.findViewById(R.id.add_button);
        button.setOnClickListener(v -> adapter.addData(numbers.size() + 1));
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SIZE, numbers.size());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter = null;
        numbers = null;
    }
}
