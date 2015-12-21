extrn ExitProcess: PROC
extrn printf: PROC
extrn scanf: PROC
 
 
.data
 
    fmtScanf db '%d%d', 0
 
    noneven db 'Not Even: %d', 13, 10, 0
    evene db 'Even: %d', 13, 10, 0
    zero db 'Zero: %d', 13, 10, 0
 
    positive db 'Positive number: %d', 13, 10, 0
    negative db 'Negative number: %d', 13, 10, 0
 
    equalss db '%d equals %d', 13, 10, 0
    variable1is db '%d is less than %d', 13, 10, 0
    variable2is db '%d is greater than %d', 13, 10, 0
 
    absolutee db 'Abs: %d', 13, 10, 0
 
    variable QWORD 0
    variable2 QWORD 0
 
.code
 
Start PROC
  
    lea rcx, fmtScanf
    lea rdx, variable
    lea r8, variable2
 
    call scanf
  
    xor rbx, rbx
    xor r9, r9
    mov rbx, variable
    mov r9, variable2
  
    cmp rbx, 0h
    je zeroo
aftZero:
  
    test ebx, ebx
 
    test ebx, 1h
  
    jp evenn
    jnp nonevenn
aftNoneven:
aftEven:
 
    cmp ebx, 0
    jl negativee
    jge positivee
aftPositive:
aftNegative: 
 
    xor rbx, rbx
    xor r9, r9
    mov rbx, variable
    mov r9, variable2
  
    cmp r9, rbx
  
    je equals
    jl  greater
    jg less
 
last:
 
    cmp ebx, 0
    jge nxtAbs1
  
    mov rax, variable
    mov r10, -1
    mul r10
    lea rcx, absolutee
    mov rdx, rax
  
    call printf
  
nxtAbs1:
 
    mov rbx, variable2
    cmp ebx, 0
    jge nxtAbs2
  
    mov rax, variable2
    mov r10, -1
    mul r10
    lea rcx, absolutee
    mov rdx, rax
 
    call printf
nxtAbs2:
  
    call  ExitProcess
 
less:
    lea   rcx,  variable1is
    mov rdx, variable
    mov r8, variable2
 
    call  printf
    jmp last
  
greater:
    lea   rcx,  variable2is
    mov rdx, variable
    mov r8, variable2
 
    call  printf
    jmp last
  
equals:
    lea   rcx,  equalss
    mov rdx, variable
    mov r8, variable2
 
    call  printf
    jmp last
  
; NON EVEN
nonevenn:
    lea   rcx,  noneven
    mov rdx, variable
 
    call  printf
    jmp aftNoneven
 
; EVEN
evenn:
    lea   rcx,  evene
    mov rdx, variable
 
    call  printf
    jmp aftEven
 
; ZERO
zeroo:
    lea   rcx,  zero
    mov rdx, variable
 
    call  printf
    jmp aftZero
 
; NEGATIVE
negativee:
    lea   rcx,  negative
    mov rdx, variable
 
    call  printf
    jmp aftNegative
 
; POSITIVE
positivee:
    lea   rcx,  positive
    mov rdx, variable
 
    call  printf
    jmp aftPositive
 
Start ENDP
 
End
