-module (lib_misc).
-export ([odds_and_evens/1, odds_and_evens_acc/1, odds_and_evens_acc/3, mycleanfilter/2, qsort/1, pythag/1, perms/1, mymap/2, myfilter/2, myfilter1/4, isEven/1]).

%% Defining map using list comprehension
mymap (F,L) ->
    [F(X) || X <- L].

%% Quick-sort
qsort ([]) ->
    [];
qsort ([Pivot|T]) ->
    qsort ([X || X <- T,
                 X < Pivot])
        ++ [Pivot] ++
        qsort ([X || X <- T,
                     X >= Pivot]).

%% printing pythagorean triplets
pythag (N) ->
    [ {A, B, C} || A <- lists:seq (1, N),
                   B <- lists:seq (1, N),
                   C <- lists:seq (1, N),
                   A + B + C =< N,
                   A*A + B*B =:= C*C].

%% Printing permutations of a list
perms ([]) ->
    [[]];
perms (L) ->
    [[H|T] || H <- L,
              T <- perms (L--[H])].

%% filter using list comprehension
isEven (X) ->
    X rem 2 =:= 0.

myfilter (P, [H|T]) ->
    myfilter1 (P(H), H, P, T);
myfilter (P, []) ->
    [].

myfilter1 (true, H, P, T) ->
    [H | myfilter (P, T)];
myfilter1 (false, H, P, T) ->
    myfilter (P, T).

%% a cleaner filter function
mycleanfilter (P, [H|T]) ->
    case P(H) of
        true ->
	    [H | mycleanfilter (P, T)];
	false ->
	    mycleanfilter (P, T)
    end;
mycleanfilter (P, []) ->
    [].

%% finding odds and evens in 2 passes over the list
odds_and_evens (L) ->
    Odds = [X || X <- L, (X rem 2) =:= 1],
    Evens = [X || X <- L, (X rem 2) =:= 0],
    {Odds, Evens}.

%% finding odds and evens in a single pass over the list
%% accumulators
odds_and_evens_acc (L) ->
    odds_and_evens_acc (L, [], []).

odds_and_evens_acc ([H|T], Odds, Evens) ->
    case (H rem 2) of
	1 ->
	    odds_and_evens_acc (T, [H|Odds], Evens);
	0 ->
	    odds_and_evens_acc (T, Odds, [H|Evens])
    end;
odds_and_evens_acc ([], Odds, Evens) ->
    {Odds, Evens}.

