// I Liceum Ogólnokształcšce
// im. K. Brodzińskiego
// w Tarnowie
//--------------------------
// Koło informatyczne 2006/7
//--------------------------
// Program: P013
//--------------------------
// Zbiór procedur obsługi konsoli znakowej w Windows
//          (C)2006 mgr Jerzy Wałaszek
//--------------------------------------------------

#include <iostream>
#include <windows.h>
#include <string>
#include "newconio.h"

using namespace std;

extern "C" {BOOL WINAPI SetConsoleDisplayMode(HANDLE,DWORD,PCOORD);}

static WORD   _txattr;
static HANDLE _hcout,_hcin;
static int    _cextend = -1;

//----------------------------------------------------------------------
// Poniższa tablica odwzorowuje wirtualne kody klawiszy Windows na kody
// BIOS dla klawiatury IBM PC. Dla każdego wirtualnego kodu klawisza
// dostępne sš cztery wartoci BIOS: zwykła, z shift, z Ctrl i z Alt.
// Kombinacje klawiszy nie posiadajšce odpowiednika BIOS majš wartoć
// -1 i sš ignorowane. Rozszerzone klawisze (nie ze zbioru ASCII)
// majš ustawiony na 1 bit 8 przy pomocy makra EXT.
//----------------------------------------------------------------------

#define EXT(key)    ((key)+0x100)
#define ISEXT(val)  ((val)&0x100)
#define EXTVAL(val) ((val)&0xff)

