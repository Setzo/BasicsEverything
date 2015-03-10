using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Consoleeee
{
	class Program
	{
		static void Main(string[] args)
		{

			int a = int.Parse(Console.ReadLine());
			int b = int.Parse(Console.ReadLine());

			Console.WriteLine("Max: " + Niezbednik.max(a, b));
			Console.WriteLine("Silnia: " + Niezbednik.factorialIterate(a));
			Console.WriteLine("Silnia Rekursja: " + Niezbednik.factorialRecursive(a));

			Console.ReadLine();
		}
	}
}
