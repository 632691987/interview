package org.vincent.controller;

import blog.csdn.net.DispatcherServlet;
import blog.csdn.net.FileHelper;
import blog.csdn.net.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;

import static org.powermock.api.mockito.PowerMockito.method;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserController.class, FileHelper.class})
@PowerMockIgnore("javax.management.*")
public class DispatcherServletTest {

    @Test
    public void testInit() throws Exception {
        DispatcherServlet ds = spy(new DispatcherServlet());

        // 用 method 方法提取 GenericServlet 里面的 init 方法
        // 用 suppress 方法去忽略掉
        suppress(method(GenericServlet.class, "init", ServletConfig.class));

        ds.init(mock(ServletConfig.class));
        // other to do ...
    }

}
