using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Rectangles
{
	public class Rectangle
	{
		private static int cnt = 0;

		private int id;

		public int x0, x1;
		public int y0, y1;

		public Rectangle()
		{
			this.id = cnt;
			cnt++;
		}

		public Rectangle(int x0, int x1, int y0, int y1)
		{
			this.x0 = x0;
			this.x1 = x1;
			this.y0 = y0;
			this.y1 = y1;
			this.id = cnt;
			cnt++;
		}

		public override string ToString()
		{
			return String.Format("Rectagle #{4}: {0} {1} {2} {3}", x0, x1, y0, y1, id);
		}
	}
}
