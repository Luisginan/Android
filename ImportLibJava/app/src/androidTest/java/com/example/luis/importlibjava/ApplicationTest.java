package com.example.luis.importlibjava;

import android.app.Application;
import android.test.ApplicationTestCase;

import Lib.Manager;
import Lib.People;

public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testGet()
    {
        Manager manager = new Manager();
        manager.save(new People("001","luis"));
    }
}