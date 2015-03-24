using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Rectangles
{
	class Program
	{
		static void Main(string[] args)
		{
			string line;
			//Console.WriteLine((line = Console.ReadLine()).GetType());

			while (!(line = Console.ReadLine()).Equals("wyjdz"))
			{
				int i = 0;
				string[] chunks = line.Split(' ');

				char sign = char.Parse(chunks[i++]);
				int rec = int.Parse(chunks[i++]);
				int res = int.Parse(chunks[i]);

				switch (sign)
				{
					case 'z':
						{
							Calc.recordTable[rec] = res;
							break;
						}
					case '+':
						{
							Console.WriteLine(String.Format("{0} + {1} = {2}"
								, Calc.recordTable[rec]
								, Calc.recordTable[res]
								, Calc.recordTable[rec] + Calc.recordTable[res]));
							break;
						}
					case '-':
						{
							Console.WriteLine(String.Format("{0} - {1} = {2}"
								, Calc.recordTable[rec]
								, Calc.recordTable[res]
								, Calc.recordTable[rec] - Calc.recordTable[res]));
							break;
						}
					case '/':
						{
							Console.WriteLine(String.Format("{0} / {1} = {2}"
								, Calc.recordTable[rec]
								, Calc.recordTable[res]
								, Calc.recordTable[rec] / Calc.recordTable[res]));
							break;
						}
					case '*':
						{
							Console.WriteLine(String.Format("{0} * {1} = {2}"
								, Calc.recordTable[rec]
								, Calc.recordTable[res]
								, Calc.recordTable[rec] * Calc.recordTable[res]));
							break;
						}
					case '%':
						{
							Console.WriteLine(String.Format("{0} % {1} = {2}"
								, Calc.recordTable[rec]
								, Calc.recordTable[res]
								, Calc.recordTable[rec] % Calc.recordTable[res]));
							break;
						}
				}
			}

			Console.ReadLine();
		}
	}
}
