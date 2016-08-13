extrn ExitProcess: PROC
extrn printf: PROC

.data

  fmtAdd db 13, 10, 'Po dodaniu 0x10 + 0x01', 13, 10, 'base16: %x, base10: %d', 13, 10, 13, 10, 0

  fmtSub db 'Po odjeciu 0x11 - 0x02', 13, 10, 'base16: %x, base10: %d', 13, 10, 13, 10, 0

  fmtMul db 'Po mnozeniu 0x09 * 0x02', 13, 10, 'base16: %x, base10: %d', 13, 10, 13, 10, 0

  fmtDiv db 'Po dzieleniu 0x12 / 0x02', 13, 10, 'base16: %x, base10: %d', 13, 10, 13, 10, 0

.code

Start PROC

;add

  lea rcx, fmtAdd
  mov edx, 10h
  add edx, 1h
  mov r8d, edx

  call printf

  xor rax,rax

;sub

  lea rcx, fmtSub
  mov edx, 11h
  sub edx, 2h
  mov r8d, edx

  call printf

  xor rax,rax

;mul

  lea rcx, fmtMul
  mov eax, 9h
  mov ebx, 2h

  mul ebx

  mov edx, eax
  mov r8d, edx

  call printf

  xor rax, rax

;div
  lea rcx, fmtDiv
  xor rdx, rdx
  mov eax, 12h
  mov ebx, 2h

  div ebx
  xor rdx, rdx

  mov rdx, rax
  mov r8, rdx

  call printf

  xor rax, rax

  call ExitProcess

Start ENDP

End
