-module(ch1).
-export([area/1, double/1, b_not/1, b_and/2, b_or/2, b_nand/2]).


% Exercise 2.2
double(Value) ->
    times(Value, 2).


times(A, B) ->
    A * B.


area({square, Side}) ->
    Side * Side;
area({circle, Radius}) ->
    math:pi() * Radius * Radius;
area({triangle, A, B, C}) ->
    S = (A + B + C) / 2,
    math:sqrt(S * (S - A) * (S - B) * (S - C));
area(_Other) ->
    {error, invalid_object}.


% Exercise 2.3
b_not(true) ->
    false;
b_not(false) ->
    true;
b_not(_) ->
    false.


b_or(true, _) ->
    true;
b_or(_, true) ->
    true;
b_or(_, _) ->
    false.


b_and(false, _) ->
    false;
b_and(_, false) ->
    false;
b_and(_, _) ->
    true.


b_nand(A, B) ->
    b_not(b_and(A, B)).



