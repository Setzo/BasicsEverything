using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Worker
{
	class Program
	{
		static void Main(string[] args)
		{
			Worker worker = new Worker();
			worker.wczytaj();
			Console.WriteLine(worker);

			Teacher teacher = new Teacher();
			teacher.wczytaj();
			Console.WriteLine(teacher);

			Console.ReadLine();
		}
	}
}
