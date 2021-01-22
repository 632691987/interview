package org.vincent.controller;

import blog.csdn.net.Sample1;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Sample1.class})
public class Sample1Test {

    private Sample1 ns = new Sample1();

    public void testFunctrion1() {
        List<String> nodes = new ArrayList<>();
        nodes.add("a");
        nodes.add("b");

        ns = spy(ns);
        when(ns.getChildren(Matchers.anyInt())).thenReturn(nodes);

        List<String> result = new ArrayList<>();
        ns.getAllChildren(1, result);

        assertEquals(result.size(), 2);
    }

}
