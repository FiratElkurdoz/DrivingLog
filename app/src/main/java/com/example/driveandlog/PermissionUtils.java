package com.example.driveandlog;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

public abstract class PermissionUtils {
    /**
     * Requests the fine location permission. If a rationale with an additional explanation should
     * be shown to the user, displays a dialog that triggers the request.
     */
    public static void requestPermission(Map_page activity, int requestId,
                                         String permission, boolean finishActivity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            // Display a dialog with rationale.
            PermissionUtils.RationaleDialog.newInstance(requestId, finishActivity)
                    .show(activity.getSupportFragmentManager(), "dialog");
        } else {
            // Location permission has not been granted yet, request it.
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestId);

        }
    }

    /**
     * Checks if the result contains a {@link PackageManager#PERMISSION_GRANTED} result for a
     * permission from a runtime permissions request.
     *
     * @see android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
     */
    public static boolean isPermissionGranted(String[] grantPermissions, int[] grantResults,
                                              String permission) {
        for (int i = 0; i < grantPermissions.length; i++) {
            if (permission.equals(grantPermissions[i])) {
                return grantResults[i] == PackageManager.PERMISSION_GRANTED;
            }
        }
        return false;
    }

    /**
     * A dialog that displays a permission denied message.
     */
    public static class PermissionDeniedDialog extends DialogFragment {

        private static final String ARGUMENT_FINISH_ACTIVITY = "finish";

        private boolean mFinishActivity = false;

        /**
         * Creates a new instance of this dialog and optionally finishes the calling Activity
         * when the 'Ok' button is clicked.
         */
        public static PermissionDeniedDialog newInstance(boolean finishActivity) {
            Bundle arguments = new Bundle();
            arguments.putBoolean(ARGUMENT_FINISH_ACTIVITY, finishActivity);

            PermissionDeniedDialog dialog = new PermissionDeniedDialog();
            dialog.setArguments(arguments);
            return dialog;
        }
    }

    /**
     * A dialog that explains the use of the location permission and requests the necessary
     * permission.
     * <p>
     * The activity should implement
     * {@link android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback}
     * to handle permit or denial of this permission request.
     */
    public static class RationaleDialog extends DialogFragment {

        private static final String ARGUMENT_PERMISSION_REQUEST_CODE = "requestCode";

        private static final String ARGUMENT_FINISH_ACTIVITY = "finish";

        private boolean mFinishActivity = false;

        /**
         * Creates a new instance of a dialog displaying the rationale for the use of the location
         * permission.
         * <p>
         * The permission is requested after clicking 'ok'.
         *
         * @param requestCode    Id of the request that is used to request the permission. It is
         *                       returned to the
         *                       {@link android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback}.
         * @param finishActivity Whether the calling Activity should be finished if the dialog is
         *                       cancelled.
         */
        public static RationaleDialog newInstance(int requestCode, boolean finishActivity) {
            Bundle arguments = new Bundle();
            arguments.putInt(ARGUMENT_PERMISSION_REQUEST_CODE, requestCode);
            arguments.putBoolean(ARGUMENT_FINISH_ACTIVITY, finishActivity);
            RationaleDialog dialog = new RationaleDialog();
            dialog.setArguments(arguments);
            return dialog;
        }
    }
}