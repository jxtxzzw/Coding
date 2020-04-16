var i,l,t:longint;
    a:array[0..1000] of longint;
    s:string;
    ch:char;
begin
  assign(input,'vigenere.in');reset(input);
  assign(output,'vigenere.out');rewrite(output);
  readln(s);
  l:=length(s);
  for i:=1 to l do
    if ord(s[i])>=97 then a[i]:=ord(s[i])-96
                     else a[i]:=ord(s[i])-64;
  a[0]:=a[l];
  read(ch);
  i:=1;
  t:=ord(ch);
  while (t>64)and(t<124) do
    begin
      if t<97 then begin
                    t:=t-64;
                    t:=(t-a[i mod l]+27) mod 26;
                    if t=0 then t:=26;
                    t:=t+64;
                    write(chr(t));
                   end
              else begin
                    t:=t-96;
                    t:=(t-a[i mod l]+27) mod 26;
                    if t=0 then t:=26;
                    t:=t+96;
                    write(chr(t));
                   end;
      read(ch);
      inc(i);
      t:=ord(ch);
    end;
close(input);close(output);
end.


