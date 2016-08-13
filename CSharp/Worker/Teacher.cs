using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Worker
{
	class Teacher : Worker
	{
		private static int cnt = 0;

		private int id;
		private double bonus;

		public Teacher()
		{
			this.id = getID();
			cnt++;
		}

		public double getBonus()
		{
			return this.bonus;
		}

		public void setBonus(double bonus)
		{
			this.bonus = bonus;
		}

		public string getPartials()
		{
			return String.Format("\n{0}{1}\n{2}{3}\n",
				"Pensja: ", this.getSalary(),
				"Premia: ", this.getBonus());
		}

		public void showPartials()
		{
			Console.WriteLine(getPartials());
		}

		public void setEverything(string name, string surname, double salary, double bonus)
		{
			this.setName(name);
			this.setSurname(surname);
			this.setSalary(salary);
			this.bonus = bonus;
			this.calculateNetto();
		}

		public override void wczytaj()
		{
			setEverything(
				Console.ReadLine(),
				Console.ReadLine(),

				double.Parse(Console.ReadLine(),
				System.Globalization.NumberStyles.AllowDecimalPoint,
				System.Globalization.NumberFormatInfo.InvariantInfo),

				double.Parse(Console.ReadLine(),
				System.Globalization.NumberStyles.AllowDecimalPoint,
				System.Globalization.NumberFormatInfo.InvariantInfo));
		}

		public override void calculateNetto()
		{
			this.setNettoSalary((this.getSalary() + this.bonus) * 0.81);
		}

		public override string ToString()
		{
			return String.Format("\n{0}{1}\n{2}{3}\n{4}{5}{6}",
				"Nazwisko: ", this.getSurname(),
				"Imie: ", this.getName(),
				"Pensja Netto: ", this.getNettoSalary(),
				this.getPartials());
		}
	}
}
