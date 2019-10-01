package com.example.submission_3.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.submission_3.R;
import com.example.submission_3.adapter.MoviesAdapter;
import com.example.submission_3.model.Movie;
import com.example.submission_3.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovieFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MoviesAdapter adapter;
    private ProgressBar progressBar;
    private MyViewModel myViewModel;
    private TextView tvError;
    private List<Movie> listmovie = new ArrayList<>();

    private Observer<List<Movie>> getFavMovies = new Observer<List<Movie>>() {
        @Override
        public void onChanged(@Nullable List<Movie> movies) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            if (movies.size() > 0) {
                adapter = new MoviesAdapter(getContext(), movies);
                mRecyclerView.setAdapter(adapter);
            } else {
                adapter = new MoviesAdapter(getContext(),listmovie);
                mRecyclerView.setAdapter(adapter);
                tvError.setVisibility(View.VISIBLE);
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        mRecyclerView = v.findViewById(R.id.rv_movie);
        progressBar = v.findViewById(R.id.progress_bar_movie);
        progressBar.setVisibility(View.GONE);
        tvError = v.findViewById(R.id.tv_error);
        loadFavoriteData();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadFavoriteData() {
        myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        myViewModel.getFavList(getActivity()).observe(getActivity(), getFavMovies);
    }


    @Override
    public void onResume() {
        super.onResume();
        loadFavoriteData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadFavoriteData();
    }

}
