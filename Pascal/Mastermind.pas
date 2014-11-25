Program MasterMind;

Uses
  Crt;

Var
   LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla:Integer;

Procedure Menu(Var LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla:Integer); Forward;

Procedure Opcje (Var LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla:Integer); Forward;

Function Prawda(KodPr:String):Boolean;

Var
   Dlugosc,i,j:Integer;

Begin
   Dlugosc:=Length(KodPr);
   Prawda:=True;
   For i:=1 To Dlugosc Do
      Begin
         For j:=1 To Dlugosc Do
            Begin
               If (i<>j) And (KodPr[i]=KodPr[j]) Then Prawda:=False;
            End;
      End;
End;

Function Prawda2(KodPr:String):Boolean;

Var
   Dlugosc,i,p:Integer;

Begin
   Dlugosc:=Length(KodPr);
   p:=0;
   For i:=1 To Dlugosc Do
      Begin
         If (KodPr[i]='A') Or (KodPr[i]='B') Or (KodPr[i]='C') Or (KodPr[i]='D') Or (KodPr[i]='E') Or (KodPr[i]='F') Then p:=p+1;
      End;
   If p=4 Then Prawda2:=True
   Else Prawda2:=False;
End;

Procedure Tryb1(LiczbaRuchow,PoziomTrudnosci:Integer);

Var
   KodPr,CopyKodPr,KodOdp,Odpowiedz:String;
   i,j,BiezacaRunda,KodPrLiczba,Wybor:Integer;

