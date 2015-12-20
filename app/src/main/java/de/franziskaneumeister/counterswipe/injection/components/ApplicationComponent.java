package de.franziskaneumeister.counterswipe.injection.components;

import dagger.Component;
import de.franziskaneumeister.counterswipe.injection.modules.ActivityModule;
import de.franziskaneumeister.counterswipe.injection.modules.AndroidModule;
import de.franziskaneumeister.counterswipe.injection.modules.ApplicationModule;

@Component(modules = {ApplicationModule.class, AndroidModule.class})
public interface ApplicationComponent {
    ActivityComponent plus(ActivityModule module);
}
