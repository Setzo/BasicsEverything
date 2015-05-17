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
	/*
	 * Klasa odpowiadająca za wszystkie możliwe akcje
	 * związane z samą grą i planszą.
	 */
	public class Board
	{
		/*
		 * Stała definiująca, czy dany pionek (lub damka)
		 * musi bić. Dokładniejsze wyjaśnienie w okolicach
		 * funkcji isColliding() i isCollidingQueenVersion().
		 */
		private const int ERROR = 100;

		/*
		 * Stałe reprezentujące 4 strony, na jakie można się poruszać
		 * w warcabach.
		 * 
		 * BTT_R - Bottom Right - Dolny Prawy
		 * BTT_L - Bottom Left  - Dolny Lewy
		 * UPP_R - Upper Right  - Górny Prawy
		 * UPP_L - Upper Left   - Górny Lewy
		 */
		private const int BTT_R = 0;
		private const int BTT_L = 1;
		private const int UPP_R = 2;
		private const int UPP_L = 3;

		/*
		 * Stała zainicjalizowana w konstruktorze, definiująca
		 * wielkość planszy. Przy czym z góry zakładam że plansza
		 * jest kwadratowa, więc stała ta reprezentuje zarówno
		 * długość jak i wysokość planszy.
		 */
		private readonly int boardSize;

		/*
		 * Przycik naciśnięty przed obecnie naciśnietym przyciskiem.
		 */
		private Button last;

		/*
		 * Etykieta pokazująca turę aktualnego gracza.
		 */
		private Label label;

		/*
		 * Tablica zawierająca współrzędne podświetlonych guzików.
		 * 
		 * 0 - brak podświetlenia
		 * 1 - podświetlony
		 * 
		 * np. jeżeli this.highlighted[4, 5] == 1, to
		 * guzik na pozycji [4, 5] jest podświetlony.
		 */
		private int[,] highlighted;

		/*
		 * Zmienna pokazująca, czy ostatni ruch gracza był udany.
		 */
		private bool justMoved = false;

		/*
		 * Zmienna pokazująca który gracz ma aktualnie turę.
		 */
		private bool turn = false;

		/*
		 * Zmienna definiująca czy trzeba zmieniać turę gracza
		 * po skończeniu ruchu (jeżeli gracz ma podwójne bicie,
		 * to ma 2 tury).
		 */
		private bool requireSwitching = true;

		/*
		 * Nasza plansza.
		 */
		private Button[,] bTab;

		/*
		 * Konstruktor ustawiający referencje do naszej planszy i etykiety.
		 * Resetuje on również wszystkie stare podświetlenia i aktualizuje
		 * etykietę z aktualną turą gracza.
		 */
		public Board(ref Button[,] bTab, ref Label label)
		{
			this.bTab = bTab;
			this.label = label;
			this.last = this.bTab[0, 7];

			this.boardSize = this.bTab.GetLength(0);

			this.highlighted = new int[8, 8];

			for (int i = 0; i < this.boardSize; i++)
			{
				for (int j = 0; j < this.boardSize; j++)
				{
					this.highlighted[i, j] = 0;

					if ((j % 2 == 0 && i % 2 != 0) || (j % 2 != 0 && i % 2 == 0))
					{
						this.bTab[i, j].BackColor = Color.Silver;
					}
					else
					{
						this.bTab[i, j].BackColor = default(Color);
					}
				}
			}

			this.updateLabel();
			//this.bTab[0, 2].Font = new Font(bTab[0, 2].Font.FontFamily, 14);
			//this.bTab[0, 1].Text = "OO";
			//this.bTab[0, 1].Image = default(Image);
			//this.label.Text = bTab[0, 0].Font.Size.ToString();		// 30
			//this.bTab[4, 4].Text = "XX";
			//this.bTab[4, 4].Font = new Font(this.bTab[4, 4].Font.FontFamily, 14);
		}


		/********************************************************
		 * Jeżeli jakiekolwiek pole na planszy ma tekst, to		*
		 * jest to pionek albo damka jednego z graczy.			*
		 *														*
		 *	Objaśnienia :										*
		 *														*
		 *		X	-	Pionek czarnego gracza					*
		 *		XX	-	Damka czarnego gracza					*
		 *														*
		 *		O	-	Pionek czerwonego gracza				*
		 *		OO	-	Damka czerwonego gracza					*
		 *														*
		 *		brak tekstu oznacza, że pole jest puste.		*
		 *														*
		 ********************************************************/


		/*
		 * Metoda zwracająca true, gdy dany jej
		 * guzik jest pionkiem czarnego gracza.
		 */
		private bool isX(Button button)
		{
			return button.Text.Equals("X");
		}

		/*
		 * Metoda zwracająca true, gdy dany jej
		 * guzik jest pionkiem czerwonego gracza.
		 */
		private bool isO(Button button)
		{
			return button.Text.Equals("O");
		}

		/*
		 * Metoda zwracająca true, gdy dany jej
		 * guzik jest pustym polem.
		 */
		private bool isE(Button button)
		{
			return button.Text.Equals("");
		}

		/*
		 * Metoda zwracająca true, gdy dany jej
		 * guzik jest damką czarnego gracza.
		 */
		private bool isXX(Button button)
		{
			return button.Text.Equals("XX");
		}

		/*
		 * Metoda zwracająca true, gdy dany jej
		 * guzik jest damką czerwonego gracza.
		 */
		private bool isOO(Button button)
		{
			return button.Text.Equals("OO");
		}

		/*
		 * Metoda zwracająca true, gdy dany jej
		 * guzik jest pionkiem lub damką czerwonego gracza.
		 */
		private bool isOSide(Button button)
		{
			return this.isO(button) || this.isOO(button);
		}

		/*
		 * Metoda zwracająca true, gdy dany jej
		 * guzik jest pionkiem lub damką czarnego gracza.
		 */
		private bool isXSide(Button button)
		{
			return this.isX(button) || this.isXX(button);
		}

		/*
		 * Metoda zwracająca true, gdy dany jej
		 * guzik jest pionkiem lub damką czarnego lub czerwonego gracza.
		 */
		private bool isP(Button button)
		{
			return this.isXSide(button) || this.isOSide(button);
		}

		/*
		 * Metoda zwracająca wartość zmiennej instancyjnej turn.
		 */
		public bool getTurn()
		{
			return this.turn;
		}

		/*
		 * Metoda ustawiająca wartość zmiennej instancyjnej turn.
		 */
		public void setTurn(bool turn)
		{
			this.turn = turn;
		}

		/*
		 * Metoda zwracająca współrzędne podanego jej guzika
		 * na planszy w postaci tablicy jenowymiarowej o dwóch elementach.
		 * 
		 * element zwrócony z indeksem 0 - współrzędna X.
		 * element zwrócony z indeksem 1 - współrzędna Y.
		 */
		private int[] getCoordinates(Button b)
		{
			for (int i = 0; i < this.boardSize; i++)
			{
				for (int j = 0; j < this.boardSize; j++)
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

		/*
		 * Główna metoda logiczna tej klasy, jeżeli podany jej ruch
		 * jest możliwy, to go wykona. Jeżeli nie - nic nie zrobi.
		 */
		public void move(Button btn)
		{
			/*
			 * Ostatnio kliknięty guzik = [this.last] <---> Aktualnie kliknięty guzik = [btn]
			 * 
			 * Jeżeli ostatnio kliknięty guzik nie jest pusty
			 * i ostatnie wywołanie tej metody się nie powiodło (nie doszło do ruchu)
			 * i	
			 *(		jest tura gracza X i ostatnio kliknięty guzik należał do gracza X
			 * lub	jest tura gracza O i ostatnio kliknięty guzik należał do gracza O)
			 */
			if ((this.last != null && !this.justMoved && this.isXSide(this.last) && this.turn)
					|| (this.last != null && !this.justMoved && this.isOSide(this.last) && !this.turn))
			{
				if (!this.isE(this.last))
				{
					/* 
					 * Sprawdzanie czy ruch z guzika [this.last] do [btn]
					 * jest prawidłowy. Jeżeli tak, to wykonaj ruch.
					 */
					if (this.isMoveValid(btn))
					{
						btn.Text = this.last.Text;
						btn.Image = this.last.Image;
						//Font fnt = new Font(btn.Font.FontFamily, btn.Font.Size);
						//btn.Font = new Font(this.last.Font.FontFamily, this.last.Font.Size);
						this.last.Text = "";
						//this.last.Font = fnt;
						this.last.Image = default(Image);
						this.justMoved = true;
						this.turn = this.requireSwitching ? !this.turn : this.turn;

						if (this.isQueen(btn))
						{
							if (this.isX(btn))
							{
								//btn.Font = new Font(btn.Font.FontFamily, 14);
								btn.Text = "XX";
								btn.Image = Image.FromFile(Form1.BLACK_QUEEN);
							}
							else
							{
								//btn.Font = new Font(btn.Font.FontFamily, 14);
								btn.Text = "OO";
								btn.Image = Image.FromFile(Form1.RED_QUEEN);
							}
						}
					}
				}

				this.last = btn;
				//this.highlight();
			}
			else
			{
				if (this.isP(btn))
				{
					this.last = btn;
					this.justMoved = false;
				}
				//this.highlight();
			}
		}

		/*
		 * Metoda aktualizująca stan etykiety z turą aktualnego gracza.
		 */
		public void updateLabel()
		{
			if (this.turn)
			{
				this.label.Text = "Black Player turn.";
			}
			else
			{
				this.label.Text = "Red Player turn.";
			}
		}

		/*
		 * Metoda podświetlająca pola z pionkami / damkami aktualnego
		 * gracza, które mają bicie i muszą je wykonać.
		 */
		public void highlight()
		{
			for (int i = 0; i < this.boardSize; i++)
			{
				for (int j = 0; j < this.boardSize; j++)
				{
					if(this.turn)
					{
						if (this.isX(this.bTab[i, j]))
						{
							if (this.isColliding(i, j)[0, 0] != Board.ERROR)
							{
								this.bTab[i, j].BackColor = Color.Coral;
								this.highlighted[i, j] = 1;
								continue;
							}
						}

						else if(this.isXX(this.bTab[i, j]))
						{
							int[, ,] collide = this.isCollidingQueenVersion(i, j);

							if (collide[Board.BTT_R, 0, 0] != Board.ERROR
								|| collide[Board.BTT_L, 0, 0] != Board.ERROR
								|| collide[Board.UPP_R, 0, 0] != Board.ERROR
								|| collide[Board.UPP_L, 0, 0] != Board.ERROR)
							{
								this.bTab[i, j].BackColor = Color.Coral;
								this.highlighted[i, j] = 1;
								continue;
							}
						}

						if (this.highlighted[i, j] == 1)
						{
							if ((j % 2 == 0 && i % 2 != 0) || (j % 2 != 0 && i % 2 == 0))
							{
								this.bTab[i, j].BackColor = Color.Silver;
							}
							else
							{
								this.bTab[i, j].BackColor = default(Color);
							}
							this.highlighted[i, j] = 0;
						}
					}
					else
					{
						if (this.isO(this.bTab[i, j]))
						{
							if (this.isColliding(i, j)[0, 0] != Board.ERROR)
							{
								this.bTab[i, j].BackColor = Color.Coral;
								this.highlighted[i, j] = 1;
								continue;
							}
						}

						else if (this.isOO(this.bTab[i, j]))
						{
							int[, ,] collide = this.isCollidingQueenVersion(i, j);

							if (collide[Board.BTT_R, 0, 0] != Board.ERROR
								|| collide[Board.BTT_L, 0, 0] != Board.ERROR
								|| collide[Board.UPP_R, 0, 0] != Board.ERROR
								|| collide[Board.UPP_L, 0, 0] != Board.ERROR)
							{
								this.bTab[i, j].BackColor = Color.Coral;
								this.highlighted[i, j] = 1;
								continue;
							}
						}

						if (this.highlighted[i, j] == 1)
						{
							if ((j % 2 == 0 && i % 2 != 0) || (j % 2 != 0 && i % 2 == 0))
							{
								this.bTab[i, j].BackColor = Color.Silver;
							}
							else
							{
								this.bTab[i, j].BackColor = default(Color);
							}
							this.highlighted[i, j] = 0;
						}
					}
				}
			}

			return;
		}

		/*
		 * Metoda zwracająca true, gdy gra się zakończyła.
		 * Poza tym metoda wyświetli komunikat o zwycięskim graczu
		 * w racie zakończenia gry.
		 */
		public bool win()
		{
			bool xAlive = false;
			bool oAlive = false;

			int cntX = 0;
			int cntO = 0;

			bool isXBlocked = this.isBlocked(true);
			bool isOBlocked = this.isBlocked(false);

			for (int i = 0; i < this.boardSize; i++)
			{
				for (int j = 0; j < this.boardSize; j++)
				{
					if (this.isXSide(this.bTab[i, j]))
					{
						xAlive = true;
						cntX++;
					}

					if (this.isOSide(this.bTab[i, j]))
					{
						oAlive = true;
						cntO++;
					}
				}
			}

			if (!xAlive && !oAlive)
			{
				MessageBox.Show("Draw.");
				return true;
			}

			if (!xAlive)
			{
				MessageBox.Show("Red Player wins!!!");
				return true;
			}

			if (!oAlive)
			{
				MessageBox.Show("Black Player wins!!!");
				return true;
			}

			if (isXBlocked && isOBlocked)
			{
				if (cntX == cntO)
				{
					MessageBox.Show("Draw.");
				}
				else if (cntX > cntO)
				{
					MessageBox.Show("Both players are blocked, black player wins\ncause of higher quantity of pawns & queens.");
				}
				else
				{
					MessageBox.Show("Both players are blocked, red player wins\ncause of higher quantity of pawns & queens.");
				}
				return true;
			}

			if (isXBlocked)
			{
				MessageBox.Show("Red Player wins, black player is blocked!!!");
				return true;
			}

			if (isOBlocked)
			{
				MessageBox.Show("Black Player wins, red player is blocked!!!");
				return true;
			}

			return false;
		}

		/****************************************
		 *										*
		 *		Gracz X - gracz czarny			*
		 *										*
		 *		Gracz O - gracz czerwony		*
		 *										*
		 ****************************************/

		/*
		 * Metoda zwracająca true, gdy dany gracz jest zablokoany
		 * i nie może się ruszyć.
		 * 
		 * [isX] = true		-> Sprawdź czy gracz X jest zablokowany.
		 * [isX] = false	-> Sprawdź czy gracz O jest zablokowany.
		 */
		private bool isBlocked(bool isX)
		{
			bool blocked = false;

			if (isX)
			{
				for (int i = 0; i < this.boardSize; i++)
				{
					for (int j = 0; j < this.boardSize; j++)
					{
						if (this.isX(this.bTab[i, j]))
						{
							if ((i == 0 && this.isOSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i + 2, j + 2 > this.boardSize - 1 ? j : j + 2]))
									|| (i == 1 && this.isOSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i + 2, j + 2 > this.boardSize - 1 ? j : j + 2]) && this.isOSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1])))
							{
								blocked = true;
							}

							else if ((i == this.boardSize - 1 && this.isOSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i - 2, j + 2 > this.boardSize - 1 ? j : j + 2]))
									|| (i == this.boardSize - 2 && this.isOSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i - 2, j + 2 > this.boardSize - 1 ? j : j + 2]) && this.isOSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1])))
							{
								blocked = true;
							}

							else if (i >= 2 && i <= this.boardSize - 3 && this.isOSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1]) && this.isOSide(this.bTab[i - 2, j - 2 < 0 ? j : j - 2]) && this.isOSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i + 2, j + 2 > this.boardSize - 1 ? j : j + 2]))
							{
								blocked = true;
							}
							else
							{
								return false;
							}
						}

						if (this.isXX(this.bTab[i, j]))
						{
							bool upperBlock = false;
							bool bottomBlock = false;

							if ((i == 0 && this.isOSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i + 2, j + 2 > this.boardSize - 1 ? j : j + 2]))
									|| (i == 1 && this.isOSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i + 2, j + 2 > this.boardSize - 1 ? j : j + 2]) && this.isOSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1])))
							{
								upperBlock = true;
							}

							else if ((i == this.boardSize - 1 && this.isOSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i - 2, j + 2 > this.boardSize - 1 ? j : j + 2]))
									|| (i == this.boardSize - 2 && this.isOSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i - 2, j + 2 > this.boardSize - 1 ? j : j + 2]) && this.isOSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1])))
							{
								upperBlock = true;
							}

							else if (i >= 2 && i <= this.boardSize - 3 && this.isOSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i - 2, j + 2 > this.boardSize - 1 ? j : j + 2]) && this.isOSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isOSide(this.bTab[i + 2, j + 2 > this.boardSize - 1 ? j : j + 2]))
							{
								upperBlock = true;
							}

							if ((i == 0 && this.isOSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1]) && this.isOSide(this.bTab[i + 2, j - 2 < 0 ? j : j - 2]))
									|| (i == 1 && this.isOSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1]) && this.isOSide(this.bTab[i + 2, j - 2 < 0 ? j : j - 2]) && this.isOSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1])))
							{
								bottomBlock = true;
							}

							else if ((i == this.boardSize - 1 && this.isOSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1]) && this.isOSide(this.bTab[i - 2, j - 2 < 0 ? j : j - 2]))
									|| (i == this.boardSize - 2 && this.isOSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1]) && this.isOSide(this.bTab[i - 2, j - 2 < 0 ? j : j - 2]) && this.isOSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1])))
							{
								bottomBlock = true;
							}

							else if (i >= 2 && i <= this.boardSize - 3 && this.isOSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1]) && this.isOSide(this.bTab[i - 2, j - 1 < 0 ? j : j - 1]) && this.isOSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1]) && this.isOSide(this.bTab[i + 2, j - 2 < 0 ? j : j - 2]))
							{
								bottomBlock = true;
							}

							if (upperBlock && bottomBlock)
							{
								blocked = true;
							}
							else
							{
								return false;
							}
						}
					}
				}
			}
			else
			{
				for (int i = 0; i < this.boardSize; i++)
				{
					for (int j = 0; j < this.boardSize; j++)
					{
						if (this.isO(this.bTab[i, j]))
						{
							if ((i == 0 && this.isXSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i + 2, j - 2 < 0 ? j : j - 2]))
									|| (i == 1 && this.isXSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i + 2, j - 2 < 0 ? j : j - 2]) && this.isXSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1])))
							{
								blocked = true;
							}

							else if ((i == this.boardSize - 1 && this.isXSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i - 2, j - 2 < 0 ? j : j - 2]))
									|| (i == this.boardSize - 2 && this.isXSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i - 2, j - 2 < 0 ? j : j - 2]) && this.isXSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1])))
							{
								blocked = true;
							}

							else if (i >= 2 && i <= this.boardSize - 3 && this.isXSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i - 2, j - 2 < 0 ? j : j - 2]) && this.isXSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i + 2, j - 2 < 0 ? j : j - 2]))
							{
								blocked = true;
							}
							else
							{
								return false;
							}
						}

						if (this.isOO(this.bTab[i, j]))
						{
							bool upperBlock = false;
							bool bottomBlock = false;

							if ((i == 0 && this.isXSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isXSide(this.bTab[i + 2, j + 2 > this.boardSize - 1 ? j : j + 2]))
									|| (i == 1 && this.isXSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isXSide(this.bTab[i + 2, j + 2 > this.boardSize - 1 ? j : j + 2]) && this.isXSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1])))
							{
								upperBlock = true;
							}

							else if ((i == this.boardSize - 1 && this.isXSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isXSide(this.bTab[i - 2, j + 2 > this.boardSize - 1 ? j : j + 2]))
									|| (i == this.boardSize - 2 && this.isXSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isXSide(this.bTab[i - 2, j + 2 > this.boardSize - 1 ? j : j + 2]) && this.isXSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1])))
							{
								upperBlock = true;
							}

							else if (i >= 2 && i <= this.boardSize - 3 && this.isXSide(this.bTab[i - 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isXSide(this.bTab[i - 2, j + 2 > this.boardSize - 1 ? j : j + 2]) && this.isXSide(this.bTab[i + 1, j + 1 > this.boardSize - 1 ? j : j + 1]) && this.isXSide(this.bTab[i + 2, j + 2 > this.boardSize - 1 ? j : j + 2]))
							{
								upperBlock = true;
							}

							if ((i == 0 && this.isXSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i + 2, j - 2 < 0 ? j : j - 2]))
									|| (i == 1 && this.isXSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i + 2, j - 2 < 0 ? j : j - 2]) && this.isXSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1])))
							{
								bottomBlock = true;
							}

							else if ((i == this.boardSize - 1 && this.isXSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i - 2, j - 2 < 0 ? j : j - 2]))
									|| (i == this.boardSize - 2 && this.isXSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i - 2, j - 2 < 0 ? j : j - 2]) && this.isXSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1])))
							{
								bottomBlock = true;
							}

							else if (i >= 2 && i <= this.boardSize - 3 && this.isXSide(this.bTab[i - 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i - 2, j - 2 < 0 ? j : j - 2]) && this.isXSide(this.bTab[i + 1, j - 1 < 0 ? j : j - 1]) && this.isXSide(this.bTab[i + 2, j - 2 < 0 ? j : j - 2]))
							{
								bottomBlock = true;
							}

							if (upperBlock && bottomBlock)
							{
								blocked = true;
							}
							else
							{
								return false;
							}
						}
					}
				}
			}

			return blocked;
		}

		/*
		 * Metoda zwracająca true, gdy podany jej guzik
		 * jest pionkiem, a powinien być damką (jeżeli pionek
		 * doszedł na sam koniec planszy).
		 */
		private bool isQueen(Button btn)
		{
			if (this.isX(btn))
			{
				if (this.getCoordinates(btn)[1] == this.boardSize - 1)
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

		/*
		 * Metoda zwracająca true, gdy dany gracz ma bicie.
		 * 
		 * [isX] = true		-> Sprawdź czy gracz X ma bicie.
		 * [isX] = false	-> Sprawdź czy gracz O ma bicie.
		 * 
		 * Więcej o działaniu tej metody w metodach:
		 * 
		 *	isCollidingQueenVersion();
		 *	isColliding();
		 */
		private bool isCollideAffecting(bool isX)
		{
			if (isX)
			{
				for (int i = 0; i < this.boardSize; i++)
				{
					for (int j = 0; j < this.boardSize; j++)
					{
						if (this.isX(this.bTab[i, j]))
						{
							if (this.isColliding(i, j)[0, 0] != Board.ERROR)
							{
								return true;
							}
						}

						if (this.isXX(this.bTab[i, j]))
						{
							int[, ,] collide = this.isCollidingQueenVersion(i, j);

							if (collide[Board.BTT_R, 0, 0] != Board.ERROR
								|| collide[Board.BTT_L, 0, 0] != Board.ERROR
								|| collide[Board.UPP_R, 0, 0] != Board.ERROR
								|| collide[Board.UPP_L, 0, 0] != Board.ERROR)
							{
								return true;
							}
						}


					}
				}
			}

			else
			{
				for (int i = 0; i < this.boardSize; i++)
				{
					for (int j = 0; j < this.boardSize; j++)
					{
						if (this.isO(this.bTab[i, j]))
						{
							if (this.isColliding(i, j)[0, 0] != Board.ERROR)
							{
								return true;
							}
						}
						if (this.isOO(this.bTab[i, j]))
						{
							int[, ,] collide = this.isCollidingQueenVersion(i, j);

							if (collide[Board.BTT_R, 0, 0] != Board.ERROR
								|| collide[Board.BTT_L, 0, 0] != Board.ERROR
								|| collide[Board.UPP_R, 0, 0] != Board.ERROR
								|| collide[Board.UPP_L, 0, 0] != Board.ERROR)
							{
								return true;
							}
						}
					}
				}
			}

			return false;
		}

		/*
		 * Metoda zwracająca trójwymiarową tablice współrzędnych,
		 * na które damka stojąca w punkcie [x, y] może ruszyć
		 * się po biciu.
		 * 
		 * W przypadku gdy damka nie ma bicia zwróci tablice wypełnioną
		 * wartościami [Board.ERROR]. Zatem łatwo sprawdzić czy damka ma 
		 * bicie - wystarczy sprawdzić, czy zwrócona tablica w polach
		 * 
		 *		[Board.BTT_R, 0, 0], [Board.BTT_L, 0, 0], [Board.UPP_R, 0, 0], [Board.BTT_L, 0, 0]
		 *		
		 * posiada same wartości [Board.ERROR].
		 * 
		 * Jeżeli damka ma bicie, to tablica ta będzie zawierała 
		 * wszystkie współrzędne możliwych ruchów damki.
		 */
		private int[, ,] isCollidingQueenVersion(int x, int y)
		{
			int[, ,] collide = new int[4, this.boardSize - 2, 2];

			for (int i = 0; i < 4; i++)
			{
				for (int j = 0; j < this.boardSize - 2; j++)
				{
					collide[i, j, 0] = Board.ERROR;
				}
			}

			/*
			 * Zmienna definiująca stronę aktualnego gracza.
			 */
			bool isXX = this.isXX(this.bTab[x, y]);

			/*
			 * Objaśnienia zmiennych :
			 * 
			 * Poniższe zmienne definiują, czy mamy sprawdzać ruchy damki
			 * w ich kierunkach.
			 * 
			 * bottomR (bottomRight), bottomL(bottomLeft)
			 * upperR  (upperRight),  upperL (upperLeft)
			 * 
			 * Poniższe zmienne definują, czy spotkaliśmy na drodzę w danym kierunku
			 * pojedyńczego pionka lub pojedyńczą damkę przeciwnego gracza.
			 * 
			 * bottomRO (bottomRightObstacle), bottomLO (bottomLeftObstacle)
			 * upperRO  (upperRightObstacle),  upperLO  (upperLeftObstacle)
			 * 
			 * Poniższe zmienne to po prostu kopie współrzędnych damki [x] i [y].
			 * 
			 * pdx - rosnący x		mdx - malejący x
			 * pdy - posnący y		mdy - malejący y
			 * 
			 * Poniższe zmienne to liczniki znalezionych możliwych ruchów
			 * po biciu w danym kierunku.
			 * 
			 * cntBTT_R (counterBottomRight) - licznik możliwych ruchów po biciu po dolnej prawej przekątnej
			 * cntBTT_L (counterBottomLeft)  - licznik możliwych ruchów po biciu po dolnej lewej przekątnej
			 * cntUPP_R (counterUpperRight)  - licznik możliwych ruchów po biciu po górnej prawej przekątnej
			 * cntUPP_L (counterUpperLeft)   - licznik możliwych ruchów po biciu po górnej lewej przekątnej
			 */
			bool bottomR = x == 7 || y == 7 ? false : true;
			bool bottomL = x == 0 || y == 7 ? false : true;
			bool upperR  = x == 7 || y == 0 ? false : true;
			bool upperL  = x == 0 || y == 0 ? false : true;

			bool bottomRO = false;
			bool bottomLO = false;
			bool upperRO = false;
			bool upperLO = false;

			int pdx = x;
			int pdy = y;

			int mdx = x;
			int mdy = y;

			int cntBTT_R = 0;
			int cntBTT_L = 0;
			int cntUPP_R = 0;
			int cntUPP_L = 0;

			/*
			 * Dopóki którakolwiek ze zmiennych nie przekroczyła wartości progowych współrzędnych planszy.
			 */
			while (pdx <= this.boardSize - 1 || pdy <= this.boardSize - 1 || mdy >= 0 || mdx >= 0)
			{
				pdx++;
				pdy++;

				mdy--;
				mdx--;

				// ****************** BOTTOM RIGHT ******************
				if (bottomR)
				{
					if (pdx <= this.boardSize - 1 && pdy <= this.boardSize - 1)
					{
						if ((this.isXSide(this.bTab[pdx, pdy]) && isXX) || (this.isOSide(this.bTab[pdx, pdy]) && !isXX))
						{
							bottomR = false;
						}
						else if ((this.isOSide(this.bTab[pdx, pdy]) && isXX) || (this.isXSide(this.bTab[pdx, pdy]) && !isXX))
						{
							if (bottomRO)
							{
								bottomR = false;
							}
							bottomRO = true;

						}
						else if (bottomRO && this.isE(this.bTab[pdx, pdy]))
						{
							collide[Board.BTT_R, cntBTT_R, 0] = pdx;
							collide[Board.BTT_R, cntBTT_R, 1] = pdy;
							cntBTT_R++;
						}
					}
					else
					{
						bottomR = false;
					}
				}

				// ****************** BOTTOM LEFT ******************
				if (bottomL)
				{
					if (mdx >= 0 && pdy <= this.boardSize - 1)
					{
						if ((this.isXSide(this.bTab[mdx, pdy]) && isXX) || (this.isOSide(this.bTab[mdx, pdy]) && !isXX))
						{
							bottomL = false;
						}
						else if ((this.isOSide(this.bTab[mdx, pdy]) && isXX) || (this.isXSide(this.bTab[mdx, pdy]) && !isXX))
						{
							if (bottomLO)
							{
								bottomL = false;
							}

							bottomLO = true;
						}
						else if (bottomLO && this.isE(this.bTab[mdx, pdy]))
						{
							collide[Board.BTT_L, cntBTT_L, 0] = mdx;
							collide[Board.BTT_L, cntBTT_L, 1] = pdy;
							cntBTT_L++;
						}
					}
					else
					{
						bottomL = false;
					}
				}

				// ****************** UPPER RIGHT ******************
				if (upperR)
				{
					if (pdx <= this.boardSize - 1 && mdy >= 0)
					{
						if ((this.isXSide(this.bTab[pdx, mdy]) && isXX) || (this.isOSide(this.bTab[pdx, mdy]) && !isXX))
						{
							upperR = false;
						}
						else if ((this.isOSide(this.bTab[pdx, mdy]) && isXX) || (this.isXSide(this.bTab[pdx, mdy]) && !isXX))
						{
							if (upperRO)
							{
								upperR = false;
							}

							upperRO = true;
						}
						else if (upperRO && this.isE(this.bTab[pdx, mdy]))
						{
							collide[Board.UPP_R, cntUPP_R, 0] = pdx;
							collide[Board.UPP_R, cntUPP_R, 1] = mdy;
							cntUPP_R++;
						}
					}
					else
					{
						upperR = false;
					}
				}

				// ****************** UPPER LEFT ******************
				if (upperL)
				{
					if (mdx >= 0 && mdy >= 0)
					{
						if ((this.isXSide(this.bTab[mdx, mdy]) && isXX) || (this.isOSide(this.bTab[mdx, mdy]) && !isXX))
						{
							upperL = false;
						}
						if ((this.isOSide(this.bTab[mdx, mdy]) && isXX) || (this.isXSide(this.bTab[mdx, mdy]) && !isXX))
						{
							if (upperLO)
							{
								upperL = false;
							}

							upperLO = true;
						}
						else if (upperLO && this.isE(this.bTab[mdx, mdy]))
						{
							//this.label.Text = this.label.Text + String.Format(" {0} {1} ", mdx, mdy);
							collide[Board.UPP_L, cntUPP_L, 0] = mdx;
							collide[Board.UPP_L, cntUPP_L, 1] = mdy;
							cntUPP_L++;
						}
					}
					else
					{
						upperL = false;
					}
				}

			}

			return collide;
		}

		/*
		 * Metoda zwracająca macierz współrzędnych,
		 * na które pionek stojący w punkcie [lx, ly] może ruszyć
		 * się po biciu.
		 */
		private int[,] isColliding(int x, int y)
		{
			int[,] tab = new int[4, 2];

			for (int i = 0; i < 4; i++)
			{
				for (int j = 0; j < 2; j++)
				{
					tab[i, j] = Board.ERROR;
				}
			}

			/*
			 * Licznik, który trzyma liczbę możliwych bić
			 * dla pionka w punkcie [lx, ly].
			 * cnt - counter
			 */
			int cnt = 0;

			if (this.isX(this.bTab[x, y]))
			{

				if (!(x + 2 > this.boardSize - 1) && !(y + 2 > this.boardSize - 1))
				{
					if ((this.isO(this.bTab[x + 1, y + 1]) || this.isOO(this.bTab[x + 1, y + 1])) && this.isE(this.bTab[x + 2, y + 2]))
					{
						tab[cnt, 0] = x + 2;
						tab[cnt, 1] = y + 2;
						cnt++;
					}
				}

				if (!(x + 2 > this.boardSize - 1) && !(y - 2 < 0))
				{
					if ((this.isO(this.bTab[x + 1, y - 1]) || this.isOO(this.bTab[x + 1, y - 1])) && this.isE(this.bTab[x + 2, y - 2]))
					{
						tab[cnt, 0] = x + 2;
						tab[cnt, 1] = y - 2;
						cnt++;
					}
				}

				if (!(x - 2 < 0) && !(y + 2 > this.boardSize - 1))
				{
					if ((this.isO(this.bTab[x - 1, y + 1]) || this.isOO(this.bTab[x - 1, y + 1])) && this.isE(this.bTab[x - 2, y + 2]))
					{
						tab[cnt, 0] = x - 2;
						tab[cnt, 1] = y + 2;
						cnt++;
					}
				}

				if (!(x - 2 < 0) && !(y - 2 < 0))
				{
					if ((this.isO(this.bTab[x - 1, y - 1]) || this.isOO(this.bTab[x - 1, y - 1])) && this.isE(this.bTab[x - 2, y - 2]))
					{
						tab[cnt, 0] = x - 2;
						tab[cnt, 1] = y - 2;
						cnt++;
					}
				}
			}
			else if (this.isO(this.bTab[x, y]))
			{

				if (!(x + 2 > this.boardSize - 1) && !(y + 2 > this.boardSize - 1))
				{
					if ((this.isX(this.bTab[x + 1, y + 1]) || this.isXX(this.bTab[x + 1, y + 1])) && this.isE(this.bTab[x + 2, y + 2]))
					{
						tab[cnt, 0] = x + 2;
						tab[cnt, 1] = y + 2;
						cnt++;
					}
				}

				if (!(x + 2 > this.boardSize - 1) && !(y - 2 < 0))
				{
					if ((this.isX(this.bTab[x + 1, y - 1]) || this.isXX(this.bTab[x + 1, y - 1])) && this.isE(this.bTab[x + 2, y - 2]))
					{
						tab[cnt, 0] = x + 2;
						tab[cnt, 1] = y - 2;
						cnt++;
					}
				}

				if (!(x - 2 < 0) && !(y + 2 > this.boardSize - 1))
				{
					if ((this.isX(this.bTab[x - 1, y + 1]) || this.isXX(this.bTab[x - 1, y + 1])) && this.isE(this.bTab[x - 2, y + 2]))
					{
						tab[cnt, 0] = x - 2;
						tab[cnt, 1] = y + 2;
						cnt++;
					}
				}

				if (!(x - 2 < 0) && !(y - 2 < 0))
				{
					if ((this.isX(this.bTab[x - 1, y - 1]) || this.isXX(this.bTab[x - 1, y - 1])) && this.isE(this.bTab[x - 2, y - 2]))
					{
						tab[cnt, 0] = x - 2;
						tab[cnt, 1] = y - 2;
						cnt++;
					}
				}
			}

			return tab;
		}

		/****************************************************************************
		 *																			*
		 *	Objaśnienia:															*
		 *																			*
		 *		x				-	współrzędna x aktualnie wciśniętego guzika		*
		 *		y				-	współrzędna y aktualnie wciśniętego guzika		*
		 *																			*
		 *		lx	(lastX)		-	współrzędna x ostatnio wciśniętego guzika		*
		 *		ly	(lastY)		-	współrzędna y ostatnio wciśniętego guzika		*
		 *																			*
		 ****************************************************************************/

		/*
		 * Metoda zwracająca true, gdy punkty
		 *		[x, y] i [lx, ly]
		 * są na tej samej linii.
		 */
		private bool onSegment(int x, int y, int lx, int ly)
		{
			int cntX = 0;
			int cntY = 0;

			while (lx != x)
			{
				lx = lx < x ? lx + 1 : lx - 1;
				cntX++;
			}

			while (ly != y)
			{
				ly = ly < y ? ly + 1 : ly - 1;
				cntY++;
			}

			return cntX == cntY;
		}

		/*
		 * Metoda usuwająca wszystkie pionki / damki
		 * na linii [x, y] -> [lx, ly].
		 */
		private void batchRemove(int x, int y, int lx, int ly)
		{
			while (lx != x && ly != y)
			{
				lx = lx < x ? lx + 1 : lx - 1;
				ly = ly < y ? ly + 1 : ly - 1;
				this.bTab[lx, ly].Text = "";
				this.bTab[lx, ly].Image = default(Image);
			}
		}

		/*
		 * Metoda zwracająca true, jeżeli na linii
		 * [x, y] -> [lx, ly] nie ma żadnego pionka lub damki.
		 */
		private bool clear(int x, int y, int lx, int ly)
		{
			if (x == lx || y == ly)
			{
				return false;
			}

			if (!this.isE(this.bTab[x, y]))
			{
				return false;
			}

			int maxX = Math.Max(x, lx);
			int minX = Math.Min(x, lx);

			int maxY = Math.Max(y, ly);
			int minY = Math.Min(y, ly);

			minX++;
			minY++;

			while (minX != maxX && minY != maxY)
			{
				if (!this.isE(this.bTab[minX, minY]))
				{
					return false;
				}

				minX++;
				minY++;
			}

			return true;
		}

		/*
		 * Metoda zwracająca true, jeżeli ruch damki z punktu [lx, ly]
		 * do punktu [x, y] jest prawidłowy.
		 */
		private bool isMoveValidQueenVersion(int x, int y, int lx, int ly)
		{
			if (x == lx || y == ly)
			{
				return false;
			}

			bool coll = this.isCollideAffecting(this.isXSide(this.last));

			/*
			 * Jeżeli nie ma przymusowego bicia.
			 */
			if (!coll)
			{
				/*
				 * Jeżeli punkty [x, y] i [lx, ly] są na tej samej linii.
				 */
				if (this.onSegment(x, y, lx, ly))
				{
					/*
					 * Jeżeli na linii [x, y] -> [lx, ly] nie ma żadnego pionka lub damki.
					 */
					if (this.clear(x, y, lx, ly))
					{
						this.requireSwitching = true;
						return true;
					}
				}
			}

			/*
			 * Jeżeli jest przymusowe bicie.
			 */
			if (coll)
			{
				int[, ,] collide = this.isCollidingQueenVersion(lx, ly);

				for (int i = 0; i < 4; i++)
				{
					for (int j = 0; j < this.boardSize - 2; j++)
					{
						/*
						 * Jeżeli punkt [x, y] nie równał się żandemu z dostępnych
						 * ruchów po biciu i nie ma już żadnych dostępnych ruchów po biciu,
						 * to zakończ pętle
						 */
						if (collide[i, j, 0] == Board.ERROR)
						{
							break;
						}

						/*
						 * Jeżeli punkt [x, y] równa się jednemu z dostępnych
						 * ruchów po biciu.
						 */
						if (collide[i, j, 0] == x && collide[i, j, 1] == y)
						{
							String tmp = this.bTab[lx, ly].Text;
							this.batchRemove(x, y, lx, ly);
							this.bTab[x, y].Text = tmp;

							/*
							 * Sprawdzanie czy nie ma podwójnego bicia.
							 * Jeżeli jest podwójne bicie, to nie zmieniaj tury.
							 */
							if (this.isCollidingQueenVersion(x, y)[Board.BTT_R, 0, 0] != Board.ERROR
									|| this.isCollidingQueenVersion(x, y)[Board.BTT_L, 0, 0] != Board.ERROR
									|| this.isCollidingQueenVersion(x, y)[Board.UPP_R, 0, 0] != Board.ERROR
									|| this.isCollidingQueenVersion(x, y)[Board.UPP_L, 0, 0] != Board.ERROR)
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
			}

			return false;
		}

		/*
		 * Metoda zwracająca true, jeżeli ruch z guzika
		 * [this.last] do [btn] jest prawidłowy.
		 */
		private bool isMoveValid(Button btn)
		{
			int[] btnCords = this.getCoordinates(btn);
			int[] lstCords = this.getCoordinates(this.last);

			int x = btnCords[0];
			int y = btnCords[1];
			int lx = lstCords[0];
			int ly = lstCords[1];

			/*
			 * Jeżeli mamy do czynienia z ruchem damki, zwróć wynik
			 * isMoveValidQueenVersion().
			 * Jeżeli natomiast mamy do czynienia z ruchem pionka
			 * kontynuuj.
			 */
			if (this.isXX(this.last) || this.isOO(this.last))
			{
				return isMoveValidQueenVersion(x, y, lx, ly);
			}

			int[,] collide = this.isColliding(lx, ly);

			/*
			 * Jeżeli pionek nie musi bić.
			 */
			if (collide[0, 0] == Board.ERROR && !this.isCollideAffecting(this.isXSide(this.last)))
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
			/*
			 * Jeżeli pionek musi bić.
			 */
			else
			{

				for (int i = 0; i < 4; i++)
				{
					/*
					 * Jeżeli żaden z dostępnych ruchów po biciu,
					 * nie równa się naszemu ruchowi to zakończ.
					 */
					if (collide[i, 0] == Board.ERROR)
					{
						break;
					}

					/*
					 * Jeżeli nasz ruch równa się jednemu z dostępnych
					 * ruchów po biciu.
					 */
					if (collide[i, 0] == x && collide[i, 1] == y)
					{
						this.bTab[(x + lx) / 2, (y + ly) / 2].Text = "";
						this.bTab[(x + lx) / 2, (y + ly) / 2].Image = default(Image);
						String tmp = this.bTab[lx, ly].Text;
						Image tmi = this.bTab[lx, ly].Image;
						this.bTab[x, y].Text = this.bTab[lx, ly].Text;

						/*
						 * Jeżeli mamy podwójne bicie to nie zmieniaj tury.
						 */
						if (this.isColliding(x, y)[0, 0] != Board.ERROR)
						{
							this.requireSwitching = false;

							this.bTab[x, y].Text = tmp;
							this.bTab[x, y].Image = tmi;
							return true;
						}

						this.bTab[x, y].Text = tmp;
						this.bTab[x, y].Image = tmi;
						this.requireSwitching = true;
						return true;
					}

				}
			}

			return false;
		}
	}
}