struct kbd
{
    short keycode; // kod wirtualny
    short normal;  // normalny kod BIOS
    short shift;   // kod BIOS z shift
    short ctrl;    // kod BIOS z Ctrl
    short alt;     // kod BIOS z Alt
} kbdtab [] =
        {
//  wirtualny     normalny   shift       ctrl         alt

                { VK_BACK,      0x08,       0x08,       0x7f,       EXT(14)  },
                { VK_TAB,       0x09,       EXT(15),    EXT(148),   EXT(165) },
                { VK_RETURN,    0x0d,       0x0d,       0x0a,       EXT(166) },
                { VK_ESCAPE,    0x1b,       0x1b,       0x1b,       EXT(1)   },
                { VK_SPACE,     0x20,       0x20,       EXT(3),     0x20,    },
                { '0',          '0',        ')',        -1,         EXT(129) },
                { '1',          '1',        '!',        -1,         EXT(120) },
                { '2',          '2',        '@',        EXT(3),     EXT(121) },
                { '3',          '3',        '#',        -1,         EXT(122) },
                { '4',          '4',        '$',        -1,         EXT(123) },
                { '5',          '5',        '%',        -1,         EXT(124) },
                { '6',          '6',        '^',        0x1e,       EXT(125) },
                { '7',          '7',        '&',        -1,         EXT(126) },
                { '8',          '8',        '*',        -1,         EXT(127) },
                { '9',          '9',        '(',        -1,         EXT(128) },

                { 'A',          'a',        'A',        0x01,       EXT(30)  },
                { 'B',          'b',        'B',        0x02,       EXT(48)  },
                { 'C',          'c',        'C',        0x03,       EXT(46)  },
                { 'D',          'd',        'D',        0x04,       EXT(32)  },
                { 'E',          'e',        'E',        0x05,       EXT(18)  },
                { 'F',          'f',        'F',        0x06,       EXT(33)  },
                { 'G',          'g',        'G',        0x07,       EXT(34)  },
                { 'H',          'h',        'H',        0x08,       EXT(35)  },
                { 'I',          'i',        'I',        0x09,       EXT(23)  },
                { 'J',          'j',        'J',        0x0a,       EXT(36)  },
                { 'K',          'k',        'K',        0x0b,       EXT(37)  },
                { 'L',          'l',        'L',        0x0c,       EXT(38)  },
                { 'M',          'm',        'M',        0x0d,       EXT(50)  },
                { 'N',          'n',        'N',        0x0e,       EXT(49)  },
                { 'O',          'o',        'O',        0x0f,       EXT(24)  },
                { 'P',          'p',        'P',        0x10,       EXT(25)  },
                { 'Q',          'q',        'Q',        0x11,       EXT(16)  },
                { 'R',          'r',        'R',        0x12,       EXT(19)  },
                { 'S',          's',        'S',        0x13,       EXT(31)  },
                { 'T',          't',        'T',        0x14,       EXT(20)  },
                { 'U',          'u',        'U',        0x15,       EXT(22)  },
                { 'V',          'v',        'V',        0x16,       EXT(47)  },
                { 'W',          'w',        'W',        0x17,       EXT(17)  },
                { 'X',          'x',        'X',        0x18,       EXT(45)  },
                { 'Y',          'y',        'Y',        0x19,       EXT(21)  },
                { 'Z',          'z',        'Z',        0x1a,       EXT(44)  },

                { VK_PRIOR,     EXT(73),    EXT(73),    EXT(132),   EXT(153) },
                { VK_NEXT,      EXT(81),    EXT(81),    EXT(118),   EXT(161) },
                { VK_END,       EXT(79),    EXT(79),    EXT(117),   EXT(159) },
                { VK_HOME,      EXT(71),    EXT(71),    EXT(119),   EXT(151) },
                { VK_LEFT,      EXT(75),    EXT(75),    EXT(115),   EXT(155) },
                { VK_UP,        EXT(72),    EXT(72),    EXT(141),   EXT(152) },
                { VK_RIGHT,     EXT(77),    EXT(77),    EXT(116),   EXT(157) },
                { VK_DOWN,      EXT(80),    EXT(80),    EXT(145),   EXT(160) },
                { VK_INSERT,    EXT(82),    EXT(82),    EXT(146),   EXT(162) },
                { VK_DELETE,    EXT(83),    EXT(83),    EXT(147),   EXT(163) },
                { VK_NUMPAD0,   '0',        EXT(82),    EXT(146),   -1       },
                { VK_NUMPAD1,   '1',        EXT(79),    EXT(117),   -1       },
                { VK_NUMPAD2,   '2',        EXT(80),    EXT(145),   -1       },
                { VK_NUMPAD3,   '3',        EXT(81),    EXT(118),   -1       },
                { VK_NUMPAD4,   '4',        EXT(75),    EXT(115),   -1       },
                { VK_NUMPAD5,   '5',        EXT(76),    EXT(143),   -1       },
                { VK_NUMPAD6,   '6',        EXT(77),    EXT(116),   -1       },
                { VK_NUMPAD7,   '7',        EXT(71),    EXT(119),   -1       },
                { VK_NUMPAD8,   '8',        EXT(72),    EXT(141),   -1       },
                { VK_NUMPAD9,   '9',        EXT(73),    EXT(132),   -1       },
                { VK_MULTIPLY,  '*',        '*',        EXT(150),   EXT(55)  },
                { VK_ADD,       '+',        '+',        EXT(144),   EXT(78)  },
                { VK_SUBTRACT,  '-',        '-',        EXT(142),   EXT(74)  },
                { VK_DECIMAL,   '.',        '.',        EXT(83),    EXT(147) },
                { VK_DIVIDE,    '/',        '/',        EXT(149),   EXT(164) },
                { VK_F1,        EXT(59),    EXT(84),    EXT(94),    EXT(104) },
                { VK_F2,        EXT(60),    EXT(85),    EXT(95),    EXT(105) },
                { VK_F3,        EXT(61),    EXT(86),    EXT(96),    EXT(106) },
                { VK_F4,        EXT(62),    EXT(87),    EXT(97),    EXT(107) },
                { VK_F5,        EXT(63),    EXT(88),    EXT(98),    EXT(108) },
                { VK_F6,        EXT(64),    EXT(89),    EXT(99),    EXT(109) },
                { VK_F7,        EXT(65),    EXT(90),    EXT(100),   EXT(110) },
                { VK_F8,        EXT(66),    EXT(91),    EXT(101),   EXT(111) },
                { VK_F9,        EXT(67),    EXT(92),    EXT(102),   EXT(112) },
                { VK_F10,       EXT(68),    EXT(93),    EXT(103),   EXT(113) },
                { VK_F11,       EXT(133),   EXT(135),   EXT(137),   EXT(139) },
                { VK_F12,       EXT(134),   EXT(136),   EXT(138),   EXT(140) },
                { 0xdc,         '\\',       '|',        0x1c,       EXT(43)  },
                { 0xbf,         '/',        '?',        -1,         EXT(53)  },
                { 0xbd,         '-',        '_',        0x1f,       EXT(130) },
                { 0xbb,         '=',        '+',        -1,         EXT(131) },
                { 0xdb,         '[',        '{',        0x1b,       EXT(26)  },
                { 0xdd,         ']',        '}',        0x1d,       EXT(27)  },
                { 0xba,         ';',        ':',        -1,         EXT(39)  },
                { 0xde,         '\'',       '"',        -1,         EXT(40)  },
                { 0xbc,         ',',        '<',        -1,         EXT(51)  },
                { 0xbe,         '.',        '>',        -1,         EXT(52)  },
                { 0xc0,         '`',        '~',        -1,         EXT(41)  },

                { -1,           -1,         -1,         -1,         -1       }  // KONIEC
        };

