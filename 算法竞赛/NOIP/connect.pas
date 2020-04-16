{$N+}
var
  a     : array[1..8,1..2] of byte;
  c     : array[1..8,1..8] of extended;
  b     : array[1..8]of boolean;
  d     : array[1..8] of byte;
  n,i,j : byte;
  min,r : extended;

procedure cal;
begin
  r := 0;
  for i := 2 to n do r := r+c[d[i],d[i-1]];
  if r < min then min := r;
end;

procedure sub(s : byte);
var i : byte;
begin
  if s > n
    then cal
    else for i := 1 to n do
           if b[i] then begin
             b[i] := false;
             d[s] := i;
             sub(s+1);
             b[i] := true;
           end;
end;

begin
  assign(input,'connect.in'); reset(input);
  assign(output,'connect.out');rewrite(output);
  repeat
    readln(n);
    if n = 0 then break;
    for i := 1 to n do readln(a[i,1],a[i,2]);
    min := 1e38;
    fillchar(b,sizeof(b),1);
    for i := 1 to n do
      for j := 1 to i do begin
        c[i,j] := sqrt(sqr(a[i,1]-a[j,1])+sqr(a[i,2]-a[j,2]))+16;
        c[j,i] := c[i,j];
      end;
    sub(1);
    writeln(min:0:2);
  until false;
  close(input);close(output);
end.