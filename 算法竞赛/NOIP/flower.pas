var n,i,j,k,l,m,a1,a2,a3:longint;
begin
  assign(input,'flower10.in');reset(input);
  assign(output,'flower10.out');rewrite(output);
  readln(n);
  if n=1 then begin write(1); halt; end;
  read(a1,a2);
  if n=2  then begin if a1=a2 then write(1) else write(2); halt; end;
  for i:=3 to n do
    begin
      read(a3);
      if ((a3>a2)and(a2<a1))or((a3<a2)and(a2>a1)) then inc(k);
      if a2=a3 then continue;
      a1:=a2;
      a2:=a3;
    end;
  write(k+2);
  close(input);close(output);
end.
