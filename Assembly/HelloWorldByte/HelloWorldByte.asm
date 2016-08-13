EXTERN GetStdHandle: PROC
EXTERN WriteFile:    PROC
EXTERN lstrlen:      PROC
EXTERN ExitProcess:  PROC

.DATA

   hFile        QWORD 0
   msglen       DWORD 0
   BytesWritten DWORD 0
   msg          BYTE "Hello x64 World", 13, 10, 0
   
.CODE
Start PROC

   sub  rsp, 28h;
   lea  rcx, msg
   call lstrlen
   mov  msglen, eax
   mov  ecx, -11
   call GetStdHandle
   mov  hFile, rax 
   lea  r9, BytesWritten
   mov  r8d, msglen
   lea  rdx, msg
   mov  rcx, hFile
   call WriteFile
   xor  ecx, ecx
   call ExitProcess

Start ENDP
END

