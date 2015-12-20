package de.franziskaneumeister.counterswipe.injection.components;

import dagger.Subcomponent;
import de.franziskaneumeister.counterswipe.activities.CountersActivity;
import de.franziskaneumeister.counterswipe.injection.modules.ActivityModule;
import de.franziskaneumeister.counterswipe.injection.modules.FragmentModule;

@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    FragmentComponent plus(FragmentModule module);
    void inject(CountersActivity activity);
}
