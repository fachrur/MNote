package com.rfachrur.mnote.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rfachrur.mnote.DBackup.RestoreDatabase;
import com.rfachrur.mnote.DBackup.SaveDatabase;
import com.rfachrur.mnote.InterfaceView.MainView;
import com.rfachrur.mnote.R;

/**
 * Created by rfachrur on 10/13/16.
 *
 */

public class Dialogs {

    private static MainView mainView;

    public static void offline(final MainView view) {
        mainView = view;
        final Context context = mainView.getContext();
        new MaterialDialog.Builder(context)
                .title(R.string.backup_title)
                .content(R.string.backup_content)
                .positiveText(R.string.backup_button)
                .negativeText(R.string.restore2)
                .neutralText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        boolean result = SaveDatabase.save();
                        Toast.makeText(context, result ? context.getString(R.string.backup_success) : context.getString(R.string.backup_fail), Toast.LENGTH_SHORT).show();

                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        boolean result = RestoreDatabase.importDB();
                        Toast.makeText(context, result ? context.getString(R.string.restore_success) : context.getString(R.string.restore_fail), Toast.LENGTH_SHORT).show();
                        mainView.showRestoredNotes();
                    }
                })
                .show();
    }

    public static void about(final Context context) {

        new MaterialDialog.Builder(context)
                .title(R.string.app_name_full)
                .content("MNote GGWP")
                .titleGravity(GravityEnum.CENTER)
                .contentGravity(GravityEnum.CENTER)
                .icon(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.ic_launcher, null))
                .show();
    }

}
