package test;

import android.content.Context;
import android.content.res.Resources;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import app.outlay.utils.ResourceUtils;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Balint on 2017. 05. 01..
 */

public class TestResourceUtils {


    private Context context = Mockito.mock(Context.class);

    private Resources resources = Mockito.mock(Resources.class);

    @Before
    public void setUp(){

    }

    @Test
    public void firstTest(){
        int[] colorArray = {1};
        when(context.getResources()).thenReturn(resources);
        when(resources.getIntArray(app.outlay.R.array.categoryColors)).thenReturn(colorArray);

        Assert.assertEquals(1,ResourceUtils.randomColor(context,1));

        verify(context, times(1)).getResources();
        verify(resources, times(1)).getIntArray(app.outlay.R.array.categoryColors);

    }
}
