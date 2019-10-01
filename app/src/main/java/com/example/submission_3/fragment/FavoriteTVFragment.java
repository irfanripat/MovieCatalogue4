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
import com.example.submission_3.adapter.TvShowAdapter;
import com.example.submission_3.model.TVShow;
import com.example.submission_3.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteTVFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TvShowAdapter adapter;
    private TextView tvError;
    private ProgressBar progressBar;
    private MyViewModel myViewModel;
    private List<TVShow> list = new ArrayList<>();

    private Observer<List<TVShow>> getFavTVShows = new Observer<List<TVShow>>() {
        @Override
        public void onChanged(@Nullable List<TVShow> tvShows) {
            if (tvShows.size() > 0) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new TvShowAdapter(getContext(), tvShows);
                mRecyclerView.setAdapter(adapter);
            } else {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new TvShowAdapter(getContext(), list);
                mRecyclerView.setAdapter(adapter);
                tvError.setVisibility(View.VISIBLE);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tvshow, container, false);
        progressBar = v.findViewById(R.id.progress_bar_tvshow);
        progressBar.setVisibility(View.GONE);
        mRecyclerView = v.findViewById(R.id.rv_tvshow);
        tvError = v.findViewById(R.id.tv_error_tv);
        return v;
    }

    private void loadFavoriteData() {
        myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        myViewModel.getFavListTv(getActivity()).observe(getActivity(), getFavTVShows);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavoriteData();
    }

}
