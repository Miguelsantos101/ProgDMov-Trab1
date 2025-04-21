package com.example.project_1;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project_1.databinding.FragmentThirdBinding;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;
    private final Map<String, Integer> movieImageMap = new HashMap<>();
    private final Map<String, Integer> movieDescriptionMap = new HashMap<>();
    private String selectedYear = "1978"; // Default year
    private String selectedMovie;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        initializeMovieData();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonThird.setOnClickListener(v ->
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_thirdFragment_to_SecondFragment)
        );

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(),
                R.array.list_years, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = parent.getItemAtPosition(position).toString();
                updateMovieList(selectedYear);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.listView.setOnItemClickListener((parent, view1, position, id) -> {
            selectedMovie = parent.getItemAtPosition(position).toString();
            updatePosterAndDescription(selectedMovie);
        });
        //Load the initial data.
        updateMovieList(selectedYear);
    }

    private void initializeMovieData() {
        // English movie titles and descriptions
        movieImageMap.put("Halloween", R.drawable.a_halloween_1978_poster);
        movieImageMap.put("Friday the 13th", R.drawable.b_friday_the_13th_1980_poster);
        movieImageMap.put("The Thing", R.drawable.c_the_thing_1982_poster);
        movieImageMap.put("Nightmare on Elm Street", R.drawable.d_a_nightmare_on_elm_street_1984_poster);
        movieImageMap.put("Scream", R.drawable.e_scream_1996_poster);
        movieImageMap.put("Saw", R.drawable.f_saw_2004_poster);

        movieDescriptionMap.put("Halloween", R.string.halloween_description);
        movieDescriptionMap.put("Friday the 13th", R.string.friday_the_13th_description);
        movieDescriptionMap.put("The Thing", R.string.the_thing_description);
        movieDescriptionMap.put("Nightmare on Elm Street", R.string.nightmare_on_elm_street_description);
        movieDescriptionMap.put("Scream", R.string.scream_description);
        movieDescriptionMap.put("Saw", R.string.saw_description);

        // Portuguese movie titles and descriptions
        movieImageMap.put("Halloween - A Noite do Terror", R.drawable.a_halloween_1978_poster);
        movieImageMap.put("Sexta-Feira 13", R.drawable.b_friday_the_13th_1980_poster);
        movieImageMap.put("O Enigma de Outro Mundo", R.drawable.c_the_thing_1982_poster);
        movieImageMap.put("A Hora do Pesadelo", R.drawable.d_a_nightmare_on_elm_street_1984_poster);
        movieImageMap.put("Pânico", R.drawable.e_scream_1996_poster);
        movieImageMap.put("Jogos Mortais", R.drawable.f_saw_2004_poster);

        movieDescriptionMap.put("Halloween - A Noite do Terror", R.string.halloween_description);
        movieDescriptionMap.put("Sexta-Feira 13", R.string.friday_the_13th_description);
        movieDescriptionMap.put("O Enigma de Outro Mundo", R.string.the_thing_description);
        movieDescriptionMap.put("A Hora do Pesadelo", R.string.nightmare_on_elm_street_description);
        movieDescriptionMap.put("Pânico", R.string.scream_description);
        movieDescriptionMap.put("Jogos Mortais", R.string.saw_description);
    }

    private void updatePosterAndDescription(String movie) {
        Integer imageResourceId = movieImageMap.get(movie);
        Integer descriptionResourceId = movieDescriptionMap.get(movie);

        if (imageResourceId != null && descriptionResourceId != null) {
            binding.imageView.setImageResource(imageResourceId);
            Spanned description = Html.fromHtml(getString(descriptionResourceId), Html.FROM_HTML_MODE_LEGACY);
            binding.textView.setText(description);
        } else {
            // Handle the case where no data is found for the selected movie
            binding.imageView.setImageResource(R.drawable.a_halloween_1978_poster); // Default image
            binding.textView.setText(R.string.halloween_description); // Default description
        }
    }

    private void updateMovieList(String year) {
        int movieArrayResourceId = getMovieArrayResourceId(year);

        if (movieArrayResourceId != 0) {
            ArrayAdapter<CharSequence> movieAdapter = ArrayAdapter.createFromResource(
                    requireActivity(), movieArrayResourceId, android.R.layout.simple_list_item_1);
            binding.listView.setAdapter(movieAdapter);
            if (movieAdapter.getCount() > 0){
                selectedMovie = Objects.requireNonNull(movieAdapter.getItem(0)).toString();
                updatePosterAndDescription(selectedMovie);
            }
        }
    }

    private int getMovieArrayResourceId(String year) {
        switch (year) {
            case "1978":
                return R.array.list_1978_movies;
            case "1980":
                return R.array.list_1980_movies;
            case "1982":
                return R.array.list_1982_movies;
            case "1984":
                return R.array.list_1984_movies;
            case "1996":
                return R.array.list_1996_movies;
            case "2004":
                return R.array.list_2004_movies;
            default:
                return 0;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}