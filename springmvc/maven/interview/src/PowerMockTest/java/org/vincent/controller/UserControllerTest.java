package org.vincent.controller;

import blog.csdn.net.FileHelper;
import blog.csdn.net.UserController;
import blog.csdn.net.UserDto;
import blog.csdn.net.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserController.class, FileHelper.class})
@PowerMockIgnore("javax.management.*")
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController uc;

    @Test
    public void testAddUser() throws Exception {
        UserDto ud = new UserDto();
        PowerMockito.when(userService.addUser(ud)).thenReturn(1);
        boolean result = uc.addUser(ud);
        Assert.assertEquals(result, true);
    }

    @Test
    public void testDelUser() throws Exception {
        int toDelete = 1;
        PowerMockito.when(userService.delUser(toDelete)).thenThrow(new Exception("mock exception"));
        boolean result = uc.delUser(toDelete);
        Assert.assertEquals(result, false);
    }

    @Test
    public void mockFileHelper() {
        PowerMockito.mockStatic(FileHelper.class);
        PowerMockito.when(FileHelper.getName("lucy")).thenReturn("lily");
        Assert.assertEquals(FileHelper.getName("lucy"), "lily");
    }

    /**
     * 这是最重要的
     */
    @Test
    public void testCallStaticMethod() {
        String name = "a2ha";
        String result = "hundan";

        PowerMockito.mockStatic(FileHelper.class);
        PowerMockito.when(FileHelper.getName(name)).thenReturn(result);

        String hunzhan = uc.callStaticMethod(name);
        Assert.assertEquals(hunzhan, result);
    }

    @Test
    public void testModUser() throws Exception {
        UserDto ud = new UserDto();
        int moded = 1;

        PowerMockito.when(userService.modUser(ud)).thenReturn(moded);

        UserController uc2 = PowerMockito.mock(UserController.class);

        // 给没有 setter 方法的 私有字段 赋值。
        Whitebox.setInternalState(uc2, "userService", userService);

        // 因为要测试的是 modUser() 方法，
        // 所以，当调用这个方法时，应该让它调用真实的方法，而非被mock掉的方法
        PowerMockito.when(uc2.modUser(ud)).thenCallRealMethod();

        // 在modUser()方法中会调用verifyMod()这个私有方法，所以，需要将mock掉
        PowerMockito.when(uc2, "verifyMod", moded).thenReturn(true);

        boolean result = uc2.modUser(ud);

        Assert.assertEquals(result, true);
    }

    @Test
    public void testModUser2() throws Exception {
        UserDto ud = new UserDto();
        int moded = 1;

        PowerMockito.when(userService.modUser(ud)).thenReturn(moded);

        // 对uc进行监视
        uc = PowerMockito.spy(uc);
        // 当uc的verifyMod被执行时，将被mock掉
        PowerMockito.when(uc, "verifyMod", moded).thenReturn(true);
        boolean result = uc.modUser(ud);

        Assert.assertEquals(result, true);
    }

    /**
     * 死有方法都能Mock 到
     */
    @Test
    public void testVerifyMod() throws Exception {
        // 获取Method对象，
        Method method = PowerMockito.method(UserController.class, "verifyMod", int.class);
        // 调用Method的invoke方法来执行
        boolean result = (boolean) method.invoke(uc, 1);
        Assert.assertEquals(result, true);
    }

    @Test
    public void testVerifyMod2() throws Exception {
        // 通过 Whitebox 来执行
        boolean result = Whitebox.invokeMethod(uc, "verifyMod", 1);
        Assert.assertEquals(result, true);
    }

    @Test
    public void testCountUser() throws Exception {
        UserDto ud = new UserDto();
        ud.setId(1);

        PowerMockito.whenNew(UserDto.class).withNoArguments().thenReturn(ud);

        int count = uc.countUser();

        Assert.assertEquals(count, 1);
    }
}
