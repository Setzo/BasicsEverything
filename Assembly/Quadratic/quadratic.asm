extrn ExitProcess: PROC
extrn printf: PROC
 
 
.data
 
    fmtPrintf db '%s%.10f', 13, 10, 0
    fmtPrintfM db 'Delta mniejsza od zera, brak pierwiastkow', 13, 10, 0

    aLabel db 'a: ', 0
    bLabel db 'b: ', 0
    cLabel db 'c: ', 0
    deltaLabel db 'delta: ', 0
    x1Label db 'x1: ', 0
    x2Label db 'x2: ', 0
             
    aValue QWORD +1.0
    bValue QWORD +6.0
    cValue QWORD +9.0
 
    minusOne QWORD -1.0
    two QWORD +2.0
    four QWORD +4.0
 
.code
 
Start PROC
 
    sub rsp, 80h
    
    ; Wrzucenie na stos wartości współczynnika a, oraz
    ; adresu na napis 'a: '. Następnie wywołanie procedury
    ; wypisującej podane jej przez stos parametry.
    ; Dla każdego innego współczynnika zostaną wykonane
    ; podobne instrukcje.
    mov rdx, aValue
    push rdx
    lea rax, aLabel
    push rax
    call wypisz
 
    ; Wyjaśnione powyzej.
    mov rdx, bValue
    push rdx
    lea rax, bLabel
    push rax
    call wypisz
 
    ; Wyjaśnione powyzej.
    mov rdx, cValue
    push rdx
    lea rax, cLabel
    push rax
    call wypisz
 
    ; Wywołanie fukcji obliczającej deltę.
    call delta
    
    ; Zdjęcie delty ze stosu.
    pop r14
    
    ; Wypisanie delty.
    push r14
    lea rax, deltaLabel
    push rax
    call wypisz
 
    ; Sprawdzenie czy delta jest mniejsza od zera,
    ; w zależności od wyniku wykonanie odpowieniego skoku.
    cmp r14, 0
 
    jl mniejsza
    je rowna
    push r14
    jg wieksza
 
; Gdy delta jest większa od zera.
wieksza:
 
    ; Pobranie delty ze stosu.
    pop r8
    
    ; Pierwiastek z delty.
    mov four, r8
    fld four
    fsqrt 
    fstp four
 
    ; Przypisanie odpowiednich wartości startowych do
    ; rejestrów xmm, by potem wykonać na nich obliczenia.
    movsd xmm0, aValue
    movsd xmm1, bValue
    movsd xmm2, minusOne
    movsd xmm3, two
    movsd xmm4, four
 
    ; Wykonanie obliczeń: (-b + sqrt(delta)) / 2*a
    mulsd xmm1, xmm2
    mulsd xmm0, xmm3
    addsd xmm1, xmm4
    divsd xmm1, xmm0
 
    ; Wypisanie x1.
    movd rax, xmm1
    push rax
    lea rax, x1Label
    push rax
    call wypisz
    
    ; Przywrócenie wartości startowych.
    movsd xmm0, aValue
    movsd xmm1, bValue
    movsd xmm2, minusOne
    movsd xmm3, two
    movsd xmm4, four
 
    ; Wykonanie obliczeń: (-b - sqrt(delta)) / 2*a
    mulsd xmm1, xmm2
    mulsd xmm0, xmm3
    subsd xmm1, xmm4
    divsd xmm1, xmm0
    
    ; Wypisanie x2.
    movd rax, xmm1
    push rax
    lea rax, x2Label
    push rax
    call wypisz
 
    call ExitProcess
 
; Gdy delta jest równa zeru.
rowna:
 
    movsd xmm0, aValue
    movsd xmm1, bValue
    movsd xmm2, minusOne
    movsd xmm3, two
    
    ; Wykonanie obliczeń: -b / 2*a
    mulsd xmm1, xmm2
    mulsd xmm0, xmm3
    divsd xmm1, xmm0
 
    ; Wypisanie x1
    movd rax, xmm1
    push rax
    lea rax, x1Label
    push rax
    call wypisz
 
    call ExitProcess

; Procedura obliczająca deltę, wartość zwraca
; na stosie.
delta:
 
    pop r13
 
    movsd xmm0, aValue
    movsd xmm1, bValue
    movsd xmm2, cValue
    movsd xmm3, four
 
    ; (b * b) - (4 * a * c)
    mulsd xmm1, xmm1
    mulsd xmm0, xmm2
    mulsd xmm0, xmm3
    subsd xmm1, xmm0
 
    movd rax, xmm1
 
    push rax
 
    push r13
    ret
 
; Procedura wypisująca liczbę i napis podany
; jej poprzez stos.
wypisz:
 
    pop r13
 
    lea rcx, fmtPrintf
    pop rdx
    pop r8
 
    call printf
    pop rcx
 
    push r13
    ret

; Jeżeli delta jest mniejsza od zera.
mniejsza:
 
    lea rcx, fmtPrintfM
 
    call printf
    pop rcx
 
    call ExitProcess
 
Start ENDP
 
End