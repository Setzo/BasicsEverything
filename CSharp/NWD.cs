using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1 {

	class Program {

		static void Main(string[] args) {

			Console.WriteLine("Podaj liczby");
			int x = int.Parse(Console.ReadLine());
			int y = int.Parse(Console.ReadLine());

			if (x != y)
			{
				while (x != y)
				{

					if (x < y)
					{
						y = y - x;
					}
					else
					{
						x = x - y;
					}
				}
				Console.WriteLine(x);
			}
			else
			{
				Console.WriteLine(x);
			}
			Console.ReadLine();
		}
	}
}