// Funkcja inicjuje parametry konsoli - należy
// jš wywołać na samym poczštku programu
//--------------------------------------------

void _cinit()
{
    _hcout = GetStdHandle(STD_OUTPUT_HANDLE);
    _hcin  = GetStdHandle(STD_INPUT_HANDLE);
    SetConsoleOutputCP(1250);
    SetConsoleCP(1250);
    _txattr = 7; // Czarne tło i białe znaki
    clrscr();
}

// Funkcja konwertuje tekst ze standardu Windows 1250
// na standard konsoli znakowej Latin II.
//---------------------------------------------------
// s - tekst kodowany wg Windows 1250
//---------------------------------------------------
string _pl(string s)
{
    for(unsigned i = 0; i < s.length(); i++)
        switch(s[i])
        {
            case 'š' : s[i] = 165; break;
            case 'ć' : s[i] = 134; break;
            case 'é' : s[i] = 'e'; break;
            case 'ę' : s[i] = 169; break;
            case 'ł' : s[i] = 136; break;
            case 'ń' : s[i] = 228; break;
            case 'ö' : s[i] = 'o'; break;
            case 'ó' : s[i] = 162; break;
            case '' : s[i] = 152; break;
            case 'ż' : s[i] = 190; break;
            case '' : s[i] = 171; break;
            case 'Ľ' : s[i] = 164; break;
            case 'Ć' : s[i] = 143; break;
            case 'Ę' : s[i] = 168; break;
            case 'Ł' : s[i] = 157; break;
            case 'Ń' : s[i] = 227; break;
            case 'Ó' : s[i] = 224; break;
            case '' : s[i] = 151; break;
            case 'Ż' : s[i] = 189; break;
            case '' : s[i] = 141; break;
        }
    return s;
}

// Funkcja wywietla zadany parametrem tekst
// na rodku podanego wiersza konsoli
//------------------------------------------
void center(int y, string s)
{
    gotoxy((80 - s.length()) / 2, y);
    cout << s;
}

// Funkcja czyci ekran konsoli.
//------------------------------
void clrscr()
{
    COORD cpos = { 0, 0 };
    DWORD cwrt;
    CONSOLE_SCREEN_BUFFER_INFO cinf;
    DWORD csize;

    if(!GetConsoleScreenBufferInfo(_hcout, &cinf)) return;
    csize = cinf.dwSize.X * cinf.dwSize.Y;
    if(!FillConsoleOutputCharacter(_hcout,' ',csize,cpos,&cwrt)) return;
    if(!FillConsoleOutputAttribute(_hcout,_txattr,csize,cpos,&cwrt)) return;
    SetConsoleCursorPosition(_hcout, cpos);
    SetConsoleTextAttribute(_hcout,_txattr);
}

// Funkcja wyłšcza kursor
//-----------------------
void cursoroff()
{
    CONSOLE_CURSOR_INFO ccinf;

    GetConsoleCursorInfo(_hcout,&ccinf);
    ccinf.bVisible = false;
    SetConsoleCursorInfo(_hcout,&ccinf);
}

// Funkcja włšcza kursor
//-----------------------
void cursoron()
{
    CONSOLE_CURSOR_INFO ccinf;

    GetConsoleCursorInfo(_hcout,&ccinf);
    ccinf.bVisible = true;
    SetConsoleCursorInfo(_hcout,&ccinf);
}

// Funkcja wstrzymuje wykonanie na zadanš
// liczbę milisekund
//---------------------------------------
// msec - liczba milisekund
//---------------------------------------
void delay(int msec)
{
    unsigned t1 = GetTickCount();
    while((GetTickCount() - t1) < (unsigned)msec) ;
}

