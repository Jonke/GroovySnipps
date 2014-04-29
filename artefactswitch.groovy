def str_search = "Celeste"
def str_replace = "Dawn"
def hi = hudson.model.Hudson.instance
def newtop =  hi.getItem("Laps_Install_Ulysses_Dawn")
  println(newtop.displayName)
  
  def bl = newtop.getBuildersList()
  println(bl)
   def cpl= bl.grep(hudson.plugins.copyartifact.CopyArtifact)
  for(a in cpl){
   println(a) //hudson.plugins.copyartifact.CopyArtifact@b6c502c
   
    def projname= a.getProjectName()

   def buildsel= a.getBuildSelector() 

  def filter =a.getFilter()

    def target=a.getTarget() 

    def isflat=  a.isFlatten() 

     def isopt= a.isOptional() 

println(projname)
     projname = projname.replace(str_search, str_replace)
     def newcopy=new hudson.plugins.copyartifact.CopyArtifact(projname, buildsel,  filter,  target,          isflat, isopt)
     bl.remove(a)
     bl.add(newcopy)
     
  }
