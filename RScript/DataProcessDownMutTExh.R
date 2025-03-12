DataMut = as.data.frame(rbind(read.table(file = "../Results/Mut8TumSizeTExh.csv",header = F,stringsAsFactors = F),
                read.table(file = "../Results/Mut16TumSizeTExh.csv",header = F,stringsAsFactors = F),
                read.table(file = "../Results/Mut24TumSizeTExh.csv",header = F,stringsAsFactors = F),
                read.table(file = "../Results/Mut32TumSizeTExh.csv",header = F,stringsAsFactors = F),
                read.table(file = "../Results/Mut40TumSizeTExh.csv",header = F,stringsAsFactors = F),
                read.table(file = "../Results/Mut48TumSizeTExh.csv",header = F,stringsAsFactors = F),
                read.table(file = "../Results/Mut56TumSizeTExh.csv",header = F,stringsAsFactors = F),
                read.table(file = "../Results/Mut64TumSizeTExh.csv",header = F,stringsAsFactors = F)
                ))
names(DataMut) = c("Mut","Tum")
DataMut$Mut = gsub("$","",DataMut$Mut,fixed=T)

DataMut = DataMut[sort(DataMut$Tum,index.return = T)$ix,]

pdf(file = "../Results/TumCarTExhMutDown.pdf",width = 14)
plot(1:length(DataMut$Tum),DataMut$Tum,cex=.5,col="red",ylim = c(0,max(DataMut$Tum)+.1),
     xlab="",ylab = "TumSize after 48h",pch = 4)
colText = rep("black",length(DataMut$Mut))
colText[which(DataMut$Mut == "wt")] = "blue"
text(1:length(DataMut$Tum),DataMut$Tum,cex=.6,labels = DataMut$Mut,srt=90,pos=3,offset=2, col = colText)
dev.off()

## DataTASensit = read.table("TumSizeSensitPropTA.csv",header = T,sep = "\t",stringsAsFactors = F)
## ColList = c("black","red","green","blue","purple","orange","grey","yellow","brown")

## pdf(file = "PlotTASensit.pdf")../Mut8TumSizeTExh.csv
## plot(DataTASensit$Time,unlist(DataTASensit[,3]),col = ColList[1],type="l",cex = .2,ylim = c(0,.5),xlab = "Time", ylab = "Tumor Size",
##      main = "Tumor Size for different TA initial proportions")
## for (i in 2:9)
## {points(DataTASensit$Time,unlist(DataTASensit[,i+2]),col = ColList[i],type="l",cex = .2)}
## legend(3,.2,legend = (1:9)/10,col = ColList, lty = 1)
## dev.off()

