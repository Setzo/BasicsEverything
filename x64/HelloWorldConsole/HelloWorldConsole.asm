extrn ExitProcess: PROC
extrn printf: PROC
.data
caption db '64-bit hello', 0
message db 'Hello World!', 0
fmt db '%s', 10, 0

.code
Start PROC
  push  rdi
  sub   rsp,28h
  lea   rcx, message
  lea   rdx, fmt
  xor   rax, rax
  call  printf
  xor   rax, rax
  mov   ecx, eax
  pop   rdi
  call  ExitProcess
Start ENDP
End
