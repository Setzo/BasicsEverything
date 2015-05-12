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
	public class Calc
	{
		public const int ERROR = 100;

		private Label label;

		private Button[,] bTab;

		public Calc(ref Button[,] bTab, ref Label label)
		{
			this.label = label;
			this.bTab = bTab;
		}

		private Button last = null;

		private bool justMoved = false;
		private bool turn = true;
		private bool requireSwitching = true;

		private bool isX(Button button)
		{
			return button.Text.Equals("X");
		}

		private bool isO(Button button)
		{
			return button.Text.Equals("O");
		}

		private bool isE(Button button)
		{
			return button.Text.Equals("");
		}

		private int[] getCoordinates(Button b)
		{
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					if (b.Equals(bTab[i, j]))
					{
						int[] t = new int[2];
						t[0] = i;
						t[1] = j;
						return t;
					}
				}
			}

			return null;
		}

		public void move(Button btn)
		{
			
			if ((this.last != null && !this.justMoved && this.isX(this.last) && this.turn)
					|| (this.last != null && !this.justMoved && this.isO(this.last) && !this.turn))
			{

				if (!this.isE(this.last))
				{
					if (this.isMoveValid(btn))
					{
						btn.Text = this.last.Text;
						this.last.Text = "";
						this.justMoved = true;
						this.turn = this.requireSwitching ? !this.turn : this.turn;
					}
				}

				this.last = btn;
			}
			else
			{
				if (this.isX(btn) || this.isO(btn))
				{
					this.last = btn;
					this.justMoved = false;
				}
			}
		}

		private bool isCollideAffecting(bool isX)
		{
			if(isX)
			{
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						if (this.isX(this.bTab[i, j]))
						{
							if(this.isColliding(i, j)[0, 0] != Calc.ERROR)
							{
								return true;
							}
						}
					}
				}
			}

			else if(!isX)
			{
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						if (this.isO(this.bTab[i, j]))
						{
							if (this.isColliding(i, j)[0, 0] != Calc.ERROR)
							{
								return true;
							}
						}
					}
				}
			}

			return false;
		}

		private int[,] isColliding(int lx, int ly)
		{
			int[,] tab = new int[4, 2];

			for(int i = 0; i < 4; i++)
			{
				for(int j = 0; j < 2; j++)
				{
					tab[i, j] = Calc.ERROR;
				}
			}

			// this.label.Text = this.label.Text + String.Format("@ lx : {0} && ly : {1} @", lx, ly);

			int cnt = 0;

			if (this.isX(this.bTab[lx, ly]))
			{
				
				if (!(lx + 2 > 7) && !(ly + 2 > 7))
				{
					if (this.isO(this.bTab[lx + 1, ly + 1]) && this.isE(this.bTab[lx + 2, ly + 2]))
					{
						tab[cnt, 0] = lx + 2;
						tab[cnt, 1] = ly + 2;
						cnt++;
					}
				}

				if (!(lx + 2 > 7) && !(ly - 2 < 0))
				{
					if (this.isO(this.bTab[lx + 1, ly - 1]) && this.isE(this.bTab[lx + 2, ly - 2]))
					{
						tab[cnt, 0] = lx + 2;
						tab[cnt, 1] = ly - 2;
						cnt++;
					}
				}

				if (!(lx - 2 < 0) && !(ly + 2 > 7))
				{
					if (this.isO(this.bTab[lx - 1, ly + 1]) && this.isE(this.bTab[lx - 2, ly + 2]))
					{
						tab[cnt, 0] = lx - 2;
						tab[cnt, 1] = ly + 2;
						cnt++;
					}
				}

				if (!(lx - 2 < 0) && !(ly - 2 < 0))
				{
					if (this.isO(this.bTab[lx - 1, ly - 1]) && this.isE(this.bTab[lx - 2, ly - 2]))
					{
						tab[cnt, 0] = lx - 2;
						tab[cnt, 1] = ly - 2;
						cnt++;
					}
				}
			}
			else if (this.isO(this.bTab[lx, ly]))
			{

				if (!(lx + 2 > 7) && !(ly + 2 > 7))
				{
					if (this.isX(this.bTab[lx + 1, ly + 1]) && this.isE(this.bTab[lx + 2, ly + 2]))
					{
						tab[cnt, 0] = lx + 2;
						tab[cnt, 1] = ly + 2;
						cnt++;
					}
				}

				if (!(lx + 2 > 7) && !(ly - 2 < 0))
				{
					if (this.isX(this.bTab[lx + 1, ly - 1]) && this.isE(this.bTab[lx + 2, ly - 2]))
					{
						tab[cnt, 0] = lx + 2;
						tab[cnt, 1] = ly - 2;
						cnt++;
					}
				}

				if (!(lx - 2 < 0) && !(ly + 2 > 7))
				{
					if (this.isX(this.bTab[lx - 1, ly + 1]) && this.isE(this.bTab[lx - 2, ly + 2]))
					{
						tab[cnt, 0] = lx - 2;
						tab[cnt, 1] = ly + 2;
						cnt++;
					}
				}

				if (!(lx - 2 < 0) && !(ly - 2 < 0))
				{
					if (this.isX(this.bTab[lx - 1, ly - 1]) && this.isE(this.bTab[lx - 2, ly - 2]))
					{
						tab[cnt, 0] = lx - 2;
						tab[cnt, 1] = ly - 2;
						cnt++;
					}
				}
			}

			return tab;
		}

		private bool isMoveValid(Button btn)
		{
			int[] btnCords = this.getCoordinates(btn);
			int[] lstCords = this.getCoordinates(this.last);

			int x = btnCords[0];
			int y = btnCords[1];
			int lx = lstCords[0];
			int ly = lstCords[1];

			int[,] collide = this.isColliding(lx, ly);

			if (collide[0, 0] == Calc.ERROR)
			{
			
				if (this.isX(this.last))
				{
					if ((y - 1 == ly) && (x - 1 == lx || x + 1 == lx))
					{
						if (this.isE(this.bTab[x - 1 == lx ? lx + 1 : lx - 1, ly + 1]))
						{
							this.requireSwitching = true;
							return true;
						}
					}
				}
				else if (this.isO(this.last))
				{
					if ((y + 1 == ly) && (x - 1 == lx || x + 1 == lx))
					{
						if (this.isE(this.bTab[x - 1 == lx ? lx + 1 : lx - 1, ly - 1]))
						{
							this.requireSwitching = true;
							return true;
						}
					}
				}
			}
			else
			{

				for(int i = 0; i < 4; i++)
				{
					if (collide[i, 0] == Calc.ERROR)
					{
						break;
					}

					if(collide[i, 0] == x && collide[i, 1] == y)
					{
						this.bTab[(x + lx) / 2, (y + ly) / 2].Text = "";
						String tmp = this.bTab[lx, ly].Text;
						this.bTab[x, y].Text = this.bTab[lx, ly].Text;

						if(this.isColliding(x, y)[0, 0] != Calc.ERROR)
						{
							this.requireSwitching = false;

							this.bTab[x, y].Text = tmp;
							return true;
						}

						this.bTab[x, y].Text = tmp;
						this.requireSwitching = true;
						return true;
					}

				}
			}

			this.requireSwitching = true;
			return false;
		}
	}
}
