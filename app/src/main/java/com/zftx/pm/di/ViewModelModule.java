package com.zftx.pm.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.mrtan.common.inject.ViewModelKey;
import com.mrtan.common.viewmodel.ViewModelFactory;
import com.zftx.pm.ui.LoginVM;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author mrtan on 1/17/18.
 */
@Module
public abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(LoginVM.class)
  public abstract ViewModel login(LoginVM loginVM);

  @Binds public abstract ViewModelProvider.Factory factory(ViewModelFactory factory);
}
