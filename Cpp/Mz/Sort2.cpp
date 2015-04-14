/*
 * Sort2.cpp
 *
 *  Created on: 14 kwi 2015
 *      Author: Wojciech Pruszak
 */

#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <windows.h>
#include <vector>
#include <ctime>
#include <limits.h>
#include <math.h>
#include <algorithm>

// Maksymalna liczba - 1, jaką może przyjąć element,
// który wczytujemy do wektora do posortowania.
// np. dla NUM_RANGE 10000, maksymalnie element może
// przyjąć wartość 9999 (10000 - 1). Zakres wartośći
// elementów wektora wyniesie zatem : <0; 9999>.
#define NUM_RANGE 10000

// *******************		COUNTER		*******************

class Counter {

public:

	void startCounter();
	double getCounter();

private:

	static double PCFreq;
	static __int64 CounterStart;

};

// Metoda rozpoczynająca odliczanie czasu
void Counter::startCounter() {

	LARGE_INTEGER li;

	if (!QueryPerformanceFrequency(&li)) {
		printf("Assert\n");
	}

	PCFreq = double(li.QuadPart) / 1000.0;

	QueryPerformanceCounter(&li);
	CounterStart = li.QuadPart;
}

// Metoda pobierająca aktualny stan zegara,
// zwraca wartość w double'u
double Counter::getCounter() {

	LARGE_INTEGER li;
	QueryPerformanceCounter(&li);
	return double(li.QuadPart - CounterStart) / PCFreq;
}

double Counter::PCFreq = 0.0;

__int64 Counter::CounterStart = 0;

// *******************		QUICKSORT		*******************

// Klasa odpowiadająca za sortowanie algorytmem
// sortowania szybkiego.
class Quicksort {

public:

	void quicksort(std::vector<unsigned> &numVector, int p, int r);

private:

	int partition(std::vector<unsigned> &numVector, int p, int r);

};

// Główna metoda sortująca, dzieli ona wektor
// [numVector] na 2 mniejsze wektory za pomocą piwota
// znalezionego dzięki funkcji partition. Następnie
// metoda ta wywołuje się rekurencyjnie aż do posortowania
// wektora [numVector].
// numVector - wektor do posortowania
// p - początek wektora (lub indeks od którego mamy zacząć sortować)
// q - koniec wektora (lub indeks na którym mamy skończyć sortować)
void Quicksort::quicksort(std::vector<unsigned> &numVector, int p, int r) {

	// Jeżeli jesteśmy już na samym końcu drzewa wywołań i
	// p == r, to zakończ działanie.
	if(p < r) {

		// q - piwot
		int q = partition(numVector, p, r);

		// Rekurencyjne wywołanie metody
		// quicksort(początek, piwot).
		Quicksort::quicksort(numVector, p, q);
		// Rekurencyjne wywołanie metody
		// quicksort(piwot + 1, koniec).
		Quicksort::quicksort(numVector, q + 1, r);
	}

	return;
}

// Funkcja zwracająca indeks piwota i sortująca wektor
// [numVector].
// numVector - wektor do posortowania
// p - początek wektora (lub indeks od którego mamy zacząć sortować)
// q - koniec wektora (lub indeks na którym mamy skończyć sortować)
int Quicksort::partition(std::vector<unsigned> &numVector, int p, int r) {

	unsigned x = numVector[p];

	int i = p;
	int j = r;
	int w;

	// Sortowanie i szukanie piwota
	while (true) {

		while (numVector[j] > x) {
			j--;
		}

		while (numVector[i] < x) {
			i++;
		}

		if (i < j) {

			w = numVector[i];
			numVector[i] = numVector[j];
			numVector[j] = w;
			i++;
			j--;

		} else {

			return j;
		}
	}
}

// *******************		COUNTING_SORT		*******************

// Klasa odpowiadająca za sortowanie przez zliczanie.
class SortCount {

public:

	void countSort(std::vector<unsigned> &numVector);

private:

	unsigned findMaximum(std::vector<unsigned> numVector);

};

// Główna metoda sortująca sortująca wektor [numVector] metodą sortowania przez zliczanie.
// numVector - wektor do posortowania
void SortCount::countSort(std::vector<unsigned> &numVector) {

	// Przypisanie zmiennej [max] wartości największego elementu
	// wektora [numVector].
	unsigned max = findMaximum(numVector);

	// Utworzenie tablicy która przechowa nasze elementy.
	// Tablica ma [max + 1] elementów, bo maksymalny element
	// wektora [numVector] ma wartość [max] i jako że indeksowanie w c++
	// zaczyna się od zera, to nie moglibyśmy przypisać wartości
	// w pole tmp[max], gdyby tmp miało długość = [max].
	int tmp[max + 1];

	// Wyzerowanie tmp
	for(unsigned currentIteration = 0; currentIteration < max + 1; currentIteration++) {

		tmp[currentIteration] = 0;
	}

	// Zliczanie elementów wektora numVector do tablicy [tmp].
	for(unsigned currentIteration = 0; currentIteration < numVector.size(); currentIteration++) {

		tmp[numVector[currentIteration]]++;
	}

	// Usunięcie już niepotrzebnych wartości wektora.
	numVector.clear();

	for(unsigned currentIteration = 0; currentIteration < max + 1; currentIteration++) {

		// Jeżeli [tmp] ma jakąś wartość [x] w polu [currentIteration]
		// to oznacza to, iż wcześniej przy zliczaniu wektor [numVector]
		// miał [x] wartości [currentIteration].
		while(tmp[currentIteration] != 0) {

			// Dodanie już posortowanych wartości do wektora.
			numVector.push_back(currentIteration);
			// Ponowne zerowanie [tmp].
			tmp[currentIteration]--;
		}
	}

	return;
}

