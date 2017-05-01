package test;

import android.content.Context;
import android.content.res.Resources;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import app.outlay.utils.IconUtils;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stahorszki Péter on 2017. 05. 01..
 */

public class TestIconUtils {

    private Context context = Mockito.mock(Context.class);
    private Resources resources = Mockito.mock(Resources.class);

    @Before
    public void setUp(){

    }

    @Test
    public void testGetAll(){
        IconUtils iconUtils = new IconUtils();
        int resultSize = iconUtils.getAll().size();
        assertEquals(67, resultSize);
    }

    @Test
    public void loadCategoryIcon(){

    }

    @Test
    public void getToolbarIcon(){

    }

    @Test
    public void getIconMaterialIcon1(){

    }

    @Test
    public void getIconMaterialIcon2(){

    }

    @Test
    public void getToolbarIcon2(){

    }

    @Test
    public void loadCategoryIcon2(){

    }

    @Test
    public void getCategoryIcon(){

    }
}
