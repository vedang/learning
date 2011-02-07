-module (ch2).
-export ([either_or_both/2, invert/1, filereadline/1, ipv4/1, current_pos/0, foo/1, rev/1, tailrev/1]).
-define (PI, 3.14159).

%% using guard clauses
either_or_both (true, B) when is_boolean (B) ->
    true;
either_or_both (A, true) when is_boolean (A) ->
    true;
either_or_both (false, false) ->
    false.

%% try-catch
invert (N) ->
    try
        1 / N
    catch
        throw:Other ->
            {got_throw, Other};
        exit:Reason ->
            {got_exit, Reason};
        error:Reason ->
            {got_error, Reason}
    end.

%% after - the erlang equivalent of finally
filereadline (Filename) ->
    {ok, Filehandle} = file:open (Filename, [read]),
    try
        file:read_line (Filehandle)
    after
        file:close (Filehandle)
    end.

ipv4 (<<Version:4, IHL:4, ToS:8, TotalLength:16,
        Identification:16, Flags:3, FragOffset:13,
        TimeToLive:8, Protocol:8, Checksum:16,
        SourceAddress:32, DestinationAddress:32,
        RemainingData/bytes>>) when Version =:= 4 ->
    %% hahaha I feel evil!
    awesome.

%% inbuilt macros to get current position.
current_pos () ->
    [{module, ?MODULE}, {file, ?FILE}, {line, ?LINE}].

-ifdef (debug).
-define (show (X), io:format ("The value is: ~w.~n", [X])).
-else.
-define (show (X), ok).
-endif.

foo (A) ->
    ?show (A),
    %% other things
    io:format ("FOO ok").

rev ([A | TheRest]) ->
    rev (TheRest) ++ [A];
rev ([]) ->
    [].

tailrev (List) ->
    tailrev (List, []).

tailrev ([X | TheRest], Acc) ->
    tailrev (TheRest, [X | Acc]);
tailrev ([], Acc) ->
    Acc.

