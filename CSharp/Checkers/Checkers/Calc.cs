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
			this.last = this.bTab[0, 7];

			//this.bTab[0, 2].Font = new Font(bTab[0, 2].Font.FontFamily, 14);
			//this.bTab[0, 1].Text = "OO";

			//this.label.Text = bTab[0, 0].Font.Size.ToString();		// 30
			//this.bTab[4, 4].Text = "XX";
			//this.bTab[4, 4].Font = new Font(this.bTab[4, 4].Font.FontFamily, 14);
		}

		private Button last = null;

		private bool justMoved = false;
		private bool turn = true;
		private bool requireSwitching = true;
		private bool xCollide = false;
		private bool oCollide = false;

		private bool upperRight = false;
		private bool upperLeft = false;
		private bool bottomRight = false;
		private bool bottomLeft = false;

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

		private bool isXX(Button button)
		{
			return button.Text.Equals("XX");
		}

		private bool isOO(Button button)
		{
			return button.Text.Equals("OO");
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
			if ((this.last != null && !this.justMoved && (this.isX(this.last) || this.isXX(this.last)) && this.turn)
					|| (this.last != null && !this.justMoved && (this.isO(this.last) || this.isOO(this.last)) && !this.turn))
			{

				// this.label.Text = (this.isXX(this.last) || this.isOO(this.last)) ? "True" : "False";

				if (!this.isE(this.last))
				{
					if (this.isMoveValid(btn))
					{
						btn.Text = this.last.Text;
						Font fnt = new Font(btn.Font.FontFamily, btn.Font.Size);
						btn.Font = new Font(this.last.Font.FontFamily, this.last.Font.Size);
						this.last.Text = "";
						this.last.Font = fnt;
						this.justMoved = true;
						this.turn = this.requireSwitching ? !this.turn : this.turn;

						if(this.isQueen(btn))
						{
							if(this.isX(btn))
							{
								btn.Font = new Font(btn.Font.FontFamily, 14);
								btn.Text = "XX";
							}
							else
							{
								btn.Font = new Font(btn.Font.FontFamily, 14);
								btn.Text = "OO";
							}
						}
					}
				}

				this.last = btn;
			}
			else
			{
				if (this.isX(btn) || this.isO(btn) || this.isXX(btn) || this.isOO(btn))
				{
					this.last = btn;
					this.justMoved = false;
				}
			}
		}

		private bool isQueen(Button btn)
		{
			if(this.isX(btn))
			{
				if(this.getCoordinates(btn)[1] == 7)
				{
					return true;
				}
			}
			else if (this.isO(btn))
			{
				if (this.getCoordinates(btn)[1] == 0)
				{
					return true;
				}
			}

			return false;
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
						if (this.isXX(this.bTab[i, j]))
						{
							this.xCollide = false;

							this.upperLeftCorner(i, j, isX);
							this.upperRightCorner(i, j, isX);
							this.bottomLeftCorner(i, j, isX);
							this.bottomRightCorner(i, j, isX);

							if (this.xCollide)
							{
								this.xCollide = false;
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
						if (this.isOO(this.bTab[i, j]))
						{
							this.xCollide = false;
							this.oCollide = false;

							this.upperLeftCorner(i, j, isX);
							this.upperRightCorner(i, j, isX);
							this.bottomLeftCorner(i, j, isX);
							this.bottomRightCorner(i, j, isX);

							if (this.oCollide)
							{
								this.oCollide = false;
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
					if ((this.isO(this.bTab[lx + 1, ly + 1]) || this.isOO(this.bTab[lx + 1, ly + 1])) && this.isE(this.bTab[lx + 2, ly + 2]))
					{
						tab[cnt, 0] = lx + 2;
						tab[cnt, 1] = ly + 2;
						cnt++;
					}
				}

				if (!(lx + 2 > 7) && !(ly - 2 < 0))
				{
					if ((this.isO(this.bTab[lx + 1, ly - 1]) || this.isOO(this.bTab[lx + 1, ly - 1])) && this.isE(this.bTab[lx + 2, ly - 2]))
					{
						tab[cnt, 0] = lx + 2;
						tab[cnt, 1] = ly - 2;
						cnt++;
					}
				}

				if (!(lx - 2 < 0) && !(ly + 2 > 7))
				{
					if ((this.isO(this.bTab[lx - 1, ly + 1]) || this.isOO(this.bTab[lx - 1, ly + 1])) && this.isE(this.bTab[lx - 2, ly + 2]))
					{
						tab[cnt, 0] = lx - 2;
						tab[cnt, 1] = ly + 2;
						cnt++;
					}
				}

				if (!(lx - 2 < 0) && !(ly - 2 < 0))
				{
					if ((this.isO(this.bTab[lx - 1, ly - 1]) || this.isOO(this.bTab[lx - 1, ly - 1])) && this.isE(this.bTab[lx - 2, ly - 2]))
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
					if ((this.isX(this.bTab[lx + 1, ly + 1]) || this.isXX(this.bTab[lx + 1, ly + 1])) && this.isE(this.bTab[lx + 2, ly + 2]))
					{
						tab[cnt, 0] = lx + 2;
						tab[cnt, 1] = ly + 2;
						cnt++;
					}
				}

				if (!(lx + 2 > 7) && !(ly - 2 < 0))
				{
					if ((this.isX(this.bTab[lx + 1, ly - 1]) || this.isXX(this.bTab[lx + 1, ly - 1])) && this.isE(this.bTab[lx + 2, ly - 2]))
					{
						tab[cnt, 0] = lx + 2;
						tab[cnt, 1] = ly - 2;
						cnt++;
					}
				}

				if (!(lx - 2 < 0) && !(ly + 2 > 7))
				{
					if ((this.isX(this.bTab[lx - 1, ly + 1]) || this.isXX(this.bTab[lx - 1, ly + 1])) && this.isE(this.bTab[lx - 2, ly + 2]))
					{
						tab[cnt, 0] = lx - 2;
						tab[cnt, 1] = ly + 2;
						cnt++;
					}
				}

				if (!(lx - 2 < 0) && !(ly - 2 < 0))
				{
					if ((this.isX(this.bTab[lx - 1, ly - 1]) || this.isXX(this.bTab[lx - 1, ly - 1])) && this.isE(this.bTab[lx - 2, ly - 2]))
					{
						tab[cnt, 0] = lx - 2;
						tab[cnt, 1] = ly - 2;
						cnt++;
					}
				}
			}

			return tab;
		}

		private bool onSegment(int x, int y, int lx, int ly)
		{
			int cntX = 0;
			int cntY = 0;

			while(lx != x)
			{
				lx = lx < x ? lx + 1 : lx - 1;
				cntX++;
			}

			while(ly != y)
			{
				ly = ly < y ? ly + 1 : ly - 1;
				cntY++;
			}

			return cntX == cntY;
		}

		private void batchRemove(int x, int y, int lx, int ly)
		{
			while(lx != x && ly != y)
			{
				lx = lx < x ? lx + 1 : lx - 1;
				ly = ly < y ? ly + 1 : ly - 1;

				this.bTab[lx, ly].Text = "";
			}
		}

		private int[,] bottomRightCorner(int x, int y, bool isXX)
		{
			int[,] tab = new int[7,2];

			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 2; j++)
				{
					tab[i, j] = Calc.ERROR;
				}
			}

			bool obstacle = false;

			int cnt = 0;
			x++;
			y++;

			while (x <= 7 && y <= 7)
			{
				if (!this.isE(this.bTab[x, y]))
				{
					if (obstacle)
					{
						return tab;
					}

					if ((isXX && (this.isO(this.bTab[x, y]) || this.isOO(this.bTab[x, y])))
							|| (!isXX && (this.isX(this.bTab[x, y]) || this.isXX(this.bTab[x, y]))))
					{
						obstacle = true;
					}
					else
					{
						return tab;
					}

				}

				if (obstacle)
				{
					if(isXX)
					{
						this.xCollide = true;
					}
					else
					{
						this.oCollide = true;
					}
				}

				tab[cnt, 0] = x;
				tab[cnt, 1] = y;
				cnt++;

				x++;
				y++;
			}

			return tab;
		}

		private int[,] bottomLeftCorner(int x, int y, bool isXX)
		{
			int[,] tab = new int[7, 2];

			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 2; j++)
				{
					tab[i, j] = Calc.ERROR;
				}
			}

			bool obstacle = false;

			int cnt = 0;
			x--;
			y++;

			while (x >= 0 && y <= 7)
			{
				if (!this.isE(this.bTab[x, y]))
				{
					if (obstacle)
					{
						return tab;
					}

					if ((isXX && (this.isO(this.bTab[x, y]) || this.isOO(this.bTab[x, y])))
							|| (!isXX && (this.isX(this.bTab[x, y]) || this.isXX(this.bTab[x, y]))))
					{
						obstacle = true;
					}
					else
					{
						return tab;
					}

				}

				if (obstacle)
				{
					if (isXX)
					{
						this.xCollide = true;
					}
					else
					{
						this.oCollide = true;
					}
				}

				tab[cnt, 0] = x;
				tab[cnt, 1] = y;
				cnt++;

				x--;
				y++;
			}

			return tab;
		}

		private int[,] upperLeftCorner(int x, int y, bool isXX)
		{
			int[,] tab = new int[7, 2];

			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 2; j++)
				{
					tab[i, j] = Calc.ERROR;
				}
			}

			bool obstacle = false;

			int cnt = 0;
			x--;
			y--;

			while (x >= 0 && y >= 0)
			{
				if (!this.isE(this.bTab[x, y]))
				{
					if (obstacle)
					{
						return tab;
					}

					if ((isXX && (this.isO(this.bTab[x, y]) || this.isOO(this.bTab[x, y])))
							|| (!isXX && (this.isX(this.bTab[x, y]) || this.isXX(this.bTab[x, y]))))
					{
						obstacle = true;
					}
					else
					{
						return tab;
					}

				}

				if (obstacle)
				{
					if (isXX)
					{
						this.xCollide = true;
					}
					else
					{
						this.oCollide = true;
					}
				}

				tab[cnt, 0] = x;
				tab[cnt, 1] = y;
				cnt++;

				x--;
				y--;
			}

			return tab;
		}

		private int[,] upperRightCorner(int x, int y, bool isXX)
		{
			int[,] tab = new int[7, 2];

			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 2; j++)
				{
					tab[i, j] = Calc.ERROR;
				}
			}

			bool obstacle = false;

			int cnt = 0;
			x++;
			y--;

			while (x <= 7 && y >= 0)
			{
				if (!this.isE(this.bTab[x, y]))
				{
					if (obstacle)
					{
						return tab;
					}

					if ((isXX && (this.isO(this.bTab[x, y]) || this.isOO(this.bTab[x, y])))
							|| (!isXX && (this.isX(this.bTab[x, y]) || this.isXX(this.bTab[x, y]))))
					{
						obstacle = true;
					}
					else
					{
						return tab;
					}

				}

				if (obstacle)
				{
					if (isXX)
					{
						this.xCollide = true;
					}
					else
					{
						this.oCollide = true;
					}
				}

				tab[cnt, 0] = x;
				tab[cnt, 1] = y;
				cnt++;

				x++;
				y--;
			}

			return tab;
		}

		private bool isMoveValidQueenVersion(Button btn, int x, int y, int lx, int ly)
		{
			if(x == lx || y == ly)
			{
				return false;
			}

			if(this.onSegment(x, y, lx, ly) && !isCollideAffecting(this.isXX(this.last)))
			{

				int[,] toGo = new int [7, 2];

				if (x > lx && y > ly)
				{
					toGo = this.bottomRightCorner(lx, ly, this.isXX(this.last));
				}
				if (x > lx && y < ly)
				{
					toGo = this.upperRightCorner(lx, ly, this.isXX(this.last));
				}
				if (x < lx && y > ly)
				{
					toGo = this.bottomLeftCorner(lx, ly, this.isXX(this.last));
				}
				if (x < lx && y < ly)
				{
					toGo = this.upperLeftCorner(lx, ly, this.isXX(this.last));
				}

				for(int i = 0; i < 7; i++)
				{
					if(toGo[i, 0] == Calc.ERROR)
					{
						break;
					}

					if(toGo[i, 0] == x && toGo[i, 1] == y)
					{
						this.batchRemove(x, y, lx, ly);
						this.requireSwitching = false;
						return true;
					}
				}
			}

			else if (this.onSegment(x, y, lx, ly) && isCollideAffecting(this.isXX(this.last)))
			{
				//this.label.Text = "ss";
				
				int[,] toGo = new int[7, 2];

				if (x > lx && y > ly)
				{
					toGo = this.bottomRightCorner(lx, ly, this.isXX(this.last));
				}
				if (x > lx && y < ly)
				{
					toGo = this.upperRightCorner(lx, ly, this.isXX(this.last));
				}
				if (x < lx && y > ly)
				{
					toGo = this.bottomLeftCorner(lx, ly, this.isXX(this.last));
				}
				if (x < lx && y < ly)
				{
					toGo = this.upperLeftCorner(lx, ly, this.isXX(this.last));
				}

				bool after = false;

				for (int i = 1; i < 7; i++)
				{
					if (toGo[i, 0] == Calc.ERROR)
					{
						break;
					}

					if (Math.Abs(toGo[i, 0] - toGo[i - 1, 0]) != 1 || Math.Abs(toGo[i, 1] - toGo[i - 1, 1]) != 1)
					{
						after = true;
					}

					if (toGo[i, 0] == x && toGo[i, 1] == y && after)
					{
						this.batchRemove(x, y, lx, ly);
						this.requireSwitching = false;
						return true;
					}
				}
			}

			return false;
		}

		private bool isMoveValid(Button btn)
		{
			int[] btnCords = this.getCoordinates(btn);
			int[] lstCords = this.getCoordinates(this.last);

			int x = btnCords[0];
			int y = btnCords[1];
			int lx = lstCords[0];
			int ly = lstCords[1];

			if(this.isXX(this.last) || this.isOO(this.last))
			{
				// this.label.Text = "ssss";
				return isMoveValidQueenVersion(btn, x, y, lx, ly);
			}

			int[,] collide = this.isColliding(lx, ly);

			if (collide[0, 0] == Calc.ERROR && !this.isCollideAffecting(this.isX(this.last)))
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

			return false;
		}
	}
}
