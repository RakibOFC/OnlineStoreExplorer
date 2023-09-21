package com.rakibofc.onlinestoreexplorer.ui;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rakibofc.onlinestoreexplorer.R;

public class PageInfoSheetFragment extends BottomSheetDialogFragment {

    public PageInfoSheetFragment() {
        // Required empty public constructor
    }

    public static PageInfoSheetFragment newInstance() {
        return new PageInfoSheetFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_page_info_sheet, container, false);
    }
}