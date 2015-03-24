using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Rectangles
{
	public class Calculate
	{
		public static int field(Rectangle r0)
		{
			return Math.Abs(Math.Max(r0.x0, r0.x1) - Math.Min(r0.x0, r0.x1))
				* Math.Abs(Math.Max(r0.y0, r0.y1) - Math.Min(r0.y0, r0.y1));
		}

		public static Rectangle commonField(Rectangle r0, Rectangle r1)
		{
			
		}
	}
}
