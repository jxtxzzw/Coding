var a,b:array[1..100000,1..2]of longint;
    x,y:array[1..100000]of longint;
    n,i,j,k,m,ans:longint;

procedure sorta(h,l:longint);
var i,j,m:longint; k:array[1..2]of longint;
begin
  i:=h; j:=l; m:=a[(i+j)div 2,1];
  while i<j do
    begin
      while a[i,1]<m do inc(i);
      while a[j,1]>m do dec(j);
      if i<=j then begin k:=a[i];a[i]:=a[j]; a[j]:=k;inc(i); dec(j); end;
    end;
  if i<l then sorta(i,l);
  if h<j then sorta(h,j);
end;

procedure sortb(h,l:longint);
var i,j,m:longint;
    k:array[1..2]of longint;
begin
  i:=h; j:=l; m:=b[(i+j)div 2,1];
  while i<j do
    begin
      while b[i,1]<m do inc(i);
      while b[j,1]>m do dec(j);
      if i<=j then begin k:=b[i]; b[i]:=b[j]; b[j]:=k;inc(i); dec(j);end;
    end;
  if i<l then sortb(i,l);
  if h<j then sortb(h,j);
end;

procedure bing(h,l:longint);
var i,k,c,f1,f2:longint;
begin
  if h=l then exit;
  c:=(h+l)div 2;
  bing(h,c);
  bing(c+1,l);
  f1:=h;f2:=c+1;k:=0;
  for i:=h to l do
    begin
      if f2>l then begin
                 x[i]:=b[f1,1]; inc(f1); inc(ans,k);
                 while ans>=99999997 do dec(ans,99999997);
                 continue;
               end;
      if b[f1,1]<b[f2,1]  then begin
                            x[i]:=b[f1,1]; inc(f1); inc(ans,k);
                            while ans>=99999997 do dec(ans,99999997);
                           end
                      else begin
                             x[i]:=b[f2,1]; inc(f2); inc(k);
                           end;
    end;
for i:=h to l do  b[i,1]:=x[i];   end;

begin
assign(input,'match10.in');reset(input);
assign(output,'match10.out');rewrite(output);
readln(n);
for i:=1 to n do   begin    read(a[i,1]);   a[i,2]:=i;   end;
for i:=1 to n do   begin    read(b[i,1]);   b[i,2]:=i;  end;
sorta(1,n); sortb(1,n);
for i:=1 to n do  b[b[i,2],1]:=a[i,2];
ans:=0;
bing(1,n);
write(ans);
close(input);close(output);
end.
