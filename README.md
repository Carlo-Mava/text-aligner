# Text aligner

## Align foo

Aligns the fields of a function as a table.

```
   foo(a, b, c);
foo(aa, bb, cc);
foo(aaa, bbb, ccc);
```
becomes
```
foo(a,   b,   c  );
foo(aa,  bb,  cc );
foo(aaa, bbb, ccc);
```

Next up

Nested functions

goo (
    foo(a,   b,   c  );
    foo(aa,  bb,  cc );
    foo(aaa, bbb, ccc);
)