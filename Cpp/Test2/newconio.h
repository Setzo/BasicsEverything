// I Liceum Ogólnokszta?c?ce
// im. K. Brodzi?skiego
// w Tarnowie
//----------------------------------------------------------------
// Ko?o informatyczne 2006/7
//----------------------------------------------------------------
// plik nag?ówkowy dla procedur obs?ugi konsoli znakowej w Windows
//                 (C)2006 mgr Jerzy Wa?aszek
//----------------------------------------------------------------

#if !defined(__NEW_CON_FUNCT__)
#define __NEW_CON_FUNCT__

#include <windows.h>
#include <string>

using namespace std;

#if !defined(__COLORS)
#define __COLORS

enum COLORS
{
    BLACK,          // ciemne kolory
    BLUE,
    GREEN,
    CYAN,
    RED,
    MAGENTA,
    BROWN,
    LIGHTGRAY,
    DARKGRAY,       // jasne kolory
    LIGHTBLUE,
    LIGHTGREEN,
    LIGHTCYAN,
    LIGHTRED,
    LIGHTMAGENTA,
    YELLOW,
    WHITE
};

#endif

#if !defined(__FRAMES_TYPES__)
#define __FRAMES_TYPES__

enum FRAMES
{
    FRAME_EMPTY,
    FRAME_SINGLE,
    FRAME_DOUBLE,
    FRAME_SOLID,
    FRAME_SHADED
};

#endif

#if !defined(__SCROLL_DIRECTION__)
#define __SCROLL_DIRECTION__

enum SCROLLS
{
    SCROLL_UP,
    SCROLL_RIGHT,
    SCROLL_DOWN,
    SCROLL_LEFT
};

#endif

void _cinit(void);
string _pl(string);
void center(int,string);
void clrscr(void);
void cursoroff(void);
void cursoron(void);
void delay(int);
void fillrectattr(WORD,int,int,int,int);
void fillrectch(TCHAR,int,int,int,int);
void fillrect(TCHAR,WORD,int,int,int,int);
void frame(int,int,int,int,int,int);
void fillframe(int,int,int,int,int,int);
void fullscreen(bool);
int  getattrxy(int,int);
int  getch(void);
char getchxy(int,int);
WORD * getrect(int,int,int,int);
void gotoxy(int,int);
void highvideo(void);
bool kbhit(void);
void lowvideo(void);
void putattrxy(WORD,int,int);
void putchxy(TCHAR,int,int);
void putrect(WORD *);
void putxy(TCHAR,WORD,int,int);
void scrollrect(int,unsigned,int,int,int,int);
void textattr(int);
void textbackground(int);
void textcolor(int);
int  wherex(void);
int  wherey(void);

#endif //__NEW_CON_FUNCT__
