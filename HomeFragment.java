package com.govind8061.superfit.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.govind8061.superfit.R;
import com.govind8061.superfit.adapters.WomenAdapter;
import com.govind8061.superfit.databinding.FragmentHomeBinding;
import com.govind8061.superfit.homefragments.FlatStomachFragment;
import com.govind8061.superfit.homefragments.FullbodyFragment;
import com.govind8061.superfit.homefragments.RoundBootyFragment;
import com.govind8061.superfit.homefragments.SplitsTrainingFragment;
import com.govind8061.superfit.homefragments.StrongThighFragment;
import com.govind8061.superfit.homefragments.TonedArmsFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater);

        List<Fragment> womenExercises=new ArrayList<>();
        womenExercises.add(new FullbodyFragment());
        womenExercises.add(new FlatStomachFragment());
        womenExercises.add(new RoundBootyFragment());
        womenExercises.add(new TonedArmsFragment());
        womenExercises.add(new StrongThighFragment());
        womenExercises.add(new SplitsTrainingFragment());

        binding.vpHome.setAdapter(new WomenAdapter(requireActivity().getSupportFragmentManager(),getLifecycle(),womenExercises));
        binding.vpHome.setOffscreenPageLimit(3);
        binding.vpHome.setClipToPadding(false);
        binding.vpHome.setClipChildren(false);
        binding.vpHome.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 * 3 + 20);
                if (position < -1) {
                    page.setTranslationX(-myOffset);
                } else if (position <= 1) {
                    float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
                    page.setTranslationX(myOffset);
//                    page.setScaleY(scaleFactor);
                    page.setAlpha(scaleFactor);
                } else {
                    page.setAlpha(0);
                    page.setTranslationX(myOffset);
                }
            }
        });

        binding.vpHome.setPageTransformer(compositePageTransformer);


        return binding.getRoot();
    }
}