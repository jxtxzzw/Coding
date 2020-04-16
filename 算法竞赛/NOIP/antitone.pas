{jxtxzzw}
var
i,j,n,s:longint;
a:array[0..9999] of longint;

begin
assign(input,'antitone.in');
reset(input);
assign(output,'antitone.out');
rewrite(output);
readln(n);
for i:=1 to n do read(a[i]);
for i:=1 to n-1 do
 for j:=i+1 to n do
  if a[i]>a[j] then inc(s);
writeln(s);
end.