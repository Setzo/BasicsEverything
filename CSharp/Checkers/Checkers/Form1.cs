﻿using System;
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

		private bool xCheat = false;
		private bool oCheat = false;

		private Calc cal;

		public Form1()
		{
			InitializeComponent();

			this.bTab = new Button[8, 8];

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

						this.bTab[x, y] = b;
					}
				}
			}

			this.label.Font = new Font(this.label.Font.FontFamily, 10);

			this.startup();
		}

		private void startup()
		{
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					this.bTab[i, j].Text = "";
					this.bTab[i, j].Font = new Font(this.bTab[i, j].Font.FontFamily, 100);
					this.bTab[i, j].Image = default(Image);
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
							if (!this.xCheat)
							{
								this.bTab[i, j].Text = "X";
								this.bTab[i, j].Image = Image.FromFile("C:\\Users\\Setzo\\Documents\\Visual Studio 2013\\Projects\\Checkers\\blackPawn.png");
								//this.bTab[i, j].Font = new Font(this.bTab[i, j].Font.FontFamily, 14);
							}
							else
							{
								this.bTab[i, j].Text = "XX";
								this.bTab[i, j].Image = Image.FromFile("C:\\Users\\Setzo\\Documents\\Visual Studio 2013\\Projects\\Checkers\\blackQueen.png");
							}
						}
					}

					if (j > 4)
					{
						if ((j % 2 != 0 && i % 2 == 0) || (j % 2 == 0 && i % 2 != 0))
						{
							if (!this.oCheat)
							{
								this.bTab[i, j].Text = "O";
								this.bTab[i, j].Image = Image.FromFile("C:\\Users\\Setzo\\Documents\\Visual Studio 2013\\Projects\\Checkers\\redPawn.png");
								//this.bTab[i, j].Font = new Font(this.bTab[i, j].Font.FontFamily, 14);
							}
							else
							{
								this.bTab[i, j].Text = "OO";
								this.bTab[i, j].Image = Image.FromFile("C:\\Users\\Setzo\\Documents\\Visual Studio 2013\\Projects\\Checkers\\redQueen.png");
							}
						}
					}
				}
			}

			cal = new Calc(ref this.bTab, ref this.label);
		}

		private void GetAllControl(Control c, List<Control> list)
		{
			foreach (Control control in c.Controls)
			{
				list.Add(control);

				if (control.GetType() == typeof(Panel)) {
					GetAllControl(control, list);
				}
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

						String cx = sr.ReadLine();
						
						if(cx[0].ToString().Equals("t"))
						{
							this.cal.setTurn(true);
						}

						else
						{
							this.cal.setTurn(false);
						}

						for (int i = 0; i < 8; i++)
						{
							for (int j = 0; j < 8; j++)
							{
								if (this.bTab[i, j].Text.Equals("XX") || this.bTab[i, j].Text.Equals("OO"))
								{
									this.bTab[i, j].Font = new Font(this.bTab[i, j].Font.FontFamily, 14);
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
			this.cal.move((Button)sender);
			this.cal.updateLabel();

			if(this.cal.win())
			{
				this.startup();
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

					if (cal.getTurn())
					{
						sw.Write("t");
					}
					else
					{
						sw.Write("f");
					}

					sw.Close();
				}
			}
		}

		private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
		{
			System.Diagnostics.Process.Start("http://www.kurnik.pl/warcaby/zasady.phtml");
		}

		private void authorsToolStripMenuItem_Click(object sender, EventArgs e)
		{
			MessageBox.Show("Created by :");
		}

		private void bluePlayerCheatToolStripMenuItem_Click(object sender, EventArgs e)
		{
			this.oCheat = !this.oCheat;
			this.startup();
		}

		private void blackPlayerCheatToolStripMenuItem_Click(object sender, EventArgs e)
		{
			this.xCheat = !this.xCheat;
			this.startup();
		}
	}
}
