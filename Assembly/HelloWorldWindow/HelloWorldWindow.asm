EXTERN ExitProcess: PROC
EXTERN MessageBoxA: PROC

.DATA

caption db '64-bit hello!', 0
message db 'Hello World', 0

.CODE
Start PROC

   sub rsp, 28h
   mov rcx, 0
   lea rdx, message
   lea r8, caption
   mov r9d, 0
   call MessageBoxA
   mov ecx, eax
   call ExitProcess
   
Start ENDP
End