// Funkcja wypełnia zadany obszar okna konsoli
// podanym atrybutem. Pozycja kursora oraz znaki
// zawarte w obszarze nie sš zmieniane.
//----------------------------------------------
// attr - atrybut do wypełnienia obszaru
// xb,yb - współrzędne lewego górnego narożnika
// xe,ye - współrzędne prawego dolnego narożnika
//----------------------------------------------
void fillrectattr(WORD attr, int xb, int yb, int xe, int ye)
{
    CONSOLE_SCREEN_BUFFER_INFO cinf;
    COORD pos;
    DWORD crd;

    if(!GetConsoleScreenBufferInfo(_hcout, &cinf)) return;
    if((xb >= cinf.dwSize.X) || (yb >= cinf.dwSize.Y) ||
       (xe < xb) || (ye < yb) || (xe < 0) || (ye < 0)) return; // poza oknem
    if(xb < 0) xb = 0;
    if(yb < 0) yb = 0;
    if(xe >= cinf.dwSize.X) xe = cinf.dwSize.X - 1;
    if(ye >= cinf.dwSize.Y) ye = cinf.dwSize.Y - 1;
    DWORD len = xe - xb + 1;
    pos.X = xb;
    for(int i = yb; i <= ye; i++)
    {
        pos.Y = i;
        FillConsoleOutputAttribute(_hcout,attr,len,pos,&crd);
    }
}

// Funkcja wypełnia zadany obszar okna konsoli
// podanym znakiem. Pozycja kursora oraz atrybuty
// zawarte w obszarze nie sš zmieniane.
//-----------------------------------------------
// c - znak do wypełnienia obszaru
// xb,yb - współrzędne lewego górnego narożnika
// xe,ye - współrzędne prawego dolnego narożnika
//-----------------------------------------------
void fillrectch(TCHAR c, int xb, int yb, int xe, int ye)
{
    CONSOLE_SCREEN_BUFFER_INFO cinf;
    COORD pos;
    DWORD crd;

    if(!GetConsoleScreenBufferInfo(_hcout, &cinf)) return;
    if((xb >= cinf.dwSize.X) || (yb >= cinf.dwSize.Y) ||
       (xe < xb) || (ye < yb) || (xe < 0) || (ye < 0)) return; // poza oknem
    if(xb < 0) xb = 0;
    if(yb < 0) yb = 0;
    if(xe >= cinf.dwSize.X) xe = cinf.dwSize.X - 1;
    if(ye >= cinf.dwSize.Y) ye = cinf.dwSize.Y - 1;
    DWORD len = xe - xb + 1;
    pos.X = xb;
    for(int i = yb; i <= ye; i++)
    {
        pos.Y = i;
        FillConsoleOutputCharacter(_hcout,c,len,pos,&crd);
    }
}

// Funkcja wypełnia zadany obszar okna konsoli
// podanym znakiem i atrybutem. Pozycja kursora
// nie jest zmieniana.
//----------------------------------------------
// c - znak do wypełnienia
// attr - atrybut do wypełnienia obszaru
// xb,yb - współrzędne lewego górnego narożnika
// xe,ye - współrzędne prawego dolnego narożnika
//----------------------------------------------
void fillrect(TCHAR c, WORD attr, int xb, int yb, int xe, int ye)
{
    CONSOLE_SCREEN_BUFFER_INFO cinf;
    COORD pos;
    DWORD crd;

    if(!GetConsoleScreenBufferInfo(_hcout, &cinf)) return;
    if((xb >= cinf.dwSize.X) || (yb >= cinf.dwSize.Y) ||
       (xe < xb) || (ye < yb) || (xe < 0) || (ye < 0)) return; // poza oknem
    if(xb < 0) xb = 0;
    if(yb < 0) yb = 0;
    if(xe >= cinf.dwSize.X) xe = cinf.dwSize.X - 1;
    if(ye >= cinf.dwSize.Y) ye = cinf.dwSize.Y - 1;
    DWORD len = xe - xb + 1;
    pos.X = xb;
    for(int i = yb; i <= ye; i++)
    {
        pos.Y = i;
        FillConsoleOutputCharacter(_hcout,c,len,pos,&crd);
        FillConsoleOutputAttribute(_hcout,attr,len,pos,&crd);
    }
}

