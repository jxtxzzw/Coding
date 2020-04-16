program tortoise;
var f:array[0..41,0..41,0..41,0..41] of integer;
    c:array[0..355] of integer;
    sum:array[1..4] of integer;
    n,m,i,x:integer;
    x1,x2,x3,x4:integer;
 
procedure max(var a,b:integer);
begin
  if b>a then a:=b;
end;
 
begin
  fillchar(f,sizeof(f),0);
  fillchar(sum,sizeof(sum),0);
  readln(n,m);
  for i:=1 to n do read(c[i]);readln;
  for i:=1 to m do
    begin
      read(x);
      inc(sum[x]);
    end;
  readln;
  f[0,0,0,0]:=c[1];
  for x1:=0 to sum[1] do
    for x2:=0 to sum[2] do
      for x3:=0 to sum[3] do
        for x4:=0 to sum[4] do
          begin
            if x1+x2+x3+x4=0 then continue;
            f[x1,x2,x3,x4]:=0;
            if x1>0 then max(f[x1,x2,x3,x4],f[x1-1,x2,x3,x4]);
            if x2>0 then max(f[x1,x2,x3,x4],f[x1,x2-1,x3,x4]);
            if x3>0 then max(f[x1,x2,x3,x4],f[x1,x2,x3-1,x4]);
            if x4>0 then max(f[x1,x2,x3,x4],f[x1,x2,x3,x4-1]);
            f[x1,x2,x3,x4]:=f[x1,x2,x3,x4]+c[x1+2*x2+3*x3+4*x4+1];
          end;
     write(f[sum[1],sum[2],sum[3],sum[4]]);
end.