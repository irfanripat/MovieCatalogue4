package com.example.submission_3.fragment;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.submission_3.R;
import com.example.submission_3.adapter.MoviesAdapter;
import com.example.submission_3.model.Movie;
import com.example.submission_3.viewmodel.MyViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment  {

    private RecyclerView mRecyclerView;
    private MoviesAdapter adapter;
    private MyViewModel myViewModel;
    private ProgressBar progressBar;


    private Observer<List<Movie>> getMovies = new Observer<List<Movie>>() {
        @Override
        public void onChanged(@Nullable List<Movie> movies) {
            if (movies.size()>0) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new MoviesAdapter(getContext(), movies);
                mRecyclerView.setAdapter(adapter);

                showLoading(false);
            }

        }
    };

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        mRecyclerView = v.findViewById(R.id.rv_movie);
        progressBar = v.findViewById(R.id.progress_bar_movie);
        showLoading(true);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadMovies();
    }

    private void loadMovies() {
            myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
            myViewModel.getMovies(getActivity()).observe(getActivity(), getMovies);
    }



    //To show progressbar or not
    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
    }

}




