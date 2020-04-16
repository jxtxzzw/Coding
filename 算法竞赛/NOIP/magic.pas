VAR
a:array[1..39,1..39]of integer;
pos:array[1..2000,1..2]of integer;
k,n,i,j:integer;
 
BEGIN
  assign(input,'magic.in');reset(input);
  assign(output,'magic.out');rewrite(output);
  readln(n);
  a[1,(n+1) div 2]:=1;
  pos[1,1]:=1;
  pos[1,2]:=(n+1) div 2;
  for k:=2 to n*n do
  begin
   if (pos[k-1,1]=1) and (pos[k-1,2]<>n) then
     begin
       a[n,pos[k-1,2]+1]:=k;
       pos[k,1]:=n;
       pos[k,2]:=pos[k-1,2]+1;
       continue;
     end;
 
     if (pos[k-1,2]=n) and (pos[k-1,1]<>1) then
       begin
         a[pos[k-1,1]-1,1]:=k;
         pos[k,1]:=pos[k-1,1]-1;
         pos[k,2]:=1;
         continue;
       end;
 
     if (pos[k-1,1]=1) and (pos[k-1,2]=n) then
       begin
         a[2,n]:=k;
         pos[k,1]:=2;
         pos[k,2]:=n;
         continue;
       end;
 
    if (pos[k-1,1]<>1) and (pos[k-1,2]<>n) then
      begin
        if (a[pos[k-1,1]-1,pos[k-1,2]+1]=0) then
          begin
             a[pos[k-1,1]-1,pos[k-1,2]+1]:=k;
             pos[k,1]:=pos[k-1,1]-1;
             pos[k,2]:=pos[k-1,2]+1;
          end
          else
          begin
             a[pos[k-1,1]+1,pos[k-1,2]]:=k;
             pos[k,1]:=pos[k-1,1]+1;
             pos[k,2]:=pos[k-1,2];
          end;
     end;
 end;
 
 for i:=1 to n do for j:=1 to n do
  begin
    write(a[i,j]);
    if j<>n then write(' ') else writeln;
  end;
 
close(input);close(output);
END.