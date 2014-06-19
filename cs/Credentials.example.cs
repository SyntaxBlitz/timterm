using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace WindowsFormsApplication1
{
    class Credentials
    {
        public static System.Security.SecureString getPass()
        {
            System.Security.SecureString pass = new System.Security.SecureString();
            pass.AppendChar('p');
            pass.AppendChar('a');
            pass.AppendChar('s');
            pass.AppendChar('s');
            pass.AppendChar('w');
            pass.AppendChar('o');
            pass.AppendChar('r');
            pass.AppendChar('d');

            return pass;
        }
    }
}