// Funkcja rysuje prostokštnš ramkę
// type - okrela typ ramki. Dozwolone typy, to:
//        FRAME_EMPTY,FRAME_SINGLE,FRAME_DOUBLE,
//        FRAME_SOLID, FRAME_SHADED
// attr - okrela atrybut koloru dla ramki
// xb,yb - współrzędne lewego górnego wierzchołka
// xe,ye - współrzędne dolnego prawego wierzchołka
//------------------------------------------------
void frame(int type,int attr,int xb,int yb,int xe,int ye)
{
    TCHAR c;

    if((xb >= xe) || (yb >= ye)) return;
    switch(type) // lewy górny róg
    {
        case FRAME_EMPTY : c =  32; break;
        case FRAME_SINGLE: c = 218; break;
        case FRAME_DOUBLE: c = 201; break;
        case FRAME_SOLID : c = 219; break;
        case FRAME_SHADED: c = 177; break;
    }
    fillrect(c,attr,xb,yb,xb,yb);
    switch(type) // lewy dolny róg
    {
        case FRAME_SINGLE: c = 192; break;
        case FRAME_DOUBLE: c = 200; break;
    }
    fillrect(c,attr,xb,ye,xb,ye);
    switch(type) // prawy górny róg
    {
        case FRAME_SINGLE: c = 191; break;
        case FRAME_DOUBLE: c = 187; break;
    }
    fillrect(c,attr,xe,yb,xe,yb);
    switch(type) // prawy dolny róg
    {
        case FRAME_SINGLE: c = 217; break;
        case FRAME_DOUBLE: c = 188; break;
    }
    fillrect(c,attr,xe,ye,xe,ye);
    switch(type) // góra i dół
    {
        case FRAME_SINGLE: c = 196; break;
        case FRAME_DOUBLE: c = 205; break;
    }
    fillrect(c,attr,xb + 1,yb,xe - 1,yb);
    fillrect(c,attr,xb + 1,ye,xe - 1,ye);
    switch(type) // lewy i prawy
    {
        case FRAME_SINGLE: c = 179; break;
        case FRAME_DOUBLE: c = 186; break;
    }
    fillrect(c,attr,xb,yb + 1,xb,ye - 1);
    fillrect(c,attr,xe,yb + 1,xe,ye - 1);
}

// Funkcja rysuje prostokštnš ramkę z wypełnieniem
// type - okrela typ ramki. Dozwolone typy, to:
//   FRAME_EMPTY  - ramka zbudowana ze spacji
//   FRAME_SINGLE - ramka pojedyncza
//   FRAME_DOUBLE - ramka podwójna
//   FRAME_SOLID  - ramka wypełniona znakiem pełnym
//   FRAME_SHADED - ramka wypełniona znakiem cieniowanym
// attr - okrela atrybut koloru dla ramki i tła
// xb,yb - współrzędne lewego górnego wierzchołka
// xe,ye - współrzędne dolnego prawego wierzchołka
//------------------------------------------------
void fillframe(int type,int attr,int xb,int yb,int xe,int ye)
{
    frame(type,attr,xb,yb,xe,ye);
    fillrect(' ',attr,xb+1,yb+1,xe-1,ye-1);
}

// Funkcja przełšcza okno konsoli w tryb pełnoekranowy
// lub okienkowy. Pełny ekran ma 80 kolumn x 50 wierszy
// mode = true  - pełny ekran
// mode = false - okno
//-----------------------------------------------------
void fullscreen(bool mode)
{
    COORD dim;
    SetConsoleDisplayMode(_hcout, mode ? 1 : 2, &dim);
}

// Funkcja odczytuje atrybut z pozycji x,y ekranu konsoli
//-------------------------------------------------------
// x - pozycja w poziomie (0..79)
// y - pozycja w pionie   (0..49)
//-------------------------------------------------------
int getattrxy(int x, int y)
{
    COORD pos = {x,y};
    WORD attr;
    DWORD crd;

    ReadConsoleOutputAttribute(_hcout,&attr,1,pos,&crd);
    return attr;
}

