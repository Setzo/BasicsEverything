#include "newconio.h"
#include <iostream>

using namespace std;

main() {

    // Init biblioteki.
    _cinit();
    cursoroff();

    // http://stackoverflow.com/questions/16569239/how-to-use-function-srand-with-time-h
    srand((unsigned) time(NULL));

    // Zainicjalizowanie czystego pola do rysowania.
    frame(FRAME_SINGLE, YELLOW, 0, 0, 79, 24);
    gotoxy(10, 0);
    textattr(0x17);

    // Ilość kwadratów.
    int squareCount;

    cout << "Podaj liczbę kwadratow." << endl;
    cin >> squareCount;

    // Max znaków w pionie.
    const int PIONOWO = 22;
    // Max znaków w poziomie.
    const int POZIOMO = 78;

    // Tablica z danymi kwadratów.
    // Miejce:
    // [0] - Wspolrzedna x dla lewego gornego wierzcholka
    // [1] - Wspolrzedna y dla lewego gornego wierzcholka
    // [2] - Wspolrzedna x dla prawego dolnego wierzcholka
    // [3] - Wspolrzedna y dla prawego dolnego wierzcholka
    // [4] - Kolor
    // [5] - Kierunek
    int squares[squareCount][6];

    // Dla każdego kwadratu - znajdź jakieś współrzędne i narysuj go.
    for (int iter = 0; iter < squareCount; iter++) {

        // Wspolrzedna x dla lewego gornego wierzcholka.
        squares[iter][0] = (rand() % POZIOMO / 2) + 1;
        // Wspolrzedna y dla lewego gornego wierzcholka.
        squares[iter][1] = (rand() % PIONOWO / 2) + 1;

        // Wspolrzedna x dla prawego dolnego wierzcholka.
        squares[iter][2] = squares[iter][0] + 10;
        // Wspolrzedna y dla prawego dolnego wierzcholka.
        squares[iter][3] = squares[iter][1] + 5;

        // Kolor jako liczba od 0 do 255.
        squares[iter][4] = rand() % 256;

        // Kierunek (od 1 do 4)
        // 0 - prawo
        // 1 - dół
        // 2 - lewo
        // 3 - góra
        squares[iter][5] = rand() % 4;

        // Narysoanie
        fillrectattr(squares[iter][4], squares[iter][0], squares[iter][1], squares[iter][2], squares[iter][3]);
    }


    do {
        for (int iter = 0; iter < squareCount; iter++) {

            // Pobierz aktualny kierunek.
            int dir = squares[iter][5];

            // Zamaluj na czarno stare koordynaty kwadratu, by nadać złudzenie poruszania się.
            fillrectattr(0, squares[iter][0], squares[iter][1], squares[iter][2], squares[iter][3]);

            // DO 4 IF'ów na dole - Jeżeli kwadrat wykracza poza którąś ramę, zmień kierunek na przeciwny.
            if(squares[iter][2] == POZIOMO - 1) {
                squares[iter][5] = 2;
            }

            if(squares[iter][0] == 2) {
                squares[iter][5] = 0;
            }

            if(squares[iter][3] == PIONOWO) {
                squares[iter][5] = 3;
            }

            if(squares[iter][1] == 2) {
                squares[iter][5] = 1;
            }

            // Zaktualizuj aktualne koordynaty kwadratu w zależności od kierunku.
            if(dir % 2 == 0) {
                squares[iter][0] += dir == 0 ? 1 : -1;
                squares[iter][2] += dir == 0 ? 1 : -1;
            } else {
                squares[iter][1] += dir == 1 ? 1 : -1;
                squares[iter][3] += dir == 1 ? 1 : -1;
            }

            // Narysuj nowy kwadrat (już na nowych koordynatach).
            fillrectattr(squares[iter][4], squares[iter][0], squares[iter][1], squares[iter][2], squares[iter][3]);
        }

        // Animacja.
        Sleep(40);

    } while (key != 27);
}
