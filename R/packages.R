# List installed packages.
library()

# Assign installed packages string to vartiable.
packages <- installed.packages()
packages

# Load library.
library('parallel')
library(parallel)

# Attaches libraries.
search()

# Detach package
detach(package:parallel, unload = T)
search()

# Load package_name, on error return false.
if(!require('package_name')) {
  # Handle error on package not installed. 
}

setRepositories()
install.packages('plyr')
install.packages(c('ggplot2', 'randomForest', 'RColorBrewer'))

# Package to download packages from github.
install.packages('devtools')
if(require(devtools)) {
  install_github('slidify', 'ramnathv')
}

# Update each package.
update.packages()
update.packages(ask = F)

# Remove packages.
remove.packages(c('slidify', 'ramnathv'))
