using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1 {

	class Program {

		static void Main(string[] args) {

			
			Console.WriteLine("Podaj liczby");

			
			Console.WriteLine("max: {0}", GetMax());
			Console.ReadLine();
		}

		static int GetMax()
		{
			int tmp = int.Parse(Console.ReadLine());
			int max = tmp;

			if (tmp > 0)
			{
				while (true)
				{
					int x = int.Parse(Console.ReadLine());
					if (x < 0) break;

					max = Math.Max(max, x);
					tmp = x;
				}
			}

			return max;
		}
	}
}
