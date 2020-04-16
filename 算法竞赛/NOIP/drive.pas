label 1;
type point=^rec;
     rec=record
           da:longint;
           la,ne:point;
         end;
var i,n,m,t,s,top,j,k,ans,dk,lj:longint;
    h,a,mi,mmi,b:array[0..100000] of int64;
    line:array[0..100000] of point;
    fa,na,nb:array[0..100000,-2..17] of int64;
    p,q:point;
    bo:array[0..100000] of boolean;
    re:real;
    la,lb,x:int64;
    flag:boolean;
procedure sort(l,r:longint);
var i,j,x,y:longint;
begin
  x:=h[(l+r) div 2];
  i:=l;
  j:=r;
  repeat
    while h[i]<x do inc(i);
    while x<h[j] do dec(j);
    if not (i>j) then  begin  y:=h[i];h[i]:=h[j];h[j]:=y;
                              y:=a[i];a[i]:=a[j];a[j]:=y;
                              inc(i);dec(j);
                       end;
  until i>j;
  if l<j then sort(l,j);
  if i<r then sort(i,r);
end;

procedure deal;
begin
  la:=0;lb:=0;k:=s;flag:=true;
  while flag do
    begin
      j:=0;
      while (x>=la+lb+na[k,j]+nb[k,j])and(fa[k,j]<>0) do inc(j);
      if (x<la+lb+na[k,j]+nb[k,j])and(j=0) then flag:=false;
      if fa[k,0]=0 then flag:=false;
      la:=la+na[k,j-1];
      lb:=lb+nb[k,j-1];
      k:=fa[k,j-1];
   end;
  if (mmi[k]<>0)and(la+lb+abs(b[mmi[k]]-b[k])<=x) then
                              la:=la+abs(b[mmi[k]]-b[k]);
end;

begin
  assign(input,'drive.in') ;reset(input);
  assign(output,'drive.out');rewrite(output);
  readln(n);
  for i:=1 to n do  begin read(h[i]);a[i]:=i;end;
  b:=h;
  sort(1,n);
  new(line[a[1]]);
  line[a[1]]^.la:=nil;
  line[a[1]]^.da:=a[1];
  for i:=2 to n do
    begin
      new(line[a[i]]);
      line[a[i]]^.la:=line[a[i-1]];
      line[a[i]]^.da:=a[i];
      line[a[i-1]]^.ne:=line[a[i]];
    end;
  line[a[n]]^.ne:=nil;
  for i:=1 to n do
    begin
      p:=line[i]^.la;
      q:=line[i]^.ne;
      if (p=nil)and(q=nil) then goto 1;
      if (q<>nil)and((p=nil)or(b[i]-b[p^.da]>b[q^.da]-b[i])) then
         begin mi[i]:=q^.da;
               q:=q^.ne;
               if (p=nil)and(q=nil) then goto 1;
               if (q<>nil)and((p=nil)or(b[i]-b[p^.da]>b[q^.da]-b[i])) then mmi[i]:=q^.da
                                                                      else mmi[i]:=p^.da;
         end
                                                             else
         begin mi[i]:=p^.da;
               p:=p^.la;
               if (p=nil)and(q=nil) then goto 1;
               if (p<>nil)and((q=nil)or(b[q^.da]-b[i]>=b[i]-b[p^.da]))then mmi[i]:=p^.da
                                                                      else mmi[i]:=q^.da;
         end;
      1:if line[i]^.la<>nil then line[i]^.la^.ne:=line[i]^.ne;
        if line[i]^.ne<>nil then line[i]^.ne^.la:=line[i]^.la;
        line[i]:=nil;
    end;
  for i:=1 to n do if mmi[i]<>0 then begin t:=mmi[i];
                                           if mi[t]<>0 then begin
                                                              nb[i,0]:=abs(b[t]-b[mi[t]]);
                                                              na[i,0]:=abs(b[i]-b[t]);
                                                            end;
                                           fa[i,0]:=mi[t];
                                     end;

  for i:=1 to n do fa[i,-1]:=i;
  for j:=1 to 17 do
    for i:=1 to n do
      begin
        fa[i,j]:=fa[fa[i,j-1],j-1];
        if fa[i,j]<>0 then begin na[i,j]:=na[i,j-1]+na[fa[i,j-1],j-1];
                                 nb[i,j]:=nb[i,j-1]+nb[fa[i,j-1],j-1];
                           end;
      end;
  read(x);
  re:=10000000000;
  for s:=1 to n do
    begin
      deal;
      if (lb<>0)and(re>la/lb) then begin re:=la/lb;
                                         ans:=s;
                                   end;

    end;
  writeln(ans);
  read(m);
  for i:=1 to m do
    begin
      read(s,x);
      deal;
      writeln(la,' ',lb);
    end;
  close(input);close(output);
end.
