using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace Checkers
{
	public partial class Form1 : Form
	{

		private Button[,] bTab;

		private Calc cal;

		public Form1()
		{
			InitializeComponent();

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

			startup();
		}

		private void startup()
		{
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					this.bTab[i, j].Text = "";
					this.bTab[i, j].Font = new Font(this.bTab[i, j].Font.FontFamily, 30);
				}
			}

			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					if (j < 3)
					{
						if ((j % 2 == 0 && i % 2 != 0) || (j % 2 != 0 && i % 2 == 0))
						{
							this.bTab[i, j].Text = "X";
							//bTab[i, j].Font = new Font(bTab[i, j].Font.FontFamily, 14);
						}
					}

					if (j > 4)
					{
						if ((j % 2 != 0 && i % 2 == 0) || (j % 2 == 0 && i % 2 != 0))
						{
							this.bTab[i, j].Text = "O";
							//bTab[i, j].Font = new Font(bTab[i, j].Font.FontFamily, 14);
						}
					}
				}
			}

			cal = new Calc(ref this.bTab);
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
			FileStream myStream = null;
			OpenFileDialog theDialog = new OpenFileDialog();
			theDialog.Title = "Load Game";
			theDialog.Filter = "Text files (*.txt)|*.txt|All files|*.*";
			if (theDialog.ShowDialog() == DialogResult.OK)
			{
				try
				{
					if ((myStream = (FileStream)theDialog.OpenFile()) != null)
					{
						StreamReader sr = new StreamReader(myStream);

						for(int i = 0; i < 8; i++)
						{
							String c = sr.ReadLine();

							for(int j = 0; j < c.Length; j++)
							{
								int x = (int)char.GetNumericValue(c[j]);
								j++;
								j++;

								int y = (int)char.GetNumericValue(c[j]);
								j++;
								j++;

								String sign = "";

								if (!c[j + 1].Equals(' '))
								{
									sign = sign + c[j] + c[j + 1];
									j++;
								}
								else
								{
									sign = sign + c[j];
								}
								j++;

								if (sign.Equals("E"))
								{
									sign = "";
								}
								this.bTab[x, y].Text = sign;
							}
						}

						for (int i = 0; i < 8; i++)
						{
							for (int j = 0; j < 8; j++)
							{
								if (this.bTab[i, j].Text.Equals("XX") || this.bTab[i, j].Text.Equals("OO"))
								{
									bTab[i, j].Font = new Font(bTab[i, j].Font.FontFamily, 14);
								}
							}
						}

						sr.Close();
					}
				}
				catch (Exception ex)
				{
					MessageBox.Show("Error: Could not read file from disk.");
				}
			}
		}

		private void button62_Click(object sender, EventArgs e)
		{
			Button btn = (Button)sender;
			cal.move(btn);

			if(cal.win())
			{
				startup();
			}
		}

		private void newGameToolStripMenuItem_Click(object sender, EventArgs e)
		{
			startup();
		}

		private void exitToolStripMenuItem_Click(object sender, EventArgs e)
		{
			System.Windows.Forms.Application.Exit();
		}

		private void saveGameToolStripMenuItem_Click(object sender, EventArgs e)
		{
			FileStream myStream;

			SaveFileDialog saveFileDialog = new SaveFileDialog();
			saveFileDialog.Filter = "Text files (*.txt)|*.txt";
			saveFileDialog.Title = "Save Game";

			saveFileDialog.FilterIndex = 2;
			saveFileDialog.RestoreDirectory = true;

			if (saveFileDialog.ShowDialog() == DialogResult.OK)
			{
				if ((myStream = (FileStream)saveFileDialog.OpenFile()) != null)
				{
					StreamWriter sw = new StreamWriter(myStream);
					
					for(int i = 0; i < 8; i++)
					{
						for (int j = 0; j < 8; j++)
						{
							String c = "";
							c = c + i.ToString() + " " + j.ToString() + " ";

							if(this.bTab[i, j].Text.Equals(""))
							{
								c = c + "E ";
							}
							else
							{
								c = c + this.bTab[i, j].Text + " ";
							}

							sw.Write(c);
						}
						sw.Write("\n");
					}

					sw.Close();
				}
			}
		}

		private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
		{
			System.Diagnostics.Process.Start("http://www.kurnik.pl/warcaby/zasady.phtml");
		}
	}
}
