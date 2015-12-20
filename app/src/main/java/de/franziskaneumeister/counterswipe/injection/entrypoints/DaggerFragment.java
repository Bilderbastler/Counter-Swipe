package de.franziskaneumeister.counterswipe.injection.entrypoints;


import android.support.v4.app.Fragment;

import de.franziskaneumeister.counterswipe.injection.components.FragmentComponent;
import de.franziskaneumeister.counterswipe.injection.modules.FragmentModule;

public class DaggerFragment extends Fragment {
    private FragmentComponent mComponent;

    public FragmentComponent getComponent() {
        if (mComponent == null) {
            DaggerActivity activity = (DaggerActivity) getActivity();
            mComponent = activity.getComponent().plus(new FragmentModule(this));
        }
        return mComponent;
    }

    public void setComponent(FragmentComponent component) {
        mComponent = component;
    }

    @Override
    public void onDestroy() {
        mComponent = null;
        super.onDestroy();
    }
}
