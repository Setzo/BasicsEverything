import re

first_word = lambda text: re.match('\W*([\w\']+)', text).group(1)
