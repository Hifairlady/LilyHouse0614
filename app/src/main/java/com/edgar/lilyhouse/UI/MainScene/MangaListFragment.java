package com.edgar.lilyhouse.UI.MainScene;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgar.lilyhouse.Data.MainSceneData.MangaItem;
import com.edgar.lilyhouse.Network.NetworkResponse;
import com.edgar.lilyhouse.R;
import com.edgar.lilyhouse.Utils.SpaceItemDecoration;

import java.util.List;

public class MangaListFragment extends Fragment {

    private static final String TAG = "==============" + MangaListFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout srlRefresh;
    private String queryUrl;

    private static int spanCount = 2;

    private boolean isLoadingNextPage = true;
    private boolean firstRun = true;

    private int regionCodeIndex;
    private int statusCodeIndex;
    private int orderCodeIndex = 0;

    // [12, 0, 0, 0, 0, 0] (position 0: yuri class; 1: unknown; 2: status; 3: region; 4: sortType; 5: pageNum)
    // public static final String DEFAULT_JSON_URL = "https://m.dmzj.com/classify/12-0-0-0-0-0.json";
    private static final int[] REGIONS_CODE = {0, 1, 2, 3, 4, 5, 6};
    private static final int[] STATUS_CODE = {0, 1, 2};
    private static final int[] ORDER_CODE = {0, 1};

    private MainViewModel mainViewModel;


    public MangaListFragment() {
        // Required empty public constructor
    }

    public static MangaListFragment newInstance() {
        return new MangaListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main_list, container, false);

        recyclerView = view.findViewById(R.id.rv_main_list);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        MangaListAdapter adapter = new MangaListAdapter(getContext());

        int spacingInPixels = 32;
        if (getActivity() != null) {
            spacingInPixels = getActivity().getResources().getDimensionPixelOffset(R.dimen.item_space_decoration);
        }
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(spacingInPixels);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        SimpleItemAnimator itemAnimator = (SimpleItemAnimator)recyclerView.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setSupportsChangeAnimations(false);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (adapter.getItemCount() != 0 && layoutManager.findLastVisibleItemPosition() == adapter.getItemCount() - 1
                            && !isLoadingNextPage) {
                        int curPage = adapter.getCurPage() + 1;
                        Log.d(TAG, "onScrollStateChanged: " + curPage);
                        isLoadingNextPage = true;
                        queryUrl = getSortUrl(0, 0, orderCodeIndex, curPage);
                        mainViewModel.refreshData(queryUrl, response);
                    }
                }
            }
        });

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getAllItems().observe(this, new Observer<List<MangaItem>>() {
            @Override
            public void onChanged(@Nullable List<MangaItem> mangaItems) {
                Log.d(TAG, "onChanged: hello");
                adapter.setListItems(mangaItems);
                srlRefresh.setRefreshing(false);
                isLoadingNextPage = false;

                if (firstRun) {
                    firstRun = false;
                    queryUrl = getSortUrl(0, 0, orderCodeIndex, adapter.getCurPage());
                    Log.d(TAG, "onChanged: " + adapter.getCurPage());
                    mainViewModel.refreshData(queryUrl, response);
                }

            }
        });

        srlRefresh = view.findViewById(R.id.srl_main_list);
        srlRefresh.setColorSchemeColors(0xFBB8AC);
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainViewModel.deleteAllItems();
                queryUrl = getSortUrl(0, 0, orderCodeIndex, 0);
                mainViewModel.refreshData(queryUrl, response);
            }
        });

//        mainViewModel.deleteAllItems();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getActivity() == null) return;
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = 2;
        } else if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 4;
        }

    }

    public String getSortUrl(int statusCode, int regionCode, int orderCode, int pageNum) {
        String jsonUrl = "https://m.dmzj.com/classify/";
        jsonUrl = jsonUrl + String.valueOf(12) + "-0-" + String.valueOf(STATUS_CODE[statusCode]) +
                "-" + REGIONS_CODE[regionCode] + "-" + String.valueOf(ORDER_CODE[orderCode]) +
                "-" + String.valueOf(pageNum) + ".json";
        return jsonUrl;
    }

    private NetworkResponse response = new NetworkResponse() {
        @Override
        public void onFailed(int resultCode) {
            isLoadingNextPage = false;
            srlRefresh.setRefreshing(false);

            switch (resultCode) {
                case 101:
                    Snackbar.make(recyclerView.getRootView(), "Error: IOException", Snackbar.LENGTH_SHORT).show();
                    break;

                case 102:
                    Snackbar.make(recyclerView.getRootView(), "Error: IllegalArgumentException", Snackbar.LENGTH_SHORT).show();
                    break;

                case 103:
                    Snackbar.make(recyclerView.getRootView(), "Error: IOException", Snackbar.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

        @Override
        public void onSucceed() {

        }
    };

}
