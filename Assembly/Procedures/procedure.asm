
extrn ExitProcess: PROC
extrn printf: PROC
extrn scanf: PROC


.data

fmtScanf db '%d', 0

fmtPrintf db '%d', 13, 10, 0

l1 WORD 0
l2 WORD 0

.code

Start PROC
    
    ; STOS /////////////////////////////
    ; Zajęcie 0x80 bajtów na stosie dla przyszłego użycia
    sub rsp, 80h
    
    ; WCZYTYWANIE //////////////////
    ; Przypisanie adresu na l1 do rdx'a, dla przyszłego użycia przez
    ; przypisanie w ten adres wczytanej wartości.
    lea rdx, l1
    push rdx
    call wczytaj
    
    lea rdx, l2
    push rdx
    call wczytaj
    
    ; DODAWANIE //////////////////////
    ; Wrzucenie l1 i l2 na stos jako parametr dla procedury dodaj,
    ; która doda do siebie obie wartości i zwróci wartość wrzucając
    ; na stos wynik.
    push l1
    push l2
    call dodaj
    
    ; WYPISYWANIE ///////////////////
    ; Wywołanie procedury, która wypiszę wynik dodawania. Procedura
    ; ta przyjmuje jeden parametr poprzez stos. Jako że wcześniej wywołaliśmy 
    ; procedurę dodaj, to nie musimy nic wrzucać na stos, bo poprzednia
    ; procedura swój wynik dodała na stos, przez co wystarczy przeczytać ze
    ; stosu wartość.
    call wypisz
    
    call ExitProcess
    
; Procedura wczytująca liczbę do adresu podanego jej poprzez stos.
wczytaj:
    
    ; Wywołanie procedury poprzez instrukcję CALL zapisuje wskaźnik
    ; powrotu na stosie. Zdejmując go ze stosu na rejestr R13 jesteśmy
    ; w stanie uzyskać dostęp do pozostałych parametrów na stosie.
    pop r13
    
    ; Przypisanie adresu na format do wczytywania liczb do rcx'a,
    ; by później używać scanf'a.
    lea rcx, fmtScanf
    ; Pobranie ze stosu przekazanej wartości, do której mamy zapisać
    ; wynik pobierania. W naszym przypadku będzie to adres na l1 lub l2.
    pop rdx
    
    ; Wywołanie funkcji pobierającej dane od użytkownika.
    call scanf
    
    ; Zwrócenie wskaźnika powrotu na stos, tak by RET wiedział gdzie
    ; ma wrócić.
    push r13
    ret

; Procedura wypisująca liczbę podaną jej przez stos.
wypisz:
    
    ; Opisane wyżej
    pop r13
    
    lea rcx, fmtPrintf
    pop rdx
    
    call printf
    pop rcx
    
    push r13
    ret
    
; Procedura dodająca dwie liczby podane jej przez stos
; i zwracająca wynik na stosie.
dodaj:
    
    pop r13
    
    ; Zerowanie rejestrów, by być pewnym, że po wykonaniu
    ; POP CX i POP DX nic nie będzie w pozostałej części rejestrów
    ; RCX i RDX.
    xor rcx, rcx
    xor rdx, rdx
    
    ; Wczytanie parametrów ze stosu.
    pop cx
    pop dx
    
    ; Dodanie RCX'a do RDX'a.
    add rdx, rcx
    ; Zwrócenie wyniku na stos.
    push rdx
    
    push r13
    ret
    
Start ENDP

End