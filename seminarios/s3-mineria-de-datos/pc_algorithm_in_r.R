
# PC Algorithm (Spirtes et al. 1993)
# http://www.inside-r.org/packages/cran/pcalg/docs/pc

install.packages("pcalg")

library(pcalg)

##################################################
## Using Gaussian Data
##################################################
## Load predefined data
data(gmG)
n <- nrow    (gmG8$ x)
V <- colnames(gmG8$ x) # labels aka node names

## estimate CPDAG
pc.fit <- pc(suffStat = list(C = cor(gmG8$x), n = n),
             indepTest = gaussCItest, ## indep.test: partial correlations
             alpha=0.01, labels = V, verbose = TRUE)
if (require(Rgraphviz)) {
  ## show estimated CPDAG
  par(mfrow=c(1,2))
  plot(pc.fit, main = "Estimated CPDAG")
  plot(gmG8$g, main = "True DAG")
}
##################################################
## Using d-separation oracle
##################################################
## define sufficient statistics (d-separation oracle)
suffStat <- list(g = gmG8$g, jp = RBGL::johnson.all.pairs.sp(gmG8$g))
## estimate CPDAG
fit <- pc(suffStat, indepTest = dsepTest, labels = V,
          alpha= 0.01) ## value is irrelevant as dsepTest returns either 0 or 1
if (require(Rgraphviz)) {
  ## show estimated CPDAG
  plot(fit, main = "Estimated CPDAG")
  plot(gmG8$g, main = "True DAG")
}

##################################################
## Using discrete data
##################################################
## Load data
data(gmD)
V <- colnames(gmD$x)
## define sufficient statistics
suffStat <- list(dm = gmD$x, nlev = c(3,2,3,4,2), adaptDF = FALSE)
## estimate CPDAG
pc.D <- pc(suffStat,
           ## independence test: G^2 statistic
           indepTest = disCItest, alpha = 0.01, labels = V, verbose = TRUE)
if (require(Rgraphviz)) {
  ## show estimated CPDAG
  par(mfrow = c(1,2))
  plot(pc.D, main = "Estimated CPDAG")
  plot(gmD$g, main = "True DAG")
}

##################################################
## Using binary data
##################################################
## Load binary data
data(gmB)
V <- colnames(gmB$x)
## estimate CPDAG
pc.B <- pc(suffStat = list(dm = gmB$x, adaptDF = FALSE),
           indepTest = binCItest, alpha = 0.01, labels = V, verbose = TRUE)
pc.B
if (require(Rgraphviz)) {
  ## show estimated CPDAG
  plot(pc.B, main = "Estimated CPDAG")
  plot(gmB$g, main = "True DAG")
}