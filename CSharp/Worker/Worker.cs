using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Worker
{
	public class Worker : Netto
	{
		private static int cnt = 0;
		private int id;

		private string name;
		private string surname;
		private double salary;
		private double netto;

		public Worker(string name, string surname, double salary)
		{
			this.id = cnt++;

			this.name = name;
			this.surname = surname;
			this.salary = salary;
		}

		public Worker()
		{
			this.id = cnt++;
		}

		public virtual int getID()
		{
			return this.id;
		}

		public virtual double getSalary()
		{
			return this.salary;
		}

		public virtual double getNettoSalary()
		{
			return this.netto;
		}

		public virtual string getName()
		{
			return this.name;
		}

		public virtual string getSurname()
		{
			return this.surname;
		}

		public virtual void setName(string name)
		{
			this.name = name;
		}

		public virtual void setSurname(string surname)
		{
			this.surname = surname;
		}

		public virtual void setSalary(double salary)
		{
			this.salary = salary;
		}

		public virtual void setNettoSalary(double netto)
		{
			this.netto = netto;
		}

		public virtual void setEverything(string name, string surname, double salary)
		{
			this.name = name;
			this.surname = surname;
			this.salary = salary;
			this.calculateNetto();
		}

		public virtual void wczytaj()
		{
			setEverything(Console.ReadLine(), Console.ReadLine(), double.Parse(Console.ReadLine(),
				System.Globalization.NumberStyles.AllowDecimalPoint,
				System.Globalization.NumberFormatInfo.InvariantInfo));
		}

		public virtual void calculateNetto()
		{
			this.netto = this.salary * 0.81;
		}

		public virtual void showNetto()
		{
			Console.Write(this.ToString());
		}

		public override string ToString()
		{
			return String.Format("\n{0}{1}\n{2}{3}\n{4}{5}\n",
				"Nazwisko: ", this.surname,
				"Imie: ", this.name,
				"Pensja Netto: ", this.netto);
		}
	}
}