// Funkcja zwracająca wartość największego elementu w wektorze [numVector]
unsigned SortCount::findMaximum(std::vector<unsigned> numVector) {

	// [max] - indeks największego elementu
	int max = 0;

	for(unsigned currentIteration = 0; currentIteration < numVector.size(); currentIteration++) {

		// Szukanie największego elementu
		if(numVector[max] < numVector[currentIteration]) {

			max = currentIteration;
		}

	}

	// Zwrócenie elementu o indeksie [max] z wektora [numVector]
	return numVector[max];
}

// *******************		BUBBLE_SORT		*******************

// Klasa odpowiadająca za sortowanie bąbelkowe.
class SortBubble {

public:

	void bubbleSort(std::vector<unsigned> &numVector);

};

// Główna metoda sortująca wektor [numVector] metodą sortowania bąbelkowego.
// numVector - wektor do posortowania
void SortBubble::bubbleSort(std::vector<unsigned> &numVector) {

	for (unsigned currentIteration = 0; currentIteration < numVector.size(); currentIteration++) {

		for (unsigned innerIteration = 0; innerIteration < numVector.size() - 1; innerIteration++) {

			// Jeżeli elementy są nie w tej kolejności, to zamień je miejscami.
			if (numVector[innerIteration] > numVector[innerIteration + 1]) {

				std::swap(numVector[innerIteration], numVector[innerIteration + 1]);
			}
		}
	}
}

// *******************		BUCKET_SORT		*******************

// Klasa odpowiadająca za sortowanie kubełkowe.
class SortBucket {

public:

	void bucketSort(std::vector<double> &numVector);

private:

	// Liczba kubełków
	static const unsigned numOfBuckets = 10;

};

// Główna metoda sortująca wektor [numVector] metodą sortowania kubełkowego.
// numVector - wektor do posortowania
void SortBucket::bucketSort(std::vector<double> &numVector) {

	// Tworzenie [numOfBuckets] wektorów (naszych kubełków).
	std::vector<double> buckets[numOfBuckets];

	// Pętla układająca wszystkie elementy wektora [numVector] do odpowiednich
	// kubełków na podstawie ich pierwszej liczbie po przecinku.
	// np. 0.442 trafi do kubełka 4 (indeks od 0 - 0, 1, 2, 3, 4),
	// bo floor(0.442 * [numOfBuckets]) =
	// = floor(0.442 * 10) = floor(4.42) = 4
	for(unsigned currentIteration = 0; currentIteration < numVector.size(); currentIteration++) {

		buckets[static_cast<int> (floor(numVector[currentIteration] * numOfBuckets))]
				.push_back(numVector[currentIteration]);
	}

	// Usunięcie już niepotrzebnych elementów wektora [numVector]
	numVector.clear();

	for(unsigned currentIteration = 0; currentIteration < numOfBuckets; currentIteration++) {

		// Sortowanie kubełka o indeksie [currentIteration].
		std::sort(buckets[currentIteration].begin(), buckets[currentIteration].end());

		for(unsigned innerIteration = 0; innerIteration < buckets[currentIteration].size(); innerIteration++) {

			// Przypisywanie wektorowi [numVector] już posortowanych elementów.
			numVector.push_back(buckets[currentIteration][innerIteration]);
		}
	}
}

// *******************		MAIN		*******************

