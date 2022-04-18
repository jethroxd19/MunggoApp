package com.example.mungoapp.dashboard.ui.rfid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.munggoapp.databinding.FragmentHomeBinding;
import com.example.munggoapp.databinding.FragmentRfidBinding;

public class RfidFragment extends Fragment {

    private FragmentRfidBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RfidViewModel rfidViewModel =
                new ViewModelProvider(this).get(RfidViewModel.class);

        binding = FragmentRfidBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRfid;
        rfidViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}