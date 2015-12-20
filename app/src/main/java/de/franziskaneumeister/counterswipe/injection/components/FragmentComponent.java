package de.franziskaneumeister.counterswipe.injection.components;

import dagger.Subcomponent;
import de.franziskaneumeister.counterswipe.fragments.CounterFragment;
import de.franziskaneumeister.counterswipe.injection.modules.FragmentModule;

@Subcomponent(modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(CounterFragment fragment);
}
