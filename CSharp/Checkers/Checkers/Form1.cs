using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Checkers
{
	public partial class Form1 : Form
	{

		private Button[,] bTab;

		private Button last = null;

		private Calc cal;

		public Form1()
		{
			InitializeComponent();

			this.label1.Text = "";

			bTab = new Button[8, 8];

			List<Control> list = new List<Control>();

			GetAllControl(this, list);

			foreach (Control control in list)
			{
				if (control.GetType() == typeof(Button))
				{
					Button b = (Button)control;

					if (b.Name.StartsWith("button"))
					{
						int y = (int)char.GetNumericValue(b.Name[b.Name.Length - 1]);
						int x = (int)char.GetNumericValue(b.Name[b.Name.Length - 2]);

						bTab[x, y] = b;
					}
				}
			}

			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					bTab[i, j].Text = "";
				}
			}

			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					if (j < 3)
					{
						if ((j % 2 == 0 && i % 2 == 0) || (j % 2 != 0 && i % 2 != 0))
						{
							bTab[i, j].Text = "X";
						}
					}

					if (j > 4)
					{
						if ((j % 2 == 0 && i % 2 == 0) || (j % 2 != 0 && i % 2 != 0))
						{
							bTab[i, j].Text = "O";
						}
					}
				}
			}

			cal = new Calc(ref bTab, ref this.label1);
		}

		private void GetAllControl(Control c, List<Control> list)
		{
			foreach (Control control in c.Controls)
			{
				list.Add(control);

				if (control.GetType() == typeof(Panel))
					GetAllControl(control, list);
			}
		}

		private void loadGameToolStripMenuItem_Click(object sender, EventArgs e)
		{
		}

		private void button62_Click(object sender, EventArgs e)
		{
			Button btn = (Button)sender;
			this.label1.Text = "";
			cal.move(btn);
		}

		private void label1_Click(object sender, EventArgs e)
		{

		}
	}
}
