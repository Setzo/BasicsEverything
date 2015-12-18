extrn ExitProcess: PROC
extrn printf: PROC
extrn scanf: PROC
 
 
.data
 
fmtScanf db '%d', 0
 
fmtPrintf db '%3d, ', 0
 
newline db 13, 10, 0
 
variable QWORD 0
 
.code
 
Start PROC
  
    sub rsp, 900h
  
    lea rcx, fmtScanf
    lea rdx, variable
 
    call scanf
 
    lea rcx, newline
    call printf
 
    mov rax, 0
 
    dec variable
    mov r12, variable
 
    cmp r12b, 0h
    jl fnloop
 
    inc variable
    inc variable
 
floop:
 
    lea rcx, fmtPrintf
    mov rdx, rax
 
    push rax
    call printf
    pop rax
    pop rax
 
    inc rax
 
    cmp rax, variable
    je fendloop
    jne floop
 
fnloop:
 
    lea rcx, fmtPrintf
    mov rdx, rax
 
    push rax
    call printf
    pop rax
    pop rax
 
    dec rax
 
    cmp eax, r12d
    je fendloop
    jne fnloop
 
fendloop:
 
    call ExitProcess
 
Start ENDP
 
End