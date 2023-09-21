package com.rakibofc.onlinestoreexplorer.ui;

import android.graphics.Typeface;
import android.os.Bundle;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rakibofc.onlinestoreexplorer.R;
import com.rakibofc.onlinestoreexplorer.databinding.FragmentPageInfoSheetBinding;
import com.rakibofc.onlinestoreexplorer.model.Page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageInfoSheetFragment extends BottomSheetDialogFragment {

    private Page pageInfo;

    public PageInfoSheetFragment() {
        // Required empty public constructor
    }

    public static PageInfoSheetFragment newInstance() {
        return new PageInfoSheetFragment();
    }

    public void setPageInfo(Page pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentPageInfoSheetBinding binding = FragmentPageInfoSheetBinding.inflate(inflater, container, false);

        if (pageInfo != null) {

            binding.tvLastlyLoadedPage.setText(getStringFormat(R.string.lastly_loaded_page_d, pageInfo.getCurrentPage()));
            binding.tvItemPerPage.setText(getStringFormat(R.string.per_page_d, pageInfo.getPerPage()));
            binding.tvItemFrom.setText(getStringFormat(R.string.from_d, pageInfo.getFrom()));
            binding.tvItemTo.setText(getStringFormat(R.string.to_d, pageInfo.getTo()));
            binding.tvTotalItem.setText(getStringFormat(R.string.total_item_d, pageInfo.getTotal()));
            binding.tvTotalPage.setText(getStringFormat(R.string.total_page_d, pageInfo.getLastPage()));
            binding.tvPath.setText(getStringFormat(R.string.path_s, pageInfo.getPath()));
        }

        binding.btnOk.setOnClickListener(v -> this.dismiss());

        return binding.getRoot();
    }

    private SpannableString getStringFormat(int stringRes, int number) {
        return getSpannableString(String.format(getString(stringRes), number));
    }

    private SpannableString getStringFormat(int stringRes, String path) {
        return getSpannableString(String.format(getString(stringRes), path));
    }

    private SpannableString getSpannableString(String text) {

        String newString = text.replace("**", "");
        SpannableString spannableString;
        Pattern pattern = Pattern.compile("\\*\\*(.*?)\\*\\*", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String matchedWord = matcher.group(1);

            if (matchedWord != null) {

                int start = matcher.start();
                int end = matcher.end();
                spannableString = new SpannableString(newString);

                spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end - 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                return spannableString;
            }
        }
        return new SpannableString(text);
    }
}