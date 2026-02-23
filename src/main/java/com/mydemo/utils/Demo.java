package com.mydemo.utils;


import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Win32VK;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.W32APIOptions;

import static com.sun.jna.platform.win32.WinUser.KEYBDINPUT.KEYEVENTF_KEYUP;


/**
 * 键盘按钮模拟
 *
 *
 * JNA 使用
 * <a href="https://juejin.cn/post/7506465696286900260">参考</a>
 *
 * 按键输入
 * <a href="https://www.cnblogs.com/yumingle/p/6776258.html">参考</a>
 * 其他按键
 * com.sun.jna.platform.win32.User32Util
 * com.sun.jna.platform.win32.Win32VK
 *
 * 输入不起作用，但是在其他应用（如idea）正常 -> 网上说游戏有检测机制 -> 用robot试试(NewDemo)
 *
 *
 */
public class Demo {

    public interface AutoProgram extends StdCallLibrary {
        // 接口名无所谓 主要是name参数是对应平台的 20250616
        // 接口名需需要查看User32.dll 20250618
        AutoProgram INSTANCE = (AutoProgram) Native.load("user32", AutoProgram.class);
        // 接口名可以参考 com/sun/jna/platform/win32/User32.java 20250619
        AutoProgram INSTANCE2 = (AutoProgram) Native.load("user32", AutoProgram.class, W32APIOptions.DEFAULT_OPTIONS);
        HWND FindWindow(String lpClassName, String lpWindowName);
        HWND FindWindowA(String lpClassName, String lpWindowName);

        HWND GetForegroundWindow();
        boolean GetWindowInfo(HWND hWnd, WinUser.WINDOWINFO pwi);
        int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);
        int GetWindowText(HWND hWnd, char[] lpString, int nMaxCount);

        // 遍历所有顶级窗口
        boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer data);
        // 按键或者组合输入
        WinDef.DWORD SendInput(WinDef.DWORD nInputs, WinUser.INPUT[] pInputs, int cbSize);
        boolean ShowWindow(HWND hWnd, int nCmdShow);
        boolean SetForegroundWindow(HWND hWnd);
        // 输入聚焦窗口
        HWND SetFocus(HWND hWnd);

        public HWND GetActiveWindow();


        public void SetCursorPos(int x, int y);




    }

    public static void main(String[] args) throws InterruptedException {
//        WinUser.WINDOWINFO pwi = new WinUser.WINDOWINFO();
//        HWND hwnd = AutoProgram.INSTANCE.GetForegroundWindow();
//        System.out.println(AutoProgram.INSTANCE.GetWindowInfo(hwnd, pwi));
//        byte[] lpString = new byte[512];
//        System.out.println(AutoProgram.INSTANCE.GetWindowTextA(hwnd, lpString, 512));
//        System.out.println(Native.toString(lpString));

//        HWND hwnd2 = AutoProgram.INSTANCE.GetForegroundWindow();
//        char[] lpString2 = new char[512];
//        System.out.println(AutoProgram.INSTANCE2.GetWindowText(hwnd2, lpString2, 512));
//        System.out.println(Native.toString(lpString2));


        autoIn();
//        HWND hwnd = AutoProgram.INSTANCE2.GetActiveWindow();
//        System.out.println(hwnd);
    }

    public static void autoIn() throws InterruptedException {
        String windowName = "地下城";
        HWND hwnd = findWindow(windowName);
        boolean a = AutoProgram.INSTANCE2.ShowWindow(hwnd, 9);
        System.out.println(a);
        Thread.sleep(2000);
        boolean b = AutoProgram.INSTANCE2.SetForegroundWindow(hwnd);
        System.out.println(b);
        Thread.sleep(2000);



        int i = 1;
        while (i <3) {
            autoInput(Win32VK.VK_RIGHT.code);
            System.out.println(Win32VK.VK_RIGHT.name());
            i++;
            Thread.sleep(50);
        }

    }

    public static HWND findWindow(String windowName) {
        final HWND[] hwnd2 = {null};
        AutoProgram.INSTANCE2.EnumWindows(new WinUser.WNDENUMPROC() {
            @Override
            public boolean callback(HWND hWnd, Pointer data) {
                char[] lpString = new char[512];
                AutoProgram.INSTANCE2.GetWindowText(hWnd, lpString, 512);
                String name = Native.toString(lpString);
//                System.out.println(name);
                if (name.contains(windowName)) {
                    hwnd2[0] = hWnd;
                    return false;
                }
                return true;
            }
        }, null);
        return hwnd2[0];
    }

    public static void autoInput(long code) throws InterruptedException {
        WinUser.INPUT input = new WinUser.INPUT();
        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_MOUSE);
        input.input.setType("ki");

        input.input.ki.wVk = new WinDef.WORD(code);
        input.input.ki.dwFlags = new WinDef.DWORD();// 按下 有异议 此处推理应该为按下/或释放的标志，但是直接用数字
        AutoProgram.INSTANCE2.SendInput(new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD), (WinUser.INPUT[]) input.toArray(1), input.size());

        Thread.sleep(150);

        input.input.ki.wVk = new WinDef.WORD(code);
        input.input.ki.dwFlags = new WinDef.DWORD( KEYEVENTF_KEYUP);//释放
        AutoProgram.INSTANCE2.SendInput(new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD), (WinUser.INPUT[]) input.toArray(1), input.size());
        Thread.sleep(150);
    }

    public static void autoMouseInput(long code) throws InterruptedException {
        WinUser.INPUT input = new WinUser.INPUT();
        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_MOUSE);
        input.input.setType("mi");

        input.input.ki.wVk = new WinDef.WORD(code);
        input.input.ki.dwFlags = new WinDef.DWORD();// 按下 有异议 此处推理应该为按下/或释放的标志，但是直接用数字
        AutoProgram.INSTANCE2.SendInput(new WinDef.DWORD(WinUser.INPUT.INPUT_MOUSE), (WinUser.INPUT[]) input.toArray(1), input.size());

        Thread.sleep(150);

        input.input.ki.wVk = new WinDef.WORD(code);
        input.input.ki.dwFlags = new WinDef.DWORD( KEYEVENTF_KEYUP);//释放
        AutoProgram.INSTANCE2.SendInput(new WinDef.DWORD(WinUser.INPUT.INPUT_MOUSE), (WinUser.INPUT[]) input.toArray(1), input.size());
        Thread.sleep(150);
    }

}
