package com.rfachrur.mnote.DBackup;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by rfachrur on 10/13/16.
 *
 */

public class RestoreDatabase {

    public static boolean importDB() {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            if (externalStorageDirectory.canWrite()) {
                String currentDBPath = "//data//" + "com.rfachrur.mnote"
                        + "//databases//" + "notes.db";
                String backupDBPath = "/NotesBackup.db"; // From SD directory.
                File backupDB = new File(data, currentDBPath);
                File currentDB = new File(externalStorageDirectory, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
            return true;
        } catch (Exception e) {
            Log.v("TAG RESTORE DATABASE",e.getLocalizedMessage());
            return false;

        }
    }

}
