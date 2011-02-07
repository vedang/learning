-module(shop).
-export([cost/1, total/1, total2/1, total3/1]).
-import(lists, [map/2]).

%% Buy = [{oranges, 5},
%%        {milk, 2},
%%        {bread, 3},
%%        {eggs, 12}].

cost(oranges) ->
    5;
cost(milk) ->
    10;
cost(eggs) ->
    2;
cost(bread) ->
    20.

sum ([H|T]) ->
    H + sum(T);
sum ([]) ->
    0.

total([{What, N}|T]) ->
    shop:cost(What) * N + total(T);
total([]) ->
    0.

total2(L) ->
    sum (map (fun ({What, N}) ->
                      cost (What) * N end, L)).

total3(L) ->
    sum ([cost (A) * B || {A, B} <- L]).