// Funkcja odczytuje kod klawisza z bufora konsoli. Jeli bufor jest pusty, czeka na klawisz.
// Odczytany znak nie pojawia się na ekranie. Jeli nacinięto klawisz sterujšcy, to pierwsze
// wywołanie funkcji zawsze zwraca kod 0. Drugie wywołanie zwraca kod klawiaturowy danego
// klawisza sterujšcego.
//-------------------------------------------------------------------------------------------
int getch()
{
    INPUT_RECORD inp;
    DWORD nread;
    DWORD kbdmode;
    struct kbd *k;
    int keycode, state, c;

// Jeli poprzednie wywołanie zwróciło rozszerzony kod 0,
// zwracamy kod klawiaturowy tego klawisza.

    if(_cextend != -1)
    {
        c = _cextend;
        _cextend = -1; // Resetujemy znacznik
        return c;      // Zwracamy rozszerzony kod klawiaturowy
    }

// Pobieramy tryb pracy konsoli, a następnie ustawiamy go w tryb binarny.
// Robimy to, aby zapobiec przetwarzaniu przez Windows klawiszy sterujšcych
// np. Ctrl-C lub Cntrl-S.

    if(!GetConsoleMode(_hcin,&kbdmode)) return -1;
    if(!SetConsoleMode(_hcin,0))        return -1;

// Pobieramy zdarzenia klawiatury, aż napotkamy właciwe

    for (;;)
    {
        if(!ReadConsoleInput(_hcin, &inp, 1, &nread))
        {
            c = -1; break;
        }
        else if((inp.EventType == KEY_EVENT) && inp.Event.KeyEvent.bKeyDown)
        {
            keycode = inp.Event.KeyEvent.wVirtualKeyCode;
            state   = inp.Event.KeyEvent.dwControlKeyState;
            if((state & NUMLOCK_ON) && (keycode == VK_DELETE)) keycode = VK_DECIMAL;

// Poszukujemy wirtualnego kodu klawisza w tablicy. Ignorujemy
// nierozpoznane klawisze

            for(k = &kbdtab[0]; keycode != k->keycode && k->keycode != -1; k++) ;
            if(k->keycode == -1) continue; // wartoć nieobecna w tablicy

// Sprawdzamy stan klawiszy sterujšcych. ALT posiada najwyższy priorytet
// następnie Ctrl i Shift. W zależnoci od stanu tych klawiszy
// wybieramy odpowiedniš pozycję w tablicy

            if(state & (RIGHT_ALT_PRESSED | LEFT_ALT_PRESSED))        c = k->alt;
            else if(state & (RIGHT_CTRL_PRESSED | LEFT_CTRL_PRESSED)) c = k->ctrl;
            else if(state & SHIFT_PRESSED)                            c = k->shift;
            else
            {

// Jeli jest to klawisz znakowy, wykorzystujemy kod ASCII dostarczony
// przez Windows i uwzględniamy stan CapsLock.

                if(keycode >= 'A' && keycode <= 'Z')
                    c = inp.Event.KeyEvent.uChar.AsciiChar;
                else
                    c = k->normal;
            }
            if(c == -1) continue; // brak odpowiednika BIOS

// Jeli jest to klawisz rozszerzony, zapamiętujemy jego kod
// i zwracamy 0.  Przy następnym wywołaniu zostanie zwrócona
// wartoć klawisza.

            if(ISEXT(c))
            {
                _cextend = EXTVAL(c);
                c = 0;
            }
            break;
        }
    }

// Przywracamy normalny tryb pracy konsoli

    if(!SetConsoleMode(_hcin,kbdmode)) return -1;
    return c;
}

// Funkcja odczytuje znak z pozycji x,y ekranu konsoli
//----------------------------------------------------
// x - pozycja w poziomie (0..79)
// y - pozycja w pionie   (0..49)
//----------------------------------------------------
char getchxy(int x, int y)
{
    COORD pos = {x,y};
    TCHAR c;
    DWORD crd;

    ReadConsoleOutputCharacter(_hcout,&c,1,pos,&crd);
    return c;
}

// Funkcja tworzy bufor na treć okna konsoli.
// Następnie umieszcza w tym buforze zawartoć
// prostokštnego obszaru konsoli i zwraca
// wskazanie do bufora.
// xb,yb - współrzędne lewego górnego rogu
// xe,ye - współrzędna prawego dolnego rogu
//--------------------------------------------
WORD * getrect(int xb, int yb, int xe, int ye)
{
    CONSOLE_SCREEN_BUFFER_INFO cinf;
    COORD pos;
    DWORD crd;

    if(!GetConsoleScreenBufferInfo(_hcout, &cinf)) return NULL;
    if((xb >= cinf.dwSize.X) || (yb >= cinf.dwSize.Y) ||
       (xe < xb) || (ye < yb) || (xe < 0) || (ye < 0)) return NULL; // poza oknem
    if(xb < 0) xb = 0;
    if(yb < 0) yb = 0;
    if(xe >= cinf.dwSize.X) xe = cinf.dwSize.X - 1;
    if(ye >= cinf.dwSize.Y) ye = cinf.dwSize.Y - 1;
    DWORD len_x = xe - xb + 1;
    WORD * buf = new(WORD[4 + 2 * len_x * (ye - yb + 1)]);
    if(buf)
    {
        buf[0] = xb; buf[1] = yb; buf[2] = xe; buf[3] = ye; // współrzędne obszaru
        int j = 0;
        for(int i = yb; i <= ye; i++)
        {
            pos.X = xb; pos.Y = i;
            ReadConsoleOutputCharacter(_hcout,(CHAR *)(buf+4+(j++)*len_x),len_x,pos,&crd);
            ReadConsoleOutputAttribute(_hcout,buf+4+(j++)*len_x,len_x,pos,&crd);
        }
    }
    return buf;
}

