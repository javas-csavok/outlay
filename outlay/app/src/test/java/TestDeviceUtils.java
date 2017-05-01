import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DimenRes;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import app.outlay.utils.DeviceUtils;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by robin on 2017. 05. 01..
 */

public class TestDeviceUtils {
    private Activity activity = Mockito.mock(Activity.class);
    private View view = Mockito.mock(View.class);
    private InputMethodManager inputMethodManager = Mockito.mock(InputMethodManager.class);
    private Context context = Mockito.mock(Context.class);
    private TypedValue tv = Mockito.mock(TypedValue.class);
    private Resources.Theme theme = Mockito.mock(Resources.Theme.class);
    private Resources resources = Mockito.mock(Resources.class);

    @Before
    public void setup(){

    }
    //cannot be tested because getMetrics returns void
    @Test
    public void screenSizeTest(){
    }
    @Test
    public void getStatusBarHeightBranchTrue(){
        @DimenRes
        int dim = 1;
        when(context.getTheme()).thenReturn(theme);
        when(context.getResources()).thenReturn(resources);
        when(resources.getIdentifier("status_bar_height", "dimen", "android")).thenReturn(1);
        when(resources.getDimensionPixelSize(dim)).thenReturn(1);
        Assert.assertEquals(1, DeviceUtils.getStatusBarHeight(context));

        verify(resources, times(1)).getDimensionPixelSize(dim);

    }
    @Test
    public void getStatusBarHeightBranchFalse(){
        @DimenRes
        int dim = 1;
        when(context.getTheme()).thenReturn(theme);
        when(context.getResources()).thenReturn(resources);
        when(resources.getIdentifier("status_bar_height", "dimen", "android")).thenReturn(-1);
        when(resources.getDimensionPixelSize(dim)).thenReturn(1);
        Assert.assertEquals(0, DeviceUtils.getStatusBarHeight(context));

        verify(resources, never()).getDimensionPixelSize(dim);
    }
    //cannot tested, because the creation of the TypedValue object in the method
    @Test
    public void getActionBarHeightBranchTrue(){

        when(context.getTheme()).thenReturn(theme);
        when(context.getResources()).thenReturn(resources);

        Assert.assertEquals(0,DeviceUtils.getActionBarHeight(context));


    }
    @Test
    public void getActionBarHeightBranchFalse(){
        when(context.getTheme()).thenReturn(theme);
        when(theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)).thenReturn(false);

        Assert.assertEquals(0,DeviceUtils.getActionBarHeight(context));

        verify(context, never()).getResources();

    }
    @Test
    public void hideKeyboardBranchTrue(){
        when(activity.getCurrentFocus()).thenReturn(view);
        when(activity.getSystemService(Context.INPUT_METHOD_SERVICE)).thenReturn(inputMethodManager);

        DeviceUtils.hideKeyboard(activity);

        verify(activity, times(1)).getSystemService(Context.INPUT_METHOD_SERVICE);
        verify(inputMethodManager, times(1)).hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    @Test
    public void hideKeyboardBranchFalse(){
        when(activity.getCurrentFocus()).thenReturn(null);

        DeviceUtils.hideKeyboard(activity);

        verify(activity, never()).getSystemService(Context.INPUT_METHOD_SERVICE);
        verify(inputMethodManager, never()).hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}
