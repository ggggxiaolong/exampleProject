package com.mrtan.app;

import com.mrtan.common.base.BaseActivity;
import com.mrtan.app.ui.CategoryActivity;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test public void testInterface(){
        System.out.println(BaseActivity.class.getInterfaces().length);
        System.out.println(CategoryActivity.class.getGenericInterfaces().length);
    }
}