var a:array[0..20] of byte;
    x:array[1..20] of longint;
    tot:longint;
    i,n,k:byte;

procedure check;
var i,p:longint;
begin
  p:=0;
  for i:=1 to k do p:=p+x[a[i]];
  for i:=2 to trunc(sqrt(p)) do
    if p mod i=0 Then exit;
  inc(tot);
end;

procedure sub(s:byte);
var i:byte;
begin
  if s>k then check
         else for i:=a[s-1]+1 to n do begin
                                       a[s]:=i;
                                       sub(s+1);
                                      end;
end;

begin
  write('Input n,k:'); readln(n,k);
  for i := 1 to n do read(x[i]);
  tot:= 0;
  sub(1);
  writeln(tot);
  readln;readln;
End.
