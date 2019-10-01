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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.submission_3.R;
import com.example.submission_3.adapter.TvShowAdapter;
import com.example.submission_3.model.TVShow;
import com.example.submission_3.viewmodel.MyViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MyViewModel myViewModel;
    private TvShowAdapter adapter;
    private ProgressBar progressBar;

    private Observer<List<TVShow>> getTvs = new Observer<List<TVShow>>() {
        @Override
        public void onChanged(@Nullable List<TVShow> tvShows) {
            if (tvShows.size()>0) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new TvShowAdapter(getContext(), tvShows);
                mRecyclerView.setAdapter(adapter);
                showLoading(false);
            }

        }
    };

    public TvShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tvshow, container, false);
        mRecyclerView = v.findViewById(R.id.rv_tvshow);
        progressBar = v.findViewById(R.id.progress_bar_tvshow);
        showLoading(true);
        return v;
    }
    private void loadTv() {
        myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        myViewModel.getTvs(getActivity()).observe(getActivity(), getTvs);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadTv();
    }

    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
    }
}
