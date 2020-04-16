var
 n,na,nb,x,gbss,i,ansa,ansb,resed  :  integer;
 aa,bb                             :  array[1..200] of integer;
 
 
function resing(a,b:integer):integer;
  begin
    resing:=0;
    if a=0 then a:=a+na;
    if b=0 then b:=b+nb;
    if aa[a]=0 then
      begin
        if (bb[b]=1) or (bb[b]=4) then exit(-1);
        if (bb[b]=2) or (bb[b]=3) then exit(1);
      end;
    if aa[a]=1 then
      begin
        if (bb[b]=2) or (bb[b]=4) then exit(-1);
        if (bb[b]=0) or (bb[b]=3) then exit(1);
      end;
    if aa[a]=2 then
      begin
        if (bb[b]=0) or (bb[b]=3) then exit(-1);
        if (bb[b]=1) or (bb[b]=4) then exit(1);
      end;
    if aa[a]=3 then
      begin
        if (bb[b]=0) or (bb[b]=1) then exit(-1);
        if (bb[b]=2) or (bb[b]=4) then exit(1);
      end;
    if aa[a]=4 then
      begin
        if (bb[b]=2) or (bb[b]=3) then exit(-1);
        if (bb[b]=0) or (bb[b]=1) then exit(1);
      end;
 end;
 
function gbs:integer;
 var i,tmp:integer;
 begin
   for i:=1 to nb do
     begin
      tmp:=na*i;
      if tmp mod nb=0 then exit(tmp);
     end;
 end;
 
begin
 assign(input,'rps.in');reset(input);
 assign(output,'rps.out');rewrite(output);
 readln(n,na,nb);
 for i:=1 to na do
   begin
    read(x);
    aa[i]:=x;
   end;
 for i:=1 to nb do
   begin
    read(x);
    bb[i]:=x;
   end;
 gbss:=gbs;
 if gbss>n then
                  begin
                    for i:=1 to n do
                     begin
                        resed:=resing(i mod na,i mod nb);
                        if resed=1 then inc(ansa);
                        if resed=-1 then inc(ansb);
                     end;
                  end
                else
                  begin
                   for i:=1 to gbss do
                     begin
                        resed:=resing(i mod na,i mod nb);
                        if resed=1 then inc(ansa);
                        if resed=-1 then inc(ansb);
                     end;
                   ansa:=ansa*(n div gbss);
                   ansb:=ansb*(n div gbss);
                   for i:=1 to (n-gbss*(n div gbss)) do
                    begin
                        resed:=resing(i mod na,i mod nb);
                        if resed=1 then inc(ansa);
                        if resed=-1 then inc(ansb);
                    end;
                  end;
write(ansa,' ',ansb);
close(input);close(output);
end.