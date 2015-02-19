package de.franziskaneumeister.counterswipe.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import roboguice.RoboGuice;

/**
 * Fragment with Robguice Support that inherits from the original fragment instead of the *
 * Support Library Fragment *
 */
public abstract class RoboFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectMembersWithoutViews(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectViewMembers(this);
    }
}