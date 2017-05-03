import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import junit.framework.Assert;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;

import app.outlay.utils.ResourceUtils;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Balint on 2017. 05. 01..
 */

public class TestResourceUtils {

    private Context context;

    private Resources resources;

    private AssetManager assetManager;

    @Before
    public void setUp() throws FileNotFoundException {
        context = Mockito.mock(Context.class);
        resources = Mockito.mock(Resources.class);
        assetManager = Mockito.mock(AssetManager.class);
    }

    @Test
    public void testGetRandomColor() {
        int[] colorArray = {1};
        when(context.getResources()).thenReturn(resources);
        when(resources.getIntArray(app.outlay.R.array.categoryColors)).thenReturn(colorArray);

        Assert.assertEquals(1, ResourceUtils.randomColor(context, 1));

        verify(context, times(1)).getResources();
        verify(resources, times(1)).getIntArray(app.outlay.R.array.categoryColors);
    }


    @Test
    public void testGetIntegerResource() {
        when(context.getResources()).thenReturn(resources);

        ResourceUtils.getIntegerResource(context,"window");

        verify(context, times(1)).getResources();
    }

    //Error only if context is null
    @Test
    public void testGetIntegerResourceWithError() {
        when(context.getResources()).thenReturn(resources);

        ResourceUtils.getIntegerResource(null, "window" );

        verify(context, times(0)).getResources();

    }

    @Test
    public void testGetStringResource() {
        when(context.getResources()).thenReturn(resources);

        ResourceUtils.getStringResource(context,"window");

        verify(context, times(1)).getResources();

    }

}
