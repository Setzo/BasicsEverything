using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace Checkers
{
	static class Program
	{
		/*
		 * Główna metoda programu.
		 */
		[STAThread]
		static void Main()
		{
			Application.EnableVisualStyles();
			Application.SetCompatibleTextRenderingDefault(false);
			Application.Run(new Form1());
		}
	}
}
