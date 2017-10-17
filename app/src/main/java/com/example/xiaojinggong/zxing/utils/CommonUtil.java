package com.example.xiaojinggong.zxing.utils;

import android.hardware.Camera;

/**
 * Created by cifer on 10/17/17.
 */

public class CommonUtil {

    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            if (mCamera != null)
                mCamera.release();
            mCamera = null;
        }
        return canUse;
    }
}
