const
  p = 10007;
var
  c             : array[0..1100,0..1100] of longint;
  a,b,k,n,m,i,j : longint;
  ans           : int64;

begin
  assign(input,'factor.in'); reset(input);
  assign(output,'factor.out'); rewrite(output);
  readln(a,b,k,n,m);
  c[0,0] := 1; c[1,0] := 1; c[1,1] := 1;
  for i := 2 to k do begin
    c[i,0] := 1; c[i,i] := 1;
    for j := 1 to i-1 do c[i,j] := (c[i-1,j]+c[i-1,j-1]) mod p;
  end;
  ans := c[k,n];
  a := a mod p; b := b mod p;
  for i := 1 to n do ans := ans*a mod p;
  for i := 1 to m do ans := ans*b mod p;
  writeln(ans);
  close(input); close(output);
end.