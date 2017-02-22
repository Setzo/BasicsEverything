# Enum like list.
factor.chr <- factor(c('str1', 'str2', 'str1', 'str2'))
as.numeric(factor.chr)

blood.types <- factor(c('A', 'B', '0', 'AB', '0', 'A', 'B', 'B'), levels = c('A', 'B', 'AB', '0'))
blood.types
