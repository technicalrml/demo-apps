// Generated by view binder compiler. Do not edit!
package com.sourcey.materiallogindemo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sourcey.materiallogindemo.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySiswaBinding implements ViewBinding {
  @NonNull
  private final SwipeRefreshLayout rootView;

  @NonNull
  public final FloatingActionButton addSiswa;

  @NonNull
  public final RecyclerView rvSiswa;

  @NonNull
  public final SwipeRefreshLayout swipe;

  @NonNull
  public final Toolbar toolbar;

  private ActivitySiswaBinding(@NonNull SwipeRefreshLayout rootView,
      @NonNull FloatingActionButton addSiswa, @NonNull RecyclerView rvSiswa,
      @NonNull SwipeRefreshLayout swipe, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.addSiswa = addSiswa;
    this.rvSiswa = rvSiswa;
    this.swipe = swipe;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public SwipeRefreshLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySiswaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySiswaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_siswa, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySiswaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_siswa;
      FloatingActionButton addSiswa = ViewBindings.findChildViewById(rootView, id);
      if (addSiswa == null) {
        break missingId;
      }

      id = R.id.rv_siswa;
      RecyclerView rvSiswa = ViewBindings.findChildViewById(rootView, id);
      if (rvSiswa == null) {
        break missingId;
      }

      SwipeRefreshLayout swipe = (SwipeRefreshLayout) rootView;

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivitySiswaBinding((SwipeRefreshLayout) rootView, addSiswa, rvSiswa, swipe,
          toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
