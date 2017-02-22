print("Hello World")

# Help commands
??ls
?ls
???ls

# Assign and access z
assign("z", 300)
z

global.var <- 'global one'
get("global.var", globalenv())

# Create new environment.
dev <- new.env()
parent.env(dev) # Check parent env.

# Assign and access dev env variable.
dev$local.var <- 10
dev$local.var

# Operators.
10 + 3
10 - 3
10 * 3
10 / 3
10 ^ 3 # pow, THIS IS NOT XOR
format(10^8, scientific = FALSE)
10 ** 3 # pow
10 %% 3 # mod
10 %/% 3 # int division

# PI constant.
pi

# REPL options.
options()
options(digits = 7)

# Behaviour.
1 / 0
-1 / 0
Inf + 5
is.finite(1 / 0)
is.infinite(1 / 0)

Inf / Inf
is.nan(Inf / Inf)
NA + 5
is.na(NA)

is.na(NaN)
is.nan(NA)

# Logical operators.
FALSE & TRUE
! FALSE
FALSE | TRUE
