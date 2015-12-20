package de.franziskaneumeister.counterswipe.injection.modules;

import android.support.v4.app.Fragment;

import dagger.Module;

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }
}
