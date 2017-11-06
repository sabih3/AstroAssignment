package ahmed.sabih.com.astroassignment.utils;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import ahmed.sabih.com.astroassignment.R;

/**
 * Created by sabih on 01-Nov-17.
 */

public class UIUtils {

    /**Method used to show / hide progress
     *
     * @param rootView
     * @param show
     */
    public static void showProgress(View rootView, boolean show){

        View progressView = rootView.findViewById(R.id.progress);

        if(progressView !=null){
            if(show){
                progressView.setVisibility(View.VISIBLE);
            }else{
                progressView.setVisibility(View.GONE);
            }
        }else{
            Log.e(UIUtils.class.getSimpleName(),"The Root view does not contain the progress bar, " +
                    "make sure you have added it in xml");
        }
    }

    /**Method for showing Snack bar
     *
     * @param coordinatorLayout
     * @param message
     */
    public static void showSnackBar(CoordinatorLayout coordinatorLayout,
                                    String message) {

        Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_LONG).show();

    }
}
