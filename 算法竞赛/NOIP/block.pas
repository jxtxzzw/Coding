var n,k,i,j:longint;
      ans:int64;
begin
  assign(input,'block10.in');reset(input);
  assign(output,'block10.out');rewrite(output);
  readln(n);
  k:=0;
  for i:=1 to n do
    begin
      read(j);
      if j>k then inc(ans,j-k);
      k:=j;
    end;
  write(ans);
  close(input);close(output);
end.