// Główna metoda programu
int main() {

	// Instancja klasy Counter, odpowiadająca za licznie czasu.
	Counter cnt;

	// Instancje klas odpowiadających za sortowanie, z użyciem
	// wybranych algorytmów.
	Quicksort  qckSort;
	SortCount  cntSort;
	SortBubble bblSort;
	SortBucket bckSort;

	// Przyszłe wyniki pomiarów czasu.
	// qck - Quicksort
	// cnt - Counting Sort
	// bbl - Bubble Sort
	// bck - Bucket Sort
	double qckSortStart;
	double qckSortEnd;
	double cntSortStart;
	double cntSortEnd;
	double bblSortStart;
	double bblSortEnd;
	double bckSortStart;
	double bckSortEnd;

	// Liczba elementów ciągu do wygenerowania i posortowania.
	unsigned numElements;

	// Wektor, który będzie przechowywał elementy do posortowania.
	std::vector<unsigned> numVector;
	// Wektor, który będzie zawierał elementy do posortowania kubełkowego
	// - liczby z przedziału <0; 1).
	std::vector<double> numVectorBucketSort;

	// Wczytanie liczby elementów do wygenerowania i posortowania.
	printf("Podaj liczbe elementow ciagu: ");
	std::cin>> numElements;

	// Po wykonaniu tej instrukcji, funkcja rand() zacznie zawsze
	// zwracać nam wartości losowe.
	srand(time(NULL));

	// Rozpoczęcie pracy zegara.
	cnt.startCounter();

	// *******************		QUICKSORT		*******************

	printf("\n%20s\nElementy na wejściu:\n", "QUICKSORT:");

	// Pętla wcztująca [numElements] losowych elementów do wektora [numVector].
	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie nieposortowanych elementów wektora.
		numVector.push_back(rand() % NUM_RANGE);
		printf("%d, ", numVector.back());
	}

	// Pobranie czasu startu, dla sortowania szybkiego.
	qckSortStart = cnt.getCounter();
	// Wywołanie funkcji sortującej algorytmem quicksort.
	qckSort.quicksort(numVector, 0, numVector.size() - 1);
	// Pobranie czasu stopu, dla sortowania szybkiego.
	qckSortEnd = cnt.getCounter();

	printf("\nElementy na wyjściu:\n");

	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie już posortowanych elementów wektora.
		printf("%d, ", numVector[currentElement]);
	}

	// *******************		COUNTING_SORT		*******************

	numVector.clear();

	printf("\n\n%20s\nElementy na wejściu:\n", "SORT_COUNT:");

	// Pętla wcztująca [numElements] losowych elementów do wektora [numVector].
	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie nieposortowanych elementów wektora.
		numVector.push_back(rand() % NUM_RANGE);
		printf("%d, ", numVector.back());
	}

	// Pobranie czasu startu, dla sortowania przez zliczanie.
	cntSortStart = cnt.getCounter();
	// Wywołanie funkcji sortującej przez zliczanie.
	cntSort.countSort(numVector);
	// Pobranie czasu stopu, dla sortowania przez zliczanie.
	cntSortEnd = cnt.getCounter();

	printf("\nElementy na wyjściu:\n");

	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie już posortowanych elementów wektora.
		printf("%d, ", numVector[currentElement]);
	}

	// *******************		BUBBLE_SORT		*******************

	numVector.clear();

	printf("\n\n%20s\nElementy na wejściu:\n", "SORT_BUBBLE:");

	// Pętla wcztująca [numElements] losowych elementów do wektora [numVector].
	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie nieposortowanych elementów wektora.
		numVector.push_back(rand() % NUM_RANGE);
		printf("%d, ", numVector.back());
	}

	// Pobranie czasu startu, dla sortowania bąbelkowego.
	bblSortStart = cnt.getCounter();
	// Wywołanie funkcji sortującej bąbelkowo.
	bblSort.bubbleSort(numVector);
	// Pobranie czasu stopu, dla sortowania bąbelkowego.
	bblSortEnd = cnt.getCounter();

	printf("\nElementy na wyjściu:\n");

	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie już posortowanych elementów wektora.
		printf("%d, ", numVector[currentElement]);
	}

	// *******************		BUCKET_SORT		*******************

	numVector.clear();

	printf("\n\n%20s\nElementy na wejściu:\n", "SORT_BUCKET:");

	// Pętla wcztująca [numElements] losowych elementów do wektora [numVector].
	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie nieposortowanych elementów wektora.
		numVectorBucketSort.push_back(rand() / (double)RAND_MAX);
		printf("%.3f, ", numVectorBucketSort.back());
	}

	// Pobranie czasu startu, dla sortowania kubełkowego.
	bckSortStart = cnt.getCounter();
	// Wywołanie funkcji sortującej kubełkowo.
	bckSort.bucketSort(numVectorBucketSort);
	// Pobranie czasu stopu, dla sortowania kubełkowego.
	bckSortEnd = cnt.getCounter();

	printf("\nElementy na wyjściu:\n");

	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie już posortowanych elementów wektora.
		printf("%.3f, ", numVectorBucketSort[currentElement]);
	}

	numVectorBucketSort.clear();

	// *******************		TIMERS		*******************

	// Wypisanie wszystkich czasów, dla poszczególnych sortowań.
	printf("\n\n%26s%d\n%27s\n%27s%-.10f\n%27s%-.10f\n%27s%-.10f\n%27s%-.10f\n",
			"Liczba elementów w ciągu : ", numElements,
			"Czasy       : ",
			"Quicksort   : ", qckSortEnd - qckSortStart,
			"Sort_Count  : ", cntSortEnd - cntSortStart,
			"Sort_Bubble : ", bblSortEnd - bblSortStart,
			"Sort_Bucket : ", bckSortEnd - bckSortStart);

	return 0;
}