// Funkcja umieszcza kursor na zadanej pozycji
//--------------------------------------------
// x - pozycja w poziomie (0..79)
// y - pozycja w pionie   (0..49)
//--------------------------------------------
void gotoxy(int x, int y)
{
    COORD cpos = {x,y};
    SetConsoleCursorPosition(_hcout, cpos);
}

// Funkcja rozjania kolor znaków
//-------------------------------
void highvideo()
{
    _txattr = _txattr | 0x8;
    SetConsoleTextAttribute(_hcout,_txattr);
}

// Funkcja sprawdza, czy użytkownik nacisnšł jaki
// klawisz na klawiaturze. Jeli tak, zwraca true.
//------------------------------------------------
bool kbhit()
{
    DWORD en, enr; // liczba zdarzeń oraz liczba odczytanych zdarzeń

    GetNumberOfConsoleInputEvents(_hcin, &en);
    INPUT_RECORD * ir = new INPUT_RECORD[en];  // przydzielamy pamięć
    PeekConsoleInput(_hcin, ir, en, &enr);     // odczytujemy zdarzenia

    for(DWORD i = 0; i < enr; i++)               // przeglšdamy zdarzenia
    {
        if(ir[i].EventType & KEY_EVENT &&
           ir[i].Event.KeyEvent.bKeyDown)        // jeli zdarzenie klawisza!
        {                                        // ale nie kontrolnego!
            if(ir[i].Event.KeyEvent.wVirtualKeyCode != VK_CONTROL &&
               ir[i].Event.KeyEvent.wVirtualKeyCode != VK_MENU &&
               ir[i].Event.KeyEvent.wVirtualKeyCode != VK_SHIFT)
            {
                delete ir; return true;              // klawisz w buforze konsoli
            }
        }
    }
    delete ir; return false;                   // bufor konsoli jest pusty
}

// Funkcja przyciemnia kolor znaków
//---------------------------------
void lowvideo()
{
    _txattr = _txattr & 0xf7;
    SetConsoleTextAttribute(_hcout,_txattr);
}

// Funkcja zapisuje podany atrybut na pozycji x,y.
// Pozycja kursora i znak na tej pozycji nie sš zmieniane
//-------------------------------------------------------
// attr - atrybut koloru
// x - pozycja w poziomie (0..79)
// y - pozycja w pionie   (0..49)
//-------------------------------------------------------
void putattrxy(WORD attr, int x, int y)
{
    COORD pos = {x,y};
    DWORD cwrt;

    WriteConsoleOutputAttribute(_hcout,&attr,1,pos,&cwrt);
}

// Funkcja zapisuje podany znak na pozycji x,y.
// Pozycja kursora i atrybut pozycji nie sš zmieniane.
//----------------------------------------------------
// c - znak ASCII do zapisu
// x - pozycja w poziomie (0..79)
// y - pozycja w pionie   (0..49)
//----------------------------------------------------
void putchxy(TCHAR c, int x, int y)
{
    COORD pos = {x,y};
    DWORD cwrt;

    WriteConsoleOutputCharacter(_hcout,&c,1,pos,&cwrt);
}

// Funkcja odtwarza prostokštny obszar ekranu
// zapamiętany w buforze przez getrect(). Bufor
// jest zwalniany do puli pamięci systemu.
// buf - wskanik bufora z treciš obszaru
//---------------------------------------------
void putrect(WORD * buf)
{
    COORD pos;
    DWORD crd;

    if(!buf) return; // na wypadek, gdy bufor jest pusty!
    DWORD len_x = buf[2] - buf[0] + 1;
    int j = 0;
    for(int i = buf[1]; i <= buf[3]; i++)
    {
        pos.X = buf[0]; pos.Y = i;
        WriteConsoleOutputCharacter(_hcout,(CHAR *)(buf+4+(j++)*len_x),len_x,pos,&crd);
        WriteConsoleOutputAttribute(_hcout,buf+4+(j++)*len_x,len_x,pos,&crd);
    }
    delete buf;
}

