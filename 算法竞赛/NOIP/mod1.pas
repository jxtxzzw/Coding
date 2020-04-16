var a,b,n,i,x:longint;
    t1,t2:array[1..1000] of longint;
begin
  assign(input,'mod.in');reset(input);
  assign(output,'mod.out');rewrite(output);
  readln(a,b);
  i:=1;
  while (a<>1) and (b<>1) do
    begin
      t1[i]:=a div b;
      a:=a mod b;
      t2[i]:=b div a;
      b:=b mod a;
      inc(i);
    end;
  dec(i);
  x:=1;
  n:=a-1;
  while (i<>0) do
      begin
        inc(x,t2[i]*n);
        inc(n,t1[i]*x);
        dec(i);
      end;
  writeln(x);
  close(input);close(output);
end.


