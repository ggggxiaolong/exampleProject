package com.zftx.pm.di;

import android.app.Application;
import com.mrtan.data.NetModule;
import com.zftx.pm.App;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

/**
 * @author mrtan on 1/17/18.
 */

@Singleton @Component(modules = {
    AndroidInjectionModule.class, NetModule.class, ActivityModule.class, ViewModelModule.class
}) public interface AppComponent {

  @Component.Builder
  interface Builder{
    @BindsInstance Builder application(Application app);

    AppComponent build();
  }

  void inject(App app);
}
