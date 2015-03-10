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
			Console.WriteLine("Silnia (a): " + Niezbednik.factorialIterate(a));
			Console.WriteLine("Silnia (b): " + Niezbednik.factorialIterate(b));
			Console.WriteLine("Silnia Rekursja(a): " + Niezbednik.factorialRecursive(a));
			Console.WriteLine("Silnia Rekursja(b): " + Niezbednik.factorialRecursive(b));

			Console.WriteLine(a + " : " + b);
			Niezbednik.swap(ref a, ref b);
			Console.WriteLine(a + " : " + b);

			Console.ReadLine();
		}
	}
}
