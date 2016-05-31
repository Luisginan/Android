package com.example.luis.exampleunittest;
import android.app.Application;
import android.test.ApplicationTestCase;

import Manager.*;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    MathManager mathManager = new MathManager();
    public ApplicationTest() {
        super(Application.class);
    }

    public void testTambah()
    {
        float result = mathManager.tambah(2, 2);
        assertEquals(result, 4f);
    }

    public void testKali()
    {
        float result = mathManager.kali(2, 2);
        assertEquals(result,4f);
    }

    public void testBagi()
    {
        float result = mathManager.bagi(2, 2);
        assertEquals(result,1f);
    }

    public void testKurang()
    {
        float result = mathManager.kurang(2, 2);
        assertEquals(result,0f);
    }

}