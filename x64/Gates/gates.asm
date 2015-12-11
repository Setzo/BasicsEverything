extrn ExitProcess: PROC
extrn printf: PROC
 
.data
 
.code
 
Start PROC
  
  xor rax, rax
  
  mov rax, 5h
  mov rcx, 8h
  
  and rax, rcx
  or rax, rcx
  not rcx
  
  mov rax, 5h
  mov rcx, 8h
  
  neg rax
  
  cmp rax, rcx
  
  test rax, rcx
  
  call  ExitProcess
  
Start ENDP
 
End
