package com.example.project_1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project_1.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );

        binding.buttonSecond2.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_thirdFragment)
        );

        int[] list = new int[]{R.drawable.a_jason_voorhees, R.drawable.b_freddy_krueger,
                R.drawable.c_michael_myers, R.drawable.d_ghostface,
                R.drawable.e_billy_the_puppet, R.drawable.f_the_thing
        };
        binding.horrorMovies.setAdapter(new ViewAdapter(getContext(), list));

        binding.horrorMovies.setOnItemClickListener((parent, view1, position, id) -> {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            switch (position) {
                case 0:
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.a_friday_the_13th);
                    mediaPlayer.start();
                    break;
                case 1:
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.b_a_nightmare_on_elm_street);
                    mediaPlayer.start();
                    break;
                case 2:
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.c_halloween);
                    mediaPlayer.start();
                    break;
                case 3:
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.d_scream);
                    mediaPlayer.start();
                    break;
                case 4:
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.e_saw);
                    mediaPlayer.start();
                    break;
                case 5:
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.f_the_thing);
                    mediaPlayer.start();
                    break;
            }
            if (mediaPlayer != null) {
                mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.release());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}