Begin
   ClrScr;
   Randomize;
   KodPr:='1234';
   If PoziomTrudnosci=1 Then
      Begin
         Repeat
            For i:=1 To 4 Do
               Begin
                  KodPrLiczba:=Random(6);
                  Case KodPrLiczba Of
                     0 : KodPr[i]:='A';
                     1 : KodPr[i]:='B';
                     2 : KodPr[i]:='C';
                     3 : KodPr[i]:='D';
                     4 : KodPr[i]:='E';
                     5 : KodPr[i]:='F';
                  Else End;
               End;
         Until Prawda(KodPr);
      End;
   If PoziomTrudnosci=2 Then
      Begin
         For i:=1 To 4 Do
            Begin
               KodPrLiczba:=Random(6);
               Case KodPrLiczba Of
                  0 : KodPr[i]:='A';
                  1 : KodPr[i]:='B';
                  2 : KodPr[i]:='C';
                  3 : KodPr[i]:='D';
                  4 : KodPr[i]:='E';
                  5 : KodPr[i]:='F';
               Else End;
            End;
      End;
   For BiezacaRunda:=1 To LiczbaRuchow Do
      Begin
         Repeat
            Writeln('Runda  : ',BiezacaRunda,'. Podaj kod');
            GoToXY (25,WhereY);
            Readln (KodOdp);
            GoToXY (35,WhereY);
         Until Length(KodOdp)=4;
         KodOdp:=UpCase(KodOdp);
         CopyKodPr:=KodPr;
         Odpowiedz:='';
         For i:=1 To 4 Do
            Begin
               If CopyKodPr[i]=KodOdp[i] then
                  Begin
                     Odpowiedz:=Odpowiedz+'x';
                     CopyKodPr[i]:='+';
                     KodOdp[i]:='-';
                  End;
            End;
         For i:=1 To 4 Do
            Begin
               If CopyKodPr[i]<>'+' Then
                  Begin
                     For j:=1 To 4 Do
                        Begin
                           If CopyKodPr[i]=KodOdp[j] Then
                              Begin
                                 Odpowiedz:=Odpowiedz+'o';
                                 KodOdp[j]:='-';
                                 Break;
                              End;
                        End;
                  End;
            End;
         Writeln ('Kod odpowiedzi : ',Odpowiedz);
         If Odpowiedz='xxxx' Then
            Begin
               Writeln;
               Writeln ('WYGRALES!!!!');
               Readln;
               Break;
            End;
      End;
   If Odpowiedz<>'xxxx' Then
      Begin
         Writeln;
         Writeln ('PRZEGRALES!!!!');
         Writeln ('Prawidlowy kod : ',KodPr);
         Readln;
      End;
   Repeat
      ClrScr;
      Writeln ('Co chcesz teraz zrobic?');
      Writeln ('(1) - Menu');
      Writeln ('(2) - Zagraj pomownie');
      Writeln ('(3) - Zakoncz');
      Readln  (Wybor);
   Until (Wybor>0) And (Wybor<4);
   Case Wybor Of
      1 : Menu(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      2 : Tryb1(LiczbaRuchow,PoziomTrudnosci);
      3 : Halt;
   Else End;
End;

Procedure Tryb2(LiczbaRuchow,PoziomTrudnosci:Integer);

Var
   KodPr,CopyKodPr,KodOdp,Odpowiedz:String;
   i,j,BiezacaRunda,Wybor:Integer;

Begin
   ClrScr;
   Randomize;
   KodPr:='1234';
   If PoziomTrudnosci=1 Then
      Begin
         Repeat
            Begin
               ClrScr;
               Writeln ('Wpisz kod mozesz uzywac tylko liter A,B,C,D,E,F. LITERY NIE MOGA SIE POWTARZAC!!');
               Readln  (KodPr);
               KodPr:=UpCase(KodPr);
            End;
         Until (Prawda(KodPr)) And (Prawda2(KodPr));
      End;
   If PoziomTrudnosci=2 Then
      Begin
         Repeat
            ClrScr;
            Writeln ('Wpisz kod mozesz uzywac tylko liter A,B,C,D,E,F.');
            Readln  (KodPr);
            KodPr:=UpCase(KodPr);
         Until Prawda2(KodPr);
      End;
   KodPr:=UpCase(KodPr);
   ClrScr;
   For BiezacaRunda:=1 To LiczbaRuchow Do
      Begin
         Repeat
            Writeln('Runda  : ',BiezacaRunda,'. Podaj kod');
            GoToXY (25,WhereY);
            Readln (KodOdp);
            GoToXY (35,WhereY);
         Until Length(KodOdp)=4;
         KodOdp:=UpCase(KodOdp);
         CopyKodPr:=KodPr;
         Odpowiedz:='';
         For i:=1 To 4 Do
            Begin
               If CopyKodPr[i]=KodOdp[i] then
                  Begin
                     Odpowiedz:=Odpowiedz+'x';
                     CopyKodPr[i]:='+';
                     KodOdp[i]:='-';
                  End;
            End;
         For i:=1 To 4 Do
            Begin
               If CopyKodPr[i]<>'+' Then
                  Begin
                     For j:=1 To 4 Do
                        Begin
                           If CopyKodPr[i]=KodOdp[j] Then
                              Begin
                                 Odpowiedz:=Odpowiedz+'o';
                                 KodOdp[j]:='-';
                                 Break;
                              End;
                        End;
                  End;
            End;
         Writeln ('Kod odpowiedzi : ',Odpowiedz);
         If Odpowiedz='xxxx' Then
            Begin
               Writeln;
               Writeln ('WYGRALES!!!!');
               Readln;
               Break;
            End;
      End;
   If Odpowiedz<>'xxxx' Then
      Begin
         Writeln;
         Writeln ('PRZEGRALES!!!!');
         Writeln ('Prawidlowy kod : ',KodPr);
         Readln;
      End;
   Repeat
      ClrScr;
      Writeln ('Co chcesz teraz zrobic?');
      Writeln ('(1) - Menu');
      Writeln ('(2) - Zagraj ponownie');
      Writeln ('(3) - Zakoncz');
      Readln  (Wybor);
   Until (Wybor>0) And (Wybor<4);
   Case Wybor Of
      1 : Menu(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      2 : Tryb2(LiczbaRuchow,PoziomTrudnosci);
      3 : Halt;
   Else End;
End;

Procedure ZasadyGry;

Var Wybor:Integer;

Begin
   ClrScr;
   CursorOff;
   Writeln ('Zasady gry MasterMind : ');
   Writeln;
   Writeln ('1. Gracz nr 1 (w przypadku, gdy gra sie na 1 gracza jest nim komputer).');
   Writeln (' wybiera kod zbudowany z czterech liter wybranych sposrod 6 (A,B,C,D,E,F).');
   Writeln ('Litery w kodzie moga sie powtarzac (Tylko wtedy, gdy gra sie na trudnym poziomie gry).');
   Writeln ('Jezeli grasz na latwym poziomie gry litery w kodzie nie moga sie powtarczac.');
   Writeln;
   Writeln ('2. Gracz nr 2 wprowadza swoj kod w celu odganiecia kodu przeciwnika.');
   Writeln ('UWAGA - Jezeli kod gracza nr 2 nie bedzie mial 4 znakow nie zostanie zaliczony');
   Writeln ('UWAGA - Jezeli kod gracza nr 2 bedzie zawieral nieprawidolwe znaki zostanie uznany za nieprawidlowy');
   Writeln ('Gracz nr 2 ma ograniczony czas na odgadniecie kodu przeciwnika.');
   Writeln ('Czas ten liczony jest w rundach (domyslnie jest to 6 rund, lecz mozna to zmienic w opcjach).');
   Writeln ('Jezeli gracz nr 2 w swoim kodzie bedzie mial choc 1 z znakow z kodu gracza');
   Writeln ('otrzyma odpowiedz : ');
   Writeln ('(x) - Pewien znak w kodach obu graczy jest taki sam i jest na takim samym miejscu.');
   Writeln ('(o) - Pewien znak w kodach obu graczy jest taki sam.');
   Writeln;
   Writeln ('3. Jezeli gracz nr 2 odgadnie kod przeciwnika przed uplywem limitu rund wygrywa');
   Readln;
   CursorOn;
   Repeat
      ClrScr;
      Writeln ('Co chcesz teraz zrobic?');
      Writeln ('(1) - Menu');
      Writeln ('(2) - Opcje');
      Writeln ('(3) - Powtorz zasady');
      Writeln ('(4) - Zakoncz');
      Readln  (Wybor);
   Until (Wybor>0) And (Wybor<5);
   Case Wybor Of
      1 : Menu(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      2 : Opcje(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      3 : ZasadyGry;
      4 : Halt;
   Else End;
End;

Procedure LiczbaR(Var LiczbaRuchow:Integer);

Var
   Wybor:Integer;
Begin
   Repeat
      ClrScr;
      Writeln ('Podaj nowa mozliwa liczbe ruchow - MAX to 15');
      Readln  (LiczbaRuchow);
   Until (LiczbaRuchow>0) And (LiczbaRuchow<16);
   Repeat
      ClrScr;
      Writeln ('Co chcesz teraz zrobic?');
      Writeln ('(1) - Menu');
      Writeln ('(2) - Opcje');
      Writeln ('(3) - Powrot');
      Writeln ('(4) - Zakoncz');
      Readln  (Wybor);
   Until (Wybor>0) And (Wybor<5);
   Case Wybor Of
      1 : Menu(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      2 : Opcje(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      3 : LiczbaR(LiczbaRuchow);
      4 : Halt;
   Else End;
End;

Procedure PoziomT(Var PoziomTrudnosci:Integer);

Var
   Wybor:Integer;

Begin
   Repeat
      ClrScr;
      Writeln ('Podaj poziom trudnosci');
      Writeln ('(1) - Latwy');
      Writeln ('(2) - Trudny');
      Readln  (PoziomTrudnosci);
   Until (PoziomTrudnosci=1) Or (PoziomTrudnosci=2);
   Repeat
      ClrScr;
      Writeln ('Co chcesz teraz zrobic?');
      Writeln ('(1) - Menu');
      Writeln ('(2) - Opcje');
      Writeln ('(3) - Powrot');
      Writeln ('(4) - Zakoncz');
      Readln  (Wybor);
   Until (Wybor>0) And (Wybor<5);
   Case Wybor Of
      1 : Menu(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      2 : Opcje(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      3 : PoziomT(PoziomTrudnosci);
      4 : Halt;
   Else End;
End;

Procedure KolorR(Var Kolor:Integer);

Var
   Wybor:Integer;

Begin
   Repeat
      ClrScr;
      Writeln ('Podaj kolor :');
      Writeln ('(1) - Niebieski');
      Writeln ('(2) - Zielony');
      Writeln ('(3) - Cyjan');
      Writeln ('(4) - Czerwony');
      Writeln ('(5) - Bialy');
      Writeln ('(6) - Zolty');
      Writeln ('(7) - Kolor domyslny');
      Readln  (Kolor);
   Until (Kolor>0) And (Kolor<8);
   Case Kolor Of
      1 : Kolor:=9;
      2 : Kolor:=10;
      3 : Kolor:=11;
      4 : Kolor:=12;
      5 : Kolor:=15;
      6 : Kolor:=14;
      7 : Kolor:=7;
   Else End;
   TextColor(Kolor);
   Repeat
      ClrScr;
      Writeln ('Co chcesz teraz zrobic?');
      Writeln ('(1) - Menu');
      Writeln ('(2) - Opcje');
      Writeln ('(3) - Powrot');
      Writeln ('(4) - Zakoncz');
      Readln  (Wybor);
   Until (Wybor>0) And (Wybor<5);
   Case Wybor Of
      1 : Menu(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      2 : Opcje(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      3 : KolorR(Kolor);
      4 : Halt;
   Else End;
End;

Procedure KolorT(Var KolorTla:Integer);

Var
   Wybor:Integer;

Begin
   Repeat
      ClrScr;
      Writeln ('Podaj kolor tla');
      Writeln ('(1) - Niebieski');
      Writeln ('(2) - Zielony');
      Writeln ('(3) - Cyjan');
      Writeln ('(4) - Czerwony');
      Writeln ('(5) - Bialy');
      Writeln ('(6) - Zolty');
      Writeln ('(7) - Kolor domyslny');
      Readln  (KolorTla);
   Until (KolorTla>0) And (KolorTla<8);
   Case KolorTla Of
      1 : KolorTla:=9;
      2 : KolorTla:=10;
      3 : KolorTla:=11;
      4 : KolorTla:=12;
      5 : KolorTla:=15;
      6 : KolorTla:=14;
      7 : KolorTla:=0;
   Else End;
   TextBackground(KolorTla);
   Repeat
      ClrScr;
      Writeln ('Co chcesz teraz zrobic?');
      Writeln ('(1) - Menu');
      Writeln ('(2) - Opcje');
      Writeln ('(3) - Powrot');
      Writeln ('(4) - Zakoncz');
      Readln  (Wybor);
   Until (Wybor>0) And (Wybor<5);
   Case Wybor Of
      1 : Menu(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      2 : Opcje(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      3 : KolorT(KolorTla);
      4 : Halt;
   Else End;
End;

Procedure Domyslne(Var LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla:Integer);

Var
   Wybor:Integer;

Begin
   LiczbaRuchow:=6;
   PoziomTrudnosci:=1;
   Kolor:=7;
   KolorTla:=0;
   TextColor(Kolor);
   TextBackground(KolorTla);
   Repeat
      ClrScr;
      Writeln ('Co chcesz teraz zrobic?');
      Writeln ('(1) - Menu');
      Writeln ('(2) - Opcje');
      Writeln ('(3) - Zakoncz');
      Readln  (Wybor);
   Until (Wybor>0) And (Wybor<4);
   Case Wybor Of
      1 : Menu(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      2 : Opcje(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      3 : Halt;
   Else End;
End;

Procedure Opcje (Var LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla:Integer);

Var
   Wybor:Integer;

Begin
   Repeat
      ClrScr;
      Writeln ('Co chcialbys zmienic?');
      Writeln ('(1) - Liczba mozliwych ruchow');
      Writeln ('(2) - Poziom trudnosci');
      Writeln ('(3) - Kolor tekstu');
      Writeln ('(4) - Kolor tla');
      Writeln ('(5) - Przywroc ustawienia domyslne');
      Writeln ('(6) - Powrot do menu');
      Writeln ('(7) - Zakoncz');
      Readln  (Wybor);
   Until (Wybor>0) And (Wybor<8);
   Case Wybor Of
      1 : LiczbaR (LiczbaRuchow);
      2 : PoziomT (PoziomTrudnosci);
      3 : KolorR  (Kolor);
      4 : KolorT  (KolorTla);
      5 : Domyslne(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      6 : Menu    (LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      7 : Halt;
   Else End;
End;

Procedure Menu(Var LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla:Integer);

Var
   Wybor:Integer;

Begin
   Repeat
      ClrScr;
      Writeln ('Podaj co chcesz zrobic');
      Writeln ('(1) - Tryb gry dla jednego gracza');
      Writeln ('(2) - Tryb gry dla dwoch graczy');
      Writeln ('(3) - Opcje');
      Writeln ('(4) - Zasady gry "MasterMind"');
      Writeln ('(5) - Zakoncz');
      Readln  (Wybor);
   Until (Wybor>0) And (Wybor<6);
   Case Wybor Of
      1 : Tryb1(LiczbaRuchow,PoziomTrudnosci);
      2 : Tryb2(LiczbaRuchow,PoziomTrudnosci);
      3 : Opcje(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
      4 : ZasadyGry;
      5 : Halt;
   Else End;
End;

Begin
   LiczbaRuchow:=6;
   PoziomTrudnosci:=1;
   Kolor:=7;
   KolorTla:=0;
   Menu(LiczbaRuchow,PoziomTrudnosci,Kolor,KolorTla);
End.
