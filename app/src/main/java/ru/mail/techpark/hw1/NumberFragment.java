package ru.mail.techpark.hw1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NumberFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_number, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arg = getArguments();
        int num = arg.getInt("arg");
        TextView textView = getActivity().findViewById(R.id.fragment_number);
        textView.setText(String.valueOf(num));
        if (num % 2 == 0)
            textView.setTextColor(getResources().getColor(R.color.red));
        else
            textView.setTextColor(getResources().getColor(R.color.blue));
    }
}
