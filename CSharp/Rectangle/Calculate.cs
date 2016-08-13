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

		public static int commonField(ref Rectangle r0, ref Rectangle r1)
		{
			return Math.Max(0, Math.Min(Math.Max(r0.x0, r0.x1), Math.Max(r1.x0, r1.x1)) - Math.Max(Math.Min(r0.x0, r0.x1), Math.Min(r1.x0, r1.x1)))
				* Math.Max(0, Math.Min(Math.Max(r0.y0, r0.y1), Math.Max(r1.y0, r1.y1)) - Math.Max(Math.Min(r0.y0, r0.y1), Math.Min(r1.y0, r1.y1)));
		}
	}
}
