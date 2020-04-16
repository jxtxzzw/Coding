const
 dx:array[1..8] of integer=(-2,-1,1,2,2,1,-1,-2);
 dy:array[1..8] of integer=(1,2,2,1,-1,-2,-2,-1);
var
 n,m,x,y,i,j: byte;
 g:array[0..20,0..20] of 0..1;
 c:longint;
 infile,outfile:text;
procedure sol(x,y:integer);
var i:integer;
begin
 if (x=n) and (y=m) then c:=c+1
  else
   begin
    if (y<m) and (g[x,y+1]=0) then begin writeln(x,y+1);sol(x,y+1);end;
    if (x<n) and (g[x+1,y]=0) then begin writeln(x+1,y);sol(x+1,y);end;
   end;
end;
begin
 assign(input,'knight.in');reset(input);
 assign(output,'knight.out');rewrite(output);
 readln(n,m,x,y);
 fillchar(g,sizeof(g),0);
 g[x,y] := 1;
 for i:=1 to 8 do
 if (x+dx[i]>=0) and (x+dx[i]<=n) and (y+dy[i]>=0) and (y+dy[i]<=m) then
  g[x+dx[i],y+dy[i]]:=1;
 sol(0,0);
 writeln('There are ',c,' paths.');
 close(input);close(output);
end.
