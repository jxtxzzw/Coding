{var
ans,i,j,a,b,k,n,m:longint;
begin
  readln(a,b,k,n,m);
  ans:=1;
  for i:=k downto k-m+1 do ans:=ans*i;
  for i:=m downto 1 do ans:=ans div i;
  for i:=1 to n do ans:=ans*a;
  for i:=1 to m do ans:=ans*b;
  writeln(ans);
end.}

var
ans:array[1..1000000]of longint;
k,n,m:integer;temp,a,b,i,j,num,p:longint;
begin
  assign(input,'factor.in');reset(input);
  assign(output,'factor.out');rewrite(output);
  readln(a,b,k,n,m);
  fillchar(ans,sizeof(ans),0);
  ans[1]:=1;
  num:=1;
  for i:=k downto k-m+1 do
  begin
    temp:=0;
    for j:=1 to num do
    begin
      ans[j]:=ans[j]*i+temp;
      temp:=ans[j] div 10;
      ans[j]:=ans[j] mod 10;
    end;
    while temp>0 do
      begin
        inc(num);
        ans[num]:=temp mod 10;
        temp:=temp div 10
      end;
  end;
  for i:= m downto 1 do
  begin
    temp:=0;
    for j:=num downto 1 do
      begin
        p:=(ans[j]+temp*10) mod i;
        ans[j]:=(ans[j]+temp*10) div i;
        temp:=p;
      end;
      while ans[num]=0 do
        dec(num);
  end;
  for i:=1 to n do
  begin
    temp:=0;
    for j:=1 to num do
    begin
      ans[j]:=ans[j]*a+temp;
      temp:=ans[j] div 10;
      ans[j]:=ans[j] mod 10;
    end;
    while temp>0 do
      begin
        inc(num);
        ans[num]:=temp mod 10;
        temp:=temp div 10
      end;
  end;
  for i:=1 to m do
  begin
    temp:=0;
    for j:=1 to num do
    begin
      ans[j]:=ans[j]*b+temp;
      temp:=ans[j] div 10;
      ans[j]:=ans[j] mod 10;
    end;
    while temp>0 do
      begin
        inc(num);
        ans[num]:=temp mod 10;
        temp:=temp div 10
      end;
  end;

    for j:=num downto 1 do
      begin
        p:=(ans[j]+temp*10) mod 10007;
        ans[j]:=(ans[j]+temp*10) div 10007;
        temp:=p;
      end;
      while ans[num]=0 do
        dec(num);
  write(temp);
  close(input);close(output);
end.