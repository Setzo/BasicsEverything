extrn ExitProcess: PROC

.data

  z0 BYTE -33
  z1 WORD 34
  z2 DWORD 123
  z3 QWORD -45

.code

Start PROC

  xor rax, rax
  xor rax, rax
  xor rax, rax
  xor rax, rax

  mov al, z0
  mov bx, z1
  mov ecx, z2
  mov rdx, z3

call ExitProcess

Start ENDP

END
