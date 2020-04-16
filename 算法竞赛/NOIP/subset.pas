Var
  a : Array[0..10] of Byte;
  n,m : Byte;
  Total : LongInt;

Procedure print;
Var j : byte;
Begin
  inc(total);
  write('[');
  for j := 1 to m-1 do write(a[j],',');
  writeln(a[m],']');
End;

Procedure Sub(s : Byte);
Var
  i : byte;
Begin
  If s > m
    Then print
    Else For i := a[s-1] + 1 to n Do Begin
           a[s] := i;
           Sub(s+1);
         End;
End;

Begin
  {Assign(Input,'Subset.in'); Reset(Input);
  Assign(Output,'Subset.out'); ReWrite(Output);}
  Read(n);
  Writeln('[]');
  total := 1;
  For m := 1 to n Do Sub(1);
  Writeln('TOTAL = ', Total);
  {Close(Input); Close(Output);}
End.
