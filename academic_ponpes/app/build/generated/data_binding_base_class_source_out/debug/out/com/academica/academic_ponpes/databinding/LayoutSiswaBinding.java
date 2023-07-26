// Generated by view binder compiler. Do not edit!
package com.academica.academic_ponpes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.academica.academic_ponpes.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LayoutSiswaBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final ImageView btnDelete;

  @NonNull
  public final ImageView btnEdit;

  @NonNull
  public final MaterialCardView cardSiswa;

  @NonNull
  public final TextView tvNama;

  @NonNull
  public final TextView tvNis;

  private LayoutSiswaBinding(@NonNull MaterialCardView rootView, @NonNull ImageView btnDelete,
      @NonNull ImageView btnEdit, @NonNull MaterialCardView cardSiswa, @NonNull TextView tvNama,
      @NonNull TextView tvNis) {
    this.rootView = rootView;
    this.btnDelete = btnDelete;
    this.btnEdit = btnEdit;
    this.cardSiswa = cardSiswa;
    this.tvNama = tvNama;
    this.tvNis = tvNis;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static LayoutSiswaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LayoutSiswaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout_siswa, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LayoutSiswaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_delete;
      ImageView btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.btn_edit;
      ImageView btnEdit = ViewBindings.findChildViewById(rootView, id);
      if (btnEdit == null) {
        break missingId;
      }

      MaterialCardView cardSiswa = (MaterialCardView) rootView;

      id = R.id.tv_nama;
      TextView tvNama = ViewBindings.findChildViewById(rootView, id);
      if (tvNama == null) {
        break missingId;
      }

      id = R.id.tv_nis;
      TextView tvNis = ViewBindings.findChildViewById(rootView, id);
      if (tvNis == null) {
        break missingId;
      }

      return new LayoutSiswaBinding((MaterialCardView) rootView, btnDelete, btnEdit, cardSiswa,
          tvNama, tvNis);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}