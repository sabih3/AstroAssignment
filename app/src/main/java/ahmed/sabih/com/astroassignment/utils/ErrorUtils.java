package ahmed.sabih.com.astroassignment.utils;

import android.content.Context;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by sabih on 02-Nov-17.
 */

public class ErrorUtils {
    public static String getResolvedError(Context context, Throwable throwable) {

        String resolvedError = ErrorMessages.DEFAULT_ERROR;

        if(throwable instanceof UnknownHostException || throwable instanceof SocketTimeoutException){

            resolvedError = ErrorMessages.NETWORK_DISCONNECTED;

        }

        return resolvedError;
    }

    private static class ErrorMessages {
        public static final String DEFAULT_ERROR ="We are facing problems on your request this time, please try again later." ;
        public static final String NETWORK_DISCONNECTED = "Issue with the network. Please try again";
    }
}
