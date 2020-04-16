var
  a       : array[1..8] of -1..1;
  n,total : longint;

procedure check;
var s,i,j,k : longint;
begin
  s := 0; j := 1; k := 1;
  for i := 1 to n-1 do
    if a[i] <> 0 then begin
      s := s+j*k;
      k := a[i];
      j := i+1;
    end
    else j := j*10+i+1;
  s := s+k*j;
  if s <> 0 then exit;
  inc(total);
  write(1);
  for i := 1 to n-1 do begin
    if a[i] = 1 then write('+');
    if a[i] = -1 then write('-');
    write(i+1);
  end;
  writeln(' = 0');
end;

procedure sub(s : longint);
var k : longint;
begin
  if s > n-1
    then check
    else for k := -1 to 1 do begin
           a[s] := k;
           sub(s+1);
         end;
end;

begin
  repeat
    write('N = ');
    readln(n);
  until n in [2..9];
  total := 0;
  sub(1);
  writeln('Total = ',total);
  readln;
end.
