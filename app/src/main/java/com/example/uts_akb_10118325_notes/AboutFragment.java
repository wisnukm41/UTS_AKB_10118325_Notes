package com.example.uts_akb_10118325_notes;
// Tanggal Pengerjaan = 6/6/2021
// NIM = 10118325
// NAMA = WISNU MURFADILAH ROKHSAN
// KELAS = IF 8 2018
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class AboutFragment extends Fragment {
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        viewPager = view.findViewById(R.id.viewpager);

        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new About1Fragment(), "About 1");
        viewPagerAdapter.add(new About2Fragment(), "About 2");

        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        return view;
    }
}
