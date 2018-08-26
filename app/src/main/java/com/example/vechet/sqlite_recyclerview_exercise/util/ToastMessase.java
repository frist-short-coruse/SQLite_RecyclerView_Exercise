package com.example.vechet.sqlite_recyclerview_exercise.util;

import android.content.Context;
import android.widget.Toast;

public class ToastMessase {
    public static void showMessage(Context context, String smg){
        Toast.makeText(context, smg, Toast.LENGTH_SHORT).show();
    }
}
