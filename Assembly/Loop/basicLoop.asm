extrn ExitProcess: PROC
extrn printf: PROC
extrn scanf: PROC
 
 
.data
 
fmtScanf db '%d', 0
 
fmtPrintf db '%d, ', 0
 
newline db 13, 10, 0
 
variable QWORD 0
 
.code
 
Start PROC
  
    lea rcx, fmtScanf
    lea rdx, variable
 
    call scanf
 
    lea rcx, newline
    call printf
 
floop:
 
    lea rcx, fmtPrintf
    mov rdx, variable
 
    call printf
 
    dec variable
 
    cmp variable, -1
    je fendloop
    jne floop
 
fendloop:
 
    call ExitProcess
 
Start ENDP
 
End