// Funkcja zapisuje podany znak i atrybut na pozycji x,y.
// Pozycja kursora pozycji nie jest zmieniana.
//-------------------------------------------------------
// c - znak ASCII do zapisu
// attr - atrybut koloru
// x - pozycja w poziomie (0..79)
// y - pozycja w pionie   (0..49)
//-------------------------------------------------------
void putxy(TCHAR c, WORD attr, int x, int y)
{
    COORD pos = {x,y};
    DWORD cwrt;

    WriteConsoleOutputCharacter(_hcout,&c,1,pos,&cwrt);
    WriteConsoleOutputAttribute(_hcout,&attr,1,pos,&cwrt);
}

// Funkcja przesuwa zawartoć prostokštnego obszaru
// w wyznaczonym kierunku o zadanš liczbę pozycji.
// Powstały pusty obszar wypełniony zostaje spacjami
// w aktualnym kolorze tła.
//------------------------------------------------------------------------
// dir - kierunek przesuwu okna. Dozwolone sš następujšce kierunki:
//   SCROLL_UP    - przesuw w górę
//   SCROLL_RIGHT - przesuw w prawo
//   SCROLL_DOWN  - przesuw w dół
//   SCROLL_LEFT  - przesuw w lewo
// howfar - okrela liczbę kolumn lub wierszy, o które będzie przesunięcie
// xb,yb - współrzędna lewego górnego wierzchołka
// xe,ye - współrzędna prawego dolnego wierzchołka
//------------------------------------------------------------------------
void scrollrect(int dir, unsigned howfar, int xb, int yb, int xe, int ye)
{
    SMALL_RECT r,cr;
    COORD dest;
    CONSOLE_SCREEN_BUFFER_INFO cinf;

    if(!GetConsoleScreenBufferInfo(_hcout, &cinf)) return;
    if((xb >= cinf.dwSize.X) || (yb >= cinf.dwSize.Y) || (dir > 3) || !howfar ||
       (xe < xb) || (ye < yb) || (xe < 0) || (ye < 0)) return; // poza oknem
    if(xb < 0) xb = 0;
    if(yb < 0) yb = 0;
    if(xe >= cinf.dwSize.X) xe = cinf.dwSize.X - 1;
    if(ye >= cinf.dwSize.Y) ye = cinf.dwSize.Y - 1;
    dest.X = r.Left = cr.Left  = xb;
    dest.Y = r.Top  = cr.Top   = yb;
    r.Right  = cr.Right  = xe;
    r.Bottom = cr.Bottom = ye;
    switch(dir)
    {
        case SCROLL_UP    : r.Top    += howfar; break;
        case SCROLL_RIGHT : r.Right  -= howfar; dest.X += howfar; break;
        case SCROLL_DOWN  : r.Bottom -= howfar; dest.Y += howfar; break;
        case SCROLL_LEFT  : r.Left   += howfar; break;
    }
    CHAR_INFO chinf = {{' '},_txattr};
    ScrollConsoleScreenBuffer(_hcout,&r,&cr,dest,&chinf);
}

// Funkcja ustawia atrybut koloru
//-------------------------------
// attr - nowy atrybut
//-------------------------------
void textattr(int attr)
{
    _txattr = attr & 0xff;
    SetConsoleTextAttribute(_hcout,_txattr);
}

// Funkcja ustawia kolor tła tekstu
//---------------------------------
// attr - kod koloru tekstu
//---------------------------------
void textbackground(int attr)
{
    _txattr = (_txattr & 0xf) | ((attr & 0xf) << 4);
    SetConsoleTextAttribute(_hcout,_txattr);
}

// Funkcja ustawia kolor tekstu i tła
//--------------------------------------------
// attr - kod atrybutu dla koloru tekstu i tła
//--------------------------------------------
void textcolor(int attr)
{
    _txattr = (_txattr & 0xf0) | (attr & 0xf);
    SetConsoleTextAttribute(_hcout,_txattr);
}

// Funkcja odczytuje bieżšcš pozycję x kursora
//--------------------------------------------
int wherex()
{
    CONSOLE_SCREEN_BUFFER_INFO sinf;

    GetConsoleScreenBufferInfo(_hcout,&sinf);
    return sinf.dwCursorPosition.X;
}

// Funkcja odczytuje bieżšcš pozycję y kursora
//--------------------------------------------
int wherey()
{
    CONSOLE_SCREEN_BUFFER_INFO sinf;

    GetConsoleScreenBufferInfo(_hcout,&sinf);
    return sinf.dwCursorPosition.Y;
}
