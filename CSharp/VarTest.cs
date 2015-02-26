using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DataTypes {

    class Program {

        static void Main(string[] args) {
            
            int x = 4;
            int y = 3;

            string c = "Hello";

            String superC = "Super";

            Console.WriteLine(String.Format("{2} {0} {1} {2} {3}", x, y, c, superC));

            Console.ReadLine();
        }
    }
}
