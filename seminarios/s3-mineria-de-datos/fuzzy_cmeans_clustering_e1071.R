# http://www.inside-r.org/packages/cran/e1071/docs/cmeans
# Fuzzy C-Means Clustering

# https://stat.ethz.ch/R-manual/R-devel/library/cluster/html/fanny.html

install.packages("e1071")

library(e1071)

# a 3-dimensional example
x<-rbind(matrix(rnorm(150,sd=0.3),ncol=3),
         matrix(rnorm(150,mean=1,sd=0.3),ncol=3),
         matrix(rnorm(150,mean=2,sd=0.3),ncol=3))
cl<-cmeans(x,6,20,verbose=TRUE,method="cmeans")
print(cl)

cl

cl$cluster
