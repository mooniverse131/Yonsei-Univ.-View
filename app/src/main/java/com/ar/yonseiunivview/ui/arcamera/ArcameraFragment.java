package com.ar.yonseiunivview.ui.arcamera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ar.yonseiunivview.R;

public class ArcameraFragment extends Fragment {
    private ArcameraViewModel acvm;
    Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        acvm = ViewModelProviders.of(this).get(ArcameraViewModel.class);
        View root = inflater.inflate(R.layout.fragment_arcamera, container, false);
        final TextView tv = root.findViewById(R.id.arcamera_fragment);
        acvm.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv.setText(s);
            }
        });

        btn = (Button) root.findViewById(R.id.ar_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), ArcameraActivity.class);
                startActivity(intent);

            }
        });

        return root;
    }
}
