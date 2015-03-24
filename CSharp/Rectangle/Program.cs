using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Rectangles
{
	class Program
	{
		static void Main(string[] args)
		{
			Rectangle rect0 = new Rectangle();
			Rectangle rect1 = new Rectangle();

			rect0.x0 = int.Parse(Console.ReadLine());
			rect0.x1 = int.Parse(Console.ReadLine());
			rect0.y0 = int.Parse(Console.ReadLine());
			rect0.y1 = int.Parse(Console.ReadLine());

			rect1.x0 = int.Parse(Console.ReadLine());
			rect1.x1 = int.Parse(Console.ReadLine());
			rect1.y0 = int.Parse(Console.ReadLine());
			rect1.y1 = int.Parse(Console.ReadLine());

			Console.WriteLine(rect0);
			Console.WriteLine(rect1);

			Console.WriteLine(Calculate.field(rect0));
			Console.WriteLine(Calculate.field(rect1));

			Console.WriteLine(Calculate.commonField(ref rect0,ref rect1));

			Console.ReadLine();
		}
	}
}
