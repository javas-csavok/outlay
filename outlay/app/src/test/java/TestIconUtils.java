import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.github.johnkil.print.PrintDrawable;
import com.github.johnkil.print.PrintView;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import app.outlay.domain.model.Category;

import java.util.List;

import app.outlay.utils.IconUtils;
import app.outlay.utils.ResourceUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Stahorszki Péter on 2017. 05. 01..
 */

public class TestIconUtils {

    private Context testContext = Mockito.mock(Context.class);
    private Context testApplicationContext = Mockito.mock(Context.class);
    private Resources testResources = Mockito.mock(Resources.class);
    private Category testCategory = Mockito.mock(Category.class);
    private PrintView testPrintView = Mockito.mock(PrintView.class);
    private IIcon testIIcon = Mockito.mock(IIcon.class);

    @Test
    public void testGetAll(){
        IconUtils iconUtils = new IconUtils();
        int resultSize = iconUtils.getAll().size();
        assertEquals(67, resultSize);
    }

    @Test
    public void testLoadCategoryIconCategoryAsParam(){
        when(testCategory.getIcon()).thenReturn("test-icon.png");
        when(testCategory.getColor()).thenReturn(3);
        when(testPrintView.getContext()).thenReturn(testContext);
        when(testContext.getPackageName()).thenReturn("packagename");
        when(testContext.getResources()).thenReturn(testResources);
        when(testResources.getIdentifier("pvar", "integer", "packagename")).thenReturn(0);

        IconUtils iconUtils = new IconUtils();

        iconUtils.loadCategoryIcon(testCategory, testPrintView);

        verify(testPrintView, times(1)).setIconFont("fonts/font-outlay.ttf");
        verify(testPrintView, times(1)).setIconCodeRes(0);
        verify(testPrintView, times(1)).setIconColor(testCategory.getColor());
    }

    @Test
    public void testLoadCategoryIconStringAsParam(){
        when(testCategory.getIcon()).thenReturn("test-icon.png");
        when(testCategory.getColor()).thenReturn(3);
        when(testPrintView.getContext()).thenReturn(testContext);
        when(testContext.getPackageName()).thenReturn("packagename");
        when(testContext.getResources()).thenReturn(testResources);
        when(testResources.getIdentifier("pvar", "integer", "packagename")).thenReturn(0);

        IconUtils iconUtils = new IconUtils();
        iconUtils.loadCategoryIcon("icon", testPrintView);

        verify(testPrintView, times(1)).setIconFont("fonts/font-outlay.ttf");
        verify(testPrintView, times(1)).setIconCodeRes(0);
    }

    @Test(expected = NullPointerException.class)
    public void testLoadCategoryIconNullCategoryParameters(){
        when(testPrintView.getContext()).thenReturn(testContext);
        when(testContext.getPackageName()).thenReturn("packagename");
        when(testContext.getResources()).thenReturn(testResources);
        when(testResources.getIdentifier("pvar", "integer", "packagename")).thenReturn(0);
        testCategory = null;
        IconUtils iconUtils = new IconUtils();
        iconUtils.loadCategoryIcon(testCategory, testPrintView);
    }

    @Test(expected = NullPointerException.class)
    public void testLoadCategoryIconNullPrintViewParameters(){
        when(testCategory.getIcon()).thenReturn("test-icon.png");
        when(testCategory.getColor()).thenReturn(3);
        when(testPrintView.getContext()).thenReturn(testContext);
        when(testContext.getPackageName()).thenReturn("packagename");
        when(testContext.getResources()).thenReturn(testResources);
        when(testResources.getIdentifier("pvar", "integer", "packagename")).thenReturn(0);
        testPrintView = null;
        IconUtils iconUtils = new IconUtils();
        iconUtils.loadCategoryIcon(testCategory, testPrintView);
    }

    @Test
    public void testGetToolbarIcon(){
        when(testIIcon.getTypeface()).thenReturn(new MaterialDesignIconic());
        when(testContext.getApplicationContext()).thenReturn(testApplicationContext);
        when(testApplicationContext.getResources()).thenReturn(testResources);
        when(testResources.getDisplayMetrics()).thenReturn(new DisplayMetrics());

        IconUtils iconUtils = new IconUtils();
        IconicsDrawable result = (IconicsDrawable) iconUtils.getToolbarIcon(testContext, testIIcon);

        assertEquals(testIIcon, result.getIcon());
        assertEquals(Color.WHITE, result.getColor());
    }

    @Test
    public void testGetToolbarIconWithPaddingDp(){
        int testDp = 15;

        when(testIIcon.getTypeface()).thenReturn(new MaterialDesignIconic());
        when(testContext.getApplicationContext()).thenReturn(testApplicationContext);
        when(testApplicationContext.getResources()).thenReturn(testResources);
        when(testResources.getDisplayMetrics()).thenReturn(new DisplayMetrics());

        IconUtils iconUtils = new IconUtils();
        IconicsDrawable result = (IconicsDrawable) iconUtils.getToolbarIcon(testContext, testIIcon, testDp);

        assertEquals(testIIcon, result.getIcon());
        assertEquals(Color.WHITE, result.getColor());
    }

    @Test
    public void testGetIconMaterialIcon(){
        int testColor = 3;
        int testSizeRes = 5;

        when(testIIcon.getTypeface()).thenReturn(new MaterialDesignIconic());
        when(testContext.getApplicationContext()).thenReturn(testApplicationContext);
        when(testApplicationContext.getResources()).thenReturn(testResources);
        when(testResources.getDisplayMetrics()).thenReturn(new DisplayMetrics());

        IconUtils iconUtils = new IconUtils();
        IconicsDrawable result = (IconicsDrawable) iconUtils.getIconMaterialIcon(testContext, testIIcon, testColor, testSizeRes);

        assertEquals(testIIcon, result.getIcon());
        assertEquals(testColor, result.getColor());
    }

    @Test
    public void testGetIconMaterialIconWithPaddingDp(){
        int testColor = 3;
        int testSizeRes = 5;
        int testPaddingDp = 10;

        when(testIIcon.getTypeface()).thenReturn(new MaterialDesignIconic());
        when(testContext.getApplicationContext()).thenReturn(testApplicationContext);
        when(testApplicationContext.getResources()).thenReturn(testResources);
        when(testResources.getDisplayMetrics()).thenReturn(new DisplayMetrics());

        IconUtils iconUtils = new IconUtils();
        IconicsDrawable result = (IconicsDrawable) iconUtils.getIconMaterialIcon(testContext, testIIcon, testColor, testSizeRes, testPaddingDp);

        assertEquals(testIIcon, result.getIcon());
        assertEquals(testColor, result.getColor());
    }

    @Test
    public void testGetCategoryIcon(){
        //TODO
        /*
        int testColor = 10;
        int testCodeResId = 3;
        int testSizeRes = 5;

        when(testIIcon.getTypeface()).thenReturn(new MaterialDesignIconic());
        when(testContext.getApplicationContext()).thenReturn(testApplicationContext);
        when(testContext.getResources()).thenReturn(testResources);
        when(testContext.getAssets()).then(new );
        when(testApplicationContext.getResources()).thenReturn(testResources);
        when(testResources.getInteger(new Integer(testCodeResId))).thenReturn(0);
        when(testResources.getDisplayMetrics()).thenReturn(new DisplayMetrics());

        IconUtils iconUtils = new IconUtils();

        PrintDrawable result = (PrintDrawable) iconUtils.getCategoryIcon(testContext, testCodeResId, testColor, testSizeRes);

        assertEquals(testColor, result.getIconColor());*/
        //assertEquals(testColor, result.getColor());
    }
}
