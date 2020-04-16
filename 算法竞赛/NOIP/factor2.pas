var a,b,h,ans:longint;k,m,n,i:integer;s:array[1..10000000] of byte;
procedure cheng(a:longint);
var i,k,t:longint;
begin
  k:=0;
  for i:=1 to h do begin
  t:=s[i]*a+k;s[i]:=t mod 10;k:=t div 10;end;
  while k>0 do begin inc(h);s[h]:=k mod 10;k:=k div 10;end;
end;
procedure chu(a:longint);
var i,k:longint;
begin
  k:=s[h];
  while (k<a) and (h>=2)  do
    begin s[h]:=0;dec(h);k:=k*10+s[h];end;s[h]:=k div a;k:=k mod a;
  for i:=h-1 downto 1 do begin
    k:=k*10+s[i];s[i]:=k div a;k:=k mod a;end;ans:=k;
end;
begin
  assign(input,'factor.in');reset(input);
  assign(output,'factor.out');rewrite(output);
  readln(a,b,k,n,m);s[1]:=1;h:=1;
  for i:=1 to n do cheng(a);for i:=1 to m do cheng(b);
  for i:=k-m+1 to k do cheng(i);
  for i:=2 to m do chu(i);
  chu(10007);
  write(ans);
  close(input);close(output);
end.