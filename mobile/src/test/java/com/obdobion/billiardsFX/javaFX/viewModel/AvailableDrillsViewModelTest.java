package com.obdobion.billiardsFX.javaFX.viewModel;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.saxsys.mvvmfx.testingutils.jfxrunner.JfxRunner;
import de.saxsys.mvvmfx.testingutils.jfxrunner.TestInJfxThread;

@RunWith(JfxRunner.class)
public class AvailableDrillsViewModelTest
{
    static AvailableDrillsViewModel vm = new AvailableDrillsViewModel();

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {}

    @Test
    @TestInJfxThread
    public void filterButtonCommand()
    {
        Assert.assertTrue(vm.getFilterCommand().isExecutable());
        vm.getFilterCommand().execute();
        Assert.assertFalse(vm.getFilterCommand().isExecutable());
    }

    @Test
    @TestInJfxThread
    public void filterButtonSetup()
    {
        Assert.assertEquals("Filter", vm.getFilterButtonTextProperty().get());
    }

    @Test
    @TestInJfxThread
    public void sortButtonCommand()
    {
        Assert.assertTrue(vm.getSortCommand().isExecutable());
        vm.getSortCommand().execute();
        Assert.assertFalse(vm.getSortCommand().isExecutable());
    }

    @Test
    @TestInJfxThread
    public void sortButtonSetup()
    {
        Assert.assertEquals("Sort", vm.getSortButtonTextProperty().get());
    }
}
