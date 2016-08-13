using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Consoleeee
{
	public class Niezbednik
	{
		public static int max(int a, int b)
		{
			return Math.Max(a, b);
		}

		public static void swap(ref int a, ref int b)
		{
			int tmp = a;
			a = b;
			b = tmp;
		}

		public static long factorialRecursive(int a)
		{
			if (a < 0)
			{
				return -1;
			}
			else if (a == 1 || a == 0)
			{
				return 1;
			}
			else
			{
				return a * factorialRecursive(a - 1);
			}
		}

		public static long factorialIterate(int a)
		{
			long num = 1;

			for (int i = a; i > 0; i--)
			{
				num *= i;
			}

			return num;
		}
	}
}
