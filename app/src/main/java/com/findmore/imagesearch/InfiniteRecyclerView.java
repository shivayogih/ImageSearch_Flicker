package com.findmore.imagesearch;

import android.content.Context;

import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 *
 */
public class InfiniteRecyclerView extends RecyclerView {

    public interface PageListener {
        void onListEnd();
    }

    private int pastVisibleItem, visibleItemCount, totalItemCount;
    private boolean isLoading = false;
    private PageListener pageListener;

    public InfiniteRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        addOnScrollListener(getOnScrollListener());
    }

    public void setPageListener(PageListener pageListener) {
        this.pageListener = pageListener;
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    private RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (dy > 0) {
                    visibleItemCount = lm.getChildCount();
                    totalItemCount = lm.getItemCount();
                    pastVisibleItem = lm.findFirstVisibleItemPosition();
                    if (!isLoading) {
                        if ((visibleItemCount + pastVisibleItem) >= totalItemCount) {
                            isLoading = true;
                            if (pageListener != null) {
                                pageListener.onListEnd();
                            }
                        }
                    }
                }
            }
        };
    }

}
