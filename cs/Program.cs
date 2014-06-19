using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.Runtime.InteropServices;
using System.Diagnostics;
using System.Net.Sockets;

namespace WindowsFormsApplication1
{
    static class Program
    {
        private const string    PATH_TO_JAR = "c:\\timterm\\tt.jar";
        private const int       PORT = 49151;

        private const int WH_KEYBOARD_LL = 13;
        private const int WM_KEYDOWN = 0x0100;
        private const int WM_KEYUP   = 0x0101;
        private static LowLevelKeyboardProc _proc = HookCallback;
        private static IntPtr _hookID = IntPtr.Zero;

        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            _hookID = SetHook(_proc);

            System.Security.SecureString pass = Credentials.getPass();

            Process.Start("javaw", "-jar c:\\timterm\\tt.jar", "Timothy", pass, Environment.UserDomainName);

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(/*new Form1()*/);

            UnhookWindowsHookEx(_hookID);
        }

        private static IntPtr SetHook(LowLevelKeyboardProc proc)
        {
            using (Process curProcess = Process.GetCurrentProcess())
            using (ProcessModule curModule = curProcess.MainModule)
            {
                return SetWindowsHookEx(WH_KEYBOARD_LL, proc,
                    GetModuleHandle(curModule.ModuleName), 0);
            }
        }

        private delegate IntPtr LowLevelKeyboardProc(
            int nCode, IntPtr wParam, IntPtr lParam);

        private static IntPtr HookCallback(
            int nCode, IntPtr wParam, IntPtr lParam)
        {
            if (nCode >= 0 && wParam == (IntPtr)WM_KEYUP)
            {
                int vkCode = Marshal.ReadInt32(lParam);
                if (vkCode == 92) {
                    SendKeys.Send("^{ESC}");

                    //System.Threading.Thread.Sleep(100);  //this delay helps ensure that the start menu is REALLY gone before running the application
                    //good thing the GUI thread isn't doing anything.
                    //I wonder why I put a GUI there in the first place.
                    //ghetto version control update 23 June 2013 (which also happens to be the day I made this project):
                    //  got rid of the gui by commenting out the bit that starts up the form.
                    //  I am a fucking genius
                    //ghetto version control update 24 June 2013 at like 4 in the morning
                    //  figured out that for whatever bollocks reason, if we use the keyup event instead, the start menu never shows up in the first place.
                    //  no sleep required.
                    //  awesome.
                    //  still have the gui commented out.
                    //ghetto version control update 27 June 2013 at 7pm right after returning from a road tip
                    //  just wanted to point out that I figured out why the start menu change worked.
                    //  don't feel the need to share it though.
                    //  peace

                    Socket s = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp); // I literally just copied this constructor from the msdn
                    s.Connect("localhost", PORT);

                }
            }
            return CallNextHookEx(_hookID, nCode, wParam, lParam);
        }

        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern IntPtr SetWindowsHookEx(int idHook,
            LowLevelKeyboardProc lpfn, IntPtr hMod, uint dwThreadId);

        [DllImport("user32.dll", CharSet = CharSet.Auto, ExactSpelling = true)]
        internal static extern short GetKeyState(int virtualKeyCode);

        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        [return: MarshalAs(UnmanagedType.Bool)]
        private static extern bool UnhookWindowsHookEx(IntPtr hhk);

        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern IntPtr CallNextHookEx(IntPtr hhk, int nCode,
            IntPtr wParam, IntPtr lParam);

        [DllImport("kernel32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern IntPtr GetModuleHandle(string lpModuleName);
    }
}
